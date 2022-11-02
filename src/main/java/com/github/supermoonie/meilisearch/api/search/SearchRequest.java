package com.github.supermoonie.meilisearch.api.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Administrator
 * @since 2022/10/30
 */
@Getter
@Setter
@ToString
public class SearchRequest {

    private String q;
    private int offset;
    private int limit;
    private List<List<String>> filter;
    /**
     * Display the count of matches per facet
     */
    private List<String> facets;
    /**
     * Attributes to display in the returned documents
     */
    private List<String> attributesToRetrieve;
    /**
     * Attributes whose values have to be cropped
     */
    private List<String> attributesToCrop;
    /**
     * Maximum length of cropped value in words
     */
    private int cropLength;
    /**
     * String marking crop boundaries
     */
    private String cropMarker;
    /**
     * Highlight matching terms contained in an attribute
     */
    private List<String> attributesToHighlight;
    /**
     * String inserted at the start of a highlighted term
     */
    private String highlightPreTag;
    /**
     * String inserted at the end of a highlighted term
     */
    private String highlightPostTag;
    /**
     * Return matching terms location
     */
    private Boolean showMatchesPosition;
    /**
     * Sort search results by an attribute's value
     */
    private List<String> sort;
    /**
     * Strategy used to match query terms within documents
     */
    private String matchingStrategy;

    public SearchRequest(String q) {
        this(q, 0, 20);
    }

    public SearchRequest(String q, int offset) {
        this(q, offset, 20);
    }

    public SearchRequest(String q, int offset, int limit) {
        this(q, offset, limit, List.of("*"));
    }

    public SearchRequest(String q, int offset, int limit, List<String> attributesToRetrieve) {
        this(
                q,
                offset,
                limit,
                null,
                null,
                attributesToRetrieve,
                null,
                200,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public SearchRequest(String q,
                         int offset,
                         int limit,
                         List<List<String>> filter,
                         List<String> facets,
                         List<String> attributesToRetrieve,
                         List<String> attributesToCrop,
                         int cropLength,
                         String cropMarker,
                         List<String> attributesToHighlight,
                         String highlightPreTag,
                         String highlightPostTag,
                         Boolean showMatchesPosition,
                         List<String> sort,
                         String matchingStrategy) {
        this.q = q;
        this.offset = offset;
        this.limit = limit;
        this.filter = filter;
        this.facets = facets;
        this.attributesToRetrieve = attributesToRetrieve;
        this.attributesToCrop = attributesToCrop;
        this.cropLength = cropLength;
        this.cropMarker = cropMarker;
        this.attributesToHighlight = attributesToHighlight;
        this.highlightPreTag = highlightPreTag;
        this.highlightPostTag = highlightPostTag;
        this.showMatchesPosition = showMatchesPosition;
        this.sort = sort;
        this.matchingStrategy = matchingStrategy;
    }
}
