package Ahorcado;

import java.io.BufferedReader;
import java.io.FileReader;

public class Ahorcado {
	private String palabra;
	private Conjunto<String> palabras;
	private Conjunto<String> letrasUsadas;
	private int intentosRestantes;
	private int guiones;
	
	public Ahorcado() {
		palabras = new Conjunto<String>();
		letrasUsadas = new Conjunto<String>();
		intentosRestantes = 6;
		palabra = "";
		guiones = palabra.length();
		leerPalabras();
		seleccionarPalabra();
	}
	
	public void leerPalabras() {
		try {
			FileReader archivo = new FileReader("animales.txt");
			BufferedReader lectura = new BufferedReader(archivo);
			String palabra;
			while ((palabra = lectura.readLine()) != null) {
				palabras.agregarElemento(palabra);
				}
			lectura.close();
		} catch(Exception ex) {}
	}
		
	public String seleccionarPalabra() {
		palabra = palabras.dameElementoAleatorio();
		guiones = palabra.length();
		return palabra;
	}
	
	public void agregarLetra(String letra) {
		if (!letraRepetida(letra)) {
			letrasUsadas.agregarElemento(letra);
		}	
	}
	
	public Boolean letraAcertada(String letra) {
		String mayusLetra = (letra).toUpperCase();
		String minusLetra = (letra).toLowerCase();
		return (palabra.indexOf(mayusLetra) != -1 || palabra.indexOf(minusLetra) != -1) && !letraRepetida(letra);
	}
	
	public Boolean letraRepetida(String letra) {
		return letrasUsadas.pertenece(letra.toUpperCase()) || letrasUsadas.pertenece(letra.toLowerCase());
	}
	
	public void restarIntento() {
		intentosRestantes--;
	}
	
	public int contarCaracterEnCadena(String cadena, String caracter) {
		int contador = 0;
		String cadenaMayus = cadena.toUpperCase();
		String cadenaMinus = cadena.toLowerCase();
		for(int i = 0; i < cadena.length(); i++) {
			if((cadenaMayus.charAt(i) + "").equals(caracter) || (cadenaMinus.charAt(i) + "").equals(caracter))
				contador++;
		}
		return contador;
	}
	
	public void restarGuion(String letra) {
		guiones -= contarCaracterEnCadena(palabra, letra);
	}
	
	public Boolean palabraAdivinada() {
		return guiones == 0;
	}

	public Boolean juegoTerminado() {
		return this.intentosRestantes == 0 || palabraAdivinada();
	}

	public void verificarLetraIngresada(String letra) {
		if (letra.length() == 1 && letra.matches("^[a-zA-Z\\u00F1\\u00D1]+$")) //verifica que ingrese solo letras (incluyendo la ñ)
		if (letraAcertada(letra)) {
			restarGuion(letra);
			agregarLetra(letra);
		} else {
			if(!letraRepetida(letra)) {
				agregarLetra(letra);
				restarIntento();
			}
		}
	}

	public String mostrarPalabraCifrada() {
		String palabraConGuiones = "";
		String letraActual;
		for (int i = 0; i < this.palabra.length(); i++) {
			letraActual = palabra.charAt(i) + "";
			if (letraRepetida(letraActual))
				palabraConGuiones += letraActual;
			else
				palabraConGuiones += "_";
		}
		return palabraConGuiones;
	}

	public void reiniciarJuego() {
		letrasUsadas = new Conjunto<String>();
		seleccionarPalabra();
		this.intentosRestantes = 6;
	}
	
	public String mensajeActual(String letra) {
		String fin = mensajeFinDeJuego();
		String error = mensajeError(letra);
		return error != "" ? error : (fin != "" ? fin : "");
	}

	private String mensajeFinDeJuego() {
		String p = this.palabra;
		String mensaje = "";
		if(palabraAdivinada()) {
			mensaje = "Ganaste! La palabra era " + p;
		}else if(this.intentosRestantes == 0) {
			mensaje = "Perdiste :( La palabra era " + p;
		}
		return mensaje;
	}

	private String mensajeError(String letra) {
		String mensaje = "";
		if (letra.length() > 1) {
			mensaje = "Se debe ingresar un solo caracter";
		}
		return mensaje;
	}
	
	public String mensajeReiniciar() {
		String m = "Jugar de nuevo";
		return m;
	}

	public Conjunto<String> getPalabras() {
		return palabras;
	}

	public Conjunto<String> getLetrasUsadas() {
		return letrasUsadas;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getGuiones() {
		return guiones;
	}

	public void setGuiones(int guiones) {
		this.guiones = guiones;
	}

	public int getIntentos() {
		return intentosRestantes;
	}

	public void setIntentosRestantes(int intentosRestantes) {
		this.intentosRestantes = intentosRestantes;
	}
}