package homework;

import com.sun.net.httpserver.Request;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class Main {
    final static int PORT = 9999;

    public static void main(String[] args) {

        final var server = new Server();
        for (String validPath : ClientsHandler.validPaths) {

            server.addHandler("GET", validPath, (request,responseStream) -> {
                try {
                    ClientsHandler.sendTemplate(request, responseStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        server.addHandler("POST", "/messages", (request, responseStream) -> {
            try {
                ClientsHandler.sendFile(request, responseStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Server.startServ(PORT);
    }
}
