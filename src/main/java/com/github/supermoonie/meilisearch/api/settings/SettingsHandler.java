package com.github.supermoonie.meilisearch.api.settings;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.supermoonie.meilisearch.MeiliSearchHttpRequest;
import com.github.supermoonie.meilisearch.api.task.OpTask;
import com.github.supermoonie.meilisearch.json.JsonHandler;

import java.util.List;
import java.util.Map;

/**
 * <a href="https://docs.meilisearch.com/reference/api/settings.html">settings</a>
 *
 * @author Administrator
 * @since 2022/11/2
 */
public class SettingsHandler {

    private final MeiliSearchHttpRequest meiliSearchHttpRequest;
    private final JsonHandler jsonHandler;

    /**
     * Constructor for the Meilisearch Settings object
     *
     * @param meiliSearchHttpRequest MeiliSearchHttpRequest
     */
    public SettingsHandler(MeiliSearchHttpRequest meiliSearchHttpRequest) {
        this.meiliSearchHttpRequest = meiliSearchHttpRequest;
        this.jsonHandler = meiliSearchHttpRequest.getJsonHandler();
    }

    /**
     * Get the settings of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Settings}
     * @throws Exception Search Exception or Client Error
     */
    public Settings getAllSettings(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), Settings.class);
    }

    /**
     * Update the settings of an index.
     * Passing null to an index setting will reset it to its default value.
     * Updates in the settings route are partial. This means that any parameters not provided in the body will be left unchanged.
     * If the provided index does not exist, it will be created.
     *
     * @param indexUid uid of the requested index
     * @param settings {@link Settings}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateAllSettings(String indexUid, Settings settings) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.patch(urlPath, settings), OpTask.class);
    }

    /**
     * Reset all the settings of an index to their default value.
     *
     * @param indexUid uid of the requested index
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetAllSettings(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.delete(urlPath), OpTask.class);
    }

    /**
     * Get the displayed attributes of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Settings}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getDisplayedAttributes(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/displayed-attributes";
        return this.jsonHandler.decodeList(this.meiliSearchHttpRequest.get(urlPath), String.class);
    }

    /**
     * Update the displayed attributes of an index.
     *
     * @param indexUid          uid of the requested index
     * @param displayAttributes An array of strings. Each string should be an attribute that exists in the selected index.
     *                          If an attribute contains an object, you can use dot notation to specify one or more of its keys,
     *                          e.g., "displayedAttributes": ["release_date.year"].
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateDisplayedAttributes(String indexUid, List<String> displayAttributes) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/displayed-attributes";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.put(urlPath, displayAttributes), OpTask.class);
    }

    /**
     * Reset the displayed attributes of the index to the default value.
     *
     * @param indexUid uid of the requested index
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetDisplayedAttributes(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/displayed-attributes";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.delete(urlPath), OpTask.class);
    }

    /**
     * Get the distinct attribute of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Settings}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getDistinctAttributes(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/distinct-attribute";
        return this.jsonHandler.decodeList(this.meiliSearchHttpRequest.get(urlPath), String.class);
    }

    /**
     * Update the distinct attribute field of an index.
     *
     * @param indexUid           uid of the requested index
     * @param distinctAttributes A string. The string should be an attribute that exists in the selected index.
     *                           If an attribute contains an object, you can use dot notation to set one or more of its keys as a value for this setting,
     *                           e.g., "distinctAttribute": "product.skuid".
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateDistinctAttributes(String indexUid, List<String> distinctAttributes) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/distinct-attribute";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.put(urlPath, distinctAttributes), OpTask.class);
    }

    /**
     * Reset the displayed attributes of the index to the default value.
     *
     * @param indexUid uid of the requested index
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetDistinctAttributes(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/distinct-attribute";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.delete(urlPath), OpTask.class);
    }

    /**
     * Get the faceting settings of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Settings}
     * @throws Exception Search Exception or Client Error
     */
    public Settings.Faceting getFaceting(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/faceting";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), Settings.Faceting.class);
    }

    /**
     * Partially update the faceting settings for an index. Any parameters not provided in the body will be left unchanged.
     *
     * @param indexUid uid of the requested index
     * @param faceting {@link Settings.Faceting}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateFaceting(String indexUid, Settings.Faceting faceting) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/faceting";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.patch(urlPath, faceting), OpTask.class);
    }

    /**
     * Reset an index's faceting settings to their default value.
     *
     * @param indexUid uid of the requested index
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetFaceting(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/faceting";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.delete(urlPath), OpTask.class);
    }

    /**
     * Get the filterable attributes for an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Settings#getFilterableAttributes()}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getFilterableAttributes(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/filterable-attributes";
        return this.jsonHandler.decodeList(this.meiliSearchHttpRequest.get(urlPath), String.class);
    }

    /**
     * Update an index's filterable attributes list.
     *
     * @param indexUid             uid of the requested index
     * @param filterableAttributes {@link Settings#getFilterableAttributes()}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateFilterableAttributes(String indexUid, List<String> filterableAttributes) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/filterable-attributes";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.put(urlPath, filterableAttributes), OpTask.class);
    }

    /**
     * Reset an index's filterable attributes list back to its default value.
     *
     * @param indexUid uid of the requested index
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetFilterableAttributes(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/filterable-attributes";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.delete(urlPath), OpTask.class);
    }

    /**
     * Get the pagination settings of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Settings.Pagination}
     * @throws Exception Search Exception or Client Error
     */
    public Settings.Pagination getPagination(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/pagination";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), Settings.Pagination.class);
    }

    /**
     * Partially update the pagination settings for an index.
     *
     * @param indexUid   uid of the requested index
     * @param pagination {@link Settings.Pagination}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updatePagination(String indexUid, Settings.Pagination pagination) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/pagination";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.patch(urlPath, pagination), OpTask.class);
    }

    /**
     * Reset an index's pagination settings to their default value.
     *
     * @param indexUid uid of the requested index
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetPagination(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/pagination";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.delete(urlPath), OpTask.class);
    }

    /**
     * Get the ranking rules of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Settings#getRankingRules()}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getRankingRules(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/ranking-rules";
        return this.jsonHandler.decodeList(this.meiliSearchHttpRequest.get(urlPath), String.class);
    }

    /**
     * Update the ranking rules of an index.
     *
     * @param indexUid     uid of the requested index
     * @param rankingRules {@link Settings#getRankingRules()}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateRankingRules(String indexUid, List<String> rankingRules) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/ranking-rules";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.put(urlPath, rankingRules), OpTask.class);
    }

    /**
     * Reset the ranking rules of an index to their default value.
     *
     * @param indexUid uid of the requested index
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetRankingRules(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/ranking-rules";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.delete(urlPath), OpTask.class);
    }

    /**
     * Get the searchable attributes of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Settings#getSearchableAttributes()}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getSearchableAttributes(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/searchable-attributes";
        return this.jsonHandler.decodeList(this.meiliSearchHttpRequest.get(urlPath), String.class);
    }

    /**
     * Update the ranking rules of an index.
     *
     * @param indexUid             uid of the requested index
     * @param searchableAttributes {@link Settings#getSearchableAttributes()}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateSearchableAttributes(String indexUid, List<String> searchableAttributes) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/searchable-attributes";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.put(urlPath, searchableAttributes), OpTask.class);
    }

    /**
     * Reset the searchable attributes of the index to the default value.
     *
     * @param indexUid uid of the requested index
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetSearchableAttributes(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/searchable-attributes";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.delete(urlPath), OpTask.class);
    }

    /**
     * Get the sortable attributes of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Settings#getSortableAttributes()}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getSortableAttributes(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/sortable-attributes";
        return this.jsonHandler.decodeList(this.meiliSearchHttpRequest.get(urlPath), String.class);
    }

    /**
     * Update an index's sortable attributes list.
     *
     * @param indexUid           uid of the requested index
     * @param sortableAttributes {@link Settings#getSortableAttributes()}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateSortableAttributes(String indexUid, List<String> sortableAttributes) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/sortable-attributes";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.put(urlPath, sortableAttributes), OpTask.class);
    }

    /**
     * Reset an index's sortable attributes list back to its default value.
     *
     * @param indexUid uid of the requested index
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetSortableAttributes(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/sortable-attributes";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.delete(urlPath), OpTask.class);
    }

    /**
     * Get the stop words list of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Settings#getStopWords()}
     * @throws Exception Search Exception or Client Error
     */
    public List<String> getStopWords(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/stop-words";
        return this.jsonHandler.decodeList(this.meiliSearchHttpRequest.get(urlPath), String.class);
    }

    /**
     * Update the list of stop words of an index.
     *
     * @param indexUid  uid of the requested index
     * @param stopWords {@link Settings#getStopWords()}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateStopWords(String indexUid, List<String> stopWords) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/stop-words";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.put(urlPath, stopWords), OpTask.class);
    }

    /**
     * Reset the list of stop words of an index to its default value.
     *
     * @param indexUid uid of the requested index
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetStopWords(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/stop-words";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.delete(urlPath), OpTask.class);
    }

    /**
     * Get the list of synonyms of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Settings#getSynonyms()}
     * @throws Exception Search Exception or Client Error
     */
    public Map<String, List<String>> getSynonyms(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/synonyms";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), new TypeReference<>() {
        });
    }

    /**
     * Update the list of synonyms of an index. Synonyms are normalized.
     *
     * @param indexUid uid of the requested index
     * @param synonyms {@link Settings#getSynonyms()}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateSynonyms(String indexUid, Map<String, List<String>> synonyms) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/synonyms";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.put(urlPath, synonyms), OpTask.class);
    }

    /**
     * Reset the list of synonyms of an index to its default value.
     *
     * @param indexUid uid of the requested index
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetSynonyms(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/synonyms";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.delete(urlPath), OpTask.class);
    }

    /**
     * Get the typo tolerance settings of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Settings.TypoTolerance}
     * @throws Exception Search Exception or Client Error
     */
    public Settings.TypoTolerance getTypoTolerance(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/typo-tolerance";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), Settings.TypoTolerance.class);
    }

    /**
     * Partially update the typo tolerance settings for an index.
     *
     * @param indexUid uid of the requested index
     * @param typoTolerance {@link Settings.TypoTolerance}
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask updateTypoTolerance(String indexUid, Settings.TypoTolerance typoTolerance) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/typo-tolerance";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.patch(urlPath, typoTolerance), OpTask.class);
    }

    /**
     * Reset an index's typo tolerance settings to their default value.
     *
     * @param indexUid uid of the requested index
     * @return {@link OpTask}
     * @throws Exception Search Exception or Client Error
     */
    public OpTask resetTypoTolerance(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/settings/typo-tolerance";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.delete(urlPath), OpTask.class);
    }
}
