package kanbanbackend.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.db.UserDaoImpl;

/**
 * This class represents a generic user in the system. It is abstract so that
 * every specific instance must be inherit from this class.
 *
 * @author caw
 */
@DatabaseTable(tableName="Users")
public class User {

    @DatabaseField(columnName="UserID", id = true)
    protected int UserID;
    @DatabaseField(columnName="UserName")
    protected String name;
    @DatabaseField(columnName="UserPassword")
    protected String password;
    @DatabaseField(columnName="UserEmail")
    protected String email;    

    // stuff not in database, but should be
    //@DatabaseField(columnName="UserWorkSet")
    protected Set<Task> workSet;
    //@DatabaseField(columnName="UserSubSet")
    protected Set<Task> subSet; // subscription set

    /*
     * No-argument constructor for mapping objects to database
     */
    public User() {
        workSet = new HashSet<Task>();
        subSet = new HashSet<Task>();
    }
    
    /*
     * Default constructor for a user.
     */
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.UserID = SQLDriver.instance().getRowId("Users");
        workSet = new HashSet<Task>();
        subSet = new HashSet<Task>();
        try {
            UserDaoImpl.instance(null).create(this);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /*
     * Access for the name field
     */
    public String getName() {
        return name;
    }

    /*
     * Mutator for the name field
     */
    public void setName(String s) {
        name = s;
    }

    /*
     * Access for the email field
     */
    public String getEmail() {
        return email;
    }

    /*
     * Mutator for the email field
     */
    public void setEmail(String s) {
        email = s;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * Subscribe to a task for notifications and ownership
     *
     * @return true if the task was added successfully, false otherwise
     */
    public boolean addTaskToWorkSet(Task t){
        boolean result = false;
        if (!workSet.contains(t)){
            workSet.add(t);
            result = true;
        }
        return result;
    }

    /*
     * Remove and return the specified task from this user's work
     * set if it exists
     *
     * @return the task that was removed from the work set
     */
    public Task removeTaskFromWorkSet(int id){
        Task result = null;
        Iterator itr = workSet.iterator();
        while (itr.hasNext()){
            Task t = (Task)itr.next();
            if (t.getId() == id){
                result = t;
                itr.remove();
                break;
            }
        }
        return result;
    }

    /*
     * Subscribe to a given task
     *
     * @return true if successful, false otherwise
     */
    public boolean subscribeToTask(Task t, boolean email, boolean rss){
        boolean result = false;
        if (!subSet.contains(t)){
            subSet.add(t);
            result = true;
        }
        return result;
    }

    /*
     * Remove and return a task from this user's subscription list
     *
     * @return the task removed from the subscription set
     */
    public Task unsubscribeFromTask(int id){
        Task result = null;
        Iterator itr = subSet.iterator();
        while (itr.hasNext()) {
            Task t = (Task)itr.next();
            if (t.getId() == id){
                result = t;
                itr.remove();
                break;
            }
        }
        return result;
    }

    /**
     * Sets the id for a user
     */
    public void setId(int id) {
        this.UserID = id;
    }

    /*
     * Retrieve the ID for this user
     */
    public int getId() {
        return UserID;
    }

    /*
     * Return the string representation of this user
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User: " + name + "\n");
        builder.append("Email: " + email + "\n");
        return builder.toString();
    }
}
