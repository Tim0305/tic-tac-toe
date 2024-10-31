package main;

import cliente.Cliente;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timoteo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(1234);

            Socket s;

            while (true) {
                s = ss.accept();
                System.out.println("Nuevo Juego");
                Cliente cliente = new Cliente(s);
                cliente.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
