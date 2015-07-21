/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Bryan Salazar
 */
public class Client implements Runnable{
   Socket socket;
   PrintWriter printWriter;
   PrintStream printStream;
   public Client() {
      
   }
   
   public static void main(String args[]) {
      (new Thread(new Client())).start();
   }
   
   @Override
   public void run() {
      try {
         socket = new Socket("localhost", 7777);
      } catch (UnknownHostException ex) {
         Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
         Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
      }
      while(true) {
         try {
            //         printWriter.println("hello world");
            Thread.sleep(5000);
            try {
               printStream = new PrintStream(socket.getOutputStream(),true);
               printStream.println(" hello world yes");
               System.out.println("hello world yes");
            } catch (IOException ex) {
               Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
                     
         } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }
}
