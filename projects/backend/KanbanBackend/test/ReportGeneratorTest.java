/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import kanbanbackend.db.SQLDriver;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kanbanbackend.util.ReportGenerator;
import kanbanbackend.model.Stage;
import kanbanbackend.model.Workspace;
import kanbanbackend.model.Task;
import java.util.Date;
import java.util.GregorianCalendar;
import kanbanbackend.model.Project;
import kanbanbackend.util.ProjectLoader;
import junit.framework.TestCase;
import kanbanbackend.model.User;
import static org.junit.Assert.*;

/**
 *
 * @author kyle
 */
public class ReportGeneratorTest extends TestCase {

    public ReportGeneratorTest() throws DocumentException, FileNotFoundException {
        SQLDriver.instance().initializeDao("src/kanbanbackend/db/KanbanDB.sqlite");
    }

    public void testRandom() {
        User Frodo = new User("Frodo", "hobbiton@theshire", "halfling");
        User Elrond = new User("Elrond", "rivendell@ME", "star-dome");
        User Boromir = new User("Boromir", "gondor@ME", "bigbro");
        User Aragorn = new User("Aragorn", "unlisted", "strider");
        User Treebeard = new User("Treebeard", "unlisted", "tree");
        User Gandalf = new User("Gandalf", "valar@aman", "pew pew");


        Project project = new Project("Save Middle Earth", "Council of Elrond",
                "Gandalf", "Aragorn",
                new GregorianCalendar(2010, 10-1, 5).getTime().toString(),
                   new GregorianCalendar(2010, 10-1, 05).getTime().toString());
        System.out.println("HERE" + project.getEndDate().toString());
        Workspace workspace = new Workspace("Middle Earth", 1, project.getId());
        Stage stage1 = new Stage("Fellowship", 9, workspace.getId(), 1);
        Stage stage2 = new Stage("Two Towers", 9, workspace.getId(), 2);
        Stage stage3 = new Stage("Return of the King", 9, workspace.getId(), 3);
        Task task1 = new Task("Form fellowship", "i'm coming too!",
                new GregorianCalendar(2010, 9-1, 05).getTime(),
                   new GregorianCalendar(2010, 9-1, 05).getTime(), stage1.getId(), Elrond.getId(), false);
        Task task2 = new Task("Survive Moria", "fly you fools",
                new GregorianCalendar(2010, 9-1, 05).getTime(),
                   new GregorianCalendar(2010, 9-1, 05).getTime(), stage1.getId(), Gandalf.getId(), false);
        Task task3 = new Task("Fail at Amon Hen", "they took the little ones",
                new GregorianCalendar(2010, 9-1, 05).getTime(),
                   new GregorianCalendar(2010, 9-1, 05).getTime(), stage1.getId(), Boromir.getId(), false);
        Task task4 = new Task("Turtle at Helm's deep", "nobody tosses a dwarf",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 06).getTime(), stage2.getId(), Aragorn.getId(), false);
        Task task5 = new Task("Zerg saruman", "the ents are going to war",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 06).getTime(), stage2.getId(), Treebeard.getId(), false);
        Task task6 = new Task("/follow Gollum", "tators, whats tators",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 10-1, 06).getTime(), stage2.getId(), Frodo.getId(), false);
        Task task7 = new Task("Knock on front door", "For frodo",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 11-1, 15).getTime(), stage3.getId(), Aragorn.getId(), false);
        Task task8 = new Task("Calvalry rush", "ride now",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 11-1, 15).getTime(), stage3.getId(), Aragorn.getId(), false);
        Task task9 = new Task("Just let it go!", "The ring is mine",
                new GregorianCalendar(2010, 10-1, 05).getTime(),
                   new GregorianCalendar(2010, 11-1, 15).getTime(), stage3.getId(), Frodo.getId(), false);



        ProjectLoader loader = new ProjectLoader();
        Project project1 = loader.loadProject(project.getId());

        ReportGenerator report = new ReportGenerator(project1);
//        try {
//            report.createReport();
//        } catch (DocumentException ex) {
//            Logger.getLogger(ReportGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ReportGeneratorTest.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
    }
    
}