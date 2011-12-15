package kanbanbackend.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.mail.MessagingException;
import kanbanbackend.data.ProjectData;
import kanbanbackend.data.TaskData;
import kanbanbackend.db.ProjectDaoImpl;
import kanbanbackend.db.ProjectUsersTupleDaoImpl;
import kanbanbackend.db.SQLDriver;
import kanbanbackend.db.TaskDaoImpl;
import kanbanbackend.db.UserDaoImpl;
import kanbanbackend.db.WorkspaceDaoImpl;
import kanbanbackend.model.Kanban;
import kanbanbackend.model.Project;
import kanbanbackend.model.ProjectUsersTuple;
import kanbanbackend.model.Stage;
import kanbanbackend.model.Task;
import kanbanbackend.model.User;
import kanbanbackend.model.Workspace;
import kanbanbackend.util.ProjectLoader;

/**
 * This class will handle session-related calls from the front-end
 *
 * @author caw
 */
public class KanbanService {

    /*
     * Add a new user to the database
     */
    public User addNewUser(User u)  {
        
        if (!SQLDriver.instance().isInitialized()) {
            SQLDriver.instance().initialize("KanbanDB.sqlite", "KanbanBacklog.sqlite");
        }

        User newUser = null;

        // send out an email to the new user
        String message = "Hi " + u.getName() + "!\n\nYou have registered - now go sign in!\n";
        String subject = "Kanban Taskboard - User Registration";
        try {
            NotificationService.instance().postMail(u.getEmail(), subject, message,
                    "do-not-reply@kanban-taskboard.com");
            System.out.println("Notification completed without any exceptions.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // create a new user object from the data sent by the frontend
        // this persists a new user to the database
        newUser = new User(u.getName(), u.getEmail(), u.getPassword());

        System.out.println("Persisted new user (" + u.getEmail() + ") to the database");

        return newUser;
    }

    /*
     * Determine if a user with the set of fields already exists in the database
     */
    public boolean userExists(User u) {
        boolean result = true;
        try {
            result = UserDaoImpl.instance(null).userExists(u);
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return result;
    }

    /*
     * Attempt to sign a user into the system and return true if the user was
     * signed in successfully
     */
    public User signIn(String email, String pw) {
        User user = null;
        try {
            user = UserDaoImpl.instance(null).userAccountCheck(email, pw);
            if (user != null) {
                System.out.println("User " + user.getEmail() + " found in system" +
                        ", trying to add them to set of current users.");
                Kanban.instance().userLogon(user);
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return user;
    }

    /*
     * Sign a user out of the system
     */
    public boolean signOut(User u) {
        return Kanban.instance().userLogoff(u);
    }

    /*
     * Create a new project and commit its information to the database
     */
    public Project createNewProject(ProjectData pd) throws SQLException, MessagingException {

        // make sure SQLDrive is initialized
        if (!SQLDriver.instance().isInitialized()) {
            SQLDriver.instance().initialize("KanbanDB.sqlite", "KanbanBacklog.sqlite");
        }

        DateFormat df = DateFormat.getInstance();
        System.out.println("DEBUG: recieved" + pd.toString());
        System.out.println("DEBUG: Starting to make the project and its sub objects..");
        
        Project proj = new Project(pd.name, pd.company, pd.manager,
                pd.developer, pd.startDate, pd.endDate);


        // create the workspace for this specific project and set its properties
        Workspace ws = new Workspace("", 1, proj.getId());
        ws.setPostitColor(pd.postitColor);
        ws.setPostitFont(pd.postitFont);
        ws.setPostitSize(pd.postitSize);
        ws.setPostitStyle(pd.postitStyle);
        ws.setPostitWeight(pd.postitWeight);
        ws.setNumColumns(pd.numColumns);

        // add the stages to the workspace
        for (int i = 0; i < 7; i++) { // only 7 columns per workspace
            String colName = null;
            switch (i) {
                case 0: colName = pd.colName1; break;
                case 1: colName = pd.colName2; break;
                case 2: colName = pd.colName3; break;
                case 3: colName = pd.colName4; break;
                case 4: colName = pd.colName5; break;
                case 5: colName = pd.colName6; break;
                case 6: colName = pd.colName7; break;
            }

            if (colName != null) {
                Stage s = new Stage(colName, 8, ws.getId(), i);
                ws.addStage(i, s);
            } else {
                break;
            }

            WorkspaceDaoImpl.instance(null).update(ws);
        }

        // set the project workspace
        proj.setWorkspace(ws);

        // add users to the XREF table now
        try {
            System.out.println("DEBUG: user list length = " + pd.users.length);

            // add the initial user
            addUserToProject(proj.getId(), proj.getName(), pd.userEmail);

            // now add any users that were provided in the list
            for (int i = 0; i < pd.users.length; i++) {
                boolean result = addUserToProject(proj.getId(), proj.getName(), pd.users[i]);
                // TODO: do something with this result
            }
        }  catch (SQLException ex1) {
            System.err.println("ERROR: " + ex1.getMessage());
            throw ex1;
        } catch (MessagingException ex2) {
            System.err.println("ERROR: " + ex2.getMessage());
            throw ex2;
        }

        return proj;
    }

    /*
     * Add a user to a project
     */
    public boolean addUserToProject(int projectId, String projectName, 
            String userEmail) throws SQLException, MessagingException {

        boolean result = true;
        try {
            // get the user id for the specified user from their email
           User u = UserDaoImpl.instance(null).getUserFromEmail(userEmail);
           if (u != null) { // if the user actually exists in the db
               //Create XREF Object
               ProjectUsersTuple put = new ProjectUsersTuple(projectId, u.getId(), 1);

                // send them an email saying they were put on board
                String message = "Hi " + u.getName() + 
                        "!\n\nYou have been signed up for the following project:\n" +
                        "\n\n" + projectName + "\n\nSign in to get going!";
                String subject = "Kanban Taskboard - User Registration";
                NotificationService.instance().postMail(u.getEmail(), subject, message,
                        "do-not-reply@kanban-taskboard.com");
                System.out.println("Notification completed without any exceptions.");
            } else {
                result = false;
            }
        } catch (SQLException e1) {
            System.err.println("ERROR: " + e1.getMessage());
            throw e1;
        } catch (MessagingException e2) {
            System.err.println("ERROR: " + e2.getMessage());
            throw e2;
        }
        return result;
    }

    /*
     * Retrieve a map of project names to their ID.
     */
    public Map<String, Integer> getProjectsForUser(User u) throws SQLException {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            List<ProjectUsersTuple> tuples =
                    ProjectUsersTupleDaoImpl.instance(null).getProjectIDsForUserID(u.getId());

            // fill in the map with the project names and their IDs
            for (ProjectUsersTuple put : tuples) {
                String name = ProjectDaoImpl.instance(null).queryForId(put.getProjectUsersProjectID()).getName();

                map.put(name, put.getProjectUsersProjectID());
            }
            //set.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw e;
        } 
        
        return map;
    }

    /*
     * Attempt to open a project that exists in the database. If the project is
     * opened successfully, set it up and add it to the list of current projects
     * if it isn't open already.
     */
    public Project openProject(int projectId) {
        Set<Project> openProjects = Kanban.instance().getOpenProjects();
        Project project = null;
        for (Project p : openProjects) {
            if (p.getId() == projectId) {
                project = p;
                break;
            }
        }

        // if the project isn't open, we're gonna have to create an instance of it
        if (project == null) {
            ProjectLoader loader = new ProjectLoader();
            project = loader.loadProject(projectId);
        }

        return project;
    }

    /*
     * Delete a specified task from the specified project
     */
    public boolean deleteTask(int taskId, Project p) throws SQLException, MessagingException {
        try {
            String query = "SELECT * FROM XREF_TaskUsers WHERE TaskUsersTaskID = " +
                    taskId;
            ResultSet set = SQLDriver.instance().getStatement().executeQuery(query);
            Set<Integer> userIds = new HashSet<Integer>();

            // get all user IDs that belong to this task
            set.first();
            while (!set.isLast()){
                userIds.add(set.getInt("TaskUsersUserID"));
            }
            userIds.add(set.getInt("TaskUsersUserID"));
            int[] userIdList = new int[userIds.size()];
            int index = 0;
            for (Integer i : userIds) {
                userIdList[index] = i.intValue();
            }

            // find all of the user email addresses
            String[] emails = new String[userIds.size()];
            for (int i = 0; i < emails.length; i++) {
                set = SQLDriver.instance().getStatement().executeQuery("SELECT * FROM Users WHERE " +
                        "UserID = " + userIdList[i]);
                emails[i] = set.getString("UserEmail");
            }

            // send an email message to all users
            String subject = "Kanban Project " + p.getName() + " - Task ID #"
                    + taskId + " deletion";
            String message = "Task ID #" + taskId + " has been deleted...";
            for (int i = 0; i < emails.length; i++) {
                NotificationService.instance().postMail(emails[i], subject, message,
                        "no-reply@kanban-taskboard.com");
            }
            set.close();
        } catch (SQLException e1) {
            System.err.println(e1.getMessage());
            throw e1;
        } catch (MessagingException e2) {
            System.err.println(e2.getMessage());
            throw e2;
        }
        
        return p.getWorkspace().removeTask(taskId);
    }

    /*
     * Add a new task to the specified project
     *
     * @return the task that has been added
     */
    public Task addTask(TaskData t, int projectId, int stageId) {

        System.out.println(t);
        Task nt = null;
        try {
            nt = new Task(t.description, t.description, Calendar.getInstance().getTime(), null, t.stageId, t.userId, false);

            // actually do the operation
            System.out.println("Adding task to project in stage ID #" + nt.getStageId());
            ProjectLoader loader = new ProjectLoader();
            Project p = loader.loadProject(projectId);
            for (Stage s : p.getWorkspace().getStages()) {
                if(s.getId() == stageId) {
                    s.addTask(nt);
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        return nt;
    }

    /*
     * Update the fields for a specified task given some new values
     */
    public Task editTask(int taskId, TaskData td, Project p) {
        Task t = p.getWorkspace().getTask(taskId);
        // TODO: fix task data in object
        return t;
    }

    /*
     * Update a priority for a given task
     */
    public Task updateTaskPriority(int taskId, int priority, Project p) {
        Task t = p.getWorkspace().getTask(taskId);
        t.setPriority(priority);
        return t;
    }

    /*
     * Move the specified task to the new stageId
     *
     * @return true if the move was successful, false otherwise
     */
    public boolean moveTask(Task t, Project p, int stageId) throws SQLException, MessagingException {
        // send an email notification to all subscribed users
        try {
            String query = "SELECT * FROM XREF_TaskUsers WHERE TaskUsersTaskID = " +
                    t.getId();
            ResultSet set = SQLDriver.instance().getStatement().executeQuery(query);
            Set<Integer> userIds = new HashSet<Integer>();

            // get all user IDs that belong to this task
            set.first();
            while (!set.isLast()){
                userIds.add(set.getInt("TaskUsersUserID"));
            }
            userIds.add(set.getInt("TaskUsersUserID"));
            int[] userIdList = new int[userIds.size()];
            int index = 0;
            for (Integer i : userIds) {
                userIdList[index] = i.intValue();
            }

            // find all of the user email addresses
            String[] emails = new String[userIds.size()];
            for (int i = 0; i < emails.length; i++) {
                set = SQLDriver.instance().getStatement().executeQuery("SELECT * FROM Users WHERE " +
                        "UserID = " + userIdList[i]);
                emails[i] = set.getString("UserEmail");
            }

            // send an email message to all users
            String subject = "Kanban Project " + p.getName() + " - Task ID #"
                    + t.getId() + " deletion";
            String message = "Task ID #" + t.getId() + " has been deleted...";
            for (int i = 0; i < emails.length; i++) {
                NotificationService.instance().postMail(emails[i], subject, message,
                        "no-reply@kanban-taskboard.com");
            }
            set.close();
        } catch (SQLException e1) {
            System.err.println(e1.getMessage());
            throw e1;
        } catch (MessagingException e2) {
            System.err.println(e2.getMessage());
            throw e2;
        }

        // actually do the operation
        System.out.println("DEBUG: Attempting to move task from stage ID #" + t.getStageId() + " to "
                + "stage ID #" + stageId);
        return p.getWorkspace().moveTask(t.getId(), t.getStageId(), stageId);
    }

    /*
     * Retrieve the workspace from the given project ID
     */
    public Workspace getWorkspaceFromProjectId(int projectId) throws SQLException {
        return WorkspaceDaoImpl.instance(null).getWorkspace(projectId);
    }

    public List<Stage> getStagesFromProjectID(int projectID) throws SQLException {
        return WorkspaceDaoImpl.instance(null).getWorkspace(projectID).getStages();
    }

    public List<Task> getTasksFromStageID(int stageId) throws SQLException {
        return TaskDaoImpl.instance(null).getTasksFromStageId(stageId);
    }

    public Task updateTask(Task t) throws SQLException {
        TaskDaoImpl.instance(null).update(t);
        return TaskDaoImpl.instance(null).getTask(t.getId());

    }

    /*
     * Attempt to save a project (commit a new version).
     */
    public Project saveProject(int projectId) throws SQLException {
        System.out.println("DEBUG: Attempting to save the project (commit a new version.");
        Project result = null;
        try {
            ProjectLoader loader = new ProjectLoader();
            result = loader.versionProject(loader.loadProject(projectId));
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
            throw e;
        }
        return null;
    }
}
