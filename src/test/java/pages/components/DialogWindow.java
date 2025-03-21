package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DialogWindow {

    private static SelenideElement
            modalContent = $(".modal-content"),
            modalTitle = $(".modal-title"),
            modalWindow = $(".modal-dialog");

    public void checkModalTitle(String title) {
        modalTitle.shouldHave(text(title));
    }

    public void checkFieldContent(String field, String value) {
        modalContent.$(byText(field)).parent().setValue(value);
    }

    public void shouldAppear() {
        modalWindow.should(appear);
    }
    public void shouldNotAppear() {
        modalWindow.shouldNot(appear);
    }

}
