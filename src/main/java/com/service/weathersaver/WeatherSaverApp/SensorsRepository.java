package com.service.weathersaver.WeatherSaverApp;

import com.service.weathersaver.WeatherSaverApp.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
}
