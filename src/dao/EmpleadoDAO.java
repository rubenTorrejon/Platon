package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import models.Empleado;

/**
 * Clase que centraliza todas las opciones de conexión de los objetos Empleado
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class EmpleadoDAO extends AbstractDAO{
	
	protected Statement st;
	protected ResultSet rs;
	protected Empleado miEmpleado;
	
	
	
	/**
	 * Constructor por defecto
	 */
	public EmpleadoDAO() {
		super();
		st = null;
		rs=null;
	}
	
	
	
	/**
	 * Método de comprobación que el empleado existe en la BBDD y los parámetros
	 * de user y password coinciden con los registrados
	 * 
	 * @param user
	 * @param pass
	 * @return Empleado
	 */
	public Empleado testLogin(String user, String pass) {
	
		try {
			st = super.con.createStatement();
			rs = st.executeQuery("SELECT * FROM empleado WHERE user='"+user+"' AND password='"+pass+"'");
			if(rs.next()) {
				miEmpleado = new Empleado();
				this.setText();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miEmpleado;
	}
	
	
	
	/**
	 * Método que nos devuelve todos los parámetros de un objeto Empleado consultando
	 * la BBDD una vez pasamos el id_empleado
	 * @param idEmpleado
	 * @return Empleado
	 */
	public Empleado buscarEmpleadoById(int idEmpleado) {
		try {
			st = super.con.createStatement();
			rs = st.executeQuery("SELECT * FROM empleado WHERE id_empleado="+idEmpleado);
			if(rs.next()) {
				miEmpleado = new Empleado();
				this.setText();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miEmpleado;
	}
	
	
	
	/**
	 * Método que obtiene la PK del usuario para entregarla como parámetro a las nuevas ventanas,
	 * una vez obtiene el usuario de la BBDD
	 * @param usuario
	 * @return integer, valor del PK id_empleado
	 */
	public int buscarUsuarioByUsuario(String usuario) {
		int miIdUsuario = 0;
		try {
			st = super.con.createStatement();
			rs = st.executeQuery("SELECT id_empleado FROM empleado WHERE user='"+usuario+"'");
			if(rs.next()) {
				miIdUsuario = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miIdUsuario;
	}
	
	

	/**
	 * Método que añade al la tabla de la BBDD un objeto de tipo empleado, una vez pasados los valores:
	 * @param miUser
	 * @param miPassword
	 * @param miNombre
	 * @param misApellidos
	 * @param miDni
	 * @param miTelefono
	 * @param miDireccion
	 * @param miSueldo
	 * @param miTitulo
	 * @param miRol
	 * @return Boolean, que será true si se ha podido añadir correctamente y false si nos encontramos con algún error.
	 */
	public boolean addEmpleado(String miUser,String miPassword,String miNombre,String misApellidos,String miDni,
			String miTelefono,String miEmail,String miDireccion,int miSueldo,String miTitulo,String miRol) {
		boolean resultado= false;
		PreparedStatement preparedStmt;
		Calendar calendario = Calendar.getInstance();
		String day = Integer.toString(calendario.get(Calendar.DATE));
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		String fecha = year+"-"+month+"-"+day;
		try {
			preparedStmt = super.con.prepareStatement("insert into empleado"
					+ "(user,password,nombre,apellidos,dni,telefono,email,direccion,sueldo,titulo,rol,fecha_alta) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStmt.setString(1,miUser);
			preparedStmt.setString(2,miPassword);
			preparedStmt.setString(3,miNombre);
			preparedStmt.setString(4,misApellidos);
			preparedStmt.setString(5,miDni);
			preparedStmt.setString(6,miTelefono);
			preparedStmt.setString(7,miEmail);			
			preparedStmt.setString(8,miDireccion);
			preparedStmt.setInt(9,miSueldo);
			preparedStmt.setString(10,miTitulo);
			preparedStmt.setString(11,miRol);
			preparedStmt.setDate(12, Date.valueOf(fecha));
			preparedStmt.executeUpdate();
			resultado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return resultado;
	}
	
	
	
	/**
	 * Método que centraliza los setters de los objetos Empleado, para evitar redundancias.
	 */
	private void setText() {
		
		try {
			miEmpleado.setIdEmpleado(rs.getInt(1));
			miEmpleado.setUser(rs.getString(2));
			miEmpleado.setPassword(rs.getString(3));
			miEmpleado.setNombre(rs.getString(4));
			miEmpleado.setApellidos(rs.getString(5));
			miEmpleado.setDni(rs.getString(6));
			miEmpleado.setTelefono(rs.getString(7));
			miEmpleado.setEmail(rs.getString(8));
			miEmpleado.setDireccion(rs.getString(9));
			miEmpleado.setSueldo(rs.getInt(10));
			miEmpleado.setTitulo(rs.getString(11));
			miEmpleado.setRol(rs.getString(12));
			miEmpleado.setFechaAlta(rs.getDate(13));
			miEmpleado.setFechaBaja(rs.getDate(14));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	

	/**
	 * Método que filtra todos los objetos empleados por profesores y no profesores y devuelve a los primeros en una lista
	 * @return ArrayList de empleados, filtrados por rol "profesor"
	 */
	public ArrayList<Empleado> buscarEmpleados(String miNombre,String misApellidos,String miDni,
			String miTelefono,String miDireccion,String miEmail,String miTitulo,String miRol) {
		ArrayList<Empleado> listaEmpleados = null;
		PreparedStatement preparedStmt;
		Empleado empleado;
		String query = "";
		try {
			query = "SELECT * FROM empleado WHERE nombre LIKE ? AND apellidos LIKE ? AND dni LIKE ? "
					+ "AND telefono LIKE ? AND direccion LIKE ? AND email LIKE ? AND titulo LIKE ? "
					+ "AND rol LIKE ? ORDER BY apellidos";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setString(1, "%"+miNombre+"%");
			preparedStmt.setString(2, "%"+misApellidos+"%");
			preparedStmt.setString(3, "%"+miDni+"%");
			preparedStmt.setString(4, "%"+miTelefono+"%");
			preparedStmt.setString(5, "%"+miDireccion+"%");
			preparedStmt.setString(6, "%"+miEmail+"%");
			preparedStmt.setString(7, "%"+miTitulo+"%");
			preparedStmt.setString(8, "%"+miRol+"%");
			rs = preparedStmt.executeQuery();	
			listaEmpleados = new ArrayList<Empleado>();
			while(rs.next()) {
				empleado = new Empleado();
				empleado.setIdEmpleado(rs.getInt(1));
				empleado.setUser(rs.getString(2));
				empleado.setPassword(rs.getString(3));
				empleado.setNombre(rs.getString(4));
				empleado.setApellidos(rs.getString(5));
				empleado.setDni(rs.getString(6));
				empleado.setTelefono(rs.getString(7));
				empleado.setEmail(rs.getString(8));
				empleado.setDireccion(rs.getString(9));
				empleado.setSueldo(rs.getInt(10));
				empleado.setTitulo(rs.getString(11));
				empleado.setRol(rs.getString(12));
				empleado.setFechaAlta(rs.getDate(13));
				empleado.setFechaBaja(rs.getDate(14));
				listaEmpleados.add(empleado);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEmpleados;
	}
	
	
	
	/**
	 * Método que filtra todos los objetos empleados por profesores y no profesores y devuelve a los primeros en una lista
	 * @return ArrayList de empleados, sin filtros
	 */
	public ArrayList<Empleado> buscarEmpleados() {
		ArrayList<Empleado> listaEmpleados = null;
		PreparedStatement preparedStmt;
		Empleado empleado;
		String query = "";
		try {
			query = "SELECT * FROM empleado ORDER BY apellidos";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = preparedStmt.executeQuery();	
			listaEmpleados = new ArrayList<Empleado>();
			while(rs.next()) {
				empleado = new Empleado();
				empleado.setIdEmpleado(rs.getInt(1));
				empleado.setUser(rs.getString(2));
				empleado.setPassword(rs.getString(3));
				empleado.setNombre(rs.getString(4));
				empleado.setApellidos(rs.getString(5));
				empleado.setDni(rs.getString(6));
				empleado.setTelefono(rs.getString(7));
				empleado.setEmail(rs.getString(8));
				empleado.setDireccion(rs.getString(9));
				empleado.setSueldo(rs.getInt(10));
				empleado.setTitulo(rs.getString(11));
				empleado.setRol(rs.getString(12));
				empleado.setFechaAlta(rs.getDate(13));
				empleado.setFechaBaja(rs.getDate(14));
				listaEmpleados.add(empleado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEmpleados;
	}

	
	
	/**
	 * Método que filtra todos los objetos empleados por profesores y no profesores y devuelve a los primeros en una lista
	 * @return ArrayList de empleados, filtrados por rol "profesor"
	 */
	public ArrayList<Empleado> buscarProfesores() {
		PreparedStatement preparedStmt;
		ArrayList<Empleado> listaEmpleados = null;
		Empleado empleado;
		String query = "";
		try {
			query = "SELECT * FROM empleado WHERE rol = 'Profesor' ORDER BY apellidos";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = preparedStmt.executeQuery();	
			listaEmpleados = new ArrayList<Empleado>();
			while(rs.next()) {
				empleado = new Empleado();
				empleado.setIdEmpleado(rs.getInt(1));
				empleado.setUser(rs.getString(2));
				empleado.setPassword(rs.getString(3));
				empleado.setNombre(rs.getString(4));
				empleado.setApellidos(rs.getString(5));
				empleado.setDni(rs.getString(6));
				empleado.setTelefono(rs.getString(7));
				empleado.setEmail(rs.getString(8));
				empleado.setDireccion(rs.getString(9));
				empleado.setSueldo(rs.getInt(10));
				empleado.setTitulo(rs.getString(11));
				empleado.setRol(rs.getString(12));
				empleado.setFechaAlta(rs.getDate(13));
				empleado.setFechaBaja(rs.getDate(14));
				listaEmpleados.add(empleado);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEmpleados;
	}
	

	
	/**
	 * Método que recibe como parámetros 2 valores string correspondientes a 'Nombre' y 'Apellidos', 
	 * consulta la BDD y devuelve el valor al id del empleado que se corresponde con dicho nombre
	 * 
	 * @param miNombre
	 * @param misApellidos
	 * @return int idEmpleado
	 */
	public int buscarEmpleadoPorNombre(String miNombre, String misApellidos) {
		PreparedStatement preparedStmt;
		Empleado empleado = new Empleado();
		try {
			String query = "SELECT id_empleado FROM empleado WHERE nombre = ? AND apellidos = ?";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setString(1,miNombre);
			preparedStmt.setString(2,misApellidos);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				empleado.setIdEmpleado(rs.getInt(1));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empleado.getIdEmpleado();
	}
	
	
	
	/**
	 * Método que modifica los valores de una fila en la tabla empleado, según los parámetros
	 * que se obtienen a través de los TextFields de la ventana
	 * 	
	 * @param miNombre
	 * @param miApellido
	 * @param miDni
	 * @param miTelefono
	 * @param miEmail
	 * @param miDireccion
	 * @param miSueldo
	 * @param miTitulo
	 * @param miRol
	 * @param miFichaEmpleado
	 * @param baja
	 * @return Empleado modificado
	 */
	public Empleado modificarEmpleado(String miNombre,String miApellido,String miDni,String miTelefono,String miEmail,
			String miDireccion,int miSueldo,String miTitulo,String miRol,Empleado miFichaEmpleado,Boolean baja) {
		
		PreparedStatement preparedStmt;
		Calendar calendario = Calendar.getInstance();
		String day = Integer.toString(calendario.get(Calendar.DATE));
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		String fecha = year+"-"+month+"-"+day;
		if(baja) {
			try {
				preparedStmt = super.con.prepareStatement("UPDATE alumno SET fecha_baja = ? WHERE id_alumno = ?");
				preparedStmt.setDate(1, Date.valueOf(fecha));
				preparedStmt.setInt(2, miFichaEmpleado.getIdEmpleado());				
				preparedStmt.executeUpdate();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			preparedStmt = super.con.prepareStatement("UPDATE empleado SET Nombre = ?, Apellidos = ?, DNI = ?, Telefono = ?, "
					+ "email = ?, Direccion = ?, sueldo = ?, titulo = ?, rol = ? where id_empleado = ?");
			preparedStmt.setString(1,miNombre);
			preparedStmt.setString(2,miApellido);
			preparedStmt.setString(3,miDni);
			preparedStmt.setString(4,miTelefono);
			preparedStmt.setString(5,miEmail);
			preparedStmt.setString(6,miDireccion);
			preparedStmt.setInt(7,miSueldo);
			preparedStmt.setString(8,miTitulo);
			preparedStmt.setString(9,miRol);
			preparedStmt.setInt(10, miFichaEmpleado.getIdEmpleado());
			preparedStmt.executeUpdate();
			
			miFichaEmpleado.setNombre(miNombre);
			miFichaEmpleado.setApellidos(miApellido);
			miFichaEmpleado.setDni(miDni);
			miFichaEmpleado.setTelefono(miTelefono);
			miFichaEmpleado.setDireccion(miDireccion);
			miFichaEmpleado.setEmail(miEmail);
			miFichaEmpleado.setSueldo(miSueldo);
			miFichaEmpleado.setTitulo(miTitulo);
			miFichaEmpleado.setRol(miRol);
			miFichaEmpleado.setFechaBaja(Date.valueOf(fecha));
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return miFichaEmpleado;
	}	
}