package com.nordicnaturals.qa.tests;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.nordicnaturals.qa.base.BasePage;
import com.nordicnaturals.qa.pages.HomePage;

public class HomePageTest {
	BasePage basePage;
	HomePage homePage;
	Properties prop;
	WebDriver driver;
	@BeforeTest(groups = {"NonElement", "Element"})
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initializeProperties();
		driver = basePage.initializeDriver(prop);
		homePage = new HomePage(driver);
	}
	@Test(priority = 1, groups = {"NonElement"})
	public void verifyTitleTest() {
		String actualTitle = homePage.verifyTitle();
		String expectedTitle = "Nordic Naturals: Healthy Supplements For The Whole Family | Nordic Naturals";
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	@Test(priority = 2, groups = {"Element"})
	public void verifyCartItemsTest() {
		String actualText = homePage.verifyCart();
		String expectedText = "You have no items in your shopping cart.";
		Assert.assertEquals(actualText, expectedText);
	}
	@Test(priority = 3, groups = {"Element"})
	public void verifLinkNumberTest() {
		int actualLinkSize = homePage.verifyLinkNumber();
		int expectedLinkSize = 152;
		//boolean status = actualLinkSize < expectedLinkSize;
		Assert.assertEquals(actualLinkSize, expectedLinkSize);
	}
	
	@Test
	public void verifyChatBoxTest() {
		String actual = homePage.verifyInvalidChatMessage("test");
		String expected = "Please enter a valid email address. : Please enter a value.";
		Assert.assertEquals(actual, expected);
	}
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}


}
