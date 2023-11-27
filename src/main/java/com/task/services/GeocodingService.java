package com.task.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.task.responses.GeocodingResponse;

@Service
public class GeocodingService {

	@Value("${openweather.api.key}")
	private String apiKey;

	@Value("${openweather.api.geocoding.url}")
	private String geocodingApiUrl;

	private final RestTemplate restTemplate;

	public GeocodingService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<GeocodingResponse> getCoordinatesByCity(String city) {
		String url = String.format("%s?q=%s&limit=1&appid=%s", geocodingApiUrl, city, apiKey);
		System.out.println("geoo");
		System.out.println(url);
		ResponseEntity<List<GeocodingResponse>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<GeocodingResponse>>() {
				});

		return responseEntity.getBody();
	}
}
