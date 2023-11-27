package com.task.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.models.WeatherResponse;

@Repository
public interface WeatherResponseReposiroy extends JpaRepository<WeatherResponse, Long> {

	Optional<WeatherResponse> findByName(String name);
}
