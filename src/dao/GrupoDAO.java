package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Grupo;

/**
 * Clase que centraliza todas las opciones de conexión de los objetos Grupo
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class GrupoDAO extends AbstractDAO{
	
	protected Statement st;
	protected ResultSet rs;
	protected Grupo miGrupo;
	
	
	/**
	 * Constructor por defecto
	 */
	public GrupoDAO() {
		super();
		st = null;
		rs=null;
	}
	
	
	
	/**
	 * Método que nos devuelve todos los parámetros de un objeto Grupo consultando
	 * la BBDD una vez pasamos el id_empleado
	 * @param idEmpleado
	 * @return Empleado
	 */
	public Grupo buscarGrupoPorId(int idGrupo) {
		try {
			st = super.con.createStatement();
			rs = st.executeQuery("SELECT * FROM grupo WHERE id_grupo="+idGrupo);
			if(rs.next()) {
				miGrupo = new Grupo();
				this.setText();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miGrupo;
	}
	
	
	
	/**
	 * Método que centraliza los setters de los objetos Grupo, para evitar redundancias.
	 */
	private void setText() {
		try {
			miGrupo.setIdGrupo(rs.getInt(1));
			miGrupo.setFkIdEmpleado(rs.getInt(2));
			miGrupo.setFkIdAula(rs.getInt(3));
			miGrupo.setNombre(rs.getString(4));
			miGrupo.setMesInicio(rs.getString(5));
			miGrupo.setYearInicio(rs.getString(6));
			miGrupo.setMesFinal(rs.getString(7));
			miGrupo.setYearFinal(rs.getString(8));	
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	/**
	 * Método que recibe el valor de idGrupo y realiza una consulta cruzada para obtener el valor
	 * del nombre del aula en la que se imparte dicho grupo.
	 * 
	 * @param idGrupo
	 * @return String nombre del aula
	 */
	public String buscarAulaPorGrupo(int idGrupo) {
		PreparedStatement preparedStmt;
		String nombreAula = "";
		try {
			String query = "SELECT aula.nombre FROM aula,grupo WHERE id_aula=fk_id_aula AND id_grupo = ?";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setInt(1,idGrupo);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				nombreAula = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombreAula;
	}

	
	
	/**
	 * Método que recibe un parámetro string, busca en la BDD si algún registro tiene en la columna
	 * nombre el valor del parámetro y, en caso que así sea, devuelve un int correspondiente al id del grupo
	 * 
	 * @param nombre
	 * @return int idGrupo
	 */
	public int buscarGrupoPorNombre(String nombre) {
		PreparedStatement preparedStmt;
		Grupo grupo = new Grupo();
		try {
			String query = "SELECT id_grupo FROM grupo WHERE nombre = ?";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setString(1,nombre);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				grupo.setIdGrupo(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grupo.getIdGrupo();
	}
	
	
	
	/**
	 * Método que añade un grupo en la BDD según los valores añadidos a los TextFields en la ventana
	 * 
	 * @param fkIdEmpleado
	 * @param fkIdAula
	 * @param nombre
	 * @param mesInicio
	 * @param yearInicio
	 * @param mesFinal
	 * @param yearFinal
	 * @return
	 */
	public boolean addGrupo(int fkIdEmpleado,int fkIdAula,String nombre,String mesInicio,
			String yearInicio,String mesFinal,String yearFinal) {
		boolean resultado = false;
		PreparedStatement preparedStmt;
		try {
			preparedStmt = super.con.prepareStatement("INSERT INTO grupo"
					+ "(fk_id_empleado,fk_id_aula,nombre,mes_inicio,year_inicio,mes_final,year_final)"
					+ "VALUES (?,?,?,?,?,?,?)");
			preparedStmt.setInt(1,fkIdEmpleado);
			preparedStmt.setInt(2,fkIdAula);
			preparedStmt.setString(3,nombre);
			preparedStmt.setString(4,mesInicio);
			preparedStmt.setString(5,yearInicio);
			preparedStmt.setString(6,mesFinal);
			preparedStmt.setString(7,yearFinal);
			preparedStmt.executeUpdate();
			resultado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}


	
	/**
	 * Método que devuelve una lista con todos los grupos registrados en la base de datos
	 * 
	 * @return ArrayList de alumnos
	 */
	public ArrayList<Grupo> buscarGrupos(
			int fkIdEmpleado,int fkIdAula,String nombre,String mesInicio,
			String yearInicio,String mesFinal,String yearFinal) {
		
		ArrayList<Grupo> listaGrupos = new ArrayList<Grupo>();
		PreparedStatement preparedStmt;
		Grupo grupo;
		String query = "";
		try {
			query = "SELECT * FROM grupo WHERE fk_id_empleado = ? AND fk_id_aula = ? AND nombre LIKE ? "
					+ "AND mes_inicio LIKE ? AND year_inicio LIKE ? AND mes_final LIKE ? AND year_final LIKE ? "
					+ "AND id_grupo <> -1 ORDER BY nombre";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setString(1, "%"+fkIdEmpleado+"%");
			preparedStmt.setString(2, "%"+fkIdAula+"%");
			preparedStmt.setString(3, "%"+nombre+"%");
			preparedStmt.setString(4, "%"+mesInicio+"%");
			preparedStmt.setString(5, "%"+yearInicio+"%");
			preparedStmt.setString(6, "%"+mesFinal+"%");
			preparedStmt.setString(7, "%"+yearFinal+"%");	
			rs = preparedStmt.executeQuery();			
			while(rs.next()) {
				grupo = new Grupo();
				grupo.setIdGrupo(rs.getInt(1));
				grupo.setFkIdEmpleado(rs.getInt(2));
				grupo.setFkIdAula(rs.getInt(3));
				grupo.setNombre(rs.getString(4));
				grupo.setMesInicio(rs.getString(5));
				grupo.setYearInicio(rs.getString(6));
				grupo.setMesFinal(rs.getString(7));
				grupo.setYearFinal(rs.getString(8));
				listaGrupos.add(grupo);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaGrupos;
	}
			
	
	
	/**
	 * Método que busca todos los grupos en la BDD, sin ningún parámetro de filtrado
	 * 
	 * @return ArrayList de grupos
	 */
	public ArrayList<Grupo> buscarGrupos() {
		ArrayList<Grupo> listaGrupos = new ArrayList<Grupo>();
		PreparedStatement preparedStmt;
		Grupo grupo;
		String query = "";
		try {
			query = "SELECT * FROM grupo WHERE id_grupo <> -1 ORDER BY nombre";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = preparedStmt.executeQuery();	
			while(rs.next()) {
				grupo = new Grupo();
				grupo.setIdGrupo(rs.getInt(1));
				grupo.setFkIdEmpleado(rs.getInt(2));
				grupo.setFkIdAula(rs.getInt(3));
				grupo.setNombre(rs.getString(4));
				grupo.setMesInicio(rs.getString(5));
				grupo.setYearInicio(rs.getString(6));
				grupo.setMesFinal(rs.getString(7));
				grupo.setYearFinal(rs.getString(8));
				listaGrupos.add(grupo);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaGrupos;
	}
	
	
	
	/**
	 * Método que modifica los valores de una fila en la tabla grupos, según los parámetros
	 * que se obtienen a través de los TextFields de la ventana
	 * 
	 * @param fkIdEmpleado
	 * @param fkIdAula
	 * @param nombre
	 * @param mesInicio
	 * @param yearInicio
	 * @param mesFinal
	 * @param yearFinal
	 * @param miGrupo
	 * @return Grupo modificado
	 */
	public Grupo modificarGrupo(
			int fkIdEmpleado,int fkIdAula,String nombre,String mesInicio,
			String yearInicio,String mesFinal,String yearFinal,Grupo miGrupo) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = super.con.prepareStatement("UPDATE grupo SET "
					+ "fk_id_empleado = ?,fk_id_aula = ?,nombre = ?,mes_inicio = ?, "
					+ "year_inicio = ?,mes_final = ?,year_final = ? WHERE id_grupo = ?");
			preparedStmt.setInt(1,fkIdEmpleado);
			preparedStmt.setInt(2,fkIdAula);
			preparedStmt.setString(3,nombre);
			preparedStmt.setString(4,mesInicio);
			preparedStmt.setString(5,yearInicio);
			preparedStmt.setString(6,mesFinal);
			preparedStmt.setString(7,yearFinal);
			preparedStmt.setInt(8,miGrupo.getIdGrupo());
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return miGrupo;
	}
}