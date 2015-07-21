/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author bryan
 */
public class GForm {
   public static GraphicsDevice device;
   public static Frame frame;
   
   /**
    * @dateCreation July 31, 2011
    * @author Bryan Salazar
    * @param Frame frame, int Width, int Height, int BitDepth 
    * @description set full screen  the form
    */
   public static void setFullScreen(Frame frame,int Width,int Height,int BitDepth) {
      GForm.frame = frame;
      DisplayMode displayMode = new DisplayMode(Width, Height, BitDepth,
              DisplayMode.REFRESH_RATE_UNKNOWN);
      
      GraphicsEnvironment environment = 
              GraphicsEnvironment.getLocalGraphicsEnvironment();
      
      GForm.device = environment.getDefaultScreenDevice();
      
      GForm.frame.setUndecorated(true);
      GForm.frame.setResizable(false);
      
      GForm.device.setFullScreenWindow(GForm.frame);
      if(displayMode != null && GForm.device.isDisplayChangeSupported()) {
         try {
            GForm.device.setDisplayMode(displayMode);
         } catch(IllegalArgumentException ex) {
            // ignore - illegal mode for this device
         }
      }
   }
   
   public static void setFullScreen(Frame frame) {
      GForm.setFullScreen(frame, 800, 600, 16);
   }
   
   public static Window getFullScreenWindow() {
      return GForm.device.getFullScreenWindow();
   }
   
   public static void restoreScreen() {
      Window window = GForm.device.getFullScreenWindow();
      if(window != null) {
         window.dispose();
      }
      GForm.device.setFullScreenWindow(null);
      GForm.frame.setSize(500, 500);
      GForm.frame.setVisible(true);
   }
   
   /**
    * @dateCreation July 23, 2011
    * @author Bryan Salazar
    * @param Frame frame
    * @description centralize the form
    */
   public static void centralize(Frame frame) {
      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (screen.width-frame.getWidth())/2;
      int y = (screen.height-frame.getHeight())/2;
      frame.setLocation(x, y);
      frame.setVisible(true);
   }
   
   /**
    * @dateCreation July 24,2011
    * @author Bryan Salazar
    * @param Frame frame
    * @description add close 
    */
   public static void attachClose(Frame frame) {
      frame.addWindowListener(new WindowAdapter(){
         public void windowClosing(WindowEvent we){
            System.exit(0);
         }
      });
   }
}
