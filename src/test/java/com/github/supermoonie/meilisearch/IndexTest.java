package com.github.supermoonie.meilisearch;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.supermoonie.meilisearch.api.documents.DocumentsQueryResult;
import com.github.supermoonie.meilisearch.api.search.SearchResult;
import com.github.supermoonie.meilisearch.api.settings.Settings;
import com.github.supermoonie.meilisearch.api.task.OpTask;
import com.github.supermoonie.meilisearch.api.task.Task;
import com.github.supermoonie.meilisearch.model.Book;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class IndexTest extends AppTest {

    @Test
    public void testSearch() throws Exception {
        Index index = meiliSearchClient.getIndex("book");
        SearchResult<Book> result = index.search("庆余年", new TypeReference<>() {
        });
        System.out.println(meiliSearchClient.getJsonHandler().encode(result));
    }

    @Test
    public void getDocument() throws Exception {
        Index index = meiliSearchClient.getIndex("book");
        Book book = index.getDocument(String.valueOf(1L), Book.class);
        System.out.println(meiliSearchClient.getJsonHandler().encode(book));
    }

    @Test
    public void getDocuments() throws Exception {
        Index index = meiliSearchClient.getIndex("book");
        DocumentsQueryResult<Book> result = index.getDocuments(new TypeReference<>() {
        });
        System.out.println(meiliSearchClient.getJsonHandler().encode(result));
    }

    @Test
    public void testAddDocuments() throws Exception {
        Index index = meiliSearchClient.getIndex("book");
        List<Book> books = List.of(
                new Book(1L, "庆余年", "猫腻", "一个年轻的病人，因为一次毫不意外的经历，重生到一个完全不同的世界，成为古代庆国伯爵府一个并不光彩的私生子。修行无名功诀，踏足京都官场，继承庞大商团范闲，包裹在他外面的是一层金光闪闪的纸衣，纸衣下面是非常刺眼使人流泪的芥末，芥末下面是甜得发腻的奶油，奶油下面是苦涩无比的毒药壳，壳的中间却有那么一抹亮光"),
                new Book(2L, "诛仙", "萧鼎", "普通少年张小凡为救红颜，手持烧火棍与整个世界为敌，何为正，何为邪，可笑万物如刍狗，谁为覆雨谁翻云！"),
                new Book(3L, "斗破苍穹", "天蚕土豆", "天才少年萧炎在创造了家族空前绝后的修炼纪录后突然成了废人，整整三年时间，家族冷遇，旁人轻视，被未婚妻退婚……种种打击接踵而至。就在他即将绝望的时候，一缕幽魂从他手上的戒指里浮现，一扇全新的大门在面前开启！这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！")
        );
        OpTask opTask = index.addDocuments(books);
        System.out.println(meiliSearchClient.getJsonHandler().encode(opTask));
    }

    @Test
    public void testGetAllSettings() throws Exception {
        Index index = meiliSearchClient.getIndex("book");
        Settings allSettings = index.getAllSettings();
        System.out.println(meiliSearchClient.getJsonHandler().encode(allSettings));
    }

    @Test
    public void testGetDisplayedAttributes() throws Exception {
        Index index = meiliSearchClient.getIndex("book");
        List<String> displayedAttributes = index.getDisplayedAttributes();
        System.out.println(meiliSearchClient.getJsonHandler().encode(displayedAttributes));
    }

    @Test
    public void testGetDistinctAttributes() throws Exception {
        Index index = meiliSearchClient.getIndex("book");
        List<String> distinctAttributes = index.getDistinctAttributes();
        System.out.println(meiliSearchClient.getJsonHandler().encode(distinctAttributes));
    }

    @Test
    public void testGetFilterableAttributes() throws Exception {
        Index index = meiliSearchClient.getIndex("book");
        List<String> filterableAttributes = index.getFilterableAttributes();
        System.out.println(meiliSearchClient.getJsonHandler().encode(filterableAttributes));
    }

    @Test
    public void testGetSynonyms() throws Exception {
        Index index = meiliSearchClient.getIndex("book");
        Map<String, List<String>> synonyms = index.getSynonyms();
        System.out.println(meiliSearchClient.getJsonHandler().encode(synonyms));
    }

    @Test
    public void testUpdateSynonyms() throws Exception {
        Index index = meiliSearchClient.getIndex("book");
        OpTask opTask = index.updateSynonyms(Map.of(
                "庆余年", List.of("qingyunian")
        ));
        Task task = index.waitForTask(opTask.getTaskUid());
        System.out.println(meiliSearchClient.getJsonHandler().encode(task));
    }

    @Test
    public void testResetSynonyms() throws Exception {
        Index index = meiliSearchClient.getIndex("book");
        OpTask opTask = index.resetSynonyms();
        Task task = index.waitForTask(opTask.getTaskUid());
        System.out.println(meiliSearchClient.getJsonHandler().encode(task));
    }
}
