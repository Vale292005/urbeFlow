package com.urbeflow.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbeflow.api.dto.RegistroVehiculoDTO;
import com.urbeflow.api.model.Ambulancia;
import com.urbeflow.api.model.Camion;
import com.urbeflow.api.model.Carro;
import com.urbeflow.api.model.Vehiculo;
import com.urbeflow.api.service.PeajeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/peaje")
public class PeajeController {
    
    private final PeajeService peajeService;

    public PeajeController(PeajeService peajeService) {
        this.peajeService = peajeService;
    }
    
    @PostMapping("/cobrar")
    public String registrarCobro(@RequestBody RegistroVehiculoDTO dto) {
        Vehiculo vehiculo;

        // Evalua el tipo del vehiculo
        if(dto.getTipo()==null) {
            return "Tipo de vehículo no especificado";
        }

        switch (dto.getTipo().toUpperCase()) {
            case "CARRO":
                vehiculo = new Carro(dto.getPlaca());
                break;
            case "CAMION":
                int ejes = (dto.getEjes() != null) ? dto.getEjes() : 2; // Asume 2 ejes si no se especifica
                vehiculo = new Camion(dto.getPlaca(), ejes);
                break;
            case "AMBULANCIA":
                vehiculo = new Ambulancia(dto.getPlaca());
                break;
            default:
                return "Tipo de vehículo no reconocido";
        }

        // Registra el paso del vehículo por el peaje y obtiene el costo
        double costoPeaje = peajeService.registrarPaso(vehiculo);
        return "Vehiculo con placa " + dto.getPlaca() + " ha sido registrado. Costo del peaje: $" + costoPeaje;
    }

    @GetMapping("/recaudoTotal")
    public String verRecaudoTotal() {
        return "Recaudo total acumulado: $" + peajeService.obtenerAcumuladoTotal();
    }
    
}
