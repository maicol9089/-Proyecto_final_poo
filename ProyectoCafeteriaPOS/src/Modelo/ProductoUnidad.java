/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Producto vendido por unidades enteras (ej. gaseosas, paquetes).
 */
public class ProductoUnidad extends Producto {
    private boolean aplicaDescuentoFiel;

    public ProductoUnidad(String codigo, String nombre, double precioBase, int existencias, boolean aplicaDescuentoFiel) {
        super(codigo, nombre, precioBase, existencias);
        this.aplicaDescuentoFiel = aplicaDescuentoFiel;
    }

    public boolean isAplicaDescuentoFiel() { return aplicaDescuentoFiel; }
    public void setAplicaDescuentoFiel(boolean aplicaDescuentoFiel) { this.aplicaDescuentoFiel = aplicaDescuentoFiel; }

    @Override
    public double calcularPrecioVenta(double cantidad) {
        double subtotal = getPrecioBase() * cantidad;
        if (aplicaDescuentoFiel && cantidad >= 3) {
            subtotal = subtotal * 0.90;
        }
        return subtotal;
    }
}