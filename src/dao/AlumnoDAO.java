package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import models.Alumno;

/**
 * Clase que centraliza todas las opciones de conexión de los objetos Alumno
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class AlumnoDAO extends AbstractDAO{
	
	protected Statement st;
	protected ResultSet rs;
	protected Alumno miAlumno;
	
	
	
	/**
	 * Constructor
	 */
	public AlumnoDAO() {
		super();
		st = null;
		rs=null;
	}
	
	
	
	/**
	 * Método que nos devuelve todos los parámetros de un objeto Empleado consultando
	 * la BBDD una vez pasamos el id_empleado
	 * @param idEmpleado
	 * @return Empleado
	 */
	public Alumno buscarAlumnoPorId(int idEmpleado) {
		try {
			st = super.con.createStatement();
			rs = st.executeQuery("SELECT * FROM alumno WHERE id_alumno="+idEmpleado);
			if(rs.next()) {
				miAlumno = new Alumno();
				this.setText();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miAlumno;
	}
	
	/**
	 * Método que, recibiendo como parámetro el Id de un grupo, devuelve la lista de todos
	 * los alumnos que han estado matriculados en dicho grupo.
	 * 
	 * @param idGrupo
	 * @return
	 */
	public ArrayList<Alumno> buscarAlumnoPorGrupo(int idGrupo) {
		PreparedStatement preparedStmt;
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		try {
			st = super.con.createStatement();
			preparedStmt = super.con.prepareStatement("SELECT alumno.* FROM alumno,alumno_grupo WHERE id_alumno = fk_id_alumno AND fk_id_grupo = ?");
			preparedStmt.setInt(1,idGrupo);
			rs = preparedStmt.executeQuery();	
			while(rs.next()) {
				miAlumno = new Alumno();
				setText();
				listaAlumnos.add(miAlumno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlumnos;
	}
	
	/**
	 * Comportamiento que recibe los parámetros 'Nombre' y 'Apellidos' como cadenas de texto y devuelve el 
	 * objeto Alumno cuyos nombre y apellidos coinciden.
	 * 
	 * @param miNombre
	 * @param misApellidos
	 * @return
	 */
	public Alumno buscarAlumnoPorNombre(String miNombre, String misApellidos) {
		PreparedStatement preparedStmt;
		try {
			st = super.con.createStatement();
			preparedStmt = super.con.prepareStatement("SELECT * FROM alumno WHERE nombre = ? AND apellidos = ?");
			preparedStmt.setString(1,miNombre);
			preparedStmt.setString(2,misApellidos);
			rs = preparedStmt.executeQuery();
			if(rs.next()) {
				miAlumno = new Alumno();
				this.setText();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return miAlumno;
	}
	
	/**
	 * Comportamiento que comprueba si un alumno ya existe en la BDD y está activo. Este comportamiento filtra de forma
	 * que, si un DNI no existe en la BDD devuelve true. Si existe, comprueba que el alumno no haya sido dado de baja anteriormente.
	 * Si es así, devuelve true. En cualquier otro caso, devuelve false
	 * 
	 * @param Dni
	 * @return
	 */
	public boolean alumnoExiste(String Dni) {
		boolean alumnoEnBaseDatos = false;
		PreparedStatement preparedStmt;
		try {
			st = super.con.createStatement();
			preparedStmt = super.con.prepareStatement("SELECT * FROM alumno WHERE dni = ? AND fecha_baja IS NULL");
			preparedStmt.setString(1,Dni);
			rs = preparedStmt.executeQuery();
			if(rs.next()) {
				alumnoEnBaseDatos = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return alumnoEnBaseDatos;
	}
	

	
	/**
	 * Comportamiento que registra un alumno en la BDD. Este comportamiento recibe todos los valores anotados en los textFields
	 * de la ventana de registro y genera una fila nueva.
	 * 
	 * Este comportamiento concreto se aplica cuando se recibe una fecha de nacimiento válida
	 * 
	 * @param miNombre
	 * @param misApellidos
	 * @param miDni
	 * @param miTelefono
	 * @param miDireccion
	 * @param miEmail
	 * @param miFechaNacimiento
	 * @param miResponsableNombre
	 * @param miResponsableDni
	 * @param miResponsableTelefono
	 * @param miResponsableEmail
	 * @return
	 */
	public boolean addAlumno(
			String miNombre,String misApellidos,String miDni,String miTelefono,String miDireccion,String miEmail,	
			Date miFechaNacimiento,String miResponsableNombre,String miResponsableDni,String miResponsableTelefono, 
			String miResponsableEmail) {
		
		boolean resultado = false;
		PreparedStatement preparedStmt;
		Calendar calendario = Calendar.getInstance();
		String day = Integer.toString(calendario.get(Calendar.DATE));
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		String fecha = year+"-"+month+"-"+day;
		
		try {
			preparedStmt = super.con.prepareStatement("INSERT INTO alumno"
					+ "(nombre,apellidos,dni,telefono,direccion,email,fecha_nacimiento,"
					+ "responsable_nombre,responsable_dni,responsable_telefono, responsable_email, fecha_alta) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			
			preparedStmt.setString(1,miNombre);
			preparedStmt.setString(2,misApellidos);
			preparedStmt.setString(3,miDni);
			preparedStmt.setString(4,miTelefono);
			preparedStmt.setString(5,miDireccion);
			preparedStmt.setString(6,miEmail);
			preparedStmt.setDate(7,(Date) miFechaNacimiento);
			preparedStmt.setString(8,miResponsableNombre);
			preparedStmt.setString(9,miResponsableDni);
			preparedStmt.setString(10,miResponsableTelefono);
			preparedStmt.setString(11,miResponsableEmail);
			preparedStmt.setDate(12, Date.valueOf(fecha));
			
			preparedStmt.executeUpdate();
			resultado = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	
	/**
	 * Comportamiento que registra un alumno en la BDD. Este comportamiento recibe todos los valores anotados en los textFields
	 * de la ventana de registro y genera una fila nueva.
	 * 
	 * Este comportamiento concreto se aplica cuando NO se recibe una fecha de nacimiento válida
	 * 
	 * @param miNombre
	 * @param misApellidos
	 * @param miDni
	 * @param miTelefono
	 * @param miDireccion
	 * @param miEmail
	 * @param miResponsableNombre
	 * @param miResponsableDni
	 * @param miResponsableTelefono
	 * @param miResponsableEmail
	 * @return
	 */
	public boolean addAlumno(
			String miNombre,String misApellidos,String miDni,String miTelefono,String miDireccion,String miEmail,
			String miResponsableNombre,String miResponsableDni,String miResponsableTelefono,String miResponsableEmail) {
		
		boolean resultado = false;
		PreparedStatement preparedStmt;
		Calendar calendario = Calendar.getInstance();
		String day = Integer.toString(calendario.get(Calendar.DATE));
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		String fecha = year+"-"+month+"-"+day;
		try {
			preparedStmt = super.con.prepareStatement("INSERT INTO alumno (nombre,apellidos,dni,telefono,direccion,email,"
					+ "responsable_nombre,responsable_dni,responsable_telefono, responsable_email, fecha_alta) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			
			preparedStmt.setString(1,miNombre);
			preparedStmt.setString(2,misApellidos);
			preparedStmt.setString(3,miDni);
			preparedStmt.setString(4,miTelefono);
			preparedStmt.setString(5,miDireccion);
			preparedStmt.setString(6,miEmail);
			preparedStmt.setString(7,miResponsableNombre);
			preparedStmt.setString(8,miResponsableDni);
			preparedStmt.setString(9,miResponsableTelefono);
			preparedStmt.setString(10,miResponsableEmail);
			preparedStmt.setDate(11, Date.valueOf(fecha));
			
			preparedStmt.executeUpdate();
			resultado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	
	/**
	 * Método que centraliza los setters de los objetos Alumno, para evitar redundancias.
	 */
	private void setText() {
		
		try {
			miAlumno.setIdAlumno(rs.getInt(1));
			miAlumno.setNombre(rs.getString(2));
			miAlumno.setApellidos(rs.getString(3));
			miAlumno.setDni(rs.getString(4));
			miAlumno.setTelefono(rs.getString(5));
			miAlumno.setDireccion(rs.getString(6));
			miAlumno.setEmail(rs.getString(7));
			miAlumno.setFechaNacimiento(rs.getDate(8));
			miAlumno.setResponsableNombre(rs.getString(9));
			miAlumno.setResponsableDni(rs.getString(10));
			miAlumno.setResponsableTelefono(rs.getString(11));
			miAlumno.setResponsableEmail(rs.getString(12));
			miAlumno.setFechaAlta(rs.getDate(13));
			miAlumno.setFechaBaja(rs.getDate(14));	
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}	

	

	/**
	 * Método que devuelve una lista con todos los alumnos registrados en la base de datos según parámetros 
	 * de búsqueda.
	 * 
	 * @return ArrayList de alumnos
	 */
	public ArrayList<Alumno> buscarAlumnos(
			String miNombre,String misApellidos,String miDni,String miTelefono,String miDireccion,
			String miEmail,String miResponsableNombre,String miResponsableDni,String miResponsableTelefono,
			String miResponsableEmail) {
		
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		PreparedStatement preparedStmt;
		String query = "";

		try {
			query = "SELECT * FROM Alumno "
					+ "WHERE nombre LIKE ? "
					+ "AND apellidos LIKE ? "
					+ "AND dni LIKE ? "
					+ "AND telefono LIKE ? "
					+ "AND direccion LIKE ? "
					+ "AND email LIKE ? "
					+ "AND responsable_nombre LIKE ? "
					+ "AND responsable_dni LIKE ? "
					+ "AND responsable_telefono LIKE ? "
					+ "AND responsable_email LIKE ? "
					+ "ORDER BY apellidos";
			
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			preparedStmt.setString(1, "%"+miNombre+"%");
			preparedStmt.setString(2, "%"+misApellidos+"%");
			preparedStmt.setString(3, "%"+miDni+"%");
			preparedStmt.setString(4, "%"+miTelefono+"%");
			preparedStmt.setString(5, "%"+miDireccion+"%");
			preparedStmt.setString(6, "%"+miEmail+"%");
			preparedStmt.setString(7, "%"+miResponsableNombre+"%");
			preparedStmt.setString(8, "%"+miResponsableDni+"%");
			preparedStmt.setString(9, "%"+miResponsableTelefono+"%");
			preparedStmt.setString(10,"%"+miResponsableEmail+"%");
	
			rs = preparedStmt.executeQuery();	
			 
			while(rs.next()) {
				miAlumno = new Alumno();
				setText();
				listaAlumnos.add(miAlumno);
			}	
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlumnos;
	}
			
	
	
	/**
	 * Método que busca la totalidad de alumnos registrados en la BDD sin realizar ningún tipo de filtro.
	 * 
	 * @return ArryList de alumnos.
	 */
	public ArrayList<Alumno> buscarAlumnos() {
		ArrayList<Alumno> listaAlumnos = null;
		PreparedStatement preparedStmt;
		String query = "";
		try {
			query = "SELECT * FROM Alumno ORDER BY apellidos";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = preparedStmt.executeQuery();	
			listaAlumnos = new ArrayList<Alumno>();
			while(rs.next()) {
				miAlumno = new Alumno();
				setText();
				listaAlumnos.add(miAlumno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlumnos;
	}
	

	
	/**
	 * Método que busca la totalidad de alumnos registrados en la BDD filtrando aquellos que NO tienen
	 * asignada una fecha de baja en la academia.
	 * 
	 * @return ArryList de alumnos.
	 */
	public ArrayList<Alumno> buscarAlumnosActivos() {
		ArrayList<Alumno> listaAlumnos = null;
		PreparedStatement preparedStmt;
		String query = "";

		try {
			query = "SELECT * FROM Alumno WHERE fecha_baja IS NULL ORDER BY apellidos";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = preparedStmt.executeQuery();	
			listaAlumnos = new ArrayList<Alumno>();
			while(rs.next()) {
				miAlumno = new Alumno();
				setText();
				listaAlumnos.add(miAlumno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlumnos;
	}
	
	
	
	/**
	 * Método que busca la totalidad de alumnos registrados en la BDD filtrando aquellos que tienen
	 * asignada una fecha de baja en la academia.
	 * 
	 * @return ArryList de alumnos.
	 */
	public ArrayList<Alumno> buscarAlumnosInactivos() {
		ArrayList<Alumno> listaAlumnos = null;
		PreparedStatement preparedStmt;
		String query = "";

		try {
			query = "SELECT * FROM Alumno WHERE fecha_baja IS NOT NULL ORDER BY apellidos";
			preparedStmt = super.con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = preparedStmt.executeQuery();	
			listaAlumnos = new ArrayList<Alumno>();
			while(rs.next()) {
				miAlumno = new Alumno();
				setText();
				listaAlumnos.add(miAlumno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAlumnos;
	}
	
	
	
	/**
	 * Método que modifica una fila registrada en la BDD y establece como nuevos parámetros aquellos que hemos introducido
	 * en los TextFields de la ventana de ficha.
	 * 
	 * @param miNombre
	 * @param miApellido
	 * @param miDni
	 * @param miTelefono
	 * @param miDireccion
	 * @param miEmail
	 * @param miFechaNacimiento
	 * @param miResponsableNombre
	 * @param miResponsableDni
	 * @param miResponsableTelefono
	 * @param miResponsableEmail
	 * @param miAlumno
	 * @param baja
	 * @return
	 */
	public Alumno modificarAlumno(
			String miNombre,String miApellido,String miDni,String miTelefono,String miDireccion,String miEmail,Date miFechaNacimiento,
			String miResponsableNombre,String miResponsableDni,String miResponsableTelefono,String miResponsableEmail,Alumno miAlumno,
			Boolean baja) {
		
		Calendar calendario = Calendar.getInstance();
		String day = Integer.toString(calendario.get(Calendar.DATE));
		String month = Integer.toString(calendario.get(Calendar.MONTH)+1);
		String year = Integer.toString(calendario.get(Calendar.YEAR));
		String fecha = year+"-"+month+"-"+day;

		PreparedStatement preparedStmt;
		
		if(baja) {
			try {
				preparedStmt = super.con.prepareStatement("UPDATE alumno SET fecha_baja = ? WHERE id_alumno = ?");
				preparedStmt.setDate(1, Date.valueOf(fecha));
				preparedStmt.setInt(2, miAlumno.getIdAlumno());
				preparedStmt.executeUpdate();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			preparedStmt = super.con.prepareStatement("UPDATE alumno SET "
					+ "Nombre = ?,Apellidos = ?,DNI = ?,Telefono = ?,Direccion = ?,email = ?,fecha_nacimiento = ?,"
					+ "responsable_nombre = ?,responsable_dni = ?,responsable_telefono = ?,responsable_email = ? "
					+ "where id_alumno = ?");
			
			preparedStmt.setString(1,miNombre);
			preparedStmt.setString(2,miApellido);
			preparedStmt.setString(3,miDni);
			preparedStmt.setString(4,miTelefono);
			preparedStmt.setString(5,miDireccion);
			preparedStmt.setString(6,miEmail);
			preparedStmt.setDate(7,miFechaNacimiento);
			preparedStmt.setString(8,miResponsableNombre);
			preparedStmt.setString(9,miResponsableDni);
			preparedStmt.setString(10,miResponsableTelefono);
			preparedStmt.setString(11,miResponsableEmail);
			preparedStmt.setInt(12, miAlumno.getIdAlumno());
			preparedStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return miAlumno;
	}
	
}