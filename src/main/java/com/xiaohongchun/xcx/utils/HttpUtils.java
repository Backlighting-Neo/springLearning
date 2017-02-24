package com.xiaohongchun.xcx.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Neo on 10/26/16.
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private HttpUtils() {
    }

    public static <T> T get(String url, Class<T> clazz) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

            HttpGet getRequest = new HttpGet(url);

            HttpResponse response = httpClient.execute(getRequest);

            String result = EntityUtils.toString(response.getEntity());

            T t = objectMapper.readValue(result, clazz);

            return t;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static <T> T post(String url, Map<String, String> headers, String body, Class<T> clazz) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

            HttpPost postRequest = new HttpPost(url);

            StringEntity params = new StringEntity(body, ContentType.APPLICATION_JSON);
            postRequest.addHeader("Content-Type", "application/json;charset=UTF-8");
            postRequest.addHeader("Accept", "application/json;charset=UTF-8");

            headers.entrySet().forEach(item->{
                postRequest.addHeader(item.getKey(), item.getValue());
            });

            postRequest.setEntity(params);

            HttpResponse response = httpClient.execute(postRequest);

            String result = EntityUtils.toString(response.getEntity());

            if(!Objects.isNull(clazz)){

                T t = objectMapper.readValue(result, clazz);
                return t;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static <T> T post(String url, String object) {
        return post(url, null, object, null);
    }


}
