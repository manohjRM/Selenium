package cashWrapMain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import dataProvider.DataProviderCashWrap;


public class CashWrapMainClass {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	public static Properties ConfigProperties() throws IOException {
		
		FileInputStream inputFile = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/"+"config.properties");
		Properties configFile = new Properties();
		configFile.load(inputFile);
		return configFile;
	}
	
	@BeforeTest
	public void LaunchBrowser() throws IOException {
		System.setProperty("webdriver.chrome.driver","H:\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to(ConfigProperties().getProperty("url"));
		driver.manage().window().maximize();
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
	}
	@AfterTest
	public void CloseBrowser() {
	//	driver.quit();
		extent.flush();
		extent.close();
	}
	
	public Object[][] getExcelData(String excelName, String sheetName){
		String excelLocation = System.getProperty("user.dir")+"/src/test/resources/"+excelName;
		//log.info("excel location "+excelLocation);
		DataProviderCashWrap excelHelper = new DataProviderCashWrap();
		Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
		return data;
		}
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{

		if(result.getStatus() == ITestResult.FAILURE){
	
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			String screenshotPath = CashWrapMainClass.getScreenshot(driver, result.getName());
			//To add it in the extent report
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}else if(result.getStatus() == ITestResult.SUCCESS){
			logger.log(LogStatus.PASS, "Test Case Passed is "+result.getName());
			//logger.log(LogStatus.PASS, "Test Case Passed is "+result.getThrowable());
			String screenshotPath = CashWrapMainClass.getScreenshot(driver, result.getName());
			//To add it in the extent report
			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
		}
		extent.endTest(logger);
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
