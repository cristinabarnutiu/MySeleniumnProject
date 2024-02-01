package com.demoqa;

import jdk.jfr.Enabled;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.herokuapp.LoginTest.sleep;

public class TestboxTest {

    WebDriver driver;
    String url = "https://demoqa.com/text-box";
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
        }
        catch (Exception e){
            throw new RuntimeException("Nu am gasit elementul");
        }

    }
    @Parameters({"name"})
    @Test
    public void fillInForm(String nume) {

            WebElement fullName = driver.findElement(By.xpath("//input[@id=\"userName\"]"));
            fullName.sendKeys(nume);

            WebElement email = driver.findElement(By.xpath("//input[@id=\"userEmail\"]"));
            email.sendKeys("ion.popescu@email.com");

            WebElement currentAddress = driver.findElement(By.xpath("//textarea[@id=\"currentAddress\"]"));
            currentAddress.sendKeys("123 Street");

            WebElement permanentAddress = driver.findElement(By.xpath("//textarea[@id=\"permanentAddress\"]"));
            permanentAddress.sendKeys("123 Street");

            WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
            new Actions(driver)
                .scrollToElement(submitButton)
                .perform();
            submitButton.click();

            WebElement name = driver.findElement(By.xpath("//p[@id=\"name\"]"));
            String numeDat = nume;
            Assert.assertTrue(name.getText().contains(nume));

        }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
