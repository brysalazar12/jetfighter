/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.GObject;
import java.util.Random;

/**
 *
 * @author bryan
 */
public class Stars extends GObject{

   public Stars() {
      String filename[] = new String[2];
      for(int i = 0; i < 2;i++) {
         filename[i] = "images/star" + i + ".png";
      }
      this.loadImage(filename);
      Random randint = new Random();
      this._img = this._sprites[randint.nextInt(2)];
      this.setSpeed(3);
      
      this.setX(randint.nextInt(500));
   }
   
   @Override
   public void animate() { 
      this._y += this._speed;
   }   
}
