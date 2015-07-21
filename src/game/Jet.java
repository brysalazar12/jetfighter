/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.GKeyManager;
import framework.GObject;
import java.util.ArrayList;
/**
 *
 * @author bryan
 */
public class Jet extends GObject{
   private int _cntr;
   public boolean isHit = false;
   public int lifePoints = 100;
   public final int maxLifePoints = 100;
   
   public Jet() {
      String filename[] = new String[7];
      for(int i = 0; i < 7;i++) {
         filename[i] = "images/jet" + i + ".png";
      }
      this.loadImage(filename);
   }
   
   public void animate() {
      
      this.move();
      if(this._x <= 0) {
         this._x = 0;
      }
      if(this._x >= 500) {
         this._x = 500;
      }
	  if(this._y >= 550) {
		  this._y = 500;
	  }
	  if(this._y <= 30) {
		  this._y = 30;
	  }
	  
      
      if(GKeyManager.LEFT == false && GKeyManager.RIGHT == false) {
         if(this._cntr >= 3) {
            this._cntr = 0;
         }
         
      } else if(GKeyManager.RIGHT) {
         if(this._cntr >=7) {
            this._cntr = 5;
         }
      } else if(GKeyManager.LEFT) {
         if(this._cntr >=5) {
            this._cntr = 3;
         }
      } else if(GKeyManager.DOWN) {
		  this._cntr = 0;
	  }
      this._img = this._sprites[this._cntr];
      this._cntr++;
   }
}
