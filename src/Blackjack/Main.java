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
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cantJugadores = 0;
		double dinero=0;
		String nombre;
		//Iniciamos la solicitud de parámetros de inicio (Cantidad de jugadores y saldo inicial del jugador)
		while (true) {
			try {
				System.out.println("Cantidad de jugadores. (1-3)");
				cantJugadores = scan.nextInt();
				if (cantJugadores < 1 || cantJugadores > 3) {
					System.out.println("Ingrese un número válido.");
					continue;
				}
				break;
			} catch (Exception e) {
				scan.next();
				System.out.println("Ingrese un número válido.");
			}
		}
		//scan.nextLine();
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		for (int i = 0; i < cantJugadores; i++) {
			System.out.print("Nombre del jugador " + (i + 1) + ": ");
			nombre = scan.next();
			boolean numErroneo=true;
			do {
				try {
					System.out.print("Cantidad de dinero jugador "+nombre+": ");
					dinero = scan.nextDouble();
					if(dinero < 0) {
						System.out.println("Ingrese una cantidad válida");
						continue;
					}
					numErroneo = false;
				}catch(Exception e) {
				    scan.next();
					System.out.println("Ingrese una cantidad válida");
				}
			} while (numErroneo);
			jugadores.add(new Jugador(dinero, nombre));
		}
		System.out.println("\nEmpieza el juego");
		BarajaAmericana baraja = new BarajaAmericana();
		Croupier croupier = new Croupier(10000, baraja, jugadores);
		croupier.comienzaJuego();
		scan.close();
	}
}
