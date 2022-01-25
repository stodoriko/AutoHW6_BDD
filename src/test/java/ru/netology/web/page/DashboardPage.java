package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private static ElementsCollection cards = $$(".list__item");
    private static String cardOneInnerText;
    private static String cardTwoInnerText;
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public static int getFirstCardBalance() {
        cardOneInnerText = cards.get(0).text();
        return extractBalance(cardOneInnerText);
    }

    public static int getSecondCardBalance() {
        cardTwoInnerText = cards.get(1).text();
        return extractBalance(cardTwoInnerText);
    }

    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public static String getCardOneNumber() {
        return cardOneInnerText.substring(14);
    }

    public static String getCardTwoNumber() {
        return cardTwoInnerText.substring(14);
    }

    public CardReplenishment selectCardButton(String cardId) {
        SelenideElement element =  $("[data-test-id='"+ cardId +"']");
        element.find("button[data-test-id=action-deposit]").click();
        return new CardReplenishment();
    }

}
