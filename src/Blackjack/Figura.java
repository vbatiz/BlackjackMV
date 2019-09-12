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
 * Este enumerado permite manejar las figuras disponibles en la baraja
 * así mismo usamos el enumerado para desplegar una figura representativa
 */
public enum Figura {
	CORAZON("♥"),
    TREBOL("♣"),
    DIAMANTE("♦"),
    PICA("♠"),
    JOKER("");
    private final String signo;
    private Figura(String signo) {
        this.signo = signo;
    }

    public String obtenSigno() {
        return signo;
    }

    @Override
    public String toString(){
        return obtenSigno();
    }

}
