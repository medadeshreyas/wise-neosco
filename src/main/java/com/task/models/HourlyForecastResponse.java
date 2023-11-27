package com.task.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HourlyForecastResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hourlyResponseId;

	@JsonProperty("cod")
	private String cod;

	@JsonProperty("message")
	private int message;

	@JsonProperty("cnt")
	private int cnt;

	@JsonProperty("list")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "hourlyResponseId")
	private List<HourlyForecastItem> hourlyForecastItems;

	@JsonProperty("city")
	@JoinColumn(name = "hourlyResponseId")
	@OneToOne(cascade = CascadeType.ALL)
	private City city;

}
