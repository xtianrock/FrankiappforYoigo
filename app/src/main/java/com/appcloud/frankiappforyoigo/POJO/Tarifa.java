package com.appcloud.frankiappforyoigo.POJO;

/**
 * Created by cristian on 13/07/2016.
 */
public class Tarifa {

    private String tarifa;
    private String cuota;
    private String cuota_promo;
    private String duracion_promo;
    private double datos;
    private double datos_extra;
    private String duracion_datos_extra;
    private boolean ilimitadas;
    private String velocidad_fibra;
    private String velocidad_adsl;
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

    public double getDatos() {
        return datos;
    }

    public void setDatos(double datos) {
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

    public String getCuota_promo() {
        return cuota_promo;
    }

    public void setCuota_promo(String cuota_promo) {
        this.cuota_promo = cuota_promo;
    }

    public String getDuracion_promo() {
        return duracion_promo;
    }

    public void setDuracion_promo(String duracion_promo) {
        this.duracion_promo = duracion_promo;
    }

    public double getDatos_extra() {
        return datos_extra;
    }

    public void setDatos_extra(double datos_extra) {
        this.datos_extra = datos_extra;
    }

    public String getDuracion_datos_extra() {
        return duracion_datos_extra;
    }

    public void setDuracion_datos_extra(String duracion_datos_extra) {
        this.duracion_datos_extra = duracion_datos_extra;
    }

    public String getVelocidad_fibra() {
        return velocidad_fibra;
    }

    public void setVelocidad_fibra(String velocidad_fibra) {
        this.velocidad_fibra = velocidad_fibra;
    }

    public String getVelocidad_adsl() {
        return velocidad_adsl;
    }

    public void setVelocidad_adsl(String velocidad_adsl) {
        this.velocidad_adsl = velocidad_adsl;
    }
}

