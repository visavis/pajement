package org.pajement.models;

public class Menu extends Element {

	public Element header = new Element(path + "//h1");
	public Element searchInput = new Element(path + "//input[@id='gbqfq']");
	public Element searchButton = new Element(path + "//button[@aria-label='Szukaj w Google']");
	public ResultsList resultsList = new ResultsList(path + "//div[@class = 'srg']");

	public Menu(String... params) {
		super(params);
	}

	public class ResultsList extends Element {
		public Element result = new Element(path + "//li[@class='g']//h3//a");		
		public ResultsList(String... params) {
			super(params);
		}
	}
}
