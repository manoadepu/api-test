package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/*
    This is a test runner class that helps us start execution of the tests
    @CucumberOptions takes multiple options.
        plugin: helps in generating a html report that can be viewed after the test execution. Location api-test/target/cucumber-html-report/index.html
        features: path to feature files
        glue: path to step definitions
        tags: execute tests that belong to specific tag
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-html-report"},
        features = "src/test/resources/features",
        glue = {"api/tests"},
        tags = {"@Regression"}
)
public class TestRunner {
}