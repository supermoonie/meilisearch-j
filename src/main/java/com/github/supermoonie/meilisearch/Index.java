package com.github.supermoonie.meilisearch;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.supermoonie.meilisearch.api.documents.DocumentsHandler;
import com.github.supermoonie.meilisearch.api.documents.DocumentsQueryResult;
import com.github.supermoonie.meilisearch.api.search.SearchHandler;
import com.github.supermoonie.meilisearch.api.search.SearchRequest;
import com.github.supermoonie.meilisearch.api.search.SearchResult;
import com.github.supermoonie.meilisearch.api.settings.Settings;
import com.github.supermoonie.meilisearch.api.settings.SettingsHandler;
import com.github.supermoonie.meilisearch.api.task.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Meilisearch index
 *
 * @author Administrator
 * @since 2022/10/31
 */
@ToString
public class Index {

    @Getter
    @Setter
    private String uid;
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date createdAt;
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date updatedAt;
    @Getter
    @Setter
    private String primaryKey;

    @Setter
    @ToString.Exclude
    @JsonIgnore
    private DocumentsHandler documentsHandler;
    @Setter
    @ToString.Exclude
    @JsonIgnore
    private SearchHandler searchHandler;
    @Setter
    @ToString.Exclude
    @JsonIgnore
    private TasksHandler tasksHandler;
    @Setter
    @ToString.Exclude
    @JsonIgnore
    private SettingsHandler settingsHandler;

    /**
     * Performs a search on a given index with a given query
     *
     * @param searchRequest {@link SearchRequest}
     * @return search results, as raw data
     * @throws Exception Search Exception or Client Error
     */
    public <T> SearchResult<T> search(SearchRequest searchRequest, TypeReference<SearchResult<T>> typeReference) throws Exception {
        return this.searchHandler.search(this.uid, searchRequest, typeReference);
    }

    /**
     * Performs a search on a given index with a given query
     *
     * @param q Query to search on index
     * @return search results
     * @throws Exception Search Exception or Client Error
     */
    public <T> SearchResult<T> search(String q, TypeReference<SearchResult<T>> typeReference) throws Exception {
        return this.searchHandler.search(this.uid, q, typeReference);
    }

    /**
     * Gets a document in the index
     *
     * @param identifier Identifier of the document to get
     * @return Meilisearch API response
     * @throws Exception if an error occurs
     */
    public <T> T getDocument(String identifier, Class<T> clazz) throws Exception {
        return this.documentsHandler.getDocument(this.uid, identifier, clazz);
    }

    /**
     * Gets documents in the index
     *
     * @return Meilisearch API response
     * @throws Exception if an error occurs
     */
    public <T> DocumentsQueryResult<T> getDocuments(TypeReference<DocumentsQueryResult<T>> typeReference) throws Exception {
        return this.documentsHandler.getDocuments(this.uid, typeReference);
    }

    /**
     * Gets documents in the index
     *
     * @return Meilisearch API response
     * @throws Exception if an error occurs
     */
    public <T> List<T> getDocuments(int limit, Class<T> elementClass) throws Exception {
        return this.documentsHandler.getDocuments(this.uid, limit, elementClass);
    }

    /**
     * Retrieves the documents at the specified uid
     *
     * @param limit  Limit on the requested documents to be returned
     * @param offset Specify the offset of the first hit to return
     * @return String containing the requested document
     * @throws Exception if the client request causes an error
     */
    public <T> List<T> getDocuments(int limit, int offset, Class<T> elementClass) throws Exception {
        return this.documentsHandler.getDocuments(this.uid, limit, offset, elementClass);
    }

    /**
     * Retrieves the documents at the specified uid
     *
     * @param limit                Limit on the requested documents to be returned
     * @param offset               Specify the offset of the first hit to return
     * @param attributesToRetrieve Document attributes to show
     * @return String containing the requested document
     * @throws Exception if the client request causes an error
     */
    public <T> List<T> getDocuments(int limit, int offset, List<String> attributesToRetrieve, Class<T> elementClass)
            throws Exception {
        return this.documentsHandler.getDocuments(this.uid, limit, offset, attributesToRetrieve, elementClass);
    }

    /**
     * Add an array of documents or replace them if they already exist.
     * If the provided index does not exist, it will be created.
     *
     * @param documents Document to add in JSON string format
     * @return Task Meilisearch API response
     * @throws Exception if an error occurs
     */
    public <D> OpTask addOrReplaceDocuments(List<D> documents) throws Exception {
        return this.documentsHandler.addOrReplaceDocuments(this.uid, documents, null);
    }

    /**
     * Add an array of documents or replace them if they already exist.
     * If the provided index does not exist, it will be created.
     *
     * @param documents  Document to add in JSON string format
     * @param primaryKey PrimaryKey of the document to add
     * @return Task Meilisearch API response
     * @throws Exception if an error occurs
     */
    public <D> OpTask addOrReplaceDocuments(List<D> documents, String primaryKey) throws Exception {
        return this.documentsHandler.addOrReplaceDocuments(this.uid, documents, primaryKey);
    }

    /**
     * Add a list of documents or update them if they already exist.
     * If the provided index does not exist, it will be created.
     *
     * @param documents  Document to add in JSON string format
     * @param primaryKey PrimaryKey of the document to add
     * @return Task Meilisearch API response
     * @throws Exception if an error occurs
     */
    public <D> OpTask addOrUpdateDocuments(List<D> documents, String primaryKey) throws Exception {
        return this.documentsHandler.addOrUpdateDocuments(this.uid, documents, primaryKey);
    }

    /**
     * Add a list of documents or update them if they already exist.
     * If the provided index does not exist, it will be created.
     *
     * @param documents Document to add in JSON string format
     * @return Task Meilisearch API response
     * @throws Exception if an error occurs
     */
    public <D> OpTask addOrUpdateDocuments(List<D> documents) throws Exception {
        return this.documentsHandler.addOrUpdateDocuments(this.uid, documents, null);
    }

    /**
     * Delete all documents in the specified index.
     *
     * @return Meilisearch's Task API response
     * @throws Exception if the client request causes an error
     */
    public OpTask deleteAllDocument() throws Exception {
        return this.documentsHandler.deleteAllDocument(this.uid);
    }

    /**
     * Delete one documents in the specified index.
     *
     * @param documentId of the requested document
     * @return Meilisearch's Task API response
     * @throws Exception if the client request causes an error
     */
    public OpTask deleteOneDocument(String documentId) throws Exception {
        return this.documentsHandler.deleteOneDocument(this.uid, documentId);
    }

    /**
     * Delete all documents in the specified index.
     *
     * @param documentIds of the requested document
     * @return Meilisearch's Task API response
     * @throws Exception if the client request causes an error
     */
    public OpTask deleteDocumentByBatch(List<String> documentIds) throws Exception {
        return this.documentsHandler.deleteDocumentByBatch(this.uid, documentIds);
    }

    /**
     * Retrieves an index tasks by its ID
     *
     * @param taskId Identifier of the requested index task
     * @return Task instance
     * @throws Exception if an error occurs
     */
    public Task getTask(int taskId) throws Exception {
        return this.tasksHandler.getTask(taskId);
    }

    /**
     * Retrieves list of tasks of the index
     *
     * @param taskQueryRequest {@link TaskQueryRequest}
     * @return {@link TaskQueryResult}
     * @throws Exception if an error occurs
     */
    public TaskQueryResult getTasks(TaskQueryRequest taskQueryRequest) throws Exception {
        return this.tasksHandler.getTasks(taskQueryRequest);
    }

    /**
     * Waits for a task to be processed
     *
     * @param taskId Identifier of the requested Task
     * @throws Exception if an error occurs or if timeout is reached
     */
    public Task waitForTask(int taskId) throws Exception {
        return this.tasksHandler.waitForTask(taskId, 5000, 50);
    }

    /**
     * Waits for a task to be processed
     *
     * @param taskId       ID of the index update
     * @param timeoutInMs  number of milliseconds before throwing an Exception
     * @param intervalInMs number of milliseconds before requesting the status again
     * @throws Exception if an error occurs or if timeout is reached
     */
    public Task waitForTask(int taskId, int timeoutInMs, int intervalInMs) throws Exception {
        return this.tasksHandler.waitForTask(taskId, timeoutInMs, intervalInMs);
    }

    /**
     * Get the settings of an index.
     *
     * @return {@link Settings}
     * @throws Exception Search Exception or Client Error
     */
    public Settings getAllSettings() throws Exception {
        return this.settingsHandler.getAllSettings(this.uid);
    }

    /**
     * Update the settings of an index.
     * Passing null to an index setting will reset it to its default value.
     * Updates in the settings route are partial. This means that any parameters not provided in the body will be left unchanged.
     * If the provided index does not exist, it will be created.
     *
     * @param settings {@link Settings}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateAllSettings(Settings settings) throws Exception {
        return this.settingsHandler.updateAllSettings(this.uid, settings);
    }

    /**
     * Reset all the settings of an index to their default value.
     *
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetAllSettings() throws Exception {
        return this.settingsHandler.resetAllSettings(this.uid);
    }

    /**
     * Get the displayed attributes of an index.
     *
     * @return {@link Settings}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getDisplayedAttributes() throws Exception {
        return this.settingsHandler.getDisplayedAttributes(this.uid);
    }

    /**
     * Update the displayed attributes of an index.
     *
     * @param displayAttributes An array of strings. Each string should be an attribute that exists in the selected index.
     *                          If an attribute contains an object, you can use dot notation to specify one or more of its keys,
     *                          e.g., "displayedAttributes": ["release_date.year"].
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateDisplayedAttributes(List<String> displayAttributes) throws Exception {
        return this.settingsHandler.updateDisplayedAttributes(this.uid, displayAttributes);
    }

    /**
     * Reset the displayed attributes of the index to the default value.
     *
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetDisplayedAttributes() throws Exception {
        return this.settingsHandler.resetDisplayedAttributes(this.uid);
    }

    /**
     * Get the distinct attribute of an index.
     *
     * @return {@link Settings}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getDistinctAttributes() throws Exception {
        return this.settingsHandler.getDistinctAttributes(this.uid);
    }

    /**
     * Update the distinct attribute field of an index.
     *
     * @param distinctAttributes A string. The string should be an attribute that exists in the selected index.
     *                           If an attribute contains an object, you can use dot notation to set one or more of its keys as a value for this setting,
     *                           e.g., "distinctAttribute": "product.skuid".
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateDistinctAttributes(List<String> distinctAttributes) throws Exception {
        return this.settingsHandler.updateDistinctAttributes(this.uid, distinctAttributes);
    }

    /**
     * Reset the displayed attributes of the index to the default value.
     *
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetDistinctAttributes() throws Exception {
        return this.settingsHandler.resetDisplayedAttributes(this.uid);
    }

    /**
     * Get the faceting settings of an index.
     *
     * @return {@link Settings}
     * @throws Exception Search Exception or Client Error
     */
    public Settings.Faceting getFaceting() throws Exception {
        return this.settingsHandler.getFaceting(this.uid);
    }

    /**
     * Partially update the faceting settings for an index. Any parameters not provided in the body will be left unchanged.
     *
     * @param faceting {@link Settings.Faceting}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateFaceting(Settings.Faceting faceting) throws Exception {
        return this.settingsHandler.updateFaceting(this.uid, faceting);
    }

    /**
     * Reset an index's faceting settings to their default value.
     *
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetFaceting() throws Exception {
        return this.settingsHandler.resetFaceting(this.uid);
    }

    /**
     * Get the filterable attributes for an index.
     *
     * @return {@link Settings#getFilterableAttributes()}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getFilterableAttributes() throws Exception {
        return this.settingsHandler.getFilterableAttributes(this.uid);
    }

    /**
     * Update an index's filterable attributes list.
     *
     * @param filterableAttributes {@link Settings#getFilterableAttributes()}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateFilterableAttributes(List<String> filterableAttributes) throws Exception {
        return this.settingsHandler.updateFilterableAttributes(this.uid, filterableAttributes);
    }

    /**
     * Reset an index's filterable attributes list back to its default value.
     *
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetFilterableAttributes() throws Exception {
        return this.settingsHandler.resetFilterableAttributes(this.uid);
    }

    /**
     * Get the pagination settings of an index.
     *
     * @return {@link Settings.Pagination}
     * @throws Exception Search Exception or Client Error
     */
    public Settings.Pagination getPagination() throws Exception {
        return this.settingsHandler.getPagination(this.uid);
    }

    /**
     * Partially update the pagination settings for an index.
     *
     * @param pagination {@link Settings.Pagination}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updatePagination(Settings.Pagination pagination) throws Exception {
        return this.settingsHandler.updatePagination(this.uid, pagination);
    }

    /**
     * Reset an index's pagination settings to their default value.
     *
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetPagination() throws Exception {
        return this.settingsHandler.resetPagination(this.uid);
    }

    /**
     * Get the ranking rules of an index.
     *
     * @return {@link Settings#getRankingRules()}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getRankingRules() throws Exception {
        return this.settingsHandler.getRankingRules(this.uid);
    }

    /**
     * Update the ranking rules of an index.
     *
     * @param rankingRules {@link Settings#getRankingRules()}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateRankingRules(List<String> rankingRules) throws Exception {
        return this.settingsHandler.updateRankingRules(this.uid, rankingRules);
    }

    /**
     * Reset the ranking rules of an index to their default value.
     *
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetRankingRules() throws Exception {
        return this.settingsHandler.resetRankingRules(this.uid);
    }

    /**
     * Get the searchable attributes of an index.
     *
     * @return {@link Settings#getSearchableAttributes()} ()}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getSearchableAttributes() throws Exception {
        return this.settingsHandler.getSearchableAttributes(this.uid);
    }

    /**
     * Update the ranking rules of an index.
     *
     * @param searchableAttributes {@link Settings#getSearchableAttributes()}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateSearchableAttributes(List<String> searchableAttributes) throws Exception {
        return this.settingsHandler.updateSearchableAttributes(this.uid, searchableAttributes);
    }

    /**
     * Reset the searchable attributes of the index to the default value.
     *
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetSearchableAttributes() throws Exception {
        return this.settingsHandler.resetSearchableAttributes(this.uid);
    }

    /**
     * Get the sortable attributes of an index.
     *
     * @return {@link Settings#getSortableAttributes()}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getSortableAttributes() throws Exception {
        return this.settingsHandler.getSortableAttributes(this.uid);
    }

    /**
     * Update an index's sortable attributes list.
     *
     * @param sortableAttributes {@link Settings#getSortableAttributes()}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateSortableAttributes(List<String> sortableAttributes) throws Exception {
        return this.settingsHandler.updateSortableAttributes(this.uid, sortableAttributes);
    }

    /**
     * Reset an index's sortable attributes list back to its default value.
     *
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetSortableAttributes() throws Exception {
        return this.settingsHandler.resetSortableAttributes(this.uid);
    }

    /**
     * Get the stop words list of an index.
     *
     * @return {@link Settings#getStopWords()}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getStopWords() throws Exception {
        return this.settingsHandler.getStopWords(this.uid);
    }

    /**
     * Update the list of stop words of an index.
     *
     * @param stopWords {@link Settings#getStopWords()}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateStopWords(List<String> stopWords) throws Exception {
        return this.settingsHandler.updateStopWords(this.uid, stopWords);
    }

    /**
     * Reset the list of stop words of an index to its default value.
     *
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetStopWords() throws Exception {
        return this.settingsHandler.resetStopWords(this.uid);
    }

    /**
     * Get the list of synonyms of an index.
     *
     * @return {@link Settings#getSynonyms()}
     * @throws Exception Search Exception or Client Error
     */
    public Map<String, List<String>> getSynonyms() throws Exception {
        return this.settingsHandler.getSynonyms(this.uid);
    }

    /**
     * Update the list of synonyms of an index. Synonyms are normalized.
     *
     * @param synonyms {@link Settings#getSynonyms()}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateSynonyms(Map<String, List<String>> synonyms) throws Exception {
        return this.settingsHandler.updateSynonyms(this.uid, synonyms);
    }

    /**
     * Reset the list of synonyms of an index to its default value.
     *
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetSynonyms() throws Exception {
        return this.settingsHandler.resetSynonyms(this.uid);
    }

    /**
     * Get the typo tolerance settings of an index.
     *
     * @return {@link Settings.TypoTolerance}
     * @throws Exception Search Exception or Client Error
     */
    public Settings.TypoTolerance getTypoTolerance() throws Exception {
        return this.settingsHandler.getTypoTolerance(this.uid);
    }

    /**
     * Partially update the typo tolerance settings for an index.
     *
     * @param typoTolerance {@link Settings.TypoTolerance}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateTypoTolerance(Settings.TypoTolerance typoTolerance) throws Exception {
        return this.settingsHandler.updateTypoTolerance(this.uid, typoTolerance);
    }

    /**
     * Reset an index's typo tolerance settings to their default value.
     *
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetTypoTolerance() throws Exception {
        return this.settingsHandler.resetTypoTolerance(this.uid);
    }
}
