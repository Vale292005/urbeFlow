package com.urbeflow.api.service.impl;

import java.util.concurrent.atomic.DoubleAdder;

import org.springframework.stereotype.Service;

import com.urbeflow.api.exception.PeajeException;
import com.urbeflow.api.model.Vehiculo;
import com.urbeflow.api.repository.VehiculoRepository;
import com.urbeflow.api.service.PeajeService;

@Service
public class PeajeServiceImpl implements PeajeService {

    private final DoubleAdder acumuladoTotal = new DoubleAdder();
    private final VehiculoRepository vehiculoRepository;

    public PeajeServiceImpl(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public double registrarPaso(Vehiculo vehiculo) {
        if(vehiculo == null) {
            throw new PeajeException("El vehículo no puede ser nulo");
        }
        if(vehiculo.getPlaca() == null || vehiculo.getPlaca().isEmpty()) {
            throw new PeajeException("La placa del vehículo no puede ser nula o vacía");
        }
        double tarifa = vehiculo.calcularCostoPeaje();
        acumuladoTotal.add(tarifa);

        vehiculoRepository.save(vehiculo); // Guarda el registro del vehículo en la base de datos
        return tarifa;// Retorna el costo del peaje para ese vehículo
    }

    @Override
    public double obtenerAcumuladoTotal() {
        return acumuladoTotal.sum();// Retorna el acumulado total de peajes recaudados
    }
    
}
