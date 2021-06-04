package common;

import java.util.ArrayList;

import javax.swing.JComboBox;

import dao.*;
import models.*;

/**
 * Clase que centraliza el completado de los valores de los ComboBoxes, con el fin de evitar
 * repetición de código en cada clase que contenga un ComboBox
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class RellenadorComboBox {

	private AlumnoDAO miAlumnoDao = new AlumnoDAO();
	private AulaDAO miAulaDao = new AulaDAO();
	private EmpleadoDAO miEmpleadoDao = new EmpleadoDAO();
	private GrupoDAO miGrupoDao = new GrupoDAO();
	private CentroDAO miCentroDao = new CentroDAO();
	
	/**
	 * Constructor por defecto
	 */
	public RellenadorComboBox() {
	}
	
	/**
	 * Comportamiento que rellena un ComboBox con las horas que, legalmente, se puede mantener
	 * abierto un local de tipo educativo en España.
	 * 
	 * @param ComboBox
	 */
	public void addHoras(JComboBox<String> miComboBox) {
		miComboBox.addItem("Hora");
		miComboBox.addItem("09:00");
		miComboBox.addItem("09:30");
		miComboBox.addItem("10:00");
		miComboBox.addItem("10:30");
		miComboBox.addItem("11:00");
		miComboBox.addItem("11:30");
		miComboBox.addItem("12:00");
		miComboBox.addItem("12:30");
		miComboBox.addItem("13:00");
		miComboBox.addItem("13:30");
		miComboBox.addItem("14:00");
		miComboBox.addItem("14:30");
		miComboBox.addItem("15:00");
		miComboBox.addItem("15:30");
		miComboBox.addItem("16:00");
		miComboBox.addItem("16:30");
		miComboBox.addItem("17:00");
		miComboBox.addItem("17:30");
		miComboBox.addItem("18:00");
		miComboBox.addItem("18:30");
		miComboBox.addItem("19:00");
		miComboBox.addItem("19:30");
		miComboBox.addItem("20:00");
		miComboBox.addItem("20:30");
		miComboBox.addItem("21:00");
		miComboBox.addItem("21:30");
		miComboBox.addItem("22:00");
	}
	
	/**
	 * Comportamiento que rellena un ComboBox con los días que, legalmente, se puede mantener
	 * abierto un local de tipo educativo en España.
	 * 
	 * @param ComboBox
	 */
	public void addDiasSemana(JComboBox<String> miComboBox) {
		miComboBox.addItem("Día");
		miComboBox.addItem("Lunes");
		miComboBox.addItem("Martes");
		miComboBox.addItem("Miercoles");
		miComboBox.addItem("Jueves");
		miComboBox.addItem("Viernes");
		miComboBox.addItem("Sábado");
		miComboBox.addItem("Domingo");
	}
	
	/**
	 * Comportamiento que rellena un ComboBox con los nombres de los meses.
	 * 
	 * @param ComboBox
	 */
	public void addMeses(JComboBox<String> miComboBox) {
		miComboBox.addItem("Mes");
		miComboBox.addItem("Enero");
		miComboBox.addItem("Febrero");
		miComboBox.addItem("Marzo");
		miComboBox.addItem("Abril");
		miComboBox.addItem("Mayo");
		miComboBox.addItem("Junio");
		miComboBox.addItem("Julio");
		miComboBox.addItem("Agosto");
		miComboBox.addItem("Septiembre");
		miComboBox.addItem("Octubre");
		miComboBox.addItem("Noviembre");
		miComboBox.addItem("Diciembre");
	}

	/**
	 * Comportamiento que rellena un ComboBox con los días del mes.
	 * @param miComboBox
	 */
	public void addDiasMes(JComboBox<String> miComboBox) {
		miComboBox.addItem("Día");
		for (int i = 1; i<=31; i++){
			String valueOfi = String.valueOf(i);
			miComboBox.addItem(valueOfi);
		}
	}
	
	/**
	 * Comportamiento que rellena un ComboBox con todos los años desde un valor inicial
	 * hasta un valor final.
	 * 
	 * @param miComboBox
	 * @param actualYear
	 * @param yearFuture
	 */
	public void addYears(JComboBox<String> miComboBox, int actualYear, int yearFuture) {
		miComboBox.addItem("Año");
		while (actualYear <= yearFuture){
			miComboBox.addItem(actualYear+"");
			actualYear++;
		}
	}

	/**
	 * Comportamiento que rellena un ComboBox con todos los años desde el actual hasta 1950, de cara
	 * a la fecha de nacimiento de alumnos.
	 * 
	 * @param miComboBox
	 * @param actualYear
	 */
	public void addYears(JComboBox<String> miComboBox, int actualYear) {
		miComboBox.addItem("Año");
		for (int i = actualYear; i>=1950; i--){
			String valueOfi = String.valueOf(i);
			miComboBox.addItem(valueOfi);
		}
	}
	
	/**
	 * Comportamiento que rellena un ComboBox con valores numéricos de 1 hasta un valor límite.
	 * 
	 * @param miComboBox
	 * @param capacidadMaxima
	 */
	public void addCapacidad(JComboBox<String> miComboBox, int capacidadMaxima) {
		miComboBox.addItem("Capacidad");
		for (int i = 1; i<=capacidadMaxima; i++){
			String valueOfi = String.valueOf(i);
			miComboBox.addItem(valueOfi);
		}
	}
	
	/**
	 * Comportamiento que rellena un ComboBox con los nombres de los profesores registrados en la BDD
	 * con formato 'Apellidos, Nombre'.
	 * 
	 * @param miComboBox
	 */
	public void addProfesores(JComboBox<String> miComboBox) {
		ArrayList<Empleado> listaEmpleados = miEmpleadoDao.buscarProfesores();
		miComboBox.addItem("Profesor");
		for(Empleado emple:listaEmpleados) {
			miComboBox.addItem(emple.getApellidos()+", "+emple.getNombre());
		}
	}
	
	/**
	 * Comportamiento que rellena un ComboBox con todos los nombres de las aulas registradas en la BDD
	 * 
	 * @param miComboBox
	 */
	public void addAulas(JComboBox<String> miComboBox) {
		ArrayList<Aula> listaAulas = miAulaDao.buscarAulas();
		miComboBox.addItem("Aula");
		for(Aula aula:listaAulas) {
			miComboBox.addItem(aula.getNombre().toString());
		}
	}
	
	/**
	 * Comportamiento que rellena un ComboBox con todos los grupos registrados en la BDD
	 * 
	 * @param miComboBox
	 */
	public void addGrupos(JComboBox<String> miComboBox) {
		ArrayList<Grupo> listaGrupos = miGrupoDao.buscarGrupos();
		miComboBox.addItem("Grupo");
		for(Grupo grupo:listaGrupos) {
			miComboBox.addItem(grupo.getNombre().toString());
		}
	}
	
	/**
	 * Comportamiento que rellena un ComboBox con los nombres de todos los alumnos registrados en la BDD
	 * que NO están dados de alta, con formato 'Apellidos, Nombre'
	 * 
	 * @param miComboBox
	 */
	public void addAlumnos(JComboBox<String> miComboBox) {
		ArrayList<Alumno> listaAlumnos = miAlumnoDao.buscarAlumnosActivos();
		miComboBox.addItem("Alumno");
		for(Alumno alumno:listaAlumnos) {
			miComboBox.addItem(alumno.getApellidos().toString()+", "+alumno.getNombre().toString());
		}
	}
	
	/**
	 * Comportamiento que 
	 * 
	 * @param miComboBox
	 */
	public void addCentros(JComboBox<String> miComboBox) {
		ArrayList<Centro> listaCentros = miCentroDao.buscarCentros();
		miComboBox.addItem("Centro");
		for(Centro centro:listaCentros) {
			miComboBox.addItem(centro.getNombre().toString());
		}
	}
	
	/**
	 * Comportamiento que, obtenido un valor de texto 'mes' correspondiente a su nombre, devuelve el
	 * número al que corresponde
	 * @param mes en formato texto
	 * @return mes en formato numero
	 */
	public String pasaMesANumero(String mes) {
		String mesNumerico = "";
		switch (mes) {
			case "Enero": 
				mesNumerico = "01";
				break;
			case "Febrero":
				mesNumerico = "02";
				break;
			case "Marzo": 
				mesNumerico = "03";
				break;
			case "Abril": 
				mesNumerico = "04";
				break;
			case "Mayo": 
				mesNumerico = "05";
				break;
			case "Junio": 
				mesNumerico = "06";
				break;
			case "Julio": 
				mesNumerico = "07";
				break;
			case "Agosto": 
				mesNumerico = "08";
				break;
			case "Septiembre": 
				mesNumerico = "09";
				break;
			case "Octubre": 
				mesNumerico = "10";
				break;
			case "Noviembre": 
				mesNumerico = "11";
				break;
			case "Diciembre": 
				mesNumerico = "12";
				break;
		}
		return mesNumerico;		
	}
	
	/**
	 * Comportamiento que, obtenido un valor de texto 'mes' correspondiente a su número, devuelve el
	 * nombre que le corresponde
	 * @param mes en formato numero
	 * @return mes en formato texto
	 */
	public String pasaNumeroAMes(String numero) {
		String mesNumerico = "";
		switch (numero) {
			case "01": 
				mesNumerico = "Enero";
				break;
			case "02": 
				mesNumerico = "Febrero";
				break;
			case "03": 
				mesNumerico = "Marzo";
				break;
			case "04": 
				mesNumerico = "Abril";
				break;
			case "05": 
				mesNumerico = "Mayo";
				break;
			case "06": 
				mesNumerico = "Junio";
				break;
			case "07": 
				mesNumerico = "Julio";
				break;
			case "08": 
				mesNumerico = "Agosto";
				break;
			case "09": 
				mesNumerico = "Septiembre";
				break;
			case "10": 
				mesNumerico = "Octubre";
				break;
			case "11": 
				mesNumerico = "Noviembre";
				break;
			case "12": 
				mesNumerico = "Diciembre";
				break;
		}
		return mesNumerico;		
	}
	
}
