/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.List;
import junit.framework.TestCase;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.db.WorkspaceDaoImpl;
import kanbanbackend.model.Workspace;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kle
 */
public class WorkspaceDBTest extends TestCase{

    public WorkspaceDBTest() {
        SQLDriver.instance().initialize("src/kanbanbackend/db/KanbanDB.sqlite",
                "src/kanbanbackend/db/KanbanBacklog.sqlite");
    }

    public void testGetWorkspace() throws SQLException {
        Workspace workspace = new Workspace("SE", 1, 1);
        Workspace workspace1 = WorkspaceDaoImpl.instance(null).queryForId(SQLDriver.instance().getRowId("Workspaces") - 1);
        assertEquals(workspace1.getName(), workspace.getName());
        WorkspaceDaoImpl.instance(null).delete(workspace);
        SQLDriver.instance().closeDaoConnection();
    }

    public void testGetAllWorkspaces() throws SQLException {
        Workspace workspace1 = new Workspace("Bat Cave", 1, 1);
        Workspace workspace2 = new Workspace("Frotress of Solitude", 2, 1);

        List<Workspace> workspaces = WorkspaceDaoImpl.instance(null).getAllWorkspaces();

        assertEquals(workspaces.get(0).getName(), workspace1.getName());
        assertEquals(workspaces.get(1).getName(), workspace2.getName());

        WorkspaceDaoImpl.instance(null).delete(workspace1);
        WorkspaceDaoImpl.instance(null).delete(workspace2);

        SQLDriver.instance().closeDaoConnection();

    }


}