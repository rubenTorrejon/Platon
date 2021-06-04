package views;

import javax.swing.*;
import common.RellenadorComboBox;
import dao.*;
import models.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Clase de vista que genera una ventana de asignar alumno, donde el usuario podrá matricular un
 * alumno en un grupo, siempre que no esté ya matriculado y el alumno no esté dado de baja, 
 * o podrá quitar un alumno de un grupo
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
@SuppressWarnings("serial")
public class VAlumnoAsignar extends JFrame implements ActionListener, MouseListener{

	private JPanel JPanel_Cabecera, JPanel_Botones, Jpanel_Formulario, JPanel_Formulario2;
	private JButton Btn_Registrar, Btn_Volver, Btn_Inicio, Btn_Config, 
		Btn_Ayuda, Btn_Logout, Btn_Quitar, Btn_Refrescar;
	private JLabel lbl_Form_0, lbl_Form_1, lbl_Form_2, lbl_Form_3, lblAlumnos, lblLogo, lblUser;
	private JTextField TF_Aula, TF_PlazasLibres;
	private JComboBox<String> CB_Alumno, CB_Grupo;
	private ImageIcon iconoConfig, iconoAyuda, iconoInicio, iconoLogout;
	private ArrayList<AlumnoGrupo> listaAlumnos;
	private String info[][];

	private JTable table;
	private int idGrupo;
    private Empleado miEmpleado;
    private AlumnoGrupoDAO miAlumnoGrupoDao = new AlumnoGrupoDAO();
    private AlumnoDAO miAlumnoDao = new AlumnoDAO();
    private GrupoDAO miGrupoDao = new GrupoDAO();
    private RellenadorComboBox rellenador = new RellenadorComboBox();

	
	/**
	 * Constructor por defecto de la ventana
	 * @wbp.parser.constructor
	 */
	public VAlumnoAsignar(Empleado miEmpleado) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		getContentPane().setForeground(Color.BLACK);
		this.miEmpleado = miEmpleado;
		initialize();
	}
	
	
	
	/**
	 * Constructor de la ventana con los TextFields completados según los
	 * valores de un alumno que recibimos por parámetro
	 *  
	 * @param miEmpleado
	 * @param miAlumno
	 */
	public VAlumnoAsignar(Empleado miEmpleado, Alumno miAlumno) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		getContentPane().setForeground(Color.BLACK);
		this.miEmpleado = miEmpleado;
		initialize();
		CB_Alumno.setSelectedItem(miAlumno.getApellidos().toString()+", "+miAlumno.getNombre().toString());
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
		table = new JTable();
		if(idGrupo != 0) {
			listaAlumnos = miAlumnoGrupoDao.buscarAlumnosActivosPorGrupo(idGrupo);
		}
		info = new String[listaAlumnos.size()][3];
		String[] nombresColumnas = {"Nombre","Apellidos","Fecha de alta"};
		for (int i = 0; i < info.length; i++) {
			info[i][0] = listaAlumnos.get(i).getNombreAlumno();
			info[i][1] = listaAlumnos.get(i).getApellidosAlumno();
			info[i][2] = listaAlumnos.get(i).getFechaInicio()+"";
		}
		table = new JTable(info, nombresColumnas);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table.setBounds(0, 0, 795, 400);
		table.addMouseListener(this);
		JScrollPane scrollPane = new  JScrollPane(table);
		scrollPane.setBackground(new java.awt.Color(244, 162, 97));
		scrollPane.setBounds(0, 0, 795, 400);
		JPanel_Formulario2.add(scrollPane);
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
		JPanel_Botones.add(Btn_Quitar);
		JPanel_Botones.add(Btn_Registrar);
		JPanel_Botones.add(Btn_Volver);
		JPanel_Botones.add(Jpanel_Formulario);
		JPanel_Botones.add(JPanel_Formulario2);
		Jpanel_Formulario.add(lbl_Form_0);
		Jpanel_Formulario.add(lbl_Form_1);
		Jpanel_Formulario.add(lbl_Form_2);
		Jpanel_Formulario.add(lbl_Form_3);
		Jpanel_Formulario.add(CB_Alumno);
		Jpanel_Formulario.add(CB_Grupo);
		Jpanel_Formulario.add(TF_Aula);
		Jpanel_Formulario.add(TF_PlazasLibres);
		JPanel_Botones.add(Btn_Refrescar);
		Btn_Logout.setIcon(iconoLogout);
		Btn_Config.setIcon(iconoConfig);
		Btn_Ayuda.setIcon(iconoAyuda);
		Btn_Inicio.setIcon(iconoInicio);
		rellenador.addAlumnos(CB_Alumno);
		rellenador.addGrupos(CB_Grupo);		
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
		Btn_Quitar.setFont(new Font("Times New Roman", Font.BOLD, 25));
		Btn_Refrescar.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAlumnos.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAlumnos.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lbl_Form_0.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		TF_Aula.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_PlazasLibres.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_Alumno.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_Grupo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	}



	/**
	 * Método para posicionar de forma estática y con valores fijos los componentes dentro
	 * de cada ventana. Establece una cuaterna de píxeles para cada componente.
	 */
	private void posicionarComponentes() {
		JPanel_Cabecera.setBounds(0, 0, 1224, 175);
		JPanel_Botones.setBounds(0, 175, 1224, 506);
		Jpanel_Formulario.setBounds(815, 11, 399, 398);
		JPanel_Formulario2.setBounds(10, 10, 795, 400);
		Btn_Logout.setBounds(1161, 64, 50, 50);
		Btn_Ayuda.setBounds(1041, 64, 50, 50);
		Btn_Inicio.setBounds(1101, 64, 50, 50);
		Btn_Config.setBounds(981, 64, 50, 50);
		Btn_Registrar.setBounds(905, 420, 150, 75);
		Btn_Volver.setBounds(1065, 420, 150, 75);
		Btn_Quitar.setBounds(585, 420, 150, 75);		
		Btn_Refrescar.setBounds(745, 420, 150, 75);
		TF_Aula.setBounds(10, 245, 380, 40);
		TF_PlazasLibres.setBounds(10, 345, 380, 40);
		CB_Alumno.setBounds(10, 55, 380, 40);
		CB_Grupo.setBounds(10, 155, 380, 40);
		lbl_Form_0.setBounds(10, 0, 200, 50);
		lbl_Form_1.setBounds(10, 100, 200, 50);
		lbl_Form_2.setBounds(10, 200, 200, 50);
		lbl_Form_3.setBounds(10, 300, 200, 50);
		lblAlumnos.setBounds(861, 119, 350, 45);
		lblLogo.setBounds(0, 0, 618, 175);
		lblUser.setBounds(881, 18, 330, 35);
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
		Btn_Refrescar.setForeground(Color.BLACK);
		Btn_Volver.setForeground(Color.WHITE);
		Btn_Quitar.setForeground(Color.BLACK);
		Btn_Quitar.setBackground(new Color(255, 112, 112));
		Btn_Registrar.setForeground(Color.BLACK);
		Btn_Registrar.setBackground(new java.awt.Color(58, 207, 65));
		Btn_Logout.setBackground(Color.RED);
		Btn_Volver.setBackground(new java.awt.Color(87, 108, 168));
		Btn_Refrescar.setBackground(new Color(255, 255, 127));
	}
		
	
	
	/**
	 * Método para asignar propiedades específicas a compoentes en la ventana
	 */
	private void establecerLayouts() {
		JPanel_Botones.setLayout(null);
		JPanel_Cabecera.setLayout(null);
		JPanel_Formulario2.setLayout(null);
		Jpanel_Formulario.setLayout(null);	
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlumnos.setHorizontalAlignment(SwingConstants.RIGHT);
		Btn_Config.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Ayuda.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Inicio.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Logout.setHorizontalAlignment(SwingConstants.CENTER);
		Btn_Registrar.addActionListener(this);
		Btn_Volver.addActionListener(this);
		Btn_Ayuda.addActionListener(this);
		Btn_Inicio.addActionListener(this);
		Btn_Logout.addMouseListener(this);
		Btn_Config.addActionListener(this);
		Btn_Refrescar.addActionListener(this);
		CB_Grupo.addActionListener(this);
		Btn_Quitar.addActionListener(this);
		Btn_Refrescar.setFocusPainted(false);
		Btn_Registrar.setFocusPainted(false);
		Btn_Volver.setFocusPainted(false);
		Btn_Config.setFocusPainted(false);
		Btn_Ayuda.setFocusPainted(false);
		Btn_Inicio.setFocusPainted(false);
		Btn_Logout.setFocusPainted(false);
		Btn_Quitar.setFocusPainted(false);
		Btn_Registrar.setEnabled(false);
		TF_Aula.setEditable(false);
		TF_PlazasLibres.setEditable(false);
		Btn_Config.setVisible(false);
	}
	
	
	/**
	 * Método que inicializa y da valores de texto a cada componente
	 */
	private void iniciarComponentes() {
		JPanel_Cabecera = new JPanel();
		JPanel_Botones = new JPanel();
		Jpanel_Formulario = new JPanel();
		Btn_Registrar = new JButton("Asignar");
		Btn_Volver = new JButton("Volver");
		Btn_Config = new JButton("Config");
		Btn_Inicio = new JButton("Inicio");
		Btn_Ayuda = new JButton("Ayuda");
		Btn_Logout = new JButton("Logout");
		Btn_Quitar = new JButton("Quitar");
		Btn_Refrescar = new JButton("Refrescar");
		CB_Alumno = new JComboBox<String>();
		CB_Grupo = new JComboBox<String>();
		TF_Aula = new JTextField();
		TF_PlazasLibres = new JTextField();
		TF_PlazasLibres.setText("");
		lbl_Form_0 = new JLabel("Alumno");
		lbl_Form_1 = new JLabel("Grupo");
		lbl_Form_2 = new JLabel("Aula");
		lbl_Form_3 = new JLabel("Plazas libres");
		lblLogo = new JLabel(new ImageIcon("assets/logoVentana.png"));
		lblUser = new JLabel(miEmpleado.getNombre()+" "+miEmpleado.getApellidos());
		lblAlumnos = new JLabel("Alumnos - Asignar");
		JPanel_Formulario2 = new JPanel();
		iconoConfig = new ImageIcon("assets/LogoConfig.png");
		iconoAyuda = new ImageIcon("assets/LogoAyuda.png");
		iconoInicio = new ImageIcon("assets/LogoInicio.png");
		iconoLogout = new ImageIcon("assets/LogoLogout.png");
		listaAlumnos = new ArrayList<AlumnoGrupo>();
	}
	
	
	
	/**
	 * Método que capta la acción del puntero del ratón en la pantalla y ejecuta la
	 * acción programada. Al pulsar el botón login, leerá de la BBDD el rol del user
	 * que intenta conectarse, e iniciará la pantalla correspondiente a ese rol.
	 * En caso de que la dupla user-password establecida no exista en la BBDD, volverá
	 * visible el TF de usuario incorrecto.
	 * @param e
	 */
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		VAlumnoGenerico alumnoNuevo;
		VAlumnoAsignar alumnoAsignar;
		Inicio ventanaInicio;
		String txtBtn = e.getActionCommand();
		switch (txtBtn) {
			case "Asignar":
				int alumno = 0;
				int grupo = 0;
				if(CB_Alumno.getSelectedItem().toString() != "Alumno") {
					String nombreCompletoREG = CB_Alumno.getSelectedItem().toString();
					String[] partesREG = nombreCompletoREG.split(",");
					String apellidosREG = partesREG[0].trim();	
					String nombreREG = partesREG[1].trim();
					alumno = miAlumnoDao.buscarAlumnoPorNombre(nombreREG, apellidosREG).getIdAlumno();
				} else {
					JOptionPane.showMessageDialog(this, "ERROR: Debe seleccionar un alumno.");
				}
				if(CB_Grupo.getSelectedItem().toString() != "Grupo") {
					grupo = miGrupoDao.buscarGrupoPorNombre(CB_Grupo.getSelectedItem().toString());
				} else {
					JOptionPane.showMessageDialog(this, "ERROR: Debe seleccionar un grupo.");
				}
				if(alumno!=0 && grupo!=0) {
					Boolean alumnoEnGrupo = miAlumnoGrupoDao.estaEnGrupo(alumno, grupo);
					if(alumnoEnGrupo)
						JOptionPane.showMessageDialog(this, "ERROR: El alumno seleccionado ya está matriculado en el grupo.");
					else if(TF_PlazasLibres.getText().startsWith("0"))
						JOptionPane.showMessageDialog(this, "ERROR: No quedan plazas libres en el grupo seleccionado.");
					else {
						miAlumnoGrupoDao.addAlumnoGrupo(alumno, grupo);
						JOptionPane.showMessageDialog(this, "Alumno registrado en el grupo con éxito.");
						TF_PlazasLibres.setText(miAlumnoGrupoDao.plazasLibres(idGrupo));
					}
				}
				rellenarTabla();
				break;
			case "Quitar":
				int idAlumnoQuitar = 0;
				int idGrupoQuitar = 0;
				if(CB_Alumno.getSelectedItem().toString() != "Alumno") {
					String nombreCompletoREG = CB_Alumno.getSelectedItem().toString();
					String[] partesREG = nombreCompletoREG.split(",");
					String apellidosREG = partesREG[0].trim();	
					String nombreREG = partesREG[1].trim();
					idAlumnoQuitar = miAlumnoDao.buscarAlumnoPorNombre(nombreREG, apellidosREG).getIdAlumno();
				} else {
					JOptionPane.showMessageDialog(this, "ERROR: Debe seleccionar un alumno.");
				}
				if(CB_Grupo.getSelectedItem().toString() != "Grupo") {
					idGrupoQuitar = miGrupoDao.buscarGrupoPorNombre(CB_Grupo.getSelectedItem().toString());
				} else {
					JOptionPane.showMessageDialog(this, "ERROR: Debe seleccionar un grupo.");
				}
				if(idAlumnoQuitar!=0 && idGrupoQuitar!=0) {
					Boolean alumnoEnGrupo = miAlumnoGrupoDao.estaEnGrupo(idAlumnoQuitar, idGrupoQuitar);
					if(alumnoEnGrupo) {
						miAlumnoGrupoDao.quitarAlumnoGrupo(idAlumnoQuitar, idGrupoQuitar);
						JOptionPane.showMessageDialog(this, "Alumno eliminado del grupo con éxito.");
						TF_PlazasLibres.setText(miAlumnoGrupoDao.plazasLibres(idGrupoQuitar));
					}	
					else {
						JOptionPane.showMessageDialog(this, "El alumno seleccionado no está actualmente en ese grupo.");
					}
				}
				rellenarTabla();
				break;
			case "Refrescar":
				if(CB_Grupo.getSelectedIndex()>0) {
					idGrupo = miGrupoDao.buscarGrupoPorNombre(CB_Grupo.getSelectedItem().toString());
					TF_Aula.setText(miGrupoDao.buscarAulaPorGrupo(idGrupo));
					TF_PlazasLibres.setText(miAlumnoGrupoDao.plazasLibres(idGrupo));
					Btn_Registrar.setEnabled(true);
				} else {
					TF_Aula.setText("");
					TF_PlazasLibres.setText("");
				}
				rellenarTabla();
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
				alumnoNuevo = new VAlumnoGenerico(miEmpleado);
				break;
		}
	}	
	
	
	
	/**
	 * Método que capta la acción del puntero del ratón en la pantalla y ejecuta la
	 * acción programada. Al pulsar el botón login, leerá de la BBDD el rol del user
	 * que intenta conectarse, e iniciará la pantalla correspondiente a ese rol.
	 * En caso de que la dupla user-password establecida no exista en la BBDD, volverá
	 * visible el TF de usuario incorrecto.
	 * @param e
	 */
	@SuppressWarnings("unused")
	@Override
	public void mouseClicked(MouseEvent e) {
		VAlumnoNuevo alumnoNuevo;
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