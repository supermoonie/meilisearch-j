package com.github.supermoonie.meilisearch.api.task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.supermoonie.meilisearch.MeiliSearchHttpRequest;
import com.github.supermoonie.meilisearch.exceptions.MeiliSearchException;
import com.github.supermoonie.meilisearch.json.JsonHandler;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class TasksHandler {

    public static final String SUCCEEDED = "succeeded";
    public static final String FAILED = "failed";

    private final MeiliSearchHttpRequest meiliSearchHttpRequest;
    private final JsonHandler jsonHandler;

    /**
     * Creates and sets up an instance of Task to simplify MeiliSearch API calls to manage tasks
     *
     * @param meiliSearchHttpRequest MeiliSearchHttpRequest
     */
    public TasksHandler(MeiliSearchHttpRequest meiliSearchHttpRequest) {
        this.meiliSearchHttpRequest = meiliSearchHttpRequest;
        this.jsonHandler = meiliSearchHttpRequest.getJsonHandler();
    }

    /**
     * Retrieves the Task at the specified indexUid with the specified taskUid
     *
     * @param taskUid Identifier of the requested Task
     * @return Task instance
     * @throws Exception if client request causes an error
     */
    public Task getTask(int taskUid) throws Exception {
        String urlPath = "/tasks/" + taskUid;
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), Task.class);
    }

    /**
     * Retrieves all TasksHandler at the specified iud
     *
     * @param taskQueryRequest {@link TaskQueryRequest}
     * @return List of task instance
     * @throws Exception if client request causes an error
     */
    public TaskQueryResult getTasks(TaskQueryRequest taskQueryRequest) throws Exception {
        String urlPath = "/tasks?";
        urlPath += "limit=" + Objects.requireNonNullElse(taskQueryRequest.getLimit(), 20);
        urlPath += "&from=" + Objects.requireNonNullElse(taskQueryRequest.getFrom(), "");
        urlPath += "&status=" + Objects.requireNonNullElse(taskQueryRequest.getStatus(), "*");
        urlPath += "&type=" + Objects.requireNonNullElse(taskQueryRequest.getType(), "*");
        urlPath += "&indexUid=" + Objects.requireNonNullElse(taskQueryRequest.getIndexUid(), "*");
        return this.jsonHandler.decode(this.meiliSearchHttpRequest.get(urlPath), new TypeReference<>() {
        });
    }

    /**
     * Waits for a task to be processed
     *
     * @param taskUid Identifier of the Task
     * @throws Exception if timeout is reached
     */
    public Task waitForTask(int taskUid) throws Exception {
        return this.waitForTask(taskUid, 5000, 50);
    }

    /**
     * Waits for a task to be processed
     *
     * @param taskUid      Identifier of the Task
     * @param timeoutInMs  number of milliseconds before throwing an Exception
     * @param intervalInMs number of milliseconds before requesting the status again
     * @throws Exception if timeout is reached
     */
    public Task waitForTask(int taskUid, int timeoutInMs, int intervalInMs) throws Exception {
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
        for (; ; ) {
            if (elapsedTime >= timeoutInMs) {
                throw new MeiliSearchException(String.format("Wait task {%d} timeout!", taskUid));
            }
            Task task = getTask(taskUid);
            if (task.getStatus().equals(SUCCEEDED) || task.getStatus().equals(FAILED)) {
                return task;
            }
            TimeUnit.MILLISECONDS.sleep(intervalInMs);
            elapsedTime = System.currentTimeMillis() - startTime;
        }
    }
}
