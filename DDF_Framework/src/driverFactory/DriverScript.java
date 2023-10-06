package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utiities.ExcelFileUtil;

public class DriverScript extends AppUtil{
	String inputpath ="./FileInput/LoginData.xlsx";
	String outputpath ="./FileOutput/DataDrivenResults.xlsx";
	boolean res=false;
	ExtentReports report;
	ExtentTest logger;
	@Test
	public void startTest() throws Throwable
	{
		//define path of html 
		report= new ExtentReports("./Reports/Login.html");
		//create object for Excelfileutil class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no o f rows in Login sheet
		int rc =xl.rowCount("Login");
		Reporter.log("No of rows are::"+rc,true);
		//iterate all rows in login sheet
		for(int i=1;i<=rc;i++)
		{
			//test case starts here
			logger = report.startTest("Validate Login");
			//read username and password cells
			String username =xl.getCellData("Login", i, 0);
			String password = xl.getCellData("Login", i, 1);
			//call login method from function library class
			res =FunctionLibrary.verify_Login(username, password);
			if(res)
			{
				//if res is true write as login success into results cell
				xl.setCellData("Login", i, 2, "Login Success", outputpath);
				//write as pass into status cell
				xl.setCellData("Login", i, 3, "Pass", outputpath);
				logger.log(LogStatus.PASS, "Valid username and password");
			}
			else
			{
				//take screen shot for fail steps
				File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./ScreenShot/Iterations/"+i+"Loginpage.png"));
				//capture error message 
				String Error_Message =driver.findElement(By.xpath(conpro.getProperty("ObjError"))).getText();
				xl.setCellData("Login", i, 2, Error_Message, outputpath);
				xl.setCellData("Login", i, 3, "Fail", outputpath);
				logger.log(LogStatus.FAIL, Error_Message);
			}
			report.endTest(logger);
			report.flush();

		}
	}
}













