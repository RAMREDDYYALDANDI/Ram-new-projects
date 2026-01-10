package Extent.Extentreports;

import java.io.File;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extentreport {
	
	
	public static ExtentReports configextentreports()
	{
		//the Extent reports are two types 1. ExtentReports and 2. ExtentSparkReporter
		String path = System.getProperty("user.dir") + File.separator + "reports"+File.separator + "index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Extent Repors");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ram Reddy Yaldandi");
		return extent;
		
		
	}

}
