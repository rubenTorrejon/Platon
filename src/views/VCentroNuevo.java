package views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import dao.CentroDAO;
import models.Centro;
import models.Empleado;

/**
 * Clase de vista que genera una ventana de centro nuevo, donde podremos registrar
 * un centro en la BDD
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
@SuppressWarnings("serial")
public class VCentroNuevo extends JFrame implements ActionListener, MouseListener{

	private JPanel JPanel_Cabecera, JPanel_Botones, Jpanel_Formulario;
	private JButton Btn_Logout, Btn_Inicio, Btn_Config, Btn_Ayuda, Btn_Limpiar, 
		Btn_Registrar, Btn_Volver, Btn_Buscar, Btn_Modificar;
	private JLabel lblLogo, lblUser, lblAlumnos;
	private JLabel lbl_Form_0, lbl_Form_1, lbl_Form_3, lbl_Form_2;	
	private ImageIcon iconoConfig, iconoAyuda, iconoInicio, iconoLogout;
	private JTextField TF_Nombre, TF_Telefono, TF_Observaciones, TF_Direccion;

	private Empleado miEmpleado;
	private Centro miCentro;
	private CentroDAO miCentroDao = new CentroDAO();
    
	

	/**
	 * Constructor por defecto
	 * 
	 * @wbp.parser.constructor
	 */
	public VCentroNuevo(Empleado miEmpleado) {
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
	public VCentroNuevo(Empleado miEmpleado, Centro miCentro) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		this.miEmpleado = miEmpleado;
		this.miCentro = miCentro;
		initialize();
		rellenarComponentesDeCentro(miCentro);
		Btn_Modificar.setEnabled(true);
	}
	
	
	
	/**
	 * Método que lee los parámetros del objeto y los asigna a los TextFields
	 * correspondientes a dicho parámetro
	 * 
	 * @param miAlumno
	 */
	private void rellenarComponentesDeCentro(Centro miCentro) {
		TF_Nombre.setText(miCentro.getNombre());
		TF_Telefono.setText(miCentro.getTelefono());
		TF_Direccion.setText(miCentro.getDireccion());
		TF_Observaciones.setText(miCentro.getObservaciones());
	}
	
	
	
	/**
	 * Contrucción gráfica de la ventana
	 */
	private void initialize() {
		this.setBounds(0, 0, 1240, 720);
		this.setTitle("PLATON - CENTROS");
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
		JPanel_Botones.add(Btn_Registrar);
		JPanel_Botones.add(Btn_Volver);
		JPanel_Botones.add(Jpanel_Formulario);
		JPanel_Botones.add(Btn_Limpiar);
		JPanel_Botones.add(Btn_Modificar);
		JPanel_Botones.add(Btn_Buscar);
		Btn_Logout.setIcon(iconoLogout);
		Btn_Config.setIcon(iconoConfig);
		Btn_Ayuda.setIcon(iconoAyuda);
		Btn_Inicio.setIcon(iconoInicio);
		Jpanel_Formulario.add(lbl_Form_0);
		Jpanel_Formulario.add(lbl_Form_1);
		Jpanel_Formulario.add(lbl_Form_3);
		Jpanel_Formulario.add(lbl_Form_2);
		Jpanel_Formulario.add(TF_Nombre);
		Jpanel_Formulario.add(TF_Telefono);
		Jpanel_Formulario.add(TF_Observaciones);
		Jpanel_Formulario.add(TF_Direccion);
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
		Btn_Buscar.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Btn_Modificar.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAlumnos.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lbl_Form_0.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		TF_Nombre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Telefono.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Observaciones.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Direccion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
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
		Btn_Limpiar.setBounds(425, 420, 150, 75);
		Btn_Buscar.setBounds(745, 420, 150, 75);
		Btn_Modificar.setBounds(585, 420, 150, 75);
		lblUser.setBounds(881, 18, 330, 35);
		lblAlumnos.setBounds(861, 119, 350, 45);
		lblLogo.setBounds(0, 0, 618, 175);
		lbl_Form_0.setBounds(10, 50, 200, 50);
		lbl_Form_1.setBounds(10, 100, 200, 50);
		lbl_Form_3.setBounds(10, 200, 200, 50);
		lbl_Form_2.setBounds(10, 150, 200, 50);
		TF_Nombre.setBounds(230, 55, 965, 40);
		TF_Telefono.setBounds(230, 105, 965, 40);
		TF_Observaciones.setBounds(230, 205, 965, 120);
		TF_Direccion.setBounds(230, 155, 965, 40);
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
		Btn_Volver.setForeground(Color.WHITE);
		Btn_Limpiar.setForeground(Color.BLACK);
		Btn_Modificar.setForeground(Color.BLACK);
		Btn_Buscar.setForeground(Color.BLACK);
		Btn_Registrar.setBackground(new java.awt.Color(58, 207, 65));
		Btn_Volver.setBackground(new java.awt.Color(87, 108, 168));
		Btn_Limpiar.setBackground(new java.awt.Color(255, 112, 112));
		Btn_Modificar.setBackground(new Color(255, 255, 127));
		Btn_Buscar.setBackground(new Color(255, 255, 127));
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
		Btn_Buscar.addActionListener(this);
		Btn_Modificar.addActionListener(this);
		Btn_Registrar.setFocusPainted(false);
		Btn_Volver.setFocusPainted(false);
		Btn_Config.setFocusPainted(false);
		Btn_Ayuda.setFocusPainted(false);
		Btn_Inicio.setFocusPainted(false);
		Btn_Logout.setFocusPainted(false);
		Btn_Buscar.setFocusPainted(false);
		Btn_Modificar.setFocusPainted(false);
		Btn_Limpiar.setFocusPainted(false);
		Btn_Modificar.setEnabled(false);
		Btn_Config.setVisible(false);
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
		Btn_Buscar = new JButton("Buscar");
		lbl_Form_0 = new JLabel("Nombre");
		lbl_Form_1 = new JLabel("Telefono");
		lbl_Form_3 = new JLabel("Observaciones");
		lbl_Form_2 = new JLabel("Dirección");
		TF_Nombre = new JTextField();
		TF_Telefono = new JTextField();
		TF_Observaciones = new JTextField();
		TF_Direccion = new JTextField();	
		lblLogo = new JLabel(new ImageIcon("assets/logoVentana.png"));
		lblUser = new JLabel(miEmpleado.getNombre()+" "+miEmpleado.getApellidos());
		lblAlumnos = new JLabel("Centros - Nuevo");
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
		VCentroGenerico centroGenerico;
		VCentroBuscar centroBuscar;
		Inicio ventanaInicio;
		String txtBtn = e.getActionCommand();
		switch (txtBtn) {
			case "Registrar":
				miCentroDao.addCentro(TF_Nombre.getText(),TF_Telefono.getText(),TF_Direccion.getText(),TF_Observaciones.getText());
				JOptionPane.showMessageDialog(this, "Centro registrado con éxito");
				reiniciaLabels();
				break;
			case "Buscar":
				ArrayList<Centro> listaCentros = miCentroDao.buscarCentros(
						TF_Nombre.getText(),TF_Telefono.getText(),TF_Direccion.getText());
				if(listaCentros.size()==1) {
					Btn_Modificar.setEnabled(true);
					miCentro = listaCentros.get(0);
					rellenarComponentesDeCentro(miCentro);
				} else if(listaCentros.size()>1) {
					this.setVisible(false);
					this.dispose();
					centroBuscar = new VCentroBuscar(miEmpleado, listaCentros);
				} else {
					JOptionPane.showMessageDialog(this, "Lo sentimos, no se ha encontrado ningún centro con esas características en la BBDD");
					reiniciaLabels();
				}
				break;
			case "Modificar":		
				miCentro = miCentroDao.modificarCentro(
					TF_Nombre.getText(),TF_Telefono.getText(),TF_Direccion.getText(),TF_Observaciones.getText(),miCentro);
				JOptionPane.showMessageDialog(this, "Datos del centro modificados con éxito");
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
				centroGenerico = new VCentroGenerico(miEmpleado);
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
		TF_Telefono.setText("");
		TF_Observaciones.setText("");
		TF_Direccion.setText("");	
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