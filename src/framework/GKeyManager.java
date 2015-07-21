/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class GKeyManager implements KeyListener {
	private boolean left = false;
	private boolean right = false;
	private boolean fire = false;
	private boolean up = false;
	private boolean down = false;
   
   public static boolean LEFT = false;
   public static boolean RIGHT = false;
   public static boolean UP = false;
   public static boolean DOWN = false;
   public static boolean FIRE = false;
   public static boolean ALT = false;
   
	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}
   
	public boolean isLeft() {
		return left;
	}
	public boolean isRight() {
		return right;
	}
	public boolean isFire() {
		return fire;
	}
	public void keyPressed(KeyEvent e){
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;        
         GKeyManager.LEFT = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
         GKeyManager.RIGHT = true;
			break;
		case KeyEvent.VK_SPACE:
			fire = true;
			 GKeyManager.FIRE = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			GKeyManager.UP = true;
			break;
		case KeyEvent.VK_DOWN:
			GKeyManager.DOWN = true;
			down = true;
			break;
      case KeyEvent.VK_ALT:
         GKeyManager.ALT = true;
         break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
         GKeyManager.LEFT = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
         GKeyManager.RIGHT = false;
			break;
		case KeyEvent.VK_UP:
			up = true;
			GKeyManager.UP = false;
			break;
		case KeyEvent.VK_DOWN:
			GKeyManager.DOWN = false;
			down = true;
			break;			
		case KeyEvent.VK_SPACE:
			fire = false;
         GKeyManager.FIRE = false;
			break;
      case KeyEvent.VK_ALT:
         GKeyManager.ALT = false;
         break;         
		}
	}
	public void keyTyped(KeyEvent e) {
// check for escape to exit the game
		if (e.getKeyChar() ==
			KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}
	public GKeyManager() {
	}
}