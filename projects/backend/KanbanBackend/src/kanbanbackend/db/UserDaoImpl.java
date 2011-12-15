package kanbanbackend.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.StatementBuilder;
import com.j256.ormlite.stmt.Where;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kanbanbackend.model.Task;
import kanbanbackend.model.User;

/**
 *
 * @author caw
 */
public class UserDaoImpl extends BaseDaoImpl<User, Object> implements UserDao {

    private static UserDaoImpl instance;
    private static Object padlock = new Object();

    // public static void openSource, closeSource

    private UserDaoImpl(DatabaseType databaseType) throws SQLException {
        super(databaseType, User.class);
    }

     /*
     * Retrieve the instance of the SQLDriver. This
     * method is thread-safe.
     */
    public static UserDaoImpl instance(DatabaseType databaseType) throws SQLException {
        if (!SQLDriver.instance().isInitialized()) {
            SQLDriver.instance().initialize("KanbanDB.sqlite", "KanbanBacklog.sqlite");
        }
        if (instance == null) {
            synchronized (padlock){
                instance = new UserDaoImpl(databaseType);
            }
        }
        return instance;
    }
    /**
     * Get a user from database with UserID
     */

    public User getUser(String name) {
        List<User> users = null;
        try {
            StatementBuilder<User, Object> queryBuilder = this.statementBuilder();
            Where where = queryBuilder.where();
            where.eq("UserName", name);
            users = this.query(queryBuilder.prepareStatement());
        } catch (SQLException ex) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (users.size() != 0) {
            return users.get(0);
        }
        return null;
    }

    public User getUserFromEmail(String email) {
        List<User> users = null;
        try {
            StatementBuilder<User, Object> queryBuilder = this.statementBuilder();
            Where where = queryBuilder.where();
            where.eq("UserEmail", email);
            users = this.query(queryBuilder.prepareStatement());
        } catch (SQLException ex) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (users.size() != 0) {
            return users.get(0);
        }
        return null;
    }

    /**
     * Deletes a user from DB
     *
     * @param user
     */
    public void deleteUser(User user) {
        try {
            this.delete(user);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addTaskSubscription(User u, Task t, boolean email, boolean rss) {
        // build the query string
        String query = "INSERT INTO XREF_TaskUsers [(TaskUsersID, TaskUsersTaskID, "
                + "TaskUsersUserID, TaskUsersEmail, TaskUsersRss)] VALUES(";
        query += "0, ";
        query += "" + t.getId() + ", ";
        query += "" + u.getId() + ", ";
        if (email) query += "1, ";
        else query += "0, ";
        if (rss) query += "1)";
        else query += "0)";
        SQLDriver.instance().executeNonQuery(query);
    }

    /*
     * Determine if the specified user exists in the database
     */
    public boolean userExists(User u) {
        boolean result = true;
        try {
            System.out.println("Checking for the existence of:\n" + u.toString());
            ResultSet set = SQLDriver.instance().getStatement().executeQuery("SELECT * FROM Users " +
                    "WHERE UserEmail = '" + u.getEmail() + "'");
            if (set.getFetchSize() == 0) result = false;
            set.close();
        } catch (SQLException e) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    /*
     * Determine if the user with the given account credentials belongs to the system
     */
    public User userAccountCheck(String email, String pw) {
        User result = null;
        List<User> users;
        try {

            System.out.println("Checking for the existence of " + email + " for sign-in");

            StatementBuilder<User, Object> queryBuilder = this.statementBuilder();
            Where where = queryBuilder.where();
            where.eq("UserEmail", email);
            where.and();
            where.eq("UserPassword", pw);
            users = this.query(queryBuilder.prepareStatement());

            if (!users.isEmpty()) {
                result = users.get(0);
            }
        } catch (SQLException e) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public User getUserByEmail(String email) {
        User result = null;
        List<User> users;
        try {

            System.out.println("Checking for the existence of " + email + " for sign-in");

            StatementBuilder<User, Object> queryBuilder = this.statementBuilder();
            Where where = queryBuilder.where();
            where.eq("UserEmail", email);
            users = this.query(queryBuilder.prepareStatement());

            if (!users.isEmpty()) {
                result = users.get(0);
            }
        } catch (SQLException e) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    /**
     * Returns a list of all users in the DB
     * @return
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        for(User user : this) {
            users.add(user);
        }

        return users;
    }
}
