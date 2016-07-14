package com.appcloud.frankiappforyoigo.POJO;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cristian on 13/06/2016.
 */
public class OfertaTactica {

    private String terminal;
    private String foto;
    private String financiacionCero;
    private String financiacionCinco;
    private String financiacionSinFin;
    private String pvp;
    private String pagoUnicoCero;
    private String pagoUnicoCinco;
    private String pagoUnicoSinFin;
    private String pagoUnicoPrepago;
    private String pagoInicialCero;
    private String pagoInicialCinco;
    private String pagoInicialSinFin;
    private String pagoFinalCero;
    private String pagoFinalCinco;
    private String pagoFinalSinFin;
    private boolean activo;

    public OfertaTactica() {
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFinanciacionCero() {
        return financiacionCero;
    }

    public void setFinanciacionCero(String financiacionCero) {
        this.financiacionCero = financiacionCero;
    }

    public String getFinanciacionCinco() {
        return financiacionCinco;
    }

    public void setFinanciacionCinco(String financiacionCinco) {
        this.financiacionCinco = financiacionCinco;
    }

    public String getFinanciacionSinFin() {
        return financiacionSinFin;
    }

    public void setFinanciacionSinFin(String financiacionSinFin) {
        this.financiacionSinFin = financiacionSinFin;
    }

    public String getPvp() {
        return pvp;
    }

    public void setPvp(String pvp) {
        this.pvp = pvp;
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

    public String getPagoUnicoSinFin() {
        return pagoUnicoSinFin;
    }

    public void setPagoUnicoSinFin(String pagoUnicoSinFin) {
        this.pagoUnicoSinFin = pagoUnicoSinFin;
    }

    public String getPagoUnicoPrepago() {
        return pagoUnicoPrepago;
    }

    public void setPagoUnicoPrepago(String pagoUnicoPrepago) {
        this.pagoUnicoPrepago = pagoUnicoPrepago;
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

    public String getPagoInicialSinFin() {
        return pagoInicialSinFin;
    }

    public void setPagoInicialSinFin(String pagoInicialSinFin) {
        this.pagoInicialSinFin = pagoInicialSinFin;
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

    public String getPagoFinalSinFin() {
        return pagoFinalSinFin;
    }

    public void setPagoFinalSinFin(String pagoFinalSinFin) {
        this.pagoFinalSinFin = pagoFinalSinFin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("terminal", terminal);
        result.put("financiacionCero", financiacionCero);
        result.put("financiacionCinco", financiacionCinco);
        result.put("financiacionSinFin", financiacionSinFin);
        result.put("pvp", pvp);
        result.put("pagoUnicoCero", pagoUnicoCero);
        result.put("pagoUnicoCinco", pagoUnicoCinco);
        result.put("pagoUnicoSinFin", pagoUnicoSinFin);
        result.put("pagoUnicoPrepago", pagoUnicoPrepago);
        result.put("pagoInicialCero", pagoInicialCero);
        result.put("pagoInicialCinco", pagoInicialCinco);
        result.put("pagoInicialSinFin", pagoInicialSinFin);
        result.put("pagoFinalCero", pagoFinalCero);
        result.put("pagoFinalCinco", pagoFinalCinco);
        result.put("pagoFinalSinFin", pagoFinalSinFin);
        result.put("activo", activo);

        return result;      
    }
}
