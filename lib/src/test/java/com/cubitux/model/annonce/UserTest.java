package com.cubitux.model.annonce;

import com.cubitux.model.User;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest extends TestCase {

    // Object that we will test
    private User user;

    // The firstname that we will use for testing
    private String expectedFirstname = "lorem ipsum";

    // The lastName that we will use for testing
    private String expectedLastname = "Ipsum lorem";

    @Before
    public void setUp() {
        // Create a new instance of the object
        user = new User();

        // Set fields
        user.setFirstName(expectedFirstname);
        user.setLastName(expectedLastname);
    }

    @Test
    public void testUser() {

        // make sure the object have been created
        assertNotNull("user is null", user);

        // Verify the result
        assertSame("firstname is different", expectedFirstname, user.getFirstName());
        assertSame("lastname is different", expectedLastname, user.getLastName());
        assertFalse("islogged is not false", user.isLogged());
    }

    @After
    public void tearDown() {
        // Clean memory
        user = null;
    }
}
