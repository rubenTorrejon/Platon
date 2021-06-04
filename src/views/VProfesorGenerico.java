package views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import dao.EmpleadoDAO;
import models.Empleado;

/**
 * Clase de vista que genera una ventana genérica de profesor, donde podremos continuar la navegacion
 * hacia todas las opciones del profesor.
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
@SuppressWarnings("serial")
public class VProfesorGenerico extends JFrame implements ActionListener, MouseListener{

	private JPanel JPanel_Cabecera, JPanel_Botones;
	private JButton Btn_ProfesorNuevo, Btn_Actividad, Btn_BuscarProfesor, 
		Btn_Volver, Btn_Inicio, Btn_Config, Btn_Ayuda, Btn_Logout;
	private JLabel lblLogo, lblUser, lblAlumnos;
	private ImageIcon iconoConfig, iconoAyuda, iconoInicio, iconoLogout;
	
	private Empleado miEmpleado;	


	
	/**
	 * Constructor por defecto de la ventana Login
	 */
	public VProfesorGenerico(Empleado miEmpleado) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		this.miEmpleado = miEmpleado;
		initialize();
	}



	/**
	 * Contrucción gráfica de la ventana
	 */
	private void initialize() {
		this.setBounds(0, 0, 1240, 720);
		this.setTitle("PLATON - PROFESORES");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		getContentPane().setLayout(null);		
		this.iniciarComponentes();
		this.establecerLayouts();
		this.establecerColores();
		this.posicionarComponentes();
		this.establecerFuentes();
		this.incluirComponentes();		
		this.setVisible(true);
	}


	
	/**
	 * Método que establece el árbol de jerarquía de los componentes, indicando
	 * cuáles deben añadirse gráficamente dentro de otros.
	 */
	private void incluirComponentes() {
		getContentPane().add(JPanel_Botones);
		getContentPane().add(JPanel_Cabecera);		
		JPanel_Cabecera.add(lblLogo);
		JPanel_Cabecera.add(lblUser);
		JPanel_Cabecera.add(lblAlumnos);
		JPanel_Cabecera.add(Btn_Inicio);
		JPanel_Cabecera.add(Btn_Config);
		JPanel_Cabecera.add(Btn_Ayuda);
		JPanel_Cabecera.add(Btn_Logout);		
		JPanel_Botones.add(Btn_ProfesorNuevo);
		JPanel_Botones.add(Btn_Actividad);
		JPanel_Botones.add(Btn_Volver);
		JPanel_Botones.add(Btn_BuscarProfesor);
		Btn_Logout.setIcon(iconoLogout);
		Btn_Config.setIcon(iconoConfig);
		Btn_Ayuda.setIcon(iconoAyuda);
		Btn_Inicio.setIcon(iconoInicio);
	}



	/**
	 * Método que establece las propiedades de las fuentes de los componentes escritos
	 * dentro de la ventana. Estas propiedades indican el tipo de fuente, tamaño, color
	 * y modificadores tales como negrita o cursiva.
	 */
	private void establecerFuentes() {
		Btn_BuscarProfesor.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Btn_Config.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Ayuda.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Inicio.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Logout.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_ProfesorNuevo.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Btn_Actividad.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Btn_Volver.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAlumnos.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAlumnos.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	}



	/**
	 * Método para posicionar de forma estática y con valores fijos los componentes dentro
	 * de cada ventana. Establece una cuaterna de píxeles para cada componente.
	 */
	private void posicionarComponentes() {
		JPanel_Cabecera.setBounds(0, 0, 1224, 175);
		JPanel_Botones.setBounds(0, 175, 1224, 506);
		lblUser.setBounds(881, 18, 330, 35);
		Btn_Logout.setBounds(1161, 64, 50, 50);
		Btn_Ayuda.setBounds(1041, 64, 50, 50);
		Btn_Inicio.setBounds(1101, 64, 50, 50);
		Btn_Config.setBounds(981, 64, 50, 50);
		Btn_BuscarProfesor.setBounds(400, 50, 300, 150);		
		Btn_ProfesorNuevo.setBounds(50, 50, 300, 150);
		Btn_Actividad.setBounds(50, 250, 300, 150);
		Btn_Volver.setBounds(1065, 420, 150, 75);		
		lblAlumnos.setBounds(861, 119, 350, 45);
		lblLogo.setBounds(0, 0, 618, 175);
	}



	/**
	 * Método para asignar a cada componente visual de la ventana el color de fondo
	 */
	private void establecerColores() {
		JPanel_Cabecera.setBackground(new java.awt.Color(27, 38, 79));
		JPanel_Botones.setBackground(new java.awt.Color(27, 38, 79));		
		lblUser.setForeground(Color.WHITE);
		lblAlumnos.setForeground(Color.WHITE);
		lblAlumnos.setForeground(Color.WHITE);		
		Btn_Logout.setBackground(Color.RED);
		Btn_Volver.setBackground(new java.awt.Color(87, 108, 168));
	}
		
	
	
	/**
	 * Método para asignar propiedades específicas a compoentes en la ventana
	 */
	private void establecerLayouts() {
		JPanel_Botones.setLayout(null);
		JPanel_Cabecera.setLayout(null);		
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlumnos.setHorizontalAlignment(SwingConstants.RIGHT);
		Btn_Config.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Ayuda.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Inicio.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Logout.setHorizontalAlignment(SwingConstants.CENTER);		
		Btn_ProfesorNuevo.addActionListener(this);
		Btn_Actividad.addActionListener(this);
		Btn_Volver.addActionListener(this);
		Btn_Ayuda.addActionListener(this);
		Btn_Inicio.addActionListener(this);
		Btn_Logout.addMouseListener(this);
		Btn_Config.addActionListener(this);
		Btn_BuscarProfesor.addActionListener(this);
		Btn_ProfesorNuevo.setFocusPainted(false);
		Btn_Actividad.setFocusPainted(false);
		Btn_Volver.setFocusPainted(false);
		Btn_Config.setFocusPainted(false);
		Btn_Ayuda.setFocusPainted(false);
		Btn_Inicio.setFocusPainted(false);
		Btn_Logout.setFocusPainted(false);
		Btn_BuscarProfesor.setFocusPainted(false);		
		Btn_Config.setVisible(false);
	}

	
	
	/**
	 * Método que inicializa y da valores de texto a cada componente
	 */
	private void iniciarComponentes() {
		JPanel_Cabecera = new JPanel();
		JPanel_Botones = new JPanel();		
		Btn_ProfesorNuevo = new JButton("PROFESOR NUEVO");
		Btn_Actividad = new JButton("ACTIVIDAD");
		Btn_BuscarProfesor = new JButton("BUSCAR PROFESOR");
		Btn_Volver = new JButton("Volver");
		Btn_Volver.setForeground(Color.WHITE);		
		Btn_Config = new JButton("Config");
		Btn_Inicio = new JButton("Inicio");
		Btn_Ayuda = new JButton("Ayuda");
		Btn_Logout = new JButton("Logout");		
		lblLogo = new JLabel(new ImageIcon("assets/logoVentana.png"));
		lblUser = new JLabel(miEmpleado.getNombre()+" "+miEmpleado.getApellidos());
		lblAlumnos = new JLabel("Profesores");
		iconoConfig = new ImageIcon("assets/LogoConfig.png");
		iconoAyuda = new ImageIcon("assets/LogoAyuda.png");
		iconoInicio = new ImageIcon("assets/LogoInicio.png");
		iconoLogout = new ImageIcon("assets/LogoLogout.png");
	}



	/**
	 * Método que registra la actividad de los botones y realiza acciones según
	 * el botón seleccionado y el escuchador asignado al mismo
	 */
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		VProfesorNuevo profesorNuevo;
		VProfesorBuscarTodos profesorBuscar;
		VProfesorActividad profesorActividad;
		Inicio ventanaInicio;		
		String txtBtn = e.getActionCommand();		
		switch (txtBtn) {
		case "PROFESOR NUEVO":
			this.setVisible(false);
			this.dispose();
			profesorNuevo = new VProfesorNuevo(miEmpleado);
			break;
		case "BUSCAR PROFESOR":
			this.setVisible(false);
			this.dispose();
			EmpleadoDAO miEmpleadoDao = new EmpleadoDAO();
			ArrayList<Empleado> listaEmpleados = miEmpleadoDao.buscarEmpleados();
			profesorBuscar = new VProfesorBuscarTodos(miEmpleado, listaEmpleados);
			break;
		case "ACTIVIDAD":
			this.setVisible(false);
			this.dispose();
			profesorActividad = new VProfesorActividad(miEmpleado);
			break;
		case "Inicio":
			this.setVisible(false);
			this.dispose();
			ventanaInicio = new Inicio(miEmpleado);
			break;
		case "Ayuda":
			try
			{
				Process p = Runtime.getRuntime().exec ("rundll32 SHELL32.DLL,ShellExec_RunDLL "+"C:\\Users\\rtorrejo\\eclipse-workspace\\Platon\\assets\\user_manual.pdf");
			} catch (Exception e1) {
			}
			break;
		case "Volver":
			this.setVisible(false);
			this.dispose();
			ventanaInicio = new Inicio(miEmpleado);
			break;
		}
	}

	
	
	/**
	 * Método que registra la actividad de los botones y realiza acciones según
	 * el botón seleccionado y el escuchador asignado al mismo
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		@SuppressWarnings("unused")
		Login nuevoLogin;
		this.setVisible(false);
		this.dispose();
		miEmpleado = null;
		nuevoLogin = new Login();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {	}

	@Override
	public void mouseExited(MouseEvent arg0) { }

	@Override
	public void mousePressed(MouseEvent arg0) {	}

	@Override
	public void mouseReleased(MouseEvent arg0) { }
}