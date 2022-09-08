import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class PongPaddle extends Rectangle {
	
	int yVelocity;
	int id;
	int speed = 10;
	
	PongPaddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id = id;
	}
	
	public void move() {
		y = y + yVelocity;
	}
	
	public void draw(Graphics g) {
		if(id==1) {
			g.setColor(Color.BLUE);
		} else {
			g.setColor(Color.RED);
		}
		
		g.fillRect(x, y, width, height);
	}
	
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1: if(e.getKeyCode() == KeyEvent.VK_W) {
			setYDirection(-speed);
			move();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			setYDirection(speed);
			move();
		} break;
		
		
		case 2: if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			setYDirection(speed);
			move();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			setYDirection(-speed);
			move();
		} break;
		
		
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 2: if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			setYDirection(0);
			move();
		} else if(e.getKeyCode() == KeyEvent.VK_UP) {
			setYDirection(0);
			move();
		} break;
		
		case 1: if(e.getKeyCode() == KeyEvent.VK_W) {
			setYDirection(0);
			move();
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			setYDirection(0);
			move();
		} break;
		}
	}

	
	
}
