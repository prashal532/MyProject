package com.TestNGFrameWork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment4_NewToursLogin {
	WebDriver driver=null;
	String ApplicationURL="http://newtours.demoaut.com";

	@BeforeTest
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver","./DriverFiles/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(ApplicationURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test(priority=1)
	public void newToursLogin() throws IOException
	{
		String Actual_Title=driver.getTitle();
		if (Actual_Title.contains("Mercury Tours"))
		{
			FileInputStream file=new FileInputStream("./src/com/ExcelFiles/NewTours_LoginCredentials_Data.xlsx");
			XSSFWorkbook workBook=new XSSFWorkbook(file);
			XSSFSheet sheet=workBook.getSheet("LoginCredentials");
			int Sheet_Rows=sheet.getLastRowNum();
			
			for(int i=1;i<=Sheet_Rows;i++)
			{
				WebElement User_Name=driver.findElement(By.name("userName"));
				Row row=sheet.getRow(i);
				String userName=row.getCell(0).getStringCellValue();
				User_Name.sendKeys(userName);
				
				WebElement Password_Data=driver.findElement(By.name("password"));
				String Password=row.getCell(1).getStringCellValue();
				Password_Data.sendKeys(Password);
				
				WebElement Sign_In=driver.findElement(By.name("login"));
				Sign_In.click();
				
				String Actual_Landing_Title=driver.getTitle();
				String Expected_Landing_Title="Find";
				if(Actual_Landing_Title.contains(Expected_Landing_Title))
				{
					Cell cell=row.createCell(2);
					cell.setCellValue("Pass");
					WebElement Sign_Off=driver.findElement(By.linkText("SIGN-OFF"));
					Sign_Off.click();
				}
				else
				{
					Cell cell=row.createCell(2);
					cell.setCellValue("Fail");
				}
				
				
			}
			FileOutputStream file1=new FileOutputStream("./src/com/ExcelFiles/NewTours_LoginCredentials_Data.xlsx");
			workBook.write(file1);
			System.out.println("Login Page Validation PASS");
			driver.close();
		}
		else
		{
			System.out.println("Login Page Validation Fail");
			driver.close();
		}
		
	}
	
	@Test(priority=2)
	public void registration() throws IOException
	{
		String Actual_Title=driver.getTitle();
		if (Actual_Title.contains("Mercury Tours"))
		{
			FileInputStream file=new FileInputStream("./src/com/ExcelFiles/NewTours_LoginCredentials_Data.xlsx");
			XSSFWorkbook workBook=new XSSFWorkbook(file);
			XSSFSheet sheet=workBook.getSheet("Registration_Information");
			int Sheet_Rows=sheet.getLastRowNum();
			WebElement Register_Link=driver.findElement(By.linkText("REGISTER"));
			Register_Link.click();
			//WebElement Register_Page=driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[1]/td/font/font/b/font/font"));
			String Actual_Register_Title=driver.getTitle();
			if (Actual_Register_Title.contains("Register"))
					+ "
			
		}
		else// Landing Page
		{
			System.out.println(" Landing Page Validation FAIL");
			driver.close();
		}
	}
}
