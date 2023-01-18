package Ahorcado;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AhorcadoTest {

	private Ahorcado juego;
	
	@Before
	public void setUp() {
		juego = new Ahorcado();
		juego.leerPalabras();
		juego.setPalabra("amatista");
		juego.setGuiones(8);
	}
	
	@Test
	public void seleccionarPalabraTest() {
		String s = juego.getPalabra();
		assertNotNull(s);
	}
	
	@Test
	public void agregarLetraTest() {
		juego.agregarLetra("a");
		assertTrue(juego.getLetrasUsadas().pertenece("a"));
	}
	
	@Test
	public void agregarLetraFalseTest() {
		assertFalse(juego.getLetrasUsadas().pertenece("a"));
	}
	
	@Test
	public void letraAcertadaTest() {
		assertTrue(juego.letraAcertada("a"));
	}
	
	@Test
	public void letraNoAcertadaTest() {
		assertFalse(juego.letraAcertada("r"));
	}
	
	@Test
	public void letraAcertadaPalabraVaciaTest() {
		juego.setPalabra("");
		assertFalse(juego.letraAcertada("a"));
	}
	
	@Test
	public void letraRepetidaTest() {
		juego.agregarLetra("a");
		assertTrue(juego.letraRepetida("a"));
	}
	
	@Test
	public void letraNoRepetidaTest() {
		juego.agregarLetra("a");
		assertFalse(juego.letraRepetida("b"));
	}
	
	@Test
	public void restarIntentoTest() {
		juego.restarIntento();
		assertEquals(5, juego.getIntentos());
	}
	
	@Test
	public void contarCaracterEnCadenaTest() {
		String a = juego.getPalabra();
		int b = juego.contarCaracterEnCadena(a, "a");
		assertEquals(3, b);
	}
	
	@Test
	public void contarCaracterEnCadenaIncorrectoTest() {
		String a = juego.getPalabra();
		int b = juego.contarCaracterEnCadena(a, "a");
		assertNotEquals(5, b);
	}
	
	@Test
	public void contarCaracterEnCadenaVaciaTest() {
		int b = juego.contarCaracterEnCadena("", "a");
		assertEquals(0, b);
	}
	
	@Test
	public void restarGuionTest() {
		juego.restarGuion("a");
		assertEquals(5, juego.getGuiones());
	}
	
	@Test
	public void restarGuionIncorrectoTest() {
		assertNotEquals(5, juego.getGuiones());
	}
	
	@Test
	public void palabraAdivinadaTest() {
		juego.setGuiones(0);
		assertTrue(juego.palabraAdivinada());
	}
	
	@Test
	public void palabraNoAdivinadaTest() {
		assertFalse(juego.palabraAdivinada());
	}
	
	@Test
	public void juegoGanadoTest() {
		juego.setGuiones(0);
		assertTrue(juego.juegoTerminado());
	}
	
	@Test
	public void juegoPerdidoTest() {
		juego.setIntentosRestantes(0);
		assertTrue(juego.juegoTerminado());
	}
	
	@Test
	public void juegoNoTerminadoTest() {
		assertFalse(juego.juegoTerminado());
	}
	
	@Test
	public void verificarLetraCorrectaTest() {
		int guiones = juego.getGuiones();
		juego.verificarLetraIngresada("a");
		assertNotEquals(guiones, juego.getGuiones());
	}
	
	@Test
	public void verificarLetraIncorrectaTest() {
		int intentos = juego.getIntentos();
		juego.verificarLetraIngresada("x");
		assertNotEquals(intentos, juego.getIntentos());
	}
	
	@Test
	public void verificarNumeroIngresadoTest() {
		int guiones = juego.getGuiones();
		int intentos = juego.getIntentos(); 
		juego.verificarLetraIngresada("1");
		assertEquals(intentos, juego.getIntentos());
		assertEquals(guiones, juego.getGuiones());
	}
	
	@Test
	public void verificarCaracterExtranoIngresadoTest() {
		int guiones = juego.getGuiones();
		int intentos = juego.getIntentos(); 
		juego.verificarLetraIngresada("$");
		assertEquals(intentos, juego.getIntentos());
		assertEquals(guiones, juego.getGuiones());
	}
	
	@Test
	public void verificarMasDeUnaLetraIngresadaTest() {
		int guiones = juego.getGuiones();
		int intentos = juego.getIntentos(); 
		juego.verificarLetraIngresada("fa");
		assertEquals(intentos, juego.getIntentos());
		assertEquals(guiones, juego.getGuiones());
	}
	
	@Test
	public void reiniciarJuegoTest() {
		String palabra = juego.getPalabra();
		juego.reiniciarJuego();
		assertNotEquals(palabra, juego.getPalabra());
	}
	
	@Test
	public void mensajeErrorTest() {
		String ingresado = "aa";
		assertEquals("Se debe ingresar un solo caracter", juego.mensajeActual(ingresado));
	}
	
	@Test
	public void mensajeGanasteTest() {
		juego.setGuiones(0);
		assertEquals("Ganaste! La palabra era " + juego.getPalabra(), juego.mensajeActual(""));
	}
	
	@Test
	public void mensajePerdisteTest() {
		juego.setIntentosRestantes(0);
		assertEquals("Perdiste :( La palabra era " + juego.getPalabra(), juego.mensajeActual(""));
	}
	
}