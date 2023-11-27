//package com.task.models;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class WeatherData {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@Column(nullable = false)
//	private String city;
//
//	@Column(nullable = false)
//	private String description;
//
//	@Column(nullable = false)
//	private double temperature;
//
//	@Column(nullable = false)
//	private double humidity;
//
//	@Column(nullable = false)
//	private LocalDateTime timestamp;
//
//}
