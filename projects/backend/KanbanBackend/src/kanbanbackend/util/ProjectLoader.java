/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kanbanbackend.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kanbanbackend.db.ProjectDaoImpl;
import kanbanbackend.db.StageDaoImpl;
import kanbanbackend.db.TaskDaoImpl;
import kanbanbackend.db.WorkspaceDaoImpl;
import kanbanbackend.model.Project;
import kanbanbackend.model.Stage;
import kanbanbackend.model.Task;
import kanbanbackend.model.Workspace;

/**
 * Project loader is responsible for loading projects from the database and
 * versioning projects
 * @author Kle
 */
public class ProjectLoader {

    public ProjectLoader() {
    }

    /**
     * Loads a complete project from the database given its ID
     * Will load the most recent version of the project
     * @param projectID
     * @return
     */
    public Project loadProject(int projectID) {
        Project project = null;
        Workspace workspace = null;
        List<Stage> stages = null;
        List<Task> tasks = null;
        try {
            //Populate all components
            project = ProjectDaoImpl.instance(null).queryForId(projectID);
            workspace = WorkspaceDaoImpl.instance(null).getWorkspace(project.getId());
            stages = StageDaoImpl.instance(null).getStages(workspace.getId());

            for (int i = 0; i < stages.size(); i++ ) {
                stages.get(i).setTasks(TaskDaoImpl.instance(null).getTasksFromStageId(stages.get(i).getId()));
            }
            workspace.setStages(stages);
            project.setWorkspace(workspace);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return project;
    }
    /**
     * Loads a complete project from the database given its ID
     * Will load workspace with the specified vesionID
     * @param projectID
     * @return
     */
    public Project loadProject(int projectID, int versionID) {
        Project project = null;
        Workspace workspace = null;
        List<Stage> stages = null;
        List<Task> tasks = null;
        try {
            //Populate all components
            project = ProjectDaoImpl.instance(null).queryForId(projectID);
            workspace = WorkspaceDaoImpl.instance(null).getWorkspace(project.getId(), versionID);
            stages = StageDaoImpl.instance(null).getStages(workspace.getId());

            for (int i = 0; i < stages.size(); i++ ) {
                stages.get(i).setTasks(TaskDaoImpl.instance(null).getTasksFromStageId(stages.get(i).getId()));
            }

            workspace.setStages(stages);
            project.setWorkspace(workspace);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return project;
    }


    /**
     * Versions a project by copying that project in the database.  The project
     * returned is the new project with incremented version number.
     * @param project
     * @return
     */
    public Project versionProject(Project project) throws SQLException {

        Workspace workspace = project.getWorkspace();
        List<Stage> stages = workspace.getStages();
        List<Task> tasks = new ArrayList<Task>();
        for (int i = 0; i < stages.size(); i++) {
            tasks.addAll(stages.get(i).getTasks());
        }

        // Create new objects, persisting them to database

        Workspace workspace1 = new Workspace(workspace.getName(),
                workspace.getVersionId() + 1, project.getId());
        for (int i = 0; i < stages.size(); i++) {
            Stage stage1 = new Stage(stages.get(i).getName(),
                    stages.get(i).getCapacity(), workspace1.getId(),
                    stages.get(i).getOrderNumber());
        }
        //New stages
        List<Stage> stages1 =
                StageDaoImpl.instance(null).getStages(workspace1.getId());

        int stageCount = 0;
        for (Stage stage : stages) {
            for (int i = 0; i < stage.getTasks().size(); i++) {
                Task task1 = new Task(stage.getTasks().get(i).getName(),
                    stage.getTasks().get(i).getDesc(),
                    stage.getTasks().get(i).getCreationDate(),
                    stage.getTasks().get(i).getCompletionDate(), 
                    stages1.get(stageCount).getId(),
                    stage.getTasks().get(i).getOwnerId(),
                    stage.getTasks().get(i).getCompleted());
            }
            stageCount++;
        }
        
        Project newProject = loadProject(project.getId());

        return newProject;
    }

}
