package com.urbeflow.api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CARRO")
public class Carro extends Vehiculo {

    public Carro() {}

    public Carro(String placa) {
        super(placa);
    }

    @Override
    public double calcularCostoPeaje() {
        return 5.0; // Ejemplo: costo fijo para carros
    }

}
