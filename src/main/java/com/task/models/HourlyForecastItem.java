package com.task.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HourlyForecastItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hourlyId;

	@JsonProperty("dt")
	private long dt;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty("main")
	private Main main;

	@OneToMany
	@JsonProperty("weather")
	private List<Weather> weather;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty("clouds")
	private Clouds clouds;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty("wind")
	private Wind wind;

	@JsonProperty("visibility")
	private int visibility;

	@JsonProperty("pop")
	private double pop;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty("rain")
	private Rain rain;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty("sys")
	private Sys sys;

	@JsonProperty("dt_txt")
	private String dtTxt;

//	@ManyToOne
//	private HourlyForecastResponse hourlyForecastResponse;

}