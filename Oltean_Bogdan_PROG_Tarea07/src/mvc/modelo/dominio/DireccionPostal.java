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
public class DireccionPostal implements Serializable {

    private String codigoPostal;
    private String calle;
    private String localidad;
    private static final long serialVersionUID = 1L;
    
    public DireccionPostal (DireccionPostal direccion) {
        setCalle (direccion.calle);
        setLocalidad (direccion.localidad);
        setCodigoPostal (direccion.codigoPostal);
    }

    public DireccionPostal(String codigoPostal, String calle, String localidad) {
        setCodigoPostal(codigoPostal);
        setCalle (calle);
        setLocalidad (localidad);
    }
    
    private void setCalle (String calle) {
        this.calle = calle;
    }
    
    private void setLocalidad (String localidad) {
        this.localidad = localidad;
    }
    
    private void setCodigoPostal (String codigoPostal) {
        if (compruebaCodigoPostal (codigoPostal) == false)
            throw new ExcepcionAlquilerVehiculos ("Error de codigo postal");
        this.codigoPostal = codigoPostal;
    }
    
    private boolean compruebaCodigoPostal(String codigoPostal) {
        Pattern comprueba = Pattern.compile("0[1-9][0-9]{3}|[1-4][0-9]{4}|5[0-2][0-9]{3}");
        Matcher matcher = comprueba.matcher(codigoPostal);
        return matcher.matches();
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getCalle() {
        return calle;
    }

    @Override
    public String toString() {
        return "DireccionPostal{" + "codigoPostal=" + codigoPostal + ", calle=" + calle + ", localidad=" + localidad + '}';
    }
    
    
}
