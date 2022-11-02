package com.github.supermoonie.meilisearch.api.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Operate Task
 *
 * @author Administrator
 * @since 2022/10/31
 */
@Data
public class OpTask {

    private Integer taskUid;
    private String indexUid;
    private String status;
    private String type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date enqueuedAt;
}
