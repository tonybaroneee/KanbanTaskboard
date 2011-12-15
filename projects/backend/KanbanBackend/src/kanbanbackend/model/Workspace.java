package kanbanbackend.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.db.WorkspaceDaoImpl;

/**
 * This class represents the workspace for a Kanban project.
 *
 * @author caw
 */
@DatabaseTable(tableName = "Workspaces")
public class Workspace {

    private List<Stage> stages;
    @DatabaseField(columnName ="WorkspaceFont")
    private String postitFont;
    @DatabaseField(columnName ="WorkspaceStyle")
    private String postitStyle;
    @DatabaseField(columnName ="WorkspaceWeight")
    private String postitWeight;
    @DatabaseField(columnName ="WorkspaceSize")
    private int postitSize;
    @DatabaseField(columnName ="WorkspaceColor")
    private int postitColor;
    @DatabaseField(columnName ="WorkspaceNumColumns")
    private int numColumns;
    @DatabaseField(id = true, columnName = "WorkspaceID")
    private int id;
    @DatabaseField(columnName ="WorkspaceName")
    private String name;
    @DatabaseField(columnName = "WorkspaceVersionID")
    private int versionID;
    @DatabaseField(columnName = "WorkspaceProjectID")
    private int projectID;

    /**
     * No argument constructor for ORM
     */
    public Workspace() {
        stages = new LinkedList<Stage>();
    }

    /*
     * 
     */
    public Workspace(String name, int versionID, int projectID) {
        this.id = SQLDriver.instance().getRowId("Workspaces");
        this.name = name;
        this.versionID = versionID;
        this.projectID = projectID;
        stages = new LinkedList<Stage>();
        try {
            WorkspaceDaoImpl.instance(null).create(this);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Constructor with all parameters
     * @param name
     * @param versionID
     * @param postitFont
     * @param postitStyle
     * @param postitWeight
     * @param postitSize
     * @param postitColor
     * @param numColumns
     */
    public Workspace(String name, int versionID, int projectID, String postitFont,
            String postitStyle, String postitWeight,
            int postitSize, int postitColor, int numColumns) {
        this.name = name;
        this.id = SQLDriver.instance().getRowId("Workspaces");
        this.versionID = versionID;
        this.projectID = projectID;
        this.postitFont = postitFont;
        this.postitColor = postitColor;
        this.postitSize = postitSize;
        this.postitWeight = postitWeight;
        this.postitStyle = postitStyle;
        this.numColumns = numColumns;
        stages = new LinkedList<Stage>();
        try {
            WorkspaceDaoImpl.instance(null).create(this);
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

    /**
     * Get Name
     * @param pos
     * @param s
     * @return
     */

    public String getName() {
        return name;
    }

    /*
     * Add a stage to this workspace
     *
     * @return true if the stage was addedd successfully, false otherwise
     */
    public boolean addStage(int pos, Stage s){
        boolean result = false;
        if (!stages.contains(s) && pos >= 0 && pos < stages.size()){
            stages.add(pos, s);
            result = true;
        }

        return result;
    }

    /*
     * Remove a stage from this workspace
     *
     * @return the removed stage
     */
    public boolean removeStage(int id){
       boolean result = false;
       ListIterator itr = stages.listIterator();
       while (itr.hasNext()){
           Stage tmp = (Stage)itr.next();
           if (tmp.getId() == id) {
               itr.remove();
               result = true;
               break;
           }
       }
       return result;
    }

    /*
     * Retrieve a task from this workspace given its ID
     */
    public Task getTask(int taskId) {
        ListIterator itr = stages.listIterator();
        while (itr.hasNext()) {
            for (Task t : ((Stage)itr.next()).getTasks()) {
                if (taskId == t.getId()) {
                    return t;
                }
            }
        }
        return null;
    }

    /*
     * Helper function to determine if the specified stage is in this workspace
     *
     * @return true if found, false otherwise
     */
    public boolean containsStage(int id) {
        for (Stage s : stages) {
            if (s.getId() == id){
                return true;
            }
        }
        return false;
    }

    /*
     * Find the index of a given stage in the list of stages
     *
     * @return the index of a stage, if present
     */
    public int findStageIndex(int id){
        int index = -1;
        for (int i = 0; i < stages.size(); i++) {
            if (stages.get(i).getId() == id){
                index = i;
                break;
            }
        }
        return index;
    }

    /*
     * Move a task between two stages, if possible, and return true or false
     * depending on whether or not the move was complete.
     *
     * @return true if move was successful, false otherwise
     */
    public boolean moveTask(int taskId, int fromStageId, int toStageId){
        boolean result = false;

        // check to make sure both stages actually exist in the work space
        if (containsStage(fromStageId) && containsStage(toStageId)){
            Task t = stages.get(findStageIndex(fromStageId)).removeTask(taskId);
            Task clone = t.clone();
            if (t != null){
                result = stages.get(findStageIndex(toStageId)).addTask(t);

                // if the move wasn't successful, put the stage back in the original
                if (!result) {
                    stages.get(findStageIndex(fromStageId)).addTask(t);
                }
            }
        }
        
        return result;
    }

    /*
     * Remove a task from this workspace.
     */
    public boolean removeTask(int taskId) {
        ListIterator stageItr = stages.listIterator();
        while (stageItr.hasNext()) {
            Stage s = (Stage)stageItr.next();
            ListIterator taskItr = s.getTasks().listIterator();
            while (taskItr.hasNext()) {
                Task t = (Task)taskItr.next();
                if (t.getId() == taskId) {
                    taskItr.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getPostitColor() {
        return postitColor;
    }

    public String getPostitFont() {
        return postitFont;
    }

    public int getPostitSize() {
        return postitSize;
    }

    public String getPostitStyle() {
        return postitStyle;
    }

    public String getPostitWeight() {
        return postitWeight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public void setPostitColor(int postitColor) {
        this.postitColor = postitColor;
    }

    public void setPostitFont(String postitFont) {
        this.postitFont = postitFont;
    }

    public void setPostitSize(int postitSize) {
        this.postitSize = postitSize;
    }

    public void setPostitStyle(String postitStyle) {
        this.postitStyle = postitStyle;
    }

    public void setPostitWeight(String postitWeight) {
        this.postitWeight = postitWeight;
    }

    public void setVersionID(int versionID) {
        this.versionID = versionID;
    }

    public int getProjectID() {
        return projectID;
    }
    public void setStages( List<Stage> stages) {
        this.stages = stages;
    }

    public List<Stage> getStages() {
        return stages;
    }
    public int getVersionId() {
        return versionID;
    }

}
