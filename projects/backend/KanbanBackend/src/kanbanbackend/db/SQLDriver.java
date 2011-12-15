package kanbanbackend.db;
    
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.db.DatabaseTypeUtils;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import kanbanbackend.model.Project;
import kanbanbackend.model.Stage;
import kanbanbackend.model.Task;
import kanbanbackend.model.User;
import kanbanbackend.model.Workspace;

/**
 * This is a thread-safe Singleton class that manages access to the
 * SQLite database. It implements the logic necessary to interface with the
 * SQLite database, with some additional features tacked on the side.
 * @author caw
 */
public class SQLDriver {

    // singleton instance variables
    private static SQLDriver instance;
    private static Object padlock = new Object();
    private boolean initialized = false;

    // SQL connection variables
    private Connection conn;
    private Statement stmt;
    private String mainDb;
    private String backlogDb;

    //ORM connection variables
    private ConnectionSource connectionSource;
    private DatabaseType databaseType;
    private TaskDaoImpl taskDao;
    private StageDaoImpl stageDao;
    private WorkspaceDaoImpl workspaceDao;
    private ProjectDaoImpl projectDao;
    private UserDaoImpl userDao;
    private ProjectUsersTupleDaoImpl projectUsersTupleDao;
    private TaskUsersTupleDaoImpl taskUsersTupleDao;

    /*
     * Private constructor for the Singleton SQLDriver.
     * This ensures only one global, static instance of the class.
     */
    private SQLDriver() {
        super();
    }

    /*
     * Retrieve the instance of the SQLDriver. This
     * method is thread-safe.
     */
    public static SQLDriver instance(){
        if (instance == null) {
            synchronized (padlock){
                instance = new SQLDriver();
            }
        }
        return instance;
    }

    /*
     * Determine if the database driver and DAO objects are initialized
     */
    public boolean isInitialized() {
        return initialized;
    }
    
    /*
     * Store the name of the database to connect to.
     *
     * @return true if successful, false otherwise
     */
    public void initialize(String main, String backlog){
        mainDb = "jdbc:sqlite:" + main;
        backlogDb = "jdbc:sqlite:" + backlog;
        initializeDao(main);
    }

    public boolean initializeDao(String db) {
        boolean result = true;
        mainDb = "jdbc:sqlite:" + db;
        try {
            // flag initialization to be true to avoid stack overflow...
            initialized = true;

            connectionSource = DatabaseTypeUtils.createJdbcConnectionSource(mainDb);
            databaseType = DatabaseTypeUtils.createDatabaseType(mainDb);

            taskDao = TaskDaoImpl.instance(databaseType);
            stageDao = StageDaoImpl.instance(databaseType);
            workspaceDao = WorkspaceDaoImpl.instance(databaseType);
            projectDao = ProjectDaoImpl.instance(databaseType);
            userDao = UserDaoImpl.instance(databaseType);
            projectUsersTupleDao = ProjectUsersTupleDaoImpl.instance(databaseType);
            taskUsersTupleDao = TaskUsersTupleDaoImpl.instance(databaseType);

            taskDao.setConnectionSource(connectionSource);
            stageDao.setConnectionSource(connectionSource);
            workspaceDao.setConnectionSource(connectionSource);
            projectDao.setConnectionSource(connectionSource);
            userDao.setConnectionSource(connectionSource);
            projectUsersTupleDao.setConnectionSource(connectionSource);
            taskUsersTupleDao.setConnectionSource(connectionSource);

            taskDao.initialize();
            stageDao.initialize();
            workspaceDao.initialize();
            projectDao.initialize();
            userDao.initialize();
            projectUsersTupleDao.initialize();
            taskUsersTupleDao.initialize();

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * Closes DAO connection source
     * @param query
     */
    public void closeDaoConnection() {
        try {
            connectionSource.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Execute a non-query on the SQLite database.
     */
    public void executeNonQuery(String query){
        try {
            Class.forName("org.sqlite.JDBC");
            try {
                conn = DriverManager.getConnection(mainDb);
                try {
                    stmt = conn.createStatement();
                    stmt.executeUpdate(query);
                } catch (SQLException e){
                    System.err.println("Error: " + e.getMessage());
                    e.printStackTrace();
                    Logger.getLogger(SQLDriver.class.getName()).log(Level.SEVERE, null, e);
                }
                conn.close();
            } catch (SQLException e){
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
                Logger.getLogger(SQLDriver.class.getName()).log(Level.SEVERE, null, e);
            }
        } catch (ClassNotFoundException e){
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            Logger.getLogger(SQLDriver.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /*
     * Execute an update on the database.
     *
     * @return number of rows affected
     */
    public int executeUpdate(String query){
        int numRows = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:" + mainDb);
                try {
                    stmt = conn.createStatement();
                    numRows = stmt.executeUpdate(query);
                } catch (SQLException e){
                    System.err.println("Error: " + e.getMessage());
                    e.printStackTrace();
                    Logger.getLogger(SQLDriver.class.getName()).log(Level.SEVERE, null, e);
                }
                conn.close();
            } catch (SQLException e){
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
                Logger.getLogger(SQLDriver.class.getName()).log(Level.SEVERE, null, e);
            }
        } catch (ClassNotFoundException e){
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            Logger.getLogger(SQLDriver.class.getName()).log(Level.SEVERE, null, e);
        }

        return numRows;
    }

    /**
     * Get the statement associated with the database.
     * Used for queries
     * @return
     */
    public Statement getStatement() throws SQLException {
        conn = DriverManager.getConnection(mainDb);
        stmt = conn.createStatement();
        return stmt;
    }

    
    /**
     * Returns the next sequence number in specified table
     * @param tableName - Name of table in database
     * @return int : next sequence number
     */
    public int getRowId(String tableName) {
        ResultSet rs = null;
        System.out.println("DEBUG: Main database name = " + mainDb);
        int seqNum = -2;
        try {
            conn = DriverManager.getConnection(mainDb);
            stmt = conn.createStatement();

            rs = stmt.executeQuery
                               ("SELECT * FROM sqlite_sequence WHERE name = '" + tableName + "'");

            seqNum = rs.getInt("seq");
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(SQLDriver.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return seqNum + 1;
    }
}
