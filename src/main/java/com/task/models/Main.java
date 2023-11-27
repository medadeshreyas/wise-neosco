package com.task.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Main {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mainId;

	@JsonProperty("temp")
	private double temp;

	@JsonProperty("feels_like")
	private double feelsLike;

	@JsonProperty("temp_min")
	private double tempMin;

	@JsonProperty("temp_max")
	private double tempMax;

	@JsonProperty("pressure")
	private int pressure;

	@JsonProperty("humidity")
	private int humidity;

	@JsonProperty("sea_level")
	private int seaLevel;

	@JsonProperty("grnd_level")
	private int grndLevel;

	// getters and setters
}
