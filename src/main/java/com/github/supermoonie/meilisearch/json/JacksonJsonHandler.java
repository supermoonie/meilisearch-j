package com.github.supermoonie.meilisearch.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.supermoonie.meilisearch.exceptions.MeiliSearchException;
import com.github.supermoonie.meilisearch.exceptions.MeiliSearchRuntimeException;

import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class JacksonJsonHandler implements JsonHandler {

    private final ObjectMapper mapper;

    /**
     * this constructor uses a default ObjectMapper with enabled 'FAIL_ON_UNKNOWN_PROPERTIES'
     * feature.
     */
    public JacksonJsonHandler() {
        this.mapper = new ObjectMapper();
        this.mapper
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * @param mapper ObjectMapper
     */
    public JacksonJsonHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode(Object obj) throws Exception {
        if (obj != null && obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new MeiliSearchException("Error while serializing: ", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T decode(String json, Class<T> targetClass) throws Exception {
        if (json == null) {
            throw new MeiliSearchRuntimeException("String to deserialize is null");
        }
        if (targetClass == String.class) {
            return (T) json;
        }
        try {
            return mapper.readValue(json, targetClass);
        } catch (IOException e) {
            throw new MeiliSearchException("Error while serializing: " + e.getMessage(), e);
        }
    }

    @Override
    public <T> T decode(String json, TypeReference<T> typeReference) throws Exception {
        if (json == null) {
            throw new MeiliSearchRuntimeException("String to deserialize is null");
        }
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new MeiliSearchException("Error while serializing: " + e.getMessage(), e);
        }
    }

    @Override
    public <T> List<T> decodeList(String json, Class<T> elementClass) throws Exception {
        if (json == null) {
            throw new MeiliSearchRuntimeException("String to deserialize is null");
        }
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, elementClass));
        } catch (IOException e) {
            throw new MeiliSearchException("Error while serializing: " + e.getMessage(), e);
        }
    }
}
