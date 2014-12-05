package test.java;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wissenteil.pajement.Menu;

public class ObjectsTestDrive {

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

		driver.get("http://www.google.pl");
		
		Menu menu = new Menu("//body");
		menu.printLocation();
		menu.searchInput.printLocation();
		menu.searchButton.printLocation();
		menu.resultsList.result.printLocation();
		assertTrue(menu.isVisible());
		menu.searchInput.type("something to search");
		menu.searchButton.click();
		Thread.sleep(1000);
		assertTrue(menu.resultsList.result.isVisible());
		assertEquals(8, menu.resultsList.result.count());
		Thread.sleep(11000);
	}
}