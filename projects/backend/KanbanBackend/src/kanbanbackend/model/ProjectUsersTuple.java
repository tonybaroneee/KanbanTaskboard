/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kanbanbackend.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.SQLException;
import kanbanbackend.db.ProjectUsersTupleDaoImpl;
import kanbanbackend.db.SQLDriver;

/**
 *
 * @author Ian
 */
@DatabaseTable(tableName="XREF_ProjectUsers")
public class ProjectUsersTuple {

    @DatabaseField(columnName="ProjectUsersID", id=true)
    private int projectUsersID;
    
    @DatabaseField(columnName="ProjectUsersProjectID")
    private int projectUsersProjectID;
    
    @DatabaseField(columnName="ProjectUsersUserID")
    private int projectUsersUserID;
    
    @DatabaseField(columnName="ProjectUsersUserTypeID")
    private int projectUsersUserTypeID;

    public ProjectUsersTuple() {

    }

    public ProjectUsersTuple(int projectUsersProjectID,
            int projectUsersUserID, int projectUsersUserTypeID) {

        this.projectUsersProjectID = projectUsersProjectID;
        this.projectUsersUserID = projectUsersUserID;
        this.projectUsersUserTypeID = projectUsersUserTypeID;

        this.projectUsersID = SQLDriver.instance().getRowId("XREF_ProjectUsers");

        try {
            ProjectUsersTupleDaoImpl.instance(null).create(this);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }   
    }

    public int getProjectUsersID() {
        return projectUsersID;
    }

    public int getProjectUsersProjectID() {
        return projectUsersProjectID;
    }

    public int getProjectUsersUserID() {
        return projectUsersUserID;
    }

    public int getProjectUsersUserTypeID() {
        return projectUsersUserTypeID;
    }






}
