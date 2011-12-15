/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kanbanbackend.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.stmt.StatementBuilder;
import com.j256.ormlite.stmt.Where;
import java.sql.SQLException;
import java.util.List;
import kanbanbackend.model.TaskUsersTuple;

/**
 *
 * @author Kle
 */
public class TaskUsersTupleDaoImpl extends BaseDaoImpl<TaskUsersTuple, Object>
        implements TaskUsersTupleDao{

    private static TaskUsersTupleDaoImpl instance;
    private static Object padlock = new Object();

    private TaskUsersTupleDaoImpl(DatabaseType databaseType) throws SQLException {
        super(databaseType, TaskUsersTuple.class);
    }

     /*
     * Retrieve the instance of the StageDaoImpl. This
     * method is thread-safe.
     */
    public static TaskUsersTupleDaoImpl instance(DatabaseType databaseType) throws SQLException{
        if (!SQLDriver.instance().isInitialized()) {
            SQLDriver.instance().initialize("KanbanDB.sqlite", "KanbanBacklog.sqlite");
        }
        if (instance == null) {
            synchronized (padlock){
                instance = new TaskUsersTupleDaoImpl(databaseType);
            }
        }
        return instance;
    }

    public List<TaskUsersTuple> getUsersForTask(int taskID) throws SQLException {

       StatementBuilder<TaskUsersTuple, Object> queryBuilder = this.statementBuilder();
       Where where = queryBuilder.where();
       where.eq("TaskUsersTaskID", taskID);
       List<TaskUsersTuple> objs = this.query(queryBuilder.prepareStatement());

       return objs;
    }

    public List<TaskUsersTuple> getTasksForUser(int userID) throws SQLException {
       StatementBuilder<TaskUsersTuple, Object> queryBuilder = this.statementBuilder();
       Where where = queryBuilder.where();
       where.eq("TaskUsersUserID", userID);
       List<TaskUsersTuple> objs = this.query(queryBuilder.prepareStatement());

       return objs;
    }
}
