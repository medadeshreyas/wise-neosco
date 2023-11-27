package com.task.models;

import java.time.LocalDateTime;
import java.util.List;

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

//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class WeatherResponse {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long weatherResponseId;
//
//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonProperty("coord")
//	private Coordinates coordinates;
//
//	@OneToMany
//	@JsonProperty("weather")
//	private List<Weather> weather;
//
//	@JsonProperty("base")
//	private String base;
//
//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonProperty("main")
//	private Main main;
//
//	@JsonProperty("visibility")
//	private int visibility;
//
//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonProperty("wind")
//	private Wind wind;
//
//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonProperty("rain")
//	private Rain rain;
//
//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonProperty("clouds")
//	private Clouds clouds;
//
//	@JsonProperty("dt")
//	private long dt;
//
//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonProperty("sys")
//	private Sys sys;
//
//	@JsonProperty("timezone")
//	private int timezone;
//
//	@JsonProperty("id")
//	private long id;
//
//	@JsonProperty("name")
//	private String name;
//
//	@JsonProperty("cod")
//	private int cod;
//
//}
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long weatherResponseId;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty("coord")
	private Coordinates coordinates;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "weatherResponseId")
	@JsonProperty("weather")
	private List<Weather> weather;

	@JsonProperty("base")
	private String base;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty("main")
	private Main main;

	@JsonProperty("visibility")
	private int visibility;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty("wind")
	private Wind wind;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty("rain")
	private Rain rain;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty("clouds")
	private Clouds clouds;

	@JsonProperty("dt")
	private long dt;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonProperty("sys")
	private Sys sys;

	@JsonProperty("timezone")
	private int timezone;

	@JsonProperty("id")
	private long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("cod")
	private int cod;

	private LocalDateTime lastFetched;

	private boolean hourlyEntry;

}
