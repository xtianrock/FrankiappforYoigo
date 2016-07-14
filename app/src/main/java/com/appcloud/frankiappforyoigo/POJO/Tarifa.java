package com.appcloud.frankiappforyoigo.POJO;

/**
 * Created by cristian on 13/07/2016.
 */
public class Tarifa {

    private String tarifa;
    private String cuota;
    private String datos;
    private boolean ilimitadas;
    private String precio_minuto;
    private String minutos_gratis;
    private String unidad_datos;

    public Tarifa() {
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public boolean isIlimitadas() {
        return ilimitadas;
    }

    public void setIlimitadas(boolean ilimmitadas) {
        this.ilimitadas = ilimmitadas;
    }

    public String getPrecio_minuto() {
        return precio_minuto;
    }

    public void setPrecio_minuto(String precio_minuto) {
        this.precio_minuto = precio_minuto;
    }

    public String getMinutos_gratis() {
        return minutos_gratis;
    }

    public void setMinutos_gratis(String minutos_gratis) {
        this.minutos_gratis = minutos_gratis;
    }

    public String getUnidad_datos() {
        return unidad_datos;
    }

    public void setUnidad_datos(String unidad_datos) {
        this.unidad_datos = unidad_datos;
    }
}
