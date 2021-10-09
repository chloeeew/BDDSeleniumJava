package com.framework.pages;

import com.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    By infoAccountLocator = By.cssSelector(".info-account");
    By accountOwnerBtnLct = By.cssSelector(".account");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean check_account_is_welcomed(){
        logger.info("check account is welcomed");
        return is_element_text_contains_text(infoAccountLocator,"Welcome");
    }

    public boolean check_account_owner_by_string(String userName){
        String name = get_element_text(accountOwnerBtnLct);
        logger.info("check account owner: "+name+" expected name:"+userName);
        return name.equals(userName);
    }
}
