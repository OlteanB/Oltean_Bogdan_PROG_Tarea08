/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo;

import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author bogdan
 */
public interface IModeloAlquilerVehiculo {

    void leerVehiculos();

    void leerClientes();

    void leerAlquileres();

    void escribirVehiculos();

    void escribirClientes();

    void escribirAlquileres();

    void anadirCliente(Cliente cliente);

    void borrarCliente(String dni);

    Cliente buscarCliente(String dni);

    Cliente[] obtenerClientes();

    void anadirVehiculo(Vehiculo vehiculo, TipoVehiculo tipoVehiculo);

    void borrarVehiculo(String matricula);

    Vehiculo buscarVehiculo(String matricula);

    Vehiculo[] obtenerVehiculo();

    void abrirAlquiler(Cliente cliente, Vehiculo vehiculo);

    void cerrarAlquiler(Cliente cliente, Vehiculo vehiculo);

    Alquiler[] obtenerAlquiler();

    //void anadirDatosPrueba();
}
