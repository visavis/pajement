package org.pajement.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pajement.models.GooglePage;

public class TestRun {
	public static WebDriverWait wait;
	public static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		TestHelper.setDriver();
//		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	@Test
	public void testMethod() throws InterruptedException {		

		GooglePage google = new GooglePage("http://www.google.pl");
		
		google.printLocation();
		google.content.printLocation();
		google.content.header.printLocation();
		google.content.resultsList.result.printLocation();
		google.content.someMatch.printLocation();
		google.content.someMatch1.printLocation();
		google.content.someMatch2.printLocation();
		google.content.someMatch3.printLocation();
		google.content.someMatch4.printLocation();
		google.content.someMatch5.printLocation();
		
		
//		google.load();
//
//		assertTrue(google.content.isVisible());
//		google.content.searchInput.type("something to search");
//		google.containing("To search for something or someone - synonyms or related ...").printLocation();
//		google.content.containing("To search for something or someone - synonyms or related ...").printLocation();
//		Thread.sleep(1000);
//		
//		google.content.searchButton.click();
//		Thread.sleep(1000);
//		System.out.println(google.content.resultsList.result.count());
//		Thread.sleep(1000);
//		assertTrue(google.content.resultsList.result.isVisible());
//		System.out.println(google.containing("To search for something or someone - synonyms or related ...").text());
//		assertEquals(10, google.content.resultsList.result.count());
//		System.out.println(google.content.resultsList.result.first().text());
//		assertTrue(google.hasText("To search for something or someone - synonyms or related ..."));
//		assertTrue(google.content.resultsList.result.containing("To search for something or someone - synonyms or related ...").isVisible());
//		google.content.resultsList.result.containing("To search for something or someone - synonyms or related ...").click();
//		Thread.sleep(2000);
	}
}
