package com.service.weathersaver.WeatherSaverApp.controllers;

import com.service.weathersaver.WeatherSaverApp.dto.MeasurementDTO;
import com.service.weathersaver.WeatherSaverApp.dto.SensorDTO;
import com.service.weathersaver.WeatherSaverApp.models.Measurement;
import com.service.weathersaver.WeatherSaverApp.services.MeasurementsService;
import com.service.weathersaver.WeatherSaverApp.services.SensorsService;
import com.service.weathersaver.WeatherSaverApp.util.exceptions.MeasurementNotAddedException;
import com.service.weathersaver.WeatherSaverApp.util.responses.MeasurementErrorResponse;
import com.service.weathersaver.WeatherSaverApp.util.validators.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper, MeasurementValidator measurementValidator, SensorsService sensorsService) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
        this.sensorsService = sensorsService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {
        Measurement measurement = convertToMeasurement(measurementDTO);

        System.out.println(measurement);

        measurementValidator.validate(measurement, bindingResult);

        System.out.println("after valid");
        if(bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for(FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            System.out.println(errorMessage);
            throw new MeasurementNotAddedException(errorMessage.toString());
        }

        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()).get());
        measurementsService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        return measurementsService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handlerException(MeasurementNotAddedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        MeasurementDTO measurementDTO = modelMapper.map(measurement, MeasurementDTO.class);
        measurementDTO.setSensorDTO(new SensorDTO(measurement.getSensor().getName()));
        return measurementDTO;
    }
}
