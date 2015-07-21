/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.GObject;

/**
 *
 * @author bryan
 */
public class Explosion extends GObject{
   public int cntr = 0;
   
   public Explosion() {
      String filename[] = new String[8];
      for(int i = 0; i < 8;i++) {
         filename[i] = "images/c" + i + ".gif";
      }
      this.loadImage(filename);
   }
   
   @Override
   public void animate() {      
      this._img = this._sprites[this.cntr];
      this.cntr++;
   }
}
