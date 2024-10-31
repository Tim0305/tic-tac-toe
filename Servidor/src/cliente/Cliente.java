package cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timoteo
 */
public class Cliente extends Thread {

    private Socket s;

    public Cliente(Socket s) {
        super();
        this.s = s;
    }

    @Override
    public void run() {
        // Logica del gato en el servidor.

        Random random = new Random();

        while (true) {
            try {
                // Recibir mensaje para hacer la jugada en el servidor
                if (s.getInputStream().available() > 0) {
                    int codigo = s.getInputStream().read();

                    if (codigo == 1) // Hacer jugada
                    {
                        int pos = random.nextInt(9) + 1; // 1 -> 9
                        s.getOutputStream().write(pos);
                    }
                    // Terminar el proceso
                    if (codigo == 0) {
                        s.close();
                        return;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
