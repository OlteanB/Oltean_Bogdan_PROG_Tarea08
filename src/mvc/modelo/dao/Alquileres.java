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
import java.util.LinkedList;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author bogdan
 */
public class Alquileres implements Serializable {

    private LinkedList<Alquiler> alquileres;

    private static final long serialVersionUID = 1L;
    
    private final String FICHERO_ALQUILERES = "datos"+File.separator+"alquileres.dat";
    public Alquileres() {
        alquileres = new LinkedList<Alquiler>();
    }

    public LinkedList<Alquiler> getAlquiler() {
        return (LinkedList<Alquiler>) alquileres.clone();
    }

    public void leerAlquileres() {
        File fichero = new File(FICHERO_ALQUILERES);
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(new FileInputStream(fichero));
            try {
                alquileres = (LinkedList<Alquiler>) entrada.readObject();
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

    public void escribirAlquileres() {
        File fichero = new File(FICHERO_ALQUILERES);
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
            salida.writeObject((LinkedList<Alquiler>) alquileres);
            salida.close();
            System.out.println("Fichero Alquileres escrito satisfactoriamente");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo crear el fichero de alquileres");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida");
        }
    }
    
    public void anadir(Alquiler alquiler) {
        if ( ! alquileres.contains(alquiler)) {
            alquileres.add(alquiler);
        } else {
            throw new ExcepcionAlquilerVehiculos("Ya existe un alquiler abierto para este vehículo");
        }
    }

    public void openAlquiler(Cliente cliente, Vehiculo vehiculo) {
        /*int posicion = 0;
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
        }*/
        for (Alquiler a : alquileres) {
            if (a.getCliente().equals (cliente) && a.getVehiculo().equals(vehiculo))
                throw new ExcepcionAlquilerVehiculos("Ya existe un alquiler abierto para este vehículo");
        }
        alquileres.add(new Alquiler(cliente, vehiculo));
    }

    public void closeAlquiler(Cliente cliente, Vehiculo vehiculo) {
        for (Alquiler a : alquileres) {
            if (a.getCliente().equals (cliente) && a.getVehiculo().equals(vehiculo)) {
                a.close();
                return;
            }
        }
        throw new ExcepcionAlquilerVehiculos("No hay ningún trabajo abierto para ese vehículo");

    }
}
