package com.github.supermoonie.meilisearch.api.instance;

import lombok.Data;

/**
 * @author Administrator
 * @since 2022/11/3
 */
@Data
public class Version {

    private String commitSha;
    private String commitDate;
    private String pkgVersion;
}
