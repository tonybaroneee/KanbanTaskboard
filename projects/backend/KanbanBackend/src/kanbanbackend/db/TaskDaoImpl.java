package kanbanbackend.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.StatementBuilder;


import com.j256.ormlite.stmt.Where;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import kanbanbackend.model.Task;

import java.util.*;

/**
 *
 * @author caw
 */
public class TaskDaoImpl extends BaseDaoImpl<Task, Object> implements TaskDao {

    private static TaskDaoImpl instance;
    private static Object padlock = new Object();

    private TaskDaoImpl(DatabaseType databaseType) throws SQLException {
        super(databaseType, Task.class);
    }

     /*
     * Retrieve the instance of the TaskDaoImpl. This
     * method is thread-safe.
     */
    public static TaskDaoImpl instance(DatabaseType databaseType) throws SQLException{
        if (!SQLDriver.instance().isInitialized()) {
            SQLDriver.instance().initialize("KanbanDB.sqlite", "KanbanBacklog.sqlite");
        }
        if (instance == null) {
            synchronized (padlock){
                instance = new TaskDaoImpl(databaseType);
            }
        }
        return instance;
    }

    /**
     * Delete a task from database.
     */
     public void deleteTask(Task task) {
        try {
            this.delete(task);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    /**
     * Using DAO objects to get a task based on TaskID
     *
     * @param id
     * @return
     */
    public Task getTask(int id) {
        Task task = null;
        try {
            task = this.queryForId(id);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return task;
    }

    /**
     * Get tasks based off stageID
     *
     * @param id
     * @return
     */

    public List<Task> getTasksFromStageId(int id) {
        List<Task> tasks = null;
        try {
            StatementBuilder<Task, Object> queryBuilder = this.statementBuilder();
            Where where = queryBuilder.where();
            where.eq("TaskStageID", id);
            tasks = this.query(queryBuilder.prepareStatement());
        } catch (SQLException ex) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tasks;
    }


    /*
     * Generate a list of all tasks in the database (this will be MASSSIVE).
     *
     * @return a list of all tasks
     */
    public List<Task> getAllTasks(){
        List<Task> tasks = new ArrayList<Task>();

        for(Task task : this) {
            tasks.add(task);
        }

        return tasks;

    }

    /*
     * Process the data in a ResultSet to produce a Task object.
     *
     * @return a new Task object
     * @throws SQLException if there is trouble accessing the result set
     */
    public Task processRow(ResultSet set) throws SQLException {
        String name = set.getString("TaskName");
        String desc = set.getString("TaskDescription");
        Date create = set.getDate("TaskDateCreated");
        Date complete = set.getDate("TaskDateCompleted");
        int stageId = set.getInt("TaskStageID");
        int ownerID = set.getInt("TaskOwnerID");
        boolean completed = set.getBoolean("TaskCompleted");
        Task task = new Task(name, desc, create, complete, stageId, 
                ownerID, completed);
        return task;
    }

}
