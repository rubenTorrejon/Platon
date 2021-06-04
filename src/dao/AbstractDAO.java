package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import common.Constant;

/**
 * Método común a todas las clases que conectan con la BDD
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
abstract class AbstractDAO {
	
	//ESTADO
	protected Connection con;
	
	/**
	 * Constructor
	 */
	public AbstractDAO() {
		ConectarBD();
	}
	
	/**
	 * Método para conectarse a la BBDD en local 
	 */
	private void ConectarBD() {
		try {
			Class.forName(Constant.CONTROLADOR);
			this.con = DriverManager.getConnection(Constant.URL, Constant.USUARIO, Constant.CLAVE);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}