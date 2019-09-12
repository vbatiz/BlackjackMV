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
 * Este enumerado nos permite controlar los distintos estados del juego y poder imprimir el mensaje
 * adecuado al finalizar el mismo.
 */
public enum EstadoJuego {
    JUGANDO(""),
    RETIRADO("Jugador se retira de la mesa"),
    PLANTADO("El jugador se planta con su mano"),
    PIDECARTA(""),
    VERIFICAR21(""),
    EMPATE("El juego quedó tablas"),
    PIERDEPORPASARSEDE21("¡La casa gana el jugador se pasó de 21 puntos!"),
    BLACKJACK("¡El jugador gana por Blackjak se le paga 3 a 2!"),
    GANAJUGADOR("¡El jugador gana!"),
    GANAJUGADOR5CARTAS("¡El jugador gana por tener 5 cartas!"),
    GANACROUPIER("¡La casa gana!"),
    BLACKJACKCROUPIER("¡La casa gana por Blackjack!");

    private String texto;

    EstadoJuego(String s) {
        texto = s;
    }

    @Override
    public String toString() {
        return texto;
    }
}
