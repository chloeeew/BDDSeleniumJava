package com.demo.pages;

import com.demo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IndexPage extends BasePage {
    By signInLocator = By.cssSelector("a.login");

    public IndexPage(WebDriver driver) {
        super(driver);
    }

    public void click_sign_in_button(){
        WebElement signInEle = driver.findElement(signInLocator);
        signInEle.click();
    }
}