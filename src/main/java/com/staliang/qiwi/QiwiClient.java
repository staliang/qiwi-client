package com.staliang.qiwi;

import com.google.gson.Gson;
import com.staliang.qiwi.exception.QiwiServiceException;
import com.staliang.qiwi.model.*;
import lombok.RequiredArgsConstructor;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class QiwiClient {

    private static final String APPLICATION_JSON = "application/json";

    private final String token;

    private <T> T get(String url, Class<T> clazz) throws IOException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Authorization", "Bearer " + token);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            String entity = EntityUtils.toString(httpResponse.getEntity());
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                throw new QiwiServiceException(new Gson().fromJson(entity, Map.class));
            }
            return new Gson().fromJson(entity, clazz);
        }
    }

    private <T> T post(String url, Map<String, String> params, Class<T> clazz) throws IOException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Authorization", "Bearer " + token);
            List<NameValuePair> formData = params.entrySet().stream()
                    .map(e -> new BasicNameValuePair(e.getKey(), e.getValue()))
                    .collect(toList());
            httpPost.setEntity(new UrlEncodedFormEntity(formData));

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            String entity = EntityUtils.toString(httpResponse.getEntity());
            return new Gson().fromJson(entity, clazz);
        }
    }

    private <T> T post(String url, Object params, Class<T> clazz) throws IOException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Authorization", "Bearer " + token);
            httpPost.setHeader("Accept", APPLICATION_JSON);
            httpPost.setHeader("Content-type", APPLICATION_JSON);
            httpPost.setEntity(new StringEntity(new Gson().toJson(params)));

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            String entity = EntityUtils.toString(httpResponse.getEntity());
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                throw new QiwiServiceException(new Gson().fromJson(entity, Map.class));
            }
            return new Gson().fromJson(entity, clazz);
        }
    }

    public UserProfile getUserProfile(UserProfileRequest request) throws IOException {
        String url = "https://edge.qiwi.com/person-profile/v1/profile/current?%s";
        return get(String.format(url, request), UserProfile.class);
    }

    public PaymentList getPayments(String wallet, PaymentListRequest getPaymentsRequest) throws IOException {
        String url = "https://edge.qiwi.com/payment-history/v2/persons/%s/payments?%s";
        return get(String.format(url, wallet, getPaymentsRequest), PaymentList.class);
    }

    public PaymentsStatistic getPaymentsStatistic(String wallet, PaymentsStatisticRequest request) throws IOException {
        String url = "https://edge.qiwi.com/payment-history/v2/persons/%s/payments/total?%s";
        return get(String.format(url, wallet, request), PaymentsStatistic.class);
    }

    public UserBalance getBalance() throws IOException {
        String url = "https://edge.qiwi.com/funding-sources/v1/accounts/current";
        return get(url, UserBalance.class);
    }

    public String getOperatorId(String phone) throws IOException {
        String url = "https://qiwi.com/mobile/detect.action";
        Map result = post(url, Collections.singletonMap("phone", phone), Map.class);
        String message = (String) result.get("message");

        Map<String, Object> code = (Map<String, Object>) result.get("code");
        Object codeValue = code.get("value");
        if ("0".equals(codeValue)) {
            return message;
        }

        throw new RuntimeException(message);
    }

    public TransferResponse transfer(String phone, BigDecimal amount) throws IOException {
        String url = String.format("https://edge.qiwi.com/sinap/api/v2/terms/%s/payments", getOperatorId(phone));
        TransferRequest request = new TransferRequest()
                .setId("" + System.currentTimeMillis())
                .setSum(new Sum(amount, "643"))
                .setFields(new Fields(phone.substring(2, phone.length())));
        return post(url, request, TransferResponse.class);
    }
}
