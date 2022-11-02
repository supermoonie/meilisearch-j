package com.github.supermoonie.meilisearch.exceptions;

import lombok.Getter;

/**
 * @author Administrator
 * @since 2022/11/2
 */
@Getter
public class MeiliSearchException extends Exception {

    private final String message;
    private String type;
    private String code;
    private String link;

    public MeiliSearchException(String message) {
        this.message = message;
    }

    public MeiliSearchException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public MeiliSearchException(String message, String type, String code, String link) {
        this.message = message;
        this.type = type;
        this.code = code;
        this.link = link;
    }

    public MeiliSearchException(ApiError apiError) {
        this.message = apiError.getMessage();
        this.type = apiError.getType();
        this.code = apiError.getCode();
        this.link = apiError.getLink();
    }
}
