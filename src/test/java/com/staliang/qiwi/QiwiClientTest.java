package com.staliang.qiwi;

import com.staliang.qiwi.exception.QiwiServiceException;
import com.staliang.qiwi.model.*;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class QiwiClientTest {

    private QiwiClient qiwiClient = new QiwiClient("5c89d74db04ad3d72eec1e908f629e37");

    @Test
    public void getUserProfile() throws IOException {
        UserProfile profile = qiwiClient.getUserProfile(new UserProfileRequest());
        assertNotNull(profile);
    }

    @Test
    public void getPaymentsHistory() throws IOException {
        PaymentsHistory payments = qiwiClient.getPaymentsHistory("79120148398", new PaymentsHistoryRequest(50));
        assertNotNull(payments);
    }

    @Test(expected = QiwiServiceException.class)
    public void getPaymentsHistoryFail() throws IOException {
        qiwiClient.getPaymentsHistory("79120148398", new PaymentsHistoryRequest(200));
    }

    @Test
    public void getPaymentsStatistic() throws IOException {
        PaymentsStatistic statistic = qiwiClient.getPaymentsStatistic("79120148398", new PaymentsStatisticRequest(new Date(), new Date()));
        assertNotNull(statistic);
    }

    @Test
    public void getTransactionInfo() throws IOException {
        TransactionInfo info = qiwiClient.getTransactionInfo(13073436766L);
        assertNotNull(info);
    }

    @Test
    public void getBalance() throws IOException {
        UserBalance balance = qiwiClient.getBalance();
        assertNotNull(balance);
    }
}
