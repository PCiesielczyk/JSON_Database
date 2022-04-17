package client;

import com.beust.jcommander.JCommander;

import java.io.*;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;
import com.google.gson.Gson;

public class ClientMain {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;

    public static void main(String[] args) throws FileNotFoundException {

        InputArguments arguments = new InputArguments();
        new JCommander(arguments).parse(args);

        Gson gson = new Gson();

        Map<String, String> mapOfArguments = new LinkedHashMap<>();

        if (arguments.getPath() != null) {

            String path = System.getProperty("user.dir") + "/JSON_Database/src/client/data/" + arguments.getPath();
            File file = new File(path);
            BufferedReader brTest = new BufferedReader(new FileReader(file));
            ArgumentsFromFile argumentsFromFile = gson.fromJson(brTest, ArgumentsFromFile.class);

            mapOfArguments.put("type", argumentsFromFile.getType());
            mapOfArguments.put("key", argumentsFromFile.getKey());
            mapOfArguments.put("value", argumentsFromFile.getValue());
            mapOfArguments.put("path", argumentsFromFile.getPath());
        } else {
            mapOfArguments.put("type", arguments.getType());
            mapOfArguments.put("key", arguments.getIndex());
            mapOfArguments.put("value", arguments.getMessage());
            mapOfArguments.put("path", arguments.getPath());
        }

        String jsonOutput = gson.toJson(mapOfArguments);

        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output  = new DataOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Client started!");

            System.out.println("Sent: " + jsonOutput);

            output.writeUTF(jsonOutput); // sending message to the server
            String receivedMsg = input.readUTF(); // response message

            System.out.println("Received: " + receivedMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
