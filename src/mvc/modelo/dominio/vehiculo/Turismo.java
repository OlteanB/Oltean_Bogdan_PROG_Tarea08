/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dominio.vehiculo;

import java.io.Serializable;

/**
 *
 * @author bogdan
 */
public class Turismo extends Vehiculo implements Serializable {

    public Turismo(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicos) {
        super(matricula, marca, modelo, datosTecnicos);
    }
    
    public Turismo(Turismo turismo){
        super(turismo);
    }

    @Override
    public TipoVehiculo getTipoVehiculo() {
        return TipoVehiculo.TURISMO;
    }

    @Override
    public double getPrecioEspecifico() {
       double precioTurismo = getDatosTecnicos().getCilindrada()/50;
       return precioTurismo;
    }

}
