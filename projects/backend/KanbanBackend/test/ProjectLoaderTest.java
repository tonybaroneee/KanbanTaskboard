/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import junit.framework.TestCase;
import kanbanbackend.db.ProjectDaoImpl;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.db.StageDaoImpl;
import kanbanbackend.db.TaskDaoImpl;
import kanbanbackend.db.WorkspaceDaoImpl;
import kanbanbackend.model.Project;
import kanbanbackend.model.Stage;
import kanbanbackend.model.Task;
import kanbanbackend.model.Workspace;
import kanbanbackend.util.ProjectLoader;
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
public class ProjectLoaderTest extends TestCase {

    public ProjectLoaderTest() {
        SQLDriver.instance().initialize("src/kanbanbackend/db/KanbanDB.sqlite",
                "src/kanbanbackend/db/KanbanBacklog.sqlite");
    }

    public void testLoadProject() throws SQLException {
        Project project = new Project("Save Middle Earth", "Council of Elrond",
                "Gandalf", "Aragorn",
                new GregorianCalendar(2010, 10-1, 05).toString(),
                   new GregorianCalendar(2010, 10-1, 05).toString());
        Workspace workspace = new Workspace("Middle Earth", 1, project.getId());
        Stage stage1 = new Stage("Fellowship", 9, workspace.getId(), 1);
        Stage stage2 = new Stage("Two Towers", 9, workspace.getId(), 2);
        Stage stage3 = new Stage("Return of the King", 9, workspace.getId(), 3);
        Task task1 = new Task("Form fellowship", "i'm coming too!",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage1.getId(), 1, false);
        Task task2 = new Task("Survive Moria", "fly you fools",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage1.getId(), 1, false);
        Task task3 = new Task("Fail at Amon Hen", "they took the little ones",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage1.getId(), 1, false);
        Task task4 = new Task("Turtle at Helm's deep", "nobody tosses a dwarf",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage2.getId(), 1, false);
        Task task5 = new Task("Zerg saruman", "the ents are going to war",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage2.getId(), 1, false);
        Task task6 = new Task("/follow Gollum", "tators, whats tators",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage2.getId(), 1, false);
        Task task7 = new Task("Knock on front door", "For frodo",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage3.getId(), 1, false);
        Task task8 = new Task("Calvalry rush", "ride now",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage3.getId(), 1, false);
        Task task9 = new Task("Just let it go!", "The ring is mine",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage3.getId(), 1, false);

        ProjectLoader loader = new ProjectLoader();
        Project project1 = loader.loadProject(project.getId());

        ProjectDaoImpl.instance(null).delete(project);
        WorkspaceDaoImpl.instance(null).delete(workspace);
        StageDaoImpl.instance(null).delete(stage1);
        StageDaoImpl.instance(null).delete(stage2);
        StageDaoImpl.instance(null).delete(stage3);
        TaskDaoImpl.instance(null).delete(task1);
        TaskDaoImpl.instance(null).delete(task2);
        TaskDaoImpl.instance(null).delete(task3);
        TaskDaoImpl.instance(null).delete(task4);
        TaskDaoImpl.instance(null).delete(task5);
        TaskDaoImpl.instance(null).delete(task6);
        TaskDaoImpl.instance(null).delete(task7);
        TaskDaoImpl.instance(null).delete(task8);
        TaskDaoImpl.instance(null).delete(task9);

        SQLDriver.instance().closeDaoConnection();


        assertEquals(project.getName(), project1.getName());
        assertEquals(workspace.getName(),
                project1.getWorkspace().getName());
        assertEquals(stage3.getName(),
                project1.getWorkspace().getStages().get(2).getName());
        assertEquals(task8.getName(),
                project1.getWorkspace().getStages().get(2).
                getTasks().get(1).getName());
    }

    public void testVersionProject() throws SQLException {
        Project project = new Project("Save Middle Earth", "Council of Elrond",
                "Gandalf", "Aragorn",
                new GregorianCalendar(2010, 10-1, 05).toString(),
                   new GregorianCalendar(2010, 10-1, 05).toString());
        Workspace workspace = new Workspace("Middle Earth", 1, project.getId());
        Stage stage1 = new Stage("Fellowship", 9, workspace.getId(), 1);
        Stage stage2 = new Stage("Two Towers", 9, workspace.getId(), 2);
        Stage stage3 = new Stage("Return of the King", 9, workspace.getId(), 3);
        Task task1 = new Task("Form fellowship", "i'm coming too!",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage1.getId(), 1, false);
        Task task2 = new Task("Survive Moria", "fly you fools",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage1.getId(), 1, false);
        Task task3 = new Task("Fail at Amon Hen", "they took the little ones",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage1.getId(), 1, false);
        Task task4 = new Task("Turtle at Helm's deep", "nobody tosses a dwarf",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage2.getId(), 1, false);
        Task task5 = new Task("Zerg saruman", "the ents are going to war",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage2.getId(), 1, false);
        Task task6 = new Task("/follow Gollum", "tators, whats tators",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage2.getId(), 1, false);
        Task task7 = new Task("Knock on front door", "For frodo",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage3.getId(), 1, false);
        Task task8 = new Task("Calvalry rush", "ride now",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage3.getId(), 1, false);
        Task task9 = new Task("Just let it go!", "The ring is mine",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 05).getTime(), stage3.getId(), 1, false);

        ProjectLoader loader = new ProjectLoader();

        Project project1 = loader.loadProject(project.getId());
        
        Project projectFinal = loader.versionProject(project1);
    }

    /**
     * Loads a specific project version
     */
    



}