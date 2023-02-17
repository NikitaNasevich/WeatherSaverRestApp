package com.service.weathersaver.WeatherSaverApp.repositories;

import com.service.weathersaver.WeatherSaverApp.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByRaining(Boolean bool);
}
