
import java.sql.SQLException;
import junit.framework.TestCase;
import kanbanbackend.db.ProjectUsersTupleDaoImpl;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.model.ProjectUsersTuple;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ian
 */
public class ProjectUsersTupleDBTest extends TestCase {

    public ProjectUsersTupleDBTest() {
        SQLDriver.instance().initialize("src/kanbanbackend/db/KanbanDB.sqlite",
                "src/kanbanbackend/db/KanbanBacklog.sqlite");
    }

    public void testGetProjectUsersTuple () throws SQLException {
        ProjectUsersTuple put = new ProjectUsersTuple(1, 1, 1);
        ProjectUsersTuple put1 = ProjectUsersTupleDaoImpl.instance(null).queryForId(SQLDriver.instance().getRowId("XREF_ProjectUsers") - 1);
        assertEquals(put1.getProjectUsersProjectID(), put.getProjectUsersProjectID());
        ProjectUsersTupleDaoImpl.instance(null).delete(put);
        SQLDriver.instance().closeDaoConnection();
        
    }



}
