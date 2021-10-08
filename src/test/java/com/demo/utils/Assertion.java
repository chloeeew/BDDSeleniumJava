package com.demo.utils;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class Assertion {
    static Logger logger = Logger.getLogger(Assertion.class);
    public static void assertEqualString(String expected,String actual){
        try {
            Assert.assertEquals(expected, actual);
        }catch (AssertionError ae){
            logger.error(ae);
            logger.error("Actual result "+ actual +" is not equivalent to expected result "+ expected);
            throw ae;
        }
        logger.info("Actual result "+ actual +" is equivalent to expected result "+ expected);
    }

    public static void assertTrue(boolean condition){
        try {
            Assert.assertTrue(condition);
        }catch (AssertionError ae){
            logger.error(ae);
            logger.error("condition does not meet the expectation, please check");
            throw ae;
        }
        logger.info("condition meet the expectation");
    }
}
