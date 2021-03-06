package org.pajement.models;

public class WebPage extends WebObject {
	String url;
		
	public WebPage(String url) {
		super();
		setUrl(url);		
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void load() {
		driver.get(url);
	}
	
	public void load(String params) {
		driver.get(url + params);
	}
}
