package com.demoqa;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.herokuapp.LoginTest.sleep;

public class WaitTest {
    WebDriver driver;
    String url = "https://demoqa.com/alerts";
    @BeforeTest(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().fullscreen();
        sleep(5);
        try {
            WebElement consimtamant = driver.findElement(By.xpath("//*[@class=\"fc-button-label\"]"));
            if (consimtamant.isDisplayed()) {
                consimtamant.click();
            }
        } catch (Exception e) {
            throw new RuntimeException("Nu am gasit elementul");
        }
    }

        @Test
        public void waitTest(){
        WebElement clickeMe = driver.findElement(By.id("timerAlertButton"));
        clickeMe.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());


        String text = alert.getText();
        System.out.println((text));

        alert.accept();

        //TO DO Test pt butonul al doilea click me


        }
        @AfterTest(alwaysRun = true)
        public void tearDown(){
            driver.close();
        }
}
