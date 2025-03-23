package utils;

import com.github.javafaker.Faker;

import java.io.File;
import java.util.*;

import static java.util.Objects.requireNonNull;


public class TestData {

    static Faker faker = new Faker();
    static final String dir = "src/test/resources/images";

    static Map<String, List<String>> stateCityMap = new HashMap<>() {{
        put("NCR", Arrays.asList("Delhi", "Gurgaon", "Noida"));
        put("Uttar Pradesh", Arrays.asList("Agra", "Lucknow", "Merrut"));
        put("Haryana", Arrays.asList("Karnal", "Panipat"));
        put("Rajasthan", Arrays.asList("Jaipur", "Jaiselmer"));
    }};

    static ArrayList<String>
            gender = new ArrayList<>(Arrays.asList("Male", "Female", "Other")),
            hobbies = new ArrayList<>(Arrays.asList("Sports", "Reading", "Music")),
            subjects = new ArrayList<>(Arrays.asList("Maths", "Arts", "History", "Biology", "Economics"));

    public static String getRandomState() {
        List<String> states = new ArrayList<>(stateCityMap.keySet());
        return states.get(faker.random().nextInt(states.size()));
    }

    public static String getRandomCity(String state) {
        List<String> cities = stateCityMap.get(state);
        return cities.get(faker.random().nextInt(cities.size()));
    }

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

    public static String getRandomMonth() {
        return faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
    }

    public static String getRandomDay() {
        return String.format("%03d", faker.number().numberBetween(1, 31));
    }

    public static String getRandomYear() {
        return String.valueOf(faker.number().numberBetween(1900, 2100));
    }

    public static String getRandomGender() {
        return gender.get(faker.random().nextInt(gender.size()));
    }

    public static String getRandomHobby() {
        return hobbies.get(faker.random().nextInt(hobbies.size()));
    }

    public static String getRandomSubject() {
        return subjects.get(faker.random().nextInt(subjects.size()));
    }

    public static String getRandomFile() {
        var fileNames = new File(dir).list();
        var index = faker.random().nextInt(requireNonNull(fileNames).length);
        return fileNames[index];
    }

    public static void main(String[] args) {
        System.out.println(getRandomFile());
    }
}
