package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    private Socket socketClient;

    public MioThread(Socket s){
        this.socketClient = s;
    }

    public void run(){
        try {
            //Tutta sta roba sotto dentro al thread
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());
            
            String stringRicevuta;
            
            do {
                stringRicevuta = in.readLine(); //Leggere ciò che ci manda il client
                System.out.println("La stringa ricevuta: " + stringRicevuta); //Leggiamo il messaggio del client
            
                if(stringRicevuta.equalsIgnoreCase("exit")){
                    out.writeBytes("!"); //Mandare al client un messaggio
                    break;
                } else{
                    String stringaMaiuscola = stringRicevuta.toUpperCase();//Metto in upperCase
                    out.writeBytes(stringaMaiuscola + "\n"); //Lo mando al client
                }
            
            } while (!stringRicevuta.equalsIgnoreCase("exit"));
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("\n Collegamento interrotto");
    }
}
