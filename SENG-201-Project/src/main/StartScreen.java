package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class StartScreen {

	private JFrame frame;
	private JTextField farmerNameField;
	private JTextField farmerAgeField;
	private JTextField farmNameField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreen window = new StartScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel welcome = new JLabel("Welcome!");
		welcome.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		welcome.setBounds(182, 5, 77, 18);
		frame.getContentPane().add(welcome);
		
		JLabel farmerNameLabel = new JLabel("Please enter your name (3-15 characters): ");
		farmerNameLabel.setBounds(16, 35, 266, 16);
		frame.getContentPane().add(farmerNameLabel);
		
		farmerNameField = new JTextField();
		farmerNameField.setBounds(300, 30, 130, 26);
		frame.getContentPane().add(farmerNameField);
		farmerNameField.setColumns(10);
		
		JLabel farmerAgeLabel = new JLabel("Please enter your age: ");
		farmerAgeLabel.setBounds(16, 63, 178, 16);
		frame.getContentPane().add(farmerAgeLabel);
		
		farmerAgeField = new JTextField();
		farmerAgeField.setBounds(300, 58, 130, 26);
		frame.getContentPane().add(farmerAgeField);
		farmerAgeField.setColumns(10);
		
		JLabel farmNameLabel = new JLabel("Name your farm: ");
		farmNameLabel.setBounds(16, 206, 109, 16);
		frame.getContentPane().add(farmNameLabel);
		
		farmNameField = new JTextField();
		farmNameField.setBounds(300, 201, 130, 26);
		frame.getContentPane().add(farmNameField);
		farmNameField.setColumns(10);
		
		JSlider gameLengthSlider = new JSlider();
		gameLengthSlider.setSnapToTicks(true);
		gameLengthSlider.setPaintLabels(true);
		gameLengthSlider.setPaintTicks(true);
		gameLengthSlider.setMaximum(10);
		gameLengthSlider.setMinimum(5);
		gameLengthSlider.setBounds(300, 219, 130, 53);
		frame.getContentPane().add(gameLengthSlider);
		
		JLabel gameLengthLabel = new JLabel("Set the length of your game: ");
		gameLengthLabel.setBounds(16, 234, 190, 16);
		frame.getContentPane().add(gameLengthLabel);
		
		JLabel farmTypeLabel = new JLabel("Choose your farm type: ");
		farmTypeLabel.setBounds(16, 91, 151, 16);
		frame.getContentPane().add(farmTypeLabel);
		
		JRadioButton friendlyFarmButton = new JRadioButton("Friendly Farm");
		buttonGroup.add(friendlyFarmButton);
		friendlyFarmButton.setBounds(16, 119, 141, 23);
		frame.getContentPane().add(friendlyFarmButton);
		
		JRadioButton fertileFarmButton = new JRadioButton("Fertile Farm");
		buttonGroup.add(fertileFarmButton);
		fertileFarmButton.setBounds(16, 154, 141, 23);
		frame.getContentPane().add(fertileFarmButton);
		
		JRadioButton fastFarmButton = new JRadioButton("Fast Farm");
		buttonGroup.add(fastFarmButton);
		fastFarmButton.setBounds(289, 119, 141, 23);
		frame.getContentPane().add(fastFarmButton);
		
		JRadioButton richFarmButton = new JRadioButton("Rich Farm");
		buttonGroup.add(richFarmButton);
		richFarmButton.setBounds(289, 154, 141, 23);
		frame.getContentPane().add(richFarmButton);
	}
}
