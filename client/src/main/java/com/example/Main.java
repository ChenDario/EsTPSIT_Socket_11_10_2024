package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Socket s = new Socket("localhost", 3000);
        System.out.println("Il client si è collegato");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        
        Scanner scanner = new Scanner(System.in);

        String stringRicevuta;
        String stringOperazione;

        do {
            //Si inserisce la stringa da mandare al server
            System.out.println("Inserire la stringa: ");
            String messaggioAlServer = scanner.nextLine(); 
    
            out.writeBytes(messaggioAlServer + "\n");

            operazione();
            stringOperazione = scanner.nextLine();

            out.writeBytes(stringOperazione + "\n");

            stringRicevuta = in.readLine();

            if(!stringRicevuta.equalsIgnoreCase("!"))
                System.out.println("La stringa ricevuta dal server: " + stringRicevuta);
            
        } while (!stringRicevuta.equalsIgnoreCase("!"));

        System.out.println("\n Server chiuso");
        s.close();
        scanner.close();
        out.close();
        in.close();
    }

    public static void operazione() {
        System.out.println("\n Selezionare una delle seguenti operazioni");
        System.out.println("\n a) Trasformare stringa in maiuscolo");
        System.out.println("\n b) Trasformare stringa in minuscolo");
        System.out.println("\n c) Ribaltare i caratteri della stringa");
        System.out.println("\n d) Contare il numero di caratteri");
        System.out.println("\n 0) Confermare la disconnesione del server digitando exit");
    }
}