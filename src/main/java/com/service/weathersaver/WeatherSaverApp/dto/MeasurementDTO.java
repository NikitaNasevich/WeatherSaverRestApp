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

    private SensorDTO sensorDTO;

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

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }
}
