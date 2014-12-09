package org.wissenteil.pajement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class WebObject {
	WebDriver driver = TestRun.driver;
	String location = "";
	String url;
	int indexPosition;

	public WebObject(String... params) {
		if (params.length == 1) {
			this.location = params[0];
		} else if (params.length == 2) {
			this.location = params[0] + "[text='" + params[1] + "']";
		}
	}
	
	public void setIndex(int index){
		this.indexPosition = index;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setUrl(String newUrl) {
		this.url = newUrl;
	}
	
	public void load() {
		driver.get(url);
	}

	/*
	 * ******************* Methods returning WebElement **********************
	 * ** Any Selenium method defined for WebElements can be called on those**
	 * ** those behave like Selenium, if more than one element is found it ***
	 * ** will grab first one ************************************************
	 */	
	
	public WebElement element() {
			return driver.findElement(By.xpath(location));
	}
//	returns nth of found elements
	//for readability indexing will start from 1 here
	public WebElement element(int position) {	
		return driver.findElements(By.xpath(this.location)).get(position);		
	}
	
//	returns array of elements - useful when operations such as count need to be performed
	public WebElement[] elements() {
		int size = driver.findElements(By.xpath(this.location)).size();
		WebElement[] results = new WebElement[size];
		for (int i = 0; i < size; i++){
			results[i] = driver.findElements(By.xpath(this.location)).get(i);
		}
		return results;
	}
	
	/*
	 * ******************* Methods returning WebObjects **********************
	 */
	
	public WebObject[] list() {
		int size = driver.findElements(By.xpath(this.location)).size();
		WebObject[] results = new WebObject[size];
		for (int i = 0; i < size; i++){
			WebObject tempObject = new WebObject(this.location);
			tempObject.setIndex(i);
			results[i] = tempObject;
		}
		return results;
	}
	
	public WebObject list(int index) {
		return list()[index];
	}
	
	public WebObject first() {
		return list(0);
	}
	
	public WebObject last() {
		int last = driver.findElements(By.xpath(this.location)).size() - 1;
		if (last > 0) {
			return list(last);
		} else {
			return list(0);
		}
	}
	
	public WebObject containing(String text) {
		if (location == "") {
			return new WebObject("//*[text()[contains(normalize-space(), '" + text + "')]]");
		} else {
			return new WebObject(this.location + "[text()[contains(normalize-space(), '" + text + "')]]");
		}
	}	
	
	// temporary method to check xpath building in early stage of this project.
	public void printLocation() {
		System.out.println(location);
	}

	/*
	 * ******************* Actions performed on WebObject **********************
	 */
	
	public boolean isVisible() {
		return element(indexPosition).isDisplayed();
	}

	public void type(String text) {
		element(indexPosition).clear();
		element(indexPosition).sendKeys(text);
	}

	public void click() {
		element(indexPosition).click();
	}
	
	/*
	 * ******************* WebObject attributes getters **********************
	 */

	public int count() {
		return list().length;
		
	}
	
	public String text(){
		return element(indexPosition).getText();
	}
	
	public boolean hasText(String text) {
		return driver.findElement(By.xpath("//[text()[contains(normalize-space(), '" + text + "')]]")).isDisplayed();
	}
}