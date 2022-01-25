package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class CardReplenishment {

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement sum = $("[data-test-id=amount] input");
    private SelenideElement whenceField = $("span[data-test-id=from] input");
    private SelenideElement topButton = $("button[data-test-id=action-transfer]");

    public CardReplenishment() {
        heading.shouldBe(Condition.visible);
    }

    public int topUpTheCard(int amount, int card) {
        sum.setValue(String.valueOf(amount));
        switch (card) {
            case 1:
                whenceField.setValue("000000000000" + DashboardPage.getCardOneNumber());
                break;
            case 2:
                whenceField.setValue("000000000000" + DashboardPage.getCardTwoNumber());
                break;
        }
        sum.setValue(String.valueOf(amount));
        topButton.click();
        return amount;
    }
}
