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

public  class Rain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rainId;

	@JsonProperty("1h")
	private double rain1h;

	// getters and setters
}