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
    String subject = "Music";
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
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", genderRadioValue)
                .checkResult("Mobile", phone)
                .checkResult("Date of Birth", day + " " + month + ", " + year)
                .checkResult("Subject", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", imageName)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    @Test
    void successfulMinFieldsTest() {
        practiceFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhone(phone)
                .selectGender(genderRadioValue);

        dialogWindow.shouldNotAppear();

        practiceFormPage
                .checkModalTitle("Thanks for submitting the form")
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", genderRadioValue)
                .checkResult("Mobile", phone);
    }

    @Test
    void withoutRequiredFieldsTest() {
        practiceFormPage
                .openPage()
                .submitForm();

        dialogWindow.shouldNotAppear();
    }
}
