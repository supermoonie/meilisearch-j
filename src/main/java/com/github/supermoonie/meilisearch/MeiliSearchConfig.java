package com.github.supermoonie.meilisearch;

import lombok.Getter;

/**
 * @author Administrator
 * @since 2022/11/2
 */
@Getter
public class MeiliSearchConfig {

    private final String hostUrl;
    private final String apiKey;

    public MeiliSearchConfig(String hostUrl) {
        this(hostUrl, "");
    }

    public MeiliSearchConfig(String hostUrl, String apiKey) {
        this.hostUrl = hostUrl;
        this.apiKey = apiKey;
    }

    public String getBearerApiKey() {
        return "Bearer " + apiKey;
    }
}
