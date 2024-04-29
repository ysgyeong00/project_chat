package project;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ChatClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new PrintWriter(out);
            in = new BufferedReader(in);
            new Thread();
        } catch (IOException e) {
            System.out.println("실패");
        }
    }

    private void readMessage() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println("실패");
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("실패");
        }
    }

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient("host", 12345);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your nickname: ");
        String nickname = scanner.nextLine();
        chatClient.sendMessage(nickname);

        String line;
        while (!(line = scanner.nextLine()).equals("/bye")) {
            chatClient.sendMessage(line);
        }
        chatClient.sendMessage("bye");
        chatClient.close();
        
        scanner.close();
    }
}
