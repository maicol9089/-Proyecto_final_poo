/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejecucion;

import logica.GestionCafeteria;
import modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Menú principal de consola para operar el POS de la cafetería.
 */
public class Principal {
    public static void main(String[] args) {
        GestionCafeteria gestion = new GestionCafeteria();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n========================================");
            System.out.println("      SISTEMA POS - CAFETERÍA UAM       ");
            System.out.println("========================================");
            System.out.println("1. Gestionar Productos");
            System.out.println("2. Control de Inventario (Stock bajo)");
            System.out.println("3. Gestionar Clientes");
            System.out.println("4. Registrar Venta");
            System.out.println("5. Mostrar Reporte de Ventas");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("¡Error! Ingrese un número válido.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    menuProductos(gestion, scanner);
                    break;
                case 2:
                    menuInventario(gestion);
                    break;
                case 3:
                    menuClientes(gestion, scanner);
                    break;
                case 4:
                    realizarVenta(gestion, scanner);
                    break;
                case 5:
                    mostrarReporteVentas(gestion);
                    break;
                case 6:
                    System.out.println("Saliendo del sistema POS...");
                    break;
                default:
                    System.out.println("Opción inexistente. Intente nuevamente.");
            }
        } while (opcion != 6);

        scanner.close();
    }

    private static void menuProductos(GestionCafeteria gestion, Scanner scanner) {
        System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
        System.out.println("1. Listar");
        System.out.println("2. Registrar nuevo");
        System.out.println("3. Buscar por código");
        System.out.println("4. Actualizar precio y existencias");
        System.out.print("Elija subopción: ");
        int sub = scanner.nextInt();
        scanner.nextLine();

        switch (sub) {
            case 1:
                for (Producto p : gestion.getListaProductos()) System.out.println(p);
                break;
            case 2:
                System.out.print("Tipo (1. Unidad, 2. Peso): ");
                int tipo = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Código: ");
                String cod = scanner.nextLine();
                System.out.print("Nombre: ");
                String nom = scanner.nextLine();
                System.out.print("Precio base: ");
                double precio = scanner.nextDouble();
                System.out.print("Existencias: ");
                int stock = scanner.nextInt();
                scanner.nextLine();

                if (tipo == 1) {
                    System.out.print("¿Aplica descuento por volumen? (true/false): ");
                    boolean desc = scanner.nextBoolean();
                    if (gestion.registrarProducto(new ProductoUnidad(cod, nom, precio, stock, desc))) {
                        System.out.println("¡Registrado con éxito!");
                    } else {
                        System.out.println("El código ya existe.");
                    }
                } else {
                    System.out.print("Costo de empaque: ");
                    double emp = scanner.nextDouble();
                    if (gestion.registrarProducto(new ProductoPeso(cod, nom, precio, stock, emp))) {
                        System.out.println("¡Registrado con éxito!");
                    } else {
                        System.out.println("El código ya existe.");
                    }
                }
                break;
            case 3:
                System.out.print("Código a buscar: ");
                Producto p = gestion.buscarProductoPorCodigo(scanner.nextLine());
                System.out.println(p != null ? p : "No encontrado.");
                break;
            case 4:
                System.out.print("Código a actualizar: ");
                Producto pa = gestion.buscarProductoPorCodigo(scanner.nextLine());
                if (pa != null) {
                    System.out.print("Nuevo precio: ");
                    pa.setPrecioBase(scanner.nextDouble());
                    System.out.print("Nuevas existencias: ");
                    pa.setExistencias(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("¡Actualizado!");
                } else {
                    System.out.println("No encontrado.");
                }
                break;
        }
    }

    private static void menuInventario(GestionCafeteria gestion) {
        System.out.println("\n--- INVENTARIO: STOCK BAJO (<5) ---");
        ArrayList<Producto> sb = gestion.obtenerProductosStockBajo();
        if (sb.isEmpty()) System.out.println("Sin alertas de stock bajo.");
        else for (Producto p : sb) System.out.println(p);
    }

    private static void menuClientes(GestionCafeteria gestion, Scanner scanner) {
        System.out.println("\n--- CLIENTES ---");
        System.out.println("1. Listar | 2. Registrar | 3. Buscar");
        int sub = scanner.nextInt();
        scanner.nextLine();
        if (sub == 1) {
            for (Cliente c : gestion.getListaClientes()) System.out.println(c);
        } else if (sub == 2) {
            System.out.print("Documento: ");
            String doc = scanner.nextLine();
            System.out.print("Nombre: ");
            String nom = scanner.nextLine();
            System.out.print("Teléfono: ");
            String tel = scanner.nextLine();
            if (gestion.registrarCliente(new Cliente(doc, nom, tel))) System.out.println("Registrado.");
            else System.out.println("El cliente ya existe.");
        } else if (sub == 3) {
            System.out.print("Documento a buscar: ");
            Cliente c = gestion.buscarClientePorDocumento(scanner.nextLine());
            System.out.println(c != null ? c : "No encontrado.");
        }
    }

    private static void realizarVenta(GestionCafeteria gestion, Scanner scanner) {
        System.out.println("\n--- REGISTRAR VENTA ---");
        System.out.print("¿Cliente registrado? (s/n): ");
        Cliente cliente = null;
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Documento: ");
            cliente = gestion.buscarClientePorDocumento(scanner.nextLine());
            if (cliente == null) System.out.println("No hallado, será Cliente General.");
        }

        System.out.println("Medio de pago: 1. Efectivo | 2. Tarjeta");
        int opPago = scanner.nextInt();
        scanner.nextLine();
        MedioPago pago;
        if (opPago == 1) {
            System.out.print("Dinero entregado: $");
            pago = new PagoEfectivo(scanner.nextDouble());
        } else {
            System.out.print("Número de tarjeta: ");
            String t = scanner.nextLine();
            System.out.print("Banco: ");
            pago = new PagoTarjeta(t, scanner.nextLine());
        }
        scanner.nextLine();

        Venta venta = new Venta(cliente, pago);
        boolean agregar = true;
        while (agregar) {
            for (Producto p : gestion.getListaProductos()) System.out.println(p);
            System.out.print("Código de producto a comprar: ");
            Producto prod = gestion.buscarProductoPorCodigo(scanner.nextLine());

            if (prod != null) {
                System.out.print("Cantidad: ");
                double cant = scanner.nextDouble();
                scanner.nextLine();
                if (cant > prod.getExistencias()) {
                    System.out.println("¡RECHAZADO! Stock disponible insuficiente (" + prod.getExistencias() + ")");
                } else {
                    venta.agregarItem(new ItemVenta(prod, cant));
                    System.out.println("Ítem agregado.");
                }
            } else {
                System.out.println("Producto no encontrado.");
            }
            System.out.print("¿Agregar otro producto? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("n")) agregar = false;
        }

        if (!venta.getItems().isEmpty() && gestion.registrarVenta(venta)) {
            System.out.println("\n¡Venta exitosa!");
            System.out.println(venta.generarComprobante());
        } else {
            System.out.println("Venta cancelada o sin ítems.");
        }
    }

    private static void mostrarReporteVentas(GestionCafeteria gestion) {
        System.out.println("\n--- REPORTE DE VENTAS ---");
        double totalGlobal = 0;
        for (Venta v : gestion.getListaVentas()) {
            System.out.println("Recibo N°: " + v.getNumeroRecibo() + " | Total: $" + v.getTotalVenta());
            totalGlobal += v.getTotalVenta();
        }
        System.out.println("Total ventas: " + gestion.getListaVentas().size() + " | Recaudado: $" + totalGlobal);
    }
}