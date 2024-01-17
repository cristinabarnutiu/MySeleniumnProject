package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LogoutTest {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/login";

    @BeforeTest
    public void setUp(){
        //1. deschide pagina Form Authentication
        System.out.println("Deschide pagina Form Authentication");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }
    @Parameters({"usernameP","passwordP", "messageP"})
    @Test
    public void logout(String username, String password, String message){
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys(username);

        System.out.println("Introdu parola");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        System.out.println("Click login button");
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();

        WebElement logoutButton = driver.findElement(By.linkText("Logout"));
        logoutButton.click();

        WebElement successMessage = driver.findElement(By.id("flash"));
        //String successMessageContent = "You logged out of the secure area!";
        Assert.assertTrue(successMessage.getText().contains(message));

    }
    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

}
