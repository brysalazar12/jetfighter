/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import framework.ErrorDialog;
import framework.GForm;
import framework.GKeyManager;
import game.Asteroids;
import game.Background;
import game.Bullet;
import game.Explosion;
import game.Jet;
import game.Life;
import game.Stars;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author bryan
 */
public class GBulletTest extends Frame implements Runnable, KeyListener {
   
   Image buffer;
   Background background;
   Life life;
   Jet jet;
   int y;
   ArrayList<Bullet> bullet = new ArrayList<Bullet>();
   ArrayList<Stars> star = new ArrayList<Stars>(); 
   ArrayList<Asteroids> asteriod = new ArrayList<Asteroids>();
   ArrayList<Explosion> explosion = new ArrayList<Explosion>();
   int starCntr = 0;
   int asteriodCntr = 0;
   int points = 0;
   public static Thread t;
//   Bullet b;
   
   @Override
   public void run() {
      while(true) {
         try {
            Thread.sleep(20);
         } catch(InterruptedException io){new ErrorDialog(io);}

         jet.animate();
         
         // check if there is bullet
         if(bullet.size() > 0) {
            for(int i = 0; i < bullet.size(); i++) {
               
               // animate bullet and check for collision if collide remove asteriod
               bullet.get(i).animate(asteriod);
               
               // bullet hit the asteriod
               if(bullet.get(i).isHit) {
                  
                  // increase points
                  points++;
                  
                  // create explosion
                  explosion.add(new Explosion());
                  int z = explosion.size() - 1;
                  explosion.get(z).setX(bullet.get(i).getX());
                  explosion.get(z).setY(bullet.get(i).getY());
                  
                  // remove bullet
                  bullet.remove(i);
               } else if(bullet.get(i).getY() <= -22) {
                  
                  // remove bullet out of screen
                  bullet.remove(i);
               }
            }
         }
         
         if(starCntr > 5) {
            star.add(new Stars());
            starCntr = 0;
         }
         if(asteriodCntr > 50) {
            asteriod.add(new Asteroids(jet));
            asteriodCntr = 0;
         }
         if(asteriod.size() > 0) {
            for(int i = 0; i < asteriod.size(); i++) {
               asteriod.get(i).animate();
               
               if(asteriod.get(i).isHit) {
                  jet.lifePoints -= asteriod.get(i).damage;
                  // create explosion
                  explosion.add(new Explosion());
                  int z = explosion.size() - 1;
                  explosion.get(z).setX((int)asteriod.get(i).getX() + (asteriod.get(i).getWidth())/2);
                  explosion.get(z).setY((int)asteriod.get(i).getY() + (asteriod.get(i).getHeight())/2);
                  asteriod.remove(i);
                  if(jet.lifePoints <= 0)
                  {
                     repaint();
                     GBulletTest.t.stop();
                  }
               } else if(asteriod.get(i).getY() >= 600) {
                  asteriod.remove(i);
               }
            }
         }
         if(star.size() > 0) {
            for(int i = 0; i < star.size(); i++) {
               star.get(i).animate();
               if(star.get(i).getY() >= 600) {
                  star.remove(i);
               }
            }
         }
         
         if(explosion.size() > 0) {
            for(int i = 0; i < explosion.size(); i++) {
               explosion.get(i).animate();
               if(explosion.get(i).cntr >= 8) {
                  explosion.remove(i);
               }
            }
         }
         
         starCntr++;
         asteriodCntr++;
         repaint();
      }
   }
   
   public GBulletTest() {
      jet = new Jet();
      jet.setX(250);
      jet.setY(480);
      jet.setSpeed(3);
      life = new Life(jet);
      background = new Background();
      background.setX(0);
      background.setY(0);
      
      GKeyManager keymanager = new GKeyManager();
      addKeyListener(keymanager);
      addKeyListener(this);
      setSize(600, 600);
      setResizable(false);
      GForm.attachClose(this);
      GForm.centralize(this);
      
   }
   
   public static void main(String args[]) {
//      (new Thread(new GBulletTest())).start();
      
      GBulletTest.t = new Thread(new GBulletTest());
      GBulletTest.t.start();
   }
   
   @Override
   public void paint(Graphics g) {
      buffer = createImage(600,600);
      Graphics bufG = buffer.getGraphics();
      background.render(bufG);
      if(star.size() > 0) {
         for(int i = 0; i < star.size(); i++) {
            star.get(i).render(bufG);
         }
      }
      if(asteriod.size() > 0) {
         for(int i = 0; i < asteriod.size(); i++) {
            asteriod.get(i).render(bufG);
         }
      }
      
      if(explosion.size() > 0) {
         for(int i = 0; i < explosion.size(); i++) {
            explosion.get(i).render(bufG);
         }
      }
      
      jet.render(bufG);
      
      
      if(bullet.size() > 0) {
         for(int i = 0; i < bullet.size(); i++) {
            bullet.get(i).render(bufG);
         }
      }

      Graphics2D g2 = (Graphics2D) bufG;
      
      g2.setColor(Color.red);
      g2.drawString("SCORE: "+points, 500, 100);
      
      life.render(g2);
      
      if(jet.lifePoints <= 0) {
         g2.setColor(Color.red);
         g2.setFont(new Font("Algerian",Font.BOLD,25));
         g2.drawString("GAME OVER!", 240, 250);
      }
      g.drawImage(buffer, 0, 0, 600, 600, this);

   }
   
   @Override
   public void update(Graphics g) {
      paint(g);
   }

   @Override
   public void keyTyped(KeyEvent e) {
//      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void keyPressed(KeyEvent e) {
      if(GKeyManager.FIRE) {
//         System.out.println(bullet.size());
         bullet.add(new Bullet(jet));
      }
   }

   @Override
   public void keyReleased(KeyEvent e) {
//      throw new UnsupportedOperationException("Not supported yet.");
   }
}
