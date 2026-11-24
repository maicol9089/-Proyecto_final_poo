/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 * Representa el comprobante de venta.
 */
public class Venta {
    public static final double IVA = 0.19;
    private static int contadorConsecutivo = 1;

    private int numeroRecibo;
    private Cliente cliente;
    private ArrayList<ItemVenta> items;
    private double subtotalVenta;
    private double valorIva;
    private double totalVenta;
    private MedioPago medioPago;

    public Venta(Cliente cliente, MedioPago medioPago) {
        this.numeroRecibo = contadorConsecutivo++;
        this.cliente = cliente;
        this.medioPago = medioPago;
        this.items = new ArrayList<>();
        this.subtotalVenta = 0.0;
        this.valorIva = 0.0;
        this.totalVenta = 0.0;
    }

    public void agregarItem(ItemVenta item) {
        this.items.add(item);
        recalcularTotales();
    }

    private void recalcularTotales() {
        this.subtotalVenta = 0.0;
        for (ItemVenta item : items) {
            this.subtotalVenta += item.getSubtotal();
        }
        this.valorIva = this.subtotalVenta * IVA;
        this.totalVenta = this.subtotalVenta + this.valorIva;
    }

    public int getNumeroRecibo() { return numeroRecibo; }
    public Cliente getCliente() { return cliente; }
    public ArrayList<ItemVenta> getItems() { return items; }
    public double getSubtotalVenta() { return subtotalVenta; }
    public double getValorIva() { return valorIva; }
    public double getTotalVenta() { return totalVenta; }
    public MedioPago getMedioPago() { return medioPago; }

    public String generarComprobante() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("          CAFETERÍA DELICIAS UAM        \n");
        sb.append("         COMPROBANTE DE VENTA           \n");
        sb.append("========================================\n");
        sb.append("Recibo N°: ").append(numeroRecibo).append("\n");
        if (cliente != null) {
            sb.append("Cliente: ").append(cliente.getNombre()).append(" (Doc: ").append(cliente.getDocumento()).append(")\n");
        } else {
            sb.append("Cliente: General\n");
        }
        sb.append("----------------------------------------\n");
        sb.append("ÍTEMS:\n");
        for (ItemVenta item : items) {
            sb.append(" - ").append(item.getProducto().getNombre())
              .append(" x ").append(item.getCantidad())
              .append(" | Subtotal: $").append(item.getSubtotal()).append("\n");
        }
        sb.append("----------------------------------------\n");
        sb.append("Subtotal: $").append(subtotalVenta).append("\n");
        sb.append("IVA (19%): $").append(valorIva).append("\n");
        sb.append("TOTAL A PAGAR: $").append(totalVenta).append("\n");
        sb.append("----------------------------------------\n");
        sb.append("Resultado: ").append(medioPago.procesarPago(totalVenta)).append("\n");
        sb.append("========================================\n");
        return sb.toString();
    }
}