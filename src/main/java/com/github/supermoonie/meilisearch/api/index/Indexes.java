package com.github.supermoonie.meilisearch.api.index;

import com.github.supermoonie.meilisearch.Index;
import com.github.supermoonie.meilisearch.api.QueryCommonResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Administrator
 * @since 2022/10/31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Indexes extends QueryCommonResult {

    private List<Index> results;
}
