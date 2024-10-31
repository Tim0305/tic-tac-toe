package gato;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timoteo
 */
public class Gato {

    private int[][] tablero;
    private List<Posicion> posicionesGanador;
    private int ganador;
    public static final int PLAYER_X = 1;
    public static final int PLAYER_O = 0;
    public static final int EMPATE = -1;

    public Gato() {

        // inicializar el tablero
        tablero = new int[3][3];
        // Rellenar el tablero con -1
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = -1;
            }
        }

        posicionesGanador = new ArrayList<>();
        ganador = EMPATE;
    }

    public boolean setCirculo(Posicion posicion) {
        if (posicion != null) {
            if (tablero[posicion.y][posicion.x] == -1) {
                tablero[posicion.y][posicion.x] = PLAYER_O;
                return true;
            }
        }
        // Retornar si se pudo establecer el turno
        return false;
    }

    public boolean setCruz(Posicion posicion) {
        if (posicion != null) {
            if (tablero[posicion.y][posicion.x] == -1) {
                tablero[posicion.y][posicion.x] = PLAYER_X;
                return true;
            }
        }
        // Retornar si se pudo establecer el turno
        return false;
    }

    private boolean isGanadorHorizontal() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == tablero[i][1] && tablero[i][0] == tablero[i][2] && tablero[i][0] != -1) {
                posicionesGanador.add(new Posicion(0, i));
                posicionesGanador.add(new Posicion(1, i));
                posicionesGanador.add(new Posicion(2, i));
                ganador = tablero[i][0];

                return true;
            }
        }
        return false;
    }

    private boolean isGanadorVertical() {
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i] == tablero[1][i] && tablero[0][i] == tablero[2][i] && tablero[0][i] != -1) {
                posicionesGanador.add(new Posicion(i, 0));
                posicionesGanador.add(new Posicion(i, 1));
                posicionesGanador.add(new Posicion(i, 2));
                ganador = tablero[0][i];

                return true;
            }
        }
        return false;
    }

    private boolean isGanadorDiagonal() {
        if (tablero[0][0] == tablero[1][1] && tablero[0][0] == tablero[2][2] && tablero[0][0] != -1) {
            posicionesGanador.add(new Posicion(0, 0));
            posicionesGanador.add(new Posicion(1, 1));
            posicionesGanador.add(new Posicion(2, 2));

            ganador = tablero[0][0];
            return true;
        } else if (tablero[2][0] == tablero[1][1] && tablero[2][0] == tablero[0][2] && tablero[2][0] != -1) {
            posicionesGanador.add(new Posicion(0, 2));
            posicionesGanador.add(new Posicion(1, 1));
            posicionesGanador.add(new Posicion(2, 0));
            ganador = tablero[2][0];

            return true;
        } else {
            // no hay ganador
            ganador = 0;
            return false;
        }
    }

    public int getGanador() {
        if (isGanadorVertical()) {
            return ganador;
        } else if (isGanadorHorizontal()) {
            return ganador;
        } else if (isGanadorDiagonal()) {
            return ganador;
        }
        return -1;
    }

    public List<Posicion> getPosicionesGanador() {
        return posicionesGanador;
    }

    private String printTablero() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(tablero[i][j]).append("\t"); // Usa tabulador para separar columnas
            }
            sb.append("\n"); // Nueva línea para cada fila
        }
        return sb.toString();
    }
}
