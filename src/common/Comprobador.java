package common;

import javax.swing.JComboBox;

/**
 * Clase que centraliza todas las comprobaciones y las expresiones regulares para que, cada vez
 * que se va a registrar un valor en la base de datos, garantizar que dichos valores son de formato
 * correcto. La función de esta clase es evitar duplicidades en el código en todas las ventanas
 * que sirvan para registros.
 * 
 * @author Rubén Torrejón Espinosa
 *
 */
public class Comprobador {
	
	/**
	 * Constructor por defecto
	 */
	public Comprobador() {
	}
	
	
	
	/**
	 * Comportamiento que comprueba que una cadena de string seleccionada tiene el formato
	 * de un teléfono fijo o móvil español.
	 * @param telefono
	 * @return true or false
	 */
	public boolean esUnTelefono(String telefono) {
		boolean tfno = false;
		if(telefono.length()!=0) {
	        try {
	            int numero = Integer.parseInt(telefono);
	            if(numero>=600000000 && numero<=999999999)
	            tfno = true;
	        } catch (NumberFormatException excepcion) {
	        	tfno = false;
	        }
		} else {
			tfno = true;
		}
		return tfno;
	}
	
	
	
	/**
	 * Comportamiento que comprueba que una cadena de texto seleccionada tiene un formato
	 * compatible con un DNI español
	 * @param String documento
	 * @return true or false
	 */
	public boolean esUnDni(String documento) {
		
		boolean dni = false;
		
		if(documento.length()!=0) {
			if (documento.length() == 9) {
				String numero = documento.substring(0, 8);
				try {
					Integer.parseInt(numero); 
					dni = true;	
				} 
				catch (Exception e) {
				}
			}
		} else {
			dni = true;
		}
		return dni;
	}

	
	
	/**
	 * Comportamiento que recibe 2 valores de formato no date pero correspondientes a una fecha
	 * y las compara para determinar que una de las fechas es posterior a la otra
	 * @param miMesInicial
	 * @param miAnnoInicial
	 * @param miMesFinal
	 * @param miAnnoFinal
	 * @return true or false
	 */
	public boolean comprobarFechas(
			JComboBox<String> miMesInicial,JComboBox<String> miAnnoInicial,
			JComboBox<String> miMesFinal,JComboBox<String> miAnnoFinal
			) {
		Boolean fechaCorrecta = false;
		
		int MI = miMesInicial.getSelectedIndex();
		int AI = miMesInicial.getSelectedIndex();
		int MF = miMesInicial.getSelectedIndex();
		int AF = miMesInicial.getSelectedIndex();
		
		if(AF>AI)
			fechaCorrecta = true;
		else if(AF == AI && MF > MI)
			fechaCorrecta = true;		
		return fechaCorrecta;
	}
	
}
