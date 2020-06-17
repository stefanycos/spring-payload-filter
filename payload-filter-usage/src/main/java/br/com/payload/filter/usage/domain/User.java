package br.com.payload.filter.usage.domain;

import br.com.payload.filter.demo.filtering.annotations.Prefix;
import br.com.payload.filter.demo.filtering.annotations.Trim;
import lombok.Data;

@Data
public class User {
	
	private String id;
	
	@Trim
	private String name;
	
	@Prefix("ROLE_")
	private String role;

}
