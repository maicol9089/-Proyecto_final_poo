/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Representa el medio de pago con tarjeta de crédito/débito.
 */
public class PagoTarjeta implements MedioPago {
    private String numeroTarjeta;
    private String banco;

    /**
     * Constructor para pago con tarjeta.
     * @param numeroTarjeta número de la tarjeta
     * @param banco entidad bancaria
     */
    public PagoTarjeta(String numeroTarjeta, String banco) {
        this.numeroTarjeta = numeroTarjeta;
        this.banco = banco;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    @Override
    public String procesarPago(double monto) {
        return "Pago con Tarjeta aprobado (" + this.banco + ") por valor de $" + monto;
    }
}
