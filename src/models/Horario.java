package models;

/**
 * Clase que crea objetos de la tabla Horario
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class Horario {

	protected int idHorario;
	protected int fkIdGrupo;
	protected String dia;
	protected String horaInicio;
	protected String horaFinal;
			
	protected String grupo;
	protected String aula;
	
	
	
	/**
	 * Constructor por defecto
	 */
	public Horario() {
		
	}
	
	
	
	/**
	 * Contructor por parámetros
	 * 
	 * @param fkIdGrupo
	 * @param dia
	 * @param horaInicio
	 * @param horaFinal
	 */
	public Horario(int fkIdGrupo, String dia, String horaInicio, String horaFinal) {
		super();
		this.fkIdGrupo = fkIdGrupo;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
	}

	
	
	//Getters y Setters
	
	public int getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public int getFkIdGrupo() {
		return fkIdGrupo;
	}

	public void setFkIdGrupo(int fkIdGrupo) {
		this.fkIdGrupo = fkIdGrupo;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}
	
	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}
	
}
