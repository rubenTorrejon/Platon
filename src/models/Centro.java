package models;

/**
 * Clase que crea objetos de la tabla Centro
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class Centro {

	protected int idCentro;
	protected String nombre;
	protected String telefono;
	protected String direccion;
	protected String observaciones;
	
	
	
	/**
	 * Constructor por defecto
	 */
	public Centro() {
		
	}

	
	
	/**
	 * Contructor por parámetros
	 * 
	 * @param idCentro
	 * @param nombre
	 * @param telefono
	 * @param direccion
	 * @param observaciones
	 */
	public Centro(int idCentro, String nombre, String telefono, String direccion, String observaciones) {
		super();
		this.idCentro = idCentro;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.observaciones = observaciones;
	}

	
	
	//Getters y Setters
	
	public int getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(int idCentro) {
		this.idCentro = idCentro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	
}
