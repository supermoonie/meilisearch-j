package com.github.supermoonie.meilisearch.api.search;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.supermoonie.meilisearch.MeiliSearchHttpRequest;
import com.github.supermoonie.meilisearch.json.JsonHandler;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class SearchHandler {

    private final MeiliSearchHttpRequest meilisearchHttpRequest;
    private final JsonHandler jsonHandler;

    public SearchHandler(MeiliSearchHttpRequest meilisearchHttpRequest) {
        this.meilisearchHttpRequest = meilisearchHttpRequest;
        this.jsonHandler = meilisearchHttpRequest.getJsonHandler();
    }

    /**
     * Performs a search on a given index with a given query
     *
     * @param uid Index identifier
     * @param q   Query to search on index
     * @return search results
     * @throws Exception Search Exception or Client Error
     */
    public <T> SearchResult<T> search(String uid, String q, TypeReference<SearchResult<T>> typeReference) throws Exception {
        String requestQuery = "/indexes/" + uid + "/search";
        SearchRequest sr = new SearchRequest(q);
        return jsonHandler.decode(meilisearchHttpRequest.post(requestQuery, sr), typeReference);
    }

    /**
     * Performs a search on a given index with a given query
     *
     * @param uid           Index id
     * @param searchRequest {@link SearchRequest}
     * @return search results, as raw data
     * @throws Exception Search Exception or Client Error
     */
    public <T> SearchResult<T> search(String uid, SearchRequest searchRequest, TypeReference<SearchResult<T>> typeReference) throws Exception {
        String requestQuery = "/indexes/" + uid + "/search";
        return jsonHandler.decode(meilisearchHttpRequest.post(requestQuery, searchRequest), typeReference);
    }
}
