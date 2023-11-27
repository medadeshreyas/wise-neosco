package com.task.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.task.models.HourlyForecastResponse;
import com.task.models.WeatherResponse;
import com.task.responses.GeocodingResponse;
import com.task.services.GeocodingService;
import com.task.services.HourlyForecastService;
import com.task.services.OpenWeatherService;

@Controller
public class WeatherController {

	@Autowired
	private GeocodingService geocodingService;

	@Autowired
	private HourlyForecastService hourlyForecastService;
	@Value("${openweather.api.key}")
	private String apiKey;

	@Value("${openweather.api.url}")
	private String weatherApiUrl;

	@Autowired
	private OpenWeatherService weatherServvice;

	@GetMapping("/current")
	public ResponseEntity<WeatherResponse> getCoordinatesByCity(@RequestParam String city) {

		List<GeocodingResponse> coordinates = geocodingService.getCoordinatesByCity(city);

		if (coordinates.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		GeocodingResponse firstCoordinates = coordinates.get(0);

		return weatherServvice.getWeatherUpdatesByCity(firstCoordinates.getLatitude(), firstCoordinates.getLongitude(),
				firstCoordinates.getName());
	}

	@GetMapping("/hourly-forecast/city")

	public ResponseEntity<HourlyForecastResponse> getHourlyBycity(@RequestParam String city) {
		List<GeocodingResponse> coordinates = geocodingService.getCoordinatesByCity(city);

		if (coordinates.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		GeocodingResponse firstCoordinates = coordinates.get(0);

		HourlyForecastResponse response = hourlyForecastService.getHourlyForecastByCoordinates(
				firstCoordinates.getLatitude(), firstCoordinates.getLongitude(), firstCoordinates.getName());
		return ResponseEntity.ok(response);
	}

//	@GetMapping("/dashboard-page")
//	public String userDashboard(Model model) {
//		ResponseEntity<WeatherResponse> weatherResponseEntity = getCoordinatesByCity("Mumbai");
//
//		System.out.println(weatherResponseEntity.getBody());
//		if (weatherResponseEntity.getStatusCode().is2xxSuccessful()) {
//			model.addAttribute("weatherResponse", weatherResponseEntity.getBody());
//		} else {
//			model.addAttribute("weatherResponse", null);
//		}
//
//		return "dashboard";
//	}
	@GetMapping("/dashboard-page/refreshWeather")
	public String userDashboardWeather(Model model, @RequestParam(required = false) String city) {
		ResponseEntity<WeatherResponse> weatherResponseEntity;

		if (city != null && !city.isEmpty()) {
			weatherResponseEntity = getCoordinatesByCity(city);
		} else {
			weatherResponseEntity = getCoordinatesByCity("Mumbai");
		}

		if (weatherResponseEntity.getStatusCode().is2xxSuccessful()) {
			System.out.println(weatherResponseEntity);
			model.addAttribute("weatherResponse", weatherResponseEntity.getBody());
		} else {
			model.addAttribute("weatherResponse", null);
		}

		return "dashboard";
	}

	@GetMapping("/dashboard-page/refreshHourly")
	public String userDashboardHourly(Model model, @RequestParam(required = false) String city) {
		ResponseEntity<HourlyForecastResponse> hourlyForecastResponse;

		if (city != null && !city.isEmpty()) {
			hourlyForecastResponse = getHourlyBycity(city);
		} else {
			hourlyForecastResponse = getHourlyBycity("Bangalore");
			// By Default Weather
		}
		System.out.println("----------------------------------------------------------");
		System.out.println(hourlyForecastResponse);
		if (hourlyForecastResponse.getStatusCode().is2xxSuccessful()) {
			model.addAttribute("hourlyForecastResponse", hourlyForecastResponse.getBody());

		} else {
			model.addAttribute("hourlyForecastResponse", null);
		}

		return "dashboard";
	}
}
