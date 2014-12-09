package org.wissenteil.pajement;

public class Menu extends Element {

	public Element header = new Element(this.location + "//h1");
	public Element searchInput = new Element(this.location + "//input[@id='gbqfq']");
	public Element searchButton = new Element(this.location	+ "//button[@aria-label='Szukaj w Google']");
	public ResultsList resultsList = new ResultsList(this.location + "//div[@class = 'srg']");

	public Menu(String... params) {
		super(params);
	}

	public class ResultsList extends Element {
		public Element result = new Element(this.location + "//li[@class='g']//h3//a");		
		public ResultsList(String... params) {
			super(params);
		}
	}
}
