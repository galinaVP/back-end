
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "stepsDefinition",
        tags = "@All",
        plugin = {"io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm", "pretty", "json:target/cucumber-report/report.json"}
)

public class TestRunner {
}