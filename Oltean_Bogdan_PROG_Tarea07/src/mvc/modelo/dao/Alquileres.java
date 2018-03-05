/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author bogdan
 */
public class Alquileres implements Serializable {

    private Alquiler[] alquileres;

    private final int MAX_ALQUILERES = 20;

    private static final long serialVersionUID = 1L;
    
    private final String FICHERO_ALQUILERES = "datos"+File.separator+"alquileres.dat";
    public Alquileres() {
        alquileres = new Alquiler[MAX_ALQUILERES];
    }

    public Alquiler[] getAlquiler() {
        return alquileres.clone();
    }

    public void leerTrabajos() {
        File fichero = new File(FICHERO_ALQUILERES);
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(new FileInputStream(fichero));
            try {
                alquileres = (Alquiler[]) entrada.readObject();
                entrada.close();
                System.out.println("Fichero alquileres leído satisfactoriamente.");
            } catch (ClassNotFoundException e) {
                System.out.println("No puedo encontrar la clase que tengo que leer.");
            } catch (IOException e) {
                System.out.println("Error inesperado de Entrada/Salida.");
            }
        } catch (IOException e) {
            System.out.println("No puedo abrir el fihero de alquileres.");
        }
    }

    public void escribirTrabajos() {
        File fichero = new File(FICHERO_ALQUILERES);
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
            salida.writeObject((Alquiler[]) alquileres);
            salida.close();
            System.out.println("Fichero Alquileres escrito satisfactoriamente");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo crear el fichero de alquileres");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida");
        }
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
