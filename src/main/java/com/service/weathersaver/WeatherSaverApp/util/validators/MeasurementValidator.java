package com.service.weathersaver.WeatherSaverApp.util.validators;


import com.service.weathersaver.WeatherSaverApp.models.Measurement;
import com.service.weathersaver.WeatherSaverApp.services.SensorsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    private final SensorsService sensorsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz.getClasses());
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if(!sensorsService.findByName(measurement.getSensor().getName()).isPresent()) {
            errors.rejectValue("sensor", "", "Sensor with this name is not registered");
        }
    }
}
