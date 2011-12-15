/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kanbanbackend.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.stmt.StatementBuilder;
import com.j256.ormlite.stmt.Where;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kanbanbackend.model.Stage;

/**
 *
 * @author Kle
 */
public class StageDaoImpl extends BaseDaoImpl<Stage, Object> implements StageDao{

    private static StageDaoImpl instance;
    private static Object padlock = new Object();

    private StageDaoImpl(DatabaseType databaseType) throws SQLException {
        super(databaseType, Stage.class);
    }

     /*
     * Retrieve the instance of the StageDaoImpl. This
     * method is thread-safe.
     */
    public static StageDaoImpl instance(DatabaseType databaseType) throws SQLException{
        if (!SQLDriver.instance().isInitialized()) {
            SQLDriver.instance().initialize("KanbanDB.sqlite", "KanbanBacklog.sqlite");
        }
        if (instance == null) {
            synchronized (padlock){
                instance = new StageDaoImpl(databaseType);
            }
        }
        return instance;
    }

    public List<Stage> getAllStages() {
        List<Stage> stages = new ArrayList<Stage>();

        for(Stage stage : this) {
            stages.add(stage);
        }

        return stages;
    }

    public List<Stage> getStages(int workspaceID) throws SQLException {
        List<Stage> stages;
        StatementBuilder<Stage, Object> queryBuilder = this.statementBuilder();
        Where where = queryBuilder.where();
        where.eq("StageWorkspaceID", workspaceID);
        stages = this.query(queryBuilder.prepareStatement());

        return stages;
    }

}
