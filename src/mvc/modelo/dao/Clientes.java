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
import java.util.LinkedList;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 *
 * @author bogdan
 */
public class Clientes {

    //private final int MAX_CLIENTES = 20;

    //private Cliente[] clientes;
    private LinkedList <Cliente> clientes;

    private final String FICHERO_CLIENTES = "datos" + File.separator + "clientes.dat";

    public Clientes() {
        //clientes = new Cliente[MAX_CLIENTES];
        clientes = new LinkedList<Cliente>();
    }

    /*public Cliente[] getClientes() {
        return clientes.clone();
    }*/
    public LinkedList<Cliente> getClientes() {
        return (LinkedList<Cliente>) clientes.clone();
    }

    public void leerClientes() {
        File fichero = new File(FICHERO_CLIENTES);
        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(new FileInputStream(fichero));
            try {
                clientes = (LinkedList<Cliente>) entrada.readObject();
                entrada.close();
                System.out.println("Fichero clientes leído satisfactoriamente.");
                Cliente.aumentarUltimoIdentificador(calcularUltimoIdentificador());
            } catch (ClassNotFoundException e) {
                System.out.println("No puedo encontrar la clase que tengo que leer.");
            } catch (IOException e) {
                System.out.println("Error inesperado de Entrada/Salida.");
            }
        } catch (IOException e) {
            System.out.println("No puedo abrir el fihero de clientes.");
        }
    }

    private int calcularUltimoIdentificador() {
        int ultimoIdentificador = 0;
        for (Cliente cli : clientes) {
            if (cli.getIdentificador() > ultimoIdentificador) {
                ultimoIdentificador = cli.getIdentificador();
            }
        }
        return ultimoIdentificador;
    }

    public void escribirClientes() {
        File fichero = new File(FICHERO_CLIENTES);
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
            salida.writeObject(clientes);
            salida.close();
            System.out.println("Fichero clientes escrito satisfactoriamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo crear el fichero de clientes");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida");
        }
    }

    public void anadir(Cliente cliente) {
        if ( ! clientes.contains(cliente)) {
            clientes.add(cliente);
        } else {
            throw new ExcepcionAlquilerVehiculos("Ese cliente ya existe");
        }
    }

   /* private int buscarPrimerIndiceComprobandoExistencia(Cliente cliente) {
        int posicion = 0;
        boolean posicionEncontrada = false;
        while (posicion < clientes.length && !posicionEncontrada) {
            if (clientes[posicion] == null) {
                posicionEncontrada = true;
            } else if (clientes[posicion].getDNI().equals(cliente.getDNI())) {
                throw new ExcepcionAlquilerVehiculos("Ya existe un cliente con ese DNI");
            } else {
                posicion++;
            }
        }
        return posicion;
    }

    private boolean indiceNoSuperaTamano(int posicion) {
        if (posicion >= clientes.length) {
            return false;
        } else {
            return true;
        }
    }*/

    public void borrar(String dni) {
        for (Cliente cli : clientes) {
            if (cli.getDNI().equals(dni)) {
                clientes.remove(cli);
                return;
            }
        }
        throw new ExcepcionAlquilerVehiculos("El cliente introducido no existe");
    }

    /*private int buscarIndiceCliente(String dni) {
        int posicion = 0;
        for (Cliente cli : clientes) {
            if (cli.getDNI().equals (dni))
                return posicion;
            else
                posicion++;
        }
        return -1;

    }

    private void desplazarUnaPosicionHaciIzquierda(int posicion) {
        for (int i = posicion; i < clientes.length - 1; i++) {
            clientes[i] = clientes[i + 1];
        }
        if (posicion == clientes.length - 1) {
            clientes[clientes.length - 1] = null;
        }
    }*/

    public Cliente buscar(String dni) {
        for (Cliente cli : clientes) {
            if (cli.getDNI().equals (dni))
                return cli;
        }
        return null;
    }
}
