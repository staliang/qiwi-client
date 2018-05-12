package com.staliang.qiwi.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserProfile {
    private AuthInfo authInfo;
    private ContractInfo contractInfo;
    private UserInfo userInfo;

    @Data
    public static class AuthInfo {
        private String boundEmail;
        private String ip;
        private Date lastLoginDate;
        private MobilePinInfo mobilePinInfo;
        private PassInfo passInfo;
        private long personId;
        private PinInfo pinInfo;
        private Date registrationDate;

        @Data
        public static class MobilePinInfo {
            private Date lastMobilePinChange;
            private boolean mobilePinUsed;
            private Date nextMobilePinChange;
        }

        @Data
        public static class PassInfo {
            private Date lastPassChange;
            private Date nextPassChange;
            private boolean passwordUsed;
        }

        @Data
        public static class PinInfo {
            private boolean pinUsed;
        }
    }

    @Data
    public static class ContractInfo {
        private boolean blocked;
        private long contractId;
        private Date creationDate;
        private List<IdentificationInfo> identificationInfo;

        @Data
        public static class IdentificationInfo {
            private String bankAlias;
            private IdentificationLevel identificationLevel;

            public enum IdentificationLevel {
                ANONYMOUS,
                SIMPLE,
                VERIFIED,
                FULL;
            }
        }
    }

    @Data
    public static class UserInfo {
        private int defaultPayCurrency;
        private String email;
        private long firstTxnId;
        private String operator;
    }
}
