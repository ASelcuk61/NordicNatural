package com.nordicnaturals.qa.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	Properties properties;

	/**
	 * This method is to initialize the driver
	 * 
	 * @param prop
	 * @return
	 */
	public WebDriver initializeDriver(Properties prop) {
		String browser = prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		}
		else {
			System.out.println(browser + " name is not found!");
		}
		
		
		driver.get(prop.getProperty("url"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * This method is used to initialize properties
	 * 
	 * @return
	 */
	public Properties initializeProperties() {
		properties = new Properties();
		String configPath = "./src/main/java/com/nordicnaturals/qa/configuration/config.properties";
		try {
			FileInputStream file = new FileInputStream(configPath);
			properties.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
