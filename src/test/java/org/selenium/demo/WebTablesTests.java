package org.selenium.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.selenium.demo.page.WebTablesPage;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebTablesTests {
    WebDriver driver;
    WebTablesPage webTablesPage;

    @BeforeAll
    void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        webTablesPage = new WebTablesPage(driver);
        webTablesPage.open();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testReadRowCount() {
        int rowCount = webTablesPage.getRowCount();
        Assertions.assertTrue(rowCount > 0, "Table should have rows");
    }

    @Test
    void testSearchExistingEmail() {
        String email = "cierra@example.com";
        webTablesPage.search(email);
        Assertions.assertTrue(webTablesPage.isTextInTable(email), "Email should be present in table");
    }

    @Test
    void testSearchNonExistingEmail() {
        String email = "notfound@example.com";
        webTablesPage.search(email);
        Assertions.assertFalse(webTablesPage.isTextInTable(email), "Email should NOT be present in table");
    }

    @Test
    void testDeleteRow() {
        String email = "cierra@example.com";

        webTablesPage.search(email);

        webTablesPage.deleteRowByEmail(email);

        webTablesPage.search("");

        Assertions.assertFalse(webTablesPage.isTextInTable(email), "Deleted email should no longer be present");
    }
}
