/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dominio;

import java.io.Serializable;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bogdan
 */
public class Alquiler implements Serializable {

    private Cliente cliente;
    private Vehiculo vehiculo;
    private Date fecha;
    private int dias;
    private final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yy");
    private final int MS_DIA = 1000 * 60 * 60 * 24;
    private final double PRECIO_DIA = 30;
    private static final long serialVersionUID = 1L;

    public Alquiler(Cliente cliente, Vehiculo vehiculo) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        fecha = new Date();
        dias = 0;
        vehiculo.setDisponible(false);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getDias() {
        return dias;
    }

    public void close() {
        Date fechaFin = new Date();
        dias = difDias(fecha, fechaFin);
        vehiculo.setDisponible(true);
    }

    public int difDias(Date fechaInicio, Date fechaFin) {
        int milisegundos = (int) (fechaFin.getTime() - fechaInicio.getTime());
        dias = milisegundos / MS_DIA;
        return dias + 1;
    }

    public double getPrecio() {
        double precio = dias * PRECIO_DIA + vehiculo.FACTOR_CILINDRADA / 100;
        return precio;
    }
    
    public double getPrecioFijo(){
        return getPrecio()+vehiculo.getPrecioEspecifico();
    }

    @Override
    public String toString() {
        return "Alquiler{" + "Cliente=" + cliente + ", Turismo=" + vehiculo + ", fecha=" + fecha + ", dias=" + dias + '}';
    }
    
    public boolean equals (Object obj) {
        Alquiler a = (Alquiler) obj;
        if(cliente.equals(a.cliente) && vehiculo.equals(a.vehiculo) && fecha.equals(a.fecha))
            return true;
        else
            return false;
    }

}
