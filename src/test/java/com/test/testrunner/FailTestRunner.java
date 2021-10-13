package com.test.testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"@target/failedRerun.txt"},
        glue = "com.test.stepdefinition",
        plugin = "rerun:target/failedRerun.txt"
)

public class FailTestRunner extends AbstractTestNGCucumberTests {
}
