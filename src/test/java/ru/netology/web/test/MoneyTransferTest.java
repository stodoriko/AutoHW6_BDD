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
    int cardOneBalanceStart;
    int cardTwoBalanceStart;
    int cardOneBalanceFinish;
    int cardTwoBalanceFinish;
    int sum;

    @BeforeEach
    void beforeScenarios() {
        open("http://localhost:9999/");
        LoginPage loginPage = new LoginPage();
        DataHelper.AutoInfo authInfo = DataHelper.getAutoInfo();

        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationInfo = DataHelper.getVerificationCodeFor();

        DashboardPage dashboardPage = verificationPage.validVerify(verificationInfo);
        cardOneBalanceStart = dashboardPage.getFirstCardBalance();
        cardTwoBalanceStart = dashboardPage.getSecondCardBalance();
    }

    @Test
    void shouldTransferMoneyFromTwoToOne() {
        DataHelper.CardsInfo cardsInfo = DataHelper.getCardsNumbers();
        CardReplenishment cardReplenishment = dashboardPage.selectCardButton(dashboardPage.cardOne);
        cardReplenishment.topUpTheCard(sum, cardsInfo);
        cardOneBalanceFinish = dashboardPage.getBalance(dashboardPage.cardOne);
        Assertions.assertEquals(cardOneBalanceStart + sum, cardOneBalanceFinish);
    }
}
