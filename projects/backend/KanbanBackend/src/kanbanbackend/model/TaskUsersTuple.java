/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kanbanbackend.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.SQLException;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.db.TaskUsersTupleDaoImpl;

/**
 *
 * @author Kle
 */
@DatabaseTable(tableName="XREF_TaskUsers")
public class TaskUsersTuple {

    @DatabaseField(columnName="TaskUsersID", id = true)
    private int ID;

    @DatabaseField(columnName="TaskUsersTaskID")
    private int taskID;

    @DatabaseField(columnName="TaskUsersUserID")
    private int userID;

    public TaskUsersTuple() {
        
    }

    public TaskUsersTuple(int taskID, int userID) {
        this.taskID = taskID;
        this.userID = userID;
        this.ID = SQLDriver.instance().getRowId("XREF_TaskUsers");

        try {
            TaskUsersTupleDaoImpl.instance(null).create(this);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public int getUserID() {
        return userID;
    }

    public int getTaskID() {
        return taskID;
    }

    public int getID() {
        return ID;
    }


}
