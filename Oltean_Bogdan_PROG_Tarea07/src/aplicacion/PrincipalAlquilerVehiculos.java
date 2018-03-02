package aplicacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import mvc.controlador.ControladorAlquilerVehiculos;
import mvc.controlador.IControladorAlquilerVehiculos;
import mvc.modelo.AlquilerVehiculo;
import mvc.modelo.IModeloAlquilerVehiculo;
import mvc.vista.IUTextual;
import mvc.vista.IVistaAlquilerVehiculos;

/**
 *
 * @author bogdan
 */
public class PrincipalAlquilerVehiculos {

    public static void main(String[] args) {
        IModeloAlquilerVehiculo modelo = new AlquilerVehiculo();
        IVistaAlquilerVehiculos vista = new IUTextual();
        IControladorAlquilerVehiculos controlador = new ControladorAlquilerVehiculos(vista, modelo);

        controlador.comenzar();
    }
}
