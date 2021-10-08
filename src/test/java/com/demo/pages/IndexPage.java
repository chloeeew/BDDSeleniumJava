package com.demo.pages;

import com.demo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndexPage extends BasePage {
    By signInLocator = By.cssSelector("a.login");

    public IndexPage(WebDriver driver) {
        super(driver);
    }

    public void click_sign_in_button(){
        click_element(signInLocator);
    }
}
