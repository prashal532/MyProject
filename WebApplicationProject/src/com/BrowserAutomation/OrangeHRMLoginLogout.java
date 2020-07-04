package com.BrowserAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrangeHRMLoginLogout {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String expectedURL="http://127.0.0.1/orangehrm-4.2.0.1/symfony/web/index.php/auth/login";
		String expectedDashBoardURL="http://127.0.0.1/orangehrm-4.2.0.1/symfony/web/index.php/dashboard";
		
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver","./DriverFiles/chromedriver.exe");
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get(expectedURL);
		String actualURL=driver.getCurrentUrl();
		//System.out.println("Current URL is"+actualURL);
		if(actualURL.equals(expectedURL))
		{
			WebElement userName=driver.findElement(By.id("txtUsername"));
			userName.sendKeys("chennp01");
			WebElement password=driver.findElement(By.id("txtPassword"));
			password.sendKeys("04e91A0532@");
			WebElement loginButton=driver.findElement(By.id("btnLogin"));
			loginButton.click();
			String actualDashBoardURL= driver.getCurrentUrl();
			//System.out.println("Landed Dashboard URL is "+actualDashBoardURL);
			if (actualDashBoardURL.equals(expectedDashBoardURL))
			{
				Thread.sleep(5000);
				WebElement dropDown=driver.findElement(By.id("welcome"));
				dropDown.click();
				Thread.sleep(5000);
				WebElement logOut=driver.findElement(By.linkText("Logout"));
				logOut.click();
				System.out.println("Testcase successfully PASSED");
				driver.close();
			}
			else
			{
				System.out.println(" System did not navigate to the Correct DashBoard URL-FAIL");
				driver.close();
			}
		}
		else
		{
			System.out.println(" System did not navigate to the Correct URL-FAIL");
			driver.close();
		}
		
		//driver.close();

	}

}
