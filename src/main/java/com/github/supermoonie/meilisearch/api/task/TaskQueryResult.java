package com.github.supermoonie.meilisearch.api.task;

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
public class TaskQueryResult extends QueryCommonResult {

    private List<Task> results;
}
