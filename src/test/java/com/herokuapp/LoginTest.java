package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void login(){
        //1. deschide pagina Form Authentication
        System.out.println("Deschide pagina Form Authentication");
        WebDriver driver = new ChromeDriver();
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);
        driver.manage().window().maximize();

        System.out.println("Asteapta 3 secunde");
        sleep(2000);


        //2. click username & enter user: tomsmith
        System.out.println("Username");

        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("tomsmith");
        sleep(2000);

        //3. click password & enter "SuperSecretPassword!"
        System.out.println("Parola");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("SuperSecretPassword!");
        sleep(2000);

        //4. click Login button
        System.out.println("Click login button");
        WebElement loginButton = driver.findElement(By.className("radius"));
        //WebElement loginButton = driver.findElement(By.name("button"));
        loginButton.click();
        sleep(2000);

        //Expected results: "Welcome to the Secure Area" is displayed
        System.out.println("Verificam continutul subheaderului");
        WebElement secureAreaSubheader = driver.findElement(By.className("subheader"));
        String subheaderContent = "Welcome to the Secure Area. When you are done click logout below.";
        Assert.assertTrue(secureAreaSubheader.isDisplayed());
        Assert.assertEquals(subheaderContent,secureAreaSubheader.getText());

        String secureUrl = "https://the-internet.herokuapp.com/secure";
        Assert.assertEquals(driver.getCurrentUrl(),secureUrl);

        WebElement successMessage = driver.findElement(By.id("flash"));
        String successMessageContent = "You logged into a secure area!";
        Assert.assertTrue(successMessage.getText().contains(successMessageContent));

        //Inchide pagina

        System.out.println("Inchide pagina");
        driver.close();
    }

    public static void sleep(int miliseconds){
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
