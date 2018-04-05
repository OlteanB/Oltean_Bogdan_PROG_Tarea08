/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista;

import mvc.controlador.IControladorAlquilerVehiculos;

/**
 *
 * @author bogdan
 */
public interface IVistaAlquilerVehiculos {

    void setControlador(IControladorAlquilerVehiculos controlador);

    void comenzar();

    void abrirAlquiler();

    void anadirCliente();

    void anadirVehiculo();

    void borrarCliente();

    void borrarVehiculo();

    void buscarCliente();

    void buscarVehiculo();

    void cerrarAlquiler();

    void listarAlquileres();

    void listarClientes();

    void listarVehiculo();

    void salir();
}
