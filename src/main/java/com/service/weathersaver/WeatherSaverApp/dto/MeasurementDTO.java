package com.service.weathersaver.WeatherSaverApp.dto;

import com.service.weathersaver.WeatherSaverApp.models.Sensor;
import jakarta.validation.constraints.*;

public class MeasurementDTO {

    @Min(value = -100, message = "Value should be between -100 and 100")
    @Max(value = 100, message = "Value should be between -100 and 100")
    @NotNull
    private Double value;

    @NotNull(message = "Raining value can't be null")
    private Boolean raining;

    private Sensor sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor name=" + sensor +
                '}';
    }
}
