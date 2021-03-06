package com.example.client.service;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.client.dto.Req;
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
	
	public UserResponse exchange() {
		
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
		
		RequestEntity<UserRequest> requestEntity = RequestEntity
				.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.header("x-authorization", "abcd")
				.header("custom-header", "ffff")
				.body(req);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);
		
		return response.getBody();
	}
	
	public Req<UserResponse> genericExchange() {
		
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/server/user/{userId}/name/{userName}")
				.encode()
				.build()
				.expand(100,"홍길동")
				.toUri();
		
		System.out.println(uri);
		
		// http body -> object -> object mapper -> json -> rest tempalte -> http body json
		
		UserRequest userRequest = new UserRequest();
		userRequest.setName("김유신");
		userRequest.setAge(45);
		
		Req<UserRequest> req = new Req<UserRequest>();
		req.setHeader(
			new Req.Header()
		);
		req.setResBody(
			userRequest
		);
		
		RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
				.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.header("x-authorization", "abcd")
				.header("custom-header", "ffff")
				.body(req);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Req<UserResponse>> response
			= restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});
		
		return response.getBody();
	}
}
