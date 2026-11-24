/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Clase base abstracta para los productos de la cafetería.
 */
public abstract class Producto {
    private String codigo;
    private String nombre;
    private double precioBase;
    private int existencias;

    /**
     * Constructor de la clase Producto.
     * @param codigo código único del producto
     * @param nombre nombre del producto
     * @param precioBase precio base
     * @param existencias cantidad disponible en inventario
     */
    public Producto(String codigo, String nombre, double precioBase, int existencias) {
        this.codigo = codigo;
        this.nombre = nombre;
        setPrecioBase(precioBase);
        setExistencias(existencias);
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) {
        if (precioBase >= 0) this.precioBase = precioBase;
    }

    public int getExistencias() { return existencias; }
    public void setExistencias(int existencias) {
        if (existencias >= 0) this.existencias = existencias;
    }

    /**
     * Método abstracto para calcular el precio de venta según el tipo de producto.
     */
    public abstract double calcularPrecioVenta(double cantidad);

    @Override
    public String toString() {
        return "Código: " + codigo + " | Nombre: " + nombre + " | Precio Base: $" + precioBase + " | Existencias: " + existencias;
    }
}