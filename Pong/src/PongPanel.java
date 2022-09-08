import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class PongPanel extends JPanel implements Runnable {
	
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = 600;
	static final Dimension screenSize = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int pongDiameter = 20;
	static final int PADDLE_WIDTH = 20;
	static final int PADDLE_HEIGHT = 100;
	
	Image image;
	Graphics graphics;
	Thread thread;
	PongPaddle paddle1;
	PongPaddle paddle2;
	PongBall ball;
	PongScore score;

	
	PongPanel() {
		newBall();
		newPaddles();
		score = new PongScore(GAME_WIDTH, GAME_HEIGHT);
		
		this.setFocusable(true);
		this.setPreferredSize(screenSize);
		
		this.addKeyListener(new AL());
		thread = new Thread(this);
		thread.start();
		
	}
	
	public void newBall() {
		ball = new PongBall(((GAME_WIDTH/2)-pongDiameter/2),((GAME_HEIGHT/2)-pongDiameter/2),pongDiameter);
	}
	
	public void newPaddles() {
		paddle1 = new PongPaddle(0,(GAME_HEIGHT / 2) - ( PADDLE_HEIGHT / 2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new PongPaddle((GAME_WIDTH - PADDLE_WIDTH),(GAME_HEIGHT / 2) - ( PADDLE_HEIGHT / 2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
		
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	
	
	public void run() { // needs to be implemented for Runnable; runnable needs to be implements to use threads in java
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime=now;
			if(delta >=1) {
				move();
				checkCollission();
				repaint();
				delta--;
			}
		}
	}
	
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	public void resetSpeed() {
		ball.resetSpeed();
		
	}
	
	public void checkCollission() {
		
		// ball no go out of field
		if(paddle1.y < 0) {
			paddle1.y = 0;
		} 
		
		if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		}
		
		if(paddle2.y <= 0) {
			paddle2.y = 0;
		}
		
		if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		}
		
		
		// ball grid detection
		
		if(ball.y <= 0 || ball.y >= (GAME_HEIGHT-pongDiameter)) {
			ball.yVelocity = ball.yVelocity *-1;
		}
		
		if(ball.x <= 0 || ball.x >= (GAME_WIDTH-pongDiameter)) {
			if(ball.x<=0) {
				score.player2++;
				
			} else if ( ball.x>=GAME_WIDTH-pongDiameter) {
				score.player1++;
				
			}
			newBall();
			newPaddles();
			resetSpeed();
			
		}
		
		// ball get hit by paddle
		
		if(paddleBallHit() == true) {
			ball.xVelocity = ball.xVelocity*-1;
			ball.speed+=1;
			
		}
		
		
		
	}
	
	public boolean paddleBallHitP1(PongPaddle paddle) {
			boolean boo1 = false;
			boolean boo2 = false;
			
			if(ball.x >= paddle.x && ball.x <= paddle.x + PADDLE_WIDTH) {
				boo1 = true;
			}
			
			if(ball.y >= paddle.y && ball.y <= paddle.y + PADDLE_HEIGHT) {
				boo2 = true;
			}
			
		
		
		if(boo1 == true && boo2 == true) {
			return true;
		} else {
			return false;
		} }
	
	public boolean paddleBallHitP2(PongPaddle paddle) {
		boolean boo1 = false;
		boolean boo2 = false;
		
		if(ball.x+pongDiameter >= paddle.x && ball.x+pongDiameter <= paddle.x + PADDLE_WIDTH) {
			boo1 = true;
		}
		
		if(ball.y+pongDiameter >= paddle.y && ball.y+pongDiameter <= paddle.y + PADDLE_HEIGHT) {
			boo2 = true;
		}
		
	
	
	if(boo1 == true && boo2 == true) {
		return true;
	} else {
		return false;
	} }

	
	public boolean paddleBallHit() {
		if(paddleBallHitP1(paddle1) == true || paddleBallHitP2(paddle2) == true) {
			return true;
		} else {
			return false;
		}
	}
	
	class AL extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
		
	}

}
