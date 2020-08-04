package com.goibibo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.goibibo.base.BaseClass;

public class PaymentsPage extends BaseClass{
	
	// Page Factory or Object Repository
	
	@FindBy(xpath = "//button//span[text()='Proceed To Payment']")
	WebElement proocedToPayment;
	
	@FindBy(xpath = "//div//span[text()='Wallets']")
	WebElement wallets;
	
	@FindBy(xpath = "//div[@class='walletWrap']//label//input[@id='amazonpay']")
	WebElement amazonPay;
	
	@FindBy(xpath = "//div//span[text()='Payment Details']")
	WebElement paymentDetailsLabel;
	
	
	
	// Initiliazing page objects
	public PaymentsPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public String proceedToPayment() {
		clickOnWebElement(proocedToPayment);
		return paymentDetailsLabel.getText();
	}
	
	public void selectAmazonPayWallet() {
		WebDriverWait wait = new WebDriverWait(driver, 300);
		System.out.println("REACHED HERE");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//span[text()='Wallets']")));
		clickOnWebElement(wallets);
		clickOnWebElement(amazonPay);
		System.out.println("REACHED DONE");
	}
	
	public void clickOnWebElement(WebElement toClick) {
		toClick.click();
	}

}
