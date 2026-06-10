package com.urbeflow.api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CAMION")
public class Camion extends Vehiculo {
    
    private int cantidadEjes;

    public Camion() {}

    public Camion(String placa, int cantidadEjes) {
        super(placa);
        this.cantidadEjes = cantidadEjes;
    }

    @Override
    public double calcularCostoPeaje() {
        return this.cantidadEjes * 10.0; // Ejemplo: cada eje cuesta 10 unidades monetarias
    }

    // Getters y Setters
    public int getCantidadEjes() { return cantidadEjes; }
    public void setCantidadEjes(int cantidadEjes) { this.cantidadEjes = cantidadEjes; } 
}
