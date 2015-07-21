/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import framework.GForm;
import java.awt.Frame;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.SysexMessage;
/**
 *
 * @author Bryan Salazar
 */
public class MainForm extends Frame implements Runnable{
   ServerSocket serverSocket;
   BufferedReader reader;
   InputStreamReader streamReader;
   PrintWriter writer;
   String line;
   boolean connect = true;
   
   public MainForm() {
//      setSize(600, 600);
//      setResizable(false);
//      GForm.attachClose(this);
//      GForm.centralize(this);
   }
   
   public static void main(String args[]) {
      (new Thread(new MainForm())).start();
   }
   
   public void run() {
      
      try {
         serverSocket = new ServerSocket(7777);
         
      } catch (IOException ex) {
         System.out.println("Could not listen on port: 4444");
      }
      Socket clientSocket = null;
      try {
         System.out.println("waiting ... ");
         clientSocket = serverSocket.accept();
      } catch (IOException e) {
         System.out.println("Accept failed: 4444");
         System.exit(-1);
      }

      System.out.println("test");
      
      while(true) {
         if(connect) {
            try {
               reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
               if(reader.read() == -1) {
                  connect = false;
                  System.out.println("disconnected");
               } else {
                  line = reader.readLine();
               }
               

            } catch (IOException ex) {
               Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (line != null) {
//              writer.println("Echo: " + line);
              System.out.println(line);
            }
         }
         
      }
   }
}
