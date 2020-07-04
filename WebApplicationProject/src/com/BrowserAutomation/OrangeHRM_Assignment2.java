package com.BrowserAutomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class OrangeHRM_Assignment2 {

	private static final Object EmployeeID = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "./DriverFiles/Chromedriver.exe");
		driver=new ChromeDriver();
		String ExpectedURL="http://127.0.0.1/orangehrm-4.2.0.1/symfony/web/index.php/auth/login";
		driver.get(ExpectedURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		String ActualURL=driver.getCurrentUrl();
		if (ActualURL.equals(ExpectedURL)) //Landing Page 
		{
			System.out.println("Landing Page Verified-PASS");
			WebElement UserName=driver.findElement(By.id("txtUsername"));
			UserName.sendKeys("chennp01");
			Actions action=new Actions(driver);
			action.sendKeys(Keys.TAB).perform();
			action.sendKeys("04e91A0532@");
			action.sendKeys(Keys.ENTER).perform();
			WebElement HomePageValidation=driver.findElement(By.id("welcome"));
			String HomePageValidationText=HomePageValidation.getText();
			System.out.println("Home Page Validation Text is "+HomePageValidationText);
			if (HomePageValidationText.contains("Admin"))//HomePage Landing validation
			{
				System.out.println("Home Page Validations PASS");
				WebElement PimMenu=driver.findElement(By.id("menu_pim_viewPimModule"));
				action.moveToElement(PimMenu).perform();
				WebElement AddEmployee=driver.findElement(By.linkText("Add Employee"));
				AddEmployee.click();
				String FirstName="Pradeep";
				String LastName="Chennu";
				String Expected_AddEmployee_Page_Validation="Add Employee";
				WebElement Add_Employee_Validation=driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/h1"));
				String Actual_Add_Employee_Validation=Add_Employee_Validation.getText();
				System.out.println("Add Employee Page validation String is :"+Actual_Add_Employee_Validation);
				if(Actual_Add_Employee_Validation.equals(Expected_AddEmployee_Page_Validation))// Add Employee Page Validation
				{
					WebElement First_Name=driver.findElement(By.id("firstName"));
					First_Name.sendKeys(FirstName);
					//String Actual_FirstName=First_Name.getText();
					//System.out.println("Retrieved Firstname is "+Actual_FirstName);
					
					WebElement Last_Name=driver.findElement(By.id("lastName"));
					Last_Name.sendKeys(LastName);
					//String Actual_LastName=Last_Name.getText();
					//System.out.println("Retrieved Firstname is "+Actual_LastName);
					
					String Actual_FullName=FirstName+LastName;
					System.out.println("Actual Full Name of the Employee is "+Actual_FullName);
					
					WebElement Employee_ID=driver.findElement(By.id("employeeId"));
					String EmployeeID=Employee_ID.getAttribute("value");
					System.out.println("Employee ID during Creastion: "+EmployeeID);
					
					WebElement Save_Btn= driver.findElement(By.id("btnSave"));
					Save_Btn.click();
					//String Expected_Personal_Details_Page_Validation="Personal Details";
					
					
					WebElement Personal_Details_Page= driver.findElement(By.xpath("//*[@id=\"profile-pic\"]"));
					String Full_Name=Personal_Details_Page.getText();
					System.out.println("The string is "+Full_Name);
					if(Full_Name.equals(Actual_FullName)); //Employee name validatrion
					{
						System.out.println("Fulname Validation on Emplyee List-PASS");
						
					}}
					else//employee name validation
					{
						System.out.println("FullName validation on Employee List-FAIL");
					}
				
					
					WebElement Actual_Employee_ID=driver.findElement(By.id("personal_txtEmployeeId"));
					String Actual_EmployeeID=Actual_Employee_ID.getAttribute("value");
					System.out.println("Actual Employee ID on the Personal Details Page is "+Actual_EmployeeID);
					if (Actual_EmployeeID.equals(EmployeeID))//employee id validation
					{
						System.out.println("Employee ID Matched -PASS");
						WebElement Logout_DropDown=driver.findElement(By.linkText("Welcome Admin"));
						Logout_DropDown.click();
						
						WebElement Logout_Btn=driver.findElement(By.linkText("Logout"));
						Logout_Btn.click();
						driver.close();
						
					}
					else//emploayee ID validation
					{
						System.out.println("Employee ID match FAIL");
					}
				}
				else//Add employee page validation
				{
					System.out.println("Add Employee Page Validation-FAIL");
					driver.close();
				}
				
			}
			else// Home Page Landing Validation
			{
				System.out.println("Home Page Validation FAIL");
			}
		}
		/*else//Landing Page
		{
			System.out.println("Landing Page FAIL");
		}*/


}