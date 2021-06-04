package views;

import javax.swing.*;

import common.RellenadorComboBox;

import java.awt.*;
import java.awt.event.*;
import dao.AulaDAO;
import dao.CentroDAO;
import models.Aula;
import models.Empleado;

/**
 * Clase de vista que genera una ventana de aula nueva, donde podremos registrar
 * un aula en la BDD
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
@SuppressWarnings("serial")
public class VAulaNueva extends JFrame implements ActionListener, MouseListener{

	private JPanel JPanel_Cabecera, JPanel_Botones, Jpanel_Formulario;
	private JButton Btn_Logout, Btn_Inicio, Btn_Config, Btn_Ayuda, 
		Btn_Limpiar, Btn_Registrar, Btn_Volver, Btn_Modificar;
	private JLabel lblLogo, lblUser, lblAlumnos;
	private JLabel lbl_Form_0, lbl_Form_1, lbl_Form_2, lbl_Form_3;
	private ImageIcon iconoConfig, iconoAyuda, iconoInicio, iconoLogout;
	private JTextField TF_Nombre, TF_Comentarios;
	private JComboBox<String> CB_Centro, CB_Capacidad;

	private Empleado miEmpleado;
	private Aula miAula;
	private AulaDAO miAulaDao = new AulaDAO();
	private CentroDAO miCentroDao = new CentroDAO();
	private RellenadorComboBox rellenador = new RellenadorComboBox();
    
	
    
	/**
	 * Constructor por defecto
	 * 
	 * @wbp.parser.constructor
	 */
	public VAulaNueva(Empleado miEmpleado) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		this.miEmpleado = miEmpleado;
		initialize();
	}
	
	
	
	/**
	 * Constructor con parámetros
	 * 
	 * @param miEmpleado
	 * @param miAula
	 */
	public VAulaNueva(Empleado miEmpleado, Aula miAula) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		this.miEmpleado = miEmpleado;
		this.miAula = miAula;
		initialize();
		rellenarComponentesDeAula(miAula);
		Btn_Modificar.setEnabled(true);
	}
	
	
	/**
	 * Método que lee los parámetros del objeto y los asigna a los TextFields
	 * correspondientes a dicho parámetro
	 * 
	 * @param miAlumno
	 */
	private void rellenarComponentesDeAula(Aula miAula) {
		CB_Centro.setSelectedItem(miAula.getCentro());
		TF_Nombre.setText(miAula.getNombre());
		CB_Capacidad.setSelectedItem(miAula.getCapacidad()+"");
		TF_Comentarios.setText(miAula.getComentarios());
	}
	
	
	
	/**
	 * Contrucción gráfica de la ventana
	 */
	private void initialize() {
		this.setBounds(0, 0, 1240, 720);
		this.setTitle("PLATON - ALUMNOS");
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
		Btn_Config.setVisible(false);
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
		JPanel_Botones.add(Btn_Registrar);
		JPanel_Botones.add(Btn_Volver);
		JPanel_Botones.add(Jpanel_Formulario);
		JPanel_Botones.add(Btn_Limpiar);
		JPanel_Botones.add(Btn_Modificar);	
		Btn_Logout.setIcon(iconoLogout);
		Btn_Config.setIcon(iconoConfig);
		Btn_Ayuda.setIcon(iconoAyuda);
		Btn_Inicio.setIcon(iconoInicio);
		Jpanel_Formulario.add(lbl_Form_0);
		Jpanel_Formulario.add(lbl_Form_1);
		Jpanel_Formulario.add(lbl_Form_2);
		Jpanel_Formulario.add(lbl_Form_3);
		Jpanel_Formulario.add(TF_Nombre);
		Jpanel_Formulario.add(TF_Comentarios);
		Jpanel_Formulario.add(CB_Centro);
		Jpanel_Formulario.add(CB_Capacidad);	
		rellenador.addCentros(CB_Centro);
		rellenador.addCapacidad(CB_Capacidad, 15);
	}
	
	
	
	/**
	 * Método que establece las propiedades de las fuentes de los componentes escritos
	 * dentro de la ventana. Estas propiedades indican el tipo de fuente, tamaño, color
	 * y modificadores tales como negrita o cursiva.
	 */
	private void establecerFuentes() {
		Btn_Config.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Ayuda.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Inicio.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Logout.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Registrar.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Btn_Volver.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Btn_Limpiar.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Btn_Modificar.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAlumnos.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lbl_Form_0.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		CB_Centro.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_Capacidad.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Nombre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Comentarios.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	}
	
	
	
	/**
	 * Método para posicionar de forma estática y con valores fijos los componentes dentro
	 * de cada ventana. Establece una cuaterna de píxeles para cada componente.
	 */
	private void posicionarComponentes() {
		JPanel_Cabecera.setBounds(0, 0, 1224, 175);
		JPanel_Botones.setBounds(0, 175, 1224, 506);
		Jpanel_Formulario.setBounds(10, 10, 1205, 400);
		Btn_Logout.setBounds(1161, 64, 50, 50);
		Btn_Ayuda.setBounds(1041, 64, 50, 50);
		Btn_Inicio.setBounds(1101, 64, 50, 50);
		Btn_Config.setBounds(981, 64, 50, 50);
		Btn_Registrar.setBounds(905, 420, 150, 75);
		Btn_Volver.setBounds(1065, 420, 150, 75);
		Btn_Limpiar.setBounds(585, 420, 150, 75);
		Btn_Modificar.setBounds(745, 420, 150, 75);
		lblUser.setBounds(881, 18, 330, 35);
		lblAlumnos.setBounds(861, 119, 350, 45);
		lblLogo.setBounds(0, 0, 618, 175);
		lbl_Form_0.setBounds(10, 50, 200, 50);
		lbl_Form_1.setBounds(10, 100, 200, 50);
		lbl_Form_2.setBounds(682, 100, 200, 50);
		lbl_Form_3.setBounds(10, 150, 200, 50);
		TF_Nombre.setBounds(230, 55, 965, 40);
		TF_Comentarios.setBounds(230, 155, 965, 150);
		CB_Centro.setBounds(230, 105, 300, 40);
		CB_Capacidad.setBounds(895, 105, 300, 40);
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
		Btn_Registrar.setForeground(Color.BLACK);
		Btn_Registrar.setBackground(new java.awt.Color(58, 207, 65));
		Btn_Volver.setForeground(Color.WHITE);
		Btn_Volver.setBackground(new java.awt.Color(87, 108, 168));
		Btn_Limpiar.setForeground(Color.BLACK);
		Btn_Limpiar.setBackground(new java.awt.Color(255, 112, 112));
		Btn_Modificar.setForeground(Color.BLACK);
		Btn_Modificar.setBackground(new Color(255, 255, 127));
	}
	
	
	
	/**
	 * Método para asignar propiedades específicas a compoentes en la ventana
	 */
	private void establecerLayouts() {
		JPanel_Botones.setLayout(null);
		JPanel_Cabecera.setLayout(null);
		Jpanel_Formulario.setLayout(null);
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlumnos.setHorizontalAlignment(SwingConstants.RIGHT);
		Btn_Config.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Ayuda.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Inicio.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Logout.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Registrar.addActionListener(this);
		Btn_Volver.addActionListener(this);
		Btn_Limpiar.addActionListener(this);
		Btn_Ayuda.addActionListener(this);
		Btn_Inicio.addActionListener(this);
		Btn_Logout.addMouseListener(this);
		Btn_Config.addActionListener(this);
		Btn_Modificar.addActionListener(this);
		Btn_Registrar.setFocusPainted(false);
		Btn_Volver.setFocusPainted(false);
		Btn_Config.setFocusPainted(false);
		Btn_Ayuda.setFocusPainted(false);
		Btn_Inicio.setFocusPainted(false);
		Btn_Logout.setFocusPainted(false);
		Btn_Modificar.setFocusPainted(false);
		Btn_Limpiar.setFocusPainted(false);
		Btn_Modificar.setEnabled(false);
	}
	
	
	
	/**
	 * Método que inicializa y da valores de texto a cada componente
	 */
	private void iniciarComponentes() {
		JPanel_Cabecera = new JPanel();
		JPanel_Botones = new JPanel();
		Jpanel_Formulario = new JPanel();
		Btn_Limpiar = new JButton("Limpiar");
		Btn_Registrar = new JButton("Registrar");
		Btn_Volver = new JButton("Volver");
		Btn_Config = new JButton("Config");
		Btn_Inicio = new JButton("Inicio");
		Btn_Ayuda = new JButton("Ayuda");
		Btn_Logout = new JButton("Logout");
		Btn_Modificar = new JButton("Modificar");
		lbl_Form_0 = new JLabel("Nombre");
		lbl_Form_1 = new JLabel("Centro");
		lbl_Form_2 = new JLabel("Capacidad");
		lbl_Form_3 = new JLabel("Comentarios");
		CB_Centro = new JComboBox<String>();
		CB_Capacidad = new JComboBox<String>();
		TF_Nombre = new JTextField();
		TF_Comentarios = new JTextField();	
		lblLogo = new JLabel(new ImageIcon("assets/logoVentana.png"));
		lblUser = new JLabel(miEmpleado.getNombre()+" "+miEmpleado.getApellidos());
		lblAlumnos = new JLabel("Alumnos - Nuevo");
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
		VAulaGenerico aulaGenerico;
		VAulaBuscar aulaBuscar;
		Inicio ventanaInicio;
		String txtBtn = e.getActionCommand();
		switch (txtBtn) {
			case "Registrar":
				if(CB_Centro.getSelectedItem().toString() == "Centro") {
					JOptionPane.showMessageDialog(this, "ERROR! Debe indicar un centro.");					
				} 
				else if(CB_Capacidad.getSelectedItem().toString() == "Capacidad") {
					JOptionPane.showMessageDialog(this, "ERROR! Debe indicar la capacidad del aula.");					
				}
				else {
					miAulaDao.addAula(
							miCentroDao.buscarCentroByNombre(CB_Centro.getSelectedItem().toString()), 
							TF_Nombre.getText(),
							Integer.parseInt(CB_Capacidad.getSelectedItem().toString()),
							TF_Comentarios.getText());
					JOptionPane.showMessageDialog(this, "Aula registrada con éxito");
					reiniciaLabels();
				}
				break;
			case "Modificar":
				if(CB_Centro.getSelectedItem().toString() == "Centro") {
					JOptionPane.showMessageDialog(this, "ERROR! Debe indicar un centro.");					
				} 
				else if(CB_Capacidad.getSelectedItem().toString() == "Capacidad") {
					JOptionPane.showMessageDialog(this, "ERROR! Debe indicar la capacidad del aula.");					
				}
				else {
					miAula = miAulaDao.modificarAula(
						miCentroDao.buscarCentroByNombre(CB_Centro.getSelectedItem().toString()),
						TF_Nombre.getText(),
						Integer.parseInt(CB_Capacidad.getSelectedItem().toString()),
						TF_Comentarios.getText(), miAula);
					JOptionPane.showMessageDialog(this, "Datos del aula modificados con éxito");
				}
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
				aulaGenerico = new VAulaGenerico(miEmpleado);
				break;
			case "Limpiar":
				reiniciaLabels();
				Btn_Modificar.setEnabled(false);
				break;
		}
	}
	
	
	
	/**
	 * Método que vacía los TextFields y devuelve los ComboBoxes al primer valor
	 */
	public void reiniciaLabels() {
		TF_Nombre.setText("");
		TF_Comentarios.setText("");
		CB_Centro.setSelectedItem("Centro");
		CB_Capacidad.setSelectedItem("Capacidad");
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