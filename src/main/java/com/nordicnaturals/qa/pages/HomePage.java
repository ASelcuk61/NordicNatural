package com.nordicnaturals.qa.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.nordicnaturals.qa.util.ElementUtil;
public class HomePage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	// Constructor
	public HomePage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver = driver;
	}
	// locators
	private By cartBtn = By.xpath("//a[contains(@href, 'cart')]");
	private By cartItems = By.xpath("//*[contains(text(), 'no items')]");
	private By homePageDomainLinks = By.xpath("//a[contains(@href, 'https://www.nordicnaturals.com/consumers/')]");
	private By chatBtn = By.xpath("//*[@id='Embed']//button//span[2]");
	private By emailBox = By.xpath("(//input[@class='sc-EHOje bNRdNb'])[2]");
	private By emailMessage = By.xpath("(//div[@data-garden-id='forms.input_message'])[1]");
	private By invalidMessage = By.xpath("(//div[@data-garden-id='forms.input_message'])[2]");
	private By closeBtn = By.xpath("//div[@id='onetrust-close-btn-container']//button");
	private By sendBtn = By.xpath("//button[@data-garden-id='buttons.button']");
	
	// page actions
	public String verifyTitle() {
		return elementUtil.doGetPageTitle();
	}
	public String verifyCart() {
		elementUtil.doClick(cartBtn);
		elementUtil.waitForElementVisible(cartItems);
		return elementUtil.doGetText(cartItems);
	}
	public int verifyLinkNumber() {
		elementUtil.waitForElementPresent(homePageDomainLinks);
		return elementUtil.getElements(homePageDomainLinks).size();
	}
	private void clickOnChatBox() {
		elementUtil.doClick(closeBtn);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("launcher"));
		// driver.switchTo().frame("launcher");
		elementUtil.waitForElementVisible(chatBtn);
		elementUtil.doClick(chatBtn);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("webWidget");
	}
	
	public String verifyInvalidChatMessage(String value) {
		clickOnChatBox();
		elementUtil.waitForElementVisible(emailBox);
		elementUtil.doSendKeys(emailBox, value);
		elementUtil.doClick(sendBtn);
		elementUtil.waitForElementVisible(emailMessage);
		return elementUtil.doGetText(emailMessage) + " : "  + elementUtil.doGetText(invalidMessage);
	}
	
}
