package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Trade",
        glue = "stepDefinitions/Trade",
        plugin = {
                "pretty",
                "html:target/cucumber-report/cucumberReport.html",
                "json:target/cucumber-json/cucumber.json"
        }
)
public class TestRunnerTrade {
}
