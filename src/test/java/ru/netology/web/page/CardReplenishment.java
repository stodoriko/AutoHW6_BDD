package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class CardReplenishment {

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement sum = $("[div[data-test-id=amount] input");
    private SelenideElement whenceField = $("span[data-test-id=from] input");
    private SelenideElement topButton = $("button[data-test-id=action-transfer]");

    public CardReplenishment() {
        heading.shouldBe(Condition.visible);
    }

    public void topUpTheCard(int amount, DataHelper.CardsInfo cardsInfo) {
        sum.setValue(String.valueOf(amount));
        whenceField.setValue(cardsInfo.getCardNumber2());
        topButton.click();
    }
}
