package com.demo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
    }

    public void input_text(WebDriver driver, By locator, String text){
        //todo: try catch the NoSuchElementException
        WebElement webElement = driver.findElement(locator);
        webElement.sendKeys(text);
    }

    public void click_element(WebDriver driver,By locator){
        //todo: consider the element is not clickable
        WebElement webElement = driver.findElement(locator);
        webElement.click();
    }

    public void select_from_radio_drop_down(WebDriver driver,By locator,String value){
        //todo: check whether the selection contains this value
        WebElement webElement = driver.findElement(locator);
        Select select = new Select(webElement);
        select.selectByValue(value);
    }

    public List<String> get_values_from_radio_drop_down(WebDriver driver, By locator){
        List<String> values = new ArrayList<>();
        WebElement webElement = driver.findElement(locator);
        Select select = new Select(webElement);
        List<WebElement> selectElements = select.getOptions();
        for(WebElement selectElement:selectElements){
            values.add(selectElement.getText());
        }
        return values;
    }
}
