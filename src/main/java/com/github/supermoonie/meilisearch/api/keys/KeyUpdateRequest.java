package com.github.supermoonie.meilisearch.api.keys;

import lombok.Data;

/**
 * @author Administrator
 * @since 2022/11/2
 */
@Data
public class KeyUpdateRequest {

    private String name;
    private String description;
}
