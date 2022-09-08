import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class PongScore extends Rectangle {
	
	static int GAME_WIDTH = 1000;
	static int GAME_HEIGHT = 600;
	
	int player1 = 0;
	int player2 = 0;

	PongScore(int GAME_WIDTH, int GAME_HEIGHT) {
		PongScore.GAME_WIDTH = GAME_WIDTH;
		PongScore.GAME_HEIGHT = GAME_HEIGHT;
		
	}
	
	public void draw(Graphics g) {
		g.setFont(new Font(("default"),Font.BOLD,20));
		g.setColor(Color.white);
		g.drawString(String.valueOf(player1), (GAME_WIDTH/4), 50);
		g.drawString(String.valueOf(player2), (GAME_WIDTH/2) + (GAME_WIDTH/4), 50);
	}
	
}
