package com.demo.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class BasePage {
    protected WebDriver driver;
    protected Logger logger = Logger.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement locate_element(By locator){
        WebElement webElement;
        try{
            webElement = driver.findElement(locator);
        }catch (NoSuchElementException exceptionN){
            logger.error("Cannot locate the element with "+locator);
            logger.error(exceptionN);
            throw exceptionN;
        }
        return webElement;
    }

    public void input_text(By locator, String text,String desc){
        locate_element(locator).sendKeys(text);
        logger.info("send key successfully with text "+text +" description: "+desc);

    }

    public void input_text(By locator, String text){
        locate_element(locator).sendKeys(text);
        logger.info("send key successfully with text "+text );

    }

    public void clean(By locator){
        WebElement webElement = locate_element(locator);
        try{
            if(webElement.isEnabled()){
                webElement.clear();
                logger.info("cleaning "+locator);
            }
        }catch (Exception e){
            logger.error("fail to clean, please check "+ locator);
            logger.error(e);
            throw e;
        }
    }


    public void verify_element_is_present(By locator){
        try {
            if(locate_element(locator).isDisplayed()){
                logger.info("element "+ locator + " is displayed");
            }
        }catch (Exception e){
            logger.error("this element "+locator+" is undisplayed, please check");
            logger.error(e);
            throw e;
        }
    }

    public void click_element(By locator){
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver,5);
            WebElement webElement = locate_element(locator);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement)).click();

        }catch (TimeoutException exceptionT){
            logger.error(" please check your locator"+locator);
            logger.error(exceptionT);
            throw exceptionT;
        }
        logger.info("click element successfully "+locator);
    }

    public void select_from_radio_drop_down(By locator,String visibleText){
        if(contain_value_in_list(locator,visibleText)){
            logger.info("drop down selection "+locator + " contains "+visibleText);
            WebElement webElement = locate_element(locator);
            try {
                Select select = new Select(webElement);
                select.selectByVisibleText(visibleText);
            }catch (Exception nse){
                logger.error("Selection cannot locate and select with this visible text :" +visibleText);
                logger.error(nse);
                throw nse;
            }
            logger.info("choose value "+ visibleText);
        }else {
            logger.error("drop down selection "+locator +"does not contain "+visibleText+" please check");
        }

    }

    public List<String> get_values_from_radio_drop_down( By locator){
        List<String> values = new ArrayList<>();
        WebElement webElement = locate_element(locator);
        Select select = new Select(webElement);
        List<WebElement> selectElements = select.getOptions();
        logger.info("get options from drop down selection by "+locator);
        for(WebElement selectElement:selectElements) {
            values.add(selectElement.getText());
        }
        return values;
    }

    public boolean contain_value_in_list(By locator,String target){
        List<String> values = get_values_from_radio_drop_down(locator);
        return values.contains(target);
    }

    public void scroll_to_top(By locator){
        WebElement element = locate_element(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()",element);
        logger.info("scroll to top by "+locator);
    }


    public void scroll_to_bottom(By locator){
        WebElement element = locate_element(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false)",element);
        logger.info("scroll to bottom by "+locator);
    }

    public void mouse_double_click(By locator){
        WebElement element = locate_element(locator);
        Actions actions = new Actions(driver);
        actions.doubleClick().perform();
        logger.info("double click "+locator);
    }

    public void mouse_drag_and_drop_element(By startLocator,By endLocator){
        WebElement startElement = locate_element(startLocator);
        WebElement endElement = locate_element(endLocator);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(startElement,endElement).perform();
        logger.info("drag "+startLocator+" to "+endLocator);
    }

    public void mouse_move_to_element(By locator){
        WebElement element = locate_element(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        logger.info("move to "+locator);
    }


    public String get_element_text(By locator){
        WebElement element = locate_element(locator);
        String result = element.getText();
        logger.info("get "+locator+" element text: "+result);
        return result;
    }

    public String get_element_attribute_value(By locator,String attribute){
        WebElement element = locate_element(locator);
        String result = element.getAttribute(attribute);
        logger.info("get "+locator+" element attribute "+attribute+" value:"+result);
        return result;
    }

    public boolean is_element_text_contains_text(By locator,String text){
        WebElement element = locate_element(locator);
        String textElement = element.getText();
        return textElement.contains(text);
    }

    public void switch_to_iframe(By locator){
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
            webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        }catch (TimeoutException timeoutException){
            logger.error("cannot switch to frame by locator:"+locator);
            logger.error(timeoutException);
            throw timeoutException;
        }
    }

    public void switch_to_parent_frame(By locator){
        try {
            driver.switchTo().parentFrame();
        }catch (Exception e){
            logger.error("fail to switch to parent frame, please check");
            logger.error(e);
            throw e;
        }
    }

    public void switch_to_window_by_string(String targetTitle){
        Set<String> allHandles = driver.getWindowHandles();
        try {
            for (String handle : allHandles) {
                if (driver.getTitle().contains(targetTitle)) {
                    break;
                } else {
                    driver.switchTo().window(handle);
                }
            }
        }catch (NoSuchWindowException noSuchWindowException){
            logger.error("fail to switch the window by title "+targetTitle);
            logger.error(noSuchWindowException);
            throw noSuchWindowException;
        }
    }

    public void switch_to_new_window(){
        String currentHandle = get_current_window_handle();
        Set<String> allHandles = driver.getWindowHandles();
        Iterator<String> it = allHandles.iterator();
        while (it.hasNext()){
            if(it.next().equalsIgnoreCase(currentHandle)){
                continue;
            }
            try{
                String newHandle = it.next();
                WebDriver newDriver = driver.switchTo().window(newHandle);
                logger.info("switch to new window: "+newDriver.getTitle());
                logger.info("current url : "+newDriver.getCurrentUrl());
            }catch (Exception e){
                logger.error("fail to switch new window");
                logger.error(e);
                throw e;
            }

        }
    }

    public String get_current_window_handle(){
        String current = driver.getWindowHandle();
        logger.info("get current handle :"+current);
        return current;
    }

    public String get_current_url(){
        String currentURL = driver.getCurrentUrl();
        logger.info("get current url:" +currentURL);
        return currentURL;
    }



}
