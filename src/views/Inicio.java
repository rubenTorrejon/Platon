package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import models.Empleado;

/**
 * Clase de vista que genera una ventana de inicio, con el inicio de la navegación
 * hacia los principales grupos de ventanas de la app
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
@SuppressWarnings("serial")
public class Inicio extends JFrame implements ActionListener, MouseListener {

	private JPanel JPanel_Cabecera, JPanel_Botones;
	private JButton Btn_Alumnos, Btn_Grupos, Btn_Aulas, Btn_Centros, Btn_Profesores, 
		Btn_Administracion, Btn_Inicio, Btn_Config, Btn_Ayuda, Btn_Logout;
	private JLabel lblLogo, lblUser, lblAlumnos, lblProfesores, lblGrupos, lblCentros, 
		lblAulas, lblAdministracion;
	private ImageIcon iconoAlumnos, iconoProfesores, iconoGrupos, iconoCentros, iconoAulas, 
		iconoAdministracion, iconoConfig, iconoAyuda, iconoInicio, iconoLogout;
	private Empleado miEmpleado;	


	
	/**
	 * Constructor por defecto de la ventana Login
	 */
	public Inicio(Empleado miEmpleado) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		this.miEmpleado = miEmpleado;
		initialize();
	}



	/**
	 * Contrucción gráfica de la ventana
	 */
	private void initialize() {
		this.setBounds(0, 0, 1240, 720);
		this.setTitle("PLATON - INICIO");
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
		JPanel_Cabecera.add(Btn_Inicio);
		JPanel_Cabecera.add(Btn_Config);
		JPanel_Cabecera.add(Btn_Ayuda);
		JPanel_Cabecera.add(Btn_Logout);
		JPanel_Botones.add(Btn_Alumnos);
		JPanel_Botones.add(Btn_Profesores);
		JPanel_Botones.add(Btn_Grupos);
		JPanel_Botones.add(Btn_Centros);
		JPanel_Botones.add(Btn_Aulas);
		JPanel_Botones.add(Btn_Administracion);
		JPanel_Botones.add(lblAlumnos);
		JPanel_Botones.add(lblProfesores);
		JPanel_Botones.add(lblGrupos);
		JPanel_Botones.add(lblCentros);
		JPanel_Botones.add(lblAulas);
		JPanel_Botones.add(lblAdministracion);
		Btn_Alumnos.setIcon(iconoAlumnos);
		Btn_Profesores.setIcon(iconoProfesores);
		Btn_Grupos.setIcon(iconoGrupos);
		Btn_Centros.setIcon(iconoCentros);
		Btn_Aulas.setIcon(iconoAulas);
		Btn_Administracion.setIcon(iconoAdministracion);
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
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		Btn_Config.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Ayuda.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Inicio.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Logout.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Alumnos.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Profesores.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Grupos.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Centros.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Aulas.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Administracion.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		lblAlumnos.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblProfesores.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblGrupos.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblCentros.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAulas.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAdministracion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	}



	/**
	 * Método para posicionar de forma estática y con valores fijos los componentes dentro
	 * de cada ventana. Establece una cuaterna de píxeles para cada componente.
	 */
	private void posicionarComponentes() {
		JPanel_Cabecera.setBounds(0, 0, 1234, 175);
		JPanel_Botones.setBounds(0, 175, 1224, 506);
		lblUser.setBounds(458, 18, 753, 35);
		Btn_Logout.setBounds(1161, 64, 50, 50);
		Btn_Ayuda.setBounds(1041, 64, 50, 50);
		Btn_Inicio.setBounds(1101, 64, 50, 50);
		Btn_Config.setBounds(981, 64, 50, 50);
		Btn_Alumnos.setBounds(10, 50, 350, 200);		
		Btn_Grupos.setBounds(865, 50, 350, 200);
		Btn_Aulas.setBounds(435, 295, 350, 200);
		Btn_Centros.setBounds(10, 295, 350, 200);
		Btn_Profesores.setBounds(435, 50, 350, 200);
		Btn_Administracion.setBounds(865, 295, 350, 200);
		lblAlumnos.setBounds(10, 5, 350, 45);
		lblProfesores.setBounds(435, 5, 350, 45);
		lblGrupos.setBounds(865, 5, 350, 45);
		lblCentros.setBounds(10, 250, 350, 45);
		lblAulas.setBounds(435, 250, 350, 45);
		lblAdministracion.setBounds(865, 250, 350, 45);
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
		lblProfesores.setForeground(Color.WHITE);
		lblGrupos.setForeground(Color.WHITE);
		lblCentros.setForeground(Color.WHITE);
		lblAulas.setForeground(Color.WHITE);
		lblAdministracion.setForeground(Color.WHITE);
		Btn_Logout.setBackground(Color.RED);
	}
		
	
	
	/**
	 * Método para asignar propiedades específicas a compoentes en la ventana
	 */
	private void establecerLayouts() {
		JPanel_Botones.setLayout(null);
		JPanel_Cabecera.setLayout(null);
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfesores.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentros.setHorizontalAlignment(SwingConstants.CENTER);
		lblAulas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministracion.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Config.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Ayuda.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Inicio.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Logout.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Alumnos.addActionListener(this);
		Btn_Grupos.addActionListener(this);
		Btn_Aulas.addActionListener(this);
		Btn_Centros.addActionListener(this);
		Btn_Profesores.addActionListener(this);
		Btn_Administracion.addActionListener(this);
		Btn_Ayuda.addActionListener(this);
		Btn_Inicio.addActionListener(this);
		Btn_Logout.addMouseListener(this);
		Btn_Config.addActionListener(this);
		Btn_Alumnos.setFocusPainted(false);
		Btn_Grupos.setFocusPainted(false);
		Btn_Aulas.setFocusPainted(false);
		Btn_Centros.setFocusPainted(false);
		Btn_Profesores.setFocusPainted(false);
		Btn_Administracion.setFocusPainted(false);
		Btn_Config.setFocusPainted(false);
		Btn_Ayuda.setFocusPainted(false);
		Btn_Inicio.setFocusPainted(false);
		Btn_Logout.setFocusPainted(false);
		Btn_Config.setVisible(false);
		Btn_Administracion.setVisible(false);
		lblAdministracion.setVisible(false);
	}

	
	
	/**
	 * Método que inicializa y da valores de texto a cada componente
	 */
	private void iniciarComponentes() {
		JPanel_Cabecera = new JPanel();
		JPanel_Botones = new JPanel();
		Btn_Alumnos = new JButton("Alumnos");
		Btn_Aulas = new JButton("Aulas");
		Btn_Grupos = new JButton("Grupos");
		Btn_Centros = new JButton("Centros");
		Btn_Profesores = new JButton("Profesores");
		Btn_Administracion = new JButton("Administracion");
		Btn_Config = new JButton("Config");
		Btn_Inicio = new JButton("Inicio");
		Btn_Inicio.setEnabled(false);
		Btn_Ayuda = new JButton("Ayuda");
		Btn_Logout = new JButton("Logout"); 
		lblLogo = new JLabel(new ImageIcon("assets/logoVentana.png"));
		lblUser = new JLabel(miEmpleado.getNombre()+" "+miEmpleado.getApellidos());
		lblAlumnos = new JLabel("Alumnos");
		lblProfesores = new JLabel("Profesores");
		lblGrupos = new JLabel("Grupos");
		lblCentros = new JLabel("Centros");
		lblAulas = new JLabel("Aulas");
		lblAdministracion = new JLabel("Administración");
		iconoAlumnos = new ImageIcon("assets/LogoAlumnos.png");
		iconoProfesores = new ImageIcon("assets/LogoProfesores.png");
		iconoGrupos = new ImageIcon("assets/LogoGrupos.png");
		iconoCentros = new ImageIcon("assets/LogoCentros.png");
		iconoAulas = new ImageIcon("assets/LogoAulas.png");
		iconoAdministracion = new ImageIcon("assets/LogoAdministracion.png");
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
		VAlumnoGenerico ventanaAlumno;
		VProfesorGenerico ventanaProfesor;
		VGrupoGenerico ventanaGrupo;
		VCentroGenerico ventanaCentro;
		VAulaGenerico ventanaAula;
		//VAdministracionGenerico ventanaAdministracion;
		String txtBtn = e.getActionCommand();
		switch (txtBtn) {
		case "Alumnos":
			this.setVisible(false);
			this.dispose();
			ventanaAlumno = new VAlumnoGenerico(miEmpleado);
			break;
		case "Profesores":
			this.setVisible(false);
			this.dispose();
			ventanaProfesor = new VProfesorGenerico(miEmpleado);
			break;
		case "Grupos":
			this.setVisible(false);
			this.dispose();
			ventanaGrupo = new VGrupoGenerico(miEmpleado);
			break;
		case "Centros":
			this.setVisible(false);
			this.dispose();
			ventanaCentro = new VCentroGenerico(miEmpleado);
			break;
		case "Aulas":
			this.setVisible(false);
			this.dispose();
			ventanaAula = new VAulaGenerico(miEmpleado);
			break;
		case "Ayuda":
			try
			{
				Process p = Runtime.getRuntime().exec ("rundll32 SHELL32.DLL,ShellExec_RunDLL "+"C:\\Users\\rtorrejo\\eclipse-workspace\\Platon\\assets\\user_manual.pdf");
			} catch (Exception e1) {
			}
			break;
//		case "Administracion":
//			this.setVisible(false);
//			this.dispose();
//			ventanaAdministracion = new VAdministracionGenerico(miEmpleado);
//			break;
		}
	}

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
