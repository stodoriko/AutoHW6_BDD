package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AutoInfo {
         String login;
         String password;
    }

    public static AutoInfo getAutoInfo() {
        return new AutoInfo("vasya", "qwerty123");
    }

    public static AutoInfo getOtherAutoInfo(AutoInfo original) {
        return new AutoInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
         String code;
    }

    public static VerificationCode getVerificationCodeFor() {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String cardId;
    }

    public static CardInfo getCardOneInfo() {
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardInfo getCardTwoInfo() {
        return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

}
