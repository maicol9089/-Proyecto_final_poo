Sistema POS - Cafetería Delicias UAM

Integrantes del Equipo

Integrante 1: Michael Gallego

Integrante 2: Catalina Martinez

Integrante 3: Sergio Gomez M

Descripción del Negocio y Alcance

Cafetería Delicias UAM es un establecimiento comercial dedicado a la venta de bebidas preparadas, refrescos embotellados, snacks empacados, café especial en grano y productos de repostería artesanal.

El sistema permite administrar el inventario en tiempo real, gestionar la base de datos de clientes frecuentes, procesar ventas calculando automáticamente subtotales e impuestos (IVA del 19%), validar existencias disponibles antes de facturar y emitir comprobantes de venta con numeración consecutiva automática.

Tipos de Producto y Cálculo de Precios

El catálogo de la cafetería se divide en dos categorías principales, cada una con una lógica distinta para calcular su precio final de venta:

Productos por Unidad (ProductoUnidad):

Descripción: Artículos que se despachan por unidades enteras (ej. Café Americano, Gaseosa 400ml, Galletas de Soda).

Cálculo de precio: Se multiplica el precio base por la cantidad de unidades llevadas. Si el producto tiene habilitado el beneficio de descuento fiel y el cliente lleva 3 o más unidades, se aplica automáticamente un 10% de descuento sobre el subtotal de ese ítem.

Productos por Peso o Porción (ProductoPeso):

Descripción: Productos que se venden a granel o por gramos/porciones (ej. Café en grano molido al instante, Torta de chocolate artesanal).

Cálculo de precio: Se multiplica el precio base por la cantidad de gramos solicitados y se suma un costo fijo de empaque especial para su conservación y transporte.

Medios de Pago Aceptados

Para facilitar el cierre de las ventas, la cafetería soporta dos modalidades de pago:

Efectivo (PagoEfectivo): Recibe el monto entregado por el cliente, valida que cubra el total de la factura y calcula automáticamente el cambio (devuelta) a entregar.

Tarjeta Débito / Crédito (PagoTarjeta): Procesa y aprueba el pago asociando el número de tarjeta y la entidad bancaria del cliente.

Arquitectura del Proyecto (Paquetes)

El código fuente está organizado en tres paquetes con responsabilidades claramente separadas:

modelo: Contiene las clases base y entidades del dominio (Producto, ProductoUnidad, ProductoPeso, Cliente, ItemVenta, Venta), así como la interfaz MedioPago y sus implementaciones (PagoEfectivo, PagoTarjeta).

logica: Contiene la clase GestionCafeteria, encargada de administrar las colecciones (ArrayList) de productos, clientes y ventas, precargar los datos iniciales, realizar búsquedas y controlar las reglas de negocio del inventario.

ejecucion: Contiene la clase Principal con el método main y el menú interactivo de consola para operar todo el sistema.

Funcionalidades Principales

Gestión de Productos (RF1): Listar catálogo, registrar nuevos productos (por unidad o por peso), buscar por código y actualizar precios o existencias.

Control de Inventario (RF2): Descuento automático de existencias al confirmar una venta, bloqueo de ventas si la cantidad supera el stock disponible y alerta de productos con existencias bajas (< 5 unidades).

Gestión de Clientes (RF3): Registro, listado y búsqueda de clientes por número de documento. Soporte para ventas a Cliente General (sin registro).

Registro de Ventas (RF4): Creación de facturas con múltiples ítems de distintos tipos, cálculo de subtotales, aplicación del IVA (19%) y selección de medio de pago.

Comprobante de Venta (RF5): Generación de recibo detallado en consola con número consecutivo automático, datos del cliente, desglose de productos, impuestos y detalle del pago.

Reporte de Ventas (RF6): Consolidado histórico de las ventas realizadas durante la ejecución del programa y total de dinero recaudado.

Datos Precargados (RF7): Al iniciar, el sistema carga automáticamente 5 productos (3 por unidad y 2 por peso) y 2 clientes registrados para pruebas inmediatas.

Cómo Ejecutar el Sistema en NetBeans

Clonar o descargar este repositorio en tu computadora.

Abrir Apache NetBeans IDE.

Ir al menú superior File > Open Project... (Ctrl + Shift + O).

Buscar y seleccionar la carpeta del proyecto NetBeans dentro del repositorio y hacer clic en Open Project.

En el panel izquierdo (Projects), desplegar el proyecto, abrir Source Packages > ejecucion.

Hacer clic derecho sobre Principal.java y seleccionar Run File (Shift + F6), o presionar F6 para ejecutar el proyecto completo.

Estructura del Repositorio

├── ProyectoCafeteriaPOS/     # Proyecto completo de Apache NetBeans
│   └── src/
│       ├── modelo/           # Entidades, herencia, interfaz y clases de pago
│       ├── logica/           # Lógica de negocio y gestión de ArrayLists
│       └── ejecucion/        # Menú principal de consola
├── docs/
│   ├── uml-v1.png            # Diagrama de clases UML inicial (Sesión 1)
│   ├── uml.png               # Diagrama de clases UML final (Sesión 3)
│   ├── javadoc/              # Documentación HTML generada desde NetBeans
│   └── informe.pdf           # Informe técnico y mapa de técnicas (RT1 - RT13)
└── README.md                 # Documentación general del proyecto
