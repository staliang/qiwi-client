package com.staliang.qiwi;

import com.staliang.qiwi.exception.QiwiServiceException;
import com.staliang.qiwi.model.*;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class QiwiClientTest {

    private static final String WALLET = System.getProperty("wallet");
    private static final QiwiClient qiwiClient = new QiwiClient(System.getProperty("token"));

    @Test
    public void getUserProfile() throws IOException {
        UserProfile profile = qiwiClient.getUserProfile(new UserProfileRequest());
        assertNotNull(profile);
    }

    @Test
    public void getPaymentsHistory() throws IOException {
        PaymentsHistory payments = qiwiClient.getPaymentsHistory(WALLET, new PaymentsHistoryRequest(50));
        assertNotNull(payments);
    }

    @Test(expected = QiwiServiceException.class)
    public void getPaymentsHistoryFail() throws IOException {
        qiwiClient.getPaymentsHistory(WALLET, new PaymentsHistoryRequest(200));
    }

    @Test
    public void getPaymentsStatistic() throws IOException {
        PaymentsStatistic statistic = qiwiClient.getPaymentsStatistic(WALLET, new PaymentsStatisticRequest(new Date(), new Date()));
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

    @Test
    public void transferToPhone() throws IOException {
        qiwiClient.transferToPhone("+79090594340", BigDecimal.ONE);
    }

    @Test
    public void getCommissionForTransferToPhone() throws IOException {
        qiwiClient.getCommissionForTransferToPhone("+79090594340", BigDecimal.ONE);
    }
}
