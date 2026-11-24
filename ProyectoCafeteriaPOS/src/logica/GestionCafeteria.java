/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import modelo.*;
import java.util.ArrayList;

/**
 * Administra el inventario, catálogo, clientes y transacciones.
 */
public class GestionCafeteria {
    private ArrayList<Producto> listaProductos;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Venta> listaVentas;
    private final int STOCK_MINIMO = 5;

    public GestionCafeteria() {
        this.listaProductos = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
        this.listaVentas = new ArrayList<>();
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        listaProductos.add(new ProductoUnidad("P001", "Café Americano", 4500.0, 20, true));
        listaProductos.add(new ProductoUnidad("P002", "Gaseosa 400ml", 3500.0, 15, false));
        listaProductos.add(new ProductoUnidad("P003", "Galletas de Soda", 2500.0, 4, true)); // Stock bajo
        listaProductos.add(new ProductoPeso("P004", "Café en Grano (gramos)", 50.0, 300, 1000.0));
        listaProductos.add(new ProductoPeso("P005", "Torta de Chocolate (gramos)", 60.0, 3, 500.0)); // Stock bajo

        listaClientes.add(new Cliente("1001234567", "Ana María Gómez", "3101234567"));
        listaClientes.add(new Cliente("1009876543", "Carlos Pérez", "3209876543"));
    }

    public boolean registrarProducto(Producto producto) {
        if (buscarProductoPorCodigo(producto.getCodigo()) != null) return false;
        listaProductos.add(producto);
        return true;
    }

    /**
     * Busca producto por código (Retorna null si no lo encuentra - RT4).
     */
    public Producto buscarProductoPorCodigo(String codigo) {
        for (Producto p : listaProductos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) return p;
        }
        return null;
    }

    public ArrayList<Producto> getListaProductos() { return listaProductos; }

    public boolean registrarCliente(Cliente cliente) {
        if (buscarClientePorDocumento(cliente.getDocumento()) != null) return false;
        listaClientes.add(cliente);
        return true;
    }

    /**
     * Busca cliente por documento (Retorna null si no lo encuentra - RT4)[cite: 2].
     */
    public Cliente buscarClientePorDocumento(String documento) {
        for (Cliente c : listaClientes) {
            if (c.getDocumento().equalsIgnoreCase(documento)) return c;
        }
        return null;
    }

    public ArrayList<Cliente> getListaClientes() { return listaClientes; }

    public boolean registrarVenta(Venta venta) {
        for (ItemVenta item : venta.getItems()) {
            if (item.getCantidad() > item.getProducto().getExistencias()) {
                return false;
            }
        }
        for (ItemVenta item : venta.getItems()) {
            int nuevaStock = (int)(item.getProducto().getExistencias() - item.getCantidad());
            item.getProducto().setExistencias(nuevaStock);
        }
        listaVentas.add(venta);
        return true;
    }

    public ArrayList<Venta> getListaVentas() { return listaVentas; }

    public ArrayList<Producto> obtenerProductosStockBajo() {
        ArrayList<Producto> stockBajo = new ArrayList<>();
        for (Producto p : listaProductos) {
            if (p.getExistencias() < STOCK_MINIMO) stockBajo.add(p);
        }
        return stockBajo;
    }
}