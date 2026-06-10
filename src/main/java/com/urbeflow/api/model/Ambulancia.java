package com.urbeflow.api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AMBULANCIA")
public class Ambulancia extends Vehiculo {
    
    public Ambulancia() {}

    public Ambulancia(String placa) {
        super(placa);
    }

    @Override
    public double calcularCostoPeaje() {
        return 0.0; // Las ambulancias no pagan peaje
    }
}
