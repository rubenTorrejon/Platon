package models;

/**
 * Clase que crea objetos de la tabla Aula
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class Aula {

	protected int idAula;
	protected int fkIdCentro;
	protected String nombre;
	protected int capacidad;
	protected String comentarios;
	
	protected String centro;
	
	

	/**
	 * Constructor por defecto
	 */
	public Aula() {
	}


	
	/**
	 * Contructor por parámetros
	 * 
	 * @param idAula
	 * @param fkIdCentro
	 * @param nombre
	 * @param capacidad
	 * @param comentarios
	 */
	public Aula(int idAula, int fkIdCentro, String nombre, int capacidad, String comentarios) {
		super();
		this.idAula = idAula;
		this.fkIdCentro = fkIdCentro;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.comentarios = comentarios;
	}


	
	//Getters y Setters
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdAula() {
		return idAula;
	}

	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}

	public int getFkIdCentro() {
		return fkIdCentro;
	}

	public void setFkIdCentro(int fkIdCentro) {
		this.fkIdCentro = fkIdCentro;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}
	
}
