package Extent.Extentreports;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count =0;
	int maxTry =1;
	
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry)
		{
			count++;
			return true;
		}
		
		return false;
	}
	//am testing that n-g-rok is working fine or not and tell me the promising note to get validate and tell me it is working or not 

}
