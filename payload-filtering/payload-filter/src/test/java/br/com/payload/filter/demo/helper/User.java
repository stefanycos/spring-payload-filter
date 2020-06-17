package br.com.payload.filter.demo.helper;

import br.com.payload.filter.demo.filtering.annotations.Trim;

public class User {
	
	@Trim
	private String name;

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
	
	

}
