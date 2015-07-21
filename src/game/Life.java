/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import framework.GObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author bryan
 */
public class Life extends GObject{
   private Jet _jet;
   
   public Life(Jet jet) {
      this._jet = jet;
   }
   
   public void render(Graphics2D g2) {
      int maxbar = this._jet.getWidth();
      int maxlife = this._jet.maxLifePoints;
      int currentLife = this._jet.lifePoints;
      
      // redbar = (maxbar * currentLife) / maxlife
      double greenBar = (maxbar * currentLife) / maxlife;
      
      
//      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.white);
      g2.fill(new Rectangle(this._jet.getX(), this._jet.getY() + this._jet.getHeight(), this._jet.getWidth(), 5));
      g2.setColor(Color.green);
      g2.fill(new Rectangle(this._jet.getX(), this._jet.getY() + this._jet.getHeight(), (int) greenBar, 5));
      g2.drawString("Life: " + currentLife,this._jet.getX(),this._jet.getY() + this._jet.getHeight() + 20);
      
   }
}
