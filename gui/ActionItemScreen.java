package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import control.*;

import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 * <p>
 * Title: ActionItemScreen
 * </p>
 * 
 * <p>
 * Description: A manually generated Action Item Screen for Della
 * </p>
 * 
 * <p>
 * Copyright: Copyright © 2007
 * </p>
 * 
 * @author Lynn Robert Carter
 * @version 1.10
 * Many thanks to Harry Sameshima for his original work.
 */
public class ActionItemScreen extends JPanel {
	//---------------------------------------------------------------------------------------------------------------------
	// Action Item Screen constants

	public static final int noItemSelected = -1;

	//---------------------------------------------------------------------------------------------------------------------
	// Action Item Screen attributes

	private Boolean updatingGUI = false;
	private Controller theController = null;
	private ActionItemManager aiM = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	// Added for Della05
	private String selectedMember = "";

	// Added for Della09
	private String selectedTeam = "";

	//---------------------------------------------------------------------------------------------------------------------
	// Action Item Screen GUI elements
	JLabel actionItemLabel = new JLabel();

	// Added for Della01 (start)
	JLabel comboBoxLabel = new JLabel();
	JComboBox aiListComboBox = new JComboBox();
	ActionListener aiListComboBoxActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { selectActionItem(ae); }
	};
	JLabel selectGuidanceLabel = new JLabel();
	// Added for Della01 (end)

	// Added for Della10 (start)
	JComboBox listInclusionComboBox = new JComboBox(ActionItemManager.inclusionFactorStrings);
	ActionListener listInclusionSelectorActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { listInclusionAction(ae); }
	};
	JLabel inclusionFactorLabel = new JLabel();
	// Added for Della10 (end)

	// Added for Della02 (start)
	JComboBox sortDirectionComboBox = new JComboBox(ActionItemManager.sortDirectionStrings);
	ActionListener sortDirectionSelectorActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { sortDirection(ae); }
	};
	JComboBox sortFactor1ComboBox = new JComboBox(ActionItemManager.sortingFactorStrings);
	ActionListener sortFactor1SelectorActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { sortingFactor1(ae); }
	};
	JComboBox sortFactor2ComboBox = new JComboBox(ActionItemManager.sortingFactorStrings);
	ActionListener sortFactor2SelectorActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { sortingFactor2(ae); }
	};
	JLabel sortFactor1Label = new JLabel();
	JLabel sortFactor2Label = new JLabel();
	JLabel sortDirectionLabel = new JLabel();
	// Added for Della02 (end)

	JLabel selectedLabel = new JLabel();
	JLabel nameLabel = new JLabel();
	JTextField nameTextField = new JTextField();
	JLabel descriptionLabel = new JLabel();
	JScrollPane descriptionScrollPane = new JScrollPane();
	JTextArea descriptionTextArea = new JTextArea();
	JLabel resolutionLabel = new JLabel();
	JScrollPane resolutionScrollPane = new JScrollPane();
	JTextArea resolutionTextArea = new JTextArea();

	// Unsaved updated fields
	DocumentListener aiChangeListener = new DocumentListener() {
		public void changedUpdate(DocumentEvent de){ checkForUnsavedUpdates(); }
		public void insertUpdate(DocumentEvent de){ checkForUnsavedUpdates(); }
		public void removeUpdate(DocumentEvent de){ checkForUnsavedUpdates(); }
	};
	JLabel unsavedChangesLabel = new JLabel();

	JLabel datesLabel = new JLabel();
	JLabel creationLabel = new JLabel();
	JLabel creationValueLabel = new JLabel();
	JLabel dueDateLabel = new JLabel();
	JTextField dueDateTextField = new JTextField();
	JLabel formatLabel = new JLabel();
	JLabel actionItemLabel2 = new JLabel();
	JLabel statusLabel = new JLabel();
	JComboBox statusComboBox = new JComboBox(ActionItemManager.statusStrings);
	ActionListener statusSelectorActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { checkForUnsavedUpdates(); }
	};

	// Added for Della05 (start)
	JLabel selectMemberComboBoxLabel = new JLabel();
	JComboBox selectMemberComboBox = new JComboBox();
	ActionListener selectMemberComboBoxActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { selectMember(ae); }
	};
	// Added for Della05 (end)

	// Added for Della09 (start)
	JLabel selectTeamComboBoxLabel = new JLabel();
	JComboBox selectTeamComboBox = new JComboBox();
	ActionListener selectTeamComboBoxActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { selectTeam(ae); }
	};
	// Added for Della09 (end)

	// Action Buttons
	JButton updateButton = new JButton();
	ActionListener updateButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { updateActionItem(ae); }
	};
	JButton clearButton = new JButton();
	ActionListener clearButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { clearActionItemForm(ae); }
	};
	
	// Added for Della01
	JButton createButton = new JButton();

	ActionListener createButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { createActionItem(ae); }
	};

	// Added for Della10 (start)
	JButton deleteButton = new JButton();
	ActionListener deleteButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { deleteActionItem(ae); }
	};
	// Added for Della10 (end)
	//---------------------------------------------------------------------------------------------------------------------


	/**
	 * The ActionItemScreen class constructor.
	 * 
	 */
	public ActionItemScreen() {
		// Use a modified singleton pattern to access the application's state
		theController = Controller.getInstance();
		aiM = theController.getActionItemManager();

		// Set up all of the Graphical User Interface elements and place them on the screen
		guiInit();

		// Initialize the screen with the current action item
		loadScreen();
	}

	/**
	 * Initialize each graphic element, position it on the screen, and add it to the loayout.
	 * 
	 */
	private void guiInit() {
		// Updating the GUI
		updatingGUI = true;
		
		// Set all of the graphical elements in this screen by adding them to the layout
		this.setLayout(null);

		actionItemLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		actionItemLabel.setBorder(BorderFactory.createEtchedBorder());
		actionItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		actionItemLabel.setText("Action Items");
		actionItemLabel.setBounds(new Rectangle(0, 0, 657, 20));

		// Added for Della01 (start)
		comboBoxLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		comboBoxLabel.setText("Action Items:");
		comboBoxLabel.setBounds(new Rectangle(7, 25, 100, 15));
		aiListComboBox.setBounds(new Rectangle(5, 40, 640, 25));
		aiListComboBox.addActionListener(aiListComboBoxActionListner);
		selectGuidanceLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 10));
		selectGuidanceLabel.setText("Select an Action Item from the pull-down list above to examine and update it.");
		selectGuidanceLabel.setBounds(new Rectangle(15, 65, 500, 15));
		// Added for Della01 (end)

		// Added for Della10 (start)
		inclusionFactorLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		inclusionFactorLabel.setText("Inclusion Factor:");
		inclusionFactorLabel.setBounds(new Rectangle(15, 90, 175, 15));
		listInclusionComboBox.setBounds(new Rectangle(15, 105, 175, 25));
		listInclusionComboBox.addActionListener(listInclusionSelectorActionListner);
		// Added for Della10 (end)        

		// Added for Della02 (start)        
		sortDirectionLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		sortDirectionLabel.setText("Sorting Direction:");
		sortDirectionLabel.setBounds(new Rectangle(195, 90, 135, 15));
		sortDirectionComboBox.setBounds(new Rectangle(195, 105, 135, 25));
		sortDirectionComboBox.addActionListener(sortDirectionSelectorActionListner);

		sortFactor1Label.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		sortFactor1Label.setText("First Sorting Factor:");
		sortFactor1Label.setBounds(new Rectangle(335, 90, 145, 15));
		sortFactor1ComboBox.setBounds(new Rectangle(335, 105, 150, 25));
		sortFactor1ComboBox.addActionListener(sortFactor1SelectorActionListner);

		sortFactor2Label.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		sortFactor2Label.setText("Second Sorting Factor:");
		sortFactor2Label.setBounds(new Rectangle(495, 90, 145, 15));
		sortFactor2ComboBox.setBounds(new Rectangle(495, 105, 150, 25));
		sortFactor2ComboBox.addActionListener(sortFactor2SelectorActionListner);
		// Added for Della02 (end)

		selectedLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		selectedLabel.setText("Selected Action Item:");
		selectedLabel.setBounds(new Rectangle(6, 145, 123, 15));
		nameLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		nameLabel.setText("Name:");
		nameLabel.setBounds(new Rectangle(7, 165, 42, 15));
		nameTextField.setText("");
		nameTextField.setBounds(new Rectangle(46, 165, 390, 22));
		nameTextField.getDocument().addDocumentListener(aiChangeListener);

		descriptionLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		descriptionLabel.setText("Description:");
		descriptionLabel.setBounds(new Rectangle(6, 190, 69, 15));
		descriptionScrollPane.setBounds(new Rectangle(7, 210, 430, 75));
		descriptionScrollPane.getViewport().add(descriptionTextArea);
		descriptionTextArea.setText("");
		descriptionTextArea.getDocument().addDocumentListener(aiChangeListener);

		resolutionLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		resolutionLabel.setText("Resolution:");
		resolutionLabel.setBounds(new Rectangle(6, 295, 73, 15));
		resolutionScrollPane.setBounds(new Rectangle(7, 315, 430, 75));
		resolutionScrollPane.getViewport().add(resolutionTextArea);
		resolutionTextArea.setToolTipText("");
		resolutionTextArea.setText("");
		resolutionTextArea.getDocument().addDocumentListener(aiChangeListener);

		datesLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		datesLabel.setText("Dates");
		datesLabel.setBounds(new Rectangle(450, 175, 34, 16));

		creationLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		creationLabel.setText("Creation:");
		creationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		creationLabel.setBounds(new Rectangle(469, 195, 51, 16));
		creationValueLabel.setText("");
		creationValueLabel.setBounds(new Rectangle(528, 195, 85, 16));

		dueDateLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		dueDateLabel.setText("Due:");
		dueDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dueDateLabel.setBounds(new Rectangle(469, 217, 51, 16));
		dueDateTextField.setBounds(new Rectangle(524, 215, 90, 20));
		dueDateTextField.getDocument().addDocumentListener(aiChangeListener);
		formatLabel.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		formatLabel.setText("Use yyyy-mm-dd format");
		formatLabel.setBounds(new Rectangle(495, 238, 125, 11));

		actionItemLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		actionItemLabel2.setText("Action Item");
		actionItemLabel2.setBounds(new Rectangle(450, 260, 67, 15));

		statusLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		statusLabel.setText("Status:");
		statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		statusLabel.setBounds(new Rectangle(469, 277, 51, 16));
		statusComboBox.setBounds(new Rectangle(524, 275, 90, 25));
		statusComboBox.addActionListener(statusSelectorActionListner);

		// Added for Della05 (start)
		selectMemberComboBoxLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		selectMemberComboBoxLabel.setText("Assigned to Member:");
		selectMemberComboBoxLabel.setBounds(new Rectangle(450, 310, 150, 16));
		selectMemberComboBox.setBounds(new Rectangle(450, 326, 195, 25));
		selectMemberComboBox.addActionListener(selectMemberComboBoxActionListner);
		// Added for Della05 (end)

		// Added for Della09 (start)
		selectTeamComboBoxLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		selectTeamComboBoxLabel.setText("Assigned to Team:");
		selectTeamComboBoxLabel.setBounds(new Rectangle(450, 350, 150, 16));
		selectTeamComboBox.setBounds(new Rectangle(450, 366, 195, 25));
		selectTeamComboBox.addActionListener(selectTeamComboBoxActionListner);
		// Added for Della09 (end)

		updateButton.setFont(new Font("Dialog", Font.BOLD, 11));
		updateButton.setBounds(new Rectangle(3, 395, 170, 30));
		updateButton.setText("Update This Action Item");
		updateButton.addActionListener(updateButtonActionListner);

		clearButton.setFont(new Font("Dialog", Font.BOLD, 11));
		clearButton.setBounds(new Rectangle(173, 395, 126, 30));
		clearButton.setText("Clear This Form");
		clearButton.addActionListener(clearButtonActionListner);

		// Added for Della01 (start)
		createButton.setFont(new Font("Dialog", Font.BOLD, 11));
		createButton.setBounds(new Rectangle(299, 395, 180, 30));
		createButton.setText("Create a New Action Item");
		createButton.addActionListener(createButtonActionListner);
		// added for Della01 (end)

		// Added for Della10 (start)
		deleteButton.setFont(new Font("Dialog", Font.BOLD, 11));
		deleteButton.setBounds(new Rectangle(479, 395, 168, 30));
		deleteButton.setText("Delete This Action Item");
		deleteButton.addActionListener(deleteButtonActionListner);
		// Added for Della10 (end)

		unsavedChangesLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		unsavedChangesLabel.setBounds(new Rectangle(250, 430, 200, 15));
		unsavedChangesLabel.setText("");
		unsavedChangesLabel.setForeground(Color.red);		
		
		//----------------------------------------------------------------------------
		// Add the objects to the layout
		this.add(actionItemLabel);

		// Added for Della01 (start)
		this.add(comboBoxLabel);
		this.add(aiListComboBox);
		this.add(selectGuidanceLabel);
		// Added for Della01 (end)

		// Added for Della10 (start)
		this.add(inclusionFactorLabel);
		this.add(listInclusionComboBox);
		// Added for Della10 (end)

		// Added for Della02 (start)
		this.add(sortDirectionLabel);
		this.add(sortDirectionComboBox);
		this.add(sortFactor1Label);
		this.add(sortFactor1ComboBox);
		this.add(sortFactor2Label);
		this.add(sortFactor2ComboBox);
		// Added for Della02 (end)

		this.add(selectedLabel);
		this.add(nameLabel);
		this.add(nameTextField);
		this.add(descriptionLabel);
		this.add(descriptionScrollPane);
		this.add(resolutionLabel);
		this.add(resolutionScrollPane);

		this.add(datesLabel);
		this.add(creationLabel);
		this.add(creationValueLabel);
		this.add(dueDateLabel);
		this.add(dueDateTextField);
		this.add(formatLabel);
		this.add(actionItemLabel2);
		this.add(statusLabel);
		this.add(statusComboBox);

		// Added for Della05 (start)
		this.add(selectMemberComboBoxLabel);
		this.add(selectMemberComboBox);
		// Added for Della05 (end)

		// Added for Della09 (start)
		this.add(selectTeamComboBoxLabel);
		this.add(selectTeamComboBox);
		// Added for Della09 (end)

		this.add(updateButton);
		this.add(clearButton);

		// Added for Della01 (start)
		this.add(createButton);
		// Added for Della01 (end)

		// Added for Della10 (start)
		this.add(deleteButton);
		// Added for Della10 (end)

		this.add(unsavedChangesLabel);

		// Done updating the GUI
		updatingGUI = false;
	}

	/**
	 * Clear the current action item and the attribute related combo boxes
	 * 
	 */
	private void clearAI() {
		updatingGUI = true;
		aiM.clearCurrentActionItem();
		nameTextField.setText("");
		descriptionTextArea.setText("");
		resolutionTextArea.setText("");
		creationValueLabel.setText("");
		dueDateTextField.setText("");

		// Select the Open status
		statusComboBox.setSelectedIndex(ActionItemManager.statusOpen);
		
		// Reset the Action Item ComboBox so no item is selected Added for Della01
		aiListComboBox.setSelectedIndex(noItemSelected);

		// No member is selected - Added for Della05
		selectMemberComboBox.setSelectedIndex(noItemSelected);

		// No team is selected - Added for Della09
		selectTeamComboBox.setSelectedIndex(noItemSelected);

		updatingGUI = false;
	}

	/**
	 * Process a "Clear This Form" button click request
	 * Clear out the current action item and inform the user if this results in unsaved changes
	 * 
	 * @param e ActionEvent
	 */
	private void clearActionItemForm(ActionEvent e) {
		// Reset the current Action Item Fields
		clearAI();
		
		theController.setDirtyFlag(true);
		checkForUnsavedUpdates();
	}

	/**
	 * Create a new action item
	 * 
	 * @param e ActionEvent
	 * 
	 * Added for Della01
	 */
	private void createActionItem(ActionEvent e) {
		ActionItem ai = null;
		try {
			ai = aiM.createActionItem(nameTextField.getText(),
					descriptionTextArea.getText(),
					resolutionTextArea.getText(),
					statusComboBox.getSelectedItem().toString(),
					dueDateTextField.getText(),
					// Added for Della05
					selectedMember,
					// Added for Della09
					selectedTeam);
		} 
		catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Update the creation date for the action item
		creationValueLabel.setText(dateFormat.format(ai.getCreatedDate()));
		updatingGUI = true;
		loadComboBoxData(aiM.getActionItemNames(), aiM.getActionItemIndex(aiM.getCurrentActionItemName())); // Modified for Della02
		updatingGUI = false;

		theController.setDirtyFlag(true);
		checkForUnsavedUpdates();
	}

	/**
	 * Update the current action item in memory
	 * 
	 * @param e ActionEvent
	 */
	private void updateActionItem(ActionEvent e) {
		// Tell the ActionItemManager to save the update
		try {
			aiM.updateActionItem(nameTextField.getText(),
					descriptionTextArea.getText(),
					resolutionTextArea.getText(),
					statusComboBox.getSelectedItem().toString(),
					dueDateTextField.getText(),
					// Added for Della01
					aiListComboBox.getSelectedIndex(),
					// Added for Della05
					selectedMember,
					// Added for Della09
					selectedTeam);
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// It is possible that the Action Item name has been changed
		// so we must re-sort the Action Items and re-establish the
		// combobox select list. - Added for Della01
		updatingGUI = true;
		loadComboBoxData(aiM.getActionItemNames(), aiM.getActionItemIndex(aiM.getCurrentActionItemName()));	// Modified for Della02
		updatingGUI = false;
		
		// Since the update action could change the state of the current action item's status to "Closed"
		// and the inclusion factor could cause the current action item to no longer be visible, we must
		// check to see if the form should be cleared.  - Added for Della10
		if (aiListComboBox.getSelectedIndex() == noItemSelected) {
			clearAI();
			loadScreen();
		}

		theController.setDirtyFlag(true);
		checkForUnsavedUpdates();
	}

	/**
	 * Fill the screen with the values of the current action item, if we have one, and display it.
	 */
	public void loadScreen() {
		updatingGUI = true;

		// If the changes pending flag is false, clear the message... this could have been reset
		// by others and not properly updated here.	Added for Della10
		if (aiM.getEditChangesPending()==false)
			unsavedChangesLabel.setText("");

		// Fetch the current action item.  If there isn't one, leave now
		ActionItem ai = aiM.getCurrentActionItem();
		if (ai == null) {
			clearAI();
			updatingGUI = true;
			statusComboBox.setSelectedIndex(ActionItemManager.statusOpen);
			creationValueLabel.setText("");
			dueDateTextField.setText("");
		}
		else {
			// Define the text fields
			updatingGUI = true;
			nameTextField.setText(ai.getActionItemName());
			descriptionTextArea.setText(ai.getDescription());
			descriptionTextArea.setCaretPosition(0);
			resolutionTextArea.setText(ai.getResolution());
			resolutionTextArea.setCaretPosition(0);
		}
		// Define the status combobox value
		for (int i = 0; i < ActionItemManager.statusStrings.length; ++i)
			if (ai.getStatus().compareTo(ActionItemManager.statusStrings[i]) == 0) {
				statusComboBox.setSelectedIndex(i);
				break;
			}

		// Define the creation and due dates
		if (ai.getCreatedDate() != null)
			creationValueLabel.setText(dateFormat.format(ai.getCreatedDate()));
		else
			creationValueLabel.setText("");
		if (ai.getDueDate() != null)
			dueDateTextField.setText(dateFormat.format(ai.getDueDate()));
		else
			dueDateTextField.setText("");

		// Set up the selection Combo Boxes - Modified for Della02 and then for Della10
		selectMemberComboBox.setSelectedIndex(noItemSelected);
		selectTeamComboBox.setSelectedIndex(noItemSelected);
		listInclusionComboBox.setSelectedIndex(aiM.getInclusionFactor());
		sortDirectionComboBox.setSelectedIndex(aiM.getSortDirection());
		sortFactor1ComboBox.setSelectedIndex(aiM.getSortFactor1());
		sortFactor2ComboBox.setSelectedIndex(aiM.getSortFactor2());

		// Set up the selection ComboBox - Modified for Della02
		loadComboBoxData(aiM.getActionItemNames(), aiM.getActionItemIndex(aiM.getCurrentActionItemName()));

		// Modified for Della 09
		// The member and team lists are related.  If there is a current selected member, the list of teams
		// shown must be limited to those to which the member belongs.  Similarly, if there is a current selected
		// team, only the members of that team can be shown as possible members.  If no member is selected, all
		// teams are shown and if no team is selected, all members are shown.
		if (ai == null) {
			selectedTeam = "";
			selectedMember = "";
		}
		else {
			selectedTeam = ai.getAssignedTeam();
			selectedMember = ai.getAssignedMember();
		}
		buildMemberList(selectedTeam, selectedMember);
		buildTeamList(selectedMember, selectedTeam);		
		
		updatingGUI = false;
	}

	/**
	 * Based on a combo box selection, establish the screen's fields
	 * 
	 * @param evt ActionEvent - Any event that gets here is treated as a selection event
	 * 
	 * Added for Della01
	 */
	private void selectActionItem(ActionEvent evt) {
		if (updatingGUI == false) {
			updatingGUI = true;
			try {
				// The combobox return an index. Use that to find the corresponding action item
				ActionItem selectedAI = new ActionItem();
				selectedAI = aiM.getActionItem(aiListComboBox.getSelectedIndex());

				// Establish the current action item fields
				aiM.setCurrentActionItem(selectedAI);

				if (selectedAI == null) clearAI();
				else
				{
					// Establish the screen editing fields
					nameTextField.setText(selectedAI.getActionItemName());
					descriptionTextArea.setText(selectedAI.getDescription());
					descriptionTextArea.setCaretPosition(0);
					resolutionTextArea.setText(selectedAI.getResolution());
					resolutionTextArea.setCaretPosition(0);
					if (selectedAI.getCreatedDate() != null)
						creationValueLabel.setText(dateFormat.format(selectedAI.getCreatedDate()));
					else
						creationValueLabel.setText("");
					if (selectedAI.getDueDate() != null)
						dueDateTextField.setText(dateFormat.format(selectedAI.getDueDate()));
					else
						dueDateTextField.setText("");

					// Establish the status combo box
					if (selectedAI.getStatus() == "Closed")
						statusComboBox.setSelectedIndex(ActionItemManager.statusClosed);
					else
						statusComboBox.setSelectedIndex(ActionItemManager.statusOpen);

					// Define the assigned member selection - Added for Della05; Modified for Della09
					selectedMember = aiM.getCurrentAssignedMember();
					selectedTeam = aiM.getCurrentAssignedTeam();
					buildMemberList(aiM.getCurrentAssignedTeam(), selectedMember);
					int memberNameIndex = selectMemberComboBox.getSelectedIndex();
					aiM.getMemberList().setCurrentSelectedElementIndex(memberNameIndex);			

					// Define the assigned team selection - Modified for Della09
					buildTeamList(selectedMember, selectedTeam);
					int teamNameIndex = selectTeamComboBox.getSelectedIndex();
					aiM.getTeamList().setCurrentSelectedElementIndex(teamNameIndex);					
				}
				// The selected action item has changed so the state has changed
				theController.setDirtyFlag(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
			updatingGUI = false;
		}
	}

	/**
	 * Based on a combobox selection, establish the sorting direction
	 * @param evt ActionEvent - Any event that get's here we process as a combo box selection
	 * 
	 * Added for Della02
	 */
	private void sortDirection(ActionEvent evt){
		if (updatingGUI == false){
			aiM.setSortDirection(sortDirectionComboBox.getSelectedIndex());
			loadComboBoxData(aiM.getActionItemNames(), aiM.getActionItemIndex(aiM.getCurrentActionItemName()));

			// The sorting direction has changed so the state has changed
			theController.setDirtyFlag(true);
		}
	}

	/**
	 * Based on a combobox selection, establish the first sorting factor
	 * @param evt ActionEvent - Any event that get's here we process as a combo box selection
	 * 
	 * Added for Della02
	 */
	private void sortingFactor1(ActionEvent evt){
		if (updatingGUI == false){
			aiM.setSortFactor1(sortFactor1ComboBox.getSelectedIndex());
			loadComboBoxData(aiM.getActionItemNames(), aiM.getActionItemIndex(aiM.getCurrentActionItemName()));

			// The first sorting factor has changed so the state has changed
			theController.setDirtyFlag(true);
		}
	}

	/**
	 * Based on a combo box selection, establish the second sorting factor
	 * @param evt ActionEvent - Any event that get's here we process as a combo box selection
	 * 
	 * Added for Della02
	 */
	private void sortingFactor2(ActionEvent evt){
		if (updatingGUI == false){
			aiM.setSortFactor2(sortFactor2ComboBox.getSelectedIndex());
			loadComboBoxData(aiM.getActionItemNames(), aiM.getActionItemIndex(aiM.getCurrentActionItemName()));

			// The second sorting factor has changed so the state has changed
			theController.setDirtyFlag(true);
		}
	}

	/**
	 * The action item selection combo box is dynamic. What is displayed there
	 * comes from the current names for each of the action items and since the
	 * user can change those at will, the combo box select list must change as
	 * well.  
	 * 
	 * This routine assumes that the order of the action items in the vector is
	 * precisely the correct order for display in the combo box. Sorting must be
	 * done elsewhere.
	 * 
	 * @param names
	 *            String[] - This is the sorted array of names for the select list
	 * 
	 * Added for Della01 updated for Della02
	 */
	private void loadComboBoxData(String[] names, int newIndex){
		// Define the combo box select list
		aiListComboBox.removeAllItems();
		if ((names != null) && (names.length > 0)) {
			// If names is null or the length is zero, there are no action items
			for (int i = 0; i < names.length; i++)
				aiListComboBox.addItem(names[i]);

			// Set the current selected item; updated for Della02
			aiListComboBox.setSelectedIndex(newIndex);
		}
	}

	/**
	 * Build the list of members given the current selected team and member
	 * 
	 * Added for Della09
	 */
	private void buildMemberList(String teamName, String memberName) {
		// Build the member list combo box
		int assignedMemberIndex = noItemSelected;
		selectMemberComboBox.removeAllItems();
		int memberListSize = aiM.getMemberListSize();
		if (teamName == "")
			for (int i=0; i<memberListSize; i++) {
				String newMember = aiM.getMember(i);
				selectMemberComboBox.addItem(newMember);
				if (memberName.compareTo(newMember) == 0) assignedMemberIndex = i;
			}
		else {
			String[] possibleMembers = aiM.getAssociatedMembers(teamName);
			for (int i=0; i<possibleMembers.length; i++) {
				selectMemberComboBox.addItem(possibleMembers[i]);
				if (memberName.compareTo(possibleMembers[i]) == 0) assignedMemberIndex = i;
			}
		}	
		selectMemberComboBox.addItem("- no member selected -");

		// Select the current member
		aiM.getMemberList().setCurrentSelectedElementIndex(assignedMemberIndex);
		selectMemberComboBox.setSelectedIndex(assignedMemberIndex);
	}

	/**
	 * Build the list of teams given the current selected member and team
	 * 
	 * Added for Della09
	 */
	private void buildTeamList(String memberName, String teamName) {
		// Build the team list combo box
		int assignedTeamIndex = noItemSelected;
		selectTeamComboBox.removeAllItems();
		int teamListSize = aiM.getTeamListSize();
		if (memberName == "")
			for (int i=0; i<teamListSize; i++) {
				String newTeam = aiM.getTeam(i);
				selectTeamComboBox.addItem(newTeam);
				if (teamName.compareTo(newTeam) == 0) assignedTeamIndex = i;
			}
		else {
			String[] possibleTeams = aiM.getAffiliatedTeams(memberName);
			for (int i=0; i<possibleTeams.length; i++) {
				selectTeamComboBox.addItem(possibleTeams[i]);
				if (teamName.compareTo(possibleTeams[i]) == 0) assignedTeamIndex = i;
			}
		}
		selectTeamComboBox.addItem("- no team selected -");

		// Select the current team
		aiM.getTeamList().setCurrentSelectedElementIndex(assignedTeamIndex);
		selectTeamComboBox.setSelectedIndex(assignedTeamIndex);
	}

	/**
	 * Given a "select a member" combo box event, process it
	 * 
	 * Added for Della05 and updated for Della09
	 */
	private void selectMember(ActionEvent evt){
		if (updatingGUI == false) {
			updatingGUI = true;
			if (selectedTeam == null) selectedTeam = "";
			ElementList memberList = aiM.getMemberList();
			int memberListSize = selectMemberComboBox.getItemCount();
			int assignedMemberIndex = selectMemberComboBox.getSelectedIndex();
			if (assignedMemberIndex > noItemSelected && assignedMemberIndex < memberListSize-1) {
				selectedMember = (String)selectMemberComboBox.getSelectedItem();
				memberList.setCurrentSelectedElementIndex(assignedMemberIndex);
			}
			else {
				memberList.setCurrentSelectedElementIndex(noItemSelected);
				selectMemberComboBox.setSelectedIndex(noItemSelected);
				selectedMember = "";
			}
			buildTeamList(selectedMember, selectedTeam);

			theController.setDirtyFlag(true);
			updatingGUI = false;
			checkForUnsavedUpdates();
		}
	}

	/**
	 * Given a "select a team" combo box event, process it
	 * 
	 * Added for Della09
	 */
	private void selectTeam(ActionEvent evt){
		if (updatingGUI == false) {
			updatingGUI = true;
			if (selectedMember == null) selectedMember = "";
			ElementList teamList = aiM.getTeamList();
			int teamListSize = selectTeamComboBox.getItemCount();
			int assignedTeamIndex = selectTeamComboBox.getSelectedIndex();
			if (assignedTeamIndex > noItemSelected && assignedTeamIndex < teamListSize-1) {
				selectedTeam = (String)selectTeamComboBox.getSelectedItem();
				teamList.setCurrentSelectedElementIndex(assignedTeamIndex);
			}
			else {
				teamList.setCurrentSelectedElementIndex(noItemSelected);
				selectTeamComboBox.setSelectedIndex(noItemSelected);
				selectedTeam = "";
			}
			buildMemberList(selectedTeam, selectedMember);

			theController.setDirtyFlag(true);
			updatingGUI = false;
			checkForUnsavedUpdates();
		}
	}

	/**
	 * Given a "select an inclusion factor" event is being processed, see if it is 
	 * okay to process it based on whether or not there are outstanding edits that 
	 * could be lost
	 * 
	 * Added for Della10
	 */
	private boolean okayToChangeInclusion(){
		// Check to see if the envent will narrow the list of possible action items displayed.
		// If the select index is greater than zero, the selection is a narrowing one, so the user
		// must specify if the operation should continue or not.
		if (listInclusionComboBox.getSelectedIndex() > ActionItemManager.inclusionFactorAll) {
			if (aiM.getEditChangesPending()) {
				int x = JOptionPane.showConfirmDialog(this,
						"You have edited this Action Items without updating it and\n" + 
						"the command you have requested may discard these changes!\n" +
						"Do you want to risk losing these edits?\n\n" +
						"Click \"Yes\" to proceed and possibly lose these edits.\n" +
						"Click \"No\" to set the inclusion factor to \"All action items\".",
						"Inclusion Factor Change Requests While Action Item Edits Pending!",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.YES_NO_OPTION);
				if (x==0) {
					aiM.setEditChangesPending(false);
					return true;
				}
				else return false;
			}
			else return true;
		}
		else return true;
	}

	/**
	 * Given a "select an inclusion factor" combo box event, this method processes the event
	 * 
	 * Added for Della10
	 */
	private void listInclusionAction(ActionEvent evt){
		if (updatingGUI == false){
			updatingGUI = true;
			if (okayToChangeInclusion()){
				aiM.setInclusionFactor(listInclusionComboBox.getSelectedIndex());
				theController.setDirtyFlag(true);
			}
			else {
				listInclusionComboBox.setSelectedIndex(ActionItemManager.inclusionFactorAll);
				aiM.setInclusionFactor(ActionItemManager.inclusionFactorAll);    			
			}
			loadComboBoxData(aiM.getActionItemNames(), aiM.getActionItemIndex(aiM.getCurrentActionItemName()));
			if (aiListComboBox.getSelectedIndex() == noItemSelected) {
				clearAI();
				loadScreen();
			}
			updatingGUI = false;
		}
	}

	/**
	 * A "Delete an Action Item" button event has occurred.  Process it.
	 * 
	 * Added for Della10
	 */
	private void deleteActionItem(ActionEvent evt){
		int x = JOptionPane.showConfirmDialog(this,
				"Click \"Yes\" to delete the action item,\n\"No\" to retain it.",
				"Delete the Current Action Item?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.YES_NO_OPTION);
		if (x == 0) {
			aiM.deleteCurrentActionItem();
			clearAI();
			loadScreen();
		}
	}

	/**
	 * In Della, an empty item is actual the same as no item selected, so this private routine supports this fact.
	 * 
	 * Added for Della05
	 */
	private String deNull(String str){
		if (str == null) return "";
		return str;
	}

	/**
	 * Any number of events has occurred that could change the display.  See if the current edit values still
	 * match the current action item.  If so, then no warning is needed.  If not, then given a warning (red
	 * text) that informs the user that there are edits to the action item that have not been saved.
	 * 
	 */
	private void checkForUnsavedUpdates(){
		if (updatingGUI) return;
		if (nameTextField.getText().equals(aiM.getCurrentActionItem().getActionItemName()) &&
				descriptionTextArea.getText().equals(aiM.getCurrentActionItem().getDescription()) &&
				resolutionTextArea.getText().equals(aiM.getCurrentActionItem().getResolution()) && 
				dueDateTextField.getText().equals(aiM.getCurrentActionItem().getDueDate()!=null?dateFormat.format(aiM.getCurrentActionItem().getDueDate()):"") &&
				(	(statusComboBox.getSelectedIndex() == 0 && aiM.getCurrentActionItem().getStatus().equals("")) ||
						(statusComboBox.getSelectedIndex() == 0 && aiM.getCurrentActionItem().getStatus().equals("Open")) ||
						(statusComboBox.getSelectedIndex() == 1 && aiM.getCurrentActionItem().getStatus().equals("Closed"))
				) &&	// Addedd for Della05
				aiM.getCurrentAssignedMember().equals(deNull((String)selectMemberComboBox.getSelectedItem())) &&
				aiM.getCurrentAssignedTeam().equals(deNull((String)selectTeamComboBox.getSelectedItem()))			// Added for Della09
		){
			unsavedChangesLabel.setText("");
			aiM.setEditChangesPending(false);
		}
		else {
			unsavedChangesLabel.setText("There are unsaved changes!");
			aiM.setEditChangesPending(true);
		}	
	}
}
