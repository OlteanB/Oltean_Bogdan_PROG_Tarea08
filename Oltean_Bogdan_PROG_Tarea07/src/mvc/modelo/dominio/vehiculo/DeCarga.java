/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dominio.vehiculo;

/**
 *
 * @author bogdan
 */
public class DeCarga extends Vehiculo {

    public DeCarga(DeCarga deCarga) {
        super(deCarga);
    }

    public DeCarga(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicos) {
        super(matricula, marca, modelo, datosTecnicos);
    }

    @Override
    public TipoVehiculo getTipoVehiculo() {
        return TipoVehiculo.DE_CARGA;
    }

    @Override
    public double getPrecioEspecifico() {
        double precioCarga = getDatosTecnicos().getPma() / 20 + 1 * getDatosTecnicos().getNumeroPlazas();
        return precioCarga;
    }

}
