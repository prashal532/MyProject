package com.BrowserAutomation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Assignment4_WorldClocks_ExcelFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "./DriverFiles/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.timeanddate.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Actions action=new Actions(driver);
		WebElement World_Clock=driver.findElement(By.xpath("//*[@id=\"navMenu\"]/li[3]/a[2]"));
		action.moveToElement(World_Clock).perform();
		WebElement Main_Clock=driver.findElement(By.linkText("Main World Clock"));
		Main_Clock.click();
		WebElement Complete_Table=driver.findElement(By.xpath("/html/body/div[1]/div[6]/section[1]/div"));
		List<WebElement> Table_Rows=Complete_Table.findElements(By.tagName("tr"));
		int Rows_Size=Table_Rows.size();
	//	System.out.println("No of Rows: "+Rows_Size);
		FileInputStream file=new FileInputStream("./src/com/ExcelFiles/Assignment3_File2.xlsx");
		XSSFWorkbook workBook=new XSSFWorkbook(file);
		XSSFSheet sheet=workBook.getSheet("World Clocks");
		
		for(int i=0;i<Rows_Size;i++)
		{
			Row Excel_Row=sheet.createRow(i);
			List<WebElement> Table_Data=Table_Rows.get(i).findElements(By.tagName("td"));
			int Table_Data_size=Table_Data.size();
			//System.out.println("No of Rows: "+Table_Data_size);
			for(int j=0;j<Table_Data_size;j++)
			{
				String Table_Data_Text=Table_Data.get(j).getText();
				Cell Row_Cell=Excel_Row.createCell(j);
				Row_Cell.setCellValue(Table_Data_Text);
				//System.out.print(Table_Data_Text+"  ");
			}
			//System.out.println();
		}
		
		FileOutputStream file1=new FileOutputStream("./src/com/ExcelFiles/Assignment3_File2.xlsx");
		workBook.write(file1);
		driver.close();
		
	}

}
