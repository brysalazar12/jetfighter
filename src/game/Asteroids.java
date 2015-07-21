/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.GObject;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author bryan
 */
public class Asteroids extends GObject{
   
   private Jet _jet;
   public boolean isHit = false;
   public int damage = 5;
   
   public Asteroids(Jet j) {
      this._jet = j;
      String filename[] = new String[1];
      for(int i = 0; i < 1;i++) {
         filename[i] = "images/gon" + i + ".gif";
      }
      this.loadImage(filename);
      Random randint = new Random();
//      this._img = this._sprites[randint.nextInt(3)];
      this._img = this._sprites[0];
      this.setSpeed(2);
      
      this.setX(randint.nextInt(500));
   }
   
   @Override
   public void animate() {
      this._y += this._speed;
      
      int maxy = this._y + this._height;
      int maxx = this._x + this._width;
      
      if(maxy >= this._jet.getY() && maxx >= this._jet.getX() && this._x <= (this._jet.getX() + this._jet.getWidth())) {
         this.isHit = true;
      }
   }  
}
