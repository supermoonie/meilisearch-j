package com.github.supermoonie.meilisearch.http.response;

import java.util.Map;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public interface HttpResponse {

    Map<String, String> getHeaders();

    int getStatusCode();

    boolean hasContent();

    String getContent();

    byte[] getContentAsBytes();
}
