package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();  // Thread safe 

	@Override
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // thread-local provide unique thread id to each test in starting
	}

	@Override
	public void onTestSuccess(ITestResult result) {
//		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
//		test.log(Status.FAIL, "Test Failed");     --> to know test is failed but below line shows error message
		extentTest.get().fail(result.getThrowable());
		
		// Getting driver life using below code
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// taking screenshot and attach to report and sending driver as parameter 
       //		to getscreenshot method in basetest
		String filepath = null;
		try {
			filepath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Skipped");
	}


	@Override
	public void onFinish(ITestContext context) {
		extent.flush();	
		
	}

	
	
}
