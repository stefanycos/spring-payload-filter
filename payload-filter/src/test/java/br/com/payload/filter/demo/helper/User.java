package br.com.payload.filter.demo.helper;

import br.com.payload.filter.demo.filtering.annotations.Trim;

public class User {
	
	@Trim
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
