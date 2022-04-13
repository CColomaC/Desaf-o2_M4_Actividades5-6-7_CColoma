package actividad5;

import java.util.ArrayList;
import java.util.Scanner;

public class AppProducto {

	static ArrayList<Producto> productos = new ArrayList<>();
	static Scanner scanner = new Scanner(System.in);
	private static final int MENU_CREAR_PRODUCTO 	= 1;
	private static final int MENU_VER_PRODUCTO 		= 2;
	private static final int MENU_SALIR 			= 0;
	
	public static void main(String[] args) {
		menu();

	}

	private static void menu() {
		int opcion = 1;
		do {
			
			System.out.println("1. Crear producto");
			System.out.println("2. Ver productos");
			System.out.println("0. Salir");
			System.out.println("Elija una opcion (0-2)");
			
			opcion = scanner.nextInt();
			
			switch(opcion) {
				case MENU_CREAR_PRODUCTO:
					crearProductos();
					break;
				case MENU_VER_PRODUCTO:
					verProductos();
					break;
				case MENU_SALIR:
					break;
			}
			
		}while(opcion != MENU_SALIR);
		
	}

	private static void crearProductos() {
		scanner.nextLine();
		
		System.out.println("Ingrese el nombre del producto");
		String nombre = scanner.nextLine();
		System.out.println("Ingrese la categoría del producto");
		String categoria = scanner.nextLine();
		System.out.println("Ingrese el precio del producto");
		int precio= scanner.nextInt();
		scanner.nextLine();
		System.out.println("Ingrese la descripcion del producto");
		String descripcion = scanner.nextLine();
		
		Producto producto = new Producto(categoria,precio,nombre,descripcion);
		productos.add(producto);
		
		System.out.printf("Se creó el producto '%s' de la categoría '%s' con un precio de '%d' . Su descripción es '%s' %n.",
				producto.getNombre(),
				producto.getCategoria(),
				producto.getPrecio(),
				producto.getDescripcion());
	}

	private static void verProductos() {
		System.out.println("Lista de productos");
		System.out.println("==========================");
		for(Producto producto : productos) {
			System.out.printf(" Nombre producto: %s %n Categoria producto: %s %n Descripcion producto: %s %n Precio producto: %d %n",
					producto.getNombre(),
					producto.getCategoria(),
					producto.getDescripcion(),
					producto.getPrecio());
		}
		
	}

}