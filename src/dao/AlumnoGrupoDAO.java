package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import models.AlumnoGrupo;

/**
 * Clase que centraliza todas las opciones de conexión de los objetos AlumnoGrupo
 * 
 * @author Rubén Torrejón
 *
 */
public class AlumnoGrupoDAO extends AbstractDAO{
	
	protected Statement st;
	protected ResultSet rs;
	protected AlumnoGrupo miAlumnoGrupo;
	
	
	
	/**
	 * Constructor
	 */
	public AlumnoGrupoDAO() {
		super();
		st = null;
		rs=null;
	}

	
	
	/**
	 * Método que registra una nueva fila en la tabla AlumnoGrupo de la BDD
	 * 
	 * @param miAlumno
	 * @param miGrupo
	 */
	public boolean addAlumnoGrupo(int miAlumno,int miGrupo) {
		boolean resultado= false;
		PreparedStatement preparedStmt;
		Calendar calendario = Calendar.getInstance();
		String day = Integer.toString(calendario.get(Calendar.DATE));
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		String fecha = year+"-"+month+"-"+day;
		
		try {
			preparedStmt = super.con.prepareStatement("INSERT INTO alumno_grupo "
					+ "(fk_id_alumno,fk_id_grupo,fecha_inicio) "
					+ "values(?,?,?)");
			preparedStmt.setInt(1,miAlumno);
			preparedStmt.setInt(2,miGrupo);
			preparedStmt.setDate(3,Date.valueOf(fecha));
			preparedStmt.executeUpdate();
			resultado = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return resultado;
	}
	
	
	
	/**
	 * Método que elimina un alumno de un grupo. Este método no borra el registro de la BDD, sino que
	 * le asigna un valor a la columna fecha_fin, con lo que el alumno deja de aparecer como alumno
	 * registrado en el grupo.
	 * 
	 * @param miAlumno
	 * @param miGrupo
	 */
	public boolean quitarAlumnoGrupo(int miAlumno,int miGrupo) {
		
		boolean resultado= false;
		PreparedStatement preparedStmt;
		Calendar calendario = Calendar.getInstance();
		String day = Integer.toString(calendario.get(Calendar.DATE));
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		String fecha = year+"-"+month+"-"+day;
		
		try {
			preparedStmt = super.con.prepareStatement("update alumno_grupo set fecha_fin = ? where fk_id_alumno = ? and fk_id_grupo = ?");
			
			preparedStmt.setDate(1,Date.valueOf(fecha));
			preparedStmt.setInt(2,miAlumno);
			preparedStmt.setInt(3,miGrupo);
			
			preparedStmt.executeUpdate();
			resultado = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return resultado;
	}
	
	
	
	/**
	 * Método que comprueba si un alumno se encuentra activo en un grupo, de cara a evitar que se matricule un alumno ya matriculado
	 * 
	 * @param miAlumno
	 * @param miGrupo
	 * @return true or false
	 */
	public boolean estaEnGrupo(int miAlumno, int miGrupo) {
		Boolean alumnoEnGrupo = false;
		PreparedStatement preparedStmt;
		try {
			st = super.con.createStatement();
			preparedStmt = super.con.prepareStatement("SELECT * FROM alumno_grupo WHERE fk_id_alumno = ? AND fk_id_grupo = ? AND fecha_fin IS NULL");
			preparedStmt.setInt(1,miAlumno);
			preparedStmt.setInt(2,miGrupo);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				alumnoEnGrupo = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alumnoEnGrupo;
	}
	
	
	
	/**
	 * Método que devuelve una lista de los alumnos que están actualmente activos en un grupo.
	 * 
	 * @param idGrupo
	 * @return ArrayList de alumnos
	 */
	public ArrayList<AlumnoGrupo> buscarAlumnosActivosPorGrupo(int idGrupo) {
		PreparedStatement preparedStmt;
		ArrayList<AlumnoGrupo> listaAlumnos = new ArrayList<AlumnoGrupo>();
		AlumnoGrupo miAlumnoGrupo;
		try {
			st = super.con.createStatement();
			preparedStmt = super.con.prepareStatement("SELECT alumno.nombre,alumno.apellidos,fecha_inicio "
					+ "FROM alumno,alumno_grupo WHERE id_alumno = fk_id_alumno AND fk_id_grupo = ? AND fecha_fin IS NULL");
			preparedStmt.setInt(1,idGrupo);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				miAlumnoGrupo = new AlumnoGrupo();
				
				miAlumnoGrupo.setNombreAlumno(rs.getString(1));
				miAlumnoGrupo.setApellidosAlumno(rs.getString(2));
				miAlumnoGrupo.setFechaInicio(rs.getDate(3));
				
				listaAlumnos.add(miAlumnoGrupo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlumnos;
	}
	
	
	
	/**
	 * Método que busca todos los alumnos que han estado registrados en un grupo, sin filtrar si siguen activos en dicho
	 * grupo o ya han cursado baja.
	 * 
	 * @param idGrupo
	 * @return
	 */
	public ArrayList<AlumnoGrupo> buscarHistoricoAlumnosPorGrupo(int idGrupo) {
		PreparedStatement preparedStmt;
		ArrayList<AlumnoGrupo> listaAlumnos = new ArrayList<AlumnoGrupo>();
		AlumnoGrupo miAlumnoGrupo;
		try {
			st = super.con.createStatement();
			preparedStmt = super.con.prepareStatement("SELECT * FROM alumno,alumno_grupo WHERE id_alumno = fk_id_alumno AND fk_id_grupo = ?");
			preparedStmt.setInt(1,idGrupo);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				miAlumnoGrupo = new AlumnoGrupo();
				
				miAlumnoGrupo.setNombreAlumno(rs.getString(1));
				miAlumnoGrupo.setApellidosAlumno(rs.getString(2));
				miAlumnoGrupo.setFechaInicio(rs.getDate(3));
				miAlumnoGrupo.setFechaFin(rs.getDate(4));
				
				listaAlumnos.add(miAlumnoGrupo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlumnos;
	}
	
	
	
	/**
	 * Método que calcula las plazas libres que quedan en un grupo. Este método recoge como parámetro el id del grupo y
	 * accede a la lista de alumnos activos y al aula donde se imparte. Compara el tamaño de la lista de alumnos activos
	 * y el valor de capacidad del aula.
	 * 
	 * @param idGrupo
	 * @return
	 */
	public String plazasLibres(int idGrupo) {
		PreparedStatement preparedStmt;
		int plazasOcupadas = 0;
		int plazasTotales = 0;
		
		try {
			String query = "SELECT capacidad FROM grupo,aula WHERE id_aula = fk_id_aula AND id_grupo = ?";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setInt(1,idGrupo);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				plazasTotales = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			String query = "SELECT COUNT(fk_id_alumno) FROM alumno_grupo WHERE fk_id_grupo = ? AND fecha_fin IS NULL GROUP BY fk_id_grupo;";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setInt(1,idGrupo);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				plazasOcupadas = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (plazasTotales - plazasOcupadas)+" plazas";
	}
}