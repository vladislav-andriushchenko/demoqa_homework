package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import pages.components.DialogWindow;

import static utils.TestData.*;

public class PracticeFormPageObjectTests extends TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();
    DialogWindow dialogWindow = new DialogWindow();

    String firstName = getRandomFirstName(),
            lastName = getRandomLastName(),
            email = getRandomEmail(),
            phone = getRandomPhoneNumber(),
            genderRadioValue = getRandomGender(),
            address = getRandomAddress(),
            imageName = getRandomFile(),
            hobby = getRandomHobby(),
            subject = getRandomSubject(),
            state = getRandomState(),
            city = getRandomCity(state),
            month = getRandomMonth(),
            year = getRandomYear(),
            day = getRandomDay();


    @Test
    void successfulMaxFieldsTest() {
        practiceFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhone(phone)
                .selectGender(genderRadioValue)
                .setDateOfBirth(day, month, year)
                .selectSubject(subject)
                .selectHobby(hobby)
                .uploadPicture(imageName)
                .setCurrentAddress(address)
                .setState(state)
                .setCity(city)
                .submitForm();

        dialogWindow.shouldAppear();

        practiceFormPage
                .checkModalTitle("Thanks for submitting the form")
                .checkResult(firstName + " " + lastName)
                .checkResult(email)
                .checkResult(genderRadioValue)
                .checkResult(phone)
                .checkResult(String.format("%02d", Integer.parseInt(day)) + " " + month + "," + year)
                .checkResult(subject)
                .checkResult(hobby)
                .checkResult(imageName)
                .checkResult(address)
                .checkResult(state + " " + city);
    }

    @Test
    void successfulMinFieldsTest() {
        practiceFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhone(phone)
                .selectGender(genderRadioValue)
                .submitForm();

        dialogWindow.shouldAppear();

        practiceFormPage
                .checkModalTitle("Thanks for submitting the form")
                .checkResult(firstName + " " + lastName)
                .checkResult(genderRadioValue)
                .checkResult(phone);
    }

    @Test
    void withoutRequiredFieldsTest() {
        practiceFormPage
                .openPage()
                .submitForm();

        dialogWindow.shouldNotAppear();
    }
}
