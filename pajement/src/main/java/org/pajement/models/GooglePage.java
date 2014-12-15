package org.pajement.models;

public class GooglePage extends WebPage {
	public Menu menu = new Menu(path + "//body");

	public GooglePage(String url) {
		super(url);
	}

}