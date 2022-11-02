package com.github.supermoonie.meilisearch.api;

import lombok.Data;

/**
 * @author Administrator
 * @since 2022/11/2
 */
@Data
public class QueryCommonResult {

    private Integer offset;
    private Integer limit;
    private Integer total;
}
