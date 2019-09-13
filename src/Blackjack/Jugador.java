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
 * Clase Jugador para manejar los distintos jugadores que estarán participando en el juego.
 */
public class Jugador {
	protected ArrayList<Carta> mano;
	protected double dinero;
	protected double apuesta;
	protected EstadoJuego estadoJuego;
	protected String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Jugador(double dinero, String nombre) {
		this.dinero = dinero;
		mano = new ArrayList<Carta>();
		estadoJuego = EstadoJuego.JUGANDO;
		this.nombre = nombre;
	}

	public Jugador(double dinero, String nombre, ArrayList<Carta> mano) {
		this.dinero = dinero;
		this.mano = mano;
		estadoJuego = EstadoJuego.JUGANDO;
		this.nombre = nombre;
	}

	public void setEstadoJuego(EstadoJuego eJ) {
		estadoJuego = eJ;
	}

	public EstadoJuego getEstadoJuego() {
		return estadoJuego;
	}

	public boolean esBlackjack() {
		return	puntaje() == 21;
	}

	public boolean esFinJuego() {
		return puntaje() > 21;
	}

	public int puntaje() {
		int puntaje = 0, cont = 0;
		for(Carta c: mano) {
			switch(c.getValor()) {
				case DOS:
					puntaje +=2;
					break;
				case TRES:
					puntaje +=3;
					break;
				case CUATRO:
					puntaje +=4;
					break;
				case CINCO:
					puntaje +=5;
					break;
				case SEIS:
					puntaje +=6;
					break;
				case SIETE:
					puntaje +=7;
					break;
				case OCHO:
					puntaje +=8;
					break;
				case NUEVE:
					puntaje +=9;
					break;
				case DIEZ:
					puntaje +=10;
					break;
				case J:
					puntaje +=10;
					break;
				case Q:
					puntaje +=10;
					break;
				case K:
					puntaje +=10;
					break;
				case AS:
					cont ++;
					break;
			}
		}

		for(int i = 1 ; i <= cont; i++) {
			if(puntaje > 10)
				puntaje ++;
			else if (puntaje < 11 && i != cont)
				puntaje ++;
			else
				puntaje += 11;
		}
		return puntaje;
	}

	public String toString () {
		String cad = "";
		for(Carta c: mano)
			cad += c.toString() + " ";
		return cad;
	}

	public void aniadirDinero (double cantidad) {
		if(dinero < 0 ) {
			dinero = 0;
			return;
		}
		dinero += cantidad;
	}

	public void quitarDinero (double cantidad) {
		dinero -= cantidad;
	}

	public double getDinero(){
		return dinero;
	}

	public void aniadirCarta(Carta carta){
		mano.add(carta);
	}

	public Carta quitarCarta(int i) {
		return mano.get(i);
	}

	public void resetMano() {
		mano = new ArrayList<Carta>();
	}

	public void setApuesta(double apuesta) {
		this.apuesta = apuesta;
	}

	public double getApuesta() {
		return apuesta;
	}

	protected boolean tieneCinco() {
		return mano.size() == 5;
	}
}
