package login;

/**
 * */
public class User {
/**name-Username.
*/
private String name = null;
/**password - User password.*/
private String password = null;
/** securitQuestion - Prompts when user forgets the password.
*/
private String securityQuestion = null;
/** teamDetails - Contains the details of the team members.
*/
private String teamDetails = null;
/**constructor.
*/
public User(String name, String password, String securityQuestion, String teamDetails) {
this.name = name;
this.password = password;
this.securityQuestion = securityQuestion;
this.teamDetails = teamDetails;
}
/**getter for name.
 * */
public final String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
	
public String getPassword() {
return password;
}
public void setPassword(String password) {
this.password = password;
}
public String getSecurityQuestion() {
return securityQuestion;
}
public void setSecurityQuestion(String securityQuestion) {
this.securityQuestion = securityQuestion;
}
public String getTeamDetails() {
return teamDetails;
}
public void setTeamDetails(String teamDetails) {
this.teamDetails = teamDetails;
}
/** Submits the details entered in the fields server and local cache.
*/
public void submit() {
}
/**validate the usernames and passwords from local cache or database.
* @return boolean
*/
public boolean validate() {
return false;
}
}
