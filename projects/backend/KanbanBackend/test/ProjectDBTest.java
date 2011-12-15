/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import junit.framework.TestCase;
import kanbanbackend.db.ProjectDaoImpl;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.model.Project;
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
public class ProjectDBTest extends TestCase{

    public ProjectDBTest() {
        SQLDriver.instance().initialize("src/kanbanbackend/db/KanbanDB.sqlite",
                "src/kanbanbackend/db/KanbanBacklog.sqlite");
    }

    public void testGetProject() throws SQLException {
        Project project = new Project("Manhattan", "USA", "Groves", "Oppenheimer",
                new GregorianCalendar(2010, 10-1, 05).toString(),
                   new GregorianCalendar(2010, 10-1, 05).toString());
        Project project1 = ProjectDaoImpl.instance(null).
                queryForId(SQLDriver.instance().getRowId("Projects")-1);
        assertEquals(project1.getName(), project.getName());
        ProjectDaoImpl.instance(null).delete(project);
        SQLDriver.instance().closeDaoConnection();
    }

    public void testGetAllProjects() throws SQLException {
        Project project1 = new Project("Manhattan", "USA", "Groves", "Oppenheimer",
                new GregorianCalendar(2010, 10-1, 05).toString(),
                   new GregorianCalendar(2010, 10-1, 05).toString());
        Project project2 = new Project("Destroy Ring", "Elrond", "Gandalf", "Aragorn",
                new GregorianCalendar(2010, 10-1, 05).toString(), new GregorianCalendar(2010, 10-1, 05).toString());

        List<Project> projects = ProjectDaoImpl.instance(null).getAllProjects();

        assertEquals(projects.get(0).getName(), project1.getName());
        assertEquals(projects.get(1).getName(), project2.getName());

        ProjectDaoImpl.instance(null).delete(project1);
        ProjectDaoImpl.instance(null).delete(project2);

        SQLDriver.instance().closeDaoConnection();
    }

}