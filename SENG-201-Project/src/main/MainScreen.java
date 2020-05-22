package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainScreen {

	private JFrame screen;
	private Game game;
	
	private final int windowHeight = 500;
	private final int windowWidth = 800;
	private JTable tendCropsTable;
	private JTable animalFoodTable;
	private JTable harvestableCropsTable;
	private JTable ownedItemsTable;
	private JTable storeAnimalsTable;
	private JTable storeCropsTable;
	private JTable storeItemsTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Defaults for easy testing: TODO: (remove later)
				//--------------------------------------------------------------------
				String farmName = "Incognito Farms";
				String farmType = "friendly";
				String farmerName = "Lollipop Chainsaw";
				int age = 22;
				int day = 5;
				//--------------------------------------------------------------------
				
				Farm farm = new Farm(farmName, farmType, new Farmer(farmerName, age));
				Store store = new Store();
				int actionsRemaining = 2;
				boolean playingGame = true;
				
				//Define game elements
				//-------------------------------------------------------------------------
				final ArrayList <Crop> cropSpecies = new ArrayList <Crop>();
				cropSpecies.add(new Crop("banana", 1, 1, 1, 1, farm.getCropGrowthBonus()));
				cropSpecies.add(new Crop("corn", 1, 1, 1, 1, farm.getCropGrowthBonus()));
				cropSpecies.add(new Crop("kiwi", 1, 1, 1, 1, farm.getCropGrowthBonus()));
				cropSpecies.add(new Crop("kumera", 1, 1, 1, 1, farm.getCropGrowthBonus()));
				cropSpecies.add(new Crop("mango", 1, 1, 1, 1, farm.getCropGrowthBonus()));
				cropSpecies.add(new Crop("spinach", 1, 1, 1, 1, farm.getCropGrowthBonus()));
				
				final ArrayList <Animal> animalSpecies = new ArrayList <Animal>();
				animalSpecies.add(new Animal("llama", 1, 1, 1, 1));
				animalSpecies.add(new Animal("koala", 1, 1, 1, 1));
				animalSpecies.add(new Animal("panda", 1, 1, 1, 1));
				
				final ArrayList <Item> foodItems = new ArrayList <Item>();
				foodItems.add(new FoodItem("sugar cane", 1, 1));
				foodItems.add(new FoodItem("eucalyptus leaves", 1, 1));
				foodItems.add(new FoodItem("brocooli", 1, 1));
				
				final ArrayList <Item> cropItems = new ArrayList <Item>();
				cropItems.add(new CropItem("fertilizer", 1, 1));
				cropItems.add(new CropItem("pestiside", 1, 1));
				//-------------------------------------------------------------------------
				

				Game dummyGame = new Game();
				dummyGame.setFarm(farm);
				dummyGame.setDuration(5);
				dummyGame.setActionCount(2);
				
				//Fill the store with the elements
				dummyGame.populateStore(store, animalSpecies, cropSpecies, foodItems, cropItems, day);
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen(dummyGame);
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
	public MainScreen(Game incomingGame) {
		game = incomingGame;
		initialize();
		screen.setVisible(true);
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
		mainTabs.setBounds(0, 40, 800, 436);
		screen.getContentPane().add(mainTabs);
		
		JPanel mainPanel = new JPanel();
		mainTabs.addTab("Overview", null, mainPanel, null);
		mainPanel.setLayout(null);
		
		JButton viewCropAnimalStatusButton = new JButton("View Crops & Animals");
		viewCropAnimalStatusButton.setBounds(170, 31, 200, 60);
		mainPanel.add(viewCropAnimalStatusButton);
		
		JButton viewFarmStatusButton = new JButton("View Farm Funds");
		viewFarmStatusButton.setBounds(410, 31, 200, 60);
		mainPanel.add(viewFarmStatusButton);
		
		JButton nextDayButton = new JButton("Move to Next Day");
		nextDayButton.setBounds(612, 330, 150, 45);
		mainPanel.add(nextDayButton);
		
		JPanel actionsPanel = new JPanel();
		mainTabs.addTab("Actions", null, actionsPanel, null);
		actionsPanel.setLayout(null);
		
		JLabel actionsRemainingLabel = new JLabel("You have 2 actions left");
		actionsRemainingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		actionsRemainingLabel.setBounds(0, 10, 780, 16);
		actionsPanel.add(actionsRemainingLabel);
		
		JTabbedPane actionTabs = new JTabbedPane(JTabbedPane.BOTTOM);
		actionTabs.setBounds(0, 30, 780, 350);
		actionsPanel.add(actionTabs);
		JPanel tendCropsTab = new JPanel();
		actionTabs.addTab("Tend Crops", null, tendCropsTab, null);
		
		JScrollPane tendCropsTableContainer = new JScrollPane();
		tendCropsTab.add(tendCropsTableContainer);
		
		tendCropsTable = new JTable();
		tendCropsTableContainer.setViewportView(tendCropsTable);
		JPanel feedAnimalsTab = new JPanel();
		actionTabs.addTab("Feed Animals", null, feedAnimalsTab, null);
		
		JScrollPane animalFoodTableContainer = new JScrollPane();
		feedAnimalsTab.add(animalFoodTableContainer);
		
		animalFoodTable = new JTable();
		animalFoodTableContainer.setViewportView(animalFoodTable);
		
		JButton feedAnimalsBtn = new JButton("Feed Animals");
		feedAnimalsBtn.setEnabled(false);
		feedAnimalsTab.add(feedAnimalsBtn);
		JPanel playAnimalsTab = new JPanel();
		actionTabs.addTab("Play with Animals", null, playAnimalsTab, null);
		
		JButton playAnimalsBtn = new JButton("Play with Animals");
		playAnimalsTab.add(playAnimalsBtn);
		JPanel harvestCropsTab = new JPanel();
		actionTabs.addTab("Harvest Crops", null, harvestCropsTab, null);
		
		JScrollPane harvestableCropsTableContainer = new JScrollPane();
		harvestCropsTab.add(harvestableCropsTableContainer);
		
		harvestableCropsTable = new JTable();
		harvestableCropsTable.setRowSelectionAllowed(false);
		harvestableCropsTableContainer.setViewportView(harvestableCropsTable);
		
		JButton harvestCropsBtn = new JButton("Harvest Crops");
		harvestCropsTab.add(harvestCropsBtn);
		JPanel tendFarmTab = new JPanel();
		actionTabs.addTab("Tend Farm", null, tendFarmTab, null);
		
		JButton tendFarmBtn = new JButton("Tend the Farm");
		tendFarmTab.add(tendFarmBtn);
		
		JPanel storePanel = new JPanel();
		mainTabs.addTab("County Store", null, storePanel, null);
		storePanel.setLayout(null);
		
		JTabbedPane storeTabs = new JTabbedPane(JTabbedPane.BOTTOM);
		storeTabs.setBounds(0, 0, 768, 380);
		storePanel.add(storeTabs);
		
		JPanel viewItemsTab = new JPanel();
		storeTabs.addTab("View Your Items", null, viewItemsTab, null);
		
		JScrollPane ownedItemsTableContainer = new JScrollPane();
		viewItemsTab.add(ownedItemsTableContainer);
		
		ownedItemsTable = new JTable();
		ownedItemsTableContainer.setViewportView(ownedItemsTable);
		
		JPanel buyAnimalsTab = new JPanel();
		storeTabs.addTab("Buy Animals", null, buyAnimalsTab, null);
		
		JScrollPane storeAnimalsTableContainer = new JScrollPane();
		buyAnimalsTab.add(storeAnimalsTableContainer);
		
		storeAnimalsTable = new JTable();
		storeAnimalsTableContainer.setViewportView(storeAnimalsTable);
		
		JPanel buyCropsTab = new JPanel();
		storeTabs.addTab("Buy Crops", null, buyCropsTab, null);
		
		JScrollPane storeCropsTableContainer = new JScrollPane();
		buyCropsTab.add(storeCropsTableContainer);
		
		storeCropsTable = new JTable();
		storeCropsTableContainer.setViewportView(storeCropsTable);
		
		JPanel buyItemsTab = new JPanel();
		storeTabs.addTab("Buy Farming Supplies", null, buyItemsTab, null);
		
		JScrollPane storeItemsTableContainer = new JScrollPane();
		buyItemsTab.add(storeItemsTableContainer);
		
		storeItemsTable = new JTable();
		storeItemsTableContainer.setViewportView(storeItemsTable);
		
		JLabel farmNameLabel = new JLabel(game.getFarm().getName());
		farmNameLabel.setBounds(0, 6, 800, 30);
		screen.getContentPane().add(farmNameLabel);
		farmNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		farmNameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		
	}
}
