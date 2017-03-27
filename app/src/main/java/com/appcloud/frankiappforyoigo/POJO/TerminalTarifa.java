package com.appcloud.frankiappforyoigo.POJO;

/**
 * Created by cristian on 27/03/2017.
 */

public class TerminalTarifa {

    private String nombreTarifa;
    private String pagoInicial;
    private String cuota;
    private String pagoFinal;

    public TerminalTarifa(String nombreTarifa,String pagoInicial,String cuota,String pagoFinal){
        this.nombreTarifa = nombreTarifa;
        this.pagoInicial = pagoInicial;
        this.cuota = cuota;
        this.pagoFinal = pagoFinal;
    }

    public String getPagoInicial() {
        return pagoInicial;
    }

    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public void setNombreTarifa(String nombreTarifa) {
        this.nombreTarifa = nombreTarifa;
    }

    public void setPagoInicial(String pagoInicial) {
        this.pagoInicial = pagoInicial;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    public String getPagoFinal() {
        return pagoFinal;
    }

    public void setPagoFinal(String pagoFinal) {
        this.pagoFinal = pagoFinal;
    }
}
