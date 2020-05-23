package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen {

	private JFrame screen;
	private Game game;
	
	private final int windowHeight = 500;
	private final int windowWidth = 800;
	private JTable tendCropsTable;
	private JTable feedAnimalsTable;
	private JTable harvestableCropsTable;
	private JTable ownedItemsTable;
	private JTable storeAnimalsTable;
	private JTable storeCropsTable;
	private JTable storeItemsTable;
	private JTable tendCropsItemTable;
	private JTable feedAnimalsItemTable;
	private JTable playAnimalsTable;

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
				dummyGame.setActionCount(actionsRemaining);
				dummyGame.populateStore(animalSpecies, cropSpecies, foodItems, cropItems);
				
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
		//set up frame
		screen = new JFrame();
		screen.setResizable(false);
		screen.setBounds(100, 100, windowWidth, windowHeight);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.getContentPane().setLayout(null);
		
		JLabel farmNameLabel = new JLabel(game.getFarm().getName());
		farmNameLabel.setBounds(0, 6, 800, 30);
		screen.getContentPane().add(farmNameLabel);
		farmNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		farmNameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		
		JTabbedPane mainTabs = new JTabbedPane(JTabbedPane.TOP);
		mainTabs.setBounds(0, 40, 800, 436);
		screen.getContentPane().add(mainTabs);
		
		//-------------------------------------------------------------
		//set up overview tab
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
		
		//-------------------------------------------------------------
		//set up actions tab
		JPanel actionsPanel = new JPanel();
		mainTabs.addTab("Actions", null, actionsPanel, null);
		actionsPanel.setLayout(null);
		
		JLabel actionsRemainingLabel = new JLabel("You have " + game.getActionCount() + " actions left");
		actionsRemainingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		actionsRemainingLabel.setBounds(0, 10, 780, 16);
		actionsPanel.add(actionsRemainingLabel);
		
		JTabbedPane actionTabs = new JTabbedPane(JTabbedPane.BOTTOM);
		actionTabs.setBounds(0, 38, 780, 350);
		actionsPanel.add(actionTabs);
		
		JPanel tendCropsTab = new JPanel();
		actionTabs.addTab("Tend Crops", null, tendCropsTab, null);
		tendCropsTab.setLayout(null);
		
		JScrollPane tendCropsTableContainer = new JScrollPane();
		tendCropsTableContainer.setBounds(5, 5, 370, 250);
		tendCropsTab.add(tendCropsTableContainer);
		
		String[] tendCropsColumnNames = {"Species", "Days Until Mature", "Age"};
		Object[][] cropsData = new Object[game.getFarm().getCrops().size()][3];
		for(int i = 0; i < game.getFarm().getCrops().size(); i++) {
			Crop c = game.getFarm().getCrops().get(i);
			Object[] tableRow = {c.getSpecies(), c.getDaysUntilMature(), c.getAge(5)};
			cropsData[i] = tableRow;
		}
		
		tendCropsTable = new JTable(cropsData, tendCropsColumnNames);
		tendCropsTableContainer.setViewportView(tendCropsTable);
		
		JScrollPane tendCropsItemTableContainer = new JScrollPane();
		tendCropsItemTableContainer.setBounds(385, 5, 370, 250);
		tendCropsTab.add(tendCropsItemTableContainer);
		
		String[] cropItemsColumnNames = {"Name", "Growth Enhancement"};
		Object[][] cropItemsData = new Object[game.getFarm().getInventory().size()+1][2];
		Object[] waterRow = {"Water", "1 Day"};
		cropItemsData[0] = waterRow;
		for(int i = 0; i < game.getFarm().getInventory().size(); i++) {
			Item item = game.getFarm().getInventory().get(i);
			if(!item.isFoodItem()) {
				Object[] tableRow = {item.getName(), item.getBenefit()};
				cropItemsData[i+1] = tableRow;
			}
		}
		
		tendCropsItemTable = new JTable(cropItemsData, cropItemsColumnNames);
		tendCropsItemTableContainer.setViewportView(tendCropsItemTable);
		
		JButton tendCropsBtn = new JButton("Tend Crops");
		if(tendCropsTable.getSelectionModel().isSelectionEmpty() || 
				tendCropsItemTable.getSelectionModel().isSelectionEmpty()) tendCropsBtn.setEnabled(false);
		else tendCropsBtn.setEnabled(true);
		tendCropsBtn.setBounds(315, 268, 120, 30);
		tendCropsTab.add(tendCropsBtn);
		
		JLabel tendCropsTableTitle = new JLabel("Crops");
		tendCropsTableTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		tendCropsTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
		tendCropsTableTitle.setBounds(156, 255, 61, 13);
		tendCropsTab.add(tendCropsTableTitle);
		
		JLabel tendCropsItemTableTitle = new JLabel("Inventory");
		tendCropsItemTableTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		tendCropsItemTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
		tendCropsItemTableTitle.setBounds(539, 255, 61, 13);
		tendCropsTab.add(tendCropsItemTableTitle);
		
		JPanel feedAnimalsTab = new JPanel();
		actionTabs.addTab("Feed Animals", null, feedAnimalsTab, null);
		feedAnimalsTab.setLayout(null);
		
		JScrollPane feedAnimalsTableContainer = new JScrollPane();
		feedAnimalsTableContainer.setBounds(5, 5, 370, 250);
		feedAnimalsTab.add(feedAnimalsTableContainer);
		
		String[] feedAnimalsColumnNames = {"Species", "Health"};
		Object[][] feedAnimalsData = new Object[game.getFarm().getAnimals().size()][2];
		for(int i = 0; i < game.getFarm().getAnimals().size(); i++) {
			Animal a = game.getFarm().getAnimals().get(i);
			Object[] tableRow = {a.getSpecies(), a.getHealth() + "/10.0"};
			feedAnimalsData[i] = tableRow;
		}
			
		feedAnimalsTable = new JTable(feedAnimalsData, feedAnimalsColumnNames);
		feedAnimalsTable.setRowSelectionAllowed(false);
		feedAnimalsTableContainer.setViewportView(feedAnimalsTable);
		
		JScrollPane feedAnimalsItemTableContainer = new JScrollPane();
		feedAnimalsItemTableContainer.setBounds(385, 5, 370, 250);
		feedAnimalsTab.add(feedAnimalsItemTableContainer);
		
		String[] feedAnimalsItemColumnNames = {"Name", "Health Points"};
		Object[][] feedAnimalsItemData = new Object[game.getFarm().getInventory().size()][2];
		for(int i = 0; i < game.getFarm().getInventory().size(); i++) {
			Item item = game.getFarm().getInventory().get(i);
			if(item.isFoodItem()) {
				Object[] tableRow = {item.getName(), item.getBenefit()};
				feedAnimalsItemData[i] = tableRow;
			}
		}
		
		feedAnimalsItemTable = new JTable(feedAnimalsItemData, feedAnimalsItemColumnNames);
		feedAnimalsItemTableContainer.setViewportView(feedAnimalsItemTable);
		
		JButton feedAnimalsBtn = new JButton("Feed Animals");
		feedAnimalsBtn.setBounds(315, 268, 120, 30);
		if(feedAnimalsItemTable.getSelectionModel().isSelectionEmpty()) feedAnimalsBtn.setEnabled(false);
		else feedAnimalsBtn.setEnabled(true);
		feedAnimalsTab.add(feedAnimalsBtn);
		
		JPanel playAnimalsTab = new JPanel();
		actionTabs.addTab("Play with Animals", null, playAnimalsTab, null);
		playAnimalsTab.setLayout(null);
		
		JScrollPane playAnimalsTableContainer = new JScrollPane();
		playAnimalsTableContainer.setBounds(5, 5, 500, 292);
		playAnimalsTab.add(playAnimalsTableContainer);
		
		String[] playAnimalsColumnNames = {"Species", "Happiness"};
		Object[][] playAnimalsData = new Object[game.getFarm().getAnimals().size()][2];
		for(int i = 0; i < game.getFarm().getAnimals().size(); i++) {
			Animal a = game.getFarm().getAnimals().get(i);
			Object[] tableRow = {a.getSpecies(), a.getHappiness()};
			playAnimalsData[i] = tableRow;
		}
		
		playAnimalsTable = new JTable(playAnimalsData, playAnimalsColumnNames);
		playAnimalsTable.setRowSelectionAllowed(false);
		playAnimalsTableContainer.setViewportView(playAnimalsTable);
		
		JButton playAnimalsBtn = new JButton("Play with Animals");
		playAnimalsBtn.setBounds(552, 119, 155, 60);
		if(game.getFarm().getAnimals().size() > 0) playAnimalsBtn.setEnabled(true);
		else playAnimalsBtn.setEnabled(false);
		playAnimalsTab.add(playAnimalsBtn);
		
		JPanel harvestCropsTab = new JPanel();
		actionTabs.addTab("Harvest Crops", null, harvestCropsTab, null);
		harvestCropsTab.setLayout(null);
		
		JScrollPane harvestableCropsTableContainer = new JScrollPane();
		harvestableCropsTableContainer.setBounds(5, 5, 500, 292);
		harvestCropsTab.add(harvestableCropsTableContainer);
		
		String[] harvestableCropsColumnNames = {"Species", "Selling Price"};
		Object[][] harvestableCropsData = new Object[game.getFarm().getCrops().size()][2];
		for(int i = 0; i < game.getFarm().getCrops().size(); i++) {
			Crop c = game.getFarm().getCrops().get(i);
			if(c.getDaysUntilMature() == 0) {
				Object[] tableRow = {c.getSpecies(), c.getSellingPrice()};
				harvestableCropsData[i] = tableRow;
			}
		}
		
		harvestableCropsTable = new JTable(harvestableCropsData, harvestableCropsColumnNames);
		harvestableCropsTable.setRowSelectionAllowed(false);
		harvestableCropsTableContainer.setViewportView(harvestableCropsTable);
		
		JButton harvestCropsBtn = new JButton("Harvest Crops");
		harvestCropsBtn.setBounds(552, 119, 155, 60);
		if(game.getFarm().getCrops().size() == 0) harvestCropsBtn.setEnabled(false);
		else harvestCropsBtn.setEnabled(true);
		harvestCropsTab.add(harvestCropsBtn);
		
		JPanel tendFarmTab = new JPanel();
		actionTabs.addTab("Tend Farm", null, tendFarmTab, null);
		tendFarmTab.setLayout(null);
		
		JButton tendFarmBtn = new JButton("Tend the Farm");
		tendFarmBtn.setBounds(312, 120, 134, 60);
		tendFarmTab.add(tendFarmBtn);
		
		//-------------------------------------------------------------
		//set up store tab
		JPanel storePanel = new JPanel();
		mainTabs.addTab("County Store", null, storePanel, null);
		storePanel.setLayout(null);
		
		JLabel farmBalance = new JLabel("Cash Remaining: $" + game.getFarm().getBalance());
		farmBalance.setHorizontalAlignment(SwingConstants.CENTER);
		farmBalance.setBounds(0, 12, 778, 16);
		storePanel.add(farmBalance);
		
		JTabbedPane storeTabs = new JTabbedPane(JTabbedPane.BOTTOM);
		storeTabs.setBounds(0, 30, 768, 360);
		storePanel.add(storeTabs);
		
		JPanel viewItemsTab = new JPanel();
		storeTabs.addTab("View Your Items", null, viewItemsTab, null);
		viewItemsTab.setLayout(null);
		
		JScrollPane ownedItemsTableContainer = new JScrollPane();
		ownedItemsTableContainer.setBounds(146, 5, 454, 292);
		viewItemsTab.add(ownedItemsTableContainer);
		
		String[] ownedItemsColumnNames = {"Name", "Type"};
		Object[][] ownedItemsData = new Object[game.getFarm().getInventory().size()][2];
		for(int i = 0; i < game.getFarm().getInventory().size(); i++) {
			Item item = game.getFarm().getInventory().get(i);
			Object[] tableRow = {item.getName(), null};
			if(item.isFoodItem()) tableRow[1] = "Food Item";
			else tableRow[1] = "Crop Item";
			ownedItemsData[i] = tableRow;
		}
		ownedItemsTable = new JTable(ownedItemsData, ownedItemsColumnNames);
		ownedItemsTableContainer.setViewportView(ownedItemsTable);
		
		JLabel inventoryLabel = new JLabel("Inventory");
		inventoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inventoryLabel.setBounds(341, 306, 61, 16);
		viewItemsTab.add(inventoryLabel);
		
		JPanel buyAnimalsTab = new JPanel();
		storeTabs.addTab("Buy Animals", null, buyAnimalsTab, null);
		buyAnimalsTab.setLayout(null);
		
		JScrollPane storeAnimalsTableContainer = new JScrollPane();
		storeAnimalsTableContainer.setBounds(5, 5, 500, 292);
		buyAnimalsTab.add(storeAnimalsTableContainer);
		
		String[] storeAnimalsColumnNames = {"ID", "Species", "Price"};
		Object[][] storeAnimalsData = new Object[game.getStore().getAnimals().size()][3];
		for(int i = 0; i < game.getStore().getAnimals().size(); i++) {
			Animal a = game.getStore().getAnimals().get(i);
			Object[] tableRow = {a.getId(), a.getSpecies(), a.getPurchasePrice()};
			storeAnimalsData[i] = tableRow;
		}
		storeAnimalsTable = new JTable(storeAnimalsData, storeAnimalsColumnNames);
		storeAnimalsTableContainer.setViewportView(storeAnimalsTable);
		
		JButton buyAnimalsBtn = new JButton("Purchase");
		buyAnimalsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] animals = new Object[storeAnimalsData.length];
				for(int i = 0; i < animals.length; i++) {
					animals[i] = storeAnimalsData[i][0];
				}
				Integer purchase = (Integer) JOptionPane.showInputDialog(screen, "Select the animal you would like to purcase", "Confirmation", JOptionPane.PLAIN_MESSAGE, null, animals, animals[0]);
				if(purchase != null && purchase.intValue() != 0 && game.getStore().animalExists(purchase.intValue()) == true) {
					Animal a = game.getStore().fetchAnimal(purchase.intValue());
					game.getStore().sellAnimal(a.getId());
					game.getFarm().addAnimal(a);
					game.getFarm().updateBalance(-a.getPurchasePrice());
					farmBalance.setText("Cash Remaining: $" + game.getFarm().getBalance());
					//Used to test that user was purchasing animals
					/*String printAnimalsinFarm = ""; 
					for(int i = 0; i < game.getFarm().getAnimals().size(); i++) {
						printAnimalsinFarm += game.getFarm().getAnimals().get(i).getSpecies() + "\n";
					}
					JOptionPane.showMessageDialog(screen, printAnimalsinFarm);*/
				}
			}
		});
		buyAnimalsBtn.setBounds(552, 119, 155, 60);
		if(game.getStore().getAnimals().size() == 0) buyAnimalsBtn.setEnabled(false);
		else buyAnimalsBtn.setEnabled(true);
		buyAnimalsTab.add(buyAnimalsBtn);
		
		JPanel buyCropsTab = new JPanel();
		storeTabs.addTab("Buy Crops", null, buyCropsTab, null);
		buyCropsTab.setLayout(null);
		
		JScrollPane storeCropsTableContainer = new JScrollPane();
		storeCropsTableContainer.setBounds(5, 5, 500, 292);
		buyCropsTab.add(storeCropsTableContainer);
		
		String[] storeCropsColumnNames = {"ID", "Species", "Days Until Mature", "Price"};
		Object[][] storeCropsData = new Object[game.getStore().getCrops().size()][4];
		for(int i = 0; i < game.getStore().getCrops().size(); i++) {
			Crop c = game.getStore().getCrops().get(i);
			Object[] tableRow = {c.getId(), c.getSpecies(), c.getDaysUntilMature(), c.getPurchasePrice()};
			storeCropsData[i] = tableRow;
		}
		storeCropsTable = new JTable(storeCropsData, storeCropsColumnNames);
		storeCropsTableContainer.setViewportView(storeCropsTable);
		
		JButton buyCropsBtn = new JButton("Purchase");
		buyCropsBtn.setBounds(552, 119, 155, 60);
		buyCropsTab.add(buyCropsBtn);
		
		JPanel buyItemsTab = new JPanel();
		storeTabs.addTab("Buy Farming Supplies", null, buyItemsTab, null);
		buyItemsTab.setLayout(null);
		
		JScrollPane storeItemsTableContainer = new JScrollPane();
		storeItemsTableContainer.setBounds(5, 5, 500, 292);
		buyItemsTab.add(storeItemsTableContainer);
		
		String[] storeItemsColumnNames = {"ID", "Name", "Type", "Benefit", "Price"};
		Object[][] storeItemsData = new Object[game.getStore().getItems().size()][5];
		for(int i = 0; i < game.getStore().getItems().size(); i++) {
			Item item = game.getStore().getItems().get(i);
			Object[] tableRow = {item.getId(), item.getName(), null, null, item.getPrice()};
			if(item.isFoodItem()) {
				tableRow[2] = "Food Item";
				tableRow[3] = item.getBenefit() + " Health Points";
			} else {
				tableRow[2] = "Crop item";
				tableRow[3] = item.getBenefit() + " Day Growth Enhancement";
			}
			storeItemsData[i] = tableRow;
		}
		storeItemsTable = new JTable(storeItemsData, storeItemsColumnNames);
		storeItemsTableContainer.setViewportView(storeItemsTable);
		
		JButton buyItemsBtn = new JButton("Purchase");
		buyItemsBtn.setBounds(552, 119, 155, 60);
		buyItemsTab.add(buyItemsBtn);
		
	}
}
