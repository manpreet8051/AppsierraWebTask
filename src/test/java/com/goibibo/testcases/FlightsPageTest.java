package com.goibibo.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.goibibo.base.BaseClass;
import com.goibibo.pages.FlightsPage;
import com.goibibo.pages.PaymentsPage;
import com.goibibo.pages.TicketDetailsPage;

public class FlightsPageTest extends BaseClass {
	FlightsPage flightPage;
	TicketDetailsPage ticketDetailsPage;
	PaymentsPage paymentsPage;
	
	public FlightsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initilization();
		flightPage = new FlightsPage();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void bookRoundTripFlightHigherPriceTest() {
		String actualLabel = flightPage.searchRoundTripFlight("Delhi", "Mumbai", "22", "30");
		Assert.assertEquals(actualLabel, "Fare Calendar");
		flightPage.sortByHigherToLower();
		ticketDetailsPage = flightPage.bookFirstSortedByPriceFlightForDesAndRet();
		paymentsPage = ticketDetailsPage.enterTravellerDetails("Mr","Manpreet","S","Singh","mp@gmail.com","7845478454");
		String actualDetails = paymentsPage.proceedToPayment();
		Assert.assertEquals(actualDetails, "Payment Details");
		paymentsPage.selectAmazonPayWallet();
	}


}
