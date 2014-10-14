package src;


public class Synchronization 
{

	private boolean userOnline;	//Della10 - to check for Internet connectivity
	
	/**
	 * Check the status of the user
	 * @return boolean
	 */
	
	public boolean isUserOnline() 
	{
		return userOnline;
	}
	
	/**
	 * Check the status of internet connectivity
	 * @return boolean
	 */
	
	public boolean is_offline()
	{
		return false;
	}
	
	/**
	 * Updating the database from the local cache
	 */
	
	public void synchronize()
	{
		
	}
	
	/**
	 * LockTask locks the task when multiple users are accessing the same task
	 * @return boolean
	 */
	
	public boolean lockTask()
	{
		return false;
	}
}
