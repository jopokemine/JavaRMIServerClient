/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiexample;

/**
 *
 * @author josh
 */
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.io.*;

public class MyServer extends UnicastRemoteObject implements MyRemoteInterface {
    
    @Override
    public void printMessage(String message) throws RemoteException {
        System.out.println(message);
    }
    
    public static void main(String args []) throws Exception {
        
        //Server
        MyServer server = new MyServer();
        
        Registry reg = LocateRegistry.createRegistry(1234);
        reg.bind("myrmiserver", server);
        
        //Client
        
        MyRemoteInterface handle = null;
        boolean exception = false;
        
        do {
            try {
                exception = false;
                Registry reg2 = LocateRegistry.getRegistry("0.0.0.0", 1235);
                handle = (MyRemoteInterface) reg2.lookup("myrmiserver");
                
            } catch (NotBoundException | RemoteException e) {
                System.out.println("Waiting...");
                exception = true;
                Thread.sleep(1000);
            }
        } while (exception);
        
        String message;
        
        while (true) {
            
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            
            message = in.readLine();
            
            handle.printMessage("Computer 1: " + message);
            
        }
        
    }
    
    public MyServer() throws RemoteException {}
        
    
}