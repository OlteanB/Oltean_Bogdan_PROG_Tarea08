/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.utilidades;

import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.DireccionPostal;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.DatosTecnicosVehiculo;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;
import mvc.vista.Opcion;

/**
 *
 * @author bogdan
 */
public class Consola {

    public static void mostrarMenu() {
        mostrarCabecera("Alquiler Vehiculos");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static void mostrarCabecera(String mensaje) {
        System.out.printf("%n%s%n", mensaje);
        System.out.println(String.format("%0" + mensaje.length() + "d%n", 0).replace("0", "-"));
    }

    public static int elegirOpcion() {
        int ordinalOpcion;
        do {
            System.out.print("\nElige una opción: ");
            ordinalOpcion = Entrada.entero();
        } while (ordinalOpcion < 0 || ordinalOpcion > 14);
        return ordinalOpcion;
    }

    public static String leerDni() {
        System.out.print("Introduce el DNI del cliente: ");
        String dniBorrar = Entrada.cadena();
        return dniBorrar;
    }

    public static String leerMatricula() {
        System.out.print("Introduce la matrícula del vehículo: ");
        String matriculaBorrar = Entrada.cadena();
        return matriculaBorrar;
    }

    public static Cliente leerCliente() {
        Cliente cliente = null;
        System.out.print("Nombre: ");
        String nombre = Entrada.cadena();
        System.out.print("DNI: ");
        String dni = Entrada.cadena();
        System.out.print("Dirección: ");
        String direccion = Entrada.cadena();
        System.out.print("Localidad: ");
        String localidad = Entrada.cadena();
        System.out.print("Código postal: ");
        String codigoPostal = Entrada.cadena();
        cliente = new Cliente(nombre, dni, new DireccionPostal(codigoPostal, direccion, localidad));
        return cliente;
    }

    public static Vehiculo leerVehiculo() {
        Vehiculo nuevoVehiculo = null;
        int ordinalVehiculo = 0;
        System.out.print("Matrícula: ");
        String matricula = Entrada.cadena();
        System.out.print("Marca: ");
        String marca = Entrada.cadena();
        System.out.print("Modelo: ");
        String modelo = Entrada.cadena();
        System.out.print("Cilindrada: ");
        int cilindrada = Entrada.entero();
        System.out.println("Numero de plazas: ");
        int numeroPlazas = Entrada.entero();
        System.out.println("PMA: ");
        int pma = Entrada.entero();
        try {
            DatosTecnicosVehiculo datosTecnicosEntrada = new DatosTecnicosVehiculo(cilindrada, numeroPlazas, pma);
            nuevoVehiculo = TipoVehiculo.getTipoVehiculoSegunOrdinal(ordinalVehiculo).getInstancia(matricula, marca, modelo, datosTecnicosEntrada);
        } catch (ExcepcionAlquilerVehiculos a) {
            System.out.printf("Error", a.getMessage());
        }
        return nuevoVehiculo;
    }

    public static int elegirTipoVehiculo() {
        int ordinalTipoVehiculo;
        do {
            System.out.printf("Elegir tipo vehiculo ", obtenerTiposVehiculo());
            ordinalTipoVehiculo = Entrada.entero();

        } while (!TipoVehiculo.esOrdinalValido(ordinalTipoVehiculo));
        return ordinalTipoVehiculo;
    }

    public static String obtenerTiposVehiculo() {
        StringBuilder tiposVehiculo = new StringBuilder();
        for (TipoVehiculo tipoVehiculo : TipoVehiculo.values()) {
            tiposVehiculo.append(tipoVehiculo.ordinal()).append(" ").append(tipoVehiculo).append(" ");
        }
        return tiposVehiculo.toString();
    }

}
