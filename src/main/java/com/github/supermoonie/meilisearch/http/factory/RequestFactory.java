package com.github.supermoonie.meilisearch.http.factory;

import com.github.supermoonie.meilisearch.http.request.HttpMethod;
import com.github.supermoonie.meilisearch.http.request.HttpRequest;

import java.util.Map;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public interface RequestFactory {

    <T> HttpRequest<?> create(HttpMethod method, String path, Map<String, String> headers, T content);
}
