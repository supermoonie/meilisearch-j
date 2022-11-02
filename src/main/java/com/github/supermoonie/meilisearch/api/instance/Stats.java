package com.github.supermoonie.meilisearch.api.instance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author Administrator
 * @since 2022/11/3
 */
@Data
public class Stats {

    private Long databaseSize;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT+8")
    private Date lastUpdate;
    private Map<String, IndexStats> indexes;

    @Data
    public static class IndexStats {
        private Long numberOfDocuments;
        private Boolean isIndexing;
        private Map<String, Long> fieldDistribution;
    }
}
