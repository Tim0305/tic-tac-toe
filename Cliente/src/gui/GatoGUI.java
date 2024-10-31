/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import gato.Gato;
import gato.Posicion;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import servidor.Servidor;

/**
 *
 * @author Timoteo
 */
public class GatoGUI extends JFrame implements ActionListener {

    private JButton[][] cmdBotonesPosicion;
    private JFrame ventanaPrincipal;
    private Gato gato;
    private int width;
    private int height;
    private boolean isTurnoUsuario = true;
    private int turnos;
    private Servidor servidor;
    private final String SERVER_IP = "127.0.0.1";
    private final int SERVER_PORT = 1234;

    public GatoGUI() {
        super();

        ventanaPrincipal = null;
        servidor = new Servidor(SERVER_IP, SERVER_PORT);

        width = 500;
        height = 530;

        turnos = 0;
        gato = new Gato();
        cmdBotonesPosicion = new JButton[3][3];
        config();

    }

    public GatoGUI(JFrame ventanaPrincipal) {
        this();
        this.ventanaPrincipal = ventanaPrincipal;
    }

    private void config() {
        setTitle("Gato");
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);

        initCmdBotonesPosicion();

        // Configuración del GroupLayout
        GroupLayout gl = new GroupLayout(getContentPane());
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        // Configurar el layout horizontal (GH)
        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addGroup(
                                gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(cmdBotonesPosicion[0][0], 50, 100, 150)
                                        .addComponent(cmdBotonesPosicion[1][0], 50, 100, 150)
                                        .addComponent(cmdBotonesPosicion[2][0], 50, 100, 150)
                        )
                        .addGroup(
                                gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(cmdBotonesPosicion[0][1], 50, 100, 150)
                                        .addComponent(cmdBotonesPosicion[1][1], 50, 100, 150)
                                        .addComponent(cmdBotonesPosicion[2][1], 50, 100, 150)
                        )
                        .addGroup(
                                gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(cmdBotonesPosicion[0][2], 50, 100, 150)
                                        .addComponent(cmdBotonesPosicion[1][2], 50, 100, 150)
                                        .addComponent(cmdBotonesPosicion[2][2], 50, 100, 150)
                        )
        );

        // Configurar el layout vertical (GV)
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addGroup(
                                gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(cmdBotonesPosicion[0][0], 50, 100, 150)
                                        .addComponent(cmdBotonesPosicion[0][1], 50, 100, 150)
                                        .addComponent(cmdBotonesPosicion[0][2], 50, 100, 150)
                        )
                        .addGroup(
                                gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(cmdBotonesPosicion[1][0], 50, 100, 150)
                                        .addComponent(cmdBotonesPosicion[1][1], 50, 100, 150)
                                        .addComponent(cmdBotonesPosicion[1][2], 50, 100, 150)
                        )
                        .addGroup(
                                gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(cmdBotonesPosicion[2][0], 50, 100, 150)
                                        .addComponent(cmdBotonesPosicion[2][1], 50, 100, 150)
                                        .addComponent(cmdBotonesPosicion[2][2], 50, 100, 150)
                        )
        );
        setLayout(gl);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (ventanaPrincipal != null) {
                    // Terminar el hilo del servidor
                    servidor.sendInt(0);
                    setVisible(false);
                    ventanaPrincipal.setVisible(true);
                } else {
                    System.exit(0);
                }
            }
        });

    }

    private void initCmdBotonesPosicion() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cmdBotonesPosicion[i][j] = new JButton("");
                cmdBotonesPosicion[i][j].addActionListener(this);
                cmdBotonesPosicion[i][j].setFocusPainted(false);
                cmdBotonesPosicion[i][j].setBorder(crearBordePersonalizado(i, j));
                cmdBotonesPosicion[i][j].setBackground(Color.WHITE);
                cmdBotonesPosicion[i][j].setFont(new Font("Arial", Font.PLAIN, 60));

                add(cmdBotonesPosicion[i][j]);
            }
        }
    }

    // Método para crear un borde personalizado dependiendo de la posición del botón
    private Border crearBordePersonalizado(int fila, int columna) {
        int borderPixels = 2;
        int top = (fila == 0) ? 0 : borderPixels;       // Borde superior (no necesario para la primera fila)
        int left = (columna == 0) ? 0 : borderPixels;   // Borde izquierdo (no necesario para la primera columna)
        int bottom = (fila == 2) ? 0 : borderPixels;    // No borde inferior para la última fila
        int right = (columna == 2) ? 0 : borderPixels;  // No borde derecho para la última columna

        // Crear el borde con los márgenes especificados
        return BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK);
    }

    private Posicion getPosicion(int numCelda) {
        if (numCelda == 1) {
            return new Posicion(0, 0);
        }
        if (numCelda == 2) {
            return new Posicion(1, 0);
        }
        if (numCelda == 3) {
            return new Posicion(2, 0);
        }
        if (numCelda == 4) {
            return new Posicion(0, 1);
        }
        if (numCelda == 5) {
            return new Posicion(1, 1);
        }
        if (numCelda == 6) {
            return new Posicion(2, 1);
        }
        if (numCelda == 7) {
            return new Posicion(0, 2);
        }
        if (numCelda == 8) {
            return new Posicion(1, 2);
        }
        if (numCelda == 9) {
            return new Posicion(2, 2);
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cmdBotonesPosicion[i][j] == e.getSource()) {
                    {
                        if (isTurnoUsuario && turnos < 9) // Si se pudo hacer la jugada?
                        {
                            if (gato.setCruz(new Posicion(j, i))) {
                                cmdBotonesPosicion[i][j].setText("X");
                                turnos++;
                                isTurnoUsuario = false;

                                int ganador = gato.getGanador();
                                switch (ganador) {
                                    case -1:
                                        if (turnos < 9) {
                                            realizarMovimientoComputadora();
                                        } else // Empate
                                        {
                                            servidor.sendInt(0);
                                            mostrarGanador("!Empate!");
                                        }
                                        break;
                                    case 1: // Jugador
                                        servidor.sendInt(0);
                                        mostrarPosicionesGanador();
                                        mostrarGanador("!El jugador ha ganado!");
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void realizarMovimientoComputadora() {
        if (!isTurnoUsuario && turnos < 9) {
            Posicion posicion;
            do {
                servidor.sendInt(1);
                posicion = getPosicion(servidor.getInt());
            } while (!gato.setCirculo(posicion));
            cmdBotonesPosicion[posicion.y][posicion.x].setText("O");
            isTurnoUsuario = true;
            turnos++;

            int ganador = gato.getGanador();
            switch (ganador) {
                case -1:
                    if (turnos < 9) {
                        realizarMovimientoComputadora();
                    } else // Empate
                    {
                        servidor.sendInt(0);
                        mostrarGanador("!Empate!");
                    }
                    break;
                case 0: // Jugador
                    servidor.sendInt(0);
                    mostrarPosicionesGanador();
                    mostrarGanador("!La computadora ha ganado!");
                    break;
            }
        }
    }

    private void mostrarPosicionesGanador() {
        for (Posicion pos : gato.getPosicionesGanador()) {
            cmdBotonesPosicion[pos.y][pos.x].setBackground(Color.GREEN);
        }
    }

    private void mostrarGanador(String mensaje) {
        // Muestra el mensaje en el JOptionPane
        JOptionPane.showMessageDialog(null, mensaje);
        if (ventanaPrincipal != null) {
            setVisible(false);
            ventanaPrincipal.setVisible(true);
        } else {
            System.exit(0);
        }
    }
}
