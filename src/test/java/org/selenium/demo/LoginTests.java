package org.selenium.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.selenium.demo.page.LoginPage;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTests {
    WebDriver driver;
    LoginPage textBoxPage;

    @BeforeAll
    void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        textBoxPage = new LoginPage(driver);
        textBoxPage.open();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testValidLogin() {
        textBoxPage.fillForm("tomsmith", "SuperSecretPassword!");
        textBoxPage.submit();
        Assertions.assertTrue(textBoxPage.isFlashMessageDisplayed());
        Assertions.assertTrue(textBoxPage.getFlashMessage().contains("You logged into a secure area!"));
    }

    @Test
    void testInvalidLogin() {
        textBoxPage.fillForm("invalidUser", "invalidPass");
        textBoxPage.submit();
        Assertions.assertTrue(textBoxPage.isFlashMessageDisplayed());
        Assertions.assertTrue(textBoxPage.getFlashMessage().contains("Your username is invalid!"));
    }

    @Test
    void testEmptyFormSubmission() {
        textBoxPage.fillForm("", "");
        textBoxPage.submit();
        Assertions.assertTrue(textBoxPage.isFlashMessageDisplayed());
        Assertions.assertTrue(textBoxPage.getFlashMessage().contains("Your username is invalid!"));
    }
}

