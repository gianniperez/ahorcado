package Ahorcado;

import java.util.ArrayList;
import java.util.Random;

public class Conjunto <T> {
	private ArrayList<T> elementos;
	private int posicion;
		
	public Conjunto() {
		elementos = new ArrayList<>();
		posicion = 0;
	}
		
	public void agregarElemento (T elemento) {
		if (! pertenece(elemento)) {
			elementos.add(elemento);
		}
	}
		
	public void quitarElemento (T elemento) {
		if (pertenece(elemento)) {
			posicion--;
			elementos.remove(elemento);
		}
	}
		
	public int cantElementos () {
		return elementos.size();
	}
		
	public boolean estaVacio () {
		return cantElementos() == 0;
	}
		
	public boolean pertenece (T elemento) {
		return elementos.contains(elemento);
	}
		
	public T dameElemento () {
		if (estaVacio()) {
			throw new RuntimeException("El conjunto está vacío");
		}
			
			
			
		if (posicion >= cantElementos()) {
			posicion = 0;
		}
			
		T pos = elementos.get(posicion);
		posicion++;
		return pos;
	}
	
	public T dameElementoAleatorio() {
		if (estaVacio()) {
			throw new RuntimeException("El conjunto está vacío");
		}
		Random random = new Random();
		int posicionAleatoria = random.nextInt(this.cantElementos()-1);
		return elementos.get(posicionAleatoria);
	}
		
	public String toString() {
		return elementos.toString();
	}

}