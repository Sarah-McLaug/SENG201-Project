package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class MainScreen {

	private JFrame screen;
	
	private final int windowHeight = 500;
	private final int windowWidth = 800;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.screen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		screen = new JFrame();
		screen.setResizable(false);
		screen.setBounds(100, 100, windowWidth, windowHeight);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.getContentPane().setLayout(null);
		
		JTabbedPane mainTabs = new JTabbedPane(JTabbedPane.TOP);
		mainTabs.setBounds(6, 42, 788, 436);
		screen.getContentPane().add(mainTabs);
		
		JPanel mainPanel = new JPanel();
		mainTabs.addTab("Overview", null, mainPanel, null);
		mainPanel.setLayout(null);
		
		JButton viewCropAnimalStatusButton = new JButton("View Crops & Animals");
		viewCropAnimalStatusButton.setBounds(180, 31, 200, 60);
		mainPanel.add(viewCropAnimalStatusButton);
		
		JButton viewFarmStatusButton = new JButton("View Farm Funds");
		viewFarmStatusButton.setBounds(420, 31, 200, 60);
		mainPanel.add(viewFarmStatusButton);
		
		JButton nextDayButton = new JButton("Move to Next Day");
		nextDayButton.setBounds(611, 339, 150, 45);
		mainPanel.add(nextDayButton);
		
		JPanel actionsPanel = new JPanel();
		mainTabs.addTab("Actions", null, actionsPanel, null);
		actionsPanel.setLayout(null);
		
		JLabel actionsLeftLabel = new JLabel("You have 2 actions left");
		actionsLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
		actionsLeftLabel.setBounds(0, 6, 768, 16);
		actionsPanel.add(actionsLeftLabel);
		
		JTabbedPane actionTabs = new JTabbedPane(JTabbedPane.BOTTOM);
		actionTabs.setBounds(0, 30, 768, 350);
		actionsPanel.add(actionTabs);
		JPanel tendCropsTab = new JPanel();
		actionTabs.addTab("Tend Crops", null, tendCropsTab, null);
		JPanel feedAnimalsTab = new JPanel();
		actionTabs.addTab("Feed Animals", null, feedAnimalsTab, null);
		JPanel playAnimalsTab = new JPanel();
		actionTabs.addTab("Play with Animals", null, playAnimalsTab, null);
		JPanel harvestCropsTab = new JPanel();
		actionTabs.addTab("Harvest Crops", null, harvestCropsTab, null);
		JPanel tendFarmTab = new JPanel();
		actionTabs.addTab("Tend Farm", null, tendFarmTab, null);
		
		JPanel storePanel = new JPanel();
		mainTabs.addTab("County Store", null, storePanel, null);
		storePanel.setLayout(null);
		
		JTabbedPane storeTabs = new JTabbedPane(JTabbedPane.BOTTOM);
		storeTabs.setBounds(0, 0, 768, 380);
		storePanel.add(storeTabs);
		
		JPanel viewItemsTab = new JPanel();
		storeTabs.addTab("View Your Items", null, viewItemsTab, null);
		
		JPanel buyAnimalsTab = new JPanel();
		storeTabs.addTab("Buy Animals", null, buyAnimalsTab, null);
		
		JPanel buyCropsTab = new JPanel();
		storeTabs.addTab("Buy Crops", null, buyCropsTab, null);
		
		JPanel buyItemsTab = new JPanel();
		storeTabs.addTab("Buy Farming Supplies", null, buyItemsTab, null);
		
		JLabel farmNameLabel = new JLabel("Farm Name");
		farmNameLabel.setBounds(0, 6, 800, 30);
		screen.getContentPane().add(farmNameLabel);
		farmNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		farmNameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		
	}
}
