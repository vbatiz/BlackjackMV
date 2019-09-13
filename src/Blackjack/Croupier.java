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
import java.lang.System;

/*
 * Esta clase extiende la clase Jugador para realizar una simulación del mundo real en donde
 * el Croupier es un jugador pero a la vez, controla la mesa y distribuye las cartas a los jugadores
 */
public class Croupier extends Jugador{

	//Declaración de variables globales
	private BarajaAmericana baraja;
	private ArrayList<Jugador> jugadores;
	private boolean destapa;

	Croupier(BarajaAmericana baraja, ArrayList<Jugador> jugadores) {
		super(0,"Croupier");
		this.baraja = baraja;
		this.jugadores = jugadores;
		this.destapa = false; //No mostrar una carta del Croupier
		this.setEstadoJuego(EstadoJuego.JUGANDO); //Inicializamos el estado del juego en general
	}
	//Reparte las primeras dos cartas a cada jugador incluyendo a Croupier
	public void repartir(){
		for(int i = 0; i < 2; i++) {
			this.aniadirCarta(baraja.sacarCarta());
			for(int j = 0 ; j < jugadores.size(); j++)
				jugadores.get(j).aniadirCarta(baraja.sacarCarta());
		}
	}
	//Llama al metodo reset mano de cada jugador para reiniciar las manos.
	private void resetManos(){
		this.resetMano();
		for(int j = 0 ; j < jugadores.size(); j++)
			jugadores.get(j).resetMano();
	}
	Scanner scan = new Scanner(System.in);
	//Pide las apuestas de cada uno de los jugadores.
	private void pedirApuestas() {
		double apuesta, aux;
		//Por cada jugador se pide una apuesta dentro del rango 0 hasta el dinero que tenga.
		for(int i = 0 ; i < jugadores.size(); i++) {
			try {
				aux = jugadores.get(i).getDinero();
				System.out.println("Ingrese la cantidad a apostar del jugador "+jugadores.get(i).getNombre()+", total de bolsa: "+aux);
				apuesta = scan.nextDouble();
				//Si La apuesta ingresada no está en el rango vuelve a preguntar.
				if(apuesta <= 0 || apuesta > aux) {
					System.out.println("Saldo inválido");
					i--;
					continue;
				}
				/* si todo sale bien, le asigna la apuesta al jugador actual. */
				jugadores.get(i).setApuesta(apuesta);
			}catch(Exception e) {
				//En caso de que se ingrese un carácter no double se captura la excepción
				scan.next();
				i--;
				System.out.println("Ingrese un valor válido");
			}
		}
	}
	//Ejecuta la verificación de los estados iniciales de los jugadores.
	public void primerTurno() {
		//Muestra las cartas del Croupier
		System.out.println("Mano del Croupier: " + this.toString());
		//Muestra el puntaje del Croupier
		System.out.println("Puntaje del " + this.getNombre() + ": " + this.puntaje());
		for(int i = 0 ; i < jugadores.size(); i++) {
			//Muestra las cartas del jugador i
			System.out.println("Mano del Jugador " + jugadores.get(i).getNombre() + ": " + jugadores.get(i).toString());
			//Muestra el puntaje del jugador
			System.out.println("Puntaje del Jugador " + jugadores.get(i).getNombre() + ": " + jugadores.get(i).puntaje());
			//Verificar Blackjack en apertura - Ambos tienen
			if (this.esBlackjack() && jugadores.get(i).esBlackjack()) {
				jugadores.get(i).setEstadoJuego(EstadoJuego.EMPATE);
				continue;
			}
			//Valida si el jugador tiene Blackjack
			if ((jugadores.get(i).esBlackjack()) && (jugadores.get(i).getEstadoJuego() == EstadoJuego.JUGANDO)) {
				jugadores.get(i).setEstadoJuego(EstadoJuego.BLACKJACK);
				continue;
			}
			//Validar si el Croupier tiene Blackjack
			if ((this.esBlackjack()) && (jugadores.get(i).getEstadoJuego() == EstadoJuego.JUGANDO)) {
				jugadores.get(i).setEstadoJuego(EstadoJuego.BLACKJACKCROUPIER);
				continue;
			}
		}
	}
	//Ejecuta una ronda de juego.
	public void ejecutaRonda(int rondas) {
		int opcion=0;
		boolean band;
		System.out.println("\n ******* Ronda " + rondas +" *******");
		for (int i = 0; i < jugadores.size(); i++) {
			band=true;
			do {
				if (jugadores.get(i).getEstadoJuego() == EstadoJuego.JUGANDO) {
					System.out.println("\n Jugador " + jugadores.get(i).getNombre() + ":");
					//Muestra las cartas del jugador i
					System.out.println("Mano del Jugador " + jugadores.get(i).getNombre() + ": " + jugadores.get(i).toString());
					//Muestra el puntaje del jugador
					System.out.println("Puntaje del Jugador " + jugadores.get(i).getNombre() + ": " + jugadores.get(i).puntaje());
					//Solicitar movimiento a Jugador
					while (true) {
						try {
							System.out.println("¿Quiere (1)Pedir carta o (2)Plantarse?");
							opcion = scan.nextInt();
							if (opcion < 1 || opcion > 2) {
								System.out.println("Elija una opción válida");
								continue;
							}
							break;
						} catch (Exception e) {
							scan.next();
							System.out.println("Ingrese un número válido.");
						}
					}
					switch (opcion) {
						case 1: //Jugador pide Carta
							jugadores.get(i).aniadirCarta(baraja.sacarCarta());
							//Muestra las cartas del jugador i
							System.out.println("Mano del Jugador " + jugadores.get(i).getNombre() + ": " + jugadores.get(i).toString());
							//Muestra el puntaje del jugador
							System.out.println("Puntaje del Jugador " + jugadores.get(i).getNombre() + ": " + jugadores.get(i).puntaje());
							//Pierde si se pasa de 21
							if (jugadores.get(i).esFinJuego()) {
								jugadores.get(i).setEstadoJuego(EstadoJuego.PIERDEPORPASARSEDE21); //Fin ronda para este jugador
								System.out.println(jugadores.get(i).getEstadoJuego().toString());
								band = false;
							} else if (jugadores.get(i).esBlackjack()) {
								jugadores.get(i).setEstadoJuego(EstadoJuego.VERIFICAR21); //Suma 21
								band = false;
							} else if (jugadores.get(i).tieneCinco()) {
								jugadores.get(i).setEstadoJuego(EstadoJuego.GANAJUGADOR5CARTAS); //Gana por tener 5 cartas
								band = false;
							}
							break;
						case 2: //El jugador se ha plantado con su mano
							jugadores.get(i).setEstadoJuego(EstadoJuego.PLANTADO); //Fin ronda para este jugador
							System.out.println(jugadores.get(i).getEstadoJuego().toString());
							band = false;
							break;
					}
				} else
					break;
			}while(band);
		}
	}
	//Ejecuta la simulación del juego del croupier
	public void juegaCroupier() {
		//Muestra las cartas del Croupier
		this.destapa =true;
		System.out.println("Mano del Croupier: " + this.toString());
		System.out.println("Puntaje del Croupier: " + this.puntaje());
		//Si el Croupier tienen menos de 17 puntos en sus cartas se le obliga a pedir cartas
		while (this.puntaje() < 17) {
			this.aniadirCarta (baraja.sacarCarta());
			System.out.println("Mano del Croupier: " + this.toString());
			System.out.println("Puntaje del Croupier: " + this.puntaje());
		}
	}
	//Imprime los resultados del juego ejecutado.
	public void resultadosJuego() {
		for (int i = 0; i < jugadores.size(); i++) {
			System.out.println("\nResultado para el jugador "+jugadores.get(i).getNombre() + ":");
			//Muestra las cartas del jugador i
			System.out.println("Mano del Jugador " + jugadores.get(i).getNombre() + ": " + jugadores.get(i).toString());
			//Muestra el puntaje del jugador
			System.out.println("Puntaje del Jugador " + jugadores.get(i).getNombre() + ": " + jugadores.get(i).puntaje());
			switch(jugadores.get(i).getEstadoJuego()) {
				case PIERDEPORPASARSEDE21: //Jugador se pasó de 21
					System.out.println(jugadores.get(i).getEstadoJuego().toString());
					jugadores.get(i).quitarDinero(jugadores.get(i).getApuesta());
					break;
				case EMPATE:
					System.out.println(jugadores.get(i).getEstadoJuego().toString());
					break;
				case BLACKJACKCROUPIER: //Gana Croupier por tener 21
					System.out.println(jugadores.get(i).getEstadoJuego().toString());
					jugadores.get(i).quitarDinero(jugadores.get(i).getApuesta());
					break;
				case BLACKJACK: //Gana Jugador por tener 21 se le paga 3x2
					System.out.println(jugadores.get(i).getEstadoJuego().toString());
					jugadores.get(i).aniadirDinero(jugadores.get(i).getApuesta()*1.5);
					break;
				case PLANTADO: //Decidir resultado del juego con base a mano del Croupier
					if ((this.esFinJuego()) || (jugadores.get(i).puntaje() > this.puntaje())) {
						jugadores.get(i).setEstadoJuego(EstadoJuego.GANAJUGADOR);
						jugadores.get(i).aniadirDinero (jugadores.get(i).getApuesta());
						System.out.println(jugadores.get(i).getEstadoJuego().toString());
						break;
					}
					if (jugadores.get(i).puntaje() == this.puntaje()) {
						jugadores.get(i).setEstadoJuego(EstadoJuego.EMPATE);
						System.out.println(jugadores.get(i).getEstadoJuego().toString());
						break;
					}
					if (jugadores.get(i).puntaje() < this.puntaje()) {
						jugadores.get(i).setEstadoJuego(EstadoJuego.GANACROUPIER);
						System.out.println(jugadores.get(i).getEstadoJuego().toString());
						jugadores.get(i).quitarDinero(jugadores.get(i).getApuesta());
						break;
					}
				case VERIFICAR21: //Jugador tiene 21, verificar croupier para decidir resultado
					if (this.esBlackjack()) {
						jugadores.get(i).setEstadoJuego(EstadoJuego.EMPATE);
						System.out.println(jugadores.get(i).getEstadoJuego().toString());
					} else {
						jugadores.get(i).setEstadoJuego(EstadoJuego.GANAJUGADOR);
						jugadores.get(i).aniadirDinero(jugadores.get(i).getApuesta());
						System.out.println(jugadores.get(i).getEstadoJuego().toString());
						break;
					}
					// code block
					break;
				case GANAJUGADOR5CARTAS: //Gana jugador por raro caso de acumular 5 cartas
					jugadores.get(i).aniadirDinero(jugadores.get(i).getApuesta());
					System.out.println(jugadores.get(i).getEstadoJuego().toString());
					break;
			}
		}
	}
	//Método de ejecución de simulación de BlackJack.
	public void comienzaJuego() {
		//Se llama al método para barajar el mazo.
		baraja.revolver();
		//El juego se ejecuta mientras haya jugadores jugando.
		while(jugadores.size()>0) {
			//se piden las apuestas de cada jugador.
			pedirApuestas();
			//Reparto inicial, 2 cartas a cada jugador
			repartir();
			System.out.println("\n******* Ronda 1 *******");
			boolean otraRonda = true, band;
			this.toString();
			//Ejecuta el turno inicial.
			primerTurno();
			//Si nadie ganó en la ronda inicial se procede a
			//preguntar a cada jugador que quiere hacer
			// Opciones del Jugador: 1-Pedir Carta, 2-Plantarse.
			int rondas = 2;
			while (otraRonda) {
				ejecutaRonda(rondas);
				//Se valida si hay jugadores con posibilidad de seguir Jugando
				otraRonda = false;
				for (int i = 0; i < jugadores.size(); i++) {
					if (jugadores.get(i).getEstadoJuego() == EstadoJuego.JUGANDO) {
						otraRonda = true;  //Tenemos un jugador activo aún, seguiremos jugando
					}
				}
				rondas++;
			}
			System.out.println();
			//Ejecuta el juego del croupier.
			juegaCroupier();
			//Se imprimen los resultados de la ronda
			System.out.println("\nLos resultados finales para cada jugador son los siguientes:");
			resultadosJuego();
			//Validamos si cada jugador quiere seguir jugando o si se ha quedado sin saldo.
			for (int i = 0; i < jugadores.size(); i++) {
				if (jugadores.get(i).getDinero() <=0) {
					System.out.println("¡El jugador " + jugadores.get(i).getNombre() + " se ha quedado sin dinero, no puede continuar!");
					jugadores.get(i).setEstadoJuego(EstadoJuego.RETIRADO);
					jugadores.remove(i);
					i--;
					continue;
				}
				String keep = obtenSiNo("Jugador "+ jugadores.get(i).getNombre() + " ¿Jugar de nuevo ? Si (S) o No (N)");
				if ("N".equalsIgnoreCase(keep)) {
					System.out.println("¡Hasta pronto, gracias por jugar!");
					jugadores.get(i).setEstadoJuego(EstadoJuego.RETIRADO);
					jugadores.remove(i);
					i--;
				} else {
					jugadores.get(i).setEstadoJuego(EstadoJuego.JUGANDO);
				}
			}
			this.destapa = false; //Como iniciará una nueva partida, Croupier no muestra una carta
			resetManos();
			baraja.reset();
			baraja.revolver();
		}
		scan.close();
	}

	/*
	 * El siguiente método nos permite validar la respuesta a preguntas Si o NO
	 * @param
	 * texto - Texto a desplegar como petición al usuario
	 */
	private String obtenSiNo(String texto) {
		Scanner scanner = new Scanner(System.in);
		String letra;
		boolean letraErronea =false;
		do {
			System.out.println(texto);
			while (!scanner.hasNextLine()) {
				String input = scanner.next();
				System.out.printf("\"%s\" No es una entrada válida.\n", input);
			}
			letra = scanner.nextLine();
			if ((letra.equalsIgnoreCase("S")) || (letra.equalsIgnoreCase("N"))) {
				letraErronea = false;
			}
			else {
				letraErronea = true;
				System.out.printf("\"%s\" es una opción incorrecta.\n", letra);
			}
		} while (letraErronea); //(number < limInferior) || (number >limSuperior)
		return letra;
	}

	/*
	 * Sobreescribimos toString() para armar la mano de cartas del croupier
	 * Validamos si debe mostrarse toda la mano o se debe ocultar una carta
	 */
	@Override
	public String toString() {
		String cad;
		if(destapa)
			cad = mano.get(0).toString()+" ";
		else
			cad = "|XXX| ";
		for(int i = 1 ; i < mano.size(); i++)
			cad += mano.get(i).toString() + " ";
		return cad;
	}
}
