package com.appcloud.frankiappforyoigo.POJO;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cristian on 13/06/2016.
 */
public class OfertaTactica {

    private String fotoUrl;
    private String pantalla;
    private String procesador;
    private String ram;
    private String rom;
    private String camaraTrasera;
    private String camaraDelantera;
    private String bateria;

    private String pvprAltaNueva;
    private String pvprAltaCombinada;
    private String pvprPrepago;

    private String pagoInicialCero;
    private String pagoInicialCinco;
    private String pagoInicialInfinita;
    private String pagoInicialSinFin;
    private String cuotaCero;
    private String cuotaCinco;
    private String cuotaInfinita;
    private String cuotaSinFin;
    private String pagoUnicoCero;
    private String pagoUnicoCinco;
    private String pagoUnicoInfinita;
    private String pagoUnicoSinFin;
    private String pagoFinalCero;
    private String pagoFinalCinco;
    private String pagoFinalInfinita;
    private String pagoFinalSinFin;

    private String pagoInicialCombinadaNaranja50;
    private String pagoInicialCombinadaVerde50;
    private String pagoInicialCombinadaMorada50;
    private String pagoInicialCombinadaAzul50;
    private String cuotaCombinadaNaranja50;
    private String cuotaCombinadaVerde50;
    private String cuotaCombinadaMorada50;
    private String cuotaCombinadaAzul50;
    private String pagoFinalCombinadaNaranja50;
    private String pagoFinalCombinadaVerde50;
    private String pagoFinalCombinadaMorada50;
    private String pagoFinalCombinadaAzul50;
    private String pagoUnicoCombinadaNaranja50;
    private String pagoUnicoCombinadaVerde50;
    private String pagoUnicoCombinadaMorada50;
    private String pagoUnicoCombinadaAzul50;

    private String pagoInicialCombinadaNaranja300;
    private String pagoInicialCombinadaVerde300;
    private String pagoInicialCombinadaMorada300;
    private String pagoInicialCombinadaAzul300;
    private String cuotaCombinadaNaranja300;
    private String cuotaCombinadaVerde300;
    private String cuotaCombinadaMorada300;
    private String cuotaCombinadaAzul300;
    private String pagoFinalCombinadaNaranja300;
    private String pagoFinalCombinadaVerde300;
    private String pagoFinalCombinadaMorada300;
    private String pagoFinalCombinadaAzul300;
    private String pagoUnicoCombinadaNaranja300;
    private String pagoUnicoCombinadaVerde300;
    private String pagoUnicoCombinadaMorada300;
    private String pagoUnicoCombinadaAzul300;
    private Boolean activo;
    private Boolean nuevo;
    private long reservas;

    public OfertaTactica() {
    }


    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getPantalla() {
        return pantalla;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getCamaraTrasera() {
        return camaraTrasera;
    }

    public void setCamaraTrasera(String camaraTrasera) {
        this.camaraTrasera = camaraTrasera;
    }

    public String getCamaraDelantera() {
        return camaraDelantera;
    }

    public void setCamaraDelantera(String camaraDelantera) {
        this.camaraDelantera = camaraDelantera;
    }

    public String getBateria() {
        return bateria;
    }

    public void setBateria(String bateria) {
        this.bateria = bateria;
    }

    public String getPvprAltaNueva() {
        return pvprAltaNueva;
    }

    public void setPvprAltaNueva(String pvprAltaNueva) {
        this.pvprAltaNueva = pvprAltaNueva;
    }

    public String getPvprAltaCombinada() {
        return pvprAltaCombinada;
    }

    public void setPvprAltaCombinada(String pvprAltaCombinada) {
        this.pvprAltaCombinada = pvprAltaCombinada;
    }

    public String getPvprPrepago() {
        return pvprPrepago;
    }

    public void setPvprPrepago(String pvprPrepago) {
        this.pvprPrepago = pvprPrepago;
    }

    public String getPagoInicialCero() {
        return pagoInicialCero;
    }

    public void setPagoInicialCero(String pagoInicialCero) {
        this.pagoInicialCero = pagoInicialCero;
    }

    public String getPagoInicialCinco() {
        return pagoInicialCinco;
    }

    public void setPagoInicialCinco(String pagoInicialCinco) {
        this.pagoInicialCinco = pagoInicialCinco;
    }

    public String getPagoInicialInfinita() {
        return pagoInicialInfinita;
    }

    public void setPagoInicialInfinita(String pagoInicialInfinita) {
        this.pagoInicialInfinita = pagoInicialInfinita;
    }

    public String getPagoInicialSinFin() {
        return pagoInicialSinFin;
    }

    public void setPagoInicialSinFin(String pagoInicialSinFin) {
        this.pagoInicialSinFin = pagoInicialSinFin;
    }

    public String getCuotaCero() {
        return cuotaCero;
    }

    public void setCuotaCero(String cuotaCero) {
        this.cuotaCero = cuotaCero;
    }

    public String getCuotaCinco() {
        return cuotaCinco;
    }

    public void setCuotaCinco(String cuotaCinco) {
        this.cuotaCinco = cuotaCinco;
    }

    public String getCuotaInfinita() {
        return cuotaInfinita;
    }

    public void setCuotaInfinita(String cuotaInfinita) {
        this.cuotaInfinita = cuotaInfinita;
    }

    public String getCuotaSinFin() {
        return cuotaSinFin;
    }

    public void setCuotaSinFin(String cuotaSinFin) {
        this.cuotaSinFin = cuotaSinFin;
    }

    public String getPagoUnicoCero() {
        return pagoUnicoCero;
    }

    public void setPagoUnicoCero(String pagoUnicoCero) {
        this.pagoUnicoCero = pagoUnicoCero;
    }

    public String getPagoUnicoCinco() {
        return pagoUnicoCinco;
    }

    public void setPagoUnicoCinco(String pagoUnicoCinco) {
        this.pagoUnicoCinco = pagoUnicoCinco;
    }

    public String getPagoUnicoInfinita() {
        return pagoUnicoInfinita;
    }

    public void setPagoUnicoInfinita(String pagoUnicoInfinita) {
        this.pagoUnicoInfinita = pagoUnicoInfinita;
    }

    public String getPagoUnicoSinFin() {
        return pagoUnicoSinFin;
    }

    public void setPagoUnicoSinFin(String pagoUnicoSinFin) {
        this.pagoUnicoSinFin = pagoUnicoSinFin;
    }

    public String getPagoFinalCero() {
        return pagoFinalCero;
    }

    public void setPagoFinalCero(String pagoFinalCero) {
        this.pagoFinalCero = pagoFinalCero;
    }

    public String getPagoFinalCinco() {
        return pagoFinalCinco;
    }

    public void setPagoFinalCinco(String pagoFinalCinco) {
        this.pagoFinalCinco = pagoFinalCinco;
    }

    public String getPagoFinalInfinita() {
        return pagoFinalInfinita;
    }

    public void setPagoFinalInfinita(String pagoFinalInfinita) {
        this.pagoFinalInfinita = pagoFinalInfinita;
    }

    public String getPagoFinalSinFin() {
        return pagoFinalSinFin;
    }

    public void setPagoFinalSinFin(String pagoFinalSinFin) {
        this.pagoFinalSinFin = pagoFinalSinFin;
    }

    public String getPagoInicialCombinadaNaranja50() {
        return pagoInicialCombinadaNaranja50;
    }

    public void setPagoInicialCombinadaNaranja50(String pagoInicialCombinadaNaranja50) {
        this.pagoInicialCombinadaNaranja50 = pagoInicialCombinadaNaranja50;
    }

    public String getPagoInicialCombinadaVerde50() {
        return pagoInicialCombinadaVerde50;
    }

    public void setPagoInicialCombinadaVerde50(String pagoInicialCombinadaVerde50) {
        this.pagoInicialCombinadaVerde50 = pagoInicialCombinadaVerde50;
    }

    public String getPagoInicialCombinadaMorada50() {
        return pagoInicialCombinadaMorada50;
    }

    public void setPagoInicialCombinadaMorada50(String pagoInicialCombinadaMorada50) {
        this.pagoInicialCombinadaMorada50 = pagoInicialCombinadaMorada50;
    }

    public String getPagoInicialCombinadaAzul50() {
        return pagoInicialCombinadaAzul50;
    }

    public void setPagoInicialCombinadaAzul50(String pagoInicialCombinadaAzul50) {
        this.pagoInicialCombinadaAzul50 = pagoInicialCombinadaAzul50;
    }

    public String getCuotaCombinadaNaranja50() {
        return cuotaCombinadaNaranja50;
    }

    public void setCuotaCombinadaNaranja50(String cuotaCombinadaNaranja50) {
        this.cuotaCombinadaNaranja50 = cuotaCombinadaNaranja50;
    }

    public String getCuotaCombinadaVerde50() {
        return cuotaCombinadaVerde50;
    }

    public void setCuotaCombinadaVerde50(String cuotaCombinadaVerde50) {
        this.cuotaCombinadaVerde50 = cuotaCombinadaVerde50;
    }

    public String getCuotaCombinadaMorada50() {
        return cuotaCombinadaMorada50;
    }

    public void setCuotaCombinadaMorada50(String cuotaCombinadaMorada50) {
        this.cuotaCombinadaMorada50 = cuotaCombinadaMorada50;
    }

    public String getCuotaCombinadaAzul50() {
        return cuotaCombinadaAzul50;
    }

    public void setCuotaCombinadaAzul50(String cuotaCombinadaAzul50) {
        this.cuotaCombinadaAzul50 = cuotaCombinadaAzul50;
    }

    public String getPagoFinalCombinadaNaranja50() {
        return pagoFinalCombinadaNaranja50;
    }

    public void setPagoFinalCombinadaNaranja50(String pagoFinalCombinadaNaranja50) {
        this.pagoFinalCombinadaNaranja50 = pagoFinalCombinadaNaranja50;
    }

    public String getPagoFinalCombinadaVerde50() {
        return pagoFinalCombinadaVerde50;
    }

    public void setPagoFinalCombinadaVerde50(String pagoFinalCombinadaVerde50) {
        this.pagoFinalCombinadaVerde50 = pagoFinalCombinadaVerde50;
    }

    public String getPagoFinalCombinadaMorada50() {
        return pagoFinalCombinadaMorada50;
    }

    public void setPagoFinalCombinadaMorada50(String pagoFinalCombinadaMorada50) {
        this.pagoFinalCombinadaMorada50 = pagoFinalCombinadaMorada50;
    }

    public String getPagoFinalCombinadaAzul50() {
        return pagoFinalCombinadaAzul50;
    }

    public void setPagoFinalCombinadaAzul50(String pagoFinalCombinadaAzul50) {
        this.pagoFinalCombinadaAzul50 = pagoFinalCombinadaAzul50;
    }

    public String getPagoUnicoCombinadaNaranja50() {
        return pagoUnicoCombinadaNaranja50;
    }

    public void setPagoUnicoCombinadaNaranja50(String pagoUnicoCombinadaNaranja50) {
        this.pagoUnicoCombinadaNaranja50 = pagoUnicoCombinadaNaranja50;
    }

    public String getPagoUnicoCombinadaVerde50() {
        return pagoUnicoCombinadaVerde50;
    }

    public void setPagoUnicoCombinadaVerde50(String pagoUnicoCombinadaVerde50) {
        this.pagoUnicoCombinadaVerde50 = pagoUnicoCombinadaVerde50;
    }

    public String getPagoUnicoCombinadaMorada50() {
        return pagoUnicoCombinadaMorada50;
    }

    public void setPagoUnicoCombinadaMorada50(String pagoUnicoCombinadaMorada50) {
        this.pagoUnicoCombinadaMorada50 = pagoUnicoCombinadaMorada50;
    }

    public String getPagoUnicoCombinadaAzul50() {
        return pagoUnicoCombinadaAzul50;
    }

    public void setPagoUnicoCombinadaAzul50(String pagoUnicoCombinadaAzul50) {
        this.pagoUnicoCombinadaAzul50 = pagoUnicoCombinadaAzul50;
    }

    public String getPagoInicialCombinadaNaranja300() {
        return pagoInicialCombinadaNaranja300;
    }

    public void setPagoInicialCombinadaNaranja300(String pagoInicialCombinadaNaranja300) {
        this.pagoInicialCombinadaNaranja300 = pagoInicialCombinadaNaranja300;
    }

    public String getPagoInicialCombinadaVerde300() {
        return pagoInicialCombinadaVerde300;
    }

    public void setPagoInicialCombinadaVerde300(String pagoInicialCombinadaVerde300) {
        this.pagoInicialCombinadaVerde300 = pagoInicialCombinadaVerde300;
    }

    public String getPagoInicialCombinadaMorada300() {
        return pagoInicialCombinadaMorada300;
    }

    public void setPagoInicialCombinadaMorada300(String pagoInicialCombinadaMorada300) {
        this.pagoInicialCombinadaMorada300 = pagoInicialCombinadaMorada300;
    }

    public String getPagoInicialCombinadaAzul300() {
        return pagoInicialCombinadaAzul300;
    }

    public void setPagoInicialCombinadaAzul300(String pagoInicialCombinadaAzul300) {
        this.pagoInicialCombinadaAzul300 = pagoInicialCombinadaAzul300;
    }

    public String getCuotaCombinadaNaranja300() {
        return cuotaCombinadaNaranja300;
    }

    public void setCuotaCombinadaNaranja300(String cuotaCombinadaNaranja300) {
        this.cuotaCombinadaNaranja300 = cuotaCombinadaNaranja300;
    }

    public String getCuotaCombinadaVerde300() {
        return cuotaCombinadaVerde300;
    }

    public void setCuotaCombinadaVerde300(String cuotaCombinadaVerde300) {
        this.cuotaCombinadaVerde300 = cuotaCombinadaVerde300;
    }

    public String getCuotaCombinadaMorada300() {
        return cuotaCombinadaMorada300;
    }

    public void setCuotaCombinadaMorada300(String cuotaCombinadaMorada300) {
        this.cuotaCombinadaMorada300 = cuotaCombinadaMorada300;
    }

    public String getCuotaCombinadaAzul300() {
        return cuotaCombinadaAzul300;
    }

    public void setCuotaCombinadaAzul300(String cuotaCombinadaAzul300) {
        this.cuotaCombinadaAzul300 = cuotaCombinadaAzul300;
    }

    public String getPagoFinalCombinadaNaranja300() {
        return pagoFinalCombinadaNaranja300;
    }

    public void setPagoFinalCombinadaNaranja300(String pagoFinalCombinadaNaranja300) {
        this.pagoFinalCombinadaNaranja300 = pagoFinalCombinadaNaranja300;
    }

    public String getPagoFinalCombinadaVerde300() {
        return pagoFinalCombinadaVerde300;
    }

    public void setPagoFinalCombinadaVerde300(String pagoFinalCombinadaVerde300) {
        this.pagoFinalCombinadaVerde300 = pagoFinalCombinadaVerde300;
    }

    public String getPagoFinalCombinadaMorada300() {
        return pagoFinalCombinadaMorada300;
    }

    public void setPagoFinalCombinadaMorada300(String pagoFinalCombinadaMorada300) {
        this.pagoFinalCombinadaMorada300 = pagoFinalCombinadaMorada300;
    }

    public String getPagoFinalCombinadaAzul300() {
        return pagoFinalCombinadaAzul300;
    }

    public void setPagoFinalCombinadaAzul300(String pagoFinalCombinadaAzul300) {
        this.pagoFinalCombinadaAzul300 = pagoFinalCombinadaAzul300;
    }

    public String getPagoUnicoCombinadaNaranja300() {
        return pagoUnicoCombinadaNaranja300;
    }

    public void setPagoUnicoCombinadaNaranja300(String pagoUnicoCombinadaNaranja300) {
        this.pagoUnicoCombinadaNaranja300 = pagoUnicoCombinadaNaranja300;
    }

    public String getPagoUnicoCombinadaVerde300() {
        return pagoUnicoCombinadaVerde300;
    }

    public void setPagoUnicoCombinadaVerde300(String pagoUnicoCombinadaVerde300) {
        this.pagoUnicoCombinadaVerde300 = pagoUnicoCombinadaVerde300;
    }

    public String getPagoUnicoCombinadaMorada300() {
        return pagoUnicoCombinadaMorada300;
    }

    public void setPagoUnicoCombinadaMorada300(String pagoUnicoCombinadaMorada300) {
        this.pagoUnicoCombinadaMorada300 = pagoUnicoCombinadaMorada300;
    }

    public String getPagoUnicoCombinadaAzul300() {
        return pagoUnicoCombinadaAzul300;
    }

    public void setPagoUnicoCombinadaAzul300(String pagoUnicoCombinadaAzul300) {
        this.pagoUnicoCombinadaAzul300 = pagoUnicoCombinadaAzul300;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public long getReservas() {
        return reservas;
    }

    public void setReservas(long reservas) {
        this.reservas = reservas;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("cuotaCero", cuotaCero);
        result.put("cuotaCinco", cuotaCinco);
        result.put("cuotaSinFin", cuotaSinFin);
        result.put("pvprAltaNueva", pvprAltaNueva);
        result.put("pagoUnicoCero", pagoUnicoCero);
        result.put("pagoUnicoCinco", pagoUnicoCinco);
        result.put("pagoUnicoSinFin", pagoUnicoSinFin);
        result.put("pvprPrepago", pvprPrepago);
        result.put("pagoInicialCero", pagoInicialCero);
        result.put("pagoInicialCinco", pagoInicialCinco);
        result.put("pagoInicialSinFin", pagoInicialSinFin);
        result.put("pagoFinalCero", pagoFinalCero);
        result.put("pagoFinalCinco", pagoFinalCinco);
        result.put("pagoFinalSinFin", pagoFinalSinFin);
        result.put("activo", activo);
        result.put("nuevo", nuevo);
        result.put("reservas",reservas);

        return result;      
    }
}
