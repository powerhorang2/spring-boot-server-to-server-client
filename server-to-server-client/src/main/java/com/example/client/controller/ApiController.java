package com.example.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;

@RestController
@RequestMapping("/api/client")
public class ApiController {

	private final RestTemplateService restTemplateService;
	
	public ApiController(RestTemplateService restTemplateService) {
		this.restTemplateService = restTemplateService;
	}
	
	@GetMapping("/hello")
	public UserResponse getHello() {
		return restTemplateService.post();
	}
	
}
