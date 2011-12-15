/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import junit.framework.TestCase;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.db.TaskUsersTupleDaoImpl;
import kanbanbackend.model.TaskUsersTuple;
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
public class TaskUsersTupleDBTest extends TestCase {

    public TaskUsersTupleDBTest() {
        SQLDriver.instance().initialize("src/kanbanbackend/db/KanbanDB.sqlite",
                "src/kanbanbackend/db/KanbanBacklog.sqlite");
    }

    public void testGetTaskUsersTuple () throws SQLException {
        TaskUsersTuple put = new TaskUsersTuple(2, 3);
        TaskUsersTuple put1 = TaskUsersTupleDaoImpl.instance(null).queryForId(SQLDriver.instance().getRowId("XREF_TaskUsers") - 1);
        assertEquals(put1.getTaskID(), put.getTaskID());
        TaskUsersTupleDaoImpl.instance(null).delete(put);
        SQLDriver.instance().closeDaoConnection();

    }

}