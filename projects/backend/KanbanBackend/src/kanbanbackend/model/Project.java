package kanbanbackend.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.SQLException;
import java.util.Date;
import kanbanbackend.db.ProjectDaoImpl;
import kanbanbackend.db.SQLDriver;

/**
 * This class is responsible for managing all relevant information about
 * an instance of a Kanban taskboard project. It is essentially the master
 * in the hierarchy of classes that make up everything that is a project.
 *
 * @author caw
 */
@DatabaseTable (tableName = "Projects")
public class Project {

    // info that needs to be stored in the database...
    private int version;
    private Workspace workspace;

    @DatabaseField(columnName = "ProjectID", id = true)
    private int id;
    @DatabaseField(columnName = "ProjectName")
    private String name;
    @DatabaseField(columnName = "ProjectCompany")
    private String company;
    @DatabaseField(columnName = "ProjectProjectManager")
    private String projectManager;
    @DatabaseField(columnName = "ProjectDeveloper")
    private String leadDeveloper;
    @DatabaseField(columnName = "ProjectStartDate")
    private String startDate;
    @DatabaseField(columnName = "ProjectEndDate")
    private String endDate;

    /*
     * Empty constructor for use with ORM
     */
    public Project() {
        
    }

    /*
     * Create a new project given the set of parameters
     */
    public Project(String name, String company, String projectManager,
            String leadDeveloper, String startDate, String endDate){
        this.name = name;
        this.company = company;
        this.projectManager = projectManager;
        this.leadDeveloper = leadDeveloper;
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = SQLDriver.instance().getRowId("Projects");
        try {
            ProjectDaoImpl.instance(null).create(this);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /* Accessors and mutators for project data */
    public String getCompany() {
        return company;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getId() {
        return id;
    }

    public String getLeadDeveloper() {
        return leadDeveloper;
    }

    public String getName() {
        return name;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getVersion() {
        return version;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLeadDeveloper(String leadDeveloper) {
        this.leadDeveloper = leadDeveloper;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }


}
