package clase;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

import utilidades.Utilidades;


public class Empleado implements Comparable<Empleado>{
	Scanner sca = new Scanner (System.in);
	
	private String dniEmpleado;
	private String nombre;
	private String apellido;
	private LocalDate fechaNac;
	private LocalDate fechaAlta;
	private int numSorteo;
	


	// Getters & Setters
	public String getDniEmpleado() {
		return dniEmpleado;
	}

	public void setDniEmpleado(String dniEmpleado) {
		this.dniEmpleado = dniEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public int getNumSorteo() {
		return numSorteo;
	}

	public void setNumSorteo(int numSorteo) {
		this.numSorteo = numSorteo;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	// Constructores
	public Empleado() {
	}

	// toString
	

	@Override
	public String toString() {
		return "Empleado [dniEmpleado=" + dniEmpleado + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNac="
				+ fechaNac + ", fechaAlta=" + fechaAlta + ", numSorteo=" + numSorteo + "]";
	}

	// setDatos
	public void setDatos(String dni) {
		Random numRandom = new Random();
		
		dniEmpleado=dni;
		nombre=Utilidades.introducirCadena("Introduce el nombre:");
		apellido=Utilidades.introducirCadena("Introduce el apellido:");
		fechaNac = Utilidades.introducirFechas("Introduce la fecha de nacimiento en formato (dd/mm/yyyy):");
		System.out.println("Quieres introducir la fecha de alta: ");		
		boolean resp = sca.next().equalsIgnoreCase("si");
		if (resp) {
			fechaAlta = Utilidades.introducirFechas("Introduce la fecha:");
		}else {
			fechaAlta=LocalDate.now();
		}
		numSorteo=numRandom.nextInt(100) + 1;
		
	}

	// Metodos propios
	public int obtenerEdad () {
		return  Period.between(fechaNac, LocalDate.now()).getYears();
	}
	public int obtenerAntiguedad () {
		return  Period.between(fechaAlta, LocalDate.now()).getYears();
	}
	public int edadAlEntrar () {
		Period periodo = Period.between(fechaNac, fechaAlta);
		return  periodo.getYears();
	}

	@Override
	public int compareTo(Empleado otroEmpleado) {
		return this.nombre.compareTo(otroEmpleado.getApellido());
	}
}
