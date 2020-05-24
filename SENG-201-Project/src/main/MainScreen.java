package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
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
	private JButton tendCropsBtn;
	private JButton feedAnimalsBtn;
	private JButton playAnimalsBtn;
	private JButton harvestCropsBtn;
	private JButton tendFarmBtn;
	private JLabel farmBalance;
	private JLabel viewfarmFundsLabel;
	private JLabel actionsRemainingLabel;
	private JTable viewFarmCropsTable;
	private JTable viewFarmAnimalsTable;

	/**
	 * Disable all of the buttons the user uses to take an action
	 */
	public void disableActions() {
		tendCropsBtn.setEnabled(false);
		feedAnimalsBtn.setEnabled(false);
		playAnimalsBtn.setEnabled(false);
		harvestCropsBtn.setEnabled(false);
		tendFarmBtn.setEnabled(false);
	}
	
	/**
	 * Make a confirmation popup for when a user takes an action
	 * @return true if the user presses ok, false if cancel
	 */
	public boolean actionConfirmation() {
		Integer input = JOptionPane.showConfirmDialog(screen, "This will take one action. \n Are you sure?", "Confirmation", 
				JOptionPane.OK_CANCEL_OPTION);
		if(input == JOptionPane.OK_OPTION) {
			game.setActionCount(game.getActionCount() - 1);
			actionsRemainingLabel.setText("Actions Remaining: " + game.getActionCount());
			if(game.getActionCount() == 0) disableActions();
			return true;
		}
		return false;
	}

	/**
	 * Create the window
	 * @param incomingGame - state of the game when the window opens
	 */
	public MainScreen(Game incomingGame) {
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
	 * Tells the game the window is ready to be closed
	 */
	public void finishedWindow() {
		game.closeMainScreen(this);
	}

	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		//set up data models
		//set up farm animal table model
		String[] farmAnimalsColumnNames = {"Species", "Health", "Happiness"};
		DefaultTableModel farmAnimalsModel = new DefaultTableModel(0, 0)
		{
		    public boolean isCellEditable(int row, int column)
		    {
		      return false; //This causes all cells to be not editable
		    }
		};
		
		farmAnimalsModel.setColumnIdentifiers(farmAnimalsColumnNames);
		for(int i = 0; i < game.getFarm().getAnimals().size(); i++) {
			Animal a = game.getFarm().getAnimals().get(i);
			farmAnimalsModel.addRow(new Object[]{a.getSpecies(), a.getHealth(), a.getHappiness()});
		}
		
		//set up farm crop table model
		String[] farmCropsColumnNames = {"Species", "Days Until Mature", "Age"};
		DefaultTableModel farmCropsModel = new DefaultTableModel(0, 0)
		{
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		farmCropsModel.setColumnIdentifiers(farmCropsColumnNames);
		for(int i = 0; i < game.getFarm().getCrops().size(); i++) {
			Crop c = game.getFarm().getCrops().get(i);
			farmCropsModel.addRow(new Object[] {c.getSpecies(), c.getDaysUntilMature(), c.getAge(game.getDay())});
		}
		
		//set up farm harvestable crops table model
		String[] harvestableCropsColumnNames = {"Species: ", "Selling Price"};
		DefaultTableModel harvestableCropsModel = new DefaultTableModel(0, 0)
		{
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		harvestableCropsModel.setColumnIdentifiers(harvestableCropsColumnNames);
		for(int i = 0; i < game.getFarm().getCrops().size(); i++) {
			Crop c = game.getFarm().getCrops().get(i);
			if(c.getDaysUntilMature() == 0) harvestableCropsModel.addRow(new Object[] {c.getSpecies(), c.getSellingPrice()});
		}
		
		//set up farm item table model
		String[] farmItemsColumnNames = {"Name", "Type", "Benefit"};
		DefaultTableModel farmItemsModel = new DefaultTableModel(0, 0)
		{
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		farmItemsModel.setColumnIdentifiers(farmItemsColumnNames);
		for(int i = 0; i < game.getFarm().getInventory().size(); i++) {
			Item item = game.getFarm().getInventory().get(i);
			if(item.isFoodItem()) farmItemsModel.addRow(new Object[] {item.getName(), "Food Item", item.getBenefit() + " Health Points"});
			else {
				String enhancement = "";
				if(item.getBenefit() == 1) enhancement += item.getBenefit() + " Day Growth Enhancement";
				else enhancement += item.getBenefit() + " Days Growth Enhancement";
				farmItemsModel.addRow(new Object[] {item.getName(), "Crop Item", enhancement});
			}
		}
		
		//set up farm food item table model
		String[] farmFoodItemsColumnNames = {"ID", "Name", "Health Points"};
		DefaultTableModel farmFoodItemsModel = new DefaultTableModel(0, 0)
		{
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		farmFoodItemsModel.setColumnIdentifiers(farmFoodItemsColumnNames);
		for(int i = 0; i < game.getFarm().getInventory().size(); i++) {
			Item item = game.getFarm().getInventory().get(i);
			if(item.isFoodItem()) {
				farmFoodItemsModel.addRow(new Object[] {item.getId(), item.getName(), item.getBenefit()});
			}
		}
		
		//set up farm crop item table model
		String[] farmCropItemsColumnNames = {"ID", "Name","Growth Enhancement"};
		DefaultTableModel farmCropItemsModel = new DefaultTableModel(0, 0)
		{
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		farmCropItemsModel.setColumnIdentifiers(farmCropItemsColumnNames);
		farmCropItemsModel.addRow(new Object[] {1, "water", "1 Day"});
		for(int i = 0; i < game.getFarm().getInventory().size(); i++) {
			Item item = game.getFarm().getInventory().get(i);
			if(!item.isFoodItem()) {
				if(item.getBenefit() == 1)  farmItemsModel.addRow(new Object[] {item.getId(), item.getName(), item.getBenefit() + " Day"});
				else farmItemsModel.addRow(new Object[] {item.getId(), item.getName(), item.getBenefit() + " Days"});
			}
		}
		
		//set up store animals table model
		String[] storeAnimalsColumnNames = {"ID", "Species", "Price"};
		DefaultTableModel storeAnimalsModel = new DefaultTableModel(0, 0)
		{
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		storeAnimalsModel.setColumnIdentifiers(storeAnimalsColumnNames);
		for(int i = 0; i < game.getStore().getAnimals().size(); i++) {
			Animal a = game.getStore().getAnimals().get(i);
			storeAnimalsModel.addRow(new Object[] {a.getId(), a.getSpecies(), a.getPurchasePrice()});
		}
		
		//set up store crops table model
		String[] storeCropsColumnNames = {"ID", "Species", "Days Until Mature", "Price"};
		DefaultTableModel storeCropsModel = new DefaultTableModel(0, 0)
		{
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		storeCropsModel.setColumnIdentifiers(storeCropsColumnNames);
		for(int i = 0; i < game.getStore().getCrops().size(); i++) {
			Crop c = game.getStore().getCrops().get(i);
			storeCropsModel.addRow(new Object[] {c.getId(), c.getSpecies(), c.getDaysUntilMature(), c.getPurchasePrice()});
		}
		
		//set up store items table model
		String[] storeItemsColumnNames = {"ID", "Name", "Type", "Benefit", "Price"};
		DefaultTableModel storeItemsModel = new DefaultTableModel(0, 0)
		{
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		};
		storeItemsModel.setColumnIdentifiers(storeItemsColumnNames);
		for(int i = 0; i < game.getStore().getItems().size(); i++) {
			Item item = game.getStore().getItems().get(i);
			Object[] tableRow = new Object[] {item.getId(), item.getName(), null, null, item.getPrice()};
			if(item.isFoodItem()) {
				tableRow[2] = "Food Item";
				tableRow[3] = item.getBenefit() + " Health Points";
			}
			else {
				tableRow[2] = "Crop Item";
				if(item.getBenefit() == 1) tableRow[3] = item.getBenefit() + " Day Growth Enhancement";
				else tableRow[3] = item.getBenefit() + " Days Growth Enhancement";
			}
			storeItemsModel.addRow(tableRow);
		}
		
		//-------------------------------------------------------------
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
		
		JButton nextDayButton = new JButton("Move to Next Day");
		JButton endGameButton = new JButton("End Game");
		endGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.advanceDay();
				JOptionPane.showMessageDialog(screen, "You have " + game.getDay() + " days left!", "Notification", JOptionPane.ERROR_MESSAGE);
				if(game.getDay() - 1 == 0) {
					nextDayButton.setVisible(false);
					nextDayButton.setEnabled(false);
					endGameButton.setVisible(true);
					endGameButton.setEnabled(true);
				}
				//update table models
				farmAnimalsModel.setRowCount(0);
				for(int i = 0; i < game.getFarm().getAnimals().size(); i++) {
					Animal a = game.getFarm().getAnimals().get(i);
					farmAnimalsModel.addRow(new Object[]{a.getSpecies(), a.getHealth(), a.getHappiness()});
				}
				farmCropsModel.setRowCount(0);
				for(int i = 0; i < game.getFarm().getCrops().size(); i++) {
					Crop c = game.getFarm().getCrops().get(i);
					farmCropsModel.addRow(new Object[] {c.getSpecies(), c.getDaysUntilMature(), c.getAge(game.getDay())});
				}
				harvestableCropsModel.setRowCount(0);
				for(int i = 0; i < game.getFarm().getCrops().size(); i++) {
					Crop c = game.getFarm().getCrops().get(i);
					if(c.getDaysUntilMature() == 0) harvestableCropsModel.addRow(new Object[] {c.getSpecies(), c.getSellingPrice()});
				}
				farmItemsModel.setRowCount(0);
				for(int i = 0; i < game.getFarm().getInventory().size(); i++) {
					Item item = game.getFarm().getInventory().get(i);
					if(item.isFoodItem()) farmItemsModel.addRow(new Object[] {item.getName(), "Food Item", item.getBenefit() + " Health Points"});
					else {
						String enhancement = "";
						if(item.getBenefit() == 1) enhancement += item.getBenefit() + " Day Growth Enhancement";
						else enhancement += item.getBenefit() + " Days Growth Enhancement";
						farmItemsModel.addRow(new Object[] {item.getName(), "Crop Item", enhancement});
					}
				}
				farmFoodItemsModel.setRowCount(0);
				for(int i = 0; i < game.getFarm().getInventory().size(); i++) {
					Item item = game.getFarm().getInventory().get(i);
					if(item.isFoodItem()) {
						farmFoodItemsModel.addRow(new Object[] {item.getId(), item.getName(), item.getBenefit()});
					}
				}
				farmCropItemsModel.setRowCount(0);
				farmCropItemsModel.addRow(new Object[] {1, "Water", "1 Day"});
				for(int i = 0; i < game.getFarm().getInventory().size(); i++) {
					Item item = game.getFarm().getInventory().get(i);
					if(!item.isFoodItem()) {
						if(item.getBenefit() == 1)  farmItemsModel.addRow(new Object[] {item.getId(), item.getName(), item.getBenefit() + " Day"});
						else farmItemsModel.addRow(new Object[] {item.getId(), item.getName(), item.getBenefit() + " Days"});
					}
				}
				storeAnimalsModel.setRowCount(0);
				for(int i = 0; i < game.getStore().getAnimals().size(); i++) {
					Animal a = game.getStore().getAnimals().get(i);
					storeAnimalsModel.addRow(new Object[] {a.getId(), a.getSpecies(), a.getPurchasePrice()});
				}
				storeCropsModel.setRowCount(0);
				for(int i = 0; i < game.getStore().getCrops().size(); i++) {
					Crop c = game.getStore().getCrops().get(i);
					storeCropsModel.addRow(new Object[] {c.getId(), c.getSpecies(), c.getDaysUntilMature(), c.getPurchasePrice()});
				}
				storeItemsModel.setRowCount(0);
				for(int i = 0; i < game.getStore().getItems().size(); i++) {
					Item item = game.getStore().getItems().get(i);
					Object[] tableRow = new Object[] {item.getId(), item.getName(), null, null, item.getPrice()};
					if(item.isFoodItem()) {
						tableRow[2] = "Food Item";
						tableRow[3] = item.getBenefit() + " Health Points";
					}
					else {
						tableRow[2] = "Crop Item";
						if(item.getBenefit() == 1) tableRow[3] = item.getBenefit() + " Day Growth Enhancement";
						else tableRow[3] = item.getBenefit() + " Days Growth Enhancement";
					}
					storeItemsModel.addRow(tableRow);
				}
				//update action button enabled
				if(farmCropsModel.getRowCount() > 0) tendCropsBtn.setEnabled(true);
				if(farmAnimalsModel.getRowCount() > 0 && farmFoodItemsModel.getRowCount() > 0) feedAnimalsBtn.setEnabled(true);
				if(farmAnimalsModel.getRowCount() > 0) playAnimalsBtn.setEnabled(true);
				if(harvestableCropsModel.getRowCount() > 0) harvestCropsBtn.setEnabled(true);
				tendFarmBtn.setEnabled(true);
				actionsRemainingLabel.setText("Actions Remaining: " + game.getActionCount());
				farmBalance.setText("Cash Remaining: $" + String.format("%.2f", game.getFarm().getBalance()));
				viewfarmFundsLabel.setText("Farm Funds: $" + String.format("%.2f", game.getFarm().getBalance()));
			}
		});
		nextDayButton.setBounds(617, 335, 150, 45);
		endGameButton.setBounds(617, 335, 150, 45);
		endGameButton.setVisible(false);
		endGameButton.setEnabled(false);
		mainPanel.add(nextDayButton);
		mainPanel.add(endGameButton);
		
		viewfarmFundsLabel = new JLabel("Farm Funds: $" + String.format("%.2f", game.getFarm().getBalance()));
		viewfarmFundsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		viewfarmFundsLabel.setBounds(0, 6, 780, 16);
		mainPanel.add(viewfarmFundsLabel);
		
		JScrollPane viewFarmCropsTableContainer = new JScrollPane();
		viewFarmCropsTableContainer.setBounds(0, 30, 380, 280);
		mainPanel.add(viewFarmCropsTableContainer);
		
		viewFarmCropsTable = new JTable();
		viewFarmCropsTable.setModel(farmCropsModel);
		viewFarmCropsTableContainer.setViewportView(viewFarmCropsTable);
		
		JScrollPane viewFarmAnimalsTableContainer = new JScrollPane();
		viewFarmAnimalsTableContainer.setBounds(400, 30, 380, 280);
		mainPanel.add(viewFarmAnimalsTableContainer);
		
		viewFarmAnimalsTable = new JTable();
		viewFarmAnimalsTable.setModel(farmAnimalsModel);
		viewFarmAnimalsTableContainer.setViewportView(viewFarmAnimalsTable);
		
		JLabel viewFarmAnimalsTableTitle = new JLabel("Animals");
		viewFarmAnimalsTableTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		viewFarmAnimalsTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
		viewFarmAnimalsTableTitle.setBounds(400, 310, 380, 16);
		mainPanel.add(viewFarmAnimalsTableTitle);
		
		JLabel viewFarmCropsTableTitle = new JLabel("Crops");
		viewFarmCropsTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
		viewFarmCropsTableTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		viewFarmCropsTableTitle.setBounds(0, 310, 380, 16);
		mainPanel.add(viewFarmCropsTableTitle);
		
		//-------------------------------------------------------------
		//set up actions tab
		JPanel actionsPanel = new JPanel();
		mainTabs.addTab("Actions", null, actionsPanel, null);
		actionsPanel.setLayout(null);
		
		actionsRemainingLabel = new JLabel("Actions Remaining: " + game.getActionCount());
		actionsRemainingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		actionsRemainingLabel.setBounds(0, 10, 780, 16);
		actionsPanel.add(actionsRemainingLabel);
		
		JTabbedPane actionTabs = new JTabbedPane(JTabbedPane.BOTTOM);
		actionTabs.setBounds(0, 38, 780, 350);
		actionsPanel.add(actionTabs);
		
		//set up specific actions
		//tend crops
		JPanel tendCropsTab = new JPanel();
		actionTabs.addTab("Tend Crops", null, tendCropsTab, null);
		tendCropsTab.setLayout(null);
		
		JScrollPane tendCropsTableContainer = new JScrollPane();
		tendCropsTableContainer.setBounds(5, 5, 370, 250);
		tendCropsTab.add(tendCropsTableContainer);
		
		tendCropsTable = new JTable();
		tendCropsTable.setModel(farmCropsModel);
		tendCropsTableContainer.setViewportView(tendCropsTable);
		
		JScrollPane tendCropsItemTableContainer = new JScrollPane();
		tendCropsItemTableContainer.setBounds(385, 5, 370, 250);
		tendCropsTab.add(tendCropsItemTableContainer);
		
		tendCropsItemTable = new JTable();
		tendCropsItemTable.setModel(farmCropItemsModel);
		tendCropsItemTableContainer.setViewportView(tendCropsItemTable);
		
		tendCropsBtn = new JButton("Tend Crops");
		tendCropsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] cropItemIds = new Object[farmCropItemsModel.getRowCount()];
				for(int i = 0; i < cropItemIds.length; i++) cropItemIds[i] = farmCropItemsModel.getValueAt(i, 0);
				ArrayList<String> listCropSpeciesOwned = new ArrayList<String>();
				for(int i = 0; i < game.getCropSpecies().size(); i++) {
					if(game.getFarm().ownsCrop(game.getCropSpecies().get(i).getSpecies())) listCropSpeciesOwned.add(game.getCropSpecies().get(i).getSpecies());
				}
				Object[] cropSpeciesOwned = new Object[listCropSpeciesOwned.size()];
				for(int i = 0; i < cropSpeciesOwned.length; i++) {
					cropSpeciesOwned[i] = listCropSpeciesOwned.get(i);
				}
				String speciesSelection = (String) JOptionPane.showInputDialog(screen, "Select the species you want to tend", "Selection",
						JOptionPane.PLAIN_MESSAGE, null, cropSpeciesOwned, cropSpeciesOwned[0]);
				if(speciesSelection != null) {
					Integer cropItemSelection = (Integer) JOptionPane.showInputDialog(screen, "Select the crop item you would like to use", "Selection", 
							JOptionPane.PLAIN_MESSAGE, null, cropItemIds, cropItemIds[0]);
					if(cropItemSelection != null && cropItemSelection.intValue() != 0) {
						Item item = game.getFarm().fetchItem(cropItemSelection.intValue());
						if(cropItemSelection.intValue() == 1) game.getFarm().tendCrop(speciesSelection);
						else {
							game.getFarm().tendCrop(speciesSelection, (CropItem) item);
							int index = -1;
							for(int i = 0; i < farmCropItemsModel.getRowCount(); i++) {
								if((int) farmCropItemsModel.getValueAt(i, 0) == item.getId()) index = i;
							}
							farmCropItemsModel.removeRow(index);
						}
						farmCropsModel.setRowCount(0);
						harvestableCropsModel.setRowCount(0);
						for(int i = 0; i < game.getFarm().getCrops().size(); i++) {
							Crop c = game.getFarm().getCrops().get(i);
							farmCropsModel.addRow(new Object[]{c.getSpecies(), c.getDaysUntilMature(), c.getAge(game.getDay())});
							if(c.getDaysUntilMature() == 0) harvestableCropsModel.addRow(new Object[] {c.getSpecies(), c.getSellingPrice()});
						}
						game.setActionCount(game.getActionCount() - 1);
						actionsRemainingLabel.setText("Actions Remaining: " + game.getActionCount());
						if(game.getActionCount() == 0) disableActions();
					}
				}
			}
		});
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
		
		//feed animals
		JPanel feedAnimalsTab = new JPanel();
		actionTabs.addTab("Feed Animals", null, feedAnimalsTab, null);
		feedAnimalsTab.setLayout(null);
		
		JScrollPane feedAnimalsTableContainer = new JScrollPane();
		feedAnimalsTableContainer.setBounds(5, 5, 370, 250);
		feedAnimalsTab.add(feedAnimalsTableContainer);
			
		feedAnimalsTable = new JTable();
		feedAnimalsTable.setModel(farmAnimalsModel);
		feedAnimalsTable.setRowSelectionAllowed(false);
		feedAnimalsTableContainer.setViewportView(feedAnimalsTable);
		
		JScrollPane feedAnimalsItemTableContainer = new JScrollPane();
		feedAnimalsItemTableContainer.setBounds(385, 5, 370, 250);
		feedAnimalsTab.add(feedAnimalsItemTableContainer);
		
		feedAnimalsItemTable = new JTable();
		feedAnimalsItemTable.setModel(farmFoodItemsModel);
		feedAnimalsTable.setRowSelectionAllowed(false);
		feedAnimalsItemTableContainer.setViewportView(feedAnimalsItemTable);
		
		feedAnimalsBtn = new JButton("Feed Animals");
		feedAnimalsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] foodItemIds = new Object[farmFoodItemsModel.getRowCount()];
				for(int i = 0; i < foodItemIds.length; i++) foodItemIds[i] = farmFoodItemsModel.getValueAt(i, 0);
				Integer selection = (Integer) JOptionPane.showInputDialog(screen, "Select the food item you would like to use", "Selection", 
						JOptionPane.PLAIN_MESSAGE, null, foodItemIds, foodItemIds[0]);
				if(selection != null && selection.intValue() != 0) {
					Item item = game.getFarm().fetchItem(selection.intValue());
					game.getFarm().feedAnimals((FoodItem) item);
					int index = -1;
					for(int i = 0; i < farmFoodItemsModel.getRowCount(); i++) {
						if((int) farmFoodItemsModel.getValueAt(i, 0) == item.getId()) index = i;
					}
					farmFoodItemsModel.removeRow(index);
					farmAnimalsModel.setRowCount(0);
					for(int i = 0; i < game.getFarm().getAnimals().size(); i++) {
						Animal a = game.getFarm().getAnimals().get(i);
						farmAnimalsModel.addRow(new Object[]{a.getSpecies(), a.getHealth(), a.getHappiness()});
					}
					game.setActionCount(game.getActionCount() - 1);
					actionsRemainingLabel.setText("Actions Remaining: " + game.getActionCount());
					if(game.getActionCount() == 0) disableActions();
					if(farmFoodItemsModel.getRowCount() == 0) feedAnimalsBtn.setEnabled(false);
				}
			}
		});
		feedAnimalsBtn.setBounds(315, 268, 120, 30);
		if(feedAnimalsItemTable.getSelectionModel().isSelectionEmpty()) feedAnimalsBtn.setEnabled(false);
		else feedAnimalsBtn.setEnabled(true);
		feedAnimalsTab.add(feedAnimalsBtn);
		
		JLabel feedAnimalsTableTitle = new JLabel("Animals");
		feedAnimalsTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
		feedAnimalsTableTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		feedAnimalsTableTitle.setBounds(5, 255, 370, 13);
		feedAnimalsTab.add(feedAnimalsTableTitle);
		
		JLabel feedAnimalsItemTableTitle = new JLabel("Inventory");
		feedAnimalsItemTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
		feedAnimalsItemTableTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		feedAnimalsItemTableTitle.setBounds(385, 255, 370, 13);
		feedAnimalsTab.add(feedAnimalsItemTableTitle);
		
		JPanel playAnimalsTab = new JPanel();
		actionTabs.addTab("Play with Animals", null, playAnimalsTab, null);
		playAnimalsTab.setLayout(null);
		
		JScrollPane playAnimalsTableContainer = new JScrollPane();
		playAnimalsTableContainer.setBounds(5, 5, 500, 292);
		playAnimalsTab.add(playAnimalsTableContainer);
		
		playAnimalsTable = new JTable();
		playAnimalsTable.setModel(farmAnimalsModel);
		playAnimalsTable.setRowSelectionAllowed(false);
		playAnimalsTableContainer.setViewportView(playAnimalsTable);
		
		playAnimalsBtn = new JButton("Play with Animals");
		playAnimalsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(actionConfirmation()) {
					game.getFarm().playAnimals();
					farmAnimalsModel.setRowCount(0);
					for(int i = 0; i < game.getFarm().getAnimals().size(); i++) {
						Animal a = game.getFarm().getAnimals().get(i);
						farmAnimalsModel.addRow(new Object[]{a.getSpecies(), a.getHealth(), a.getHappiness()});
					}
				}
			}
		});
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
		
		harvestableCropsTable = new JTable();
		harvestableCropsTable.setModel(harvestableCropsModel);
		harvestableCropsTable.setRowSelectionAllowed(false);
		harvestableCropsTableContainer.setViewportView(harvestableCropsTable);
		
		harvestCropsBtn = new JButton("Harvest Crops");
		harvestCropsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(actionConfirmation()) {
					game.getFarm().harvestCrop();
					farmBalance.setText("Cash Remaining: $" + String.format("%.2f", game.getFarm().getBalance()));
					viewfarmFundsLabel.setText("Farm Funds: $" + String.format("%.2f", game.getFarm().getBalance()));
					harvestableCropsModel.setRowCount(0);
					for(int i = 0; i < farmCropsModel.getRowCount(); i++) {
						if((int) farmCropsModel.getValueAt(i, 1) == 0) { 
							farmCropsModel.removeRow(i);
							i--;
						}
						
					}
				}
				
			}
		});
		harvestCropsBtn.setBounds(552, 119, 155, 60);
		if(game.getFarm().getCrops().size() == 0) harvestCropsBtn.setEnabled(false);
		else harvestCropsBtn.setEnabled(true);
		harvestCropsTab.add(harvestCropsBtn);
		
		JPanel tendFarmTab = new JPanel();
		actionTabs.addTab("Tend Farm", null, tendFarmTab, null);
		tendFarmTab.setLayout(null);
		
		tendFarmBtn = new JButton("Tend the Farm");
		tendFarmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(actionConfirmation()) {
					game.getFarm().tendFarm();
				}
			}
		});
		tendFarmBtn.setBounds(312, 120, 134, 60);
		tendFarmTab.add(tendFarmBtn);
		
		//-------------------------------------------------------------
		//set up store tab
		JPanel storePanel = new JPanel();
		mainTabs.addTab("County Store", null, storePanel, null);
		storePanel.setLayout(null);
		
		farmBalance = new JLabel("Cash Remaining: $" + String.format("%.2f", game.getFarm().getBalance()));
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
		ownedItemsTableContainer.setBounds(146, 5, 454, 270);
		viewItemsTab.add(ownedItemsTableContainer);
		
		ownedItemsTable = new JTable();
		ownedItemsTable.setModel(farmItemsModel);
		ownedItemsTableContainer.setViewportView(ownedItemsTable);
		
		JLabel inventoryLabel = new JLabel("Inventory");
		inventoryLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		inventoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inventoryLabel.setBounds(146, 275, 454, 13);
		viewItemsTab.add(inventoryLabel);
		
		JPanel buyAnimalsTab = new JPanel();
		storeTabs.addTab("Buy Animals", null, buyAnimalsTab, null);
		buyAnimalsTab.setLayout(null);
		
		JScrollPane storeAnimalsTableContainer = new JScrollPane();
		storeAnimalsTableContainer.setBounds(5, 5, 500, 270);
		buyAnimalsTab.add(storeAnimalsTableContainer);
		
		//set up table of animals user is able to buy
		storeAnimalsTable = new JTable();
		storeAnimalsTable.setModel(storeAnimalsModel);
		storeAnimalsTableContainer.setViewportView(storeAnimalsTable);
		
		JButton buyAnimalsBtn = new JButton("Purchase");
		buyAnimalsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] animalIds = new Object[storeAnimalsModel.getRowCount()];
				for(int i = 0; i < animalIds.length; i++) animalIds[i] = storeAnimalsModel.getValueAt(i, 0);
				Integer purchase = (Integer) JOptionPane.showInputDialog(screen, "Select the animal you would like to purchase", "Selection", 
						JOptionPane.PLAIN_MESSAGE, null, animalIds, animalIds[0]);
				if(purchase != null && purchase.intValue() != 0 && game.getStore().animalExists(purchase.intValue())) {
					Animal a = game.getStore().fetchAnimal(purchase.intValue());
					int index = -1;
					for(int i = 0; i < storeAnimalsModel.getRowCount(); i++) {
						if((int) storeAnimalsModel.getValueAt(i, 0) == a.getId()) index = i;
					}
					storeAnimalsModel.removeRow(index);
					game.getStore().sellAnimal(a.getId());
					game.getFarm().addAnimal(a);
					game.getFarm().updateBalance(-a.getPurchasePrice());
					farmBalance.setText("Cash Remaining: $" + String.format("%.2f", game.getFarm().getBalance()));
					viewfarmFundsLabel.setText("Farm Funds: $" + String.format("%.2f", game.getFarm().getBalance()));
					farmAnimalsModel.addRow(new Object[]{a.getSpecies(), a.getHealth(), a.getHappiness()});
					if(farmFoodItemsModel.getRowCount() > 0 && game.getActionCount() > 0) feedAnimalsBtn.setEnabled(true);
					if(game.getActionCount() > 0) playAnimalsBtn.setEnabled(true);
					if(game.getStore().getAnimals().size() == 0) buyAnimalsBtn.setEnabled(false);
				}
			}
		});
		buyAnimalsBtn.setBounds(552, 119, 155, 60);
		if(game.getStore().getAnimals().size() == 0) buyAnimalsBtn.setEnabled(false);
		else buyAnimalsBtn.setEnabled(true);
		buyAnimalsTab.add(buyAnimalsBtn);
		
		JLabel storeAnimalsTableTitle = new JLabel("Store Animals");
		storeAnimalsTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
		storeAnimalsTableTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		storeAnimalsTableTitle.setBounds(5, 275, 500, 13);
		buyAnimalsTab.add(storeAnimalsTableTitle);
		
		JPanel buyCropsTab = new JPanel();
		storeTabs.addTab("Buy Crops", null, buyCropsTab, null);
		buyCropsTab.setLayout(null);
		
		JScrollPane storeCropsTableContainer = new JScrollPane();
		storeCropsTableContainer.setBounds(5, 5, 500, 270);
		buyCropsTab.add(storeCropsTableContainer);
		
		storeCropsTable = new JTable();
		storeCropsTable.setModel(storeCropsModel);
		storeCropsTableContainer.setViewportView(storeCropsTable);
		
		JButton buyCropsBtn = new JButton("Purchase");
		buyCropsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] cropIds = new Object[storeCropsModel.getRowCount()];
				for(int i = 0; i < cropIds.length; i++) cropIds[i] = storeCropsModel.getValueAt(i,  0);
				Integer purchase = (Integer) JOptionPane.showInputDialog(screen, "Select the crop you would like to purchase", "Selection",
						JOptionPane.PLAIN_MESSAGE, null, cropIds, cropIds[0]);
				if(purchase != null && purchase.intValue() != 0 && game.getStore().cropExists(purchase.intValue())) {
					Crop c = game.getStore().fetchCrop(purchase.intValue());
					int index = -1;
					for(int i = 0; i < storeCropsModel.getRowCount(); i++) {
						if((int) storeCropsModel.getValueAt(i,  0) == c.getId()) index = i;
					}
					storeCropsModel.removeRow(index);
					game.getStore().sellCrop(c.getId());
					game.getFarm().addCrop(c, game.getDay());
					game.getFarm().updateBalance(-c.getPurchasePrice());
					farmBalance.setText("Cash Remaining: $" + String.format("%.2f", game.getFarm().getBalance()));
					viewfarmFundsLabel.setText("Farm Funds: $" + String.format("%.2f", game.getFarm().getBalance()));
					farmCropsModel.addRow(new Object[] {c.getSpecies(), c.getDaysUntilMature(), c.getAge(game.getDay())});
					if(game.getActionCount() > 0) tendCropsBtn.setEnabled(true);
					if(game.getStore().getCrops().size() == 0) buyCropsBtn.setEnabled(false);
				}
			}
		});
		buyCropsBtn.setBounds(552, 119, 155, 60);
		buyCropsTab.add(buyCropsBtn);
		
		JLabel storeCropsTableTitle = new JLabel("Store Crops");
		storeCropsTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
		storeCropsTableTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		storeCropsTableTitle.setBounds(5, 275, 500, 13);
		buyCropsTab.add(storeCropsTableTitle);
		
		JPanel buyItemsTab = new JPanel();
		storeTabs.addTab("Buy Farming Supplies", null, buyItemsTab, null);
		buyItemsTab.setLayout(null);
		
		JScrollPane storeItemsTableContainer = new JScrollPane();
		storeItemsTableContainer.setBounds(5, 5, 500, 270);
		buyItemsTab.add(storeItemsTableContainer);
		
		storeItemsTable = new JTable();
		storeItemsTable.setModel(storeItemsModel);
		storeItemsTableContainer.setViewportView(storeItemsTable);
		
		JButton buyItemsBtn = new JButton("Purchase");
		buyItemsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] itemIds = new Object[storeItemsModel.getRowCount()];
				for(int i = 0; i < itemIds.length; i++) itemIds[i] = storeItemsModel.getValueAt(i,  0);
				Integer purchase = (Integer) JOptionPane.showInputDialog(screen, "Select the supply you would like to purchase", "Selection",
						JOptionPane.PLAIN_MESSAGE, null, itemIds, itemIds[0]);
				if(purchase != null && purchase.intValue() != 0 && game.getStore().itemExists(purchase.intValue())) {
					Item item = game.getStore().fetchItem(purchase.intValue());
					int index = -1;
					for(int i = 0; i < storeItemsModel.getRowCount(); i++) {
						if((int) storeItemsModel.getValueAt(i, 0) == item.getId()) index = i;
					}
					storeItemsModel.removeRow(index);
					game.getStore().sellItem(item.getId());
					game.getFarm().addItem(item);
					game.getFarm().updateBalance(-item.getPrice());
					farmBalance.setText("Cash Remaining: $" + String.format("%.2f", game.getFarm().getBalance()));
					viewfarmFundsLabel.setText("Farm Funds: $" + String.format("%.2f", game.getFarm().getBalance()));
					//update all item models
					if(item.isFoodItem()) {
						farmItemsModel.addRow(new Object[] {item.getName(), "Food Item", item.getBenefit() + " Health Points"});
						farmFoodItemsModel.addRow(new Object[] {item.getId(), item.getName(), item.getBenefit()});
						if(game.getFarm().getAnimals().size() > 0 && game.getActionCount() > 0) feedAnimalsBtn.setEnabled(true);
					}
					else {
						String enhancement = "";
						if(item.getBenefit() == 1) {
							enhancement += item.getBenefit() + " Day Growth Enhancement";
							farmCropItemsModel.addRow(new Object[] {item.getId(), item.getName(), item.getBenefit() + " Day"});
						}
						else {
							enhancement += item.getBenefit() + " Days Growth Enhancement";
							farmCropItemsModel.addRow(new Object[] {item.getId(), item.getName(), item.getBenefit() + " Days"});
						}
						farmItemsModel.addRow(new Object[] {item.getName(), "Crop Item", enhancement});
					}
					if(game.getStore().getItems().size() == 0) buyItemsBtn.setEnabled(false);
				}
			}
		});
		buyItemsBtn.setBounds(552, 119, 155, 60);
		buyItemsTab.add(buyItemsBtn);
		
		JLabel storeItemsTableTitle = new JLabel("Store Items");
		storeItemsTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
		storeItemsTableTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		storeItemsTableTitle.setBounds(5, 275, 500, 13);
		buyItemsTab.add(storeItemsTableTitle);
		
	}
}
