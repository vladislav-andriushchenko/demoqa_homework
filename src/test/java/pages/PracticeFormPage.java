package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.DialogWindow;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    CalendarComponent calendarComponent = new CalendarComponent();
    DialogWindow dialogWindow = new DialogWindow();

    private static final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            phoneInput = $("#userNumber"),
            genderInput = $("#genterWrapper"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            uploadPictureSelector = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit");

    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public PracticeFormPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public PracticeFormPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public PracticeFormPage setPhone(String value) {
        phoneInput.setValue(value);
        return this;
    }

    public PracticeFormPage selectGender(String value) {
        genderInput.$(byText(value)).click();
        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public PracticeFormPage selectSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public PracticeFormPage selectHobby(String value) {
        hobbiesInput.$(byText(value)).click();
        return this;
    }

    public PracticeFormPage uploadPicture(String filePath) {
        uploadPictureSelector.uploadFromClasspath("images/" + filePath);
        return this;
    }

    public PracticeFormPage setCurrentAddress(String imageName) {
        currentAddressInput.setValue(imageName);
        return this;
    }

    public PracticeFormPage setState(String value) {
        stateInput.click();
        $(byText(value)).click();
        return this;
    }

    public PracticeFormPage setCity(String value) {
        cityInput.click();
        $(byText(value)).click();
        return this;
    }

    public void submitForm() {
        submitButton.click();
    }

    public PracticeFormPage checkResult(String value) {
        dialogWindow.checkFieldContent(value);
        return this;
    }

    public PracticeFormPage checkModalTitle(String title) {
        dialogWindow.checkModalTitle(title);
        return this;
    }
}
