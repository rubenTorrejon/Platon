package views;

import javax.swing.*;

import common.Comprobador;
import common.RellenadorComboBox;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.Calendar;

import dao.AlumnoDAO;
import models.Empleado;

/**
 * Clase de vista que genera una ventana de alumno nuevo, donde podremos registrar
 * un alumno en la BDD
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
@SuppressWarnings("serial")
public class VAlumnoNuevo extends JFrame implements ActionListener, MouseListener{

	private JPanel JPanel_Cabecera, JPanel_Botones, Jpanel_Formulario;
	private JButton Btn_Logout, Btn_Inicio, Btn_Config, Btn_Ayuda, Btn_Limpiar, 
		Btn_Registrar, Btn_Volver;
	private JLabel lblLogo, lblUser, lblAlumnos;
	private JLabel lbl_Form_0, lbl_Form_1, lbl_Form_2, lbl_Form_3, lbl_Form_4, lbl_Form_5, 
		lbl_Form_6, lbl_Form_7, lbl_Form_8, lbl_Form_9, lbl_Form_10, lbl_Form_11;	
	private ImageIcon iconoConfig, iconoAyuda, iconoInicio, iconoLogout;
	private JTextField TF_Nombre, TF_Apellidos, TF_Dni, TF_Telefono, TF_Email, TF_Direccion, 
		TF_NombreResp, TF_DniResp, TF_TelefonoResp, TF_EmailResp;
	private JComboBox<String> CB_DiaNac, CB_MesNac, CB_YearNac;

	private Empleado miEmpleado;
	private AlumnoDAO miAlumnoDao = new AlumnoDAO();
	private RellenadorComboBox rellenador = new RellenadorComboBox();
	private Comprobador comprobador = new Comprobador();
	
    private Calendar fecha = Calendar.getInstance();
    private int actualYear = fecha.get(Calendar.YEAR);
    
	
    
	/**
	 * Constructor por defecto
	 * 
	 * @wbp.parser.constructor
	 */
	public VAlumnoNuevo(Empleado miEmpleado) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		this.miEmpleado = miEmpleado;
		initialize();
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
		Btn_Logout.setIcon(iconoLogout);
		Btn_Config.setIcon(iconoConfig);
		Btn_Ayuda.setIcon(iconoAyuda);
		Btn_Inicio.setIcon(iconoInicio);
		Jpanel_Formulario.add(lbl_Form_0);
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
		Jpanel_Formulario.add(CB_DiaNac);
		Jpanel_Formulario.add(CB_MesNac);
		Jpanel_Formulario.add(CB_YearNac);
		Jpanel_Formulario.add(TF_NombreResp);
		Jpanel_Formulario.add(TF_DniResp);
		Jpanel_Formulario.add(TF_TelefonoResp);
		Jpanel_Formulario.add(TF_EmailResp);			
		rellenador.addDiasMes(CB_DiaNac);
		rellenador.addMeses(CB_MesNac);
		rellenador.addYears(CB_YearNac, actualYear);
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
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAlumnos.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lbl_Form_0.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_5.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_6.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_7.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl_Form_8.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_9.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_10.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_11.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		CB_DiaNac.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_MesNac.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_YearNac.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Nombre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Apellidos.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Dni.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Telefono.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Email.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Direccion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_NombreResp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_DniResp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_TelefonoResp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_EmailResp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
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
		Btn_Limpiar.setBounds(745, 420, 150, 75);
		lblUser.setBounds(881, 18, 330, 35);
		lblAlumnos.setBounds(861, 119, 350, 45);
		lblLogo.setBounds(0, 0, 618, 175);
		lbl_Form_0.setBounds(10, 0, 200, 50);
		lbl_Form_1.setBounds(10, 50, 200, 50);
		lbl_Form_2.setBounds(10, 100, 200, 50);
		lbl_Form_3.setBounds(10, 150, 200, 50);
		lbl_Form_4.setBounds(10, 200, 200, 50);
		lbl_Form_5.setBounds(10, 250, 200, 50);
		lbl_Form_6.setBounds(10, 300, 207, 50);	
		lbl_Form_7.setBounds(623, 0, 572, 50);
		lbl_Form_8.setBounds(682, 50, 200, 50);
		lbl_Form_9.setBounds(682, 100, 200, 50);
		lbl_Form_10.setBounds(682, 150, 200, 50);
		lbl_Form_11.setBounds(682, 200, 200, 50);
		TF_Nombre.setBounds(230, 5, 300, 40);
		TF_Apellidos.setBounds(230, 55, 300, 40);
		TF_Dni.setBounds(230, 105, 300, 40);
		TF_Telefono.setBounds(230, 155, 300, 40);
		TF_Email.setBounds(230, 205, 300, 40);
		TF_Direccion.setBounds(230, 255, 300, 40);
		TF_NombreResp.setBounds(895, 55, 300, 40);
		TF_DniResp.setBounds(895, 105, 300, 40);
		TF_TelefonoResp.setBounds(895, 155, 300, 40);
		TF_EmailResp.setBounds(895, 205, 300, 40);
		CB_DiaNac.setBounds(230, 305, 60, 40);
		CB_MesNac.setBounds(300, 305, 140, 40);
		CB_YearNac.setBounds(450, 305, 80, 40);
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
		lbl_Form_7.setHorizontalAlignment(SwingConstants.CENTER);
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
		lbl_Form_0 = new JLabel("Nombre");
		lbl_Form_1 = new JLabel("Apellidos");
		lbl_Form_2 = new JLabel("DNI");
		lbl_Form_3 = new JLabel("Telefono");
		lbl_Form_4 = new JLabel("eMail");
		lbl_Form_5 = new JLabel("Dirección");
		lbl_Form_6 = new JLabel("Fecha de nacimiento");		
		lbl_Form_7 = new JLabel("Persona responsable del alumno");
		lbl_Form_8 = new JLabel("Nombre completo");
		lbl_Form_9 = new JLabel("DNI");
		lbl_Form_10 = new JLabel("Teléfono");
		lbl_Form_11 = new JLabel("eMail");
		CB_DiaNac = new JComboBox<String>();
		CB_MesNac = new JComboBox<String>();
		CB_YearNac = new JComboBox<String>();
		TF_Nombre = new JTextField();
		TF_Apellidos = new JTextField();
		TF_Dni = new JTextField();
		TF_Telefono = new JTextField();
		TF_Email = new JTextField();
		TF_Direccion = new JTextField();
		TF_NombreResp = new JTextField();
		TF_DniResp = new JTextField();
		TF_TelefonoResp = new JTextField();
		TF_EmailResp = new JTextField();	
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
		VAlumnoGenerico alumnoGenerico;
		VAlumnoAsignar alumnoAsignar;
		Inicio ventanaInicio;
		boolean telefonoAlumno = false;
		boolean telefonoResponsable = false;
		boolean dniAlumno = false;
		boolean dniResponsable = false;
		boolean alumnoExiste = true;
		String txtBtn = e.getActionCommand();
		switch (txtBtn) {
			case "Registrar":
				Boolean fechaCorrecta = false;
				if(CB_DiaNac.getSelectedItem().toString() != "Día" 
						&& CB_MesNac.getSelectedItem().toString() != "Mes" 
						&& CB_YearNac.getSelectedItem().toString() != "Año") {
					fechaCorrecta = true;
				}
				if(!comprobador.esUnTelefono(TF_Telefono.getText()))
					JOptionPane.showMessageDialog(this, "ERROR: El teléfono del alumno no es correcto");
				else	
					telefonoAlumno = true;
				if(!comprobador.esUnTelefono(TF_TelefonoResp.getText()))
					JOptionPane.showMessageDialog(this, "ERROR: El teléfono del responsable no es correcto");
				else
					telefonoResponsable = true;
				if(!comprobador.esUnDni(TF_Dni.getText()))
					JOptionPane.showMessageDialog(this, "ERROR: El dni del alumno no es correcto");
				else
					dniAlumno = true;
				if(!comprobador.esUnDni(TF_DniResp.getText()))
					JOptionPane.showMessageDialog(this, "ERROR: El dni del responsable no es correcto");
				else
					dniResponsable = true;
				if(miAlumnoDao.alumnoExiste(TF_Dni.getText()) && TF_Dni.getText().length()>0)
					JOptionPane.showMessageDialog(this, "ERROR: El dni seleccionado ya pertenece a un alumno registrado");
				else
					alumnoExiste = false;
				if(telefonoAlumno && telefonoResponsable && dniAlumno && dniResponsable && !alumnoExiste) {
					if(TF_Nombre.getText().length() == 0 || TF_Apellidos.getText().length() == 0)
						JOptionPane.showMessageDialog(this, "Los valores de nombre y apellidos no pueden estar vacíos");
					else {
						if(fechaCorrecta) {
							String dia = CB_DiaNac.getSelectedItem().toString(); 
							String mes = rellenador.pasaMesANumero(CB_MesNac.getSelectedItem().toString());
							String year = CB_YearNac.getSelectedItem().toString();			
							String fechaNac = year+"-"+mes+"-"+dia;
							miAlumnoDao.addAlumno(
									TF_Nombre.getText(),TF_Apellidos.getText(),TF_Dni.getText(),
									TF_Telefono.getText(),TF_Direccion.getText(),TF_Email.getText(),
									Date.valueOf(fechaNac),TF_NombreResp.getText(),TF_DniResp.getText(),
									TF_TelefonoResp.getText(),TF_EmailResp.getText());
							JOptionPane.showMessageDialog(this, "Alumno registrado con éxito");
							reiniciaLabels();
						} else {
							miAlumnoDao.addAlumno(
									TF_Nombre.getText(),TF_Apellidos.getText(),TF_Dni.getText(),
									TF_Telefono.getText(),TF_Direccion.getText(),TF_Email.getText(),TF_NombreResp.getText(),TF_DniResp.getText(),
									TF_TelefonoResp.getText(),TF_EmailResp.getText());
							JOptionPane.showMessageDialog(this, "Alumno registrado con éxito");
							reiniciaLabels();
						}
					}
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
				alumnoGenerico = new VAlumnoGenerico(miEmpleado);
				break;
			case "Limpiar":
				reiniciaLabels();
				break;
		}
	}
	
	
	
	/**
	 * Método que vacía los TextFields y devuelve los ComboBoxes al primer valor
	 */
	public void reiniciaLabels() {
		TF_Nombre.setText("");
		TF_Apellidos.setText("");
		TF_Dni.setText("");
		TF_Telefono.setText("");
		TF_Email.setText("");
		TF_Direccion.setText("");
		TF_NombreResp.setText("");
		TF_DniResp.setText("");
		TF_TelefonoResp.setText("");
		TF_EmailResp.setText("");
		CB_DiaNac.setSelectedItem("Día");
		CB_MesNac.setSelectedItem("Mes");
		CB_YearNac.setSelectedItem("Año");	
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