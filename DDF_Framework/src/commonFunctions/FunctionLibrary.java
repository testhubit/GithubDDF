package commonFunctions;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
	public static boolean verify_Login(String user,String pass)
	{
		driver.get(conpro.getProperty("Url"));
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(pass);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		String Expected ="dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			Reporter.log("Login success::"+Expected+"    "+Actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Failed::"+Expected+"   "+Actual,true); 
			return false;

		}
	}

}
