package com.github.supermoonie.meilisearch.api.keys;

import lombok.Data;

/**
 * @author Administrator
 * @since 2022/11/2
 */
@Data
public class KeysQueryRequest {

    private Integer offset;
    private Integer limit;
}
