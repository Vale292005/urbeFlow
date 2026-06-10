package com.urbeflow.api.service;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;

import com.urbeflow.api.model.Ambulancia;
import com.urbeflow.api.model.Camion;
import com.urbeflow.api.model.Carro;
import com.urbeflow.api.model.Vehiculo;

public class SimuladorTrafico implements CommandLineRunner {

    private final PeajeService peajeService;

    public SimuladorTrafico(PeajeService peajeService) {
        this.peajeService = peajeService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Simulador de tráfico iniciado...");
        Thread.sleep(2000);

        ExecutorService cabinasPeaje = Executors.newFixedThreadPool(10); // Simula 10 cabinas de peaje
        Random random = new Random();

        int totalVehiculos = 1000; // Simula el paso de 1000 vehículos

        long tiempoInicio = System.currentTimeMillis();

        for (int i = 0; i < totalVehiculos; i++) {
            final int numeroVehiculo = i;

            // cada vehiculo pasa por uno de los peajes
            cabinasPeaje.execute(() -> {
                Vehiculo v;
                String placa = "ABC" + numeroVehiculo;
                int tipoAleatorio = random.nextInt(3);// numero entre 0 y 2

                switch (tipoAleatorio) {
                    case 0:
                        v = new Carro(placa);
                        break;
                    case 1:
                        int ejes = 2 + random.nextInt(4); // entre 2 y 5 ejes
                        v = new Camion(placa, ejes);
                        break;
                    default:
                        v = new Ambulancia(placa);
                        break;
                }
                peajeService.registrarPaso(v);
            });
        }

        cabinasPeaje.shutdown();
        cabinasPeaje.awaitTermination(1, TimeUnit.MINUTES);

        long tiempoFin = System.currentTimeMillis();

        System.out.println("\n🛑 [SIMULADOR] ¡Simulación finalizada con éxito!");
        System.out.println("⏱️ Tiempo total de procesamiento: " + (tiempoFin - tiempoInicio) + " milisegundos.");
        System.out.println("💰 " + peajeService.obtenerAcumuladoTotal()
                + " USD recaudados de forma segura mediante DoubleAdder.\n");
    }
}
