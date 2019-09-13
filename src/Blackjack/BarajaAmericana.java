package Blackjack;
/* * *
 * Implementación en Consola del juego de BlackJack
 * Materia: Tecnologías de Programación
 * Tutor: María Lucía Barrón Estrada.
 *
 * Autores:
 * Manuel Alejandro Medrano Díaz.
 * Víctor Manuel Bátiz Beltrán.
 * Última Revisión: 10/09/2019
 *
 * * */
import java.util.*;

/*
 * Pensando en implementaciones futuras tenemos esta clase nombrada como BarajaAmericana, ya que
 * en futuros desarrollos pudieramos crear una clase con mayor nivel de abstracción y llamarla Baraja
 * haciendo que clases como la descrita aquí extiendan a la clase Baraja
 */

public class BarajaAmericana {
	private ArrayList<Carta> baraja;
	private boolean joker;

	public BarajaAmericana() {
		baraja = new ArrayList<Carta>();
		crearBaraja();
	}

	public BarajaAmericana(boolean joker) {
		baraja = new ArrayList<Carta>();
		joker = true;
		crearBarajaCompleta();
	}

	public void reset() {
		baraja = new ArrayList<Carta>();
		if(joker)
			crearBarajaCompleta();
		else
			crearBaraja();
	}

	public Carta sacarCarta() {
		return baraja.remove(0);
	}

	public void revolver() {
		Collections.shuffle(baraja);
	}

	private void crearBaraja() {
		Figura [] fig = Figura.values();
		Valor [] val = Valor.values();
		for(int i = 0 ; i < fig.length-1; i++) {
			for(int j = 0 ; j < val.length-1; j++) {
				baraja.add(new Carta(fig[i],val[j]));
			}
		}
	}

	private void crearBarajaCompleta() {
		Figura [] fig = Figura.values();
		Valor [] val = Valor.values();
		for(int i = 0 ; i < fig.length-1; i++) {
			for(int j = 0 ; j < val.length-1; j++) {
				baraja.add(new Carta(fig[i],val[j]));
			}
		}
		baraja.add(new Carta(Figura.JOKER,Valor.TODOS));
		baraja.add(new Carta(Figura.JOKER,Valor.TODOS));
	}

	public String toString() {
		String cad = "";
		for(Carta car: baraja)
			cad += car.toString()+"\n";
		return cad;
	}
}
