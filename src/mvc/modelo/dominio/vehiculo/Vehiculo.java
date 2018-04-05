/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dominio.vehiculo;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;

/**
 *
 * @author bogdan
 */
public abstract class Vehiculo implements Serializable {
    private DatosTecnicosVehiculo datosTecnicos;

    private String matricula, marca, modelo;
    private boolean disponible;
    
    public final double FACTOR_CILINDRADA=0.0;
    public final double FACTOR_NUMERO_PLAZAS=0.0;
    public final double FACTOR_PMA=0.0;
    
    private static final long serialVersionUID = 1L;
    
    
    public abstract TipoVehiculo getTipoVehiculo();
    public abstract double getPrecioEspecifico();

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Vehiculo(String matricula, String marca, String modelo, DatosTecnicosVehiculo datosTecnicosVehiculo) {
        if (compruebaMatricula(matricula)) {
            this.matricula = matricula;
        } else {
            throw new ExcepcionAlquilerVehiculos("Error matricula.");
        }
        this.marca = marca;
        this.modelo = modelo;
        this.datosTecnicos=datosTecnicosVehiculo;
    }

    public Vehiculo(Vehiculo vehiculo) {
        this.matricula = vehiculo.matricula;
        this.marca = vehiculo.marca;
        this.modelo = vehiculo.modelo;
        this.datosTecnicos=vehiculo.getDatosTecnicos();
    }

    private boolean compruebaMatricula(String matricula) {
        Pattern comprueba = Pattern.compile("^\\d{4}[A-Z]{3}");
        Matcher matcher = comprueba.matcher(matricula);
        return matcher.matches();
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public DatosTecnicosVehiculo getDatosTecnicos() {
        return datosTecnicos;
    }

    

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", disponible=" + disponible + ", datosTecnicosVehiculo=" + datosTecnicos + '}';
    }

    public boolean equals (Object obj) {
        Vehiculo v = (Vehiculo) obj;
        return this.matricula.equals(v.matricula);
    }

}
