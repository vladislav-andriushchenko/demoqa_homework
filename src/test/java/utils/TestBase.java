package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;


public class TestBase {

    @BeforeAll
    public static void setup() {
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.pageLoadStrategy = "eager";
        boolean isRemote = Boolean.parseBoolean(System.getProperty("remote", "false"));

        if (isRemote) {
            Configuration.remote = System.getProperty("remoteUrl", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(
                    "--disable-dev-shm-usage",
                    "--no-sandbox",
                    "--remote-allow-origins=*",
                    "--disable-gpu",
                    "--window-size=1920,1080"
            );

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));

            String browserVersion = System.getProperty("browserVersion", "128.0");

            String browser = Configuration.browser;
            if (browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("firefox")) {
                capabilities.setCapability("version", browserVersion);
            } else {
                System.out.printf("Skipping version setting for unsupported browser: %s%n", browser);
            }

            Configuration.browserCapabilities = capabilities;
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
