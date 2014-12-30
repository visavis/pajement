package org.pajement.models;

public class GooglePage extends WebPage {
	public Content content = new Content(path, "//body");

	public GooglePage(String url) {
		super(url);
	}

}