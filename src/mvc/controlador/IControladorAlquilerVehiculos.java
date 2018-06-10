/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controlador;

import java.util.LinkedList;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;

/**
 *
 * @author bogdan
 */
public interface IControladorAlquilerVehiculos {
    void salir();

    void abrirAlquiler(Cliente cliente, Vehiculo vehiculo);

    void anadirCliente(Cliente cliente);

    //void anadirDatosPrueba();

    void anadirVehiculo(Vehiculo vehiculo, TipoVehiculo tipoVehiculo);

    void borrarCliente(String dni);

    void borrarVehiculo(String matricula);

    Cliente buscarCliente(String dni);

    Vehiculo buscarVehiculo(String matricula);

    void cerrarAlquiler(Cliente cliente, Vehiculo vehiculo);

    void comenzar();

    LinkedList<Alquiler> obtenerAlquiler();

    LinkedList<Cliente> obtenerClientes();

    LinkedList<Vehiculo> obtenerVehiculos();

    LinkedList<Alquiler> obtenerAlquileresAbiertos();
    
    LinkedList<Alquiler> obtenerAlquileresCliente(String dni);
    
    LinkedList<Alquiler> obtenerAlquileresVehiculo(String maritucla);
}
