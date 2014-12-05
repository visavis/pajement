package org.wissenteil.pajement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class WebObject {
	WebDriver driver = ObjectsTestDrive.driver;	
	String location;
	WebElement element;
	
	public WebObject(String...params){
		if (params.length == 1) {
			this.location = params[0];
		} else if (params.length == 2) {
			this.location = params[0] + "[text='" + params[1] + "']";
		}		
	}
	
	public WebObject withText(String text) {
		return new WebObject(this.location + "[text='" + text + "']");
	}
		
	public void printLocation() {
		System.out.println(location);
	}
	//sel methods
	public boolean isVisible() {
		return driver.findElement(By.xpath(this.location)).isDisplayed();
	}
	
	public void type(String text) {
		element = driver.findElement(By.xpath(this.location));
		element.clear();
		element.findElement(By.xpath(this.location)).sendKeys(text);
	}
	
	public void click(){
		driver.findElement(By.xpath(this.location)).click();
	}
	
	public int count(){		
		return driver.findElements(By.xpath(this.location)).size();
	}
	
	
	
	
	
}
