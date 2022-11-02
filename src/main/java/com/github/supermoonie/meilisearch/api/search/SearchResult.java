package com.github.supermoonie.meilisearch.api.search;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 * @since 2022/10/30
 */
@Data
public class SearchResult<T> {

    private List<T> hits;
    private Integer offset;
    private Integer limit;
    private Long estimatedTotalHits;
    private Integer processingTimeMs;
    private String query;
}
