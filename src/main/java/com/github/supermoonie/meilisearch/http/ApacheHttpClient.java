package com.github.supermoonie.meilisearch.http;

import com.github.supermoonie.meilisearch.MeiliSearchConfig;
import com.github.supermoonie.meilisearch.http.request.HttpRequest;
import com.github.supermoonie.meilisearch.http.response.BasicHttpResponse;
import com.github.supermoonie.meilisearch.http.response.HttpResponse;
import org.apache.hc.client5.http.async.HttpAsyncClient;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.async.methods.SimpleRequestProducer;
import org.apache.hc.client5.http.async.methods.SimpleResponseConsumer;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;

import java.util.Arrays;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class ApacheHttpClient extends AbstractHttpClient {

    private final HttpAsyncClient client;

    public ApacheHttpClient(MeiliSearchConfig meiliSearchConfig) {
        super(meiliSearchConfig);
        final IOReactorConfig ioReactorConfig =
                IOReactorConfig.custom().setSoTimeout(Timeout.ofSeconds(5)).build();
        this.client = HttpAsyncClients.custom().setIOReactorConfig(ioReactorConfig).build();
    }

    public ApacheHttpClient(MeiliSearchConfig meiliSearchConfig, HttpAsyncClient client) {
        super(meiliSearchConfig);
        this.client = client;
    }

    @Override
    public HttpResponse execute(HttpRequest<?> request) throws Exception {
        CompletableFuture<SimpleHttpResponse> response = new CompletableFuture<>();
        client.execute(
                SimpleRequestProducer.create(mapRequest(request)),
                SimpleResponseConsumer.create(),
                null,
                HttpClientContext.create(),
                getCallback(response));
        try {
            return response.thenApply(this::mapResponse).get();
        } catch (CancellationException | ExecutionException e) {
            throw new Exception(e);
        }
    }

    private SimpleHttpRequest mapRequest(HttpRequest<?> request) {
        SimpleHttpRequest httpRequest =
                new SimpleHttpRequest(request.getMethod().name(), request.getPath());
        if (request.hasContent())
            httpRequest.setBody(request.getContentAsBytes(), ContentType.APPLICATION_JSON);
        httpRequest.addHeader("Authorization", this.meiliSearchConfig.getBearerApiKey());
        return httpRequest;
    }

    private HttpResponse mapResponse(SimpleHttpResponse response) {
        return new BasicHttpResponse(
                Arrays.stream(response.getHeaders())
                        .collect(Collectors.toConcurrentMap(NameValuePair::getName, NameValuePair::getValue)),
                response.getCode(),
                response.getBodyText());
    }

    private FutureCallback<SimpleHttpResponse> getCallback(
            CompletableFuture<SimpleHttpResponse> completableFuture) {
        return new FutureCallback<>() {
            @Override
            public void completed(SimpleHttpResponse result) {
                completableFuture.complete(result);
            }

            @Override
            public void failed(Exception ex) {
                completableFuture.completeExceptionally(ex);
            }

            @Override
            public void cancelled() {
                completableFuture.cancel(false);
            }
        };
    }
}
