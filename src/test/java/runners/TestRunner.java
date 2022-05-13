package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/*
    This is a test runner class that helps us start execution of the tests
    @CucumberOptions takes multiple options.
        plugin: helps in generating a html report that can be viewed after the test execution. Location api-test/target/cucumber-html-report/index.html
        features: path to feature files
        glue: path to step definitions
        tags: execute tests that belong to specific tag
 */
//@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = "src/test/resources/features",
        glue = {"api/tests"},
        monochrome = true,
        publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}