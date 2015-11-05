//
// based on: http://cs.lmu.edu/~ray/notes/javanetexamples/#date
//

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
/**
 * 3320 students: DO NOT CHANGE THIS SERVER
 *                you may only adjust the port number
 */                 

/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client a random integer, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 */
public class SecretServer {

    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                    // secrets are a lot of work to find
                    try {
                    Thread.sleep(15000);    
                    } catch (Exception e) {}
                    String secret = "" + new Random().nextInt();
                    out.println(secret);
                    System.out.println("debug, secret sent: " + secret);
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
    }
}