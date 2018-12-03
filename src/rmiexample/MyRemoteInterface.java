/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiexample;

import java.rmi.* ;

  public interface MyRemoteInterface extends Remote {
      void printMessage(String message) throws RemoteException ;
  }