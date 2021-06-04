package models;

import java.sql.Date;

/**
 * Clase que crea objetos de la tabla Empleado
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class Empleado {

	protected int idEmpleado;
	protected String user;
	protected String password;
	protected String nombre;
	protected String apellidos;
	protected String dni;
	protected String telefono;
	protected String email;
	protected String direccion;
	protected int sueldo;
	protected String titulo;
	protected String rol;
	protected Date fechaAlta;
	protected Date fechaBaja;
	
	
	
	/**
	 * Constructor por defecto
	 */
	public Empleado() {
	}

	
	
	/**
	 * Contructor por parámetros
	 * 
	 * @param idEmpleado
	 * @param user
	 * @param password
	 * @param nombre
	 * @param apellidos
	 * @param dni
	 * @param telefono
	 * @param email
	 * @param direccion
	 * @param sueldo
	 * @param titulo
	 * @param rol
	 * @param fechaAlta
	 * @param fechaBaja
	 */
	public Empleado(
			int idEmpleado,String user,String password,String nombre,String apellidos, 
			String dni,String telefono,String email,String direccion,int sueldo, 
			String titulo,String rol,Date fechaAlta,Date fechaBaja) {
		
		super();
		this.idEmpleado = idEmpleado;
		this.user = user;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.sueldo = sueldo;
		this.titulo = titulo;
		this.rol = rol;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
	}


	
	//Getters y Setters
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getSueldo() {
		return sueldo;
	}
	
	public String getSueldoString() {
		String sueldoString = ""+sueldo;
		return sueldoString;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
