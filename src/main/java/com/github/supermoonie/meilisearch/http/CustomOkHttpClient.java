package com.github.supermoonie.meilisearch.http;

import com.github.supermoonie.meilisearch.MeiliSearchConfig;
import com.github.supermoonie.meilisearch.http.request.HttpRequest;
import com.github.supermoonie.meilisearch.http.response.BasicHttpResponse;
import com.github.supermoonie.meilisearch.http.response.HttpResponse;
import com.github.supermoonie.meilisearch.utils.StringUtils;
import okhttp3.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class CustomOkHttpClient extends AbstractHttpClient {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final RequestBody EMPTY_REQUEST_BODY = RequestBody.create("".getBytes());
    private final OkHttpClient client;

    public CustomOkHttpClient(MeiliSearchConfig meiliSearchConfig, OkHttpClient client) {
        super(meiliSearchConfig);
        this.client = client;
    }

    public CustomOkHttpClient(MeiliSearchConfig meiliSearchConfig) {
        super(meiliSearchConfig);
        this.client = new OkHttpClient();
    }

    @Override
    public HttpResponse execute(HttpRequest<?> request) throws Exception {
        Request okRequest = buildRequest(request);
        Response execute = client.newCall(okRequest).execute();
        return buildResponse(execute);
    }

    private RequestBody getBodyFromRequest(HttpRequest<?> request) {
        if (request.hasContent()) return RequestBody.create(request.getContentAsBytes(), JSON);
        return EMPTY_REQUEST_BODY;
    }

    private Request buildRequest(HttpRequest<?> request) throws MalformedURLException {
        URL url = new URL(this.meiliSearchConfig.getHostUrl() + request.getPath());
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (StringUtils.isNotBlank(this.meiliSearchConfig.getApiKey())) {
            builder.addHeader("Authorization", this.meiliSearchConfig.getBearerApiKey());
        }
        switch (request.getMethod()) {
            case GET:
                builder.get();
                break;
            case POST:
                builder.post(getBodyFromRequest(request));
                break;
            case PUT:
                if (request.hasContent()) builder.put(getBodyFromRequest(request));
                else builder.delete();
                break;
            case DELETE:
                if (request.hasContent()) builder.delete(getBodyFromRequest(request));
                else builder.delete();
                break;
            case PATCH:
                if (request.hasContent()) builder.patch(getBodyFromRequest(request));
                else builder.delete();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + request.getMethod());
        }

        return builder.build();
    }

    private HttpResponse buildResponse(Response response) throws IOException {
        String body = null;
        ResponseBody responseBody = response.body();
        if (responseBody != null) body = responseBody.string();

        return new BasicHttpResponse(parseHeaders(response.headers().toMultimap()), response.code(), body);
    }

    private Map<String, String> parseHeaders(Map<String, List<String>> headers) {
        HashMap<String, String> headerMap = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            headerMap.put(entry.getKey(), String.join("; ", entry.getValue()));
        }
        return headerMap;
    }
}
