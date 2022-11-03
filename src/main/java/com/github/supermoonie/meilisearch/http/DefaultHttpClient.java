package com.github.supermoonie.meilisearch.http;

import com.github.supermoonie.meilisearch.MeiliSearchConfig;
import com.github.supermoonie.meilisearch.http.request.HttpRequest;
import com.github.supermoonie.meilisearch.http.response.BasicHttpResponse;
import com.github.supermoonie.meilisearch.http.response.HttpResponse;
import com.github.supermoonie.meilisearch.utils.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @since 2022/11/2
 */
public class DefaultHttpClient extends AbstractHttpClient {

    public DefaultHttpClient(MeiliSearchConfig meiliSearchConfig) {
        super(meiliSearchConfig);
        allowMethods("PATCH", "PUT");
    }

    /**
     * Create and get a validated HTTP connection to url with method and API key
     *
     * @param url    URL to connect to
     * @param method HTTP method to use for the connection
     * @param apiKey API Key to use for the connection
     * @return Validated connection (otherwise, will throw a {@link IOException})
     * @throws IOException if unable to establish connection
     */
    private HttpURLConnection getConnection(final URL url, final String method, final String apiKey)
            throws IOException {
        if (url == null || "".equals(method))
            throw new IOException("Unable to open an HttpURLConnection with no URL or method");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");

        // Use API key header only if one is provided
        if (StringUtils.isNotBlank(apiKey)) {
            connection.setRequestProperty("Authorization", this.meiliSearchConfig.getBearerApiKey());
        }
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.setUseCaches(false);

        return connection;
    }

    public HttpResponse execute(HttpRequest<?> request) throws IOException {
        URL url = new URL(this.meiliSearchConfig.getHostUrl() + request.getPath());
        HttpURLConnection connection =
                this.getConnection(url, request.getMethod().name(), this.meiliSearchConfig.getApiKey());

        connection.setDoOutput(true);
        if (request.hasContent()) {
            connection.getOutputStream().write(request.getContentAsBytes());
        }
        InputStream errorStream = connection.getErrorStream();
        InputStream inputStream = connection.getInputStream();

        String content = null == inputStream ?
                (
                        null == errorStream ?
                                null
                                :
                                readStreamToString(errorStream)
                )
                :
                readStreamToString(inputStream);

        return new BasicHttpResponse(
                Collections.emptyMap(),
                connection.getResponseCode(),
                content);
    }

    private String readStreamToString(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    private static void allowMethods(String... methods) {
        try {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            methodsField.setAccessible(true);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);
            methodsField.set(null/*static field*/, newMethods);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
