package com.appcloud.frankiappforyoigo.POJO;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cristian on 18/07/2016.
 */
public class Reserva {
    private String nombre;
    private String email;
    private String telefono;
    private long fechaReserva;
    private String urlFoto;
    private String terminal;
    private String keyTerminal;
    private String tarifa;
    private int codTarifa;
    private boolean pagoUnico;

    public Reserva() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public long getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(long fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getKeyTerminal() {
        return keyTerminal;
    }

    public void setKeyTerminal(String keyTerminal) {
        this.keyTerminal = keyTerminal;
    }

    public int getCodTarifa() {
        return codTarifa;
    }

    public void setCodTarifa(int codTarifa) {
        this.codTarifa = codTarifa;
    }

    public boolean isPagoUnico() {
        return pagoUnico;
    }

    public void setPagoUnico(boolean pagoUnico) {
        this.pagoUnico = pagoUnico;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("nombre", nombre);
        result.put("telefono", telefono);
        result.put("fechaReserva", fechaReserva);
        result.put("urlFoto", urlFoto);
        return result;
    }
}
