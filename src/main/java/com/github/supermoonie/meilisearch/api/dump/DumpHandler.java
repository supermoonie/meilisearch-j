package com.github.supermoonie.meilisearch.api.dump;

import com.github.supermoonie.meilisearch.MeiliSearchHttpRequest;
import com.github.supermoonie.meilisearch.api.task.OpTask;
import com.github.supermoonie.meilisearch.json.JsonHandler;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class DumpHandler {

    private final MeiliSearchHttpRequest meiliSearchHttpRequest;
    private final JsonHandler jsonHandler;

    /**
     * Creates and sets up an instance of Dump to simplify Meilisearch API calls to manage dumps
     *
     * @param meiliSearchHttpRequest MeiliSearchHttpRequest
     */
    public DumpHandler(MeiliSearchHttpRequest meiliSearchHttpRequest) {
        this.meiliSearchHttpRequest = meiliSearchHttpRequest;
        this.jsonHandler = meiliSearchHttpRequest.getJsonHandler();
    }

    /**
     * Triggers a dump creation task. Once the process is complete, a dump is created in the dumps directory.
     * If the dumps directory does not exist yet, it will be created.
     *
     * @return {@link OpTask}
     * @throws Exception if an error occurs
     */
    public OpTask dumps() throws Exception {
        String urlPath = "/dumps";
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.post(urlPath, null), OpTask.class);
    }
}
