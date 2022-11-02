package com.github.supermoonie.meilisearch.api.documents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.supermoonie.meilisearch.MeiliSearchHttpRequest;
import com.github.supermoonie.meilisearch.api.task.OpTask;
import com.github.supermoonie.meilisearch.json.JsonHandler;
import com.github.supermoonie.meilisearch.utils.StringUtils;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author Administrator
 * @since 2022/10/31
 */
public class DocumentsHandler {

    private final MeiliSearchHttpRequest meilisearchHttpRequest;
    private final JsonHandler jsonHandler;

    public DocumentsHandler(MeiliSearchHttpRequest meilisearchHttpRequest) {
        this.meilisearchHttpRequest = meilisearchHttpRequest;
        this.jsonHandler = meilisearchHttpRequest.getJsonHandler();
    }

    /**
     * Retrieves the document at the specified uid with the specified identifier
     *
     * @param uid        Partial index identifier for the requested documents
     * @param identifier ID of the document
     * @param clazz      Type of the document
     * @return String containing the requested document
     * @throws Exception if client request causes an error
     */
    public <T> T getDocument(String uid, String identifier, Class<T> clazz) throws Exception {
        String urlPath = "/indexes/" + uid + "/documents/" + identifier;
        return jsonHandler.decode(meilisearchHttpRequest.get(urlPath), clazz);
    }

    /**
     * Retrieves the documents at the specified uid
     *
     * @param uid Partial index identifier for the requested documents
     * @return String containing the requested document
     * @throws Exception if the client request causes an error
     */
    public <T> DocumentsQueryResult<T> getDocuments(String uid, TypeReference<DocumentsQueryResult<T>> typeReference) throws Exception {
        String urlPath = "/indexes/" + uid + "/documents";
        return jsonHandler.decode(meilisearchHttpRequest.get(urlPath), typeReference);
    }

    /**
     * Retrieves the documents at the specified uid
     *
     * @param uid   Partial index identifier for the requested documents
     * @param limit Limit on the requested documents to be returned
     * @return String containing the requested document
     * @throws Exception if the client request causes an error
     */
    public <T> List<T> getDocuments(String uid, int limit, Class<T> elementClass) throws Exception {
        String urlQuery = "/indexes/" + uid + "/documents?limit=" + limit;
        return jsonHandler.decodeList(meilisearchHttpRequest.get(urlQuery), elementClass);
    }

    /**
     * Retrieves the documents at the specified uid
     *
     * @param uid    Partial index identifier for the requested documents
     * @param limit  Limit on the requested documents to be returned
     * @param offset Specify the offset of the first hit to return
     * @return String containing the requested document
     * @throws Exception if the client request causes an error
     */
    public <T> List<T> getDocuments(String uid, int limit, int offset, Class<T> elementClass) throws Exception {
        String urlQuery = "/indexes/" + uid + "/documents?limit=" + limit + "&offset=" + offset;
        return jsonHandler.decodeList(meilisearchHttpRequest.get(urlQuery), elementClass);
    }

    /**
     * Retrieves the documents at the specified uid
     *
     * @param uid                  Partial index identifier for the requested documents
     * @param limit                Limit on the requested documents to be returned
     * @param offset               Specify the offset of the first hit to return
     * @param attributesToRetrieve Document attributes to show
     * @return String containing the requested document
     * @throws Exception if the client request causes an error
     */
    public <T> List<T> getDocuments(String uid, int limit, int offset, List<String> attributesToRetrieve, Class<T> elementClass)
            throws Exception {
        if (attributesToRetrieve == null || attributesToRetrieve.size() == 0) {
            attributesToRetrieve = singletonList("*");
        }

        String attributesToRetrieveCommaSeparated = String.join(",", attributesToRetrieve);
        String urlQuery =
                "/indexes/"
                        + uid
                        + "/documents?limit="
                        + limit
                        + "&offset="
                        + offset
                        + "&attributesToRetrieve="
                        + attributesToRetrieveCommaSeparated;

        return jsonHandler.decodeList(meilisearchHttpRequest.get(urlQuery), elementClass);
    }

    /**
     * Adds/Replaces a document at the specified uid
     *
     * @param uid        Partial index identifier for the document
     * @param documents  String containing the document to add
     * @param primaryKey PrimaryKey of the document
     * @return Meilisearch's Task API response
     * @throws Exception if the client request causes an error
     */
    public <D> OpTask addDocuments(String uid, List<D> documents, String primaryKey) throws Exception {
        String urlQuery = "/indexes/" + uid + "/documents";
        if (StringUtils.isNotBlank(primaryKey)) {
            urlQuery += "?primaryKey=" + primaryKey;
        }
        return meilisearchHttpRequest.getJsonHandler().decode(meilisearchHttpRequest.post(urlQuery, documents), OpTask.class);
    }
}
