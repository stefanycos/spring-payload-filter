package br.com.payload.filter.usage.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.payload.filter.demo.filtering.annotations.Filtered;
import br.com.payload.filter.usage.domain.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@PostMapping
	public User create(@RequestBody @Filtered User payload) {
		return payload;
	}

}
