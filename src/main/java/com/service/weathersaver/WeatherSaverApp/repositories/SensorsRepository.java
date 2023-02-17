package com.service.weathersaver.WeatherSaverApp.repositories;

import com.service.weathersaver.WeatherSaverApp.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
    List<Sensor> findByName(String name);
}
