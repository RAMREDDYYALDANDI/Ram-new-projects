package Extent.Extentreports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testclass {
	
	public WebDriver driver;
	public ExtentReports extent;
	public WebElement lastElement;
	
	
	public String takescreenshotfailure(String testName) throws IOException
	{
		TakesScreenshot ts =   (TakesScreenshot)driver;
		File source =  ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ File.separator+"screenshots"+File.separator+"failure.png");
		FileUtils.copyFile(source,file);
		return testName;
	}
	
	public void ElementScreenshhot(WebElement element) throws IOException
	{
		//screenshots folder path 
		File folder = new File(System.getProperty("user.dir")
				+File.separator+ "screenshots");
		//delete all existing png files
		File[] files = folder.listFiles((dir,name)->name.endsWith(".png"));
		/*if (files !=null)
		{
			for(File f:files) {
				f.delete();
			}
		}*/
		//creates new unique file name
		String fileName ="element_"+System.currentTimeMillis()+".png";
		File destFile = new File(folder+ File.separator+fileName);
		
		//take element Screenshot
		File source = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, destFile);
				
	}
	
	@Test
	public void Baseclass() throws IOException
	{
		extent = Extentreport.configextentreports();
		extent.createTest("Initial Demo");
		// ✅ ADD HERE (TOP of method)
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/Resources/global.properties");
		prop.load(fis);
		  // ✅ READ from properties
		//run from terminal = maven test -Dbrowser=chrome or edge 
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		// by using the above statement we can use the browser automatically by the terminal at the run time 
		//String browser = prop.getProperty("browser");
		
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		}
		 // ✅ URL from properties
		driver.get(prop.getProperty("url"));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println(driver.getTitle());
		
		WebElement searchBox = driver.findElement(By.cssSelector("#APjFqb"));
		searchBox.sendKeys("Flipkart.com");
		ElementScreenshhot(searchBox);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		searchBox.clear();
		ElementScreenshhot(searchBox);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		searchBox.sendKeys("Amazon.in");
		ElementScreenshhot(searchBox);
		
		System.out.println(" this is exam am testing for the project git seeing the error");
		System.out.println(" this is exam am testing for the project git seeing the error");
		System.out.println(" this is exam am testing for the project git seeing the error");
		
		
		System.out.println(" this is exam am testing for the project git seeing the error");
		System.out.println(" this is exam am testing for the project git seeing the error");



		
		
		
		
	}

	
	
	
	
	

}
