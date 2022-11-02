package com.github.supermoonie.meilisearch.http;

import com.github.supermoonie.meilisearch.MeiliSearchConfig;
import com.github.supermoonie.meilisearch.http.request.HttpRequest;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public abstract class AbstractHttpClient implements HttpClient<HttpRequest<?>> {

    protected final MeiliSearchConfig meiliSearchConfig;

    public AbstractHttpClient(MeiliSearchConfig meiliSearchConfig) {
        this.meiliSearchConfig = meiliSearchConfig;
    }
}
