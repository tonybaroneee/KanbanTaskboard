package kanbanbackend.db;


import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.stmt.StatementBuilder;
import com.j256.ormlite.stmt.Where;
import java.sql.SQLException;
import java.util.List;
import kanbanbackend.model.ProjectUsersTuple;




public class ProjectUsersTupleDaoImpl extends BaseDaoImpl<ProjectUsersTuple, Object>
        implements ProjectUsersTupleDao{

    private static ProjectUsersTupleDaoImpl instance;
    private static Object padlock = new Object();

    private ProjectUsersTupleDaoImpl(DatabaseType databaseType) throws SQLException {
        super(databaseType, ProjectUsersTuple.class);
    }


    public static ProjectUsersTupleDaoImpl instance(DatabaseType databaseType)
            throws SQLException {


        if (!SQLDriver.instance().isInitialized()) {
            SQLDriver.instance().initialize("KanbanDB.sqlite", "KanbanBacklog.sqlite");
        }
        if (instance == null) {
            synchronized (padlock){
                instance = new ProjectUsersTupleDaoImpl(databaseType);
            }
        }
        return instance;
    }

    public List<ProjectUsersTuple> getProjectIDsForUserID (int userID) throws SQLException {


       StatementBuilder<ProjectUsersTuple, Object> queryBuilder = this.statementBuilder();
       Where where = queryBuilder.where();
       where.eq("ProjectUsersUserID", userID);
       List<ProjectUsersTuple> objs = this.query(queryBuilder.prepareStatement());

       return objs;
    }



}