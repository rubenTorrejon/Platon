package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.EmpleadoDAO;
import models.Empleado;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

/**
 * Clase de vista que genera una ventana de login, donde el usuario podr� indicar el user y el password
 * y, en caso de estar registrado, acceder a la ventana inicio.
 * 
 * @author Rub�n Torrej�n Espinosa
 *
 */
@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener{
	
	private JPanel JPanel_Principal;
	private JTextField Tf_Usuario;
	private JPasswordField Pf_Password;
	protected JLabel Lbl_Usuario;
	protected JLabel Lbl_Password;
	protected JButton Btn_Inicio;

	protected EmpleadoDAO miEmpleadoDao;
	protected Empleado miEmpleado;
		

	
	/**
	 * Constructor por defecto de la ventana Login
	 */
	public Login() {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		initialize();
		miEmpleadoDao = new EmpleadoDAO();
	}



	/**
	 * Contrucci�n gr�fica de la ventana
	 */
	private void initialize() {
		this.setBounds(0, 0, 1240, 720);
		this.setResizable(true);
		this.setTitle("PLATON");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.iniciarComponentes();
		this.establecerLayouts();
		this.establecerColores();
		this.posicionarComponentes();
		this.establecerFuentes();
		this.incluirComponentes();
		this.getRootPane().setDefaultButton(Btn_Inicio);
		this.setVisible(true);
	}


	
	/**
	 * M�todo que establece el �rbol de jerarqu�a de los componentes, indicando
	 * cu�les deben a�adirse gr�ficamente dentro de otros.
	 */
	private void incluirComponentes() {
		JPanel_Principal.add(new JLabel(new ImageIcon("assets/logoLogin.png")));		
		getContentPane().add(JPanel_Principal);
		getContentPane().add(Lbl_Usuario);
		getContentPane().add(Lbl_Password);
		getContentPane().add(Pf_Password);
		getContentPane().add(Btn_Inicio);
		getContentPane().add(Tf_Usuario);
	}



	/**
	 * M�todo que establece las propiedades de las fuentes de los componentes escritos
	 * dentro de la ventana. Estas propiedades indican el tipo de fuente, tama�o, color
	 * y modificadores tales como negrita o cursiva.
	 */
	private void establecerFuentes() {
		Lbl_Usuario.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Tf_Usuario.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Lbl_Password.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Pf_Password.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Btn_Inicio.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		Pf_Password.setColumns(15);
		Tf_Usuario.setColumns(15);
	}



	/**
	 * M�todo para posicionar de forma est�tica y con valores fijos los componentes dentro
	 * de cada ventana. Establece una cuaterna de p�xeles para cada componente.
	 */
	private void posicionarComponentes() {
		JPanel_Principal.setBounds(10, 10, 1214, 300);
		Lbl_Usuario.setBounds(386, 405, 114, 36);
		Tf_Usuario.setBounds(545, 412, 269, 26);
		Lbl_Password.setBounds(386, 442, 138, 36);
		Pf_Password.setBounds(545, 449, 269, 26);
		Btn_Inicio.setBounds(545, 486, 269, 45);		
	}



	/**
	 * M�todo para asignar a cada componente visual de la ventana el color de fondo
	 */
	private void establecerColores() {
		Lbl_Password.setForeground(Color.WHITE);
		Lbl_Usuario.setForeground(Color.WHITE);
		JPanel_Principal.setBackground(new java.awt.Color(27, 38, 79));
	}

	
	
	/**
	 * M�todo para asignar propiedades espec�ficas a compoentes en la ventana
	 */
	private void establecerLayouts() {
		Btn_Inicio.addActionListener(this);
	}

	
	
	/**
	 * M�todo que inicializa y da valores de texto a cada componente
	 */
	private void iniciarComponentes() {
		JPanel_Principal = new JPanel();
		Lbl_Usuario = new JLabel("Usuario");
		Lbl_Password = new JLabel("Contrase�a");
		Tf_Usuario = new JTextField();
		Pf_Password = new JPasswordField();
		Btn_Inicio = new JButton("Iniciar sesi�n");
	}



	/**
	 * M�todo que capta la acci�n del puntero del rat�n en la pantalla y ejecuta la
	 * acci�n programada. Al pulsar el bot�n login, leer� de la BBDD el rol del user
	 * que intenta conectarse, e iniciar� la pantalla correspondiente a ese rol.
	 * En caso de que la dupla user-password establecida no exista en la BBDD, volver�
	 * visible el TF de usuario incorrecto.
	 * @param e
	 */
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		String pass = new String(Pf_Password.getPassword());		
		Inicio ventana;
		pass = this.getMD5(pass);
		miEmpleado = miEmpleadoDao.testLogin(Tf_Usuario.getText(),pass);
		if(miEmpleado!=null) {
			String rol = miEmpleado.getRol();
			switch (rol) {
			case "Profesor":
				this.setVisible(false);
				dispose();
				ventana = new Inicio(miEmpleado);
				break;
			case "Jefe":
				this.setVisible(false);
				ventana = new Inicio(miEmpleado);
				break;
			case "Administracion":
				this.setVisible(false);
				dispose();
				ventana = new Inicio(miEmpleado);
				break;
			}
		} else {
			JOptionPane.showMessageDialog(this, "ERROR: Usuario o contrase�a incorrectos");
			Pf_Password.setText("");
		}
		
	}
	
	
	
	/**
	 * M�todo de encriptaci�n y desencriptaci�n de la constrase�a en BBDD
	 * @param input, string recibido a trav�s del Pf_Password
	 * @return contrase�a encriptada
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
}
