package com.staliang.qiwi;

import com.google.gson.Gson;
import com.staliang.qiwi.exception.QiwiServiceException;
import com.staliang.qiwi.exception.model.QiwiError;
import com.staliang.qiwi.model.UserBalance;
import com.staliang.qiwi.model.PaymentList;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@RequiredArgsConstructor
public class QiwiClient {

    private final String token;

    private <T> T get(String url, Class<T> clazz) throws IOException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Authorization", "Bearer " + token);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            String entity = EntityUtils.toString(httpResponse.getEntity());
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                throw new QiwiServiceException(new Gson().fromJson(entity, QiwiError.class));
            }
            return new Gson().fromJson(entity, clazz);
        }
    }

    public PaymentList getPayments(String wallet, int rows) throws IOException {
        String url = "https://edge.qiwi.com/payment-history/v2/persons/%s/payments?rows=%s";
        return get(String.format(url, wallet, rows), PaymentList.class);
    }

    public UserBalance getBalance() throws IOException {
        String url = "https://edge.qiwi.com/funding-sources/v1/accounts/current";
        return get(url, UserBalance.class);
    }
}
