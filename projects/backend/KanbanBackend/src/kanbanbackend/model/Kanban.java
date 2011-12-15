package kanbanbackend.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kanbanbackend.db.SQLDriver;

/**
 * This class is responsible for managing user/project session information 
 * and any similar logic.
 *
 * @author caw
 */
public class Kanban {

    private static Kanban instance;
    private static Object padlock = new Object();
    public Set<User> currentUsers;
    public Map<Project, Integer> openProjects;

    /*
     * Default constructor for the Kanban application
     */
    public Kanban() {
        currentUsers = new HashSet<User>();
        openProjects = new HashMap<Project, Integer>();
    }

    /*
     * Fetch the instance for this kanban object
     */
    public static Kanban instance() {
        if (instance == null) {
            synchronized (padlock) {
                instance = new Kanban();
            }
        }
        return instance;
    }

    /*
     * Log a user onto the system (add to current users)
     *
     * @return true if successful, false otherwise
     */
    public boolean userLogon(User u){
        boolean result = false;
        if (!currentUsers.contains(u)) {
            currentUsers.add(u);
            result = true;
        }
        return result;
    }

    /*
     * Log a user off from the system (remove from current users)
     *
     * @true if successful, false otherwise
     */
    public boolean userLogoff(User u){
        boolean result = false;
        if (currentUsers.contains(u)){
            currentUsers.remove(u);
            result = true;
        }
        return result;
    }

    /*
     * Attempt to open a project
     */
    public boolean openProject(Project p) {
        boolean result = false;
        if (openProjects.containsKey(p)) {
            openProjects.put(p, openProjects.get(p) + 1);
            result = true;
        } else {
            openProjects.put(p, 1); // first open reference to the project
        }
        return result;
    }

    /*
     * Retrieve the list of open projects
     */
    public Set<Project> getOpenProjects() {
        return openProjects.keySet();
    }

}
