/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Representa el medio de pago en efectivo.
 */
public class PagoEfectivo implements MedioPago {
    private double dineroRecibido;

    /**
     * Constructor para el pago en efectivo.
     * @param dineroRecibido El dinero entregado por el cliente.
     */
    public PagoEfectivo(double dineroRecibido) {
        this.dineroRecibido = dineroRecibido;
    }

    public double getDineroRecibido() {
        return dineroRecibido;
    }

    public void setDineroRecibido(double dineroRecibido) {
        if (dineroRecibido >= 0) {
            this.dineroRecibido = dineroRecibido;
        }
    }

    @Override
    public String procesarPago(double monto) {
        if (this.dineroRecibido < monto) {
            return "Pago rechazado: Dinero insuficiente.";
        }
        double cambio = this.dineroRecibido - monto;
        return "Efectivo recibido: $" + this.dineroRecibido + " | Cambio: $" + cambio;
    }
}
