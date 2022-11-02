package com.github.supermoonie.meilisearch.http.request;

import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Administrator
 * @since 2022/11/2
 */
@Getter
public class BasicHttpRequest implements HttpRequest<String> {

    @Setter
    private HttpMethod method;
    @Setter
    private String path;
    @Setter
    private Map<String, String> headers;
    private String content;

    public BasicHttpRequest(HttpMethod method, String path) {
        this.method = method;
        this.path = path;
    }

    public BasicHttpRequest(HttpMethod method, String path, Map<String, String> headers) {
        this.method = method;
        this.path = path;
        this.headers = headers;
    }

    public BasicHttpRequest(HttpMethod method, String path, Map<String, String> headers, String content) {
        this.method = method;
        this.path = path;
        this.headers = headers;
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
