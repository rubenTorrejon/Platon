package models;

import java.util.Date;

import dao.*;

/**
 * Clase que crea objetos de la tabla Alumno_Grupo
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class AlumnoGrupo {

	protected int fkIdAlumno;
	protected int fkIdGrupo;
	protected Date fechaInicio;
	protected Date fechaFin;
	
	protected String nombreAlumno;
	protected String apellidosAlumno;
	
	protected AlumnoDAO miAlumnoDao = new AlumnoDAO();
	protected GrupoDAO miGrupoDao = new GrupoDAO();
	
	
	
	/**
	 * Constructor por defecto
	 */
	public AlumnoGrupo() {
		
	}

	
	
	/**
	 * Contructor por parámetros
	 * 
	 * @param fkIdAlumno
	 * @param fkIdGrupo
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public AlumnoGrupo(int fkIdAlumno, int fkIdGrupo, Date fechaInicio, Date fechaFin) {
		super();
		this.fkIdAlumno = fkIdAlumno;
		this.fkIdGrupo = fkIdGrupo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	
	
	//Getters y Setters
	
	public int getFkIdAlumno() {
		return fkIdAlumno;
	}

	public void setFkIdAlumno(int fkIdAlumno) {
		this.fkIdAlumno = fkIdAlumno;
	}

	public int getFkIdGrupo() {
		return fkIdGrupo;
	}

	public void setFkIdGrupo(int fkIdGrupo) {
		this.fkIdGrupo = fkIdGrupo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}

	public String getApellidosAlumno() {
		return apellidosAlumno;
	}

	public void setApellidosAlumno(String apellidosAlumno) {
		this.apellidosAlumno = apellidosAlumno;
	}
	
	
	
}
