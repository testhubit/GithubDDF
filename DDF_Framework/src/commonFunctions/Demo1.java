package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Demo1 {

	public static void main(String[] args) throws Throwable{

      //Create instance object for different browser
		WebDriver dr =new ChromeDriver();
		dr.get("http://facebook.com");
		dr.manage().window().maximize();
		//delete all cookies in browsers
		dr.manage().deleteAllCookies();
		
		//suspend tool from execution for 5 second
		Thread.sleep(5000);
		dr.close();




		

	}

}