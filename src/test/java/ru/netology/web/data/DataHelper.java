package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AutoInfo {
        private String login;
        private String password;
    }

    public static AutoInfo getAutoInfo() {
        return new AutoInfo("vasya", "qwerty123");
    }

    public static AutoInfo getOtherAutoInfo(AutoInfo original) {
        return new AutoInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor() {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardsInfo {
        private String cardNumber1;
        private String cardNumber2;
    }

    public static CardsInfo getCardsNumbers() {
        return new CardsInfo("5559000000000001", "5559000000000002");
    }

    @Value
    public static class Replenishment {
        private String sum;
        private String fromCard;
    }

    public static Replenishment getRepSum() {
        return new Replenishment("500", "");
    }

}
