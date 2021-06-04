package views;

import javax.swing.*;

import common.RellenadorComboBox;
import dao.AulaDAO;
import dao.EmpleadoDAO;
import dao.HorarioDAO;
import models.Empleado;
import models.Horario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Clase de vista que genera una ventana de actividad de profesor, donde se podrá
 * consultar todos los grupos, aulas y horarios en los que un profesor activo esté
 * asignado
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
@SuppressWarnings("serial")
public class VProfesorActividad extends JFrame implements ActionListener, MouseListener{

	private JPanel JPanel_Cabecera, JPanel_Botones, panel_1;
	private JButton Btn_Consultar, Btn_Volver, Btn_Inicio, Btn_Config, Btn_Ayuda, Btn_Logout;
	private JLabel lblLogo, lblUser, lblAlumnos, lbl_Aula;
	private ImageIcon iconoConfig, iconoAyuda, iconoInicio, iconoLogout;
	
	private JComboBox<String> CB_Profesor;
	private JTable table;
	
	protected ArrayList<Horario> listaHorarios;
	private Empleado miEmpleado;	
	protected Horario horario;
	protected HorarioDAO horarioDao;
	protected AulaDAO aulaDao;
	protected EmpleadoDAO empleadoDao;
	private RellenadorComboBox rellenador;
	protected String info[][];


	
	/**
	 * Constructor por defecto de la ventana Login
	 */
	public VProfesorActividad(Empleado miEmpleado) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		this.miEmpleado = miEmpleado;
		initialize();
	}



	/**
	 * Contrucción gráfica de la ventana
	 */
	private void initialize() {
		this.setBounds(0, 0, 1240, 720);
		this.setTitle("PLATON - AULAS");
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
		this.rellenarTabla();
		
		this.setVisible(true);
	}

	
	
	/**
	 * Método que, obteniendo la lista correspondiente, genera una matriz de datos en forma de
	 * tabla y la completa con los nombres de cabecera y los valores de la tabla
	 */
	private void rellenarTabla() {
		info = new String[listaHorarios.size()][5];
		String[] nombresColumnas = {"Grupo", "Día","Hora de inicio","Hora de fin","Aula"};
		for (int i = 0; i < info.length; i++) {
			info[i][0] = listaHorarios.get(i).getGrupo();
			info[i][1] = listaHorarios.get(i).getDia();
			info[i][2] = listaHorarios.get(i).getHoraInicio();
			info[i][3] = listaHorarios.get(i).getHoraFinal();
			info[i][4] = listaHorarios.get(i).getAula();
		}
		table = new JTable(info, nombresColumnas);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table.setBounds(0, 0, 510, 209);
		table.addMouseListener(this);
		JScrollPane scrollPane = new  JScrollPane(table);
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scrollPane.setBackground(new java.awt.Color(244, 162, 97));
		scrollPane.setBounds(10, 11, 885, 484);
		JPanel_Botones.add(scrollPane);
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
		JPanel_Botones.add(Btn_Consultar);
		JPanel_Botones.add(Btn_Volver);
		JPanel_Botones.add(panel_1);
		panel_1.add(lbl_Aula);
		panel_1.add(CB_Profesor);
		rellenador.addProfesores(CB_Profesor);
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
		Btn_Consultar.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Btn_Volver.setFont(new Font("Times New Roman", Font.BOLD, 25));
		CB_Profesor.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_Aula.setFont(new Font("Times New Roman", Font.PLAIN, 25));
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
		Btn_Consultar.setBounds(905, 420, 150, 75);
		Btn_Volver.setBounds(1065, 420, 150, 75);
		lblAlumnos.setBounds(861, 119, 350, 45);
		lblLogo.setBounds(0, 0, 618, 175);
		panel_1.setBounds(905, 11, 309, 398);
		lbl_Aula.setBounds(10, 50, 200, 50);
		CB_Profesor.setBounds(10, 90, 289, 40);
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
		Btn_Consultar.setForeground(Color.BLACK);
		Btn_Volver.setForeground(Color.WHITE);
		Btn_Logout.setBackground(Color.RED);
		Btn_Consultar.setBackground(new java.awt.Color(255, 255, 127));
		Btn_Volver.setBackground(new java.awt.Color(87, 108, 168));
	}
		
	
	
	/**
	 * Método para asignar propiedades específicas a compoentes en la ventana
	 */
	private void establecerLayouts() {
		JPanel_Botones.setLayout(null);
		JPanel_Cabecera.setLayout(null);
		panel_1.setLayout(null);		
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlumnos.setHorizontalAlignment(SwingConstants.RIGHT);
		Btn_Config.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Ayuda.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Inicio.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Logout.setHorizontalAlignment(SwingConstants.CENTER);		
		Btn_Consultar.addActionListener(this);
		Btn_Volver.addActionListener(this);
		Btn_Ayuda.addActionListener(this);
		Btn_Inicio.addActionListener(this);
		Btn_Logout.addMouseListener(this);
		Btn_Config.addActionListener(this);		
		Btn_Consultar.setFocusPainted(false);
		Btn_Volver.setFocusPainted(false);
		Btn_Config.setFocusPainted(false);
		Btn_Ayuda.setFocusPainted(false);
		Btn_Inicio.setFocusPainted(false);
		Btn_Logout.setFocusPainted(false);	
		Btn_Config.setVisible(false);		
		Btn_Logout.setIcon(iconoLogout);
		Btn_Config.setIcon(iconoConfig);
		Btn_Ayuda.setIcon(iconoAyuda);
		Btn_Inicio.setIcon(iconoInicio);
	}

	
	
	/**
	 * Método que inicializa y da valores de texto a cada componente
	 */
	private void iniciarComponentes() {
		JPanel_Cabecera = new JPanel();
		JPanel_Botones = new JPanel();	
		Btn_Consultar = new JButton("Consultar");
		Btn_Volver = new JButton("Volver");
		Btn_Config = new JButton("Config");
		Btn_Inicio = new JButton("Inicio");
		Btn_Ayuda = new JButton("Ayuda");
		Btn_Logout = new JButton("Logout"); 		
		lblLogo = new JLabel(new ImageIcon("assets/logoVentana.png"));
		lblUser = new JLabel(miEmpleado.getNombre()+" "+miEmpleado.getApellidos());
		lblAlumnos = new JLabel("Profesores - Actividad");
		iconoConfig = new ImageIcon("assets/LogoConfig.png");
		iconoAyuda = new ImageIcon("assets/LogoAyuda.png");
		iconoInicio = new ImageIcon("assets/LogoInicio.png");
		iconoLogout = new ImageIcon("assets/LogoLogout.png");
		panel_1 = new JPanel();
		lbl_Aula = new JLabel("Profesor");
		CB_Profesor = new JComboBox<String>();		
		rellenador = new RellenadorComboBox();
		horario = new Horario();
		horarioDao = new HorarioDAO();
		aulaDao = new AulaDAO();
		empleadoDao = new EmpleadoDAO();		
		listaHorarios = horarioDao.buscarDisponibilidad(0);
	}



	/**
	 * Método que registra la actividad de los botones y realiza acciones según
	 * el botón seleccionado y el escuchador asignado al mismo
	 */
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		VProfesorGenerico profesorGenerico;
		Inicio ventanaInicio;
		String txtBtn = e.getActionCommand();
		switch (txtBtn) {
		case "Consultar":
			if(CB_Profesor.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(this, "ERROR: Debe seleccionar un profesor");
			}
			else {
				String nombreCompletoREG = CB_Profesor.getSelectedItem().toString();
				String[] partes = nombreCompletoREG.split(",");
				String apellidos = partes[0].trim();	
				String nombre = partes[1].trim();
				int idProfesor = empleadoDao.buscarEmpleadoPorNombre(nombre, apellidos);
				listaHorarios = horarioDao.buscarActividadProfesor(idProfesor);
				if(listaHorarios.size() == 0) {
					JOptionPane.showMessageDialog(this, "El profesor seleccionado no tiene ningún grupo asignado");
				}
				rellenarTabla();
			}
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
			profesorGenerico = new VProfesorGenerico(miEmpleado);
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