package kanbanbackend.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.db.StageDaoImpl;

/**
 * This class represents a specific stage (or column) on a Kanban workspace.
 *
 * @author caw
 */
@DatabaseTable(tableName = "Stages")
public class Stage implements Comparable<Stage> {

    private List<Task> tasks;
    private int capacity;
    private int numTasks;
    @DatabaseField(columnName ="StageID", id = true)
    private int id;
    @DatabaseField(columnName = "StageName")
    private String name;
    @DatabaseField(columnName = "StageWorkspaceID")
    private int workspaceID;
    @DatabaseField(columnName = "StageOrderNumber")
    private int orderNumber;

    /**
     * No arg for ORM
     */
    public Stage() {

    }
    /*
     * Default constructor for a stage
     */
    public Stage(String name, int capacity, int workspaceID, int orderNumber){
        this.id = SQLDriver.instance().getRowId("Stages");
        this.name = name;
        this.capacity = capacity;
        this.workspaceID = workspaceID;
        this.orderNumber = orderNumber;
        numTasks = 0;
        tasks = new ArrayList<Task>();
        try {
            StageDaoImpl.instance(null).create(this);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /*
     * Access for the id field
     */
    public int getId() {
        return id;
    }

    /*
     * Mutator for the id field
     */
    public void setId(int n){
        id = n;
    }

    /*
     * Get the capacity of this stage
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /*
     * Change the capacity of this stage
     */
    public void setCapacity(int n){
        capacity = n;
    }

    /*
     * Retrieve the set of tasks that belong to this system
     *
     * @return set of all tasks
     */
    public List<Task> getTasks(){
        return tasks;
    }

    /*
     * Retrieve the name of this stage
     *
     * @return stage name
     */
    public String getName(){
        return name;
    }

    /*
     * Set the name for this stage
     */
    public void setName(String s){
        name = s;
    }

    /*
     * Retrieve the number of tasks that are in this stage
     */
    public int getNumTasks(){
        return tasks.size();
    }

    /*
     * Add a task to the set of tasks in this stage
     *
     * @return true if the task was added successfully, false otherwise
     */
    public boolean addTask(Task t){
        if (!tasks.contains(t)) {
            tasks.add(t);
            t.setStageId(id);
            return true;
        } else {
            return false;
        }
    }

    /*
     * Remove a task from the set of tasks in this stage
     */
    public Task removeTask(int taskId){
        Iterator itr = tasks.iterator();
        while (itr.hasNext()) {
            Task t = (Task)itr.next();
            if (t.getId() == taskId){
                itr.remove();
                return t;
            }
        }
        return null;
    }

    /*
     * Remove all tasks from this stage
     */
    public void clearTasks() {
        tasks.clear();
    }

    /*
     * Does this stage contain the specified ID?
     *
     * @return true if this stage contains the ID, false otherwise
     */
    public boolean hasTask(Task t){
        for (Task t1 : tasks) {
            if (t1.equals(t)){
                return true;
            }
        }
        return false;
    }

    /*
     * Print out the information pertaining to this Stage object.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Stage " + name + "\n");
        builder.append("   ID: " + id + "\n");
        builder.append("   Capacity: " + capacity + "\n");
        builder.append("   Number of tasks: " + numTasks + "\n");
        builder.append("\n");
        builder.append("Task list:\n");
        for (Task t : tasks){
            builder.append(t.toString() + "\n");
        }
        return builder.toString();
    }

    /*
     * Compare two stage objects and return the result of the comparison
     */
    public int compareTo(Stage o) {
        int result = 0;
        if (id < o.getId()) result = -1;
        else if (id > o.getId()) result = 1;
        return result;
    }

    /*
     * Determine if two stages are equal and return the result.
     * The equality of a stage depends on its unique ID.
     */
    @Override
    public boolean equals(Object o){
        boolean result = false;
        if (o instanceof Stage){
            Stage s = (Stage)o;
            result = id == s.getId() ? true : false;
        }
        return result;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    public int getOrderNumber() {
        return orderNumber;
    }
    public int getWorkspaceId() {
        return workspaceID;
    }

}
