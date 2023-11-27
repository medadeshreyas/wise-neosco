package com.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.models.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

}
