/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista;

import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 *
 * @author bogdan
 */
public enum Opcion {
    SALIR("Salir") {
        public void ejecutar() {
            vista.salir();
        }
    },
    ANADIR_CLIENTE("Añadir cliente") {
        public void ejecutar() {
            vista.anadirCliente();
        }
    },
    BORRAR_CLIENTE("Borrar cliente") {
        public void ejecutar() {
            vista.borrarCliente();
        }
    },
    BUSCAR_CLIENTE("Buscar cliente") {
        public void ejecutar() {
            vista.buscarCliente();
        }
    },
    LISTAR_CLIENTES("Listar clientes") {
        public void ejecutar() {
            vista.listarClientes();
        }
    },
    ANADIR_VEHICULO("Añadir vehiculo") {
        public void ejecutar() {
            vista.anadirVehiculo();
        }
    },
    BORRAR_VEHICULO("Borrar vehiculo") {
        public void ejecutar() {
            vista.borrarVehiculo();
        }
    },
    BUSCAR_VEHICULO("Buscar vehiculo") {
        public void ejecutar() {
            vista.buscarVehiculo();
        }
    },
    LISTAR_VEHICULOS("Listar vehiculoS") {
        public void ejecutar() {
            vista.listarVehiculo();
        }
    },
    ABRIR_ALQUILER("Abrir alquiler") {
        public void ejecutar() {
            vista.abrirAlquiler();
        }
    },
    CERRAR_ALQUILER("Cerrar alquiler") {
        public void ejecutar() {
            vista.cerrarAlquiler();
        }
    },
    LISTAR_ALQUILERES("Listar alquileres") {
        public void ejecutar() {
            vista.listarAlquileres();
        }
    };

    private String mensaje;
    private static IUTextual vista;

    private Opcion(String mensaje) {
        this.mensaje = mensaje;
    }

    public abstract void ejecutar();

    public String getMensaje() {
        return mensaje;
    }

    public static void setVista(IUTextual vista) {
        Opcion.vista = vista;
    }

    public String toString() {
        return String.format("%d.- %s", ordinal(), mensaje);
    }

    public static Opcion getOpcionSegunOridnal(int ordinal) {
        if (esOrdinalValido(ordinal)) {
            return values()[ordinal];
        } else {
            throw new ExcepcionAlquilerVehiculos("Ordinal de la opción no válido");
        }
    }

    public static boolean esOrdinalValido(int ordinal) {
        return (ordinal >= 0 && ordinal <= values().length - 1) ? true : false;
    }
}
