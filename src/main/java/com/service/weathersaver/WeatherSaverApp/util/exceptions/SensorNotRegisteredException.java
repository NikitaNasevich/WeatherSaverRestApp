package com.service.weathersaver.WeatherSaverApp.util.exceptions;

public class SensorNotRegisteredException extends RuntimeException {
    public SensorNotRegisteredException(String message) {
        super(message);
    }
}
