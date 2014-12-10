package org.wissenteil.pajement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestRun {
	public static WebDriverWait wait;
	public static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	@Test
	public void testMethod() throws InterruptedException {

		GooglePage google = new GooglePage("http://www.google.pl");
		google.load();

		assertTrue(google.menu.isVisible());
		google.menu.searchInput.type("something to search");
		google.containing("To search for something or someone - synonyms or related ...").printLocation();
		google.menu.containing("To search for something or someone - synonyms or related ...").printLocation();
		Thread.sleep(1000);
//		assertTrue(google.containing("no text here jndjnafjndfjd").isVisible());
//		assertTrue(google.hasText("no text here jndjnafjndfjd"));
		google.menu.searchButton.click();
		Thread.sleep(1000);
		System.out.println(google.menu.resultsList.result.count());
		Thread.sleep(1000);
		assertTrue(google.menu.resultsList.result.isVisible());
		System.out.println(google.containing("To search for something or someone - synonyms or related ...").text());
		assertEquals(10, google.menu.resultsList.result.count());
		System.out.println(google.menu.resultsList.result.first().text());
		assertTrue(google.hasText("To search for something or someone - synonyms or related ..."));
		assertTrue(google.menu.resultsList.result.containing("To search for something or someone - synonyms or related ...").isVisible());
		google.menu.resultsList.result.containing("To search for something or someone - synonyms or related ...").click();
		Thread.sleep(2000);
	}
}
