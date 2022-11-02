package com.github.supermoonie.meilisearch.api.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @since 2022/10/30
 */
@Data
public class Task {

    private Long uid;
    private String indexUid;
    private String status;
    private String type;
    private Detail details;
    private String duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date enqueuedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date startedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date finishedAt;
    private Error error;

    @Data
    public static class Detail {
        private Long receivedDocuments;
        private Long indexedDocuments;
    }

    @Data
    public static class Error {
        private String message;
        private String code;
        private String type;
        private String link;
    }
}
