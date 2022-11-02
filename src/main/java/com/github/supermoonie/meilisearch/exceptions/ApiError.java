package com.github.supermoonie.meilisearch.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @since 2022/11/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private String message;
    private String code;
    private String type;
    private String link;
}
