
package persistence;

/**
 * */
public class Synchronization {
/**Della10 - to check for Internet connectivity.
*/
private boolean userOnline = true;
/** Check the status of the user.
* @return boolean
*/
public final boolean isUserOnline() {
return userOnline;
}
/** Updating the database from the local cache.
*/
public void synchronize() {
}
/**LockTask locks the update operation
 *when multiple users are accessing the same table.
* @return boolean
*/
public final boolean lockTask() {
return false;
}
}
