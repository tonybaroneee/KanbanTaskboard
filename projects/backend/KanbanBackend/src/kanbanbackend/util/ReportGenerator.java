/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kanbanbackend.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kanbanbackend.db.UserDaoImpl;
import kanbanbackend.model.Project;
import kanbanbackend.model.Stage;
import kanbanbackend.model.Task;
import kanbanbackend.model.Workspace;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Generates reports based on team performance
 * This will include which tasks are current, and which have / have not been
 * completed by the task completion date.
 * @author kyle
 */
public class ReportGenerator extends HttpServlet{

    public Project project;
    public List<Task> allTasks;
    public List<Task> currentTasks;
    public List<Task> completedTasks;

    /*
     * Instantiate a ReportGenerator for a project
     */
    public ReportGenerator(Project project) {
        this.project = project;
        this.allTasks = this.getTasks();
        this.currentTasks = this.getCurrentTasks(allTasks);
        this.completedTasks = this.getCompletedTasks(allTasks);
    }
    
    /**
     * Gets all tasks from a project
     * @param project
     * @return
     */
    public List<Task> getTasks() {
        
        Workspace workspace = project.getWorkspace();
        List<Stage> stages = workspace.getStages();
        List<Task> tasks = new ArrayList<Task>();

        for (int i = 0; i < stages.size(); i++) {
            tasks.addAll(stages.get(i).getTasks());
        }

        return tasks;

    }

    /**
     * Gets all the tasks past completion date
     * @param allTasks
     * @return
     */
    public List<Task> getCompletedTasks(List<Task> allTasks) {

        List<Task> pastTasks = new ArrayList<Task>();

        Date today = Calendar.getInstance().getTime();

        for (int i = 0; i < allTasks.size(); i++) {
            if (allTasks.get(i).getCompletionDate().compareTo(today) < 0) {
                pastTasks.add(allTasks.get(i));
            }
        }

        return pastTasks;
    }

    /**
     * Get all tasks that are not past completion date
     * @param allTasks
     * @return
     */
    public List<Task> getCurrentTasks(List<Task> allTasks) {

        List<Task> currentTasks = new ArrayList<Task>();

        Date today = Calendar.getInstance().getTime();

        for(int i = 0; i < allTasks.size(); i++) {

            if ((allTasks.get(i).getCreationDate().compareTo(today) < 0) &&
                    (allTasks.get(i).getCompletionDate().compareTo(today) >= 0)) {
                currentTasks.add(allTasks.get(i));
            }
            
        }

        return currentTasks;
    }

    public void createReport(Document doc, HttpServletResponse response) throws DocumentException,
            FileNotFoundException, SQLException, IOException {
        PdfWriter writer = PdfWriter.getInstance(doc, response.getOutputStream());
        //PdfWriter writer = null;
        //ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        //writer = PdfWriter.getInstance(doc, baosPDF);
        //Add Project name

        doc.open();
        doc.add(new Paragraph(project.getName() + "\n",
                FontFactory.getFont(FontFactory.TIMES_BOLD, 18, BaseColor.BLACK)));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Current Tasks" + "\n",
                FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK)));

        PdfPTable t1 = new PdfPTable(5);
        t1.addCell("Task Name");
        t1.addCell("Task Start Date");
        t1.addCell("Task Completion Date");
        t1.addCell("Task Owner");
        t1.addCell("Task Completed");

        for (Task task : currentTasks) {
            t1.addCell(task.getName());
            t1.addCell(formatDate(task.getCreationDate()));
            t1.addCell(formatDate(task.getCompletionDate()));
            t1.addCell(UserDaoImpl.instance(null).queryForId(task.getOwnerId()).getName());
            t1.addCell(new Boolean(task.getCompleted()).toString());
        }

        doc.add(new Paragraph(" "));
        doc.add(t1);
        doc.add(new Paragraph("Completed Tasks",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK)));

        PdfPTable t2 = new PdfPTable(5);
        t2.addCell("Task Name");
        t2.addCell("Task Start Date");
        t2.addCell("Task Completion Date");
        t2.addCell("Task Owner");
        t2.addCell("Task Completed");
        for (Task task : completedTasks) {
            t2.addCell(task.getName());
            t2.addCell(formatDate(task.getCreationDate()));
            t2.addCell(formatDate(task.getCompletionDate()));
            t2.addCell(UserDaoImpl.instance(null).queryForId(task.getOwnerId()).getName());
            t2.addCell(new Boolean(task.getCompleted()).toString());
        }

        doc.add(new Paragraph(" "));
        doc.add(t2);
    }

    /**
     * Formats date string to yyyy-mm-dd
     * @param d
     * @return
     */
    public String formatDate(Date d) {
        String date = d.toString();
        String regex = "00:00:00 \\w\\w\\w";
        date = date.replaceAll(regex, "");
        return date;
    }

    public void createReport() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
