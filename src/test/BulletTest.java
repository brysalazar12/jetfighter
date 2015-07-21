/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import framework.ErrorDialog;
import framework.GForm;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author bryan
 */
public class BulletTest extends Frame implements Runnable {
   String fullLocation = "images/gon.gif";
   BufferedImage img = null;
   BufferedImage img2 = null;
   BufferedImage img3 = null;
   URL url;
  static int imageW = 100;

  static int imageH = 150;
  ImageIcon icon;
  Image originalImage;
  static int  y=500;
  Rectangle rect = new Rectangle(0, 0, 700, 700);
  
   public BulletTest(String s) {
      super(s);
      setSize(700, 700);
      GForm.attachClose(this);
      GForm.centralize(this);

   }
   
   public void createAnimation() throws InterruptedException {

   }
   
   public void run() {
//      int cntr = 0;
      while(true){
//         cntr++;
//         System.out.println(cntr);
         try {
            Thread.sleep(20);
         }catch(InterruptedException ie){}
         repaint();

      }
   }
   
   public static void main(String args[]) {
      (new Thread(new BulletTest("Bullet"))).start();
   }
   
   @Override
   public void update(Graphics g) {
      paint(g);
   }
   
   @Override
   public void paint(Graphics g) {

   }
}
