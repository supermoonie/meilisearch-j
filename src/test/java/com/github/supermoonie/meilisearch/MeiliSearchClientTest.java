package com.github.supermoonie.meilisearch;

import com.github.supermoonie.meilisearch.api.index.Indexes;
import com.github.supermoonie.meilisearch.api.instance.Health;
import com.github.supermoonie.meilisearch.api.instance.Stats;
import com.github.supermoonie.meilisearch.api.instance.Version;
import com.github.supermoonie.meilisearch.api.keys.*;
import com.github.supermoonie.meilisearch.api.task.OpTask;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class MeiliSearchClientTest extends AppTest {

    @Test
    public void testCreateIndex() throws Exception {
        OpTask task = meiliSearchClient.createIndex("book", "id");
        System.out.println(meiliSearchClient.getJsonHandler().encode(task));
    }

    @Test
    public void testGetIndexList() throws Exception {
        Indexes indexes = meiliSearchClient.getIndexes();
        System.out.println(meiliSearchClient.getJsonHandler().encode(indexes));
    }

    @Test
    public void testGetIndex() throws Exception {
        Index index = meiliSearchClient.getIndex("book_");
        System.out.println(meiliSearchClient.getJsonHandler().encode(index));
    }

    @Test
    public void testCreateKey() throws Exception {
        KeyCreateRequest createRequest = new KeyCreateRequest();
        createRequest.setName("test");
        createRequest.setDescription("test test test");
        createRequest.setExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000));
        Key key = meiliSearchClient.createKey(createRequest);
        System.out.println(meiliSearchClient.getJsonHandler().encode(key));
    }

    @Test
    public void testUpdateKey() throws Exception {
        KeyUpdateRequest updateRequest = new KeyUpdateRequest();
        updateRequest.setName("demo");
        updateRequest.setDescription("demo demo demo");
        Key key = meiliSearchClient.updateKey("dd439129-8742-4afd-acf7-4de6d9d3db5f", updateRequest);
        System.out.println(meiliSearchClient.getJsonHandler().encode(key));
    }

    @Test
    public void testDeleteKey() throws Exception {
        meiliSearchClient.deleteKey("0646ecd3-9d22-44fc-b5ad-21da4b0664ab");
    }

    @Test
    public void testGetKey() throws Exception {
        Key key = meiliSearchClient.getKey("0646ecd3-9d22-44fc-b5ad-21da4b0664ab");
        System.out.println(meiliSearchClient.getJsonHandler().encode(key));
    }

    @Test
    public void testGetAllKeys() throws Exception {
        KeysQueryResult allKeys = meiliSearchClient.getAllKeys(new KeysQueryRequest());
        System.out.println(meiliSearchClient.getJsonHandler().encode(allKeys));
    }

    @Test
    public void testInstance() throws Exception {
        Stats stats = meiliSearchClient.getStats();
        Health health = meiliSearchClient.getHealth();
        Version version = meiliSearchClient.getVersion();
        System.out.println(meiliSearchClient.getJsonHandler().encode(stats));
        System.out.println(meiliSearchClient.getJsonHandler().encode(health));
        System.out.println(meiliSearchClient.getJsonHandler().encode(version));
    }
}
