package task.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(value = Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"task.stepdef"},
        tags = {"@suite"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"})
public class RunSuite {

}
