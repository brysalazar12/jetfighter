/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

/**
 *
 * @author bryan
 */
public class GBullet extends GObject {
   private int _cntr = 0;
   
   public GBullet() {
      String filename[] = new String[2];
      for(int i = 0; i < 2;i++) {
         filename[i] = "images/bullet" + i + ".png";
      }
      this.loadImage(filename);
   }
   
   public GBullet(int speed) {
      this._speed = speed;
      String filename[] = new String[2];
      for(int i = 0; i < 2;i++) {
         filename[i] = "images/bullet" + i + ".png";
      }
      this.loadImage(filename);
   }   
   
   @Override
   public void animate() { 
      this._y -= this._speed;
      
      if(this._cntr >= this._sprites.length) {
         this._cntr = 0;
      }
      this._img = this._sprites[this._cntr];
      this._cntr++;
   }
}
