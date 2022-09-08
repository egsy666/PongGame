import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class PongFrame extends JFrame {

	PongPanel panel;
	
	PongFrame() {
		
		panel = new PongPanel();
		this.add(panel);
		
		this.setBackground(Color.black); // vom frame wird der hintergrund gesetzt nicht vom panel; 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Pong");
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		
	}

}
