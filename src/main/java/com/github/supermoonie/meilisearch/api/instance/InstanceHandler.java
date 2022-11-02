package com.github.supermoonie.meilisearch.api.instance;

import com.github.supermoonie.meilisearch.MeiliSearchHttpRequest;
import com.github.supermoonie.meilisearch.json.JsonHandler;

/**
 * @author Administrator
 * @since 2022/11/3
 */
public class InstanceHandler {

    private final MeiliSearchHttpRequest meiliSearchHttpRequest;
    private final JsonHandler jsonHandler;

    public InstanceHandler(MeiliSearchHttpRequest meiliSearchHttpRequest) {
        this.meiliSearchHttpRequest = meiliSearchHttpRequest;
        this.jsonHandler = meiliSearchHttpRequest.getJsonHandler();
    }

    /**
     * Get stats of all indexes.
     *
     * @return {@link Stats}
     * @throws Exception if an error occurs
     */
    public Stats getStats() throws Exception {
        String urlPath = "/stats";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), Stats.class);
    }

    /**
     * Get stats of an index.
     *
     * @param indexUid uid of the requested index
     * @return {@link Stats.IndexStats}
     * @throws Exception if an error occurs
     */
    public Stats.IndexStats getStats(String indexUid) throws Exception {
        String urlPath = "/indexes/" + indexUid + "/stats";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), Stats.IndexStats.class);
    }

    /**
     * Get health of Meilisearch server.
     *
     * @return {@link Health}
     * @throws Exception if an error occurs
     */
    public Health getHealth() throws Exception {
        String urlPath = "/health";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), Health.class);
    }

    /**
     * Get version of Meilisearch.
     *
     * @return {@link Version}
     * @throws Exception if an error occurs
     */
    public Version getVersion() throws Exception {
        String urlPath = "/version";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), Version.class);
    }
}
