/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.TestCase;
import java.sql.SQLException;
import java.util.List;
import kanbanbackend.db.UserDaoImpl;
import kanbanbackend.model.User;
import kanbanbackend.db.SQLDriver;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kyle
 */
public class UserDBTest extends TestCase {

    public UserDBTest() {
        SQLDriver.instance().initialize("src/kanbanbackend/db/KanbanDB.sqlite",
                "src/kanbanbackend/db/KanbanBacklog.sqlite");
    }
    public void testGetUser() throws SQLException {
        User user1 = new User("Tyler Durden", "ed@norton", "schizo");
        User user = UserDaoImpl.instance(null).queryForId(SQLDriver.instance().getRowId("Users") - 1);
        assertEquals(user.getName(), "Tyler Durden");
        UserDaoImpl.instance(null).delete(user1);
        SQLDriver.instance().closeDaoConnection();
    }

    public void testGetUserByName() throws SQLException {
        User user = new User("Tyler Durden", "ed@norton", "schizo");
        User user1 = UserDaoImpl.instance(null).getUser("Tyler Durden");

        assertEquals(user1.getEmail(), "ed@norton");

        UserDaoImpl.instance(null).delete(user);

        SQLDriver.instance().closeDaoConnection();
    }

    public void testUserAccountCheck() throws SQLException {
        User user = new User("Tyler Durden", "ed@norton", "schizo");

        assertEquals(user.getName(), UserDaoImpl.instance(null).userAccountCheck("ed@norton", "schizo").getName());


        UserDaoImpl.instance(null).delete(user);

        assertNull(UserDaoImpl.instance(null).userAccountCheck("ed@norton", "schizo"));

        SQLDriver.instance().closeDaoConnection();
    }

    public void testQueryForAll() throws SQLException {
        User user1 = new User("Aragorn", "man", "sword");
        User user2 = new User("Legolas", "elf", "bow");
        User user3 = new User("Gimli", "dwarf", "axe");

        List<User> users = UserDaoImpl.instance(null).getAllUsers();

        assertEquals(users.get(0).getName(), user1.getName());
        assertEquals(users.get(1).getName(), user2.getName());
        assertEquals(users.get(2).getName(), user3.getName());

        UserDaoImpl.instance(null).delete(user3);
        UserDaoImpl.instance(null).delete(user2);
        UserDaoImpl.instance(null).delete(user1);

        SQLDriver.instance().closeDaoConnection();
    }

}