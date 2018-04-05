/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dominio.vehiculo;

import java.io.Serializable;
import java.util.Arrays;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 *
 * @author bogdan
 */
public enum TipoVehiculo implements Serializable {

    TURISMO("Vehiculo turismo") {
        public Vehiculo getInstancia(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicos) {
            return new Turismo(matricula, marca, modelo, datosTecnicos);
        }
    },
    DE_CARGA("Vehiculo de carga") {
        public Vehiculo getInstancia(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicos) {
            return new DeCarga(matricula, marca, modelo, datosTecnicos);
        }
    },
    AUTOBUS("Vehiculo autobus") {
        public Vehiculo getInstancia(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicos) {
            return new Autobus(matricula, marca, modelo, datosTecnicos);
        }
    };

    private String tipoVehiculo;

    private TipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public abstract Vehiculo getInstancia(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicos);

    public String toString() {
        return tipoVehiculo;
    }

    public static TipoVehiculo getTipoVehiculoSegunOrdinal(int ordinal) {
        if (esOrdinalValido(ordinal)) {
            return values()[ordinal];
        } else {
            throw new ExcepcionAlquilerVehiculos("Ordinal no valido.");
        }
    }

    public static boolean esOrdinalValido(int ordinal) {
        return (ordinal >= 0 && ordinal <= values().length - 1);
    }
}
