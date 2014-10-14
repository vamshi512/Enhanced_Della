package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.ActionItemManager;
import model.ElementList;
import control.Controller;

/**
 * <p>
 * Title: TeamScreen
 * </p>
 *
 * <p>
  * Description: The Della Team Screen code
* </p>
 *
 * <p>
 * Copyright: Copyright © 2007
 * </p>
 *
 * @author Lynn Robert Carter
 * @version 1.08
 * Many thanks to Harry Sameshima for his original work.
 */
public class TeamScreen extends JPanel {
	//---------------------------------------------------------------------------------------------------------------------
	// Team Screen constants

	public static final int noItemSelected = -1;	// Added for Della06

	//---------------------------------------------------------------------------------------------------------------------
	// Team Screen attributes

	// Added for Della06 (start)
	Boolean updatingGUI = false;

	private Controller theController = null;
	private ActionItemManager aiM = null;
	// Added for Della06 (end)

	//---------------------------------------------------------------------------------------------------------------------
	// Team Screen GUI elements
	JLabel teamLabel = new JLabel();

	// Added for Della06 (start)
	JLabel nameLabel = new JLabel();
	JTextField nameTextField = new JTextField();

	JLabel guidanceR1Label = new JLabel();
	JLabel guidanceR2Label = new JLabel();
	JLabel guidanceR3Label = new JLabel();
	JLabel guidanceR4Label = new JLabel();
	JLabel guidanceR5Label = new JLabel();
	JLabel guidanceR6Label = new JLabel();
	JLabel guidanceR7Label = new JLabel();

	// Get current classloader 
	ClassLoader cl = this.getClass().getClassLoader(); 
	// Create icons 
	Icon icon = new ImageIcon(cl.getResource("TrashCan.gif"));
	JLabel trashCanLabel = new JLabel(icon);

	JButton addTeamButton = new JButton();
	ActionListener addTeamButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { addTeam(ae); }
	};
	JButton removeTeamButton = new JButton();
	ActionListener removeTeamButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { removeTeam(ae); }
	};

	JLabel teamListLabel = new JLabel();
	JList teamSelectList = new JList(new DefaultListModel());
	JScrollPane scrollableTeamListPane = new JScrollPane(teamSelectList, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	ListSelectionListener listSelectionListner = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent listSelectionEvent) { selectTeam(); }
	};
	// Added for Della06 (end)

	// Added for Della08
	JLabel guidanceR8Label = new JLabel();
	JLabel guidanceR9Label = new JLabel();
	JLabel guidanceR10Label = new JLabel();
	JLabel guidanceR11Label = new JLabel();
	JLabel guidanceR12Label = new JLabel();
	JLabel guidanceR13Label = new JLabel();
	JLabel guidanceR14Label = new JLabel();
	JLabel guidanceR15Label = new JLabel();
	JLabel guidanceR16Label = new JLabel();
	JLabel guidanceR17Label = new JLabel();

	JLabel unassociatedMembersListLabel = new JLabel();
	JLabel unassociatedMembersTeamNameLabel = new JLabel();
	JList unassociatedMembersSelectList = new JList(new DefaultListModel());
	JScrollPane scrollableUnassociatedMembersListPane = new JScrollPane(unassociatedMembersSelectList, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	ListSelectionListener unassociatedMembersListSelectionListner = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent listSelectionEvent) { selectUnassociatedMember(); }
	};

	JButton addMemberAssociationButton = new JButton();
	ActionListener addMemberAssociationButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { addMemberAssociation(ae); }
	};
	JButton removeMemberAssociationButton = new JButton();
	ActionListener removeMemberAssociationButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { removeMemberAssociation(ae); }
	};

	JLabel associatedMembersListLabel = new JLabel();
	JLabel associatedMembersTeamNameLabel = new JLabel();
	JList associatedMembersSelectList = new JList(new DefaultListModel());
	JScrollPane scrollableAssociatedMembersListPane = new JScrollPane(associatedMembersSelectList, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	ListSelectionListener associatedMembersListSelectionListner = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent listSelectionEvent) { selectAssociatedMember(); }
	};
	// Added for Della08 (end)
	//---------------------------------------------------------------------------------------------------------------------
	public TeamScreen() {
		// Use a modified singleton pattern to access the application's state; Added for Della06
		theController = Controller.getInstance();
		aiM = theController.getActionItemManager();

		// Set up all of the Graphical User Interface elements and place them on the screen
		guiInit();

		// Initialize the screen; Added for Della06
		loadScreen();  
	}

	/**
	 * Initialize each graphic element, position it on the screen, and add it to the layout.
	 * 
	 */
	private void guiInit() {
		// Set all of the graphical elements in this screen by adding them to the layout
		this.setLayout(null);

		teamLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		teamLabel.setBorder(BorderFactory.createEtchedBorder());
		teamLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamLabel.setText("Teams");
		teamLabel.setBounds(new Rectangle(0, 0, 657, 20));

		// Added for Della06 (start)
		nameLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		nameLabel.setText("Name of a new team");
		nameLabel.setBounds(new Rectangle(5, 23, 240, 15));
		nameTextField.setText("");
		nameTextField.setBounds(new Rectangle(5, 40, 190, 20));

		guidanceR1Label.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		guidanceR1Label.setText("To add a name to the list:");
		guidanceR1Label.setBounds(new Rectangle(5, 70, 240, 15));
		guidanceR2Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR2Label.setText("1. Click on the box above.");
		guidanceR2Label.setBounds(new Rectangle(5, 85, 240, 15));
		guidanceR3Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR3Label.setText("2. Type the name.");
		guidanceR3Label.setBounds(new Rectangle(5, 100, 240, 15));
		guidanceR4Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR4Label.setText("3. Click the \"Add to List\" button.");
		guidanceR4Label.setBounds(new Rectangle(5, 115, 240, 15));
		guidanceR5Label.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		guidanceR5Label.setText("To remove a name from the list:");
		guidanceR5Label.setBounds(new Rectangle(5, 150, 240, 15));
		guidanceR6Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR6Label.setText("1. Click on the name to remove.");
		guidanceR6Label.setBounds(new Rectangle(5, 165, 240, 15));
		guidanceR7Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR7Label.setText("2. Click on \"Remove from List\" button.");
		guidanceR7Label.setBounds(new Rectangle(5, 180, 240, 15));

		trashCanLabel.setBounds(new Rectangle(185, 70, 50, 83));

		addTeamButton.setFont(new Font("Dialog", Font.BOLD, 12));
		addTeamButton.setBounds(new Rectangle(243, 35, 170, 35));
		addTeamButton.setText("Add to List ->");
		addTeamButton.addActionListener(addTeamButtonActionListner);
		removeTeamButton.setFont(new Font("Dialog", Font.BOLD, 12));
		removeTeamButton.setBounds(new Rectangle(243, 95, 170, 35));
		removeTeamButton.setText("<- Remove from List");
		removeTeamButton.addActionListener(removeTeamButtonActionListner);

		teamListLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		teamListLabel.setText("Teams known by Della");
		teamListLabel.setBounds(new Rectangle(460, 23, 200, 15));
		teamSelectList.addListSelectionListener(listSelectionListner);
		scrollableTeamListPane.setBounds(new Rectangle(460, 40, 180, 155));
		// Added for Della06 (end)

		// Added for Della08 (start)
		guidanceR8Label.setFont(new java.awt.Font("Dialog", Font.BOLD, 10));
		guidanceR8Label.setText("To add a member association for a team:");
		guidanceR8Label.setBounds(new Rectangle(5, 205, 240, 15));
		guidanceR9Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR9Label.setText("1. Click on the name of the team above right.");
		guidanceR9Label.setBounds(new Rectangle(5, 220, 240, 15));
		guidanceR10Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR10Label.setText("2. Click on a member name in the list below.");
		guidanceR10Label.setBounds(new Rectangle(5, 235, 240, 15));
		guidanceR11Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR11Label.setText("3. Click on \"Add association\" button.");
		guidanceR11Label.setBounds(new Rectangle(5, 250, 240, 15));
		guidanceR12Label.setFont(new java.awt.Font("Dialog", Font.BOLD, 10));
		guidanceR12Label.setText("To remove a member association for a team:");
		guidanceR12Label.setBounds(new Rectangle(400, 205, 240, 15));
		guidanceR13Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR13Label.setText("1. Click on the name of the team above.");
		guidanceR13Label.setBounds(new Rectangle(400, 220, 240, 15));
		guidanceR14Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR14Label.setText("2. Click on a member name in the list below.");
		guidanceR14Label.setBounds(new Rectangle(400, 235, 240, 15));
		guidanceR15Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR15Label.setText("3. Click on \"Remove association\" button.");
		guidanceR15Label.setBounds(new Rectangle(400, 250, 240, 15));
		guidanceR16Label.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		guidanceR16Label.setText("Click on an team\'s name");
		guidanceR16Label.setBounds(new Rectangle(255, 150, 240, 15));
		guidanceR17Label.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		guidanceR17Label.setText("to see member associations.");
		guidanceR17Label.setBounds(new Rectangle(255, 165, 240, 15));

		unassociatedMembersListLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		unassociatedMembersListLabel.setText("Available members for team");
		unassociatedMembersListLabel.setBounds(new Rectangle(10, 275, 200, 15));
		unassociatedMembersTeamNameLabel.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		unassociatedMembersTeamNameLabel.setText("");
		unassociatedMembersTeamNameLabel.setBounds(new Rectangle(10, 290, 200, 15));
		unassociatedMembersSelectList.addListSelectionListener(unassociatedMembersListSelectionListner);
		scrollableUnassociatedMembersListPane.setBounds(new Rectangle(10, 305, 180, 140));

		addMemberAssociationButton.setFont(new Font("Dialog", Font.BOLD, 12));
		addMemberAssociationButton.setBounds(new Rectangle(240, 330, 176, 35));
		addMemberAssociationButton.setText("Add association ->");
		addMemberAssociationButton.addActionListener(addMemberAssociationButtonActionListner);
		removeMemberAssociationButton.setFont(new Font("Dialog", Font.BOLD, 12));
		removeMemberAssociationButton.setBounds(new Rectangle(240, 385, 176, 35));
		removeMemberAssociationButton.setText("<- Remove association");
		removeMemberAssociationButton.addActionListener(removeMemberAssociationButtonActionListner);

		associatedMembersListLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		associatedMembersListLabel.setText("Current members for team");
		associatedMembersListLabel.setBounds(new Rectangle(460, 275, 200, 15));
		associatedMembersTeamNameLabel.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		associatedMembersTeamNameLabel.setText("");
		associatedMembersTeamNameLabel.setBounds(new Rectangle(460, 290, 200, 15));
		associatedMembersSelectList.addListSelectionListener(associatedMembersListSelectionListner);
		scrollableAssociatedMembersListPane.setBounds(new Rectangle(460, 305, 180, 140));
		// Added for Della08 (end)

		//----------------------------------------------------------------------------
		// Add the objects to the layout
		this.add(teamLabel);

		// Added for Della06 (start)
		this.add(nameLabel);
		this.add(nameTextField);

		this.add(guidanceR1Label);
		this.add(guidanceR2Label);
		this.add(guidanceR3Label);
		this.add(guidanceR4Label);
		this.add(guidanceR5Label);
		this.add(guidanceR6Label);
		this.add(guidanceR7Label);

		this.add(trashCanLabel);

		this.add(addTeamButton);
		this.add(removeTeamButton);

		this.add(teamListLabel);
		this.add(scrollableTeamListPane);
		// Added for Della06 (end)

		// Added for Della08 (start)
		this.add(guidanceR8Label);
		this.add(guidanceR9Label);
		this.add(guidanceR10Label);
		this.add(guidanceR11Label);
		this.add(guidanceR12Label);
		this.add(guidanceR13Label);
		this.add(guidanceR14Label);
		this.add(guidanceR15Label);
		this.add(guidanceR16Label);
		this.add(guidanceR17Label);

		this.add(unassociatedMembersListLabel);
		this.add(unassociatedMembersTeamNameLabel);
		this.add(scrollableUnassociatedMembersListPane);

		this.add(addMemberAssociationButton);
		this.add(removeMemberAssociationButton);

		this.add(associatedMembersListLabel);
		this.add(associatedMembersTeamNameLabel);
		this.add(scrollableAssociatedMembersListPane);
		// Added for Della08 (end)
	}

	/**
	 * Process a "Add to List" button click request
	 * Add the new name, if valid, to the team list
	 * 
	 * @param e ActionEvent
	 * 
	 * Added for Della06
	 */
	private void addTeam(ActionEvent e){
		ElementList teamList = aiM.getTeamList();

		try {
			String newName = nameTextField.getText();
			loadScreenAndLists(teamList.addElement(newName));
			nameTextField.setText("");		// If name was accepted, blank our the input field
			teamList.setUnaddedName("");	// and reset the persistent input field copy
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		theController.setDirtyFlag(true);
	}

	/**
	 * Process a "Remove from List" button click request
	 * Remove the selected name from the member list
	 * 
	 * @param e ActionEvent
	 * 
	 * Added for Della06
	 */
	private void removeTeam(ActionEvent e){
		int selectedIndex = teamSelectList.getSelectedIndex();
		if (selectedIndex == noItemSelected) {
			JOptionPane.showMessageDialog(this, "No team was selected!",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		else {
			String teamName = aiM.getTeamList().getName(selectedIndex);
			aiM.removeAssignedTeam(teamName);
			aiM.getMemberList().removeAssociatedName(teamName);	// Added for Della08
			nameTextField.setText(teamName);
			loadScreenAndLists(noItemSelected);
			theController.setDirtyFlag(true); 
		}
	}

	/**
	 * Process a team select list selection action
	 * 
	 * Added for Della06
	 */
	private void selectTeam(){
		if (updatingGUI == false) {
			ElementList teamList = aiM.getTeamList();
			int selectedIndex = teamSelectList.getSelectedIndex();
			teamList.setCurrentSelectedElementIndex(selectedIndex);
			establishUnassociatedMemberSelectList(teamList.getName(selectedIndex));	// Added for Della08
			establishAssociatedMemberSelectList(teamList.getName(selectedIndex));	// Added for Della08
			theController.setDirtyFlag(true); 
		}
	}

	/**
	 * When a shut down occurs, this routine is called to cause the UI state of the text input box
	 * to be saved to the persistent store
	 * 
	 * Added for Della06
	 */
	public void saveScreenState() {
		ElementList teamList = aiM.getTeamList();
		teamList.setUnaddedName(nameTextField.getText());   	
	}

	/**
	 * When a navigation button click requires this screen to be activated, this routine
	 * is called to load the screen and re-establish the perishable fields
	 * 
	 * Added for Della06
	 */
	public void loadScreen() {   
		ElementList teamList = aiM.getTeamList();
		loadScreenAndLists(teamList.getCurrentSelectedElementIndex());
		nameTextField.setText(teamList.getUnaddedName());
	}

	/**
	 * Shared routine to load the screen and the various select lists
	 * 
	 * Added for Della06
	 */
	private void loadScreenAndLists(int selectedIndex) {
		updatingGUI = true;
		// Set the flag so that no select events are processed by these actions
		ElementList teamList = aiM.getTeamList();
		// Fetch the list of teams to populate the select list
		((DefaultListModel)teamSelectList.getModel()).removeAllElements();
		// Reset the select list so it contains no elements
		int listSize = teamList.getListSize();
		// Fetch the size of the list of teams and use this to iterate over all members
		for (int i=0; i<listSize; i++)
			((DefaultListModel)teamSelectList.getModel()).addElement(teamList.getName(i));
		// Add each team to the select list
		if (selectedIndex == noItemSelected) {	// See if a team is selected
			// If not, make sure the team list has no element selected
			teamSelectList.clearSelection();
			// If no team is selected the unassociated list must be empty; Added for Della08
			establishUnassociatedMemberSelectList("");
			// Same thing for the associated list; Added for Della08
			establishAssociatedMemberSelectList("");
		}
		else {	// A team was selected
			teamSelectList.setSelectedIndex(selectedIndex);			// Select that team
			// Use that team to establish the unassociated list of members; Added for Della08
			establishUnassociatedMemberSelectList(teamList.getName(selectedIndex));
			// Same thing for the associated list; Added for Della08
			establishAssociatedMemberSelectList(teamList.getName(selectedIndex));
		}
		teamList.setCurrentSelectedElementIndex(selectedIndex);			
		updatingGUI = false;
	}

	/**
	 * When the "Add association" button is clicked, this routine is called to process it
	 * 
	 * Added for Della08
	 */
	private void addMemberAssociation(ActionEvent e){
		if (unassociatedMembersSelectList.getSelectedIndex()>noItemSelected) {
			String selectedMember = (String)unassociatedMembersSelectList.getSelectedValue();
			String selectedTeam = (String)teamSelectList.getSelectedValue();
			int numMemberTeams = aiM.getNumTeamAffiliations(selectedMember);
			int numTeamMembers = aiM.getNumMemberAssociations(selectedTeam);
			// Verify that there is is room for this addition!			
			if ((numMemberTeams < 10) && (numTeamMembers < 10)) {
				// Add a team affiliation to this member
				aiM.addTeamAffiliation(selectedMember,selectedTeam);
				// Add a member association to this team
				aiM.addMemberAssociation(selectedTeam,selectedMember);
				// Set the newly added member as selected in the list of members for this team
				aiM.setSelectedAssociatedMember(selectedTeam, selectedMember);
				// Have no team in the unassociated list selected
				aiM.setSelectedUnassociatedMember(selectedTeam, "");
				// Set the newly added team as selected in the list of teams for this member
				aiM.setSelectedAffiliatedTeam(selectedMember, selectedTeam);
				// Have no team in the unaffiliated list selected
				aiM.setSelectedUnaffiliatedTeam(selectedMember, "");
			}
			else {
				if (numMemberTeams>9)
					JOptionPane.showMessageDialog(this, 
							"A member may not be on more than 10 teams!",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(this, 
							"A team may not have more than 10 members!",
							"Error",
							JOptionPane.ERROR_MESSAGE);					
				return;
			}
			// Rebuild the affiliated team list for this member and display it
			establishAssociatedMemberSelectList(selectedTeam);
			// Rebuild the unaffiliated team list for this member and display it
			establishUnassociatedMemberSelectList(selectedTeam);
			theController.setDirtyFlag(true); 
		}
		else
			JOptionPane.showMessageDialog(this, "No member was selected!",
					"Error",
					JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * When the "Remove association" button is clicked, this routine is called to process it
	 * 
	 * Added for Della08
	 */
	private void removeMemberAssociation(ActionEvent e){
		if (associatedMembersSelectList.getSelectedIndex()>noItemSelected) {
			String selectedMember = (String)associatedMembersSelectList.getSelectedValue();
			String selectedTeam = (String)teamSelectList.getSelectedValue();
			aiM.removeMemberAssociation(selectedTeam,selectedMember);
			// Remove this member from this team's list of members
			aiM.removeTeamAffiliation(selectedMember,selectedTeam);
			// Remove this team from this member's list of teams
			aiM.setSelectedUnassociatedMember(selectedTeam, selectedMember);
			// Have this member be selected in the list of unassociated members for this team
			aiM.setSelectedAssociatedMember(selectedTeam, "");
			// Have no member be selected in the list of associated members for this team
			aiM.setSelectedUnaffiliatedTeam(selectedMember, selectedTeam);
			// Have this team be selected in the list of unaffiliated teams for this member
			aiM.setSelectedAffiliatedTeam(selectedMember, "");
			// Have no team be selected in the list of affiliated teams for this member
			establishAssociatedMemberSelectList(selectedTeam);
			// Rebuild the list of associated members for this team and display it
			establishUnassociatedMemberSelectList(selectedTeam);
			// Rebuild the list of unassociated members for this team and display it
			theController.setDirtyFlag(true); 
		}
		else
			JOptionPane.showMessageDialog(this, "No member was selected!",
					"Error",
					JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * When an associated member is clicked, this routine is called to process it
	 * 
	 * Added for Della08
	 */
	private void selectAssociatedMember(){
		if (updatingGUI != true) {
			String selectedMember = (String)associatedMembersSelectList.getSelectedValue();
			String selectedTeam = (String)teamSelectList.getSelectedValue();
			aiM.setSelectedAssociatedMember(selectedTeam, selectedMember);
			// Select the associated member that was clicked
			aiM.setSelectedUnassociatedMember(selectedTeam, "");
			// Clear the unassociated member select list... both cannot have selected items
			establishUnassociatedMemberSelectList(selectedTeam);
			// Cause the unassociated select list to be repainted incase a deselect has occurred
			theController.setDirtyFlag(true); 
		}
	}

	/**
	 * When an unassociated member is clicked, this routine is called to process it
	 * 
	 * Added for Della08
	 */
	private void selectUnassociatedMember(){
		if (updatingGUI != true) {
			String selectedMember = (String)unassociatedMembersSelectList.getSelectedValue();
			String selectedTeam = (String)teamSelectList.getSelectedValue();
			aiM.setSelectedUnassociatedMember(selectedTeam, selectedMember);
			// Select the unasscoaited member that was clicked
			aiM.setSelectedAssociatedMember(selectedTeam, "");
			// Clear the associated mdember select list... both cannot have a selected items
			establishAssociatedMemberSelectList(selectedTeam);
			// Cause the associated select list to be repainted incase a deselect has occurred
			theController.setDirtyFlag(true); 
		}
	}

	/**
	 * This routine establishes a select list of all of the unassocuated members for a specific team and finds the selected one
	 * 
	 * Added for Della08
	 */
	private void establishUnassociatedMemberSelectList(String teamName){
		updatingGUI = true;
		unassociatedMembersTeamNameLabel.setText(teamName);
		((DefaultListModel)unassociatedMembersSelectList.getModel()).removeAllElements();
		if (teamName.length()>0){	// If the team name is not empty, then use that team's unassociated member names
			String[] UnassociatedMember = aiM.getUnassociatedMembers(teamName);
			int numUnassociatedMembers = UnassociatedMember.length;
			String selectedUnassociatedMember = aiM.getSelectedUnassociatedMember(teamName);
			if (selectedUnassociatedMember == null) selectedUnassociatedMember = "";
			int selectedIndex = noItemSelected;
			for (int ndx=0; ndx < numUnassociatedMembers; ndx++) {	// Add all the names to the select list
				((DefaultListModel)unassociatedMembersSelectList.getModel()).addElement(UnassociatedMember[ndx]);
				if (selectedUnassociatedMember.compareToIgnoreCase(UnassociatedMember[ndx]) == 0) selectedIndex = ndx;
				// After adding each name, see if that name is the selected one.  If so, remember its index
			}
			unassociatedMembersSelectList.setSelectedIndex(selectedIndex);
			// After the select list is build, specify which one was the selected element
		}
		updatingGUI = false;
	}

	/**
	 * This routine establishes a select list of all of the associated members for a specific team and finds the selected one
	 * 
	 * Added for Della08
	 */
	private void establishAssociatedMemberSelectList(String teamName){
		updatingGUI = true;
		associatedMembersTeamNameLabel.setText(teamName);
		((DefaultListModel)associatedMembersSelectList.getModel()).removeAllElements();
		if (teamName.length()>0){	// If the team name is not empty, then use that team's associated member names
			String[] AssociatedMember = aiM.getAssociatedMembers(teamName);
			int numAssociatedMembers = AssociatedMember.length;
			String selectedAssociatedMember = aiM.getSelectedAssociatedMember(teamName);
			if (selectedAssociatedMember == null) selectedAssociatedMember = "";
			int selectedIndex = noItemSelected;
			for (int ndx=0; ndx < numAssociatedMembers; ndx++) {	// Add all the names to the select list
				((DefaultListModel)associatedMembersSelectList.getModel()).addElement(AssociatedMember[ndx]);
				if (selectedAssociatedMember.compareToIgnoreCase(AssociatedMember[ndx]) == 0) selectedIndex = ndx;
				// After adding each name, see if that name is the selected one.  If so, remember its index
			}
			associatedMembersSelectList.setSelectedIndex(selectedIndex);
			// After the select list is build, specify which one was the selected element
		}
		updatingGUI = false;
	}
}
