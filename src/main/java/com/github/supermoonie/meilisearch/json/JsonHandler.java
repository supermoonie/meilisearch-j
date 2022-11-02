package com.github.supermoonie.meilisearch.json;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public interface JsonHandler {

    /**
     * @param obj the Object to serialize
     * @return the serialized Object {@code o}
     * @throws Exception wrapped exceptions of the used json library
     */
    String encode(Object obj) throws Exception;

    /**
     * @param json        Object to deserialize, most of the time this is a string
     * @param targetClass return type
     * @param <T>         Abstract type to deserialize
     * @return the deserialized object
     * @throws Exception wrapped exceptions of the used json library
     */
    <T> T decode(String json, Class<T> targetClass) throws Exception;

    /**
     * @param json          Object to deserialize, most of the time this is a string
     * @param typeReference return type of reference
     * @param <T>           Abstract type to deserialize
     * @return the deserialized object
     * @throws Exception wrapped exceptions of the used json library
     */
    <T> T decode(String json, TypeReference<T> typeReference) throws Exception;

    /**
     * @param json         Object to deserialize, most of the time this is a string
     * @param elementClass return type of List
     * @param <T>          Abstract type to deserialize
     * @return the deserialized object
     * @throws Exception wrapped exceptions of the used json library
     */
    <T> List<T> decodeList(String json, Class<T> elementClass) throws Exception;

}
