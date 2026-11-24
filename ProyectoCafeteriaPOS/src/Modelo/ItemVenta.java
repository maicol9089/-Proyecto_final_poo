/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Representa un ítem dentro de una venta.
 */
public class ItemVenta {
    private Producto producto;
    private double cantidad;
    private double subtotal;

    public ItemVenta(Producto producto, double cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.calcularPrecioVenta(cantidad);
    }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) {
        if (cantidad >= 0) {
            this.cantidad = cantidad;
            this.subtotal = this.producto.calcularPrecioVenta(cantidad);
        }
    }

    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return producto.getNombre() + " (Cant: " + cantidad + ") - Subtotal: $" + subtotal;
    }
}