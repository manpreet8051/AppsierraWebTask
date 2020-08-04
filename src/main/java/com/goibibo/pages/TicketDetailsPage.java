package com.goibibo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.goibibo.base.BaseClass;

public class TicketDetailsPage extends BaseClass{
	
	// Page Factory or Object Repository
	
	@FindBy(id = "AdultfirstName1")
	WebElement firstName;
	
	@FindBy(id = "AdultmiddleName1")
	WebElement middleName;
	
	@FindBy(id = "AdultlastName1")
	WebElement lastName;
	
	@FindBy(xpath = "//input[@name='email']")
	WebElement emailId;
	
	@FindBy(xpath = "//input[@name='mobile']")
	WebElement mobileNumber;
	
	@FindBy(xpath = "//button//div[text()='Proceed ']")
	WebElement proceedButton;
	
	@FindBy(xpath = "//input[@id='secure-trip']")
	WebElement secureTrip;
	
	@FindBy(xpath = "//button[text()='OK']")
	WebElement okButtonOnAlert;
	
	public String titleXpath =  "//select[@id='Adulttitle1']";
	
	// Initiliazing page objects
	public TicketDetailsPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public PaymentsPage enterTravellerDetails(String title,String fName,String mName,String lName,String eMail,String phone) {
		clickOnWebElement(secureTrip);;
		selectValueFromDropDown(titleXpath,title);
		enterDetailsInWebElement(firstName,fName);
		enterDetailsInWebElement(middleName,mName);
		enterDetailsInWebElement(lastName,lName);
		enterDetailsInWebElement(emailId,eMail);
		enterDetailsInWebElement(mobileNumber,phone);
		clickOnWebElement(proceedButton);
		clickOnWebElement(okButtonOnAlert);
		return new PaymentsPage();	
	}
	
	public void selectValueFromDropDown(String dropdown,String value) {
		Select allValues = new Select(driver.findElement(By.xpath(dropdown)));
		allValues.selectByVisibleText(value);
	}
	
	public void clickOnWebElement(WebElement toClick) {
		toClick.click();
	}

	public void enterDetailsInWebElement(WebElement In, String details) {
		In.clear();
		In.sendKeys(details);
		;
	}
}
