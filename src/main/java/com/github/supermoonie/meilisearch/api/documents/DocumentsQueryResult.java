package com.github.supermoonie.meilisearch.api.documents;

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
public class DocumentsQueryResult<T> extends QueryCommonResult {

    private List<T> results;
}
