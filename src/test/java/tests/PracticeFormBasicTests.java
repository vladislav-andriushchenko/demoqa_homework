package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Disabled
public class PracticeFormBasicTests {

    String firstName = "Jack";
    String lastName = "Black";
    String email = "testmail@test.com";
    String genderRadioValue = "Male";
    String phone = "7909654321";
    String month = "May";
    String day = "15";
    String year = "1995";
    String subject = "Music";
    String address = "Some address";
    String city = "Karnal";
    String state = "Haryana";
    String hobby = "Music";
    String imageName = "sample-image.jpg";

    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    void successfulMaxFieldsTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("Jack");
        $("#lastName").setValue("Black");
        $("#userEmail").setValue("testmail@test.com");
        $("#gender-radio-1").parent().$(byText("Male")).click();
        $("#userNumber").setValue("7909654321");
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
        $("#uploadPicture").uploadFromClasspath(imageName);
        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".modal-content").shouldHave(text(firstName + " " + lastName));
        $(".modal-content").shouldHave(text(email));
        $(".modal-content").shouldHave(text(genderRadioValue));
        $(".modal-content").shouldHave(text(phone));
        $(".modal-content").shouldHave(text(day + " " + month + "," + year));
        $(".modal-content").shouldHave(text(subject));
        $(".modal-content").shouldHave(text(hobby));
        $(".modal-content").shouldHave(text(imageName));
        $(".modal-content").shouldHave(text(address));
        $(".modal-content").shouldHave(text(state + " " + city));
    }
}
