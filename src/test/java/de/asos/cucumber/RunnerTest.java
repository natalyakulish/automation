package de.asos.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features",
        glue = "de.asos.cucumber",
        tags = "@all",
        format = "html:reports"
)
public class RunnerTest {
}