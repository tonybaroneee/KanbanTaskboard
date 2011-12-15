/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.List;
import junit.framework.TestCase;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.db.StageDaoImpl;
//import kanbanbackend.model.Stage;
import kanbanbackend.model.Stage;
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
public class StageDBTest extends TestCase{

    public StageDBTest() {
        SQLDriver.instance().initialize("src/kanbanbackend/db/KanbanDB.sqlite",
                "src/kanbanbackend/db/KanbanBacklog.sqlite");
    }

    public void testGetStage() throws SQLException {
        Stage stage = new Stage("Sensorimotor", 8, 2, 5);
        Stage stage1 = StageDaoImpl.instance(null).queryForId(SQLDriver.instance().getRowId("Stages") - 1);
        assertEquals(stage1.getName(), stage.getName());
        StageDaoImpl.instance(null).delete(stage);
        SQLDriver.instance().closeDaoConnection();
    }

    public void testGetAllStages() throws SQLException {
        Stage stage1 = new Stage("Sensorimotor", 8, 2, 5);
        Stage stage2 = new Stage("Pre-operational", 4, 2, 2);

        List<Stage> stages = StageDaoImpl.instance(null).getAllStages();

        assertEquals(stages.get(0).getName(), stage1.getName());
        assertEquals(stages.get(1).getName(), stage2.getName());

        StageDaoImpl.instance(null).delete(stage1);
        StageDaoImpl.instance(null).delete(stage2);

        SQLDriver.instance().closeDaoConnection();

    }


}