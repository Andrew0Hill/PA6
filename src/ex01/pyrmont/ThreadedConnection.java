package ex01.pyrmont;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Andrew_2 on 11/5/2015.
 */
public class ThreadedConnection implements Runnable {
    InputStream input;
    OutputStream output;
    Socket socket;
    boolean shutdown = false;
    ThreadedConnection(Socket s) {
        socket = s;
        try {
             input = socket.getInputStream();
             output = socket.getOutputStream();
        }catch (Exception e) {e.printStackTrace();}
    }
    public boolean shutdownStatus() {
        return shutdown;
    }
    @Override
    public void run() {
        try {
            // create Request object and parse
            Request request = new Request(input);
            request.parse();

            // create Response object
            Response response = new Response(output);
            response.setRequest(request);
            response.sendStaticResource();

            // Close the socket
            socket.close();

            shutdown = request.getUri().equals("/SHUTDOWN");
        }catch (Exception e) {e.printStackTrace();}
    }
}
