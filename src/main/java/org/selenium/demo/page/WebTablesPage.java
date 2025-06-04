package org.selenium.demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class WebTablesPage {
    private WebDriver driver;
    private final String url = "https://demoqa.com/webtables";

    private final By tableRows = By.cssSelector(".rt-tr-group");
    private final By searchBox = By.id("searchBox");

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    public int getRowCount() {
        List<WebElement> rows = driver.findElements(tableRows);
        return rows.size();
    }

    public boolean isTextInTable(String text) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            if (row.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }

    public void search(String text) {
        WebElement searchInput = driver.findElement(searchBox);
        searchInput.clear();
        searchInput.sendKeys(text);
    }

    public void deleteRowByEmail(String email) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            if (row.getText().contains(email)) {
                row.findElement(By.cssSelector("[title='Delete']")).click();
                break;
            }
        }
    }
}
