package com.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.models.HourlyForecastResponse;

public interface HourlyForecastRepository extends JpaRepository<HourlyForecastResponse, Long> {

}
