package com.example.client.service;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;

@Service
public class RestTemplateService {

	// http://localhost/api/server/hello
	//response
	public UserResponse hello() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/server/hello")
				.queryParam("name", "홍길동")
				.queryParam("age", 99)
				.encode()
				.build()
				.toUri();
		
		System.out.println(uri.toString());
		
		RestTemplate resTemplate = new RestTemplate();
//		String result = resTemplate.getForObject(uri, String.class);
		ResponseEntity<UserResponse> result = resTemplate.getForEntity(uri, UserResponse.class);
		
		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());
		
		return result.getBody();
	}
	
	public UserResponse post() {
		// http://localhost:9090/api/server/user/{userId}/name/{userName}
		
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/server/user/{userId}/name/{userName}")
				.encode()
				.build()
				.expand(100,"홍길동")
				.toUri();
		
		System.out.println(uri);
		
		// http body -> object -> object mapper -> json -> rest tempalte -> http body json
		UserRequest req = new UserRequest();
		req.setName("김유신");
		req.setAge(45);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getHeaders());
		System.out.println(response.getBody());
		
		return response.getBody();
		
		
	}
}
