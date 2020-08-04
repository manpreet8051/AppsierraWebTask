package com.goibibo.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.goibibo.utils.TestUtil;

public class BaseClass {
	public static WebDriver driver;
	public static Properties prop;
	
	public BaseClass() {
		String userDirectPath = System.getProperty("user.dir");
		System.out.println(userDirectPath);

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(userDirectPath+"\\src\\main\\java\\com\\goibibo\\config\\config.properties");
			System.out.println("Properties Loaded");
			prop.load(ip);
			System.out.println(prop.getProperty("browser"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initilization() {
		String userDirectPath = System.getProperty("user.dir");
		String browserName = prop.getProperty("browser");
		if(browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", userDirectPath+"\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
					}
		else if(browserName.equals("IE")) {
			System.setProperty("webdriver.chrome.driver", userDirectPath+"\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.page_load_timeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.implicit_load_timeOut, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}
}
