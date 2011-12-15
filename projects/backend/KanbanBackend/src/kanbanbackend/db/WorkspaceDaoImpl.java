package kanbanbackend.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.stmt.StatementBuilder;
import com.j256.ormlite.stmt.Where;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kanbanbackend.model.Stage;
import kanbanbackend.model.Workspace;

/**
 *
 * @author caw
 */
public class WorkspaceDaoImpl extends BaseDaoImpl<Workspace, Object> implements WorkspaceDao {

    // singleton instance variables
    private static WorkspaceDaoImpl instance;
    private static Object padlock = new Object();

    private WorkspaceDaoImpl(DatabaseType databaseType) throws SQLException {
        super(databaseType, Workspace.class);
    }
     /*
     * Retrieve the instance of the WorkspaceDaoImpl. This
     * method is thread-safe.
     */
    public static WorkspaceDaoImpl instance(DatabaseType databaseType) throws SQLException{
        if (!SQLDriver.instance().isInitialized()) {
            SQLDriver.instance().initialize("KanbanDB.sqlite", "KanbanBacklog.sqlite");
        }
        if (instance == null) {
            synchronized (padlock){
                instance = new WorkspaceDaoImpl(databaseType);
            }
        }
        return instance;
    }

    /**
     * Return all workspaces in DB.
     * @return
     */
    public List<Workspace> getAllWorkspaces() {
        List<Workspace> workspaces = new ArrayList<Workspace>();

        for(Workspace workspace : this) {
            workspaces.add(workspace);
        }

        return workspaces;
    }

    /**
     * Get most recent workspace from DB
     */
    public Workspace getWorkspace(int projectID) throws SQLException {
        List<Workspace> workspaces;
        StatementBuilder<Workspace, Object> queryBuilder = this.statementBuilder();
        Where where = queryBuilder.where();
        where.eq("WorkspaceProjectID", projectID);
        workspaces = this.query(queryBuilder.prepareStatement());
        
        //Get workspace with most recent versionID
        int newestId = -1;
        Workspace newestWorkspace = null;
        for (Workspace workspace : workspaces) {
            if (workspace.getVersionId() > newestId ) {
                newestWorkspace = workspace;
                newestId = workspace.getVersionId();
            }
        }

        List<Stage> stages = StageDaoImpl.instance(null).getStages(newestWorkspace.getId());
        newestWorkspace.setStages(stages);
        
        return newestWorkspace;
    }
    /**
     * Get most specified version of a workspace from the DB
     */
    public Workspace getWorkspace(int projectID, int versionID) throws SQLException {
        List<Workspace> workspaces;
        StatementBuilder<Workspace, Object> queryBuilder = this.statementBuilder();
        Where where = queryBuilder.where();
        where.eq("WorkspaceProjectID", projectID);
        where.and();
        where.eq("WorkspaceVersionID", versionID);
        workspaces = this.query(queryBuilder.prepareStatement());

        return workspaces.get(0);
    }
    
}
