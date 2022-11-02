package com.github.supermoonie.meilisearch.http.response;

import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Administrator
 * @since 2022/11/2
 */
@Getter
public class BasicHttpResponse implements HttpResponse {

    private final Map<String, String> headers;
    private final int statusCode;
    private final String content;

    public BasicHttpResponse(Map<String, String> headers, int statusCode, String content) {
        this.headers = headers;
        this.statusCode = statusCode;
        this.content = content;
    }

    @Override
    public boolean hasContent() {
        return content != null;
    }

    @Override
    public byte[] getContentAsBytes() {
        return content.getBytes(StandardCharsets.UTF_8);
    }
}
