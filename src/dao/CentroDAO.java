package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Centro;

/**
 * Clase que centraliza todas las opciones de conexión de los objetos Centro
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class CentroDAO extends AbstractDAO{
	
	protected Statement st;
	protected ResultSet rs;
	protected Centro miCentro;
	
	
	
	/**
	 * Constructor por defecto
	 */
	public CentroDAO() {
		super();
		st = null;
		rs = null;
	}

	

	/**
	 * Método que devuelve el objeto Centro al que corresponde un id concreto
	 * 
	 * @param idCentro
	 * @return Centro
	 */
	public Centro buscarCentroById(int idCentro) {
		try {
			st = super.con.createStatement();
			rs = st.executeQuery("SELECT * FROM centro WHERE id_centro="+idCentro);
			if(rs.next()) {
				miCentro = new Centro();
				this.setText();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miCentro;
	}
	
	

	/**
	 * Método que recoge el nombre de un centro, consulta si existe dicho nombre en la BDD y devuelve
	 * el id del centro que se corresponde con ese nombre
	 * 
 	 * @param nombre
	 * @return int idCentro
	 */
	public int buscarCentroByNombre(String nombre) {
		int miIdUsuario = 0;
		try {
			st = super.con.createStatement();
			rs = st.executeQuery("SELECT id_centro FROM centro WHERE nombre='"+nombre+"'");
			if(rs.next()) {
				miIdUsuario = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miIdUsuario;
	}
	
	
	
	/**
	 * Método que registra una nueva fila en la tabla Centro de la BDD, utilizando como valores los parámetros
	 * obtenidos a través de los TextFields
	 * 
	 * @param miNombre
	 * @param miTelefono
	 * @param miDireccion
	 * @param misObservaciones
	 * @return
	 */
	public boolean addCentro(String miNombre,String miTelefono,String miDireccion,String misObservaciones) {
		boolean resultado= false;
		PreparedStatement preparedStmt;
		try {
			preparedStmt = super.con.prepareStatement("insert into centro"
					+ "(nombre,telefono,direccion,observaciones) values(?,?,?,?)");
			preparedStmt.setString(1,miNombre);
			preparedStmt.setString(2,miTelefono);
			preparedStmt.setString(3,miDireccion);
			preparedStmt.setString(4,misObservaciones);
			preparedStmt.executeUpdate();
			resultado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return resultado;
	}
	
	
	
	/**
	 * Método que centraliza los setters de los objetos Centro, para evitar redundancias.
	 */
	private void setText() {
		try {
			miCentro.setIdCentro(rs.getInt(1));
			miCentro.setNombre(rs.getString(2));
			miCentro.setTelefono(rs.getString(3));
			miCentro.setDireccion(rs.getString(4));
			miCentro.setObservaciones(rs.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	/**
	 * Método que busca todos los centros registrados en la BDD, según los parámetros de búsqueda
	 * que se obtienen a través de los TextFields
	 * 
	 * @param miNombre
	 * @param miTelefono
	 * @param miDireccion
	 * @return ArrayList de centros
	 */
	public ArrayList<Centro> buscarCentros(String miNombre,String miTelefono,String miDireccion) {
		ArrayList<Centro> listaCentros = new ArrayList<Centro>();
		PreparedStatement preparedStmt;
		Centro centro;
		String query = "";
		try {
			query = "SELECT * FROM centro "
					+ "WHERE nombre LIKE ? "
					+ "AND telefono LIKE ? "
					+ "AND direccion LIKE ? ";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setString(1, "%"+miNombre+"%");
			preparedStmt.setString(2, "%"+miTelefono+"%");
			preparedStmt.setString(3, "%"+miDireccion+"%");
			rs = preparedStmt.executeQuery();	
			while(rs.next()) {
				centro = new Centro();
				centro.setIdCentro(rs.getInt(1));
				centro.setNombre(rs.getString(2));
				centro.setTelefono(rs.getString(3));
				centro.setDireccion(rs.getString(4));
				centro.setObservaciones(rs.getString(5));
				listaCentros.add(centro);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCentros;
	}
	
	
	
	/**
	 * Método que busca todos los centros registrados en la BDD, sin filtrados
	 * 
	 * @return ArrayList de centros
	 */
	public ArrayList<Centro> buscarCentros() {
		ArrayList<Centro> listaCentros = null;
		PreparedStatement preparedStmt;
		Centro centro;
		String query = "";
		try {
			query = "SELECT * FROM centro";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = preparedStmt.executeQuery();	
			listaCentros = new ArrayList<Centro>();
			while(rs.next()) {
				centro = new Centro();
				centro.setIdCentro(rs.getInt(1));
				centro.setNombre(rs.getString(2));
				centro.setTelefono(rs.getString(3));
				centro.setDireccion(rs.getString(4));
				centro.setObservaciones(rs.getString(5));
				listaCentros.add(centro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCentros;
	}
	
	
	
	/**
	 * Método que modifica los valores de una fila existente en la tabla Centros de la BDD, utilizando como
	 * nuevos valores los parámetros obtenidos en los TextFields
	 * 
	 * @param miNombre
	 * @param miTelefono
	 * @param miDireccion
	 * @param miObservacion
	 * @return Centro modificado
	 */
	public Centro modificarCentro(String miNombre,String miTelefono,String miDireccion,String miObservacion, Centro miCentro) {
		PreparedStatement preparedStmt;
		try {
			preparedStmt = super.con.prepareStatement("UPDATE centro SET "
					+ "Nombre = ?,"
					+ "Telefono = ?,"
					+ "Direccion = ?,"
					+ "Observaciones = ? "
					+ "WHERE id_centro = ?");
			preparedStmt.setString(1,miNombre);
			preparedStmt.setString(2,miTelefono);
			preparedStmt.setString(3,miDireccion);
			preparedStmt.setString(4,miObservacion);
			preparedStmt.setInt(5, miCentro.getIdCentro());
			preparedStmt.executeUpdate();
			
			miCentro.setNombre(miNombre);
			miCentro.setTelefono(miTelefono);
			miCentro.setDireccion(miDireccion);
			miCentro.setObservaciones(miObservacion);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return miCentro;
	}	
}