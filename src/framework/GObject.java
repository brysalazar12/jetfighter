/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author bryan
 */
public class GObject implements GIObject {
	protected Image _img;
   protected Image _sprites[] = null;
   // width of image
	protected int _width;
   // height of image
	protected int _height;
   // x-coordinate of image
   protected int _x;
   // y-coordinate of image
   protected int _y;
   
   protected int _speed = 1;
   
   boolean isAnimatedSprite;
   int numberOfImages;
   
   private int _cntr = 0;
      
   public void loadImage(String filename) {
      URL url;
      url = this.getClass().getClassLoader().getResource(filename);
      try {
         this._img = ImageIO.read(url);
         this._width = this._img.getWidth(null);
         this._height = this._img.getHeight(null);
      } catch(IOException io){new ErrorDialog(io);}
   }
   
   public void loadImage(String filename[]) {
      this._sprites = new Image[filename.length];
      URL url;
      for(int i=0; i < filename.length; i++) {
         try {
            url = this.getClass().getClassLoader().getResource(filename[i]);
            this._sprites[i] = ImageIO.read(url);
            this._width = this._sprites[i].getWidth(null);
            this._height = this._sprites[i].getHeight(null);
         } catch(IOException io) {
            new ErrorDialog(io);
         }
      }
   }
   
   public void animate() {      
      if(this._cntr >= this._sprites.length) {
         this._cntr = 0;
      }
      this._img = this._sprites[this._cntr];
      this._cntr++;
   }
   
//   public Image loadImage(String filename){
//      try {
//         URL url = this.getClass().getClassLoader().getResource(filename);
//         sprite = ImageIO.read(url);
//      }catch(IOException io) {
//         new ErrorDialog(io);
//      }   
//
////      width = sprite.getWidth(null);
////      height = sprite.getHeight(null);
////      return sprite;
//   }   
   
	public int getWidth() {
		return this._width;
	}
	public int getHeight() {
		return this._height;
	}
   
   public void move() {
      if(GKeyManager.LEFT) {
         this._x -= this._speed;
      }
      if(GKeyManager.RIGHT) {
         this._x += this._speed;
      }
	  if(GKeyManager.UP) {
		  this._y -= this._speed;
	  }
	  if(GKeyManager.DOWN) {
		  this._y += this._speed;
	  }
   }
   
   

   @Override
   public boolean hitTest(GObject piece) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public Rectangle getHitTestRectangle() {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void setSpeed(int speed) {
      this._speed = speed;
   }

   @Override
   public void render(Graphics g, int x, int y) {
      g.drawImage(this._img,x,y,null);
   }
   
   
   @Override
   public void render(Graphics g) {
//      AffineTransform at = new AffineTransform();
//    // scale image
//    at.scale(2.0, 2.0);
//
//    // rotate 45 degrees around image center
//    at.rotate(45.0 * Math.PI / 180.0, this._img.getWidth(null) / 2.0, this._img.getHeight(null) / 2.0);
//    Graphics2D g2 = (Graphics2D) g;
//    g2.
      g.drawImage(this._img, this._x, this._y, null);
   }

   @Override
   public int getX() {
      return this._x;
   }

   @Override
   public int getY() {
      return this._y;
   }
   
   public void setY(int y) {
      this._y = y;
   }
   
   public void setX(int x) {
      this._x = x;
   }
}
