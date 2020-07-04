package com.BrowserAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.jna.platform.FileUtils;

public class Assignment3_DropDown {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "./DriverFiles/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://newtours.demoaut.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		File Screen_Shot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		org.apache.commons.io.FileUtils.copyFile(Screen_Shot, new File ("./ScreenShot/NewToursLandingPage.png"));
		WebElement Register_Link= driver.findElement(By.linkText("REGISTER"));
		Register_Link.click();
		WebElement Country_DropDown =driver.findElement(By.name("country"));
		List<WebElement> Data_Dropdown=Country_DropDown.findElements(By.tagName("option"));
		int Data_Size=Data_Dropdown.size();
		for(int i=0;i<Data_Size;i++)
		{
			String Data_Print=Data_Dropdown.get(i).getText();
			System.out.println(Data_Print);
		}
		FileInputStream file=new FileInputStream("./src/com/ExcelFiles/Assignment3_File1.xlsx");
		XSSFWorkbook workBook=new XSSFWorkbook(file);
		XSSFSheet sheet= workBook.getSheet("Countries");
		
		for(int i=0;i<Data_Size;i++)
		{
			Row row=sheet.createRow(i);
			String Excel_Write=Data_Dropdown.get(i).getText();
			Cell cell=row.createCell(0);
			cell.setCellValue(Excel_Write);
		}
		FileOutputStream file_Write=new FileOutputStream("./src/com/ExcelFiles/Assignment3_File1.xlsx");
		workBook.write(file_Write);
		driver.close();

	}
}
