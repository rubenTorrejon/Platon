package models;

import java.util.Date;

/**
 * Clase que crea objetos de la tabla Alumno
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class Alumno {
	
	protected int idAlumno;
	protected String nombre;
	protected String apellidos;
	protected String dni;
	protected String telefono;
	protected String direccion;
	protected String email;
	protected Date fechaNacimiento;
	protected String responsableNombre;
	protected String responsableDni;
	protected String responsableTelefono;
	protected String responsableEmail;
	protected Date fechaAlta;
	protected Date fechaBaja;
	
	
	
	/**
	 * Constructor por defecto
	 */
	public Alumno() {
	}
	
	
	
	/**
	 * Contructor por parámetros
	 * 
	 * @param idAlumno
	 * @param nombre
	 * @param apellidos
	 * @param dni
	 * @param telefono
	 * @param direccion
	 * @param email
	 * @param fechaNacimiento
	 * @param responsableNombre
	 * @param responsableDni
	 * @param responsableTelefono
	 * @param responsableEmail
	 * @param fechaAlta
	 * @param fechaBaja
	 */
	public Alumno(int idAlumno, String nombre, String apellidos, String dni, String telefono, String direccion,
			String email, Date fechaNacimiento, String responsableNombre, String responsableDni,
			String responsableTelefono, String responsableEmail, Date fechaAlta, Date fechaBaja) {
		super();
		this.idAlumno = idAlumno;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.direccion = direccion;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.responsableNombre = responsableNombre;
		this.responsableDni = responsableDni;
		this.responsableTelefono = responsableTelefono;
		this.responsableEmail = responsableEmail;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
	}


	
	//Getters y Setters
	
	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getResponsableNombre() {
		return responsableNombre;
	}

	public void setResponsableNombre(String responsableNombre) {
		this.responsableNombre = responsableNombre;
	}

	public String getResponsableDni() {
		return responsableDni;
	}

	public void setResponsableDni(String responsableDni) {
		this.responsableDni = responsableDni;
	}

	public String getResponsableTelefono() {
		return responsableTelefono;
	}

	public void setResponsableTelefono(String responsableTelefono) {
		this.responsableTelefono = responsableTelefono;
	}

	public String getResponsableEmail() {
		return responsableEmail;
	}

	public void setResponsableEmail(String responsableEmail) {
		this.responsableEmail = responsableEmail;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	public String getDay(Date fecha) {
		String day = "";
		String fechaCompleta = this.fechaNacimiento.toString();
		if(!fechaCompleta.substring(8).startsWith("0")) {
			day = fechaCompleta.substring(8);
		} else {
			day = fechaCompleta.substring(9);
		}
		return day;
	}
	
	public String getMonth(Date fecha) {
		String month = this.fechaNacimiento.toString().substring(5,7);
		return month;
	}
	
	public String getYear(Date fecha) {
		String year = this.fechaNacimiento.toString().substring(0,4);
		return year;
	}
}
