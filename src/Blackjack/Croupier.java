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
 * Esta clase extiende la clase Jugador para realizar una simulación del mundo real en donde
 * el Croupier es un jugador pero a la vez, controla la mesa y distribuye las cartas a los jugadores
 */
public class Croupier extends Jugador{
	private BarajaAmericana baraja;
	private ArrayList<Jugador> jugadores;
	private boolean destapa;

	Croupier(double dinero, BarajaAmericana baraja, ArrayList<Jugador> jugadores) {
		super(dinero,"Croupier");
		this.dinero = dinero;
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
	private void resetManos(){
		this.resetMano();
		for(int j = 0 ; j < jugadores.size(); j++)
			jugadores.get(j).resetMano();
	}
	public void comienzaJuego() {
		Scanner scan = new Scanner(System.in);
		double apuesta, aux;
		baraja.revolver();
		while(jugadores.size()>0) {
			for(int i = 0 ; i < jugadores.size(); i++) {
				try {
					aux = jugadores.get(i).getDinero();
					System.out.println("Ingrese la cantidad a apostar del jugador "+jugadores.get(i).getNombre()+", total de bolsa: "+aux);
					apuesta = scan.nextDouble();
					if(apuesta <= 0 || apuesta > aux) {
						System.out.println("Saldo inválido");
						i--;
						continue;
					}
					jugadores.get(i).setApuesta(apuesta);
				}catch(Exception e) {
					scan.next();
					i--;
					System.out.println("Ingrese un valor válido");
				}
			}
			//Reparto inicial, 2 cartas a cada jugador
			repartir();
			System.out.println("\n ******* Ronda 1 *******");
			boolean otraRonda = true, band;
			this.toString();
			for(int i = 0 ; i < jugadores.size(); i++) {
				//Muestra las cartas del Croupier
				System.out.println("Mano del Croupier: " + this.toString());
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
			//Nadie ganó en la ronda inicial se procede a
			//preguntar a cada jugador que quiere hacer
			// Opciones del Jugador: 1-Pedir Carta, 2-Plantarse.
			int rondas = 2;
			int opcion=0;
			while (otraRonda) {
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
										//System.out.println("La casa gana, puntaje mayor a 21");
										//jugadores.get(i).quitarDinero(jugadores.get(i).getApuesta());
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
				//Se valida si hay jugadores con posibilidad de seguir Jugando
				otraRonda = false;
				for (int i = 0; i < jugadores.size(); i++) {
					if (jugadores.get(i).getEstadoJuego() == EstadoJuego.JUGANDO) {
						otraRonda = true;  //Tenemos un jugador activo aún, seguiremos jugando
						rondas++;
					}
				}
			}
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
			//Se imprimen los resultados de la ronda
			System.out.println("\nLos resultados finales para cada jugador son los siguientes:");
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
			//Validamos si cada jugador quiere seguir jugando o si se ha quedado sin saldo.
			ArrayList copia = new ArrayList();
			copia = (ArrayList)jugadores.clone();

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
