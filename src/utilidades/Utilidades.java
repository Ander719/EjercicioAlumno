package utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Utilidades {

	public static String introducirCadena(String mensaje) {
		Scanner teclado = new Scanner(System.in);
		String cadena = null;

		System.out.println(mensaje);
		try {
			cadena = teclado.next();
		} catch (NoSuchElementException e) {
			System.out.println("Error al introducir la cadena");
		}
		return cadena;
	}

	public static int introducirNumero(String mensaje) {
		Scanner teclado = new Scanner(System.in);
		String cadena;
		int numero = 0;
		boolean correcto = true;

		do {
			correcto = true;
			cadena = introducirCadena(mensaje);
			try {
				numero = Integer.parseInt(cadena);
			} catch (NumberFormatException e) {
				System.out.println("Error los digitos no son numeros enteros");
				correcto = false;
			}
		} while (!correcto);

		return numero;
	}

	public static float introducirFloat(String mensaje) {
		Scanner teclado = new Scanner(System.in);
		String cadena;
		float numero = 0;
		boolean correcto = true;

		do {
			correcto = true;
			cadena = introducirCadena(mensaje);
			try {
				numero = Float.parseFloat(cadena);
			} catch (NumberFormatException e) {
				System.out.println("Error los digitos no son numeros enteros");
				correcto = false;
			}
		} while (!correcto);

		return numero;

	}

	public static LocalDate introducirFechas(String mensaje) {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		boolean correcto = true;
		LocalDate fecha = null;
		String fechaCadena;

		do {
			correcto=true;
			fechaCadena = introducirCadena(mensaje);
			try {
				fecha=LocalDate.parse(fechaCadena, formateador);
			} catch (DateTimeParseException  e) {
				System.out.println("No es una fecha correcta");
				correcto=false;
			}
		} while (!correcto);

		return fecha;

	}
}
