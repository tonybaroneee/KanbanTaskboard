/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import junit.framework.TestCase;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.db.TaskDaoImpl;
import kanbanbackend.model.Task;
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
public class TaskDBTest extends TestCase{

    public Task task1;
    public Task task2;
    public Task task3;

    public TaskDBTest() {
        SQLDriver.instance().initialize("src/kanbanbackend/db/KanbanDB.sqlite",
                "src/kanbanbackend/db/KanbanBacklog.sqlite");
    }

    public void testGetTask() throws SQLException {
        Task task1 = new Task("Flush toilet", "Press the handle",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), 2, 3, false);
        Task task = TaskDaoImpl.instance(null).queryForId(SQLDriver.instance().getRowId("Tasks") - 1);
        assertEquals(task.getName(), "Flush toilet");
        TaskDaoImpl.instance(null).delete(task1);
        SQLDriver.instance().closeDaoConnection();
    }

    public void testGetTasksFromStageId() throws SQLException {
        Task task1 = new Task("Flush toilet", "Press the handle",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), 2, 3, false);
        Task task2 = new Task("PokeHobo", "prevents smells",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), 1, 3, false);
        Task task3 = new Task("Shoot bear", "save kids",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), 2, 3, false);
        List<Task> tasks = TaskDaoImpl.instance(null).getTasksFromStageId(2);
        assertEquals(tasks.get(0).getName(), "Flush toilet");
        assertEquals(tasks.get(1).getName(), "Shoot bear");

        TaskDaoImpl.instance(null).delete(task1);
        TaskDaoImpl.instance(null).delete(task2);
        TaskDaoImpl.instance(null).delete(task3);
        SQLDriver.instance().closeDaoConnection();

    }

    public void testGetAllTasks() throws SQLException {
        Task task1 = new Task("Flush toilet", "Press the handle",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), 2, 3, false);
        Task task2 = new Task("PokeHobo", "prevents smells",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), 1, 3, false);
        Task task3 = new Task("Shoot bear", "save kids",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), 2, 3, false);

        List<Task> tasks = TaskDaoImpl.instance(null).getAllTasks();

        assertEquals(tasks.get(0).getName(), task1.getName());
        assertEquals(tasks.get(1).getName(), task2.getName());
        assertEquals(tasks.get(2).getName(), task3.getName());

        TaskDaoImpl.instance(null).delete(task1);
        TaskDaoImpl.instance(null).delete(task2);
        TaskDaoImpl.instance(null).delete(task3);

        SQLDriver.instance().closeDaoConnection();
    }

}