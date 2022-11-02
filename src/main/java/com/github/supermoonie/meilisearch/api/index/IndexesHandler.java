package com.github.supermoonie.meilisearch.api.index;

import com.github.supermoonie.meilisearch.Index;
import com.github.supermoonie.meilisearch.MeiliSearchHttpRequest;
import com.github.supermoonie.meilisearch.api.task.OpTask;
import com.github.supermoonie.meilisearch.json.JsonHandler;
import com.github.supermoonie.meilisearch.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper around the meiliSearchHttpRequest class to ease usage for Meilisearch indexes
 *
 * @author Administrator
 * @since 2022/11/2
 */
public class IndexesHandler {

    private final MeiliSearchHttpRequest meiliSearchHttpRequest;
    private final JsonHandler jsonHandler;

    /**
     * Creates and sets up an instance of IndexesHandler to simplify Meilisearch API calls to manage
     * indexes
     *
     * @param meiliSearchHttpRequest meiliSearchHttpRequest
     */
    public IndexesHandler(MeiliSearchHttpRequest meiliSearchHttpRequest) {
        this.meiliSearchHttpRequest = meiliSearchHttpRequest;
        this.jsonHandler = meiliSearchHttpRequest.getJsonHandler();
    }

    /**
     * Creates an index with a unique identifier
     *
     * @param uid Unique identifier to create the index with
     * @return Meilisearch API response
     * @throws Exception if an error occurs
     */
    public OpTask create(String uid) throws Exception {
        return this.create(uid, null);
    }

    /**
     * Creates an index with a unique identifier
     *
     * @param uid        Unique identifier to create the index with
     * @param primaryKey Field to use as the primary key for documents in that index
     * @return Meilisearch API response
     * @throws Exception if an error occurs
     */
    public OpTask create(String uid, String primaryKey) throws Exception {
        Map<String, String> params = new HashMap<>(2);
        params.put("uid", uid);
        if (StringUtils.isNotBlank(primaryKey)) {
            params.put("primaryKey", primaryKey);
        }
        return jsonHandler.decode(meiliSearchHttpRequest.post("/indexes", params), OpTask.class);
    }

    /**
     * Gets an index from its unique identifier
     *
     * @param uid Unique identifier of the index to get
     * @return Meilisearch API response
     * @throws Exception if an error occurs
     */
    public Index get(String uid) throws Exception {
        String requestQuery = "/indexes/" + uid;
        return jsonHandler.decode(meiliSearchHttpRequest.get(requestQuery), Index.class);
    }

    /**
     * Gets all indexes in the current Meilisearch instance
     *
     * @return Meilisearch API response
     * @throws Exception if an error occurs
     */
    public Indexes getAll() throws Exception {
        return jsonHandler.decode(meiliSearchHttpRequest.get("/indexes"), Indexes.class);
    }

    /**
     * Updates the primary key of an index in the Meilisearch instance
     *
     * @param uid        Unique identifier of the index to update
     * @param primaryKey New primary key field to use for documents in that index
     * @return Meilisearch API response
     * @throws Exception if an error occurs
     */
    public OpTask updatePrimaryKey(String uid, String primaryKey) throws Exception {
        Map<String, String> params = Map.of(
                "primaryKey", primaryKey
        );
        String requestQuery = "/indexes/" + uid;
        return jsonHandler.decode(meiliSearchHttpRequest.patch(requestQuery, params), OpTask.class);
    }

    /**
     * Deletes an index in the Meilisearch instance
     *
     * @param uid Unique identifier of the index to delete
     * @return Meilisearch API response
     * @throws Exception if an error occurs
     */
    public OpTask delete(String uid) throws Exception {
        String requestQuery = "/indexes/" + uid;
        return jsonHandler.decode(meiliSearchHttpRequest.delete(requestQuery), OpTask.class);
    }
}
