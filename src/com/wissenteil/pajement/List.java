package com.wissenteil.pajement;



public class List extends Section {
	public Section row = new Section(this.location + "//li");
	public List(String...params) {
		super(params);
	}
}
