/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.GBullet;
import framework.GObject;
import java.util.ArrayList;

/**
 *
 * @author bryan
 */
public class Bullet extends GBullet {
   private int _cntr = 0;
   public boolean isHit = false;
   
   public Bullet() {
      super();
      this.setSpeed(3);
   }
   public Bullet(int speed) {
      super(speed);
      this.setSpeed(3);
   }
   
   public Bullet(Jet jet) {
      this.setSpeed(3);
      
      String filename[] = new String[2];
      for(int i = 0; i < 2;i++) {
         filename[i] = "images/bullet" + i + ".png";
      }
      this.loadImage(filename);
      
      double difx = (jet.getWidth() - this._width) / 2;
      
      this.setX(jet.getX() + (int) difx);
      this.setY(jet.getY() - 24);
   }
   
   public void animate(ArrayList<Asteroids> asteriod) { 
      if(asteriod.size() > 0) {
         for(int i=0;i<asteriod.size(); i++) {            
            if((this._x + this._width) >= asteriod.get(i).getX() && (this._x + this._width) < (asteriod.get(i).getX() + asteriod.get(i).getWidth()) && 
                    this._y <= (asteriod.get(i).getY() + asteriod.get(i).getHeight()) && this._y >= asteriod.get(i).getY()) {
               
               asteriod.remove(i);
               this.isHit = true;
            }
         }
      }
      
      
      this._y -= this._speed;
      
      if(this._cntr >= this._sprites.length) {
         this._cntr = 0;
      }
      this._img = this._sprites[this._cntr];
      this._cntr++;
   }   
}
