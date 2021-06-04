package views;

import javax.swing.*;

import models.Empleado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Clase de vista que genera una ventana de b�squeda de profesores, con una tabla que obtiene los
 * profesores generados seg�n los par�metros de b�squeda que se hayan decidido
 * 
 * @author Rub�n Torrej�n Espinosa
 *
 */
@SuppressWarnings("serial")
public class VProfesorBuscarTodos extends JFrame implements ActionListener, MouseListener{

	private JPanel JPanel_Cabecera, JPanel_Botones;
	private JButton Btn_Filtrar, Btn_Volver, Btn_Inicio, Btn_Config, Btn_Ayuda, Btn_Logout;
	private JLabel lblLogo, lblUser, lblAlumnos;
	private ImageIcon iconoConfig, iconoAyuda, iconoInicio, iconoLogout;
	
	private Empleado miEmpleado;
	protected ArrayList<Empleado> listaEmpleados;
	protected String info[][];
	private JTable table;
	
	

	/**
	 * Constructor por defecto
	 * @param miEmpleado
	 * @param miListaEmpleados
	 */
	public VProfesorBuscarTodos(Empleado miEmpleado, ArrayList<Empleado> miListaEmpleados) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		getContentPane().setForeground(Color.BLACK);
		this.listaEmpleados = miListaEmpleados;
		this.miEmpleado = miEmpleado;
		initialize();
	}



	/**
	 * Contrucci�n gr�fica de la ventana
	 */
	private void initialize() {
		this.setBounds(0, 0, 1240, 720);
		this.setTitle("PLATON - EMPLEADOS");
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
	 * M�todo que, obteniendo la lista correspondiente, genera una matriz de datos en forma de
	 * tabla y la completa con los nombres de cabecera y los valores de la tabla
	 */
	private void rellenarTabla() {
		info = new String[listaEmpleados.size()][7];
		String[] nombresColumnas = {"Nombre","Apellidos","Tel�fono","Titulaci�n","Rol","Fecha de alta","Fecha de baja"};
		for (int i = 0; i < info.length; i++) {
			info[i][0] = listaEmpleados.get(i).getNombre();
			info[i][1] = listaEmpleados.get(i).getApellidos();
			info[i][2] = listaEmpleados.get(i).getTelefono();
			info[i][3] = listaEmpleados.get(i).getTitulo();
			info[i][4] = listaEmpleados.get(i).getRol();
			info[i][5] = listaEmpleados.get(i).getFechaAlta()+"";
			info[i][6] = listaEmpleados.get(i).getFechaBaja()+"";
		}
		table = new JTable(info, nombresColumnas);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table.setBounds(0, 0, 510, 209);
		table.addMouseListener(this);
		JScrollPane scrollPane= new  JScrollPane(table);
		scrollPane.setBackground(new java.awt.Color(244, 162, 97));
		scrollPane.setBounds(10, 10, 1205, 399);
		JPanel_Botones.add(scrollPane);
	}
		
	



	/**
	 * M�todo que establece el �rbol de jerarqu�a de los componentes, indicando
	 * cu�les deben a�adirse gr�ficamente dentro de otros.
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
		JPanel_Botones.add(Btn_Filtrar);
		JPanel_Botones.add(Btn_Volver);
		Btn_Logout.setIcon(iconoLogout);
		Btn_Config.setIcon(iconoConfig);
		Btn_Ayuda.setIcon(iconoAyuda);
		Btn_Inicio.setIcon(iconoInicio);
	}



	/**
	 * M�todo que establece las propiedades de las fuentes de los componentes escritos
	 * dentro de la ventana. Estas propiedades indican el tipo de fuente, tama�o, color
	 * y modificadores tales como negrita o cursiva.
	 */
	private void establecerFuentes() {
		Btn_Config.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Ayuda.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Inicio.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Logout.setFont(new Font("Times New Roman", Font.PLAIN, 0));
		Btn_Filtrar.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Btn_Volver.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAlumnos.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAlumnos.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	}



	/**
	 * M�todo para posicionar de forma est�tica y con valores fijos los componentes dentro
	 * de cada ventana. Establece una cuaterna de p�xeles para cada componente.
	 */
	private void posicionarComponentes() {
		JPanel_Cabecera.setBounds(0, 0, 1224, 175);
		JPanel_Botones.setBounds(0, 175, 1224, 506);
		lblUser.setBounds(881, 18, 330, 35);
		Btn_Logout.setBounds(1161, 64, 50, 50);
		Btn_Ayuda.setBounds(1041, 64, 50, 50);
		Btn_Inicio.setBounds(1101, 64, 50, 50);
		Btn_Config.setBounds(981, 64, 50, 50);
		Btn_Filtrar.setBounds(905, 420, 150, 75);
		Btn_Volver.setBounds(1065, 420, 150, 75);		
		lblAlumnos.setBounds(861, 119, 350, 45);
		lblLogo.setBounds(0, 0, 618, 175);
	}



	/**
	 * M�todo para asignar a cada componente visual de la ventana el color de fondo
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
		Btn_Filtrar.setBackground(new java.awt.Color(255, 255, 127));
		Btn_Volver.setBackground(new java.awt.Color(87, 108, 168));
	}
		
	
	
	/**
	 * M�todo para asignar propiedades espec�ficas a compoentes en la ventana
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
		Btn_Filtrar.addActionListener(this);
		Btn_Volver.addActionListener(this);
		Btn_Ayuda.addActionListener(this);
		Btn_Inicio.addActionListener(this);
		Btn_Logout.addMouseListener(this);
		Btn_Config.addActionListener(this);
		Btn_Filtrar.setFocusPainted(false);
		Btn_Volver.setFocusPainted(false);
		Btn_Config.setFocusPainted(false);
		Btn_Ayuda.setFocusPainted(false);
		Btn_Inicio.setFocusPainted(false);
		Btn_Logout.setFocusPainted(false);		
		Btn_Config.setVisible(false);
	}

	
	
	/**
	 * M�todo que inicializa y da valores de texto a cada componente
	 */
	private void iniciarComponentes() {
		JPanel_Cabecera = new JPanel();
		JPanel_Botones = new JPanel();		
		Btn_Filtrar = new JButton("Filtrar");
		Btn_Volver = new JButton("Volver");
		Btn_Config = new JButton("Config");
		Btn_Inicio = new JButton("Inicio");
		Btn_Ayuda = new JButton("Ayuda");
		Btn_Logout = new JButton("Logout"); 		
		lblLogo = new JLabel(new ImageIcon("assets/logoVentana.png"));
		lblUser = new JLabel(miEmpleado.getNombre()+" "+miEmpleado.getApellidos());
		lblAlumnos = new JLabel("Profesores - Buscar");
		iconoConfig = new ImageIcon("assets/LogoConfig.png");
		iconoAyuda = new ImageIcon("assets/LogoAyuda.png");
		iconoInicio = new ImageIcon("assets/LogoInicio.png");
		iconoLogout = new ImageIcon("assets/LogoLogout.png");
	}


	
	/**
	 * M�todo que registra la actividad de los botones y realiza acciones seg�n
	 * el bot�n seleccionado y el escuchador asignado al mismo
	 */
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		VProfesorGenerico profesorNuevo;
		VProfesorFicha profesorFicha;
		Inicio ventanaInicio;	
		String txtBtn = e.getActionCommand();	
		switch (txtBtn) {
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
			case "Filtrar":
				this.setVisible(false);
				this.dispose();
				profesorFicha = new VProfesorFicha(miEmpleado);
				break;
			case "Volver":
				this.setVisible(false);
				this.dispose();
				profesorNuevo = new VProfesorGenerico(miEmpleado);
				break;
		}
	}

	
	
	/**
	 * M�todo que registra la actividad de los botones y realiza acciones seg�n
	 * el bot�n seleccionado y el escuchador asignado al mismo
	 */
	@SuppressWarnings("unused")
	@Override
	public void mouseClicked(MouseEvent e) {
		VProfesorFicha profesorFicha;
		Login login;
		Component txtBtn = e.getComponent();
		if(txtBtn==Btn_Logout) {
			this.setVisible(false);
			this.dispose();
			miEmpleado = null;
			login = new Login();
		} else {
	        int row = table.getSelectedRow();
	        int i = table.getSelectedColumn();
			table.getValueAt(row,i);
			this.setVisible(false);
			profesorFicha = new VProfesorFicha(miEmpleado, listaEmpleados.get(row));    //, listaAlumnos.get(row),this,ventanaPropuesta);
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