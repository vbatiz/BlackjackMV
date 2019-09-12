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

/*
 * Este enumerado se utiliza para controlar los distintos valores de las cartas
 * de la baraja, así mismo lo usamos para desplegar un nombre personalizado
 * y controlar su valor.
 */
public enum Valor {
	//DOS,TRES,CUATRO,CINCO,SEIS,SIETE,OCHO,NUEVE,DIEZ,J,Q,R,AS,TODOS;

	DOS("2 ", 2),
	TRES("3 ", 3),
	CUATRO("4 ", 4),
	CINCO("5 ", 5),
	SEIS("6 ", 6),
	SIETE("7 ", 7),
	OCHO("8 ", 8),
	NUEVE("9 ", 9),
	DIEZ("10", 10),
	J("J ", 10),
	Q("Q ", 10),
	K("K ", 10),
	AS("A ", 11),
	TODOS("JOKER",0); //Joker caso especial

	private final String rango;
	private final int valor;

	Valor(String rango, int valor) {
		this.rango = rango;
		this.valor = valor;
	}

	public String obtenRango() {
		return rango;
	}

	public int obtenValor() {
		return valor;
	}


	@Override
	public String toString(){
		return obtenRango();
	}
}
