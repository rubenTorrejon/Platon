package views;

import javax.swing.*;

import common.RellenadorComboBox;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;

import dao.*;
import models.*;

/**
 * Clase de vista que genera una ventana de grupo nuevo, donde podremos registrar
 * un grupo en la BDD
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
@SuppressWarnings("serial")
public class VGrupoNuevo extends JFrame implements ActionListener, MouseListener{

	private JPanel JPanel_Cabecera, JPanel_Botones, Jpanel_Formulario;
	private JButton Btn_Logout, Btn_Inicio, Btn_Config, Btn_Ayuda, Btn_Limpiar, 
		Btn_Registrar, Btn_Volver, Btn_Modificar, Btn_Add, Btn_Quitar;
	private JLabel lblLogo, lblUser, lblAlumnos;
	private JLabel lbl_Form_0, lbl_Form_1, lbl_Form_2, lbl_Form_3, 
		lbl_Form_4, lbl_Form_5, lbl_Form_6, lbl_Form_7, lbl_Form_8;	
	private ImageIcon iconoConfig, iconoAyuda, iconoInicio, iconoLogout;
	private JTextField TF_Nombre;
	private JComboBox<String> CB_Aula, CB_Profesor, CB_MesFin, CB_YearFin, 
		CB_Dia, CB_HoraInicio, CB_HoraFin, CB_MesInicio, CB_YearInicio;	
	
    private Calendar fecha = Calendar.getInstance();
    private int actualYear = fecha.get(Calendar.YEAR);
    private int yearFuture = actualYear + 5;
    private JTable table;
    
    private Empleado miEmpleado;
    private Empleado miProfesor;
    private Aula miAula;
    private Grupo miGrupo;
    private HorarioDAO miHorarioDao = new HorarioDAO();
    private AulaDAO miAulaDao = new AulaDAO();
    private GrupoDAO miGrupoDao = new GrupoDAO();
    private EmpleadoDAO miEmpleadoDao = new EmpleadoDAO();
    private RellenadorComboBox rellenador = new RellenadorComboBox();
    protected ArrayList<Horario> listaHorarios = miHorarioDao.buscarHorarios(0);
	protected String info[][];
	
    
	
	/**
	 * Constructor por defecto
	 * 
	 * @wbp.parser.constructor
	 */
	public VGrupoNuevo(Empleado miEmpleado) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		this.miEmpleado = miEmpleado;
		initialize();
	}
	
	
	
	/**
	 * Contructor por parámetros
	 * 
	 * @wbp.parser.constructor
	 */
	public VGrupoNuevo(Empleado miEmpleado, Grupo miGrupo) {
		getContentPane().setBackground(new java.awt.Color(27, 38, 79));
		this.miEmpleado = miEmpleado;
		this.miGrupo = miGrupo;
		initialize();
		rellenarComponentesDeGrupo(miGrupo);
		Btn_Modificar.setEnabled(true);
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
		this.rellenarComboBoxes();
		this.establecerLayouts();
		this.establecerColores();
		this.posicionarComponentes();
		this.establecerFuentes();
		this.incluirComponentes();
		this.rellenarTabla();
		this.setVisible(true);
	}
	
	
	
	/**
	 * Método que lee los parámetros del objeto y los asigna a los TextFields
	 * correspondientes a dicho parámetro
	 * 
	 * @param miGrupo
	 */
	private void rellenarComponentesDeGrupo(Grupo miGrupo) {
		miProfesor = miEmpleadoDao.buscarEmpleadoById(miGrupo.getFkIdEmpleado());
		miAula = miAulaDao.buscarAulaById(miGrupo.getFkIdAula());
		TF_Nombre.setText(miGrupo.getNombre());
		CB_Aula.setSelectedItem(miAula.getNombre());
		CB_Profesor.setSelectedItem(miProfesor.getApellidos()+", "+miProfesor.getNombre());
		CB_MesInicio.setSelectedItem(miGrupo.getMesInicio());
		CB_YearInicio.setSelectedItem(miGrupo.getYearInicio());
		CB_MesFin.setSelectedItem(miGrupo.getMesFinal());
		CB_YearFin.setSelectedItem(miGrupo.getYearFinal());
		rellenarTabla();
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
		Jpanel_Formulario.add(lbl_Form_4);
		Jpanel_Formulario.add(lbl_Form_5);
		Jpanel_Formulario.add(lbl_Form_6);
		Jpanel_Formulario.add(lbl_Form_7);
		Jpanel_Formulario.add(lbl_Form_8);
		Jpanel_Formulario.add(Btn_Quitar);
		Jpanel_Formulario.add(Btn_Add);
		Jpanel_Formulario.add(TF_Nombre);
		Jpanel_Formulario.add(CB_Aula);
		Jpanel_Formulario.add(CB_Profesor);
		Jpanel_Formulario.add(CB_MesInicio);
		Jpanel_Formulario.add(CB_YearInicio);
		Jpanel_Formulario.add(CB_MesFin);
		Jpanel_Formulario.add(CB_YearFin);
		Jpanel_Formulario.add(CB_Dia);
		Jpanel_Formulario.add(CB_HoraInicio);
		Jpanel_Formulario.add(CB_HoraFin);	
	}
	
	
	
	/**
	 * Método que llama al objeto de la clase RellenadorComboBoxes y
	 * rellena cada uno de los ComboBoxes de la ventana. Este método
	 * está apartado para no hacer demasiado grande el método addComponentes 
	 */
	private void rellenarComboBoxes() {
		rellenador.addAulas(CB_Aula);
		rellenador.addProfesores(CB_Profesor);
		rellenador.addMeses(CB_MesInicio);
		rellenador.addYears(CB_YearInicio,actualYear,yearFuture);
		rellenador.addMeses(CB_MesFin);
		rellenador.addYears(CB_YearFin,actualYear,yearFuture);
		rellenador.addDiasSemana(CB_Dia);
		rellenador.addHoras(CB_HoraInicio);
		rellenador.addHoras(CB_HoraFin);		
	}
	
	
	
	/**
	 * Método que, obteniendo la lista correspondiente, genera una matriz de datos en forma de
	 * tabla y la completa con los nombres de cabecera y los valores de la tabla
	 */
	private void rellenarTabla() {
		int idGrupo = 0;
		if(miGrupo!=null) idGrupo = miGrupo.getIdGrupo();
		listaHorarios = miHorarioDao.buscarHorarios(idGrupo);
		info = new String[listaHorarios.size()][3];
		String[] nombresColumnas = {"Día","Hora Inicio","Hora Final"};
		for (int i = 0; i < info.length; i++) {
			info[i][0] = listaHorarios.get(i).getDia();
			info[i][1] = listaHorarios.get(i).getHoraInicio();
			info[i][2] = listaHorarios.get(i).getHoraFinal();
		}
		table = new JTable(info, nombresColumnas);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		table.setBounds(0, 0, 510, 209);
		table.addMouseListener(this);
		JScrollPane scrollPane = new  JScrollPane(table);
		scrollPane.setBackground(new java.awt.Color(244, 162, 97));
		scrollPane.setBounds(230, 206, 965, 133);
		Jpanel_Formulario.add(scrollPane);
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
		Btn_Add.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Btn_Quitar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAlumnos.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lbl_Form_0.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_5.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_6.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_7.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lbl_Form_8.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		CB_MesInicio.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_YearInicio.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_MesFin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_YearFin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_Dia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_HoraInicio.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_HoraFin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_Aula.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CB_Profesor.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TF_Nombre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
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
		Btn_Add.setBounds(230, 155, 103, 40);
		Btn_Quitar.setBounds(345, 155, 103, 40);
		lblUser.setBounds(881, 18, 330, 35);
		lblAlumnos.setBounds(861, 119, 350, 45);
		lblLogo.setBounds(0, 0, 618, 175);
		lbl_Form_0.setBounds(10, 0, 200, 50);
		lbl_Form_1.setBounds(10, 50, 200, 50);
		lbl_Form_2.setBounds(682, 50, 200, 50);
		lbl_Form_3.setBounds(10, 100, 200, 50);
		lbl_Form_4.setBounds(230, 105, 115, 40);
		lbl_Form_5.setBounds(585, 105, 115, 40);
		lbl_Form_6.setBounds(941, 105, 115, 40);
		lbl_Form_7.setBounds(10, 350, 200, 50);
		lbl_Form_8.setBounds(682, 350, 200, 50);
		CB_Profesor.setBounds(895, 55, 300, 40);
		CB_Aula.setBounds(230, 55, 300, 40);
		CB_Dia.setBounds(345, 105, 140, 40);
		CB_MesInicio.setBounds(230, 355, 140, 40);
		CB_YearInicio.setBounds(390, 355, 140, 40);
		CB_MesFin.setBounds(892, 355, 140, 40);
		CB_YearFin.setBounds(1055, 355, 140, 40);
		CB_HoraInicio.setBounds(700, 105, 140, 40);
		CB_HoraFin.setBounds(1055, 105, 140, 40);
		TF_Nombre.setBounds(230, 5, 965, 40);		
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
		Btn_Add.addActionListener(this);
		Btn_Quitar.addActionListener(this);
		Btn_Registrar.setFocusPainted(false);
		Btn_Volver.setFocusPainted(false);
		Btn_Config.setFocusPainted(false);
		Btn_Ayuda.setFocusPainted(false);
		Btn_Inicio.setFocusPainted(false);
		Btn_Logout.setFocusPainted(false);
		Btn_Modificar.setFocusPainted(false);
		Btn_Limpiar.setFocusPainted(false);
		Btn_Add.setFocusPainted(false);
		Btn_Quitar.setFocusPainted(false);
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
		table = new JTable();
		Btn_Limpiar = new JButton("Limpiar");
		Btn_Registrar = new JButton("Registrar");
		Btn_Volver = new JButton("Volver");
		Btn_Config = new JButton("Config");
		Btn_Inicio = new JButton("Inicio");
		Btn_Ayuda = new JButton("Ayuda");
		Btn_Logout = new JButton("Logout");
		Btn_Modificar = new JButton("Modificar");
		Btn_Add = new JButton("Añadir");
		Btn_Quitar = new JButton("Quitar");
		lblLogo = new JLabel(new ImageIcon("assets/logoVentana.png"));
		lblUser = new JLabel(miEmpleado.getNombre()+" "+miEmpleado.getApellidos());
		lblAlumnos = new JLabel("Grupos - Nuevo");
		lbl_Form_0 = new JLabel("Nombre");
		lbl_Form_1 = new JLabel("Aula");
		lbl_Form_2 = new JLabel("Profesor");
		lbl_Form_3 = new JLabel("Horario");
		lbl_Form_4 = new JLabel("Día");
		lbl_Form_5 = new JLabel("H. Inicio");
		lbl_Form_6 = new JLabel("H. Fin");
		lbl_Form_7 = new JLabel("Fecha de inicio");
		lbl_Form_8 = new JLabel("Fecha de fin");
		CB_Aula = new JComboBox<String>();
		CB_Profesor = new JComboBox<String>();
		CB_MesInicio = new JComboBox<String>();
		CB_YearInicio = new JComboBox<String>();
		CB_MesFin = new JComboBox<String>();
		CB_YearFin = new JComboBox<String>();
		CB_Dia = new JComboBox<String>();
		CB_HoraInicio = new JComboBox<String>();
		CB_HoraFin = new JComboBox<String>();		
		TF_Nombre = new JTextField();
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
		VGrupoBuscar grupooBuscar;
		Inicio ventanaInicio;
		String txtBtn = e.getActionCommand();
		switch (txtBtn) {
			case "Registrar":
				if(TF_Nombre.getText() == "")
					JOptionPane.showMessageDialog(this, "ERROR: Debe establecer un nombre para el grupo");
				else if(CB_Aula.getSelectedItem().toString() == "Aula")
					JOptionPane.showMessageDialog(this, "ERROR: Debe asignar un aula para el grupo");
				else if(CB_Profesor.getSelectedItem().toString() == "Profesor")
					JOptionPane.showMessageDialog(this, "ERROR: Debe asignar un profesor para el grupo");
				else {
					String nombreCompletoREG = CB_Profesor.getSelectedItem().toString();
					String[] partesREG = nombreCompletoREG.split(",");
					String apellidosREG = partesREG[0].trim();	
					String nombreREG = partesREG[1].trim();
					miGrupoDao.addGrupo(
						miEmpleadoDao.buscarEmpleadoPorNombre(nombreREG, apellidosREG),
						miAulaDao.buscarAulaPorNombre(CB_Aula.getSelectedItem().toString()),
						TF_Nombre.getText(),
						CB_MesInicio.getSelectedItem().toString(),
						CB_YearInicio.getSelectedItem().toString(),
						CB_MesFin.getSelectedItem().toString(),
						CB_YearFin.getSelectedItem().toString());
					int idGrupo = miGrupoDao.buscarGrupoPorNombre(TF_Nombre.getText());
					miHorarioDao.horarioDefinitivo(idGrupo);				
					JOptionPane.showMessageDialog(this, "Grupo registrado con éxito");
				reiniciaLabels();
				}
				break;
			case "Modificar":
				String nombreCompletoMOD = CB_Profesor.getSelectedItem().toString();
				String[] partesMOD = nombreCompletoMOD.split(",");
				String apellidosMOD = partesMOD[0].trim();	
				String nombreMOD = partesMOD[1].trim();
				miGrupo = miGrupoDao.modificarGrupo(
					miEmpleadoDao.buscarEmpleadoPorNombre(nombreMOD, apellidosMOD),
					miAulaDao.buscarAulaPorNombre(CB_Aula.getSelectedItem().toString()),
					TF_Nombre.getText(),
					CB_MesInicio.getSelectedItem().toString(),
					CB_YearInicio.getSelectedItem().toString(),
					CB_MesFin.getSelectedItem().toString(),
					CB_YearFin.getSelectedItem().toString(),
					miGrupo);
				miHorarioDao.horarioDefinitivo(miGrupo.getIdGrupo());
				JOptionPane.showMessageDialog(this, "Datos del grupo modificados con éxito");
				break;
			case "Añadir":
				miHorarioDao.addHorarioProvisional(
					CB_Dia.getSelectedItem().toString(),
					CB_HoraInicio.getSelectedItem().toString(),
					CB_HoraFin.getSelectedItem().toString());
				rellenarTabla();
				break;
			case "Quitar":
				miHorarioDao.borrarHorarioDeGrupo(miGrupo.getIdGrupo());
				rellenarTabla();				
				break;
			case "Inicio":
				miHorarioDao.eliminarProvisionales();
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
				miHorarioDao.eliminarProvisionales();
				this.setVisible(false);
				this.dispose();
				grupoGenerico = new VGrupoGenerico(miEmpleado);
				break;
			case "Limpiar":
				miHorarioDao.eliminarProvisionales();
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
		CB_Aula.setSelectedItem("Aula");
		CB_Profesor.setSelectedItem("Profesor");
		CB_MesInicio.setSelectedItem("Mes");
		CB_YearInicio.setSelectedItem("Año");
		CB_MesFin.setSelectedItem("Mes");
		CB_YearFin.setSelectedItem("Año");
		CB_Dia.setSelectedItem("Día");
		CB_HoraInicio.setSelectedItem("Hora");
		CB_HoraFin.setSelectedItem("Hora");
		miGrupo = new Grupo();
		rellenarTabla();
	}
	
	
	/**
	 * Método que registra la actividad de los botones y realiza acciones según
	 * el botón seleccionado y el escuchador asignado al mismo
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		miHorarioDao.eliminarProvisionales();
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