package com.service.weathersaver.WeatherSaverApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @Min(value = -100, message = "Value should be between -100 and 100")
    @Max(value = 100, message = "Value should be between -100 and 100")
    @NotNull
    private Double value;

    @Column(name = "raining")
    @NotNull
    private Boolean raining;
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotNull(message = "Sensor shouldn't be empty")
    @JsonBackReference
    private Sensor sensor;

    @Column(name = "added_at")
    private LocalDateTime addedAt;

    public Measurement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

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

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", sensor=" + sensor +
                ", value=" + value +
                ", raining=" + raining +
                '}';
    }
}
