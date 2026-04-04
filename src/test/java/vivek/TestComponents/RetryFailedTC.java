package vivek.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTC implements IRetryAnalyzer{

	int count=0;
	int maxTry=2;
	
	public boolean retry(ITestResult result) {
		if(count<maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
