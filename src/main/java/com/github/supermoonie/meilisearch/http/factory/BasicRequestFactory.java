package com.github.supermoonie.meilisearch.http.factory;

import com.github.supermoonie.meilisearch.exceptions.MeiliSearchRuntimeException;
import com.github.supermoonie.meilisearch.http.request.BasicHttpRequest;
import com.github.supermoonie.meilisearch.http.request.HttpMethod;
import com.github.supermoonie.meilisearch.http.request.HttpRequest;
import com.github.supermoonie.meilisearch.json.JsonHandler;

import java.util.Map;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class BasicRequestFactory implements RequestFactory {

    private final JsonHandler jsonHandler;

    public BasicRequestFactory(JsonHandler jsonHandler) {
        this.jsonHandler = jsonHandler;
    }

    @Override
    public <T> HttpRequest<?> create(
            HttpMethod method, String path, Map<String, String> headers, T content) {
        try {
            return new BasicHttpRequest(
                    method,
                    path,
                    headers,
                    content == null ? null : this.jsonHandler.encode(content));
        } catch (Exception e) {
            throw new MeiliSearchRuntimeException(e);
        }
    }
}
