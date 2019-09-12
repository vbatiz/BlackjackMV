# Blackjack
Tarea 2<br>
Materia: Tecnologías de Programación de la MCC del ITC
<br>
### Tutor: Dra. María Lucía Barrón Estrada ##

#### Autores: Víctor Bátiz y Manuel Medrano

#### Lista de entregables:
1. El código fuente se encuentra en la carpeta: src\BlackjackMV (https://github.com/vbatiz/BlackjackMV/tree/master/src/Blackjack)
2. El Diagrama UML final: DiagramaUML.png 
![alt text](https://github.com/vbatiz/BlackjackMV/blob/master/DiagramaUML.png)

#### Descripción
El presente proyecto es una implementación en Java del juego Blackjack. Esta desarrollado utilizando la consola como interfaz y utilizando el IDE IntelliJ IDEA Ultimate 2019.2.
<br><br>
En esta implementación se siguieron las siguientes indicaciones:
##### Requisitos:
Este proyecto consiste en escribir un conjunto de clases en Java para implementar el juego de baraja Blackjack. Posteriormente se podrán implementar otros juegos por lo que es necesario que las clases e interfaces estén diseñadas para ser reutilizadas.
Requisitos:
1. Modelar la baraja (paquete de cartas con número y figura). En la creación de la baraja se puede incluir o no cartas especiales (ej. jocker). La baraja puede barajarse, partirse, etc.
2. Modelar a los jugadores que participan en el juego incluyendo al Croupier, es el jugador que tendrá la baraja, es el encargado de barajar el paquete de cartas, entregar a cada jugador las cartas, puntuar las jugadas, determinar al ganador, etc. Además, en el Blackjack el croupier también juega, así que él mismo también tiene una mano de cartas.
3. Modelar el juego de acuerdo con los elementos que participan siguiendo las reglas (entregar cartas abiertas o cerradas, aceptar apuestas, plantarse, etc.). El croupier administra las apuestas (iniciar con apuestas básicas).
4. Debes usar métodos para realizar los diferentes procesos del juego.
5. El programa debe estar documentado con comentarios.
6. Los datos de entrada se darán por teclado y deberás leerlos para saber si el jugador desea recibir o no más cartas.
7. El juego finaliza para cada jugador cuando:
    a) Si en la primera repartición el Jugador tiene Blackjack (21) gana y se le paga en razón de 3 a 2, a menos que el Croupier también tenga BlackJack, en ese caso es empate.
    b) Tiene 21 puntos exactos en sus cartas (gana si el Croupier no tiene 21, si ambos tienen 21 es empate).
    c) Se planta (no quiere más cartas cuando es su turno). Gana o pierde dependiendo de lo que tenga el Croupier.
    d) Tiene más de 21 puntos en sus cartas (pierde).
    e) El jugador reúne 5 cartas y no se ha pasado de 21
8. Imprime en la pantalla los datos que permitan seguir el proceso del juego
<br>
