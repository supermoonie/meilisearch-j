package com.github.supermoonie.meilisearch.exceptions;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class MeiliSearchApiException extends MeiliSearchRuntimeException {

    private final ApiError error;

    public MeiliSearchApiException(ApiError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.getMessage();
    }

    public String getCode() {
        return error.getCode();
    }

    public String getType() {
        return error.getType();
    }

    public String getLink() {
        return error.getLink();
    }
}
