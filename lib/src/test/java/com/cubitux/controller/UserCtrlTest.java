package com.cubitux.controller;

import com.cubitux.model.Role;
import com.cubitux.model.User;
import com.cubitux.model.exception.AccountNotActivatedException;
import com.cubitux.model.exception.LoginOrPasswordException;
import com.cubitux.utils.DateUtil;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * This class will test the User Controller
 *
 * @created by pierre on 2016-05-13.
 */
public class UserCtrlTest extends TestCase {

    /**
     * Object that is tested
     */
    private UserCtrl controller = new UserCtrl();

    /**
     * Required to test the controller
     */
    private User admin = new User();
    private User user = new User();


    @Before
    public void setUp() {
        // Create a new Annonce and populate fields
        System.out.println("@Before - setUp");
        admin.setLogin("admin@localhost");
        admin.setPassword("12345");

        user.setLogin("user1");
        user.setPassword("123");
    }

    @Test
    public void testLogout() throws Exception {
        System.out.println("@Test - testAuthenticate");

        // Verify authenticate method
        UserCtrl.authenticate(admin);
        assertTrue("admin not logged", admin.isLogged());
        assertNotNull("admin has no session", admin.getSession());

        // Store session
        String session = admin.getSession();

        // Force logout
        UserCtrl.logout(admin);
        assertFalse("admin is still logged", admin.isLogged());
        assertNull("admin still have session", admin.getSession());

        // Verify session have been removed
        admin.setSession(session);
        UserCtrl.isAuthenticate(admin);
        assertFalse("admin session is still active", admin.isLogged());

    }

    @Test
    public void testAuthenticate() throws Exception {
        System.out.println("@Test - testAuthenticate");

        // Verify authenticate method
        UserCtrl.authenticate(admin);
        assertTrue("admin not logged", admin.isLogged());
        assertNotNull("admin has no session", admin.getSession());
        assertEquals("firstname is incorrect", "Mr", admin.getFirstName());
        assertEquals("lastname is incorrect", "Admin", admin.getLastName());
        assertEquals("email is incorrect", "admin@localhost", admin.getEmail());
        assertEquals("address is incorrect", "1 rue du petit chemin", admin.getAddress());
        assertEquals("creation date is incorrect", DateUtil.dateToString(admin.getCreated()), "2016-06-16 10:44:57");
        assertEquals("city is incorrect", admin.getCity(), "Paris");
        assertEquals("country is incorrect", admin.getCountry(), "France");
        assertEquals("role is incorrect", admin.getRole(), Role.Administrator);
        try {
            UserCtrl.authenticate(user);
        } catch (Exception e) {
            assertSame("Wrong exception was thrown", e.getClass(), AccountNotActivatedException.class);
        }

        // Verify isAuthenticate method
        assertTrue("user should be authenticate with this session id", UserCtrl.isAuthenticate(admin));

        // Verify user is not auth if password is incorrect
        try {
            admin.setPassword("456");
            UserCtrl.authenticate(admin);
        } catch (Exception e) {
            assertSame("Wrong exception was thrown", e.getClass(), LoginOrPasswordException.class);
        }
        assertNull("session was not removed", admin.getSession());
        assertFalse("user is still logged...", admin.isLogged());


    }


}
