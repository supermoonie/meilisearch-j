package com.github.supermoonie.meilisearch.api.keys;

import com.github.supermoonie.meilisearch.api.QueryCommonResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Administrator
 * @since 2022/11/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KeysQueryResult extends QueryCommonResult {

    private List<Key> results;
}
