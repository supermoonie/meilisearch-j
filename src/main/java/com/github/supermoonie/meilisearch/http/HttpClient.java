package com.github.supermoonie.meilisearch.http;

import com.github.supermoonie.meilisearch.http.request.HttpRequest;
import com.github.supermoonie.meilisearch.http.response.HttpResponse;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public interface HttpClient<T extends HttpRequest<?>> {

    HttpResponse execute(T request) throws Exception;
}
