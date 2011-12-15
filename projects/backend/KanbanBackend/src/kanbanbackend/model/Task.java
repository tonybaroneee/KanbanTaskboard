package kanbanbackend.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Date;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.db.TaskDaoImpl;

/**
 * This class represents a task in the Kanban system.
 *
 * @author caw
 */
@DatabaseTable(tableName ="Tasks")
public class Task implements Comparable<Task> {

    // instance variables
    @DatabaseField(columnName="TaskName")
    private String name;
    @DatabaseField(columnName="TaskDescription")
    private String description;
    @DatabaseField(columnName="TaskDateCreated")
    private Date creationDate;
    @DatabaseField(columnName="TaskDateCompleted")
    private Date completionDate;
    @DatabaseField(columnName="TaskStageID")
    private int stageId;
    @DatabaseField(columnName="TaskOwnerID")
    private int ownerId;
    @DatabaseField(columnName="TaskPriority")
    private int priority;
    @DatabaseField(id = true, columnName="TaskID")
    private int id;
    @DatabaseField(columnName="TaskCompleted")
    private boolean completed;

    /*
     * No-argument constructor for mapping objects to database
     */
    public Task() {
        // ...
    }

    /**
     * Construct a task
     * @param name
     * @param description
     * @param creationDate
     * @param completionDate
     * @param stageId
     * @param ownerId
     * @param completed
     */
    public Task(String name, String description, Date creationDate,
            Date completionDate, int stageId, int ownerId, boolean completed){
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.stageId = stageId;
        this.ownerId = ownerId;
        this.completed = completed;
        this.id = SQLDriver.instance().getRowId("Tasks");
        try {
            TaskDaoImpl.instance(null).create(this);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /*
     * Accessor for name field
     */
    public String getName(){
        return name;
    }

    /*
     * Mutator for name field
     */
    public void setName(String s){
        name = s;
    }

    /*
     * Accessor for description field
     */
    public String getDesc(){
        return description;
    }

    /*
     * Mutator for description field
     */
    public void setDesc(String s){
        description = s;
        try {
            TaskDaoImpl.instance(null).update(this);
        } catch (SQLException ex) {}
    }

    /*
     * Accessor for creation date field
     */
    public Date getCreationDate(){
        return creationDate;
    }

    /*
     * Mutator for creation date field
     */
    public void setCreationDate(Date d){
        creationDate = d;
    }

    /*
     * Accessor for completion date field
     */
    public Date getCompletionDate(){
        return completionDate;
    }

    /*
     * Mutator for completion date field
     */
    public void setCompletionDate(Date d){
        completionDate = d;
    }

    /*
     * Accessor for stage ID field
     */
    public int getStageId(){
        return stageId;
    }

    /*
     * Mutator for stage ID field
     */
    public void setStageId(int n){
        stageId = n;
    }

    /*
     * Accessor for owner ID field
     */
    public int getOwnerId(){
        return ownerId;
    }

    /*
     * Mutator for owner ID field
     */
    public void setOwnerId(int n){
        ownerId = n;
    }

    /*
     * Accessor for priority field
     */
    public int getPriority(){
        return priority;
    }

    /*
     * Mutator for priority field
     */
    public void setPriority(int n){
        priority = n;
    }

    /*
     * Accessor for ID field
     */
    public int getId(){
        return id;
    }

    /*
     * Mutator for ID field
     */
    public void setId(int n){
        id = n;
    }

    /*
     * Accessor for the completed field
     */
    public boolean getCompleted() {
        return completed;
    }

    /*
     * Mutator for the completed field
     */
    public void setCompleted(boolean b){
        completed = b;
    }

    /*
     * Equals override for tasks
     *
     * @return true if equal, false otherwise
     */
    public boolean equals(Object t){
        boolean result = false;
        if (t instanceof Task){
            Task tmp = (Task)t;
            result = tmp.getId() == id ? true : false;
        }
        return result;
    }

    /*
     * Override hashcode method for tasks (necessary for overriding equals)
     */
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        return hash;
    }

    /*
     * Return a description of this task
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Task " + name + "\n");
        builder.append("    ID: " + id + "\n");
        builder.append("    Creation date: " + creationDate.toString() + "\n");
        builder.append("    Stage ID: " + stageId + "\n");
        builder.append("    Owner ID: " + ownerId + "\n");
        builder.append("    Completed: " + completed + "\n");
        builder.append("    Description: " + description + "\n");
        return builder.toString();
    }

    /*
     * Return a new Task object with the same properties as this one
     */
    public Task clone() {
        return new Task(name, description, creationDate,
            completionDate, stageId, ownerId, completed);
    }

    /*
     * Compare two task objects and return the result
     *
     * @return the result of the comparison
     */
    public int compareTo(Task o) {
        int result = 0;
        if (id < o.getId()) result = -1;
        else if (id > o.getId()) result = 1;
        return result;
    }

}
