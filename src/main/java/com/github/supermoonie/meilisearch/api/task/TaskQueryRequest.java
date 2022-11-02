package com.github.supermoonie.meilisearch.api.task;

import lombok.Data;

/**
 * @author Administrator
 * @since 2022/11/2
 */
@Data
public class TaskQueryRequest {

    private Integer limit;
    private Integer from;
    private String status;
    private String type;
    private String indexUid;

    public TaskQueryRequest() {
    }

    public TaskQueryRequest(String indexUid) {
        this.indexUid = indexUid;
        this.limit = 20;
    }

    public TaskQueryRequest(Integer limit, Integer from, String status, String type, String indexUid) {
        this.limit = limit;
        this.from = from;
        this.status = status;
        this.type = type;
        this.indexUid = indexUid;
    }
}
