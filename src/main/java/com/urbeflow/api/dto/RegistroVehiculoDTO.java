package com.urbeflow.api.dto;

public class RegistroVehiculoDTO {
    private String placa;
    private String tipo;
    private Integer ejes;

    public RegistroVehiculoDTO() {}

    public RegistroVehiculoDTO(String placa, String tipo, Integer ejes) {
        this.placa = placa;
        this.tipo = tipo;
        this.ejes = ejes;
    }

    // Getters y Setters
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Integer getEjes() { return ejes; }
    public void setEjes(Integer ejes) { this.ejes = ejes; }
}
