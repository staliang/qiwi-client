package com.staliang.qiwi;

import com.google.gson.Gson;
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

    public PaymentList getPayments(String wallet, int rows) throws IOException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            String url = "https://edge.qiwi.com/payment-history/v2/persons/%s/payments?rows=%s";

            HttpGet httpGet = new HttpGet(String.format(url, wallet, rows));
            httpGet.setHeader("Authorization", "Bearer " + token);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            String result = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(result);
            return new Gson().fromJson(result, PaymentList.class);
        }
    }
}
