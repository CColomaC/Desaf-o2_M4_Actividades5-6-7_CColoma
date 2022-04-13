package actividad6;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Consola {

static DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	private static ArrayList<Producto> productos = new ArrayList<Producto>();
	private static ArrayList<Venta> ventas = new ArrayList<Venta>();
	
	static Scanner scanner = new Scanner(System.in);
	
	//constantes
	public final static int OPCION_MENU_CREAR	 	= 1;
	public final static int OPCION_MENU_LISTA 		= 2;
	public final static int OPCION_MENU_ELIMINAR 	= 3;
	public final static int OPCION_MENU_COMPRAR 	= 4;
	public final static int OPCION_MENU_VENTAS 		= 5;
	public final static int OPCION_MENU_REPORTE 	= 6;
	public final static int OPCION_MENU_SALIR		= 7;
	
	public static void main(String[] args) {
		

		
		int opcionMenu;		
		
		do {

			opcionMenu = menu();
			
			System.out.printf("Ha seleccionado la opcion %d \n \n", opcionMenu);
			
			switch(opcionMenu) {
				case OPCION_MENU_CREAR:
					crearProducto();
					break;
				case OPCION_MENU_LISTA:
					verListaProductos();
					break;
				case OPCION_MENU_ELIMINAR:
					eliminarProducto();
					break;	
				case OPCION_MENU_COMPRAR:
					comprarProductos();
					break;
				case OPCION_MENU_VENTAS:
					verVentasProductos();
					break;
				case OPCION_MENU_REPORTE:
					generarReporte();
					break;
				case OPCION_MENU_SALIR:
					System.out.println("Saliendo del sistema...");
					break;
				default:
					System.out.println("Opcion no valida, inténtelo de nuevo");
					break;
			}
			
		} while(opcionMenu != OPCION_MENU_SALIR);
	
	}

	private static void crearProducto() {
		scanner.nextLine();
		System.out.println("Digite un código para el producto");
		String codigoProducto = scanner.nextLine();

		System.out.println("Digite un nombre para el producto");
		String nombreProducto = scanner.nextLine();
		
		System.out.println("Digite un precio para el producto");
		int precioProducto = scanner.nextInt();
		
		Producto nuevoProducto = new Producto(codigoProducto, nombreProducto, precioProducto);
		productos.add(nuevoProducto);
		
	}	
	
	private static void verListaProductos() {
		System.out.println("\n PRODUCTOS");
		System.out.println("==============");
		
		for (Producto producto : productos) {
			System.out.printf("Codigo: %s Producto: %s Precio: %d %n", producto.getCodigo(), producto.getNombre(), producto.getPrecio());
			System.out.println("--------------------------------------------------");
		}
		System.out.println("\n\n");

	}
	
	private static void eliminarProducto() {
		scanner.nextLine();
		System.out.println("Digite el código del producto a eliminar: ");
		String codigo = scanner.nextLine();
		System.out.println(codigo);
		
		Producto producto = buscarProducto(codigo);
		if (producto != null) {
			productos.remove(producto);
			System.out.printf("Producto eliminado: %s %n%n", producto.getNombre());
		}else {
			System.out.printf("No se encuentra el producto %n%n");
		}
		
	}

	private static void comprarProductos() {
		Venta venta = new Venta();
		boolean seguirAgregandoProductos = true;
		
		do {

		verListaProductos();

		scanner.nextLine(); 
		System.out.println("Digite el código del producto que desea comprar: ");
		String codigo = scanner.nextLine();
		

		Producto producto = buscarProducto(codigo);
		
		System.out.println("Escriba la cantidad que desea comprar: ");
		int cantidad = scanner.nextInt();

		LineaDetalle lineaDetalle = new LineaDetalle(cantidad,producto);
		venta.agregarLineaDetalle(lineaDetalle);
		
		System.out.println("¿Desea agregar más productos al carro? (si/no)");

		seguirAgregandoProductos = scanner.next().equalsIgnoreCase("SI") ? true : false;
		}while (seguirAgregandoProductos == true);
	
		ventas.add(venta);
	}

	private static void verVentasProductos() {
		System.out.println("\n VENTAS");
		System.out.println("==============");
		
		for (Venta venta : ventas) {			
			System.out.printf(" Fecha: %s %n", formateador.format(venta.getFecha()));
			System.out.println(venta.productosTotales());
			System.out.printf(" Total: %s %n", venta.calcularTotal());
			System.out.println("--------------------------------------------------");
		}
		System.out.println("\n\n");			}

	private static void generarReporte() {

		String nombreArchivo = "REPORTE-VENTAS.csv";

		String contenidoArchivo = "REPORTE VENTAS\n______________\n";

		for (Venta venta : ventas) {
			contenidoArchivo += "Fecha: "+ formateador.format(venta.getFecha())+"\n";
			contenidoArchivo += venta.productosTotales()+"\n";
			contenidoArchivo += "Total: "+venta.calcularTotal()+"\n";
			contenidoArchivo += "-----------------------------------------------\n";
		}

		try {
			FileWriter writer = new FileWriter(nombreArchivo);
			writer.write(contenidoArchivo);
			writer.close();
			
			System.out.println("Archivo generado exitosamente");
			
		} catch(IOException ioe) {
			System.out.println("Falla al escribir el archivo.");
		}
		
	}
	
	private static Producto buscarProducto(String codigo) {
		for (Producto p: productos) {
			if (p.getCodigo().equalsIgnoreCase(codigo)) {
				return p;
			}
		}
		return null;
	}

	private static int menu() {
		System.out.println("Punto de Venta A6:\n"); // 
		System.out.println("1. Crear producto");
		System.out.println("2. Ver lista de productos");
		System.out.println("3. Eliminar producto");
		System.out.println("4. Agregar productos al carro");
		System.out.println("5. Ver ventas realizadas");
		System.out.println("6. Generar reporte");
		System.out.println("7. Salir \n");

		System.out.println("Seleccione una opcion del 1 al 7");
 
		int opcionSeleccionada = scanner.nextInt(); 

		return opcionSeleccionada;
	}
}
