package com.github.supermoonie.meilisearch.exceptions;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class MeiliSearchRuntimeException extends RuntimeException {

    public MeiliSearchRuntimeException() {
    }

    public MeiliSearchRuntimeException(String message) {
        super(message);
    }

    public MeiliSearchRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MeiliSearchRuntimeException(Throwable cause) {
        super(cause);
    }

    public MeiliSearchRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
