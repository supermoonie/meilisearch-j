package com.github.supermoonie.meilisearch;

import com.github.supermoonie.meilisearch.http.AbstractHttpClient;
import com.github.supermoonie.meilisearch.http.ApacheHttpClient;
import com.github.supermoonie.meilisearch.json.JacksonJsonHandler;
import com.github.supermoonie.meilisearch.json.JsonHandler;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class AppTest {
    protected MeiliSearchClient meiliSearchClient;

    @Before
    public void init() {
        MeiliSearchConfig config = new MeiliSearchConfig("http://127.0.0.1:7700", "");
        AbstractHttpClient httpClient = new ApacheHttpClient(config);
        JsonHandler jsonHandler = new JacksonJsonHandler();
        meiliSearchClient = new MeiliSearchClient(jsonHandler, httpClient);
    }
}
