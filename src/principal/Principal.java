package principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import clase.Empleado;
import utilidades.Utilidades;

public class Principal {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		int opc;

		do {
			opc = mostarMenu();
			if (empleados.isEmpty() && opc > 1 && opc < 11) {
				System.out.println("No hay empleados registrados.");
			} else {
				switch (opc) {
				case 1:
					introducirEmpleado(empleados);
					break;
				case 2:
					visualizarTodosEmpleados(empleados);
					break;
				case 3:
					ordenarApellido(empleados);
					break;
				case 4:
					modificarPorDni(empleados, teclado);
					break;
				case 5:
					borrarPorDni(empleados, teclado);
					break;
				case 6:
					masJovenMayor(empleados, teclado);
					break;
				case 7:
					edadAlEntrarEmpresa(empleados, teclado);
					break;
				case 8:
					ordenDescendenteAntiguedad(empleados, teclado);
					break;
				case 9:
					sorteo(empleados);
					break;
				case 10:
					System.out.println("Quieres hacer el 1 o el 2");
					opc = teclado.nextInt();
					if (opc == 1) {
						buscarMesCumple(empleados, teclado);
					} else {

					}
					break;
				case 11:
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Opción inválida. Intenta de nuevo.");
				}
			}
		} while (opc != 11);
	}

	private static int mostarMenu() {
		int opc;
		System.out.println("1. Introducir empleado");
		System.out.println("2. Listado de empleados");
		System.out.println("3. Listado de empleados ordenados por apellido");
		System.out.println("4. Consultar/Modificar datos del empleado a partir del DNI");
		System.out.println("5. Borrado de empleado a partir de DNI");
		System.out.println("6. Listado de la edad de los empleados indicando el más joven y el más mayor");
		System.out.println("7. Listado de la edad de los empleados en el momento en el que entraron a la empresa");
		System.out.println("8. Listado ordenado en descendente de la antigüedad de los empleados");
		System.out.println("9. Sorteo diario (se sortea hasta que hay ganador)");
		System.out.println("10. Estadísticas:");
		System.out.println(
				"(Dentro del 10) 1. Se introduce mes y nos salen los empleados que cumplen en ese mes (nombre/apellido/dia ordenado por día)");
		System.out.println(
				"(Dentro del 10) 2. Estadística de nombres (nombre/nº vecesordenado de mayor a menor por número de veces)");
		System.out.println("11. Salir");
		opc = Utilidades.introducirNumero("Elige una opción: ");

		return opc;
	}

	private static void ordenarApellido(ArrayList<Empleado> empleados) {
		Collections.sort(empleados);
		visualizarTodosEmpleados(empleados);
	}

	private static void buscarMesCumple(ArrayList<Empleado> empleados, Scanner teclado) {
		int mes;
		Empleado inter;
		boolean compr = false;

		mes = Utilidades.introducirNumero("Introduce el mes a buscar (mm): ");
		for (int i = 0; i < empleados.size(); i++) {
			if (empleados.get(i).getFechaNac().getMonthValue() == mes) {
				for (int j = i + 1; j < empleados.size(); j++) {
					if (empleados.get(j).getFechaNac().isBefore(empleados.get(i).getFechaNac())) {
						inter = empleados.get(j);
						empleados.set(j, empleados.get(i));
						empleados.set(i, inter);
						compr = true;
					}
				}
				System.out.println(empleados.get(i));
			}
		}
		if (!compr) {
			System.out.println("Ningun empleado ha nacido ese mes");
		}
	}

	private static void sorteo(ArrayList<Empleado> empleados) {
		boolean premiado = false;
		int random;

		do {
			Random numeroRandom = new Random();
			random = numeroRandom.nextInt(100) + 1;
			for (int i = 0; i < empleados.size(); i++) {
				if (empleados.get(i).getNumSorteo() == random) {
					System.out.println(empleados.get(i));
					premiado = true;
				}
			}

		} while (!premiado);
	}

	private static void ordenDescendenteAntiguedad(ArrayList<Empleado> empleados, Scanner teclado) {
		Empleado inter;

		for (int i = 0; i < empleados.size(); i++) {
			for (int j = i + 1; j < empleados.size(); j++) {
				if (empleados.get(j).getFechaAlta().isBefore(empleados.get(i).getFechaAlta())) {
					inter = empleados.get(j);
					empleados.set(j, empleados.get(i));
					empleados.set(i, inter);
				}
			}
			System.out.println(empleados.get(i));
		}
	}

	private static void edadAlEntrarEmpresa(ArrayList<Empleado> empleados, Scanner teclado) {
		for (int i = 0; i < empleados.size(); i++) {
			System.out.println("La edad del empleado con dni: " + empleados.get(i).getDniEmpleado()
					+ " al entrar en la entresa era de " + empleados.get(i).edadAlEntrar() + " años");
		}

	}

	private static void masJovenMayor(ArrayList<Empleado> empleados, Scanner teclado) {
		Empleado inter;

		for (int i = 0; i < empleados.size(); i++) {
			for (int j = i + 1; j < empleados.size(); j++) {
				if (empleados.get(j).getFechaNac().isAfter(empleados.get(i).getFechaNac())) {
					inter = empleados.get(i);
					empleados.set(i, empleados.get(j));
					empleados.set(j, inter);
				}

			}
		}
		System.out.println("El mas joven es: " + empleados.get(0));

		for (int i = 0; i < empleados.size(); i++) {
			for (int j = i + 1; j < empleados.size(); j++) {
				if (empleados.get(j).getFechaNac().isBefore(empleados.get(i).getFechaNac())) {
					inter = empleados.get(i);
					empleados.set(i, empleados.get(j));
					empleados.set(j, inter);
				}

			}
		}
		System.out.println("El mas mayor es: " + empleados.get(0));
	}

	private static void modificarPorDni(ArrayList<Empleado> empleados, Scanner teclado) {
		String dni;
		boolean resp;
		Empleado empl;

		dni = Utilidades.introducirCadena("Introduce el dni del empleado a editar: ");
		for (int i = 0; i < empleados.size(); i++) {
			if (empleados.get(i).getDniEmpleado().equalsIgnoreCase(dni)) {
				System.out.println("Deseas cambiar la información del empleado: " + empleados.get(i));
				resp = teclado.next().equalsIgnoreCase("si");
				if (resp) {
					empleados.remove(i);
					empl = new Empleado();
					empl.setDatos(dni);
					empleados.add(empl);
					System.out.println("Modificado exitosamente.");
				}
			}
		}
	}

	private static void borrarPorDni(ArrayList<Empleado> empleados, Scanner teclado) {
		String dniBuscar;
		boolean compro = false;

		dniBuscar = Utilidades.introducirCadena("Introduce el DNI del empleado a dar de baja:");

		for (int i = 0; i < empleados.size(); i++) {
			if (empleados.get(i).getDniEmpleado().equalsIgnoreCase(dniBuscar)) {
				empleados.remove(i);
				System.out.println("Empleado eliminado correctamente.");
				compro = true;
			}
		}
		if (!compro) {
			System.out.println("No se encontró ningún empleado con ese dni.");

		}
	}

	private static void visualizarTodosEmpleados(ArrayList<Empleado> empleados) {
		for (int i = 0; i < empleados.size(); i++) {
			System.out.println(empleados.get(i));
		}
	}

	private static void introducirEmpleado(ArrayList<Empleado> empleados) {
		Empleado empl;
		String dni;
		int existeEmpleado;
		boolean seguir = true;

		for (; seguir;) {
			dni = Utilidades.introducirCadena("Introduce el dni: ");
			existeEmpleado = buscarEmpleado(empleados, dni);
			if (existeEmpleado == -1) {
				empl = new Empleado();
				empl.setDatos(dni);
				empleados.add(empl);
				System.out.println("Añadido exitosamente.");
			} else {
				System.out.println("Ya existe un empleado con ese dni.");
			}

			seguir = Utilidades.introducirCadena("Quieres seguir introduciendo empleados?").equalsIgnoreCase("SI");
		}
	}

	private static int buscarEmpleado(ArrayList<Empleado> empleados, String dni) {
		for (int i = 0; i < empleados.size(); i++) {
			if (empleados.get(i).getDniEmpleado().equalsIgnoreCase(dni)) {
				return 0;
			}
		}
		return -1;

	}

}
