package org.example;

import java.io.*;
import java.util.*;
import java.net.*;

public class Server {

    static Vector<ClientHandler> ar = new Vector<>();

    static int i = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1234);

        Socket s;
        List<String> names = new ArrayList<>();
        while (true) {
            s = ss.accept();
            System.out.println("New client request received : " + s);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            System.out.println("Creating a new handler for this client...");
            Scanner scn = new Scanner(System.in);
            System.out.println("Enter the name of user to be created: ");
            String name = scn.nextLine();
            names.add(name);
            ClientHandler mtch = new ClientHandler(s, "client " + name, dis, dos);
            Thread t = new Thread(mtch);
            System.out.println("Adding this client to active client list");
            if (names.contains(name)) {
                ar.add(mtch);
                t.start();
                names.add(name);
            }
        }
    }
}