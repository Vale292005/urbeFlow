package com.urbeflow.api.service;

import com.urbeflow.api.model.Vehiculo;

public interface PeajeService {

    // Registrar el paso de un vehículo por el peaje y retornar el costo del peaje para ese vehículo
    double registrarPaso(Vehiculo vehiculo);

    // Obtener acumulado total
    double obtenerAcumuladoTotal();
}
