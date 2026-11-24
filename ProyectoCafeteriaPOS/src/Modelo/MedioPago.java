/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Interfaz que define el contrato para los diferentes medios de pago en la cafetería.
 */
public interface MedioPago {
    /**
     * Procesa el pago del valor total de la venta.
     * @param monto El valor total a pagar.
     * @return Una cadena con el comprobante o detalle del pago.
     */
    String procesarPago(double monto);
}
