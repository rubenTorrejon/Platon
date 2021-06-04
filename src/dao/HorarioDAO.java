package dao;

import java.sql.*;
import java.util.ArrayList;

import models.Horario;

/**
 * Clase que centraliza todas las opciones de conexión de los objetos Alumno
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class HorarioDAO extends AbstractDAO{
	
	protected Statement st;
	protected ResultSet rs;
	protected Horario miHorario;
	
	
	
	/**
	 * Constructor por defecto
	 */
	public HorarioDAO() {
		super();
		st = null;
		rs=null;
	}
	
	
	
	/**
	 * Método que nos devuelve todos los parámetros de un objeto Horario consultando
	 * la BBDD una vez pasamos el id_horario
	 * 
	 * @param idHorario
	 * @return Horario
	 */
	public Horario buscarHorarioPorId(int idHorario) {
		try {
			st = super.con.createStatement();
			rs = st.executeQuery("SELECT * FROM horario WHERE id_horario in (-1,"+idHorario+")");
			if(rs.next()) {
				miHorario = new Horario();
				this.setText();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miHorario;
	}
	
	
	
	/**
	 * Método que centraliza los setters de los objetos Empleado, para evitar redundancias.
	 */
	private void setText() {
		try {
			miHorario.setFkIdGrupo(rs.getInt(1));
			miHorario.setDia(rs.getString(2));
			miHorario.setHoraInicio(rs.getString(3));
			miHorario.setHoraFinal(rs.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}	


	
	/**
	 * Método que añade un horario en la BDD según los valores añadidos a los TextFields en la ventana
	 * 
	 * @param miGrupo
	 * @param miDia
	 * @param miHoraInicio
	 * @param miHoraFinal
	 * @return
	 */
	public boolean addHorario(int miGrupo,String miDia,String miHoraInicio,String miHoraFinal) {
		boolean resultado = false;
		PreparedStatement preparedStmt;
		try {
			preparedStmt = super.con.prepareStatement("INSERT INTO horario"
					+ "(fk_id_grupo,dia,hora_inicio,hora_final) VALUES (?,?,?,?)");
			preparedStmt.setInt(1,miGrupo);
			preparedStmt.setString(2,miDia);
			preparedStmt.setString(3,miHoraInicio);
			preparedStmt.setString(4,miHoraFinal);
			preparedStmt.executeUpdate();
			resultado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	
	/**
	 * Método que reinicia los valores de Horario de un grupo, eliminando dichos registros de la BDD
	 * 
	 * @param idGrupo
	 * @return
	 */
	public boolean borrarHorarioDeGrupo(int idGrupo) {
		boolean resultado = false;
		PreparedStatement preparedStmt;
		try {
			preparedStmt = super.con.prepareStatement("DELETE FROM horario WHERE fk_id_grupo in (?,-1)");
			preparedStmt.setInt(1,idGrupo);
			preparedStmt.executeUpdate();
			resultado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	
	/**
	 * Método que asigna un horario provisional a un grupo obtenido por parámetro
	 * 
	 * @param idGrupo
	 */
	public void horarioDefinitivo(int idGrupo) {	
		PreparedStatement preparedStmt;
		try {
			preparedStmt = super.con.prepareStatement("UPDATE horario SET "
					+ "fk_id_grupo = ? WHERE fk_id_grupo = -1");
			preparedStmt.setInt(1,idGrupo);
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	
	/**
	 * Método que devuelve todos los grupos con sus respectivos valores de dia, horas y aula
	 * a los que debe asistir un alumno, una vez obtenemos por parámetro el id del alumno
	 * 
	 * @param idAlumno
	 * @return ArrayList de horarios
	 */
	public ArrayList<Horario> buscarActividad(int idAlumno) {
		ArrayList<Horario> listaHorarios = new ArrayList<Horario>();
		PreparedStatement preparedStmt;
		Horario horario;
		String query = "";
		try {
			query = "SELECT grupo.nombre, dia, hora_inicio, hora_final, aula.nombre "
					+ "FROM alumno, alumno_grupo, grupo, horario, aula "
					+ "WHERE id_grupo = horario.fk_id_grupo "
					+ "AND id_grupo = alumno_grupo.fk_id_grupo "
					+ "AND id_alumno = fk_id_alumno "
					+ "AND id_aula = fk_id_aula "
					+ "AND id_alumno = ?";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setInt(1,idAlumno);
			rs = preparedStmt.executeQuery();	
			while(rs.next()) {
				horario = new Horario();
				horario.setGrupo(rs.getString(1));
				horario.setDia(rs.getString(2));
				horario.setHoraInicio(rs.getString(3));
				horario.setHoraFinal(rs.getString(4));
				horario.setAula(rs.getString(5));
				listaHorarios.add(horario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaHorarios;
	}	
	
	
	
	/**
	 * Método que devuelve todos los grupos con sus respectivos valores de dia, horas y aula
	 * a los que debe asistir un profesor, una vez obtenemos por parámetro el id del profesor
	 * 
	 * @param idProfesor
	 * @return ArrayList de horarios
	 */
	public ArrayList<Horario> buscarActividadProfesor(int idProfesor) {
		ArrayList<Horario> listaHorarios = new ArrayList<Horario>();
		PreparedStatement preparedStmt;
		Horario horario;
		String query = "";

		try {
			query = "SELECT grupo.nombre,horario.dia,hora_inicio,hora_final,aula.nombre "
					+ "FROM grupo, horario, empleado, aula, enum_dias "
					+ "WHERE id_aula = fk_id_aula and id_grupo = fk_id_grupo "
					+ "AND id_empleado = fk_id_empleado "
					+ "AND enum_dias.dia = horario.dia "
					+ "AND id_empleado = ? "
					+ "ORDER BY id_dia, hora_inicio;";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setInt(1,idProfesor);
			rs = preparedStmt.executeQuery();	
			
			while(rs.next()) {
				horario = new Horario();
				
				horario.setGrupo(rs.getString(1));
				horario.setDia(rs.getString(2));
				horario.setHoraInicio(rs.getString(3));
				horario.setHoraFinal(rs.getString(4));
				horario.setAula(rs.getString(5));
				
				listaHorarios.add(horario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaHorarios;
	}	
	
	
	
	/**
	 * Método que guarda los valores de horario que vamos guardando en la ventana de grupos, de cara
	 * a que una vez registrado el grupo se le asignen todos los valores que están en la tabla de
	 * horarios provisionales
	 * 
	 * @param miDia
	 * @param miHoraInicio
	 * @param miHoraFinal
	 * @return
	 */
	public boolean addHorarioProvisional(String miDia,String miHoraInicio,String miHoraFinal) {
		boolean resultado = false;
		PreparedStatement preparedStmt;
		try {
			preparedStmt = super.con.prepareStatement("INSERT INTO horario"
					+ "(fk_id_grupo,dia,hora_inicio,hora_final) VALUES (?,?,?,?)");
			preparedStmt.setInt(1,-1);
			preparedStmt.setString(2,miDia);
			preparedStmt.setString(3,miHoraInicio);
			preparedStmt.setString(4,miHoraFinal);
			preparedStmt.executeUpdate();
			resultado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	
	/**
	 * Método que elimina los registros en el grupo provisional si se pulsa cualquier botón diferente a
	 * guardar grupo.
	 * 
	 * @return
	 */
	public boolean eliminarProvisionales() {
		boolean resultado = false;
		PreparedStatement preparedStmt;
		try {
			preparedStmt = super.con.prepareStatement("DELETE FROM horario WHERE fk_id_grupo = -1");
			preparedStmt.executeUpdate();
			resultado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	
	/**
	 * Método que devuelve todos los horarios que pertenecen a un grupo concreto, cuyo id se obtiene
	 * por parámetro.
	 * 
	 * @param idGrupo
	 * @return ArrayList de horarios.
	 */
	public ArrayList<Horario> buscarHorarios(int idGrupo) {
		ArrayList<Horario> listaHorarios = new ArrayList<Horario>();
		PreparedStatement preparedStmt;
		Horario horario;
		String query = "";
		try {
			query = "SELECT * FROM horario WHERE fk_id_grupo in (-1,?)";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setInt(1,idGrupo);
			rs = preparedStmt.executeQuery();	
			while(rs.next()) {
				horario = new Horario();
				horario.setIdHorario(rs.getInt(1));
				horario.setFkIdGrupo(rs.getInt(2));
				horario.setDia(rs.getString(3));
				horario.setHoraInicio(rs.getString(4));
				horario.setHoraFinal(rs.getString(5));
				listaHorarios.add(horario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaHorarios;
	}	
	
	
	
	/**
	 * Método que obtiene el id de un aula y devuelve una lista de horarios con su respectivo grupo
	 * que pertenecen a dicho aula
	 * 
	 * @param idAula
	 * @return ArrayList de horarios
	 */
	public ArrayList<Horario> buscarDisponibilidad(int idAula) {
		ArrayList<Horario> listaHorarios = new ArrayList<Horario>();
		PreparedStatement preparedStmt;
		Horario horario;
		String query = "";
		try {
			query = "SELECT g.nombre,dia,hora_inicio,hora_final FROM aula a, grupo g, horario h "
					+ "WHERE a.id_aula = g.fk_id_aula AND g.id_grupo = h.fk_id_grupo AND id_aula = ?";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setInt(1,idAula);
			rs = preparedStmt.executeQuery();	
			while(rs.next()) {
				horario = new Horario();
				horario.setGrupo(rs.getString(1));
				horario.setDia(rs.getString(2));
				horario.setHoraInicio(rs.getString(3));
				horario.setHoraFinal(rs.getString(4));
				listaHorarios.add(horario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaHorarios;
	}	
	
	
	
	/**
	 * Método que, una vez obtenido el id de un grupo por parámetro, devuelve todos los horarios correspondientes
	 * a dicho grupo
	 * 
	 * @param idGrupo
	 * @return
	 */
	public ArrayList<Horario> buscarHorariosPorGrupo(int idGrupo) {
		ArrayList<Horario> listaHorarios = new ArrayList<Horario>();
		PreparedStatement preparedStmt;
		Horario horario;
		String query = "";
		try {
			query = "SELECT * FROM horario WHERE fk_id_grupo = ?";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setInt(1,idGrupo);
			rs = preparedStmt.executeQuery();	
			while(rs.next()) {
				horario = new Horario();
				horario.setIdHorario(rs.getInt(1));
				horario.setFkIdGrupo(rs.getInt(2));
				horario.setDia(rs.getString(3));
				horario.setHoraInicio(rs.getString(4));
				horario.setHoraFinal(rs.getString(5));
				listaHorarios.add(horario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaHorarios;
	}	
}