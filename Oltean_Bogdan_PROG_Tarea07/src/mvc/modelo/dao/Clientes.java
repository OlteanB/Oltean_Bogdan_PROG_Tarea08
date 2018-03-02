/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dao;

import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 *
 * @author bogdan
 */
public class Clientes {

    private final int MAX_CLIENTES = 20;

    private Cliente[] clientes;

    public Clientes() {
        clientes = new Cliente[MAX_CLIENTES];
    }

    public Cliente[] getClientes() {
        return clientes.clone();
    }

    public void anadir(Cliente cliente) {
        int posicion = buscarPrimerIndiceComprobandoExistencia(cliente);
        if (indiceNoSuperaTamano(posicion)) {
            clientes[posicion] = cliente;
        } else {
            throw new ExcepcionAlquilerVehiculos("El array de clientes está lleno.");
        }
    }

    private int buscarPrimerIndiceComprobandoExistencia(Cliente cliente) {
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
    }

    public void borrar(String dni) {
        int posicion = buscarIndiceCliente(dni);
        boolean encontrado = false;
        if (indiceNoSuperaTamano(posicion)) {
            desplazarUnaPosicionHaciIzquierda(posicion);
        } else {
            throw new ExcepcionAlquilerVehiculos("El cliente introducido no existe");
        }
    }

    private int buscarIndiceCliente(String dni) {
        int posicion = 0;
        boolean encontrado = false;
        while (posicion < clientes.length && !encontrado) {
            if (clientes[posicion] != null && clientes[posicion].getDNI().equals(dni)) {
                encontrado = true;
            } else {
                posicion++;
            }
        }
        if (encontrado) {
            return posicion;
        } else {
            return clientes.length;
        }

    }

    private void desplazarUnaPosicionHaciIzquierda(int posicion) {
        for (int i = posicion; i < clientes.length - 1; i++) {
            clientes[i] = clientes[i + 1];
        }
        if (posicion == clientes.length - 1) {
            clientes[clientes.length - 1] = null;
        }
    }

    public Cliente buscar(String dni) {
        int posicion = buscarIndiceCliente(dni);
        if (indiceNoSuperaTamano(posicion)) {
            return new Cliente(clientes[posicion]);
        } else {
            return null;
        }
    }
}
