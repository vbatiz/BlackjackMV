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
 * Esta clase nos permite manejar cada elemento (carta) de la baraja y almacenar
 * su figura y valor
 */
public class Carta {
    private Figura figura;
    private Valor valor;

    public Carta(Figura figura, Valor valor) {
        this.figura = figura;
        this.valor = valor;
    }
    //Usamos toString() para regresar la carta en formato texto
    public String toString() {
        return  "|" + valor.toString() + figura.toString() +"|";
    }

    public Figura getFigura() {
        return figura;
    }

    public Valor getValor () {
        return valor;
    }
}
