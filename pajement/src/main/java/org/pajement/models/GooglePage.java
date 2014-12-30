package org.pajement.models;

public class GooglePage extends WebPage {
	public Content menu = new Content(path + "//body");

	public GooglePage(String url) {
		super(url);
	}

}