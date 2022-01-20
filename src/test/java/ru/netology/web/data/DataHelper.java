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

    public static VerificationCode getVerificationCodeFor(AutoInfo autoInfo) {
        return new VerificationCode("12345");
    }
}
