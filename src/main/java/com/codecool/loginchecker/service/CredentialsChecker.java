package com.codecool.loginchecker.service;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CredentialsChecker {

    private static final String chromeDriverPath = "src/main/resources/chromedriver";

    @PostConstruct
    private void init() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    public boolean gmail(String email, String password){
        return false;
    }

    public boolean facebook(String email, String password){
        ChromeDriver driver = new ChromeDriver();
        try {
            driver.get("https://facebook.com");
            driver.findElementById("email").sendKeys(email);
            driver.findElementById("pass").sendKeys(password);
            driver.findElementById("loginbutton").click();
            if (driver.manage().getCookieNamed("c_user") != null)
                return true;
        } finally {
            driver.close();
        }
        return false;
    }

    public boolean instagram(String email, String password){
        return false;
    }

    public boolean github(String email, String password){
        return false;
    }

    public boolean linkedin(String email, String password){
        return false;
    }
}
