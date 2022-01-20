package ru.netology.web.page;

import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPageV1 {

    public VerificationPage validLogin(DataHelper.AutoInfo autoInfo) {
        $("[data-test-id=login] input").setValue(autoInfo.getLogin());
        $("[data-test-id=password] input").setValue(autoInfo.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }

}
