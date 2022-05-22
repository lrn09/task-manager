package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class TodoServer {
    private int port;
    private final Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    String json = in.readLine();
                    Gson gson = new Gson();
                    Map<String, String> map = gson.fromJson(json, Map.class); //это костыль?

                    String action = "", task = "";
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (key.equals("type")) {
                            action = value;
                        } else {
                            task = value;
                        }
                    }
                    if (action.equals("ADD")) {
                        todos.addTask(task);
                    } else if (action.equals("REMOVE")) {
                        todos.removeTask(task);
                    }

                    out.println(todos.getAllTasks());

                }
            }
        }
    }
}
