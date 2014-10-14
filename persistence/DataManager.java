package persistence;

import java.io.*;
import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import model.AccessItem;
import model.ActionItem;
import model.Element;
import model.ElementList;

import com.mysql.jdbc.Statement;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.corba.se.spi.orbutil.fsm.State;
import com.thoughtworks.xstream.XStream;

import control.Controller;

/**
 * <p>
 * Title: DataManager
 * </p>
 *
 * <p>
 * Description: Read and write data to the persistent store, an xml file
 * </p>
 *
 * <p>
 * Copyright: Copyright © 2005
 * </p>
 *
 * @author Harry Sameshima
 * @version 1
 */
public class DataManager {
	private static final String storageFile = "Della.xml";

	public DataManager() {
	}

	/**
	 * Read our persistent store into an object
	 * @return Object
	 */
	public static Object readData() {

		// Does the persistent store exist?
		File file = new File(storageFile);
		if (!file.exists()) { return null; }

		// Yes, so let's deserialize the object
		XStream x = new XStream();
		Object result = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			ObjectInputStream oin = x.createObjectInputStream(in);
			result = oin.readObject();
			oin.close();
		}
		catch (IOException ex) {
			System.out.println("IO exception reading " + storageFile);
			System.exit(0);
		}
		catch (ClassNotFoundException ex) {
			System.out.println("Class not found exception while reading " + storageFile);
			System.exit(0);
		}
		return result;
	}

	/**
	 * Write out an object to the persistent store so we can save data
	 * @param o Object
	 */
	public static void writeData(Object o) {
		try {
			System.out.println("writing to XML");
			
			XStream x = new XStream();
			FileWriter fw = new FileWriter(storageFile);
			ObjectOutputStream out = x.createObjectOutputStream(fw);
			writeDataDB(o);
			out.writeObject(o);
			out.close();
//			Scanner scanner=new Scanner(System.in);
//			int i=scanner.nextInt();
			
		}
		catch (IOException ex) {
			System.out.println("IO Exception writing " + storageFile);
			//System.exit(0);
		}
	}
	public static void readDataDB()
	{
		try {
			Statement statement= (Statement) DBConnection.getConnection().createStatement();
			String string="select * from actionItemList;";
			ResultSet resultSet=statement.executeQuery(string);
			ArrayList<ActionItem> actionItems=new  ArrayList<ActionItem>();
			ActionItem  actionItem;
			String testDate = "";
			Date date ;
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			while (resultSet.next()) {
				
				actionItem=new ActionItem();
				actionItem.setActionItemName(resultSet.getString(1));
				actionItem.setDescription(resultSet.getString(2));
				actionItem.setResolution(resultSet.getString(3));
				actionItem.setStatus(resultSet.getString(4));
				testDate = resultSet.getString(5);
				date = formatter.parse(testDate);
				actionItem.setCreatedDate(date);
				actionItem.setAssignedMember(resultSet.getString(6));
				actionItem.setAssignedTeam(resultSet.getString(7));
				testDate = resultSet.getString(8);
				date = formatter.parse(testDate);
				actionItem.setDueDate(date);
				actionItems.add(actionItem);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void writeDataDB(Object o)
	{
		System.out.println("in writeobject");
		Statement statement = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			statement = (Statement) DBConnection.getConnection().createStatement();
		Controller controller=(Controller)o;
		String string="";
		statement.executeUpdate("truncate table actionitemlist;");
		statement.executeUpdate("truncate table accessitem;");
		statement.executeUpdate("truncate table memberlist;");
		statement.executeUpdate("truncate table teamlist;");
		statement.executeUpdate("truncate table sortdata;");
		ActionItem actionItem;
		for (int i = 0; i < controller.getActionItemManager().getActionItemList().size(); i++) {

			actionItem=(ActionItem) controller.getActionItemManager().getActionItemList().get(i);
			System.out.println(actionItem.getDueDate());
			string="insert into actionitemList values(";
			
			
			string=string+"'"+actionItem.getActionItemName()+"','"+actionItem.getDescription()
					+"','"+actionItem.getResolution()+"','"+actionItem.getStatus()+"','"+new java.sql.Timestamp( actionItem.getCreatedDate().getTime())
					+"','"+actionItem.getAssignedMember()+"','"+actionItem.getAssignedTeam()+"',";
			if(actionItem.getDueDate()!=null){
				string=string+"'"+new java.sql.Timestamp( actionItem.getDueDate().getTime())+"'";
			}
			else string = string + "NULL";
			
			string=string+");";
			System.out.println(string);
			statement.executeUpdate(string);
				

//				if(!actionItem.getDescription().isEmpty()){
//				string=string+",'"+actionItem.getResolution()+"'";
//			}
//			else string=string+",'null'";
//			if(!actionItem.getStatus().isEmpty()){
//				string=string+",'"+actionItem.getStatus()+"'";
//			}
//			else string=string+",'null'";
//			
//			if(actionItem.getDueDate()!=null){
//				string=string+new java.sql.Timestamp( actionItem.getDueDate().getTime());
//			}
//			els
				
				
				
				
				
				
		}
		ElementList elements=controller.getActionItemManager().getMemberList();
		Element element;
		for (int i = 0; i < elements.getListSize(); i++) {
			string="insert into memberList values('";
			element=elements.get(i);
			string=string+element.getName()+"',"+element.getNumAssociatedNames()+",";
			for (int j = 0; j < element.getAssociatedNames().length; j++) {
				string=string+"'"+element.getAssociatedNames()[j]+"',";
			}
			for (int j = 0; j < 10-element.getAssociatedNames().length; j++) {
				string=string+"'null',";
			}
			string=string+"'"+element.getSelectedAssociatedName()+"','"+element.getSelectedUnassociatedName()+"');";
			System.out.println(string);
			statement.executeUpdate(string);
		}
		
		elements=controller.getActionItemManager().getTeamList();
		for (int i = 0; i < elements.getListSize(); i++) {
			string="insert into teamList values('";
			element=elements.get(i);
			string=string+element.getName()+"',"+element.getNumAssociatedNames()+",";
			for (int j = 0; j < element.getAssociatedNames().length; j++) {
				string=string+"'"+element.getAssociatedNames()[j]+"',";
			}
			for (int j = 0; j < 10-element.getAssociatedNames().length; j++) {
				string=string+"'null',";
			}
			string=string+"'"+element.getSelectedAssociatedName()+"','"+element.getSelectedUnassociatedName()+"');";
			System.out.println(string);
			statement.executeUpdate(string);
		}
		
		String[] actionItems=controller.getActionItemManager().getActionItemNames();
		for (int i = 0; i < actionItems.length; i++) {
			string="insert into accessItem values('";
			
			string=string+actionItems[i]+"',"+controller.getActionItemManager().getActionItemIndex(actionItems[i])+");";
			System.out.println(string);
			statement.executeUpdate(string);
		}
		
		string="insert into sortdata values(";
		
		string=string+controller.getActionItemManager().getSortDirection()+","+controller.getActionItemManager().getSortFactor1()+","+
		controller.getActionItemManager().getSortFactor2()+","+controller.getActionItemManager().getInclusionFactor()+",'"+controller.getActionItemManager().getCurrentActionItemName()+"');";
		System.out.println(string);
		statement.executeUpdate(string);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
