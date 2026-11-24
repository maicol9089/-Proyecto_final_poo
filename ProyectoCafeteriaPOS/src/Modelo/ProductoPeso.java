/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Producto vendido por peso o porción (ej. café en gramos, pastelería).
 */
public class ProductoPeso extends Producto {
    private double costoEmpaque;

    public ProductoPeso(String codigo, String nombre, double precioBase, int existencias, double costoEmpaque) {
        super(codigo, nombre, precioBase, existencias);
        setCostoEmpaque(costoEmpaque);
    }

    public double getCostoEmpaque() { return costoEmpaque; }
    public void setCostoEmpaque(double costoEmpaque) {
        if (costoEmpaque >= 0) this.costoEmpaque = costoEmpaque;
    }

    @Override
    public double calcularPrecioVenta(double cantidad) {
        return (getPrecioBase() * cantidad) + costoEmpaque;
    }
}