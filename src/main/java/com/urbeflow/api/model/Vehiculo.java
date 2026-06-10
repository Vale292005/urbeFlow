package com.urbeflow.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehiculos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_vehiculo", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String placa;

    public Vehiculo() {}

    public Vehiculo(String placa) {
        this.placa = placa;
    }

    //Método abstacto: Cada tipo de vehículo implementará su propia forma de calcular el costo del peaje
    public abstract double calcularCostoPeaje();

    //Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

}