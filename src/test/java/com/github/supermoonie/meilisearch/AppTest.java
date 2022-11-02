package com.github.supermoonie.meilisearch;

import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class AppTest {
    protected MeiliSearchClient meiliSearchClient;

    @Before
    public void init() {
        MeiliSearchConfig config = new MeiliSearchConfig("http://127.0.0.1:7700", "wangchao123");
        meiliSearchClient = new MeiliSearchClient(config);
    }
}
