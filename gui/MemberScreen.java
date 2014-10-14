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
 * Title: MemberScreen
 * </p>
 *
 * <p>
 * Description: The Della Member Screen code
 * </p>
 *
 * <p>
 * Copyright: Copyright © 2007
 * </p>
 *
 * @author Lynn Robert Carter
 * @version 1.07
 * Many thanks to Harry Sameshima for his original work.
*/
public class MemberScreen extends JPanel {
	//---------------------------------------------------------------------------------------------------------------------
	// Member Screen constants

	private static final int noItemSelected = -1;	// Added for Della04

	//---------------------------------------------------------------------------------------------------------------------
	// Member Screen attributes

	// Added for Della04
	private Boolean updatingGUI = false;

	private Controller theController = null;
	private ActionItemManager aiM = null;

	//---------------------------------------------------------------------------------------------------------------------
	// Member Screen GUI elements
	JLabel memberLabel = new JLabel();

	// Added for Della04 (start)
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

	JButton addMemberButton = new JButton();
	ActionListener addMemberButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { addMember(ae); }
	};
	JButton removeMemberButton = new JButton();
	ActionListener removeMemberButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { removeMember(ae); }
	};

	JLabel memberListLabel = new JLabel();
	JList memberSelectList = new JList(new DefaultListModel());
	JScrollPane scrollableMemberListPane = new JScrollPane(memberSelectList, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	ListSelectionListener listSelectionListner = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent listSelectionEvent) { selectMember(); }
	};
	// Added for Della04 (end)


	// Added for Della07 (start)
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

	JLabel unaffiliatedTeamsListLabel = new JLabel();
	JLabel unaffiliatedTeamsMemberNameLabel = new JLabel();
	JList unaffiliatedTeamsSelectList = new JList(new DefaultListModel());
	JScrollPane scrollableUnaffiliatedTeamsListPane = new JScrollPane(unaffiliatedTeamsSelectList, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	ListSelectionListener unaffiliatedTeamsListSelectionListner = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent listSelectionEvent) { selectUnaffiliatedTeam(); }
	};

	JButton addTeamAffiliationButton = new JButton();
	ActionListener addTeamAffiliationButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { addTeamAffiliation(ae); }
	};
	JButton removeTeamAffiliationButton = new JButton();
	ActionListener removeTeamAffilitationButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { removeTeamAffiliation(ae); }
	};

	JLabel affiliatedTeamsListLabel = new JLabel();
	JLabel affiliatedTeamsMemberNameLabel = new JLabel();
	JList affiliatedTeamsSelectList = new JList(new DefaultListModel());
	JScrollPane scrollableAffiliatedTeamsListPane = new JScrollPane(affiliatedTeamsSelectList, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	ListSelectionListener affiliatedTeamsListSelectionListner = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent listSelectionEvent) { selectAffiliatedTeam(); }
	};
	// Added for Della07 (end)
	//---------------------------------------------------------------------------------------------------------------------

	/**
	 * The MemberScreen class constructor.
	 * 
	 */
	public MemberScreen() {
		// Use a modified singleton pattern to access the application's state; Added for Della04
		theController = Controller.getInstance();
		aiM = theController.getActionItemManager();

		// Set up all of the Graphical User Interface elements and place them on the screen
		guiInit();

		// Initialize the screen; Added for Della04
		loadScreen();  
	}

	/**
	 * Initialize each graphic element, position it on the screen, and add it to the layout.
	 * 
	 */
	private void guiInit() {
		this.setLayout(null);
		memberLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		memberLabel.setBorder(BorderFactory.createEtchedBorder());
		memberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		memberLabel.setText("Members");
		memberLabel.setBounds(new Rectangle(0, 0, 657, 20));

		// Added for Della04 (start)
		nameLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		nameLabel.setText("Name of someone new (Last, First Middle)");
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

		addMemberButton.setFont(new Font("Dialog", Font.BOLD, 12));
		addMemberButton.setBounds(new Rectangle(243, 35, 170, 35));
		addMemberButton.setText("Add to List ->");
		addMemberButton.addActionListener(addMemberButtonActionListner);
		removeMemberButton.setFont(new Font("Dialog", Font.BOLD, 12));
		removeMemberButton.setBounds(new Rectangle(243, 95, 170, 35));
		removeMemberButton.setText("<- Remove from List");
		removeMemberButton.addActionListener(removeMemberButtonActionListner);

		memberListLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		memberListLabel.setText("Individuals known by Della");
		memberListLabel.setBounds(new Rectangle(460, 23, 200, 15));
		memberSelectList.addListSelectionListener(listSelectionListner);
		scrollableMemberListPane.setBounds(new Rectangle(460, 40, 180, 155));
		// Added for Della04 (end)

		// Added for Della07 (start)
		guidanceR8Label.setFont(new java.awt.Font("Dialog", Font.BOLD, 10));
		guidanceR8Label.setText("To add a team affiliation for an individual:");
		guidanceR8Label.setBounds(new Rectangle(5, 205, 240, 15));
		guidanceR9Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR9Label.setText("1. Click on the name of the indivudal above right.");
		guidanceR9Label.setBounds(new Rectangle(5, 220, 240, 15));
		guidanceR10Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR10Label.setText("2. Click on a team name in the list below.");
		guidanceR10Label.setBounds(new Rectangle(5, 235, 240, 15));
		guidanceR11Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR11Label.setText("3. Click on \"Add affiliation\" button.");
		guidanceR11Label.setBounds(new Rectangle(5, 250, 240, 15));
		guidanceR12Label.setFont(new java.awt.Font("Dialog", Font.BOLD, 10));
		guidanceR12Label.setText("To remove a team affiliation for an individual:");
		guidanceR12Label.setBounds(new Rectangle(400, 205, 240, 15));
		guidanceR13Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR13Label.setText("1. Click on the name of the indivudal above.");
		guidanceR13Label.setBounds(new Rectangle(400, 220, 240, 15));
		guidanceR14Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR14Label.setText("2. Click on a team name in the list below.");
		guidanceR14Label.setBounds(new Rectangle(400, 235, 240, 15));
		guidanceR15Label.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		guidanceR15Label.setText("3. Click on \"Remove affiliation\" button.");
		guidanceR15Label.setBounds(new Rectangle(400, 250, 240, 15));
		guidanceR16Label.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		guidanceR16Label.setText("Click on an individual\'s name");
		guidanceR16Label.setBounds(new Rectangle(255, 150, 240, 15));
		guidanceR17Label.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		guidanceR17Label.setText("to see team affiliations.");
		guidanceR17Label.setBounds(new Rectangle(255, 165, 240, 15));

		unaffiliatedTeamsListLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		unaffiliatedTeamsListLabel.setText("Available teams for");
		unaffiliatedTeamsListLabel.setBounds(new Rectangle(10, 275, 200, 15));
		unaffiliatedTeamsMemberNameLabel.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		unaffiliatedTeamsMemberNameLabel.setText("");
		unaffiliatedTeamsMemberNameLabel.setBounds(new Rectangle(10, 290, 200, 15));
		unaffiliatedTeamsSelectList.addListSelectionListener(unaffiliatedTeamsListSelectionListner);
		scrollableUnaffiliatedTeamsListPane.setBounds(new Rectangle(10, 305, 180, 140));

		addTeamAffiliationButton.setFont(new Font("Dialog", Font.BOLD, 12));
		addTeamAffiliationButton.setBounds(new Rectangle(240, 330, 176, 35));
		addTeamAffiliationButton.setText("Add affiliation ->");
		addTeamAffiliationButton.addActionListener(addTeamAffiliationButtonActionListner);
		removeTeamAffiliationButton.setFont(new Font("Dialog", Font.BOLD, 12));
		removeTeamAffiliationButton.setBounds(new Rectangle(240, 385, 176, 35));
		removeTeamAffiliationButton.setText("<- Remove affiliation");
		removeTeamAffiliationButton.addActionListener(removeTeamAffilitationButtonActionListner);

		affiliatedTeamsListLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		affiliatedTeamsListLabel.setText("Current teams for");
		affiliatedTeamsListLabel.setBounds(new Rectangle(460, 275, 200, 15));
		affiliatedTeamsMemberNameLabel.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		affiliatedTeamsMemberNameLabel.setText("");
		affiliatedTeamsMemberNameLabel.setBounds(new Rectangle(460, 290, 200, 15));
		affiliatedTeamsSelectList.addListSelectionListener(affiliatedTeamsListSelectionListner);
		scrollableAffiliatedTeamsListPane.setBounds(new Rectangle(460, 305, 180, 140));
		// Added for Della07 (end)
		
		//----------------------------------------------------------------------------
		// Add the objects to the layout
		this.add(memberLabel);

		// Added for Della04 (start)
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

		this.add(addMemberButton);
		this.add(removeMemberButton);

		this.add(memberListLabel);
		this.add(scrollableMemberListPane);
		// Added for Della04 (end)

		// Added for Della07 (start)
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

		this.add(unaffiliatedTeamsListLabel);
		this.add(unaffiliatedTeamsMemberNameLabel);
		this.add(scrollableUnaffiliatedTeamsListPane);

		this.add(addTeamAffiliationButton);
		this.add(removeTeamAffiliationButton);

		this.add(affiliatedTeamsListLabel);
		this.add(affiliatedTeamsMemberNameLabel);
		this.add(scrollableAffiliatedTeamsListPane);
		// Added for Della07 (end)
	}

	/**
	 * Process a "Add to List" button click request
	 * Add the new name, if valid, to the member list
	 * 
	 * @param e ActionEvent
	 * 
	 * Added for Della04
	 */
	private void addMember(ActionEvent e){
		ElementList memberList = aiM.getMemberList();

		try {
			String newName = nameTextField.getText();
			loadScreenAndLists(memberList.addElement(newName));
			nameTextField.setText("");		// If name was accepted, blank out the input field
			memberList.setUnaddedName("");	// and reset the persistent input field copy
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
	 * Added for Della04
	 */
	private void removeMember(ActionEvent e){
		int selectedIndex = memberSelectList.getSelectedIndex();
		if (selectedIndex == noItemSelected) {
			JOptionPane.showMessageDialog(this, "No member was selected!",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		else {
			String memberName = aiM.getMemberList().getName(selectedIndex);
			aiM.removeAssignedMember(memberName);
			aiM.getTeamList().removeAssociatedName(memberName);	// Added for Della07
			nameTextField.setText(memberName);
			loadScreenAndLists(noItemSelected);
			theController.setDirtyFlag(true); 
		}
	}

	/**
	 * Process a member select list selection action
	 * 
	 * Added for Della04
	 */
	private void selectMember(){
		if (updatingGUI == false) {
			ElementList memberList = aiM.getMemberList();
			int selectedIndex = memberSelectList.getSelectedIndex();
			memberList.setCurrentSelectedElementIndex(selectedIndex);
			establishUnaffiliatedTeamSelectList(memberList.getName(selectedIndex));	// Added for Della07
			establishAffiliatedTeamSelectList(memberList.getName(selectedIndex));	// Added for Della07
			theController.setDirtyFlag(true); 
		}
	}

	/**
	 * When a shut down occurs or transfer to some other screen occurs, this routine is called 
	 * to cause the UI state of the text input to be saved to the persistent store
	 * 
	 * Added for Della04
	 */
	public void saveScreenState() {
		ElementList memberList = aiM.getMemberList();
		memberList.setUnaddedName(nameTextField.getText());   	
	}

	/**
	 * When a navigation button click requires this screen to be activated, this routine
	 * is called to load the screen and re-establish the perishable fields
	 * 
	 * Added for Della04
	 */
	public void loadScreen() {   
		ElementList memberList = aiM.getMemberList();
		loadScreenAndLists(memberList.getCurrentSelectedElementIndex());
		nameTextField.setText(memberList.getUnaddedName());
	}

	/**
	 * This shared private routine does the heavy lifting of actually setting up the GUI for this screen
	 * 
	 * Added for Della04
	 */
	private void loadScreenAndLists(int selectedIndex) {
		updatingGUI = true;
		// Set the flag so that no select events are processed by these actions
		ElementList memberList = aiM.getMemberList();
		// Fetch the list of members to populate the select list
		((DefaultListModel)memberSelectList.getModel()).removeAllElements();
		// Reset the select list so it contains no elements
		int listSize = memberList.getListSize();
		// Fetch the size of the list of members and use this to iterate over all members
		for (int i=0; i<listSize; i++)
			((DefaultListModel)memberSelectList.getModel()).addElement(memberList.getName(i));
		// Add each member to the select list
		if (selectedIndex == noItemSelected) {	// See if a member is selected
			// If not, make sure the member list has no element selected
			memberSelectList.clearSelection();
			// If no member is selected the unaffiliated list must be empty; Added for Della07
			establishUnaffiliatedTeamSelectList("");
			// Same thing for the affiliated list; Added for Della07
			establishAffiliatedTeamSelectList("");
		}
		else {	// A member was selected
			// Select that member
			memberSelectList.setSelectedIndex(selectedIndex);
			// Use that member to establish the unaffiliated list of teams; Added for Della07
			establishUnaffiliatedTeamSelectList(memberList.getName(selectedIndex));
			// Same thing for the affiliated list; Added for Della07
			establishAffiliatedTeamSelectList(memberList.getName(selectedIndex));
		}
		memberList.setCurrentSelectedElementIndex(selectedIndex);			
		updatingGUI = false;
	}

	/**
	 * When the "Add affiliation" button is clicked, this routine is called to process it
	 * 
	 * Added for Della07
	 */
	private void addTeamAffiliation(ActionEvent e){
		if (unaffiliatedTeamsSelectList.getSelectedIndex()>noItemSelected) {
			String selectedTeam = (String)unaffiliatedTeamsSelectList.getSelectedValue();
			String selectedMember = (String)memberSelectList.getSelectedValue();
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
			establishAffiliatedTeamSelectList(selectedMember);
			// Rebuild the unaffiliated team list for this member and display it
			establishUnaffiliatedTeamSelectList(selectedMember);
			theController.setDirtyFlag(true); 
		}
		else
			JOptionPane.showMessageDialog(this, "No team was selected!",
					"Error",
					JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * When the "Remove affiliation" button is clicked, this routine is called to process it
	 * 
	 * Added for Della07
	 */
	private void removeTeamAffiliation(ActionEvent e){
		if (affiliatedTeamsSelectList.getSelectedIndex()>noItemSelected) {
			String selectedTeam = (String)affiliatedTeamsSelectList.getSelectedValue();
			String selectedMember = (String)memberSelectList.getSelectedValue();
			aiM.removeTeamAffiliation(selectedMember,selectedTeam);
			// Remove this team from this member's list of teams
			aiM.removeMemberAssociation(selectedTeam,selectedMember);
			// Remove this member from this team's list of members
			aiM.setSelectedUnassociatedMember(selectedTeam, selectedMember);
			// Have this member be selected in the list of unassociated members for this team
			aiM.setSelectedAssociatedMember(selectedTeam, "");
			// Have no member be selected in the list of associated members for this team
			aiM.setSelectedUnaffiliatedTeam(selectedMember, selectedTeam);
			// Have this team be selected in the list of unaffiliated teams for this member
			aiM.setSelectedAffiliatedTeam(selectedMember, "");
			// Have no team be selected in the list of affiliated teams for this member
			establishAffiliatedTeamSelectList(selectedMember);
			// Rebuild the list of affiliated teams for this member and display it
			establishUnaffiliatedTeamSelectList(selectedMember);
			// Rebuild the list of unaffiliated teams for this member and display it
			theController.setDirtyFlag(true); 
		}
		else
			JOptionPane.showMessageDialog(this, "No team was selected!",
					"Error",
					JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * When an affiliated team is clicked, this routine is called to process it
	 * 
	 * Added for Della07
	 */
	private void selectAffiliatedTeam(){
		if (updatingGUI != true) {
			String selectedTeam = (String)affiliatedTeamsSelectList.getSelectedValue();
			String selectedMember = (String)memberSelectList.getSelectedValue();
			aiM.setSelectedAffiliatedTeam(selectedMember, selectedTeam);
			// Select the affiliated team that was clicked
			aiM.setSelectedUnaffiliatedTeam(selectedMember, "");
			// Clear the unafffiliated team select list... both cannot have selected items
			establishUnaffiliatedTeamSelectList(selectedMember);
			// Cause the unaffiliated select list to be repainted incase a deselect has occurred
			theController.setDirtyFlag(true); 
		}
	}

	/**
	 * When an unaffiliated team is clicked, this routine is called to process it
	 * 
	 * Added for Della07
	 */
	private void selectUnaffiliatedTeam(){
		if (updatingGUI != true) {
			String selectedTeam = (String)unaffiliatedTeamsSelectList.getSelectedValue();
			String selectedMember = (String)memberSelectList.getSelectedValue();
			aiM.setSelectedUnaffiliatedTeam(selectedMember, selectedTeam);
			// Select the unaffiliated team that was clicked
			aiM.setSelectedAffiliatedTeam(selectedMember, "");
			// Clear the affiliated team select list... both cannot have a selected items
			establishAffiliatedTeamSelectList(selectedMember);
			// Cause the affiliated select list to be repainted incase a deselect has occurred
			theController.setDirtyFlag(true); 
		}
	}

	/**
	 * This routine establishes a select list of all of the unaffiliated teams for a specific member and finds the selected one
	 * 
	 * Added for Della07
	 */
	private void establishUnaffiliatedTeamSelectList(String memberName){
		updatingGUI = true;
		unaffiliatedTeamsMemberNameLabel.setText(memberName);
		((DefaultListModel)unaffiliatedTeamsSelectList.getModel()).removeAllElements();
		if (memberName.length()>0){	// If the member name is not empty, then use that member's unaffiliated team names
			String[] UnaffiliatedTeam = aiM.getUnaffiliatedTeams(memberName);
			int numUnaffiliatedTeams = UnaffiliatedTeam.length;
			String selectedUnaffiliatedTeam = aiM.getSelectedUnaffiliatedTeam(memberName);
			if (selectedUnaffiliatedTeam == null) selectedUnaffiliatedTeam = "";
			int selectedIndex = noItemSelected;
			for (int ndx=0; ndx < numUnaffiliatedTeams; ndx++) {	// Add all the names to the select list
				((DefaultListModel)unaffiliatedTeamsSelectList.getModel()).addElement(UnaffiliatedTeam[ndx]);
				if (selectedUnaffiliatedTeam.compareToIgnoreCase(UnaffiliatedTeam[ndx]) == 0) selectedIndex = ndx;
				// After adding each name, see if that name is the selected one.  If so, remember its index
			}
			unaffiliatedTeamsSelectList.setSelectedIndex(selectedIndex);
			// After the select list is build, specify which one was the selected element
		}
		updatingGUI = false;
	}

	/**
	 * This routine establishes a select list of all of the affiliated teams for a specific member and finds the selected one
	 * 
	 * Added for Della07
	 */
	private void establishAffiliatedTeamSelectList(String memberName){
		updatingGUI = true;
		affiliatedTeamsMemberNameLabel.setText(memberName);
		((DefaultListModel)affiliatedTeamsSelectList.getModel()).removeAllElements();
		if (memberName.length()>0){	// If the member name is not empty, then use that mamber's affiliated team names
			String[] AffiliatedTeam = aiM.getAffiliatedTeams(memberName);
			int numAffiliatedTeams = AffiliatedTeam.length;
			String selectedAffiliatedTeam = aiM.getSelectedAffiliatedTeam(memberName);
			if (selectedAffiliatedTeam == null) selectedAffiliatedTeam = "";
			int selectedIndex = noItemSelected;
			for (int ndx=0; ndx < numAffiliatedTeams; ndx++) {	// Add all the names to the select list
				((DefaultListModel)affiliatedTeamsSelectList.getModel()).addElement(AffiliatedTeam[ndx]);
				if (selectedAffiliatedTeam.compareToIgnoreCase(AffiliatedTeam[ndx]) == 0) selectedIndex = ndx;
				// After adding each name, see if that name is the selected one.  If so, remember its index
			}
			affiliatedTeamsSelectList.setSelectedIndex(selectedIndex);
			// After the select list is build, specify which one was the selected element
		}
		updatingGUI = false;
	}
}

