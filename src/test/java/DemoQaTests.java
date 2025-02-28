import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoQaTests {

    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void successfulMaxFieldsTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Jack");
        $("#lastName").setValue("Black");
        $("#userEmail").setValue("testmail@test.com");
        $("#gender-radio-1").parent().$(byText("Male")).click();
        $("#userNumber").setValue("79096543210");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__day--015").click();
        $("#subjectsInput").setValue("M").pressEnter();
        $("#hobbies-checkbox-3").parent().$(byText("Music")).click();
        $("#currentAddress").setValue("Some address");
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Karnal")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/sample-image.jpg"));
        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
    }
}
