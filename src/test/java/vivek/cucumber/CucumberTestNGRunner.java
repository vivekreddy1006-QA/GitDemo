package vivek.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features="src/test/java/vivek/cucumber",
	    glue="vivek.CucumberStepDefinitions",
	    monochrome=true,
	    plugin={"pretty","html:target/cucumber.html","json:target/cucumber.json"}
	)
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests{
	
	

}
