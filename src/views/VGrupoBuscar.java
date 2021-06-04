package views;

import javax.swing.*;

import models.Empleado;
import models.Grupo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Clase de vista que genera una ventana de búsqueda de grupos, con una tabla que obtiene los
 * grupos generados según los parámetros de búsqueda que se hayan decidido
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
@SuppressWarnings("serial")
public class VGrupoBuscar extends JFrame implements ActionListener, MouseListener{

	private JPanel JPanel_Cabecera, JPanel_Botones;
	private JButton Btn_Volver, Btn_Inicio, Btn_Config, Btn_Ayuda, Btn_Logout;
	private JLabel lblLogo, lblUser, lblAlumnos;
	private ImageIcon iconoConfig, iconoAyuda, iconoInicio, iconoLogout;
	
	private Empleado miEmpleado;	
	protected ArrayList<Grupo> miListaGrupos;
	protected String info[][];
	private JTable table;

	
	
	/**
	 * Constructor por defecto de la ventana Login
	 */
	public VGrupoBuscar(Empleado miEmpleado, ArrayList<Grupo> miListaGrupos) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		getContentPane().setForeground(Color.BLACK);
		this.miListaGrupos = miListaGrupos;
		this.miEmpleado = miEmpleado;
		initialize();
	}
	


	/**
	 * Contrucción gráfica de la ventana
	 */
	private void initialize() {
		this.setBounds(0, 0, 1240, 720);
		this.setTitle("PLATON - GRUPOS");
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
		info = new String[miListaGrupos.size()][7];
		String[] nombresColumnas = {"Nombre","Aula","Profesor","Mes Inicio","Año Inicio","Mes Final","Año Final"};
		for (int i = 0; i < info.length; i++) {
			info[i][0] = miListaGrupos.get(i).getNombre();
			info[i][1] = miListaGrupos.get(i).getFkIdAula()+"";
			info[i][2] = miListaGrupos.get(i).getFkIdEmpleado()+"";
			info[i][3] = miListaGrupos.get(i).getMesInicio();
			info[i][4] = miListaGrupos.get(i).getYearInicio();
			info[i][5] = miListaGrupos.get(i).getMesFinal();
			info[i][6] = miListaGrupos.get(i).getYearFinal();
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
		Btn_Volver.setForeground(Color.WHITE);
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
		Btn_Volver.addActionListener(this);
		Btn_Ayuda.addActionListener(this);
		Btn_Inicio.addActionListener(this);
		Btn_Logout.addMouseListener(this);
		Btn_Config.addActionListener(this);
		Btn_Volver.setFocusPainted(false);
		Btn_Config.setFocusPainted(false);
		Btn_Ayuda.setFocusPainted(false);
		Btn_Inicio.setFocusPainted(false);
		Btn_Logout.setFocusPainted(false);
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
		lblLogo = new JLabel(new ImageIcon("assets/logoVentana.png"));
		lblUser = new JLabel(miEmpleado.getNombre()+" "+miEmpleado.getApellidos());
		lblAlumnos = new JLabel("Grupos - Buscar");
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
		VGrupoGenerico grupoGenerico;
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
			case "Volver":
				this.setVisible(false);
				this.dispose();
				grupoGenerico = new VGrupoGenerico(miEmpleado);
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
		VGrupoNuevo grupoNuevo;
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
			grupoNuevo = new VGrupoNuevo(miEmpleado, miListaGrupos.get(row));
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