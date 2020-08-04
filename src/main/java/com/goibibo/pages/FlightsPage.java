package com.goibibo.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.goibibo.base.BaseClass;

public class FlightsPage extends BaseClass {

	// Page Factory or Object Repository

	@FindBy(id = "roundTrip")
	WebElement roundTripButton;

	@FindBy(xpath = "//input[@id='start-date_srp']")
	WebElement departureInput;

	@FindBy(xpath = "//input[@id='end-date_srp']")
	WebElement returnInput;

	@FindBy(xpath = "//button[@id='gi_search_btn']")
	WebElement searchButton;
	
	@FindBy(xpath = "//div[@class='DayPicker-Body']//div//div[@class='DayPicker-Day']//div[@class='calDate' and text()]")
	List<WebElement> dates;

	@FindBy(xpath = "//div[text()='Fare Calendar']")
	WebElement fareCalenderLabel;

	@FindBy(xpath = "(//ul//li[@id='PRICE'])[1]")
	WebElement sortByPriceButtonFrom;

	@FindBy(xpath = "(//ul//li[@id='PRICE'])[2]")
	WebElement sortByPriceButtonReturn;

	@FindBy(xpath = "(//div[@class='fltHpyOnwrdWrp']//div//span//label)[1]")
	WebElement sortedFirstFlightDeparture;

	@FindBy(xpath = "(//div[@class='fltHpyRtrnWrp']//div//span//label)[1]")
	WebElement sortedFirstFlightReturn;

	@FindBy(xpath = "//input[@value='BOOK']")
	WebElement bookButton;

	String inputSourceXpath = "//input[@id='gosuggest_inputSrc']";
	String destinationXpath = "//input[@id='gosuggest_inputDest']";

	// Initiliazing page objects
	public FlightsPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions

	public String searchRoundTripFlight(String from, String des, String deartureDate, String returnDate) {
		clickOnWebElement(roundTripButton);
		enterDetailsInSourceDestination(inputSourceXpath, from);
		enterDetailsInSourceDestination(destinationXpath, des);
		selectDateFromCalender(dates, deartureDate);
		selectDateFromCalender(dates, returnDate);
		clickOnWebElement(searchButton);
		return fareCalenderLabel.getText();
	}

	public void clickOnWebElement(WebElement toClick) {
		toClick.click();
	}

	public void enterDetailsInSourceDestination(String xpath, String details) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).sendKeys(details);
		List<WebElement> myList = new WebDriverWait(driver, 20)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@role='listbox']//li")));
		for (int i = 0; i < myList.size(); i++) {
			System.out.println(myList.get(i).getText());

			if (myList.get(i).getText().contains(details)) {
				myList.get(i).click();
				break;
			}
		}

	}

	public void selectDateFromCalender(List<WebElement> allDates, String dateToSelect) {
		try {
			for (int date = 1; date < allDates.size(); date++) {
				if (allDates.get(date).getText().contains(dateToSelect)) {
					clickOnWebElement(allDates.get(date));
					break;
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void sortByHigherToLower() {
		clickOnWebElement(sortByPriceButtonFrom);
		clickOnWebElement(sortByPriceButtonReturn);
	}

	public TicketDetailsPage bookFirstSortedByPriceFlightForDesAndRet() {
		clickOnWebElement(sortedFirstFlightDeparture);
		clickOnWebElement(sortedFirstFlightReturn);
		clickOnWebElement(bookButton);
		return new TicketDetailsPage();
	}
}
