package com.service.weathersaver.WeatherSaverApp.controllers;

import com.service.weathersaver.WeatherSaverApp.dto.SensorDTO;
import com.service.weathersaver.WeatherSaverApp.models.Sensor;
import com.service.weathersaver.WeatherSaverApp.services.SensorsService;
import com.service.weathersaver.WeatherSaverApp.util.errors.ErrorUtil;
import com.service.weathersaver.WeatherSaverApp.util.exceptions.SensorException;
import com.service.weathersaver.WeatherSaverApp.util.errors.SensorErrorResponse;
import com.service.weathersaver.WeatherSaverApp.util.validators.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorsController(SensorsService sensorsService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        Sensor sensorToRegister = convertToSensor(sensorDTO);

        sensorValidator.validate(sensorToRegister, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            if (bindingResult.hasErrors()) {
                ErrorUtil.returnErrorsToClient(bindingResult);
            }

        }

        sensorsService.save(sensorToRegister);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handlerException(SensorException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
