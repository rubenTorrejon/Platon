package views;

import javax.swing.*;
import models.Alumno;
import models.Empleado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Clase de vista que genera una ventana de búsqueda de alumnos, con una tabla que obtiene los
 * alumnos generados según los parámetros de búsqueda que se hayan decidido
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
@SuppressWarnings("serial")
public class VAlumnoBuscarTodos extends JFrame implements ActionListener, MouseListener{

	private JPanel JPanel_Cabecera, JPanel_Botones;
	private JButton Btn_Volver, Btn_Inicio, Btn_Config, Btn_Ayuda, Btn_Logout, Btn_Filtrar;
	private JLabel lblLogo, lblUser, lblAlumnos;
	private ImageIcon iconoConfig, iconoAyuda, iconoInicio, iconoLogout;
	
	private Empleado miEmpleado;
	protected ArrayList<Alumno> listaAlumnos;
	protected String info[][];
	private JTable table;
	
	
	/**
	 * Constructor por defecto
	 */
	public VAlumnoBuscarTodos(Empleado miEmpleado, ArrayList<Alumno> miListaAlumnos) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		getContentPane().setForeground(Color.BLACK);
		this.listaAlumnos = miListaAlumnos;
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
		this.rellenarTabla();
		this.setVisible(true);
	}


	
	/**
	 * Método que, obteniendo la lista correspondiente, genera una matriz de datos en forma de
	 * tabla y la completa con los nombres de cabecera y los valores de la tabla
	 */
	private void rellenarTabla() {
		info = new String[listaAlumnos.size()][7];
		String[] nombresColumnas = {"Nombre","Apellidos","Teléfono","DNI","Nombre Responsable","Fecha de alta","Fecha de baja"};
		for (int i = 0; i < info.length; i++) {
			info[i][0] = listaAlumnos.get(i).getNombre();
			info[i][1] = listaAlumnos.get(i).getApellidos();
			info[i][2] = listaAlumnos.get(i).getTelefono();
			info[i][3] = listaAlumnos.get(i).getDni();
			info[i][4] = listaAlumnos.get(i).getResponsableNombre();
			info[i][5] = listaAlumnos.get(i).getFechaAlta()+"";
			info[i][6] = listaAlumnos.get(i).getFechaBaja()+"";
		}
		table = new JTable(info, nombresColumnas);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table.setBounds(0, 0, 510, 209);
		table.addMouseListener(this);
		JScrollPane scrollPane = new  JScrollPane(table);
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scrollPane.setBackground(new java.awt.Color(244, 162, 97));
		scrollPane.setBounds(10, 11, 1205, 398);
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
		JPanel_Botones.add(Btn_Volver);
		JPanel_Botones.add(Btn_Filtrar);
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
		Btn_Config.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Ayuda.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Inicio.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Logout.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Volver.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Btn_Filtrar.setFont(new Font("Times New Roman", Font.BOLD, 25));
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
		Btn_Logout.setBounds(1161, 64, 50, 50);
		Btn_Ayuda.setBounds(1041, 64, 50, 50);
		Btn_Inicio.setBounds(1101, 64, 50, 50);
		Btn_Config.setBounds(981, 64, 50, 50);
		Btn_Volver.setBounds(1065, 420, 150, 75);
		Btn_Filtrar.setBounds(905, 420, 150, 75);
		lblUser.setBounds(881, 18, 330, 35);
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
		Btn_Filtrar.setForeground(Color.BLACK);
		Btn_Volver.setForeground(Color.WHITE);
		Btn_Logout.setBackground(Color.RED);
		Btn_Volver.setBackground(new java.awt.Color(87, 108, 168));
		Btn_Filtrar.setBackground(new Color(255, 255, 127));
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
		Btn_Volver.addActionListener(this);
		Btn_Ayuda.addActionListener(this);
		Btn_Inicio.addActionListener(this);
		Btn_Config.addActionListener(this);
		Btn_Filtrar.addActionListener(this);
		Btn_Logout.addMouseListener(this);
		Btn_Volver.setFocusPainted(false);
		Btn_Config.setFocusPainted(false);
		Btn_Ayuda.setFocusPainted(false);
		Btn_Inicio.setFocusPainted(false);
		Btn_Logout.setFocusPainted(false);
		Btn_Filtrar.setFocusPainted(false);
		Btn_Config.setVisible(false);
	}

	
	
	/**
	 * Método que inicializa y da valores de texto a cada componente
	 */
	private void iniciarComponentes() {
		JPanel_Cabecera = new JPanel();
		JPanel_Botones = new JPanel();
		Btn_Volver = new JButton("Volver");
		Btn_Config = new JButton("Config");
		Btn_Inicio = new JButton("Inicio");
		Btn_Ayuda = new JButton("Ayuda");
		Btn_Logout = new JButton("Logout"); 
		Btn_Filtrar = new JButton("Filtrar");
		lblLogo = new JLabel(new ImageIcon("assets/logoVentana.png"));
		lblUser = new JLabel(miEmpleado.getNombre()+" "+miEmpleado.getApellidos());
		lblAlumnos = new JLabel("Alumnos - Buscar");
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
		VAlumnoBuscarTodos alumnoBuscar;
		VAlumnoAsignar alumnoAsignar;
		VAlumnoFicha alumnoFicha;
		Inicio ventanaInicio;
		String txtBtn = e.getActionCommand();
		switch (txtBtn) {
			case "Inicio":
				this.setVisible(false);
				this.dispose();
				ventanaInicio = new Inicio(miEmpleado);
				break;	
			case "Filtrar":
				this.setVisible(false);
				this.dispose();
				alumnoFicha = new VAlumnoFicha(miEmpleado);
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
		}
	}

	
	
	/**
	 * Método que registra la actividad de los botones y realiza acciones según
	 * el botón seleccionado y el escuchador asignado al mismo
	 */
	@SuppressWarnings("unused")
	@Override
	public void mouseClicked(MouseEvent e) {
		VAlumnoFicha alumnoFicha;
		Login login;
		Component txtBtn = e.getComponent();
		if(txtBtn==Btn_Logout) {
			this.setVisible(false);
			this.dispose();
			miEmpleado = null;
			login = new Login();
		} else {
	        int row = table.getSelectedRow();
	        int column = table.getSelectedColumn();
			table.getValueAt(row,column);
			this.setVisible(false);
			alumnoFicha = new VAlumnoFicha(miEmpleado, listaAlumnos.get(row));
		}
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