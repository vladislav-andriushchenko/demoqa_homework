package utils;

import com.github.javafaker.Faker;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;


public class TestData {

    private static final Faker faker = new Faker();
    private static final String DIR = "src/test/resources/images";

    private static final List<String>
            GENDER = Arrays.asList("Male", "Female", "Other"),
            HOBBIES = Arrays.asList("Sports", "Reading", "Music"),
            SUBJECTS = Arrays.asList("Maths", "Arts", "History", "Biology", "Economics"),
            MONTH = Arrays.asList("January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December");

    private static final Map<String, List<String>> STATE_CITY_MAP = new HashMap<>() {{
        put("NCR", Arrays.asList("Delhi", "Gurgaon", "Noida"));
        put("Uttar Pradesh", Arrays.asList("Agra", "Lucknow", "Merrut"));
        put("Haryana", Arrays.asList("Karnal", "Panipat"));
        put("Rajasthan", Arrays.asList("Jaipur", "Jaiselmer"));
    }};

    public static String getRandomState() {
        return String.valueOf(faker.options().option(STATE_CITY_MAP.keySet().toArray()));
    }

    public static String getRandomCity(String state) {
        List<String> cities = STATE_CITY_MAP.get(state);
        return String.valueOf(faker.options().option(cities.toArray()));
    }

    public static String getRandomFile() {
        var fileNames = new File(DIR).list();
        var index = faker.random().nextInt(requireNonNull(fileNames).length);
        return fileNames[index];
    }

    public static String getRandomMonth() {return MONTH.get(faker.random().nextInt(GENDER.size()));}

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomPhoneNumber() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }

    public static String getRandomDay() {
        return String.format("%02d", faker.number().numberBetween(1, 28));
    }

    public static String getRandomYear() {
        return String.valueOf(faker.number().numberBetween(1900, 2100));
    }

    public static String getRandomGender() {
        return GENDER.get(faker.random().nextInt(GENDER.size()));
    }

    public static String getRandomHobby() {
        return HOBBIES.get(faker.random().nextInt(HOBBIES.size()));
    }

    public static String getRandomSubject() {
        return SUBJECTS.get(faker.random().nextInt(SUBJECTS.size()));
    }
}
