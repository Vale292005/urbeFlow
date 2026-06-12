package com.urbeflow.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.urbeflow.api.model.Camion;
import com.urbeflow.api.model.Vehiculo;
import com.urbeflow.api.repository.VehiculoRepository;
import com.urbeflow.api.service.impl.PeajeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PeajeServiceTest {

    @Mock
    private VehiculoRepository vehiculoRepository;

    @InjectMocks
    private PeajeServiceImpl peajeService;

    @Test
    public void registrarPaso_DeberiaRetornarCostoCorrecto_CuandoSeRegistraUnVehiculo(){
        Camion camionSimulado = new Camion();
        camionSimulado.setId(1L);
        camionSimulado.setPlaca("AKN123");
        camionSimulado.setCantidadEjes(3);

        double costoEsperaado = 3*10.0;

        when(vehiculoRepository.save(any(Vehiculo.class))).thenReturn(camionSimulado);

        Double vehiculoCreado = peajeService.registrarPaso(camionSimulado);

        assertEquals(costoEsperaado, vehiculoCreado,"El costo calculado para el camión no es el esperado\"");
    }
    
}
