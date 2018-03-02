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
public class Autobus extends Vehiculo {

    public Autobus(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicos) {
        super(matricula, marca, modelo, datosTecnicos);
    }

    public Autobus(Autobus autobus) {
        super(autobus);
    }

    @Override
    public TipoVehiculo getTipoVehiculo() {
        return TipoVehiculo.AUTOBUS;
    }

    @Override
    public double getPrecioEspecifico() {
        double precioAutobus = getDatosTecnicos().getCilindrada() / 50 + 1 * getDatosTecnicos().getNumeroPlazas();
        return precioAutobus;
    }
}
