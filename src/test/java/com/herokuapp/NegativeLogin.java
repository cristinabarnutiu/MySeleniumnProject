package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeLogin {

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

    @Parameters({"usernameP","passwordP","errorP"})
    @Test(priority=1, groups = {"smoke","all"})
    public void loginWithInvalidUser(String username, String password, String error){
        WebElement usernameInput = driver.findElement(By.xpath("//input[@type='text']"));
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//button[@class='radius']"));
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.id("flash"));
        String invalidUserMessageContent = error;
        Assert.assertTrue(errorMessage.getText().contains(invalidUserMessageContent));


    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

//    @Test(priority = 2, groups = {"smoke"})
//    public void loginWithInvalidPassdword(){
//        WebDriver driver = new ChromeDriver();
//        String url = "https://the-internet.herokuapp.com/login";
//        driver.get(url);
//        driver.manage().window().maximize();
//
//        WebElement usernameInput = driver.findElement(By.xpath("//input[@type='text']"));
//        usernameInput.sendKeys("tomsmith");
//
//        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
//        passwordInput.sendKeys("wrongPassword");
//
//        WebElement loginButton = driver.findElement(By.xpath("//button[@class='radius']"));
//        loginButton.click();
//
//        WebElement errorMessage = driver.findElement(By.id("flash"));
//        String invalidPasswordMessageContent = "Your password is invalid!";
//        Assert.assertTrue(errorMessage.getText().contains(invalidPasswordMessageContent));

//        driver.close();

//    }
}
