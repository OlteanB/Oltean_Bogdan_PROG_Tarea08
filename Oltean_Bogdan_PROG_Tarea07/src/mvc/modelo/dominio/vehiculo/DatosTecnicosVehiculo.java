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
public class DatosTecnicosVehiculo {

    private int cilindrada, numeroPlazas, pma;

    public DatosTecnicosVehiculo(DatosTecnicosVehiculo datosTecnicosVehiculo) {
        cilindrada = datosTecnicosVehiculo.getCilindrada();
        numeroPlazas = datosTecnicosVehiculo.getNumeroPlazas();
        pma = datosTecnicosVehiculo.getPma();
    }

    public DatosTecnicosVehiculo(int cilindrada, int numeroPlazas, int pma) {
        this.cilindrada = cilindrada;
        this.numeroPlazas = numeroPlazas;
        this.pma = pma;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    private void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getNumeroPlazas() {
        return numeroPlazas;
    }

    private void setNumeroPlazas(int numeroPlazas) {
        this.numeroPlazas = numeroPlazas;
    }

    public int getPma() {
        return pma;
    }

    private void setPma(int pma) {
        this.pma = pma;
    }

    @Override
    public String toString() {
        return "DatosTecnicosVehiculo{" + "cilindrada=" + cilindrada + ", numeroPlazas=" + numeroPlazas + ", pma=" + pma + '}';
    }

}
