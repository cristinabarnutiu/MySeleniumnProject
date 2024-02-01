package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UploadTest {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/upload";

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    public void testUpload() {
        WebElement fileUpload = driver.findElement(By.id("file-upload"));
        //update with your own file location!!!
        fileUpload.sendKeys("C:\\Users\\cristina\\IdeaProjects\\MySeleniumnProject\\src\\test\\resources\\test.txt");

        WebElement fileSubmit = driver.findElement(By.id("file-submit"));
        fileSubmit.click();

        WebElement messasge = driver.findElement(By.xpath("//h3"));
        String expectedMessage = "File Uploaded!";
        Assert.assertTrue(messasge.getText().contains(expectedMessage));

    }

}
