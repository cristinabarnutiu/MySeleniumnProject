package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLogin {

    @Test
    public void loginWithInvalidUser(){
        WebDriver driver = new ChromeDriver();
        //WebDriver driver = new FirefoxDriver();
        //WebDriver driver = new EdgeDriver();
        //WebDriver driver = new SafariDriver();
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        driver.manage().window().maximize();

        WebElement usernameInput = driver.findElement(By.xpath("//input[@type='text']"));
        usernameInput.sendKeys("wronguser");

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.sendKeys("SuperSecretPassword!");

        WebElement loginButton = driver.findElement(By.xpath("//button[@class='radius']"));
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.id("flash"));
        String invalidUserMessageContent = "Your username is invalid!";
        Assert.assertTrue(errorMessage.getText().contains(invalidUserMessageContent));

        driver.close();

    }

    @Test
    public void loginWithInvalidPassdword(){
        WebDriver driver = new ChromeDriver();
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        driver.manage().window().maximize();

        WebElement usernameInput = driver.findElement(By.xpath("//input[@type='text']"));
        usernameInput.sendKeys("tomsmith");

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.sendKeys("wrongPassword");

        WebElement loginButton = driver.findElement(By.xpath("//button[@class='radius']"));
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.id("flash"));
        String invalidPasswordMessageContent = "Your password is invalid!";
        Assert.assertTrue(errorMessage.getText().contains(invalidPasswordMessageContent));

        driver.close();
    }
}
