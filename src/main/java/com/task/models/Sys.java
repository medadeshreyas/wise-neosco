package com.task.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Sys {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sysId;

	@JsonProperty("type")
	private int type;

	@JsonProperty("id")
	private int id;

	@JsonProperty("country")
	private String country;

	@JsonProperty("sunrise")
	private long sunrise;

	@JsonProperty("sunset")
	private long sunset;

}