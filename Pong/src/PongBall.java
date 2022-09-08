import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class PongBall extends Rectangle {
	
	Random random = new Random();
	int [] randomX = {-1,1};
	int randomChose;
	int yVelocity;
	int xVelocity;
	int speed = 2;
	
	PongBall(int x, int y, int pongDiameter) {
		super(x,y,(pongDiameter),(pongDiameter));
		randomChose = random.nextInt(2);
		if(randomChose == 0) {
			randomChose--;
		}
		setXDirection(randomChose);
		randomChose = random.nextInt(2);
		if(randomChose == 0) {
			randomChose--;
		}
		setYDirection(randomChose);
		
	}
	
	public void resetSpeed() {
		speed = 2;
	}
	
	public void setXDirection(int randomX) {
		xVelocity = randomX;
	}
	
	public void setYDirection(int randomY) {
		yVelocity = randomY;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
	public void move() {
		this.x += xVelocity*speed;
		this.y += yVelocity*speed;
	}

}
