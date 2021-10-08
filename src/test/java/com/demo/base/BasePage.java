package com.demo.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class BasePage {
    protected WebDriver driver;
    protected Logger logger = Logger.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void input_text(WebDriver driver, By locator, String text,String desc){
        try {
            WebElement webElement = driver.findElement(locator);
            webElement.sendKeys(text);
        }catch (NoSuchElementException exceptionN){
            logger.error( desc +" please check your locator"+locator);
            logger.error(exceptionN);
            throw exceptionN;
        }
        logger.info("send key successfully with text "+text +" description: "+desc);

    }

    public void input_text(WebDriver driver, By locator, String text){
        try {
            WebElement webElement = driver.findElement(locator);
            webElement.sendKeys(text);
        }catch (NoSuchElementException exceptionN){
            logger.error(" please check your locator"+locator);
            logger.error(exceptionN );
            throw exceptionN;
        }
        logger.info("send key successfully with text "+text );

    }



    public void click_element(WebDriver driver,By locator){
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver,5);
            WebElement webElement = driver.findElement(locator);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement)).click();

        }catch (Exception exceptionN){
            logger.error(" please check your locator"+locator);
            logger.error(exceptionN);
            throw exceptionN;
        }
        logger.info("click element successfully "+locator);
    }

    public void select_from_radio_drop_down(WebDriver driver,By locator,String value){
        if(contain_value_in_list(driver,locator,value)){
            logger.info("drop down selection "+locator + " contains "+value);
            try {
                WebElement webElement = driver.findElement(locator);
                Select select = new Select(webElement);
                select.selectByVisibleText(value);
            }catch (Exception nse){
                logger.error("Cannot locate the element :" +locator);
                logger.error(nse);
                throw nse;
            }
            logger.info("choose value "+ value);
        }else {
            logger.error("drop down selection "+locator +"does not contain "+value+" please check");
        }

    }

    public List<String> get_values_from_radio_drop_down(WebDriver driver, By locator){
        List<String> values = new ArrayList<>();
        try {
            WebElement webElement = driver.findElement(locator);
            Select select = new Select(webElement);
            List<WebElement> selectElements = select.getOptions();
            for(WebElement selectElement:selectElements){
                values.add(selectElement.getText());
            }
        }catch (NoSuchElementException exceptionN){
            logger.error(exceptionN);
            logger.error("Cannot locate this element "+locator);
            throw exceptionN;
        }


        return values;
    }

    public boolean contain_value_in_list( WebDriver driver,By locator,String target){
        List<String> values = get_values_from_radio_drop_down(driver,locator);
        return values.contains(target);
    }

    public void scroll_to_top(WebDriver driver,By locator){
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()",element);
    }

}
