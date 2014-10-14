package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.ActionItem;
import model.ActionItemManager;
import control.Controller;

/**
 * <p>
 * Title: ConsoleScreen
 * </p>
 *
 * <p>
 * Description:  A manually generated action item screen for Della
 * </p>
 *
 * <p>
 * Copyright: Copyright © 2007
 * </p>
 *
 * @author Lynn Robert Carter
 * Many thanks to Harry Sameshima for his original work.
 * @version 1.09
 */
public class ConsoleScreen extends JPanel {
	//---------------------------------------------------------------------------------------------------------------------
	// Console Screen constants

	public static final int noItemSelected = -1;	// Added for Della03

	//---------------------------------------------------------------------------------------------------------------------
	// Console Screen attributes

	// Added for Della03 (start)
	private boolean updatingGUI = false;
	private Controller theController = null;
	private ActionItemManager aiM = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/// Added for Della03 (end)

	//---------------------------------------------------------------------------------------------------------------------
	// Console Screen GUI elements
	JLabel consoleLabel = new JLabel();

	// Added for Della03 (start)
	JLabel actionItemsLabel = new JLabel();
	JList aiSelectList = new JList(new DefaultListModel());
	JScrollPane scrollableListPane = new JScrollPane(aiSelectList, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	ListSelectionListener listSelectionListner = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
			selectActionItem();
		}
	};

	JComboBox sortDirectionComboBox = new JComboBox(ActionItemManager.sortDirectionStrings);
	JComboBox sortFactor1ComboBox = new JComboBox(ActionItemManager.sortingFactorStrings);
	JComboBox sortFactor2ComboBox = new JComboBox(ActionItemManager.sortingFactorStrings);
	JLabel firstSortingLabel = new JLabel();
	JLabel secondSortingLabel = new JLabel();
	JLabel sortDirectionLabel = new JLabel();

	ActionListener sortDirectionActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			sortDirection(ae);
		}
	};
	ActionListener sortFactor1ActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			sortFactor1(ae);
		}
	};
	ActionListener sortFactor2ActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			sortFactor2(ae);
		}
	};
	// Added for Della03 (end)

	// Added for Della10 (start)
	JLabel inclusionFactorLabel = new JLabel();
	JComboBox inclusionFactorComboBox = new JComboBox(ActionItemManager.inclusionFactorStrings);
	ActionListener inclusionFactorActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			listInclusionAction(ae);
		}
	};
	// Added for Della10 (end)

	// Added for Della03 (start)
	JLabel selectedLabel = new JLabel();
	JLabel nameLabel = new JLabel();
	JLabel nameTextField = new JLabel();
	JLabel descriptionLabel = new JLabel();
	JScrollPane descriptionScrollPane = new JScrollPane();
	JTextArea descriptionTextArea = new JTextArea();
	JLabel resolutionLabel = new JLabel();
	JScrollPane resolutionScrollPane = new JScrollPane();
	JTextArea resolutionTextArea = new JTextArea();

	JLabel datesLabel = new JLabel();
	JLabel creationDateLabel = new JLabel();
	JLabel creationDateValueLabel = new JLabel();
	JLabel dueLabel = new JLabel();
	JLabel dueDateTextLabel = new JLabel();
	JLabel actionItemLabel2 = new JLabel();
	JLabel statusLabel = new JLabel();
	JLabel statusValueLabel = new JLabel();
	// Added for Della03 (end)

	// Added for Della05 (start)
	JLabel selectMemberLabel = new JLabel();
	JLabel selectMemberValueLabel = new JLabel();
	// Added for Della05 (end)

	// Added for Della09 (start)
	JLabel selectTeamLabel = new JLabel();
	JLabel selectTeamValueLabel = new JLabel();
	// Added for Della09 (end)

	JLabel copyrightLabel = new JLabel();
	//---------------------------------------------------------------------------------------------------------------------

	/**
	 * The ConsoleScreen class constructor.
	 * 
	 */
	public ConsoleScreen() {
		// Use a modified singleton pattern to access the application's state; Added for Della03
		theController = Controller.getInstance();
		aiM = theController.getActionItemManager();

		// Set up all of the Graphical User Interface elements and position them on the screen
		guiInit();

		// Initialize the screen with the current action item; Added for Della03
		loadScreen();
	}

	/**
	 * Initialize each graphic element, position it on the screen, and add it to the loayout.
	 * 
	 */
	private void guiInit() {
		this.setLayout(null);
		consoleLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		consoleLabel.setBorder(BorderFactory.createEtchedBorder());
		consoleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		consoleLabel.setText("Console");
		consoleLabel.setBounds(new Rectangle(0, 0, 657, 20));

		// Added for Della03 (start)
		actionItemsLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		actionItemsLabel.setText("Action Items:");
		actionItemsLabel.setBounds(new Rectangle(7, 22, 607, 20));        
		aiSelectList.addListSelectionListener(listSelectionListner);
		scrollableListPane.setBounds(new Rectangle(5, 42, 450, 175));

		sortDirectionLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		sortDirectionLabel.setText("Sorting Direction:");
		sortDirectionLabel.setBounds(new Rectangle(470, 50, 135, 15));
		firstSortingLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		firstSortingLabel.setText("First Sorting Factor:");
		firstSortingLabel.setBounds(new Rectangle(470, 95, 130, 15));
		secondSortingLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		secondSortingLabel.setText("Second Sorting Factor:");
		secondSortingLabel.setBounds(new Rectangle(470, 140, 176, 15));
		sortDirectionComboBox.setBounds(new Rectangle(470, 65, 177, 25));
		sortDirectionComboBox.addActionListener(sortDirectionActionListner);
		sortFactor1ComboBox.setBounds(new Rectangle(470, 110, 176, 25));
		sortFactor1ComboBox.addActionListener(sortFactor1ActionListner);
		sortFactor2ComboBox.setBounds(new Rectangle(470, 155, 176, 25));
		sortFactor2ComboBox.addActionListener(sortFactor2ActionListner);
		// Added for Della03 (end)

		// Added for Della10 (start)
		inclusionFactorLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		inclusionFactorLabel.setText("Inclusion Factor:");
		inclusionFactorLabel.setBounds(new Rectangle(470, 185, 130, 15));
		inclusionFactorComboBox.setBounds(new Rectangle(470, 200, 176, 25));
		inclusionFactorComboBox.addActionListener(inclusionFactorActionListner);
		// Added for Della10 (end)

		// Added for Della03 (start)
		selectedLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		selectedLabel.setText("Selected Action Item:");
		selectedLabel.setBounds(new Rectangle(6, 222, 123, 15));
		nameLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		nameLabel.setText("Name:");
		nameLabel.setBounds(new Rectangle(7, 239, 42, 15));
		nameTextField.setText("name goes here");
		nameTextField.setBorder(BorderFactory.createEtchedBorder());
		nameTextField.setBounds(new Rectangle(47, 237, 390, 20));
		descriptionLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		descriptionLabel.setText("Description:");
		descriptionLabel.setBounds(new Rectangle(6, 257, 69, 15));
		descriptionTextArea.setText("Description text");
		descriptionTextArea.setEditable(false);
		descriptionScrollPane.setBounds(new Rectangle(7, 272, 430, 75));
		descriptionScrollPane.getViewport().add(descriptionTextArea);
		resolutionLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		resolutionLabel.setText("Resolution:");
		resolutionLabel.setBounds(new Rectangle(6, 352, 73, 15));
		resolutionTextArea.setText("Resolution text");
		resolutionTextArea.setEditable(false);
		resolutionScrollPane.setBounds(new Rectangle(7, 367, 430, 75));
		resolutionScrollPane.getViewport().add(resolutionTextArea);

		datesLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		datesLabel.setText("Dates");
		datesLabel.setBounds(new Rectangle(460, 242, 34, 16));
		creationDateLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		creationDateLabel.setText("Creation:");
		creationDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		creationDateLabel.setBounds(new Rectangle(479, 258, 51, 16));
		creationDateValueLabel.setBounds(new Rectangle(538, 258, 85, 16));
		dueLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		dueLabel.setText("Due:");
		dueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dueLabel.setBounds(new Rectangle(479, 274, 51, 16));
		dueDateTextLabel.setBounds(new Rectangle(538, 274, 90, 16));
		actionItemLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		actionItemLabel2.setText("Action Item");
		actionItemLabel2.setBounds(new Rectangle(460, 290, 67, 15));
		statusLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		statusLabel.setText("Status:");
		statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		statusLabel.setBounds(new Rectangle(479, 305, 51, 16));
		statusValueLabel.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		statusValueLabel.setBounds(new Rectangle(538, 305, 85, 16));
		// Added for Della03 (end)

		// Added for Della05 (start)
		selectMemberLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		selectMemberLabel.setText("Assigned to Member:");
		selectMemberLabel.setBounds(new Rectangle(460, 325, 175, 16));
		selectMemberValueLabel.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		selectMemberValueLabel.setBounds(new Rectangle(465, 341, 190, 16));
		// Added for Della05 (end)

		// Added for Della09 (start)
		selectTeamLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		selectTeamLabel.setText("Assigned to Team:");
		selectTeamLabel.setBounds(new Rectangle(460, 360, 175, 16));
		selectTeamValueLabel.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		selectTeamValueLabel.setBounds(new Rectangle(465, 376, 190, 16));
		// Added for Della09 (end)

		copyrightLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 10));
		copyrightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		copyrightLabel.setText("Copyright © 2007 Lynn Robert Carter");
		copyrightLabel.setBounds(new Rectangle(0, 428, 645, 15));

		this.add(consoleLabel);

		// Added for Della03
		this.add(actionItemsLabel);
		this.add(scrollableListPane);

		this.add(sortDirectionLabel);
		this.add(sortDirectionComboBox);
		this.add(firstSortingLabel);
		this.add(sortFactor1ComboBox);
		this.add(secondSortingLabel);
		this.add(sortFactor2ComboBox);

		this.add(selectedLabel);
		this.add(nameLabel);
		this.add(nameTextField);
		this.add(descriptionLabel);
		this.add(descriptionScrollPane);
		this.add(resolutionLabel);
		this.add(resolutionScrollPane);

		this.add(datesLabel);
		this.add(creationDateLabel);
		this.add(creationDateValueLabel);
		this.add(dueLabel);
		this.add(dueDateTextLabel);
		this.add(actionItemLabel2);
		this.add(statusLabel);
		this.add(statusValueLabel);
		// Added for Della03 (end)

		// Added for Della05 (start)
		this.add(selectMemberLabel);
		this.add(selectMemberValueLabel);
		// Added for Della05 (end)

		// Added for Della09 (start)
		this.add(selectTeamLabel);
		this.add(selectTeamValueLabel);
		// Added for Della09 (end)

		// Added for Della10 (start)
		this.add(inclusionFactorLabel);
		this.add(inclusionFactorComboBox);
		// Added for Della10 (end)

		this.add(copyrightLabel);
	}

	/**
	 * Based on a combobox selection, establish the screen's fields
	 * 
	 * @param evt	ActionEvent - Any event that gets here is treated as a selection event
	 * 
	 * Added for Della03
	 */
	private void selectActionItem() {
		if (updatingGUI == false){
			// The select list yields an index.  Use that to find the corresponding action item
			ActionItem selectedAI = aiM.getActionItem(aiSelectList.getSelectedIndex());

			// Establish the current action item fields
			aiM.setCurrentActionItem(selectedAI);

			// Establish the screen editing fields
			nameTextField.setText(selectedAI.getActionItemName());
			descriptionTextArea.setText(selectedAI.getDescription());
			descriptionTextArea.setCaretPosition(0);
			resolutionTextArea.setText(selectedAI.getResolution());
			resolutionTextArea.setCaretPosition(0);
			creationDateValueLabel.setText(dateFormat.format(selectedAI.getCreatedDate()));
			if (selectedAI.getDueDate() != null)
				dueDateTextLabel.setText(dateFormat.format(selectedAI.getDueDate()));
			else
				dueDateTextLabel.setText("");

			// Establish the status display 
			if (selectedAI.getStatus() == "Closed")
				statusValueLabel.setText("Closed");
			else
				statusValueLabel.setText("Open");

			// Establish the assigned member display - Added for Della05
			String memberName = selectedAI.getAssignedMember();
			if (memberName.length()==0) {
				selectMemberValueLabel.setText("- no member assigned -");
				aiM.getMemberList().setCurrentSelectedElementIndex(noItemSelected);
			}
			else {
				selectMemberValueLabel.setText(selectedAI.getAssignedMember());
				int memberNameIndex = aiM.getMemberList().findElement(memberName);
				aiM.getMemberList().setCurrentSelectedElementIndex(memberNameIndex);
			}

			// Establish the assigned team display - Added for Della09
			String teamName = selectedAI.getAssignedTeam();
			if (teamName.length()==0) {
				selectTeamValueLabel.setText("- no team assigned -");
				aiM.getTeamList().setCurrentSelectedElementIndex(noItemSelected);
			}
			else {
				selectTeamValueLabel.setText(teamName);
				int teamNameIndex = aiM.getTeamList().findElement(teamName);
				aiM.getTeamList().setCurrentSelectedElementIndex(teamNameIndex);
			}

			// The selected action item has changed so the state has changed
			theController.setDirtyFlag(true);
		}
	}


	/**
	 * The action item selection select list is dynamic.  What is displayed there 
	 * comes from the current names for each of the action items and since the
	 * user can change those at will, the select list must change as well
	 * 
	 * The buildingSelectList flag is used to signal the rest of this screen that
	 * the select list is in the process of being updated.  Change change to the 
	 * select list whether brought about by the user or by code, results in change
	 * events.  We do not want change events that come from defining the 
	 * select list to cause changes to the display.
	 * 
	 * This routine assumes that the order of the action items in the vector is
	 * precisely the correct order for display in the select list.  Sorting must be
	 * done elsewhere.
	 * 
	 * @param names	String[] - This is the sorted array of names for the select list
	 * 
	 * Added for Della03
	 */
	private void loadSelectListData(String[] names, int newIndex){
		// Ignore combobox change events
		updatingGUI = true;

		// Define the select list
		((DefaultListModel)aiSelectList.getModel()).removeAllElements();
		if (names != null) // If names is null, there are no action items
			for (int i=0; i < names.length; i++){
				((DefaultListModel)aiSelectList.getModel()).addElement(names[i]);
			}

		// Set the current selected item
		aiSelectList.setSelectedIndex(newIndex);

		// Done updating the Select List
		updatingGUI = false;
	}

	/**
	 * Based on a combobox selection, establish the sorting direction
	 * @param evt ActionEvent - Any event that get's here we process as a combo box selection
	 * 
	 * Added for Della03
	 */
	private void sortDirection(ActionEvent evt){
		if (updatingGUI == false){
			// Get the sorting direction indicator and use that to rebuild the select list
			aiM.setSortDirection(sortDirectionComboBox.getSelectedIndex());
			loadSelectListData(aiM.getActionItemNames(), aiM.getActionItemIndex(aiM.getCurrentActionItemName()));

			// The sorting direction has changed so the state has changed
			theController.setDirtyFlag(true);
		}
	}

	/**
	 * Based on a combobox selection, establish the sorting factor
	 * @param evt ActionEvent - Any event that get's here we process as a combo box selection
	 * 
	 * Added for Della03
	 */
	private void sortFactor1(ActionEvent evt){
		if (updatingGUI == false){
			// Get the sorting factor 1 indicator and use that to rebuild the select list
			aiM.setSortFactor1(sortFactor1ComboBox.getSelectedIndex());
			loadSelectListData(aiM.getActionItemNames(), aiM.getActionItemIndex(aiM.getCurrentActionItemName()));

			// A sorting factor has changed so the state has changed
			theController.setDirtyFlag(true);
		}
	}

	/**
	 * Based on a combobox selection, establish the sorting factor
	 * @param evt ActionEvent - Any event that get's here we process as a combo box selection
	 * 
	 * Added for Della03
	 */
	private void sortFactor2(ActionEvent evt){
		if (updatingGUI == false){
			// Get the sorting factor 2 indicator and use that to rebuild the select list
			aiM.setSortFactor2(sortFactor2ComboBox.getSelectedIndex());
			loadSelectListData(aiM.getActionItemNames(), aiM.getActionItemIndex(aiM.getCurrentActionItemName()));

			// A sorting factor has changed so the state has changed
			theController.setDirtyFlag(true);
		}
	} 

	/**
	 * Clear the current action item and reset the text fields on the display
	 * 
	 * Added for Della03
	 */
	private void clearAI(){
		updatingGUI = true;
		aiM.setCurrentActionItem(aiM.getActionItem(noItemSelected));
		aiM.clearCurrentActionItem();
		nameTextField.setText("");
		descriptionTextArea.setText("");
		descriptionTextArea.setCaretPosition(0);
		resolutionTextArea.setText("");
		resolutionTextArea.setCaretPosition(0);
		statusValueLabel.setText("Open");
		creationDateValueLabel.setText("");
		creationDateValueLabel.setText("");
		selectMemberValueLabel.setText("- no member assigned -");	// Added for Della05
		selectTeamValueLabel.setText("- no team assigned -");		// Added for Della09
		updatingGUI = false;
	}

	/**
	 * Fill the screen with the values of the current action item, if we have one
	 * 
	 * Added for Della03
	 */
	public void loadScreen() {
		updatingGUI = true;
		// Fetch the current action item.  If there isn't one, set up a blank action item
		ActionItem ai = aiM.getCurrentActionItem();
		if (ai == null) {
			clearAI();
		}
		else {
			// Define the text fields for the existing action item
			nameTextField.setText(ai.getActionItemName());
			descriptionTextArea.setText(ai.getDescription());
			descriptionTextArea.setCaretPosition(0);
			resolutionTextArea.setText(ai.getResolution());
			resolutionTextArea.setCaretPosition(0);

			// Establish the status display 
			if (ai.getStatus() == "Closed")
				statusValueLabel.setText("Closed");
			else
				statusValueLabel.setText("Open");


			// Define the creation and due dates
			if (ai.getCreatedDate() != null)
				creationDateValueLabel.setText(dateFormat.format(ai.getCreatedDate()));
			else
				creationDateValueLabel.setText("");
			if (ai.getDueDate() != null)
				dueDateTextLabel.setText(dateFormat.format(ai.getDueDate()));
			else
				creationDateValueLabel.setText("");

			// Establish the assigned member display - Added for Della05
			String name = ai.getAssignedMember();
			if (name.length()==0)
				selectMemberValueLabel.setText("- no member assigned -");
			else
				selectMemberValueLabel.setText(ai.getAssignedMember());

			// Establish the assigned team display - Added for Della09
			String teamName = ai.getAssignedTeam();
			if (teamName.length()==0)
				selectTeamValueLabel.setText("- no team assigned -");
			else
				selectTeamValueLabel.setText(ai.getAssignedTeam());
		}
		// Set up the selection ComboBoxes and the select list - Modified for Della03
		sortDirectionComboBox.setSelectedIndex(aiM.getSortDirection());
		sortFactor1ComboBox.setSelectedIndex(aiM.getSortFactor1());
		sortFactor2ComboBox.setSelectedIndex(aiM.getSortFactor2());
		inclusionFactorComboBox.setSelectedIndex(aiM.getInclusionFactor());	// Added for Della10
		loadSelectListData(aiM.getActionItemNames(), aiM.getActionItemIndex(aiM.getCurrentActionItemName()));
		updatingGUI = false;
	}

	/**
	 * Process a select an inclusion factor event
	 * 
	 * @param e ActionEvent
	 * 
	 * Added for Della10
	 */
	private void listInclusionAction(ActionEvent evt){
		if (updatingGUI == false){
			aiM.setInclusionFactor(inclusionFactorComboBox.getSelectedIndex());
			loadSelectListData(aiM.getActionItemNames(), aiM.getActionItemIndex(aiM.getCurrentActionItemName()));
			if (aiSelectList.getSelectedIndex() == noItemSelected) {
				clearAI();
				loadScreen();
			}
			// A sorting factor has changed so the state has changed
			theController.setDirtyFlag(true);
		}
	}
}
