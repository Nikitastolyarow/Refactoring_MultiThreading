package homework;

import java.io.IOException;
import java.net.ServerSocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    static final int COUNT = 64;

    private final static Map<String, Map<String, Handler>> handlers = new ConcurrentHashMap<>();

    static ExecutorService threadPool = Executors.newFixedThreadPool(COUNT);


    public static void startServ(int port) {

        try (final var serverSocket = new ServerSocket(port)) {
            while (true) {
               final var socket = serverSocket.accept();
                ClientsHandler clientsHandler= new ClientsHandler(socket);
                threadPool.submit(clientsHandler);
            }
        } catch (IOException e) {

        }

    }
    public void addHandler(String method, String path, Handler handler) {
        if (handlers.containsKey(method)) {
            handlers.get(method).put(path, handler);
        } else {
            handlers.put(method, new ConcurrentHashMap<>(Map.of(path, handler)));
        }
        System.out.println(handlers);
    }
    public static Map<String, Map<String, Handler>> getHandlers() {
        return handlers;
    }
}


