package Extent.Extentreports;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//for demoper purpose am doing this for the n-g-rok is working or nor 
public class Listeners extends testclass implements ITestListener{
	ExtentTest test;
	 ExtentReports extent = Extentreport .configextentreports();
	 
	 ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//Thread safe
	@Override
	public void onTestStart(ITestResult result)
	{
		test= extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id(ErrorValidationTest)-->test
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
		
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		String filePath = null;
		try {
			filePath = takescreenshotfailure(result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}
	@Override
	public void onTestSkipped(ITestResult result)
	{
		extentTest.get().log(Status.SKIP, "Test Skiped at this level");
		
	}
	@Override
	public void onFinish(ITestContext context) {
	    extent.flush();
	}
	

}
