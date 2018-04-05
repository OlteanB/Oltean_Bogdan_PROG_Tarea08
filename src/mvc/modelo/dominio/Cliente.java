/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.modelo.dominio;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bogdan
 */
public class Cliente implements Serializable{

    private String nombre, dni;
    private int identificador, numClientes;
    private DireccionPostal direccionPostal;
    private static final long serialVersionUID=1L;
    private static int ultimoIdentificador=0;

    public Cliente(String nombre, String DNI, DireccionPostal direccionPostal) {
        this.nombre = new String(nombre);
        //DNI
        setDni (DNI);
        //direccion postal
        setDireccionPostal(direccionPostal);
        asignarNuevoIdentificador();

    }
    
    private void asignarNuevoIdentificador(){
        ultimoIdentificador++;
        //aumentarUltimoIdentificador (1);
        identificador=ultimoIdentificador;
    }
    
    public static void aumentarUltimoIdentificador(int cantidad){
        if (cantidad>=0) {
            ultimoIdentificador+=cantidad;
        }else{
            throw new ExcepcionAlquilerVehiculos("Error al aumentar el identificador");
        }
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void setDni(String dni) {
        if (compruebaDni(dni)) {
            this.dni = new String(dni);
        } else {
            throw new ExcepcionAlquilerVehiculos("Error DNI.");
        }
    }

    private void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    
    public void setDireccionPostal(DireccionPostal direccionPostal) {
        this.direccionPostal = new DireccionPostal(direccionPostal);
    }

    public Cliente(Cliente cliente) {
        this.nombre = cliente.nombre;
        this.dni = cliente.dni;
        this.direccionPostal = cliente.direccionPostal;
        this.identificador=cliente.getIdentificador();
    }

    private boolean compruebaDni(String DNI) {
        Pattern comprueba = Pattern.compile("\\d{8}[A-HJ-NP-TV-Z]");
        Matcher matcher = comprueba.matcher(DNI);
        return matcher.matches();
    }

    public DireccionPostal getDireccionPostal() {
        return direccionPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDNI() {
        return dni;
    }

    public int getIdentificador() {
        return identificador;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", dni=" + dni + ", identificador=" + identificador + ", direccionPostal=" + direccionPostal + '}';
    }


    public boolean equals (Object obj) {
        Cliente cli = (Cliente) obj;
        return this.identificador == cli.identificador;
    }
}
