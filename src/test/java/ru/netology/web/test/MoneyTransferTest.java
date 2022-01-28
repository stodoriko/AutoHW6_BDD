package ru.netology.web.test;

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
    private int cardOneBalanceStart;
    private int cardTwoBalanceStart;
    private int cardOneBalanceFinish;
    private int cardTwoBalanceFinish;

    @BeforeEach
    void beforeScenarios() {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = DataHelper.getAutoInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationInfo = DataHelper.getVerificationCodeFor();
        dashboardPage = verificationPage.validVerify(verificationInfo);
        cardOneBalanceStart = dashboardPage.getFirstCardBalance();
        cardTwoBalanceStart = dashboardPage.getSecondCardBalance();
    }

    @Test
    void shouldTransferMoneyFromTwoToOne() {
        int sum = 10000;
        var cardInfo = DataHelper.getCardOneInfo();
        var cardReplenishment = dashboardPage.selectCardButton(cardInfo.getCardId());
        dashboardPage = cardReplenishment.topUpTheCard(sum, DataHelper.getCardTwoInfo().getCardNumber());
        cardOneBalanceFinish = dashboardPage.getFirstCardBalance();
        cardTwoBalanceFinish = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(cardOneBalanceStart + sum, cardOneBalanceFinish);
        Assertions.assertEquals(cardTwoBalanceStart - sum, cardTwoBalanceFinish);
    }

    @Test
    void shouldTransferMoneyFromOneToTwo() {
        int sum = 1;
        var cardInfo = DataHelper.getCardTwoInfo();
        var cardReplenishment = dashboardPage.selectCardButton(cardInfo.getCardId());
        dashboardPage = cardReplenishment.topUpTheCard(sum, DataHelper.getCardOneInfo().getCardNumber());
        cardOneBalanceFinish = dashboardPage.getFirstCardBalance();
        cardTwoBalanceFinish = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(cardTwoBalanceStart + sum, cardTwoBalanceFinish);
        Assertions.assertEquals(cardOneBalanceStart - sum, cardOneBalanceFinish);
    }
}