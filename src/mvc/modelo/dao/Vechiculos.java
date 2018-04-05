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
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author bogdan
 */
public class Vechiculos implements Serializable {

    private LinkedList<Vehiculo> vehiculos;

    //private final int MAX_VEHICULOS = 20;

    private final String FICHERO_VEHICULOS = "datos" + File.separator + "vehiculos.dat";

    public Vechiculos() {
        vehiculos = new LinkedList<Vehiculo>();
    }

    public void leerVehiculos() {
        File fichero = new File(FICHERO_VEHICULOS);
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(new FileInputStream(fichero));
            try {
                vehiculos = (LinkedList<Vehiculo>) entrada.readObject();
                entrada.close();
                System.out.println("Fichero vehículos leído satisfactoriamente.");
            } catch (ClassNotFoundException e) {
                System.out.println("No puedo encontrar la clase que tengo que leer.");
            } catch (IOException e) {
                System.out.println("Error inesperado de Entrada/Salida.");
            }
        } catch (IOException e) {
            System.out.println("No puedo abrir el fihero de vehículos.");
        }
    }

    public void escribirVehiculos() {
        File fichero = new File(FICHERO_VEHICULOS);
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
            salida.writeObject((LinkedList<Vehiculo>) vehiculos);
            salida.close();
            System.out.println("Fichero vehículos escrito satisfactoriamente");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo crear el fichero de vehículos");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida");
        }
    }

    /*public void anadir(Vehiculo vehiculo, TipoVehiculo tipoVehiculo) {
        int posicion = buscarPrimerIndiceLibreComprobandoExistencia(vehiculo);
        if (indiceNoSuperaTamano(posicion)) {
            vehiculos[posicion] = tipoVehiculo.getInstancia(vehiculo.getMatricula(), vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getDatosTecnicos());
        } else {
            throw new ExcepcionAlquilerVehiculos("El array de vehículos está lleno.");
        }
    }*/
    public void anadir(Vehiculo vehiculo, TipoVehiculo tipoVehiculo) {
        if ( ! vehiculos.contains(vehiculo)) {
            vehiculos.add(tipoVehiculo.getInstancia(vehiculo.getMatricula(), vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getDatosTecnicos()));
        } else {
            throw new ExcepcionAlquilerVehiculos("Ese vehiculo ya existe");
        }
    }

    public LinkedList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    /*private int buscarPrimerIndiceLibreComprobandoExistencia(Vehiculo turismo) {
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

    private boolean indiceNoSuperaTamano(int posicion) {
        return posicion < vehiculos.length;
    }*/

    public void borrar(String matricula) {
        for(Vehiculo v: vehiculos) {
            if (v.getMatricula().equals(matricula)) {
                vehiculos.remove(v);
                return;
            } 
        }
        throw new ExcepcionAlquilerVehiculos("El vehículo a borrar no existe");
    }

    /*private int buscarIndiceVehiculo(String matricula) {
        int posicion = 0;
        boolean encontrado = false;
        while (posicion < vehiculos.length && !encontrado) {
            if (vehiculos[posicion] != null && vehiculos[posicion].getMatricula().equals(matricula)) {
                encontrado = true;
            } else {
                posicion++;
            }
        }
        if (encontrado) {
            return posicion;
        } else {
            return vehiculos.length;
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int posicion) {
        for (int i = posicion; i < vehiculos.length - 1; i++) {
            vehiculos[i] = vehiculos[i + 1];
        }
        vehiculos[vehiculos.length - 1] = null;
    }*/

    public Vehiculo buscar(String matricula) {
        for (Vehiculo v : vehiculos){
            if(v.getMatricula().equals(matricula))
                return v;
        }
        return null;
    }
}
