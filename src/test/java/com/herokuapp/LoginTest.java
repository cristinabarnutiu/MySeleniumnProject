package com.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;
    String url = "https://the-internet.herokuapp.com/login";

    @Parameters({"browserP"})
    @BeforeTest
    public void setUp(String browser){
        switch (browser){
            case "chrome": driver = new ChromeDriver();break;
            case "edge": driver = new EdgeDriver();break;
            case "firefox": driver = new FirefoxDriver();break;
            default:driver = new ChromeDriver();
        }

        driver.get(url);
        driver.manage().window().maximize();
    }

    @Parameters({"usernameP","passwordP","successMessageP"})
    @Test(testName="Login test")
    public void login(String username, String password, String message){
        System.out.println("Asteapta 2 secunde");
        sleep(2000);

        //2. click username & enter user: tomsmith
        System.out.println("Introdu username");

        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys(username);
        sleep(2000);

        //3. click password & enter "SuperSecretPassword!"
        System.out.println("Introdu parola");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        sleep(2000);

        //4. click Login button
        System.out.println("Click login button");
        WebElement loginButton = driver.findElement(By.className("radius"));
        //WebElement loginButton = driver.findElement(By.name("button"));
        loginButton.click();

        System.out.println("Asteapta 2 secunde");
        sleep(2000);

        //Expected results: "Welcome to the Secure Area" is displayed
        System.out.println("Verificam continutul subheaderului");
        WebElement secureAreaSubheader = driver.findElement(By.className("subheader"));
        String subheaderContent = "Welcome to the Secure Area. When you are done click logout below.";
        Assert.assertTrue(secureAreaSubheader.isDisplayed());
        Assert.assertEquals(subheaderContent,secureAreaSubheader.getText());

        //Verifica linkul pe care suntem redirectionati
        System.out.println("Verifica linkul pe care suntem redirectionati");
        String secureUrl = "https://the-internet.herokuapp.com/secure";
        Assert.assertEquals(driver.getCurrentUrl(),secureUrl);

        //Verifica mesajul de succes
        System.out.println("Verifica mesajul de succes");
        WebElement successMessage = driver.findElement(By.id("flash"));
        //String successMessageContent = "You logged into a secure area!";
        Assert.assertTrue(successMessage.getText().contains(message));

    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
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
