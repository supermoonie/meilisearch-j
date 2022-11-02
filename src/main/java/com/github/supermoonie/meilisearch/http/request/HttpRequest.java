package com.github.supermoonie.meilisearch.http.request;

import java.util.Map;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public interface HttpRequest<T> {

    HttpMethod getMethod();

    void setMethod(HttpMethod method);

    String getPath();

    void setPath(String path);

    Map<String, String> getHeaders();

    void setHeaders(Map<String, String> headers);

    boolean hasContent();

    T getContent();

    byte[] getContentAsBytes();
}
