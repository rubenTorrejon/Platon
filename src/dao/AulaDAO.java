package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Aula;

/**
 * Clase que centraliza todas las opciones de conexión de los objetos Aula
 * 
 * @author Rubén Torrejón
 *
 */
public class AulaDAO extends AbstractDAO{
	
	protected Statement st;
	protected ResultSet rs;
	protected Aula miAula;
	
	
	
	/**
	 * Constructor
	 */
	public AulaDAO() {
		super();
		st = null;
		rs=null;
	}

	
	
	/**
	 * Método que nos devuelve todos los parámetros de un objeto Aula consultando
	 * la BBDD una vez pasamos el id del aula
	 * 
	 * @param idAula
	 * @return Aula
	 */
	public Aula buscarAulaById(int idAula) {
		try {
			st = super.con.createStatement();
			rs = st.executeQuery("SELECT * FROM aula WHERE id_aula = "+idAula);
			if(rs.next()) {
				miAula = new Aula();
				miAula.setIdAula(rs.getInt(1));
				miAula.setFkIdCentro(rs.getInt(2));
				miAula.setNombre(rs.getString(3));
				miAula.setCapacidad(rs.getInt(4));
				miAula.setComentarios(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miAula;
	}

	
	
	/**
	 * Método que recoge el nombre de un aula, consulta si existe dicho nombre en la BDD y devuelve
	 * el id del aula que se corresponde con ese nombre
	 * 
 	 * @param nombre
	 * @return int idAula
	 */
	public int buscarAulaPorNombre(String nombre) {
		PreparedStatement preparedStmt;
		Aula aula = new Aula();
		try {
			String query = "SELECT id_aula FROM aula WHERE nombre = ?";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setString(1,nombre);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				aula.setIdAula(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aula.getIdAula();
	}
	
	
	
	/**
	 * Método que añade un aula en la BDD según los valores añadidos a los TextFields en la ventana
	 * 
	 * @param miIdCentro
	 * @param miNombre
	 * @param miCapacidad
	 * @param misComentarios
	 * @return
	 */
	public boolean addAula(int miIdCentro,String miNombre,int miCapacidad,String misComentarios) {
		
		boolean resultado= false;
		PreparedStatement preparedStmt;
		
		try {
			preparedStmt = super.con.prepareStatement("INSERT INTO aula"
					+ "(fk_id_centro, nombre, capacidad, comentarios) values(?,?,?,?)");
			
			preparedStmt.setInt(1,miIdCentro);
			preparedStmt.setString(2, miNombre);
			preparedStmt.setInt(3,miCapacidad);
			preparedStmt.setString(4,misComentarios);
			
			preparedStmt.executeUpdate();
			resultado = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return resultado;
	}
	
	

	/**
	 * Método que busca todas las aulas que coinciden con una serie de valores
	 * de búsqueda pasados a través de los TextFields de la ventana
	 * 
	 * @param miCentro
	 * @param miNombre
	 * @param miCapacidad
	 * @param miComentario
	 * @return ArrayList de aulas
	 */
	public ArrayList<Aula> buscarAulas(int miCentro,String miNombre,int miCapacidad,String miComentario) {
		ArrayList<Aula> listaAulas = new ArrayList<Aula>();
		PreparedStatement preparedStmt;
		Aula aula;
		String query = "";
		try {
			query = "SELECT * FROM Aula WHERE fk_id_centro LIKE ? AND nombre LIKE ? AND capacidad LIKE ? AND comentarios LIKE ? ";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setString(1, "%"+miCentro+"%");
			preparedStmt.setString(2, "%"+miNombre+"%");
			preparedStmt.setString(3, "%"+miCapacidad+"%");
			preparedStmt.setString(4, "%"+miComentario+"%");
			rs = preparedStmt.executeQuery();	
			while(rs.next()) {				
				aula = new Aula();
				aula.setIdAula(rs.getInt(1));
				aula.setFkIdCentro(rs.getInt(2));
				aula.setNombre(rs.getString(3));
				aula.setCapacidad(rs.getInt(4));
				aula.setComentarios(rs.getString(5));
				listaAulas.add(aula);
			}		 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAulas;
	}
	
	
	
	/**
	 * Método que busca todas las aulas registradas en la BDD, sin filtrados
	 * 
	 * @return ArrayList de aulas.
	 */
	public ArrayList<Aula> buscarAulas() {
		
		ArrayList<Aula> listaAulas = new ArrayList<Aula>();
		PreparedStatement preparedStmt;
		Aula aula;
		String query = "";

		try {
			query = "select centro.nombre, aula.nombre, capacidad, aula.comentarios from centro,aula where id_centro = fk_id_centro";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = preparedStmt.executeQuery();	
			while(rs.next()) {
				aula = new Aula();
				
				aula.setCentro(rs.getString(1));
				aula.setNombre(rs.getString(2));
				aula.setCapacidad(rs.getInt(3));
				aula.setComentarios(rs.getString(4));
				
				listaAulas.add(aula);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAulas;
	}
			
	
	
	/**
	 * Método que modifica los valores de una fila en la tabla aulas, según los parámetros
	 * que se obtienen a través de los TextFields de la ventana
	 * 
	 * @param miFkIdCentro
	 * @param miNombre
	 * @param miCapacidad
	 * @param miComentario
	 * @return Aula modificada
	 */
	public Aula modificarAula(int miFkIdCentro,String miNombre,int miCapacidad,String miComentario, Aula miAula) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = super.con.prepareStatement("UPDATE aula SET "
					+ "fk_id_centro = ?,"
					+ "Nombre = ?,"
					+ "capacidad = ?,"
					+ "comentarios = ? "
					+ "WHERE id_aula = ?");
			preparedStmt.setInt(1,miFkIdCentro);
			preparedStmt.setString(2,miNombre);
			preparedStmt.setInt(3,miCapacidad);
			preparedStmt.setString(4,miComentario);
			preparedStmt.setInt(5, miAula.getIdAula());
			preparedStmt.executeUpdate();
			
			miAula.setFkIdCentro(miFkIdCentro);
			miAula.setNombre(miNombre);
			miAula.setCapacidad(miCapacidad);
			miAula.setComentarios(miComentario);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return miAula;
	}
}