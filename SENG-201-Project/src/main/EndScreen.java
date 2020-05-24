package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EndScreen {

	private JFrame screen;
	private Game game;

	private final int windowHeight = 300;
	private final int windowWidth = 600;
	
	/**
	 * Create the window
	 * @param incomingGame - state of the game when window is launched
	 */
	public EndScreen(Game incomingGame) {
		game = incomingGame;
		initialize();
		screen.setVisible(true);
	}
	
	/**
	 * Close the window
	 */
	public void closeWindow() {
		screen.dispose();
	}
	
	/**
	 * Tells the game that the window can be closed
	 */
	public void finishedWindow() {
		game.closeEndScreen(this);
	}

	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		screen = new JFrame();
		screen.setBounds(100, 100, windowWidth, windowHeight);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.getContentPane().setLayout(null);
		
		JLabel farmNameLabel = new JLabel(game.getFarm().getName());
		farmNameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		farmNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		farmNameLabel.setBounds(0, 20, windowWidth, 30);
		screen.getContentPane().add(farmNameLabel);
		
		JLabel durationLabel = new JLabel("Duration: " + game.getDuration() + " days");
		durationLabel.setBounds(100, 70, 200, 16);
		screen.getContentPane().add(durationLabel);
		
		JLabel profitLabel = new JLabel("Profit: $" + String.format("%.2f", game.profit()));
		profitLabel.setBounds(100, 100, 200, 16);
		screen.getContentPane().add(profitLabel);
		
		JLabel scoreLabel = new JLabel("Score: " + game.score() + "pts");
		scoreLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setBounds(0, 130, windowWidth, 30);
		screen.getContentPane().add(scoreLabel);
		
		JButton closeGameBtn = new JButton("Close Game");
		closeGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		closeGameBtn.setBounds(windowWidth/2 - 75, 190, 150, 50);
		screen.getContentPane().add(closeGameBtn);
	}
}
