package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import pages.components.DialogWindow;

public class PracticeFormPageObjectTests extends TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();
    DialogWindow dialogWindow = new DialogWindow();
    String firstName = "Jack";
    String lastName = "Black";
    String email = "testmail@test.com";
    String genderRadioValue = "Male";
    String phone = "7909654321";
    String month = "May";
    String day = "15";
    String year = "1995";
    String subject = "Maths";
    String address = "Some address";
    String city = "Karnal";
    String state = "Haryana";
    String hobby = "Music";
    String imageName = "sample-image.jpg";

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
                .checkResult( genderRadioValue)
                .checkResult( phone)
                .checkResult( day + " " + month + "," + year)
                .checkResult( subject)
                .checkResult( hobby)
                .checkResult( imageName)
                .checkResult( address)
                .checkResult( state + " " + city);
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
                .checkResult( genderRadioValue)
                .checkResult( phone);
    }

    @Test
    void withoutRequiredFieldsTest() {
        practiceFormPage
                .openPage()
                .submitForm();

        dialogWindow.shouldNotAppear();
    }
}
