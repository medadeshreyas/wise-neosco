package com.task.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.task.models.Coordinates;
import com.task.models.WeatherResponse;
import com.task.repositories.WeatherRepository;
import com.task.repositories.WeatherResponseReposiroy;

@Service
@EnableScheduling
public class OpenWeatherService {

	@Value("${openweather.api.key}")
	private String apiKey;

	@Value("${openweather.api.url}")
	private String apiUrl;

	private final RestTemplate restTemplate;

	public OpenWeatherService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Autowired
	private WeatherResponseReposiroy weatherResponseReposiroy;

	public ResponseEntity<WeatherResponse> getWeatherUpdatesByCity(double lat, double lon, String city) {
		System.out.println(city);
		Optional<WeatherResponse> latest = weatherResponseReposiroy.findByName(city);
		if (latest.isEmpty()) {
			String url = String.format("%s?lat=%f&lon=%f&appid=%s", apiUrl, lat, lon, apiKey);
			System.out.println(lat + " lat");
			System.out.println(lon + " lon");
			WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
			response.setLastFetched(LocalDateTime.now());
			response.setName(city);
			weatherResponseReposiroy.save(response);
			System.out.println("I GOT CAUGHT");
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.ok(latest.get());
		}
	}

	@Scheduled(fixedRate = 900000)
	public void scheduleRefreshWeatherData() {
		refreshWeatherDataForAllCities();
	}

	public void refreshWeatherDataForAllCities() {
		List<WeatherResponse> allCities = weatherResponseReposiroy.findAll();
		System.out.println("=======================================================================================");
		for (WeatherResponse city : allCities) {
			Coordinates coordinates = city.getCoordinates();

			if (coordinates != null) {
				double lat = coordinates.getLat();
				double lon = coordinates.getLon();
				String cityName = city.getName();

				ResponseEntity<WeatherResponse> weatherResponseEntity = getWeatherUpdatesByCity(lat, lon, cityName);

				Optional<WeatherResponse> existingData = weatherResponseReposiroy.findByName(cityName);
				existingData.ifPresent(existing -> {
					WeatherResponse updatedData = weatherResponseEntity.getBody();
					existing.setMain(updatedData.getMain());
					existing.setWind(updatedData.getWind());
					existing.setClouds(updatedData.getClouds());
					existing.setRain(updatedData.getRain());
					existing.setWeather(updatedData.getWeather());
					existing.setLastFetched(LocalDateTime.now());
					weatherResponseReposiroy.save(existing);
				});
			}
		}
	}

}
