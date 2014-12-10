package org.wissenteil.pajement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestHelper {
	public static WebDriverWait wait;
	public static WebDriver driver;
	
	public static void setDriver() {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
	}
}
