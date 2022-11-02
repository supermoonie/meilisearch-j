package com.github.supermoonie.meilisearch.api.keys;

import com.github.supermoonie.meilisearch.MeiliSearchHttpRequest;
import com.github.supermoonie.meilisearch.exceptions.MeiliSearchInvalidParamsException;
import com.github.supermoonie.meilisearch.json.JsonHandler;

import java.util.List;
import java.util.Objects;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class KeysHandler {

    private final MeiliSearchHttpRequest meiliSearchHttpRequest;
    private final JsonHandler jsonHandler;

    /**
     * Creates and sets up an instance of Key to simplify MeiliSearch API calls to manage keys
     *
     * @param meiliSearchHttpRequest MeiliSearchHttpRequest
     */
    public KeysHandler(MeiliSearchHttpRequest meiliSearchHttpRequest) {
        this.meiliSearchHttpRequest = meiliSearchHttpRequest;
        this.jsonHandler = meiliSearchHttpRequest.getJsonHandler();
    }

    /**
     * Retrieves the Key with the specified uid
     *
     * @param keyOrUid Identifier of the requested Key
     * @return Key instance
     * @throws Exception if client request causes an error
     */
    public Key getOnKey(String keyOrUid) throws Exception {
        String urlPath = "/keys/" + keyOrUid;
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), Key.class);
    }

    /**
     * Retrieves the Key with the specified uid
     *
     * @param keysQueryRequest {@link KeysQueryRequest}
     * @return Keys
     * @throws Exception if client request causes an error
     */
    public KeysQueryResult getAllKeys(KeysQueryRequest keysQueryRequest) throws Exception {
        String urlPath = "/keys?";
        urlPath += "offset=" + Objects.requireNonNullElse(keysQueryRequest.getOffset(), 0);
        urlPath += "&limit=" + Objects.requireNonNullElse(keysQueryRequest.getLimit(), 20);
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), KeysQueryResult.class);
    }

    /**
     * Create an API key with the provided description, permissions, and expiration date.
     *
     * @param createRequest {@link KeyCreateRequest}
     * @return {@link Key}
     * @throws Exception if client request causes an error
     */
    public Key createKey(KeyCreateRequest createRequest) throws Exception {
        String urlPath = "/keys";
        if (null == createRequest.getActions() || createRequest.getActions().size() == 0) {
            createRequest.setActions(List.of("*"));
        }
        if (null == createRequest.getIndexes() || createRequest.getIndexes().size() == 0) {
            createRequest.setIndexes(List.of("*"));
        }
        if (null == createRequest.getExpiresAt()) {
            throw new MeiliSearchInvalidParamsException("ExpiresAt is null!");
        }
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.post(urlPath, createRequest), Key.class);
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
        String urlPath = "/keys/" + keyOrUid;
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.patch(urlPath, updateRequest), Key.class);
    }

    /**
     * Delete the specified API key.
     *
     * @param keyOrUid A valid API key or uid is required.
     * @throws Exception if client request causes an error
     */
    public void deleteKey(String keyOrUid) throws Exception {
        String urlPath = "/keys/" + keyOrUid;
        this.meiliSearchHttpRequest.delete(urlPath);
    }
}
