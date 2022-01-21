package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.page;

public class LoginPageV3 {

    @FindBy(css = "[data-test-id=login] input")
    private SelenideElement loginField;
    @FindBy(css = "[data-test-id=password] input")
    private SelenideElement passwordField;
    @FindBy(css = "[data-test-id=action-login]")
    private SelenideElement loginButton;

    public VerificationPage validLogin(DataHelper.AutoInfo autoInfo) {
        loginField.setValue(autoInfo.getLogin());
        passwordField.setValue(autoInfo.getPassword());
        loginButton.click();
        return page(VerificationPage.class);
    }
}
