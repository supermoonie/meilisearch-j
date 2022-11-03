package com.github.supermoonie.meilisearch;

import com.github.supermoonie.meilisearch.api.documents.DocumentsHandler;
import com.github.supermoonie.meilisearch.api.dump.DumpHandler;
import com.github.supermoonie.meilisearch.api.index.Indexes;
import com.github.supermoonie.meilisearch.api.index.IndexesHandler;
import com.github.supermoonie.meilisearch.api.instance.Health;
import com.github.supermoonie.meilisearch.api.instance.InstanceHandler;
import com.github.supermoonie.meilisearch.api.instance.Stats;
import com.github.supermoonie.meilisearch.api.instance.Version;
import com.github.supermoonie.meilisearch.api.keys.*;
import com.github.supermoonie.meilisearch.api.search.SearchHandler;
import com.github.supermoonie.meilisearch.api.settings.SettingsHandler;
import com.github.supermoonie.meilisearch.api.task.OpTask;
import com.github.supermoonie.meilisearch.api.task.Task;
import com.github.supermoonie.meilisearch.api.task.TasksHandler;
import com.github.supermoonie.meilisearch.exceptions.MeiliSearchException;
import com.github.supermoonie.meilisearch.http.AbstractHttpClient;
import com.github.supermoonie.meilisearch.http.DefaultHttpClient;
import com.github.supermoonie.meilisearch.http.factory.BasicRequestFactory;
import com.github.supermoonie.meilisearch.http.factory.RequestFactory;
import com.github.supermoonie.meilisearch.json.JacksonJsonHandler;
import com.github.supermoonie.meilisearch.json.JsonHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @since 2022/11/2
 */
@Getter
@Slf4j
public class MeiliSearchClient {

    private final JsonHandler jsonHandler;
    private final AbstractHttpClient httpClient;
    private final RequestFactory requestFactory;
    private final MeiliSearchHttpRequest meiliSearchHttpRequest;
    private final IndexesHandler indexesHandler;
    private final DocumentsHandler documentsHandler;
    private final SearchHandler searchHandler;
    private final SettingsHandler settingsHandler;
    private final TasksHandler tasksHandler;
    private final KeysHandler keysHandler;
    private final InstanceHandler instanceHandler;
    private final DumpHandler dumpHandler;

    public MeiliSearchClient(MeiliSearchConfig config) {
        this.jsonHandler = new JacksonJsonHandler();
        this.httpClient = new DefaultHttpClient(config);
        this.requestFactory = new BasicRequestFactory(this.jsonHandler);
        this.meiliSearchHttpRequest = new MeiliSearchHttpRequest(this.httpClient, this.requestFactory, this.jsonHandler);
        this.indexesHandler = new IndexesHandler(this.meiliSearchHttpRequest);
        this.documentsHandler = new DocumentsHandler(this.meiliSearchHttpRequest);
        this.searchHandler = new SearchHandler(this.meiliSearchHttpRequest);
        this.settingsHandler = new SettingsHandler(this.meiliSearchHttpRequest);
        this.tasksHandler = new TasksHandler(this.meiliSearchHttpRequest);
        this.keysHandler = new KeysHandler(this.meiliSearchHttpRequest);
        this.instanceHandler = new InstanceHandler(this.meiliSearchHttpRequest);
        this.dumpHandler = new DumpHandler(this.meiliSearchHttpRequest);
    }

    public MeiliSearchClient(JsonHandler jsonHandler, AbstractHttpClient httpClient) {
        this.jsonHandler = jsonHandler;
        this.httpClient = httpClient;
        this.requestFactory = new BasicRequestFactory(this.jsonHandler);
        this.meiliSearchHttpRequest = new MeiliSearchHttpRequest(this.httpClient, this.requestFactory, this.jsonHandler);
        this.indexesHandler = new IndexesHandler(this.meiliSearchHttpRequest);
        this.documentsHandler = new DocumentsHandler(this.meiliSearchHttpRequest);
        this.searchHandler = new SearchHandler(this.meiliSearchHttpRequest);
        this.settingsHandler = new SettingsHandler(this.meiliSearchHttpRequest);
        this.tasksHandler = new TasksHandler(this.meiliSearchHttpRequest);
        this.keysHandler = new KeysHandler(this.meiliSearchHttpRequest);
        this.instanceHandler = new InstanceHandler(this.meiliSearchHttpRequest);
        this.dumpHandler = new DumpHandler(this.meiliSearchHttpRequest);
    }

    /**
     * Creates index Refer <a href="https://docs.meilisearch.com/reference/api/indexes.html#create-an-index">create-an-index</a>
     *
     * @param uid Unique identifier for the index to create
     * @return Meilisearch API response as Task
     * @throws Exception if an error occurs
     */
    public Index createIndexSync(String uid) throws Exception {
        return createIndexSync(uid, null);
    }

    /**
     * Creates index Refer <a href="https://docs.meilisearch.com/reference/api/indexes.html#create-an-index">create-an-index</a>
     *
     * @param uid Unique identifier for the index to create
     * @return Meilisearch API response as Task
     * @throws Exception if an error occurs
     */
    public OpTask createIndex(String uid) throws Exception {
        return this.createIndex(uid, null);
    }

    /**
     * Creates index Refer <a href="https://docs.meilisearch.com/reference/api/indexes.html#create-an-index">create-an-index</a>
     *
     * @param uid        Unique identifier for the index to create
     * @param primaryKey The primary key of the documents in that index
     * @return Meilisearch API response as Task
     * @throws Exception if an error occurs
     */
    public Index createIndexSync(String uid, String primaryKey) throws Exception {
        OpTask opTask = this.indexesHandler.create(uid, primaryKey);
        Task task = this.tasksHandler.waitForTask(opTask.getTaskUid());
        if (TasksHandler.FAILED.equals(task.getStatus())) {
            Task.Error error = task.getError();
            throw new MeiliSearchException(error);
        }
        return this.getIndex(uid);
    }

    /**
     * Creates index Refer <a href="https://docs.meilisearch.com/reference/api/indexes.html#create-an-index">create-an-index</a>
     *
     * @param uid        Unique identifier for the index to create
     * @param primaryKey The primary key of the documents in that index
     * @return Meilisearch API response as Task
     * @throws Exception if an error occurs
     */
    public OpTask createIndex(String uid, String primaryKey) throws Exception {
        return this.indexesHandler.create(uid, primaryKey);
    }

    /**
     * Gets all indexes Refer
     * <a href="https://docs.meilisearch.com/reference/api/indexes.html#list-all-indexes">list-all-indexes</a>
     *
     * @return List of indexes in the Meilisearch client
     * @throws Exception if an error occurs
     */
    public Indexes getIndexes() throws Exception {
        Indexes indexes = this.indexesHandler.getAll();
        if (null != indexes.getResults()) {
            for (Index index : indexes.getResults()) {
                index.setDocumentsHandler(this.documentsHandler);
                index.setSearchHandler(this.searchHandler);
                index.setTasksHandler(this.tasksHandler);
                index.setSettingsHandler(this.settingsHandler);
            }
        }
        return indexes;
    }

    /**
     * Gets single index by uid Refer
     * <a href="https://docs.meilisearch.com/reference/api/indexes.html#get-one-index">get-one-index</a>
     *
     * @param uid Unique identifier of the index to get
     * @return Meilisearch API response
     * @throws Exception if an error occurs
     */
    public Index getIndex(String uid) throws Exception {
        Index index = this.indexesHandler.get(uid);
        if (null == index) return null;
        index.setDocumentsHandler(this.documentsHandler);
        index.setSearchHandler(this.searchHandler);
        index.setTasksHandler(this.tasksHandler);
        index.setSettingsHandler(this.settingsHandler);
        return index;
    }

    /**
     * Retrieves the Key with the specified uid
     *
     * @param keyOrUid Identifier of the requested Key
     * @return Key instance
     * @throws Exception if client request causes an error
     */
    public Key getKey(String keyOrUid) throws Exception {
        return this.keysHandler.getOnKey(keyOrUid);
    }

    /**
     * Retrieves the Key with the specified uid
     *
     * @param keysQueryRequest {@link KeysQueryRequest}
     * @return Keys
     * @throws Exception if client request causes an error
     */
    public KeysQueryResult getAllKeys(KeysQueryRequest keysQueryRequest) throws Exception {
        return this.keysHandler.getAllKeys(keysQueryRequest);
    }

    /**
     * Create an API key with the provided description, permissions, and expiration date.
     *
     * @param createRequest {@link KeyCreateRequest}
     * @return {@link Key}
     * @throws Exception if client request causes an error
     */
    public Key createKey(KeyCreateRequest createRequest) throws Exception {
        return this.keysHandler.createKey(createRequest);
    }

    /**
     * Update the name and description of an API key.
     * Updates to keys are partial. This means you should provide only the fields you intend to update,
     * as any fields not present in the payload will remain unchanged.
     *
     * @param keyOrUid      A valid API key or uid is required.
     * @param updateRequest {@link KeyUpdateRequest}
     * @return {@link Key}
     * @throws Exception if client request causes an error
     */
    public Key updateKey(String keyOrUid, KeyUpdateRequest updateRequest) throws Exception {
        return this.keysHandler.updateKey(keyOrUid, updateRequest);
    }

    /**
     * Delete the specified API key.
     *
     * @param keyOrUid A valid API key or uid is required.
     * @throws Exception if client request causes an error
     */
    public void deleteKey(String keyOrUid) throws Exception {
        this.keysHandler.deleteKey(keyOrUid);
    }

    /**
     * Get stats of all indexes.
     *
     * @return {@link Stats}
     * @throws Exception if an error occurs
     */
    public Stats getStats() throws Exception {
        return this.instanceHandler.getStats();
    }

    /**
     * Get stats of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Stats.IndexStats}
     * @throws Exception if an error occurs
     */
    public Stats.IndexStats getStats(String indexUid) throws Exception {
        return this.instanceHandler.getStats(indexUid);
    }

    /**
     * Get health of Meilisearch server.
     *
     * @return {@link Health}
     * @throws Exception if an error occurs
     */
    public Health getHealth() throws Exception {
        return this.instanceHandler.getHealth();
    }

    /**
     * Get version of Meilisearch.
     *
     * @return {@link Version}
     * @throws Exception if an error occurs
     */
    public Version getVersion() throws Exception {
        return this.instanceHandler.getVersion();
    }

    /**
     * Triggers a dump creation task. Once the process is complete, a dump is created in the dumps directory.
     * If the dumps directory does not exist yet, it will be created.
     *
     * @return {@link OpTask}
     * @throws Exception if an error occurs
     */
    public OpTask dumps() throws Exception {
        return this.dumpHandler.dumps();
    }
}
