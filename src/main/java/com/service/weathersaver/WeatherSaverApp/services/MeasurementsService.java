package com.service.weathersaver.WeatherSaverApp.services;

import com.service.weathersaver.WeatherSaverApp.models.Measurement;
import com.service.weathersaver.WeatherSaverApp.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setAddedAt(LocalDateTime.now());
    }
}
