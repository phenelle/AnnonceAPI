package com.cubitux.view;

import com.cubitux.model.User;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by pierre on 2016-07-15.
 */
public class UserViewTest extends TestCase {

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
    public void testShow() throws Exception {

        // make sure the object have been created
        assertNotNull("user is null", user);

        UserView.show(user);
    }
}
