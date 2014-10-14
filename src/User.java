package src;


public class User {

	/** name - Username
	 * password - User password
	 * securitQuestion - Prompts when user forgets the password
	 * teamDetails - Contains the details of the team members
	 * 
	 */
	
	private String name;
	private String password;
	private String securityQuestion;
	private String teamDetails;
	
	public User(String name,String password,String securityQuestion, String teamDetails)
	{
		this.name=name;
		this.password=password;
		this.securityQuestion=securityQuestion;
		this.teamDetails=teamDetails;
	}
	
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getSecurityQuestion() 
	{
		return securityQuestion;
	}
	
	public void setSecurityQuestion(String securityQuestion) 
	{
		this.securityQuestion = securityQuestion;
	}
	
	public String getTeamDetails()
	{
		return teamDetails;
	}
	
	public void setTeamDetails(String teamDetails) 
	{
		this.teamDetails = teamDetails;
	}
	
	/**
	 * Submits the details entered in the fields server and local cache
	 */
	
	public void submit()
	{
		
	}
	
	/**
	 * Validate will validate the usernames and passwords from local cache or database
	 * @return boolean
	 */
	public boolean validate()
	{
		return false;
	}
}
