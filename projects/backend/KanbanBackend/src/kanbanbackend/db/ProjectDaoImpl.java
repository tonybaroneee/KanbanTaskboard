package kanbanbackend.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.db.DatabaseType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kanbanbackend.model.Project;

/**
 * This class represents the DAO object for the project class
 *
 * @author caw
 */
public class ProjectDaoImpl extends BaseDaoImpl<Project, Object> implements ProjectDao{

    private static ProjectDaoImpl instance;
    private static Object padlock = new Object();

    private ProjectDaoImpl(DatabaseType databaseType) throws SQLException {
        super(databaseType, Project.class);
    }

     /*
     * Retrieve the instance of the ProjectDaoImpl. This
     * method is thread-safe.
     */
    public static ProjectDaoImpl instance(DatabaseType databaseType) throws SQLException{
        if (!SQLDriver.instance().isInitialized()) {
            SQLDriver.instance().initialize("KanbanDB.sqlite", "KanbanBacklog.sqlite");
        }
        if (instance == null) {
            synchronized (padlock){
                instance = new ProjectDaoImpl(databaseType);
            }
        }
        return instance;
    }


    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<Project>();

        for(Project project : this) {
            projects.add(project);
        }

        return projects;
    }

}
