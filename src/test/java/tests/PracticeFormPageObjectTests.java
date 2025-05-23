package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.PracticeFormPage;
import pages.components.DialogWindow;
import utils.TestBase;

import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static utils.TestData.*;

@Epic("Practice Form")
@Feature("Practice Form Submission")
@Owner("vladislav-andriushchenko")

public class PracticeFormPageObjectTests extends TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();
    DialogWindow dialogWindow = new DialogWindow();

    String firstName, lastName, email, phone, genderRadioValue, address, imageName, hobby,
            subject, state, city, month, year, day;

    static Stream<Arguments> errorData() {
        return Stream.of(
                Arguments.of(getRandomFirstName(), "", getRandomPhoneNumber(), getRandomEmail()),
                Arguments.of("", getRandomLastName(), getRandomPhoneNumber(), getRandomEmail()),
                Arguments.of(getRandomFirstName(), getRandomLastName(), getRandomPhoneNumber(), "test@test"),
                Arguments.of(getRandomFirstName(), getRandomLastName(), "", getRandomEmail()));
    }

    @Test
    @DisplayName("Проверка успешного сценария с максимальными полями")
    void successfulMaxFieldsTest() {
        firstName = getRandomFirstName();
        lastName = getRandomLastName();
        email = getRandomEmail();
        phone = getRandomPhoneNumber();
        genderRadioValue = getRandomGender();
        address = getRandomAddress();
        imageName = getRandomFile();
        hobby = getRandomHobby();
        subject = getRandomSubject();
        state = getRandomState();
        city = getRandomCity(state);
        month = getRandomMonth();
        year = getRandomYear();
        day = getRandomDay();

        step("Заполнение формы", () -> {
            practiceFormPage
                    .openPage()
                    .removeBanner()
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
        });

        step("Проверить наличие модального окна с инфомацией о пользователе", () -> {
            dialogWindow.shouldAppear();
        });

        step("Проверить наличие сохранененной информации о пользователе", () -> {
            practiceFormPage
                    .checkModalTitle("Thanks for submitting the form")
                    .checkResult(firstName + " " + lastName)
                    .checkResult(email)
                    .checkResult(genderRadioValue)
                    .checkResult(phone)
                    .checkResult(day + " " + month + "," + year)
                    .checkResult(subject)
                    .checkResult(hobby)
                    .checkResult(imageName)
                    .checkResult(address)
                    .checkResult(state + " " + city);
        });
    }

    @Test
    @DisplayName("Проверка успешного сценария с минимальными полями")
    void successfulMinFieldsTest() {
        firstName = getRandomFirstName();
        lastName = getRandomLastName();
        phone = getRandomPhoneNumber();
        genderRadioValue = getRandomGender();

        step("Заполнение формы", () -> {
            practiceFormPage
                    .openPage()
                    .removeBanner()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setPhone(phone)
                    .selectGender(genderRadioValue)
                    .submitForm();
        });

        step("Проверить наличие модального окна с инфомацией о пользователе", () -> {
            dialogWindow.shouldAppear();
        });

        step("Проверить наличие сохранененной информации о пользователе", () -> {
            practiceFormPage
                    .checkModalTitle("Thanks for submitting the form")
                    .checkResult(firstName + " " + lastName)
                    .checkResult(genderRadioValue)
                    .checkResult(phone);
        });
    }

    @Test
    @DisplayName("Проверка получения ошибки при отсутствии обязательных полей")
    void checkWithoutRequiredFieldsTest() {
        step("Заполнение формы", () -> {
            practiceFormPage
                    .openPage()
                    .removeBanner()
                    .submitForm();
        });

        step("Проверить отсутствие модального окна", () -> {
            dialogWindow.shouldNotAppear();
        });
    }

    @ParameterizedTest(name = "Проверка получения ошибки при передачи некорректного номера телефона = {0}")
    @ValueSource(strings = {"123456789", "0", "phonenumba"})
    void checkIncorrectPhoneNumberTest(String phoneNumber) {
        step("Заполнение формы", () -> {
            practiceFormPage
                    .openPage()
                    .removeBanner()
                    .setFirstName(getRandomFirstName())
                    .setLastName(getRandomLastName())
                    .setPhone(phoneNumber)
                    .selectGender(getRandomGender())
                    .submitForm();
        });

        step("Проверить отсутствие модального окна", () -> {
            dialogWindow.shouldNotAppear();
        });

        step("Проверить наличие валидации", () -> {
            practiceFormPage.checkRequiredPhoneField();
        });
    }

    @ParameterizedTest()
    @DisplayName("Проверка получения ошибки при передачи некорректный данных пользователя")
    @MethodSource("errorData")
    void checkInvalidDataTest(String firstName, String lastName, String phone, String email) {
        step("Заполнение формы", () -> {
            practiceFormPage
                    .openPage()
                    .removeBanner()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setPhone(phone)
                    .setEmail(email)
                    .selectGender(getRandomGender())
                    .submitForm();
        });

        step("Проверить отсутствие модального окна", () -> {
            dialogWindow.shouldNotAppear();
        });
    }

    @ParameterizedTest(name = "Проверка успешного сценария с различными параметрами чекбоксов {0} и {1}")
    @CsvSource(value = {
            "Male, Sports",
            "Female, Reading",
            "Other, Music"
    })
    void checkDifferentCheckboxTest(String gender, String hobby) {
        step("Заполнение формы", () -> {
            practiceFormPage
                    .openPage()
                    .removeBanner()
                    .setFirstName(getRandomFirstName())
                    .setLastName(getRandomLastName())
                    .setPhone(getRandomPhoneNumber())
                    .selectGender(gender)
                    .selectHobby(hobby)
                    .submitForm();
        });

        step("Проверить наличие модального окна с инфомацией о пользователе", () -> {
            dialogWindow.shouldAppear();
        });

        step("Проверить наличие сохранененной информации о пользователе", () -> {
            practiceFormPage
                    .checkModalTitle("Thanks for submitting the form")
                    .checkResult(gender)
                    .checkResult(hobby);
        });
    }
}
