package ru.netology.web.test;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CardReplenishment;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    DashboardPage dashboardPage;
    private static int cardOneBalanceStart;
    private static int cardTwoBalanceStart;
    private static int cardOneBalanceFinish;
    private static int cardTwoBalanceFinish;

    @BeforeEach
    void beforeScenarios() {
        open("http://localhost:9999/");
        LoginPage loginPage = new LoginPage();
        DataHelper.AutoInfo authInfo = DataHelper.getAutoInfo();

        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationInfo = DataHelper.getVerificationCodeFor();

        dashboardPage = verificationPage.validVerify(verificationInfo);
        cardOneBalanceStart = dashboardPage.getFirstCardBalance();
        cardTwoBalanceStart = dashboardPage.getSecondCardBalance();
    }

    @Test
    void shouldTransferMoneyFromTwoToOne() {
        int sum = 10000;
        DataHelper.CardInfo cardInfo = DataHelper.getCardOneId();
        CardReplenishment cardReplenishment = dashboardPage.selectCardButton(cardInfo.getCardId());
        cardReplenishment.topUpTheCard(sum, 1);
        cardOneBalanceFinish = DashboardPage.getFirstCardBalance();
        Assertions.assertEquals(cardOneBalanceStart + sum, cardOneBalanceFinish);
    }

    @Test
    void shouldTransferMoneyFromOneToTwo() {
        int sum = 1;
        DataHelper.CardInfo cardInfo = DataHelper.getCardTwoId();
        CardReplenishment cardReplenishment = dashboardPage.selectCardButton(cardInfo.getCardId());
        cardReplenishment.topUpTheCard(sum, 2);
        cardTwoBalanceFinish = DashboardPage.getSecondCardBalance();
        Assertions.assertEquals(cardTwoBalanceStart + sum, cardTwoBalanceFinish);
    }
}
