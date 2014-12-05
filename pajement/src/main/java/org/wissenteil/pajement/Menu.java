package org.wissenteil.pajement;

public class Menu extends Section {
	
	public List list = new List(this.location + "//ul");
	public Element header = new Element(this.location + "//h1");
	public Element searchInput = new Element(this.location + "//input[@id='gbqfq']");
	public Element searchButton = new Element(this.location + "//button[@aria-label='Szukaj w Google']");
	public ResultsList resultsList = new ResultsList(this.location + "//div[@class = 'srg']");
	
	
	public Menu(String...params) {
		super(params);
	}
	
	public class ResultsList extends Section {
		public Element result = new Element(this.location + "//li[@class='g']");
		
		
		public ResultsList(String...params) {
			super(params);
		}
	}	
}
