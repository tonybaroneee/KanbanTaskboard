import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import junit.framework.TestCase;
import kanbanbackend.model.Stage;
import kanbanbackend.model.Workspace;
import kanbanbackend.model.Task;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the Workspace and its implemented functionality.
 *
 * @author caw
 */
public class WorkspaceTest extends TestCase {

    private Workspace ws;

    /*
     * Test the ability to move a task between stages on a workspace
     */
    public void testTaskMove() {
        ws = new Workspace("Workspace1", 1, 1);
        Stage s1 = new Stage("stage 1", 5, 1, 2);
        Stage s2 = new Stage("stage 2", 5, 1 ,2);
        ws.addStage(0, s1);
        ws.addStage(1, s2);
        Task t1 = new Task("Flush toilet", "Press the handle", new GregorianCalendar(2010, 10-1, 05).getTime(),
                    new GregorianCalendar(2010, 10-1, 05).getTime(), 2, 3, false);
        Task t2 = new Task("Flush toilet", "Press the handle", new GregorianCalendar(2010, 10-1, 05).getTime(),
                    new GregorianCalendar(2010, 10-1, 05).getTime(), 2, 3, false);
        s1.addTask(t1);
        s2.addTask(t2);
        //System.out.println(s1);
        //System.out.println(s2);
        Task t = s1.removeTask(t1.getId());
        assertNotNull(t);
        boolean b = s2.addTask(t);
        assertEquals(b, true);
        System.out.println(s1);
        System.out.println(s2);
    }

}
