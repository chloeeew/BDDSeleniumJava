package com.test.testrunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/com/test/features"},
        glue = "com.test.stepdefinition",
        plugin = "rerun:target/failedRerun.txt",
        tags = "@Trytry",
        monochrome = true
)
public class AuthorizeRunner extends AbstractTestNGCucumberTests {

}
