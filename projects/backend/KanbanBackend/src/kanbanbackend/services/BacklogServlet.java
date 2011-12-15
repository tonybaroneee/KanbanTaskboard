/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kanbanbackend.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import kanbanbackend.db.SQLDriver;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import kanbanbackend.model.Project;
import kanbanbackend.model.Stage;
import kanbanbackend.model.Task;
import kanbanbackend.model.Workspace;
import kanbanbackend.util.ProjectLoader;

/**
 * This class is responsible for logging all events that occur within a project.
 *
 * @author caw
 */
public class BacklogServlet extends HttpServlet {

    /*
     * Initialize the servlet
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /*
     * Respond to a HTTP GET request
     */
    public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
                    throws ServletException, IOException{
            doPost(request, response);
    }

    /*
     * Respond to a HTTP POST request
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int projectId = Integer.parseInt(request.getParameter("projectId"));
        System.out.println("DEBUG: project id = " + projectId);
        ProjectLoader loader = new ProjectLoader();
        Project proj = loader.loadProject(projectId);

        if (proj == null) return;

        OutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            //TODO: fetch the name from the xml file that is passed in
            response.setHeader("Content-Disposition", "attachment; filename=sampleName.xls");

            // create the document and the individual sheets that will go on the document
            WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet projectSheet = w.createSheet("Project", 0);
            WritableSheet workspaceSheet = w.createSheet("Workspace", 0);
            WritableSheet stageSheet = w.createSheet("Stage", 0);
            WritableSheet taskSheet = w.createSheet("Task", 0);
            WritableSheet projectUsersSheet = w.createSheet("ProjectUsers", 0);
            WritableSheet projectVersionsSheet = w.createSheet("ProjectVersions", 0);
            WritableSheet taskUsersSheet = w.createSheet("TaskUsers", 0);

            // TODO: include all information from objects AND database (for completeness)

            /* project sheet */
            // fill in column headers for project sheet
            projectSheet.addCell(new Label(0, 0, "ProjectID"));
            projectSheet.addCell(new Label(1, 0, "ProjectName"));
            projectSheet.addCell(new Label(2, 0, "ProjectCompany"));
            projectSheet.addCell(new Label(3, 0, "ProjectCreatorUserID"));
            projectSheet.addCell(new Label(4, 0, "ProjectStartDate"));
            projectSheet.addCell(new Label(5, 0, "ProjectEndDate"));

            // fill in the data for these headings
            projectSheet.addCell(new Label(0, 1, "" + proj.getId()));
            projectSheet.addCell(new Label(1, 1, proj.getName()));
            projectSheet.addCell(new Label(2, 1, proj.getCompany()));
            projectSheet.addCell(new Label(3, 1, "" + 0));
            projectSheet.addCell(new Label(4, 1, "null"));
            projectSheet.addCell(new Label(5, 1, "null"));

            /* workspace sheet */
            // fill in the column headers for workspace sheet
            Workspace ws = proj.getWorkspace();
            workspaceSheet.addCell(new Label(0, 0, "WorkspaceID"));
            workspaceSheet.addCell(new Label(1, 0, "WorkspaceName"));
            workspaceSheet.addCell(new Label(2, 0, "WorkspaceFont"));
            workspaceSheet.addCell(new Label(3, 0, "WorkspaceStyle"));
            workspaceSheet.addCell(new Label(4, 0, "WorkspaceWeight"));
            workspaceSheet.addCell(new Label(5, 0, "WorkspaceSize"));
            workspaceSheet.addCell(new Label(6, 0, "WorkspaceColor"));
            workspaceSheet.addCell(new Label(7, 0, "WorkspaceNumColumns"));
            workspaceSheet.addCell(new Label(8, 0, "WorkspaceProjectID"));

            // fill in the data
            workspaceSheet.addCell(new Label(0, 1, "" + ws.getId()));
            workspaceSheet.addCell(new Label(1, 1, ws.getName()));
            workspaceSheet.addCell(new Label(2, 1, ws.getPostitFont()));
            workspaceSheet.addCell(new Label(3, 1, ws.getPostitStyle()));
            workspaceSheet.addCell(new Label(4, 1, ws.getPostitWeight()));
            workspaceSheet.addCell(new Label(5, 1, "" + ws.getPostitSize()));
            workspaceSheet.addCell(new Label(6, 1, "" + ws.getPostitColor()));
            workspaceSheet.addCell(new Label(7, 1, "" + ws.getNumColumns()));
            workspaceSheet.addCell(new Label(8, 1, "" + ws.getProjectID()));

            /* stage sheet */
            // fill in the column headers for stage sheet
            stageSheet.addCell(new Label(0, 0, "StageID"));
            stageSheet.addCell(new Label(1, 0, "StageName"));
            stageSheet.addCell(new Label(2, 0, "StageWorkspaceID"));
            stageSheet.addCell(new Label(3, 0, "StageOrderNumber"));
            stageSheet.addCell(new Label(4, 0, "StageCapacity"));
            stageSheet.addCell(new Label(5, 0, "StageTaskCount"));

            // fill in the data for the task sheet
            for (int i = 0; i < ws.getStages().size(); i++) {
                Stage s = ws.getStages().get(i);
                stageSheet.addCell(new Label(0, i + 1, "" + s.getId()));
                stageSheet.addCell(new Label(1, i + 1, s.getName()));
                stageSheet.addCell(new Label(2, i + 1, "" + ws.getId()));
                stageSheet.addCell(new Label(3, i + 1, "" + s.getOrderNumber()));
                stageSheet.addCell(new Label(4, i + 1, "" + s.getCapacity()));
                stageSheet.addCell(new Label(5, i + 1, "" + s.getNumTasks()));
            }

            /* task sheet */
            // fill in the column headers for task sheet
            taskSheet.addCell(new Label(0, 0, "TaskID"));
            taskSheet.addCell(new Label(1, 0, "TaskName"));
            taskSheet.addCell(new Label(2, 0, "TaskDescription"));
            taskSheet.addCell(new Label(3, 0, "TaskStageID"));
            taskSheet.addCell(new Label(4, 0, "TaskOwnerID"));
            taskSheet.addCell(new Label(5, 0, "TaskDateCreated"));
            taskSheet.addCell(new Label(6, 0, "TaskDateCompleted"));

            // fill in the data for the task sheet
            int stageNum = 0;
            for (Stage s : ws.getStages()) {
                for (int i = 0; i < s.getTasks().size(); i++) {
                    Task t = s.getTasks().get(i);
                    taskSheet.addCell(new Label(0, (stageNum * 6) + i + 1, "" + t.getId()));
                    taskSheet.addCell(new Label(1, (stageNum * 6) + i + 1, t.getName()));
                    taskSheet.addCell(new Label(2, (stageNum * 6) + i + 1, t.getDesc()));
                    taskSheet.addCell(new Label(3, (stageNum * 6) + i + 1, "" + s.getId()));
                    taskSheet.addCell(new Label(4, (stageNum * 6) + i + 1, "" + t.getOwnerId()));
                    taskSheet.addCell(new Label(5, (stageNum * 6) + i + 1, t.getCreationDate().toString()));
                    taskSheet.addCell(new Label(6, (stageNum * 6) + i + 1, t.getCompletionDate().toString()));
                }
                stageNum++;
            }

            /* project users sheet */
            projectUsersSheet.addCell(new Label(0, 0, "ProjectUsersUserID"));
            String query = "SELECT * FROM XREF_ProjectUsers WHERE ProjectUsersProjectID = " + proj.getId();
            ResultSet set = SQLDriver.instance().getStatement().executeQuery(query);
            int rowIndex = 1;
            projectUsersSheet.addCell(new Label(0, rowIndex++, "" + set.getInt("ProjectUsersUserID")));
            while (set.next()) {
                projectUsersSheet.addCell(new Label(0, rowIndex++, "" + set.getInt("ProjectUsersUserID")));
            }

            // write the content to the excel file and close it up
            w.write();
            w.close();
        } catch (Exception e){
            System.err.println(e.getMessage());
            throw new ServletException("Exception in BacklogServlet", e);
        } finally{
            if (out != null)
            out.close();
        }
    }
}
