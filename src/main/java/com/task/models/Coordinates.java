package com.task.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coordinates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codId;

	@JsonProperty("lon")
	private double lon;

	@JsonProperty("lat")
	private double lat;

}