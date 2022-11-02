package com.github.supermoonie.meilisearch;

import com.github.supermoonie.meilisearch.exceptions.ApiError;
import com.github.supermoonie.meilisearch.exceptions.MeiliSearchApiException;
import com.github.supermoonie.meilisearch.http.AbstractHttpClient;
import com.github.supermoonie.meilisearch.http.factory.RequestFactory;
import com.github.supermoonie.meilisearch.http.request.HttpMethod;
import com.github.supermoonie.meilisearch.http.response.HttpResponse;
import com.github.supermoonie.meilisearch.json.JsonHandler;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * @author Administrator
 * @since 2022/11/2
 */
@Getter
public class MeiliSearchHttpRequest {

    private final AbstractHttpClient client;
    private final RequestFactory factory;
    private final JsonHandler jsonHandler;

    /**
     * Constructor for the MeiliSearchHttpRequest
     *
     * @param client      HttpClient for making calls to server
     * @param factory     RequestFactory for generating calls to server
     * @param jsonHandler JsonHandler for encode request body or decode response body
     */
    public MeiliSearchHttpRequest(AbstractHttpClient client, RequestFactory factory, JsonHandler jsonHandler) {
        this.client = client;
        this.factory = factory;
        this.jsonHandler = jsonHandler;
    }

    /**
     * Gets a document at the specified path
     *
     * @param api Path to document
     * @return document that was requested
     * @throws Exception               if the client has an error
     * @throws MeiliSearchApiException if the response is an error
     */
    public String get(String api) throws Exception, MeiliSearchApiException {
        return this.get(api, "");
    }

    /**
     * Gets a document at the specified path with a given parameter
     *
     * @param api    Path to document
     * @param params Parameter to be passed
     * @return document that was requested
     * @throws Exception               if the client has an error
     * @throws MeiliSearchApiException if the response is an error
     */
    public String get(String api, String params) throws Exception, MeiliSearchApiException {
        HttpResponse httpResponse =
                this.client.execute(
                        factory.create(HttpMethod.GET, api + params, Collections.emptyMap(), null));
        if (httpResponse.getStatusCode() >= 400) {
            throw new MeiliSearchApiException(
                    jsonHandler.decode(httpResponse.getContent(), ApiError.class));
        }
        return new String(httpResponse.getContentAsBytes(), StandardCharsets.UTF_8);
    }

    /**
     * Adds a document to the specified path
     *
     * @param api  Path to server
     * @param body Query for search
     * @return results of the search
     * @throws Exception               if the client has an error
     * @throws MeiliSearchApiException if the response is an error
     */
    public <B> String post(String api, B body) throws Exception, MeiliSearchApiException {
        HttpResponse httpResponse =
                this.client.execute(
                        factory.create(HttpMethod.POST, api, Collections.emptyMap(), body));
        if (httpResponse.getStatusCode() >= 400) {
            throw new MeiliSearchApiException(
                    jsonHandler.decode(httpResponse.getContent(), ApiError.class));
        }
        return new String(httpResponse.getContentAsBytes(), StandardCharsets.UTF_8);
    }

    /**
     * Replaces the requested resource with new data
     *
     * @param api  Path to the requested resource
     * @param body Replacement data for the requested resource
     * @return updated resource
     * @throws Exception               if the client has an error
     * @throws MeiliSearchApiException if the response is an error
     */
    public <B> String patch(String api, B body) throws Exception, MeiliSearchApiException {
        HttpResponse httpResponse =
                this.client.execute(factory.create(HttpMethod.PATCH, api, Collections.emptyMap(), body));
        if (httpResponse.getStatusCode() >= 400) {
            throw new MeiliSearchApiException(
                    jsonHandler.decode(httpResponse.getContent(), ApiError.class));
        }
        return new String(httpResponse.getContentAsBytes());
    }

    /**
     * Replaces the requested resource with new data
     *
     * @param api  Path to the requested resource
     * @param body Replacement data for the requested resource
     * @return updated resource
     * @throws Exception               if the client has an error
     * @throws MeiliSearchApiException if the response is an error
     */
    public <B> String put(String api, B body) throws Exception, MeiliSearchApiException {
        HttpResponse httpResponse =
                this.client.execute(factory.create(HttpMethod.PUT, api, Collections.emptyMap(), body));
        if (httpResponse.getStatusCode() >= 400) {
            throw new MeiliSearchApiException(
                    jsonHandler.decode(httpResponse.getContent(), ApiError.class));
        }
        return new String(httpResponse.getContentAsBytes());
    }

    /**
     * Deletes the specified resource
     *
     * @param api Path to the requested resource
     * @return deleted resource
     * @throws Exception               if the client has an error
     * @throws MeiliSearchApiException if the response is an error
     */
    public String delete(String api) throws Exception, MeiliSearchApiException {
        HttpResponse httpResponse =
                this.client.execute(
                        factory.create(HttpMethod.DELETE, api, Collections.emptyMap(), null));
        if (httpResponse.getStatusCode() >= 400) {
            throw new MeiliSearchApiException(
                    jsonHandler.decode(httpResponse.getContent(), ApiError.class));
        }
        if (httpResponse.getStatusCode() == 204) return null;
        return new String(httpResponse.getContentAsBytes());
    }
}
