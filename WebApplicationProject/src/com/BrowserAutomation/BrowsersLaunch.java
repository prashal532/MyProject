package com.BrowserAutomation;

import org.openqa.selenium.chrome.ChromeDriver;

public class BrowsersLaunch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String expectedURL="https://www.facebook.com/";
		System.setProperty("webdriver.chrome.driver",".\\DriverFiles\\chromedriver.exe");
		ChromeDriver cd=new ChromeDriver();
		cd.manage().window().maximize();
		cd.get(expectedURL);
		String currentURL=cd.getCurrentUrl();
		if(currentURL.equals(expectedURL))
			System.out.println("url's matched");
		else
		{
			System.out.println("url's not matched");
			System.out.println("Current URL retrieved is "+currentURL);
		}
		//System.out.println("The url of the current page is "+currentURL);
		//System.out.println("The Current title of the page is "+cd.getTitle());
		cd.close();
	}

}
