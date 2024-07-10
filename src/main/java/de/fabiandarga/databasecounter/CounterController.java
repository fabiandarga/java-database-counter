package de.fabiandarga.databasecounter;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import com.sun.net.httpserver.*;

public class CounterController {
    private static final CounterDAO counterDAO = new CounterDAO();
    private static final String HTML_FILE_PATH = "src/main/resources/templates/index.html";

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = getHtmlString();
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });
        server.createContext("/increment", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                counterDAO.incrementCounter();
                exchange.getResponseHeaders().set("Location", "/");
                exchange.sendResponseHeaders(302, -1);
            }
        });
        server.start();
        System.out.println("Server is running on port 8000");
    }

    private static String getHtmlString() throws IOException {
        Path path = Paths.get(HTML_FILE_PATH);
        return new String(Files.readAllBytes(path));
    }
}