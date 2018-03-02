/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dao;

import java.io.Serializable;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author bogdan
 */
public class Vechiculos implements Serializable {

    private Vehiculo[] vehiculos;

    private final int MAX_VEHICULOS = 20;
    
    private static final long serialVersionUID= 1L;

    public Vechiculos() {
        vehiculos = new Vehiculo[MAX_VEHICULOS];
    }

    public void anadir(Vehiculo vehiculo, TipoVehiculo tipoVehiculo) {
        int posicion=buscarPrimerIndiceLibreComprobandoExistencia(vehiculo);
        if (indiceNoSuperaTamano(posicion)) {
            vehiculos[posicion] = tipoVehiculo.getInstancia(vehiculo.getMatricula(), vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getDatosTecnicos());
        } else {
            throw new ExcepcionAlquilerVehiculos("El array de vehículos está lleno.");
        }
    }

    public Vehiculo[] getVehiculos() {
        return vehiculos;
    }
    
    private int buscarPrimerIndiceLibreComprobandoExistencia(Vehiculo turismo){
        int posicion = 0;
        boolean posicionEncontrada = false;
        while (posicion < vehiculos.length && !posicionEncontrada) {
            if (vehiculos[posicion] == null) {
                posicionEncontrada = true;
            } else if (vehiculos[posicion].getMatricula().equals(turismo.getMatricula())) {
                throw new ExcepcionAlquilerVehiculos("Ya existe un vehículo con esa matrícula");
            } else {
                posicion++;
            }
        }
        return posicion;
    }

    private boolean indiceNoSuperaTamano(int posicion){
        return posicion<vehiculos.length;
    }
    
    public void borrar(String matricula) {
        int posicion=buscarIndiceVehiculo(matricula);
        if (indiceNoSuperaTamano(posicion)) {
            desplazarUnaPosicionHaciaIzquierda(posicion);
        } else {
            throw new ExcepcionAlquilerVehiculos("El vehículo a borrar no existe");
        }
    }
    
    private int buscarIndiceVehiculo(String matricula){
        int posicion = 0;
        boolean encontrado = false;
        while (posicion < vehiculos.length && !encontrado) {
            if (vehiculos[posicion] != null && vehiculos[posicion].getMatricula().equals(matricula)) {
                encontrado = true;
            } else {
                posicion++;
            }
        }
        if(encontrado){
            return posicion;
        }else{
            return vehiculos.length;
        }
    }
    
    private void desplazarUnaPosicionHaciaIzquierda(int posicion){
        for (int i = posicion; i < vehiculos.length - 1; i++) {
                vehiculos[i] = vehiculos[i + 1];
            }
            vehiculos[vehiculos.length - 1] = null;
    }
    
    public Vehiculo buscar(String matricula){
        int posicion = buscarIndiceVehiculo(matricula);
        if(indiceNoSuperaTamano(posicion))
            return (vehiculos[posicion]);
        else
            return null;
    }
}
