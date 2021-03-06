package views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.EmpleadoDAO;
import models.Empleado;

/**
 * Clase de vista que genera una ventana de profesor nuevo, donde podremos registrar
 * un profesor en la BDD
 * 
 * @author Rub?n Torrej?n Espinosa
 *
 */
@SuppressWarnings("serial")
public class VProfesorNuevo extends JFrame implements ActionListener, MouseListener{

	private JPanel JPanel_Cabecera, JPanel_Botones, Jpanel_Formulario;
	private JButton Btn_Logout, Btn_Inicio, Btn_Config, Btn_Ayuda, Btn_Limpiar,	Btn_Registrar, Btn_Volver;
	private JLabel lblLogo, lblUser, lblAlumnos;
	private JLabel lbl_Form_1, lbl_Form_2, lbl_Form_3, lbl_Form_4, lbl_Form_5, lbl_Form_7, 
		lbl_Form_6, lbl_Form_8, lbl_Form_9, lbl_Form_10, lbl_Form_11;	
	private ImageIcon iconoConfig, iconoAyuda, iconoInicio, iconoLogout;
	private JTextField TF_Nombre, TF_Apellidos, TF_Dni, TF_Telefono, TF_Email, TF_Direccion, TF_User,
		TF_Password, TF_Sueldo, TF_Titulo;
	private JComboBox<String> CB_Rol;

	private Empleado miEmpleado; 
	private EmpleadoDAO miEmpleadoDao = new EmpleadoDAO();    
	
    

	/**
	 * Constructor por defecto
	 * 
	 * @wbp.parser.constructor
	 */
	public VProfesorNuevo(Empleado miEmpleado) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		this.miEmpleado = miEmpleado;
		initialize();
	}
	
	
	
	/**
	 * Contrucci?n gr?fica de la ventana
	 */
	private void initialize() {
		this.setBounds(0, 0, 1240, 720);
		this.setTitle("PLATON - PROFESORES");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
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
	 * M?todo que establece el ?rbol de jerarqu?a de los componentes, indicando
	 * cu?les deben a?adirse gr?ficamente dentro de otros.
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
		Btn_Logout.setIcon(iconoLogout);
		Btn_Config.setIcon(iconoConfig);
		Btn_Ayuda.setIcon(iconoAyuda);
		Btn_Inicio.setIcon(iconoInicio);		
		Jpanel_Formulario.add(lbl_Form_1);
		Jpanel_Formulario.add(lbl_Form_2);
		Jpanel_Formulario.add(lbl_Form_3);
		Jpanel_Formulario.add(lbl_Form_4);
		Jpanel_Formulario.add(lbl_Form_5);
		Jpanel_Formulario.add(lbl_Form_6);
		Jpanel_Formulario.add(lbl_Form_7);
		Jpanel_Formulario.add(lbl_Form_8);
		Jpanel_Formulario.add(lbl_Form_9);
		Jpanel_Formulario.add(lbl_Form_10);
		Jpanel_Formulario.add(lbl_Form_11);
		Jpanel_Formulario.add(TF_Nombre);
		Jpanel_Formulario.add(TF_Telefono);
		Jpanel_Formulario.add(TF_Email);
		Jpanel_Formulario.add(TF_Direccion);
		Jpanel_Formulario.add(TF_Apellidos);
		Jpanel_Formulario.add(TF_Dni);
		Jpanel_Formulario.add(TF_User);
		Jpanel_Formulario.add(TF_Password);
		Jpanel_Formulario.add(TF_Sueldo);
		Jpanel_Formulario.add(TF_Titulo);
		Jpanel_Formulario.add(CB_Rol);		
		CB_Rol.addItem("Rol");
		CB_Rol.addItem("Profesor");
		CB_Rol.addItem("Jefe");
		CB_Rol.addItem("Administracion");
	}
	
	
	
	/**
	 * M?todo que establece las propiedades de las fuentes de los componentes escritos
	 * dentro de la ventana. Estas propiedades indican el tipo de fuente, tama?o, color
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
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAlumnos.setFont(new Font("Times New Roman", Font.PLAIN, 30));		
		lbl_Form_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_5.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_7.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_6.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_8.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_9.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_10.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_11.setFont(new Font("Times New Roman", Font.PLAIN, 25));		
		TF_Nombre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_Rol.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Apellidos.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Dni.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Telefono.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Email.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Direccion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_User.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Password.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Sueldo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Titulo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	}
	
	
	
	/**
	 * M?todo para posicionar de forma est?tica y con valores fijos los componentes dentro
	 * de cada ventana. Establece una cuaterna de p?xeles para cada componente.
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
		Btn_Limpiar.setBounds(745, 420, 150, 75);	
		lbl_Form_1.setBounds(10, 0, 200, 50);
		lbl_Form_2.setBounds(682, 0, 200, 50);
		lbl_Form_3.setBounds(10, 100, 200, 50);
		lbl_Form_4.setBounds(10, 150, 200, 50);
		lbl_Form_5.setBounds(10, 200, 200, 50);
		lbl_Form_6.setBounds(10, 250, 200, 50);
		lbl_Form_7.setBounds(10, 300, 207, 50);	
		lbl_Form_8.setBounds(10, 350, 200, 50);
		lbl_Form_9.setBounds(682, 100, 200, 50);
		lbl_Form_10.setBounds(682, 150, 200, 50);
		lbl_Form_11.setBounds(682, 200, 200, 50);		
		TF_Nombre.setBounds(230, 105, 300, 40);
		TF_Apellidos.setBounds(230, 155, 300, 40);
		TF_Dni.setBounds(230, 205, 300, 40);
		TF_Telefono.setBounds(230, 305, 300, 40);
		TF_Email.setBounds(230, 255, 300, 40);
		TF_Direccion.setBounds(230, 355, 300, 40);
		CB_Rol.setBounds(895, 205, 300, 40);
		TF_Sueldo.setBounds(895, 105, 300, 40);
		TF_Titulo.setBounds(895, 155, 300, 40);
		TF_User.setBounds(230, 5, 300, 40);
		TF_Password.setBounds(895, 5, 300, 40);		
		lblUser.setBounds(881, 18, 330, 35);
		lblAlumnos.setBounds(861, 119, 350, 45);
		lblLogo.setBounds(0, 0, 618, 175);
	}
	
	
	
	/**
	 * M?todo para asignar a cada componente visual de la ventana el color de fondo
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
		Btn_Registrar.setBackground(new java.awt.Color(58, 207, 65));
		Btn_Volver.setBackground(new java.awt.Color(87, 108, 168));
		Btn_Limpiar.setBackground(new java.awt.Color(255, 112, 112));
	}
	
	
	
	/**
	 * M?todo para asignar propiedades espec?ficas a compoentes en la ventana
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
		Btn_Registrar.setFocusPainted(false);
		Btn_Volver.setFocusPainted(false);
		Btn_Config.setFocusPainted(false);
		Btn_Ayuda.setFocusPainted(false);
		Btn_Inicio.setFocusPainted(false);
		Btn_Logout.setFocusPainted(false);
		Btn_Limpiar.setFocusPainted(false);	
		Btn_Config.setVisible(false);
	}
	
	
	
	/**
	 * M?todo que inicializa y da valores de texto a cada componente
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
		CB_Rol = new JComboBox<String>();		
		lbl_Form_1 = new JLabel("Usuario");
		lbl_Form_2 = new JLabel("Contrase?a");
		lbl_Form_3 = new JLabel("Nombre");
		lbl_Form_4 = new JLabel("Apellidos");
		lbl_Form_5 = new JLabel("DNI");
		lbl_Form_6 = new JLabel("eMail");
		lbl_Form_7 = new JLabel("Telefono");
		lbl_Form_8 = new JLabel("Direcci?n");
		lbl_Form_9 = new JLabel("Sueldo");
		lbl_Form_10 = new JLabel("T?tulo");
		lbl_Form_11 = new JLabel("Rol");		
		TF_Nombre = new JTextField();
		TF_Apellidos = new JTextField();
		TF_Dni = new JTextField();
		TF_Telefono = new JTextField();
		TF_Email = new JTextField();
		TF_Direccion = new JTextField();
		TF_User = new JTextField();
		TF_Password = new JTextField();
		TF_Sueldo = new JTextField();
		TF_Titulo = new JTextField();			
		lblLogo = new JLabel(new ImageIcon("assets/logoVentana.png"));
		lblUser = new JLabel(miEmpleado.getNombre()+" "+miEmpleado.getApellidos());
		lblAlumnos = new JLabel("Profesores - Nuevo");
		iconoConfig = new ImageIcon("assets/LogoConfig.png");
		iconoAyuda = new ImageIcon("assets/LogoAyuda.png");
		iconoInicio = new ImageIcon("assets/LogoInicio.png");
		iconoLogout = new ImageIcon("assets/LogoLogout.png");
	}
	
	
	
	/**
	 * M?todo que registra la actividad de los botones y realiza acciones seg?n
	 * el bot?n seleccionado y el escuchador asignado al mismo
	 */
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		VProfesorGenerico profesorGenerico;
		Inicio ventanaInicio;		
		String txtBtn = e.getActionCommand();
		switch (txtBtn) {
			case "Registrar":
				if(CB_Rol.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(this, "ERROR: Debe seleccionar un rol para el empleado");
				} else {
					String pass = this.getMD5(TF_Password.getText());
					int sueldoR = 0;
					Boolean sueldoCorrectoR = true;
					if(TF_Sueldo.getText().length()>0) {
						try {
							sueldoR = Integer.parseInt(TF_Sueldo.getText());
						} catch(Exception e1) {
							sueldoCorrectoR = false;
						}	
					}
					if(sueldoCorrectoR) {
						miEmpleadoDao.addEmpleado(
							TF_User.getText(),pass,TF_Nombre.getText(),
							TF_Apellidos.getText(),TF_Dni.getText(),TF_Telefono.getText(),
							TF_Email.getText(),TF_Direccion.getText(),sueldoR,
							TF_Titulo.getText(),CB_Rol.getSelectedItem().toString());
						JOptionPane.showMessageDialog(this, "Profesor registrado con ?xito");
						reiniciaLabels();
					} else {
						JOptionPane.showMessageDialog(this, "ERROR: El sueldo debe ser un valor num?rico");
					}
				}
				break;		
			case "Inicio":
				this.setVisible(false);
				this.dispose();
				ventanaInicio = new Inicio(miEmpleado);
				break;				
			case "Volver":
				this.setVisible(false);
				this.dispose();
				profesorGenerico = new VProfesorGenerico(miEmpleado);
				break;
			case "Ayuda":
				try
				{
					Process p = Runtime.getRuntime().exec ("rundll32 SHELL32.DLL,ShellExec_RunDLL "+"C:\\Users\\rtorrejo\\eclipse-workspace\\Platon\\assets\\user_manual.pdf");
				} catch (Exception e1) {
				}
				break;
			case "Limpiar":
				reiniciaLabels();
				break;
		}
	}
	
	
	
	/**
	 * M?todo que vac?a los TextFields y devuelve los ComboBoxes al primer valor
	 */
	public void reiniciaLabels() {
		TF_Nombre.setText("");
		TF_Apellidos.setText("");
		TF_Dni.setText("");
		TF_Telefono.setText("");
		TF_Email.setText("");
		TF_Direccion.setText("");
		TF_User.setText("");
		TF_Password.setText("");
		TF_Sueldo.setText("");
		TF_Titulo.setText("");
		CB_Rol.setSelectedItem("");
	}
	
	
	
	/**
	 * M?todo de encriptaci?n y desencriptaci?n de la constrase?a en BBDD
	 * @param input, string recibido a trav?s del Pf_Password
	 * @return contrase?a encriptada
	 */
	private String getMD5(String input) {
		try {
			 MessageDigest md = MessageDigest.getInstance("MD5");
			 byte[] messageDigest = md.digest(input.getBytes());
			 BigInteger number = new BigInteger(1, messageDigest);
			 String hashtext = number.toString(16);

			 while (hashtext.length() < 32) {
				 hashtext = "0" + hashtext;
			 } 
			 return hashtext;
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	
	
	/**
	 * M?todo que registra la actividad de los botones y realiza acciones seg?n
	 * el bot?n seleccionado y el escuchador asignado al mismo
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