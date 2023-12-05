package ru.itis.kpfu.skvortsova.chatbot.bot.client;

import ru.itis.kpfu.skvortsova.chatbot.bot.BotApplication;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClient {

    private BotApplication application;
    private Socket socket;
    private ClientThread thread;

    public ChatClient(BotApplication application) {
        this.application = application;
    }

    public void sendMessage(String message) {
        try {
            thread.getOutput().write(message);
            thread.getOutput().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        String host = application.getUserConfig().getHost();
        int port = application.getUserConfig().getPort();

        BufferedReader input;
        BufferedWriter output;
        try {
            socket = new Socket(host, port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        thread = new ClientThread(input, output, this);
        new Thread(thread).start();
    }

    public BotApplication getApplication() {
        return application;
    }

    static class ClientThread implements Runnable {

        private BufferedReader input;
        private BufferedWriter output;
        private ChatClient chatClient;

        public ClientThread(BufferedReader input, BufferedWriter output, ChatClient chatClient) {
            this.input = input;
            this.output = output;
            this.chatClient = chatClient;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String message = input.readLine() + "\n";
                    chatClient.getApplication().appendMessage(message);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public BufferedWriter getOutput() {
            return output;
        }
    }
}
