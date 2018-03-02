/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dao;

import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author bogdan
 */
public class Alquileres {
    private Alquiler[] alquileres;
    
    private final int MAX_ALQUILERES=20;
    
    public Alquileres(){
        alquileres = new Alquiler[MAX_ALQUILERES];
    }
    
    public Alquiler[] getAlquiler(){
        return alquileres.clone();
    }
    
    public void openAlquiler(Cliente cliente, Vehiculo vehiculo) {
        int posicion = 0;
        boolean posicionEncontrada = false;
        while (posicion < alquileres.length && !posicionEncontrada) {
            if (alquileres[posicion] == null) {
                posicionEncontrada = true;
            } else if (alquileres[posicion].getVehiculo().getMatricula().equals(vehiculo.getMatricula()) 
                    && alquileres[posicion].getCliente().equals(cliente)
                    && !alquileres[posicion].getVehiculo().getDisponible()) {
                throw new ExcepcionAlquilerVehiculos("Ya existe un alquiler abierto para este vehículo");
            } else {
                posicion++;
            }
        }
        if (posicionEncontrada) {
            alquileres[posicion] = new Alquiler(cliente, vehiculo);
        } else {
            throw new ExcepcionAlquilerVehiculos("El array de trabajos está lleno.");
        }

    }

    public void closeAlquiler(Cliente cliente, Vehiculo vehiculo) {
        int posicion = 0;
        boolean encontrado = false;
        while (posicion < alquileres.length && !encontrado) {
            if (alquileres[posicion] != null
                    && alquileres[posicion].getVehiculo().getMatricula().equals(vehiculo.getMatricula())
                    && alquileres[posicion].getCliente().getDNI().equals(cliente.getDNI())
                    && !alquileres[posicion].getVehiculo().getDisponible()) {
                encontrado = true;
            } else {
                posicion++;
            }
        }
        if (encontrado) {
            alquileres[posicion].close();
        } else {
            throw new ExcepcionAlquilerVehiculos("No hay ningún trabajo abierto para ese vehículo");
        }

    }
}
