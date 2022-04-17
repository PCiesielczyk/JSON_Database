package server;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ServerMain {
    private static final int PORT = 34522;
    private static final String PATH = System.getProperty("user.dir") + "/JSON_Database/src/server/data/db.json";

    public static void main(String[] args) {

        Map<String, String> database = new HashMap<>();
        DatabaseManagement databaseManagement = new DatabaseManagement(database);

        ExecutorService executor = Executors.newScheduledThreadPool(4);
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();

        executor.submit(() -> {
            try (ServerSocket server = new ServerSocket(PORT)) {

                System.out.println("Server started!");

                while (true) {

                    try (
                            Socket socket = server.accept(); // accepting a new client
                            DataInputStream input = new DataInputStream(socket.getInputStream());
                            DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                    ) {

                        String msgFromClient = input.readUTF(); // reading a message

                        Gson gson = new Gson();
                        ArgumentsFromClient arg = gson.fromJson(msgFromClient, ArgumentsFromClient.class);

                        String msgToClient = "undefined";
                        String value = "undefined";
                        String key = "undefined";

                        String type = arg.getType();

                        if (!type.equals("exit")) {
                            key = arg.getKey();
                        }

                        if (type.equals("set")) {
                            value = arg.getValue();
                        }

                        switch (type) {
                            case "set":
                                msgToClient = gson.toJson(databaseManagement.setData(key, value));
                                break;
                            case "get":
                                msgToClient = gson.toJson(databaseManagement.getData(key));
                                break;
                            case "delete":
                                msgToClient = gson.toJson(databaseManagement.deleteData(key));
                                break;
                            case "exit":
                                msgToClient = "OK";
                                break;
                        }

                        writeLock.lock();
                        File file = new File(PATH);
                        try (Writer writer = new FileWriter(file)) {
                            gson.toJson(databaseManagement, writer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        writeLock.unlock();

                        output.writeUTF(msgToClient); // resend it to the client
                        if (type.equals("exit")) {
                            socket.close();
                            server.close();
                            System.exit(0);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
