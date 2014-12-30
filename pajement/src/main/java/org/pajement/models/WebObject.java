package org.pajement.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.pajement.test.TestRun;

public class WebObject {
	WebDriver driver = TestRun.driver;
	String path = "//*";
	String url;
	int indexPosition;
//TODO - consider how to solve nesting objects arrays so they will return proper object instances and not WebObjects.
	public WebObject(String... params) {
		//TODO - the parent path should be collected by constructor correctly without passing it as parameter while declaring an object.
		if (params.length == 0) {
			path = "//*";
		} else if (params.length == 1) {
			path = xpathyPath("//*", params[0]);
		} else if (params.length == 2) {
			path = xpathyPath(params[0], params[1]);
		}
	}
	
	private String xpathyPath(String parentPath, String givenPath) {
		String xpathed;
		if (givenPath.matches("(^/+)(.*)")) {
			xpathed = givenPath;
			return parentPath + xpathed;
		} else if (givenPath.contains("#")) {
			System.out.println(givenPath.indexOf("#"));
			xpathed = xpathyCss(givenPath.indexOf("#"), givenPath, "id");
			return parentPath + xpathed;
		} else if (givenPath.contains(".")) {
			System.out.println(givenPath.indexOf("."));
			xpathed = xpathyCss(givenPath.indexOf("."), givenPath, "class");
			return parentPath + xpathed;
		} else if (givenPath.contains("[") && givenPath.contains("=") && givenPath.contains("]")) { 
			xpathed = xpathyBracketNotation(givenPath);
			return parentPath + xpathed;
		} else {
			return parentPath + "//" + givenPath;
		}
	}

	private String xpathyCss(int index, String givenPath, String css_type) {
		String tag, value;
		if (index > 0) {
			if (index < givenPath.length() - 1) {
				tag = givenPath.substring(0, index);
				value = givenPath.substring(index + 1, givenPath.length());
				return "//" + tag + "[@" + css_type + " = '" + value + "']";
			} else {
				tag = givenPath.substring(0, index);
				return "//" + tag;
			}
		} else {
			value = givenPath.substring(1, givenPath.length());
			if (value.length() > 0) {
				return "//*[@" + css_type + " = '" + value + "']";
			} else {
				return "//" + css_type;
			}
		}
	}
	
	private String xpathyBracketNotation(String givenPath) {
		int bracketIndex = givenPath.indexOf("[");
		if (bracketIndex > 0) {
			return "//" + givenPath.substring(0, bracketIndex) + "[@" + givenPath.substring(bracketIndex +1, givenPath.length());
		} else {
			return "//*" + "[@" + givenPath.substring(1, givenPath.length());
		}
	}

	public void setIndex(int index) {
		this.indexPosition = index;
	}

	public void setLocation(String location) {
		this.path = location;
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
		return driver.findElement(By.xpath(path));
	}

	// returns nth of found elements
	// for readability indexing will start from 1 here
	public WebElement element(int position) {

		return driver.findElements(By.xpath(this.path)).get(position);
	}

	// returns array of elements - useful when operations such as count need to
	// be performed
	public WebElement[] elements() {
		int size = driver.findElements(By.xpath(this.path)).size();
		WebElement[] results = new WebElement[size];
		for (int i = 0; i < size; i++) {
			results[i] = driver.findElements(By.xpath(this.path)).get(i);
		}
		return results;
	}

	/*
	 * ******************* Methods returning WebObjects **********************
	 */

	public WebObject[] list() {
		try {
			int size = driver.findElements(By.xpath(this.path)).size();
			WebObject[] results = new WebObject[size];
			for (int i = 0; i < size; i++) {
				WebObject tempObject = new WebObject(this.path);
				tempObject.setIndex(i);
				results[i] = tempObject;
			}
			return results;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebObject list(int index) {
		return list()[index];
	}

	public WebObject first() {
		return list(0);
	}

	public WebObject last() {
		int last = driver.findElements(By.xpath(this.path)).size() - 1;
		if (last > 0) {
			return list(last);
		} else {
			return list(0);
		}
	}

	public WebObject containing(String text) {
		if (path == "") {
			return new WebObject("//*[text()[contains(normalize-space(), '"
					+ text + "')]]");
		} else {
			return new WebObject(this.path
					+ "[text()[contains(normalize-space(), '" + text + "')]]");
		}
	}

	// temporary method to test path building in early stage of this project.
	public void printLocation() {
		System.out.println(path);
	}

	/*
	 * ******************* Actions performed on WebObject **********************
	 */

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

	public String text() {
		return element(indexPosition).getText();
	}

	public boolean isVisible() {
		try {
			return element().isDisplayed();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean hasText(String text) {
		try {
			return containing(text).isVisible();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}
}