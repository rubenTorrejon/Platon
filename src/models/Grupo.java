package models;

/**
 * Clase que crea objetos de la tabla Grupo
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class Grupo {
	
	protected int idGrupo;
	protected int fkIdEmpleado;
	protected int fkIdAula;
	protected String nombre;
	protected String mesInicio;
	protected String yearInicio;
	protected String mesFinal;
	protected String yearFinal;
	
	
	
	/**
	 * Constructor por defecto
	 */
	public Grupo() {
	}

	
	
	/**
	 * Contructor por parámetros
	 * 
	 * @param fkIdEmpleado
	 * @param fkIdAula
	 * @param nombre
	 * @param mesInicio
	 * @param yearInicio
	 * @param mesFinal
	 * @param yearFinal
	 */
	public Grupo(int fkIdEmpleado, int fkIdAula, String nombre, String mesInicio, String yearInicio, String mesFinal,
			String yearFinal) {
		super();
		this.fkIdEmpleado = fkIdEmpleado;
		this.fkIdAula = fkIdAula;
		this.nombre = nombre;
		this.mesInicio = mesInicio;
		this.yearInicio = yearInicio;
		this.mesFinal = mesFinal;
		this.yearFinal = yearFinal;
	}

	
	
	//Getters y Setters
	
	public int getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	public int getFkIdEmpleado() {
		return fkIdEmpleado;
	}

	public void setFkIdEmpleado(int fkIdEmpleado) {
		this.fkIdEmpleado = fkIdEmpleado;
	}

	public int getFkIdAula() {
		return fkIdAula;
	}

	public void setFkIdAula(int fkIdAula) {
		this.fkIdAula = fkIdAula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMesInicio() {
		return mesInicio;
	}

	public void setMesInicio(String mesInicio) {
		this.mesInicio = mesInicio;
	}

	public String getYearInicio() {
		return yearInicio;
	}

	public void setYearInicio(String yearInicio) {
		this.yearInicio = yearInicio;
	}

	public String getMesFinal() {
		return mesFinal;
	}

	public void setMesFinal(String mesFinal) {
		this.mesFinal = mesFinal;
	}

	public String getYearFinal() {
		return yearFinal;
	}

	public void setYearFinal(String yearFinal) {
		this.yearFinal = yearFinal;
	}
	
}
