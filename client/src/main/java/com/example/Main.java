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

        do {
            //Si inserisce la stringa da mandare al server
            System.out.println("Inserire la stringa: ");
            String messaggioAlServer = scanner.nextLine(); 
    
            out.writeBytes(messaggioAlServer + "\n");
    
            stringRicevuta = in.readLine();

            if(stringRicevuta.equalsIgnoreCase("!")){
                System.out.println("\n Server chiuso");
                s.close();
                scanner.close();
                out.close();
                in.close();
                break;
            } else {
                System.out.println("La stringa ricevuta dal server: " + stringRicevuta);
            }
        
        } while (!stringRicevuta.equalsIgnoreCase("!"));

    }
}