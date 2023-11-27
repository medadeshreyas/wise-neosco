package com.task.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wind {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long windId;

	@JsonProperty("speed")
	private double speed;

	@JsonProperty("deg")
	private int deg;

	@JsonProperty("gust")
	private double gust;

	// getters and setters
}
