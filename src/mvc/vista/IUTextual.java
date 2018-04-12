/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista;

import mvc.controlador.IControladorAlquilerVehiculos;
import mvc.modelo.dao.Alquileres;
import mvc.modelo.dao.Clientes;
import mvc.modelo.dao.Vechiculos;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.DireccionPostal;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.vista.utilidades.Consola;

/**
 *
 * @author bogdan
 */
public class IUTextual implements IVistaAlquilerVehiculos {

    IControladorAlquilerVehiculos controlador;

    public IUTextual() {
        Opcion.setVista(this);
    }

    @Override
    public void setControlador(IControladorAlquilerVehiculos controlador) {
        this.controlador = controlador;
    }

    @Override
    public void comenzar() {
        int ordinalOpcion;
        do {
            Consola.mostrarMenu();
            ordinalOpcion = Consola.elegirOpcion();
            Opcion opcion = Opcion.getOpcionSegunOridnal(ordinalOpcion);
            opcion.ejecutar();
        } while (ordinalOpcion != Opcion.SALIR.ordinal());
    }

    @Override
    public void salir() {
        System.out.println("FIN");
    }

    @Override
    public void anadirCliente() {
        Consola.mostrarCabecera("Añadir cliente");
        try {
            Cliente cliente = Consola.leerCliente();
            controlador.anadirCliente(cliente);
            System.out.println("Cliente añadido satisfactoriamente\n");
        } catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("ERROR: %s%n%n", e.getMessage());
        }
    }

    @Override
    public void borrarCliente() {
        Consola.mostrarCabecera("Borrar cliente");
        String dni = Consola.leerDni();
        try {
            controlador.borrarCliente(dni);
            System.out.println("Cliente borrado satisfactoriamente\n");
        } catch (Exception e) {
            System.out.printf("ERROR: %s%n%n", e.getMessage());
        }
    }

    @Override
    public void buscarCliente() {
        Consola.mostrarCabecera("Buscar cliente");
        String dni = Consola.leerDni();
        Cliente cliente = controlador.buscarCliente(dni);
        String mensaje = (cliente != null) ? cliente.toString() : "El cliente no existe";
        System.out.printf("%s%n%n", mensaje);
    }

    @Override
    public void listarClientes() {
        Consola.mostrarCabecera("Listar clientes");
        for (Cliente cliente : controlador.obtenerClientes()) {
            if (cliente != null) {
                System.out.println(cliente);
            }
        }
        System.out.println("");
    }

    @Override
    public void anadirVehiculo() {
        Consola.mostrarCabecera("Añadir Vehiculo");
        try {
            Vehiculo vehiculo = Consola.leerVehiculo();
            int tipoVehiculo = Consola.elegirTipoVehiculo();
            controlador.anadirVehiculo(vehiculo, TipoVehiculo.getTipoVehiculoSegunOrdinal(tipoVehiculo));
            System.out.println("Vehiculo añadido satisfactoriamente\n");
        } catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("ERROR: %s%n%n", e.getMessage());
        }
    }

    @Override
    public void borrarVehiculo() {
        Consola.mostrarCabecera("Borrar vehiculo");
        String matricula = Consola.leerMatricula();
        try {
            controlador.borrarVehiculo(matricula);
            System.out.println("Vehiculo borrado satisfactoriamente\n");
        } catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("ERROR: %s%n%n", e.getMessage());
        }
    }

    @Override
    public void buscarVehiculo() {
        Consola.mostrarCabecera("Buscar vehiculo");
        String matricula = Consola.leerMatricula();
        Vehiculo vehiculoBuscado = controlador.buscarVehiculo(matricula);
        String mensaje = (vehiculoBuscado != null) ? vehiculoBuscado.toString() : "El turismo no existe";
        System.out.printf("%s%n%n", mensaje);
    }

    @Override
    public void listarVehiculo() {
        Consola.mostrarCabecera("Listar vehiculos");
        for (Vehiculo vehiculo : controlador.obtenerVehiculos()) {
            if (vehiculo != null) {
                System.out.println(vehiculo);
            }
        }
        System.out.println("");
    }

    @Override
    public void abrirAlquiler() {
        Consola.mostrarCabecera("Abrir alquiler");
        String matricula = Consola.leerMatricula();
        String dni = Consola.leerDni();
        Vehiculo vehiculo = controlador.buscarVehiculo(matricula);
        Cliente cliente = controlador.buscarCliente(dni);
        if (vehiculo == null || cliente == null) {
            System.out.println("Matricula o DNI erroneo\n");
        } else {
            try {
                controlador.abrirAlquiler(cliente, vehiculo);
                System.out.println("Alquiler abierto satisfactoriamente\n");
            } catch (ExcepcionAlquilerVehiculos e) {
                System.out.printf("ERROR: %s%n%n", e.getMessage());
            }
        }
    }

    @Override
    public void cerrarAlquiler() {
        Consola.mostrarCabecera("Cerrar trabajo");
        String matricula = Consola.leerMatricula();
        String dni = Consola.leerDni();
        Cliente cliente = controlador.buscarCliente(dni);
        Vehiculo vehiculo = controlador.buscarVehiculo(matricula);
        if (vehiculo == null || cliente == null) {
            System.out.println("Matricula o DNI erroneo\n");
        } else {
            try {
                controlador.cerrarAlquiler(cliente, vehiculo);
                System.out.println("Alquiler cerrado satisfactoriamente\n");
            } catch (ExcepcionAlquilerVehiculos e) {
                System.out.printf("ERROR: %s%n%n", e.getMessage());
            }
        }
    }

    @Override
    public void listarAlquileres() {
        Consola.mostrarCabecera("Listar trabajos");
        for (Alquiler alquiler : controlador.obtenerAlquiler()) {
            if (alquiler != null) {
                System.out.println(alquiler);
            }
        }
        System.out.println("");
    }
    
    @Override
    public void obtenerAlquileresAbiertos(){
        Consola.mostrarCabecera("Listado de alquileres abiertos");
        for (Alquiler alquiler : controlador.obtenerAlquiler()) {
            if (alquiler != null && alquiler.getDias() == 0)
                System.out.println(alquiler);
        }
    }

    @Override
    public void obtenerAlquileresCliente(){
        String dni = Consola.leerDni();
        Consola.mostrarCabecera("Listado de alquileres para ese cliente");
        for (Alquiler alquiler : controlador.obtenerAlquiler()) {
            if (alquiler != null && alquiler.getCliente().getDNI().equals(dni))
                System.out.println(alquiler);
        }
    }
    
    @Override
    public void obtenerAlquileresVehiculo(){
        
        String matricula = Consola.leerMatricula();
        Consola.mostrarCabecera("Listado de alquileres para ese vehiculo");
        for (Alquiler alquiler : controlador.obtenerAlquiler()) {
            if (alquiler != null && alquiler.getVehiculo().getMatricula().equals(matricula))
                System.out.println(alquiler);
        }
    }
}
