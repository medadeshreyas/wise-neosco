package com.task.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.task.models.Coordinates;
import com.task.models.HourlyForecastItem;
import com.task.models.HourlyForecastResponse;
import com.task.models.Weather;
import com.task.repositories.HourlyForecastRepository;
import com.task.repositories.WeatherRepository;
import com.task.responses.GeocodingResponse;

@Service
public class HourlyForecastService {

	@Value("${openweather.api.key.hourly}")
	private String apiKey;

	@Value("${openweather.api.hourly.url}")
	private String hourlyForecastApiUrl;

	private final RestTemplate restTemplate;

	@Autowired
	private HourlyForecastRepository hourlyForecastRepository;

	@Autowired
	private WeatherRepository weatherRepo;

	@Autowired
	GeocodingService groGeocodingService;

	public HourlyForecastService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public HourlyForecastResponse getHourlyForecastByCoordinates(double lat, double lon, String city) {
		String url = String.format("%s?lat=%f&lon=%f&appid=%s", hourlyForecastApiUrl, lat, lon, apiKey);
		System.out.println(url);
		HourlyForecastResponse responseFromApi = restTemplate.getForObject(url, HourlyForecastResponse.class);
		responseFromApi.getCity().setName(city);
		prepareAndSaveHourlyForecastResponse(responseFromApi);

		return responseFromApi;
	}

	private void prepareAndSaveHourlyForecastResponse(HourlyForecastResponse response) {
		for (HourlyForecastItem item : response.getHourlyForecastItems()) {

			item.setWeather(prepareAndSaveWeatherEntities(item.getWeather()));
		}

		hourlyForecastRepository.save(response);

	}

	private List<Weather> prepareAndSaveWeatherEntities(List<Weather> weatherEntities) {
		return weatherRepo.saveAll(weatherEntities);
	}

	@Scheduled(fixedRate = 900000)
	public void scheduleRefreshWeatherData() {
		refreshData();
	}

	public void refreshData() {
		List<HourlyForecastResponse> list = hourlyForecastRepository.findAll();

		System.out.println("HOURLUYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYyy");
		for (HourlyForecastResponse data : list) {

			String city = data.getCity().getName();
			List<GeocodingResponse> cood = groGeocodingService.getCoordinatesByCity(city);
			GeocodingResponse coordinates = cood.get(0);

			String url = String.format("%s?lat=%f&lon=%f&appid=%s", hourlyForecastApiUrl, coordinates.getLatitude(),
					coordinates.getLongitude(), apiKey);
			HourlyForecastResponse updatedResponse = restTemplate.getForObject(url, HourlyForecastResponse.class);
			updatedResponse.setHourlyResponseId(data.getHourlyResponseId());

			prepareAndSaveHourlyForecastResponse(updatedResponse);
		}

	}

}
