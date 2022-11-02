package com.github.supermoonie.meilisearch.api.settings;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author supermoonie
 * @since 2022/10/31
 */
@Data
public class Settings {

    /**
     * 返回文档中显示的字段
     */
    private List<String> displayedAttributes;
    /**
     * 搜索返回具有给定字段的不同（不同）值的文档
     */
    private List<String> searchableAttributes;
    private List<String> filterableAttributes;
    private List<String> sortableAttributes;
    private List<String> rankingRules;
    private List<String> stopWords;
    /**
     * 类似处理的相关词列表
     */
    private Map<String, List<String>> synonyms;
    private String distinctAttribute;
    private TypoTolerance typoTolerance;
    private Faceting faceting;
    private Pagination pagination;

    @Data
    public static class TypoTolerance {
        private Boolean enabled;
        private MinWordSizeForTypos minWordSizeForTypos;
        private List<String> disableOnWords;
        private List<String> disableOnAttributes;
    }

    @Data
    public static class MinWordSizeForTypos {
        private Integer oneTypo;
        private Integer twoTypos;
    }

    @Data
    public static class Faceting {
        /**
         * Maximum number of facet values returned for each facet.
         * Values are sorted in ascending lexicographical order
         */
        private Integer maxValuesPerFacet;
    }

    @Data
    public static class Pagination {
        /**
         * The maximum number of search results Meilisearch can return
         */
        private Integer maxTotalHits;
    }
}
