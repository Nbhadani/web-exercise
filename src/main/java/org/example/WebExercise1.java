package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WebExercise1 {

    static protected WebDriver driver;

//    TO ADD @BEFORE METHOD FOR OPEN BROWSER
    @Before
    public void openBrowser(){

        System.setProperty("webdriver.chrome.driver", "src\\test\\java\\BrowserDrivers\\chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    //    TO ADD @AFTER METHOD FOR CLOSE BROWSER

    @After
            public void closeBrowser(){
        driver.quit();

    }
//   timestamp method for register email so we dnt have to keep changing the email in register page.

    public String timeStamp(){
        DateFormat dateFormat = new SimpleDateFormat( "ddmmyyhhmmss");
        Date date = new Date();
        return (dateFormat.format(date));
    }
    // reusable for click method

    public void clickOnElement(By by){ driver.findElement(by).click();
    }

    // wait for clickable method
    public void waitForClickable(By by,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    // wait for visible method
    public void waitForVisibility(By by,int time){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //  wait for element present method
        public void waitForElementsPresent(By by,int time){
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

//    REUSABLE METHOD FOR SEND KEYS.

    public void enterText(By by,String text){
        driver.findElement(by).sendKeys(text);
    }


    String expected = "My registration successfully";

    @Test
    public void userShouldAbleToRegisterSuccessfully()
    {

//        System.setProperty("webdriver.chrome.driver", "src\\test\\java\\BrowserDrivers\\chromedriver.exe");
//        driver= new ChromeDriver();
//        driver.manage().window().fullscreen();
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        clickOnElement(By.linkText("Register"));
        WebDriverWait wait = new WebDriverWait(driver, 60);
       waitForClickable(By.id("register-button"),10) ;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
// fill up the form by using locators

        enterText(By.xpath("//input[@id='FirstName']"),"John");
        enterText(By.id("LastName"),"Smith");
        enterText(By.name("Email"),"khushiindia25+"+timeStamp()+"@yahoo.com");
        enterText(By.name("Password"),"John125");
        enterText(By.name("ConfirmPassword"),"John125");
        clickOnElement(By.name("register-button"));

//   compare the expected vs actual result of test case by using assert.
        String expected = "My registration successfully";
        String Actual = driver.findElement(By.className("result")).getText();
        Assert.assertEquals("Test case Failed",expected, Actual);

    }
}
