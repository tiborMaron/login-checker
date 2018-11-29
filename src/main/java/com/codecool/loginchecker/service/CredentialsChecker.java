package com.codecool.loginchecker.service;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class CredentialsChecker {

    private static final String chromeDriverPath = "src/main/resources/chromedriver";

    private static final int timeOutInSeconds = 10;

    @PostConstruct
    private void init() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    public boolean google(String email, String password){
        ChromeDriver driver = new ChromeDriver();
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            driver.get("https://accounts.google.com/signin");

            WebElement identifierInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("identifier")));
            identifierInput.sendKeys(email);
            driver.findElementById("identifierNext").click();

            WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
            passwordInput.sendKeys(password);
            driver.findElementById("passwordNext").click();

            wait.until(ExpectedConditions.urlContains("myaccount"));
            if (driver.manage().getCookieNamed("APISID") != null)
                return true;

        } catch (Exception e) {
            return false;
        } finally {
            driver.close();
        }
        return false;
    }

    public boolean facebook(String email, String password){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            driver.get("https://facebook.com");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
            driver.findElementById("email").sendKeys(email);
            driver.findElementById("pass").sendKeys(password);
            driver.findElementById("loginbutton").click();
            wait.until(ExpectedConditions.not(ExpectedConditions.urlMatches("https://facebook.com/")));
            if (driver.manage().getCookieNamed("c_user") != null)
                return true;
        } finally {
            driver.close();
        }
        return false;
    }

    public boolean instagram(String email, String password){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            driver.get("https://www.instagram.com/accounts/login/");
            WebElement usernameInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
            usernameInput.sendKeys(email);
            driver.findElementByName("password").sendKeys(password);
            driver.findElementByCssSelector("button._0mzm-.sqdOP.L3NKy").click();
            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login")));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            driver.close();
        }
    }

    public boolean github(String email, String password){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            driver.get("https://github.com/login");
            WebElement loginInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("login")));
            loginInput.sendKeys(email);
            driver.findElementByName("password").sendKeys(password);
            driver.findElementByName("commit").click();
            wait.until(ExpectedConditions.urlMatches("https://github.com/"));
            if (driver.manage().getCookieNamed("logged_in").getValue().equals("yes"))
                return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            driver.close();
        }
        return false;
    }

    public boolean linkedin(String email, String password){
        return false;
    }
}
