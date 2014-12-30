package org.pajement.models;

public class Content extends WebObject {

	public WebObject header = new WebObject(path, "//h1");
	public WebObject searchInput = new WebObject(path, "input#gbqfq");
	public WebObject searchButton = new WebObject(path, "///button[@aria-label='Szukaj w Google']");
	public ResultsList resultsList = new ResultsList(path, "//div[@class = 'srg']");
	public WebObject someMatch = new WebObject(path, "div#someMatcchers");
	public WebObject someMatch1 = new WebObject(path, "div#");
	public WebObject someMatch2 = new WebObject(path, "#someMatcchers");
	public WebObject someMatch3 = new WebObject(path, "div.someMatcchers");
	public WebObject someMatch4 = new WebObject(path, ".a");
	public WebObject someMatch5 = new WebObject(path, "div[name = 'something]");

	public Content(String... params) {
		super(params);
	}

	public class ResultsList extends WebObject {
		public WebObject result = new WebObject(path, "//li[@class='g']//h3//a");		
		
		public ResultsList(String... params) {
			super(params);
		}
	}
}
