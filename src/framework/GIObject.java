/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author bryan
 */
public interface GIObject {
   
   public int getX();
   
   public int getY();
   
	public void move();
   
	public void render(Graphics g);
   
   public void render(Graphics g, int x, int y);
   
	public boolean hitTest(GObject piece);
   
	public Rectangle getHitTestRectangle(); 
   
   public void setSpeed(int speed);
}
