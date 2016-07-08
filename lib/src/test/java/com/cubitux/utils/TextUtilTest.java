package com.cubitux.utils;

import com.cubitux.model.Category;
import com.cubitux.model.annonce.Annonce;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by pierre on 2016-06-17.
 */
public class TextUtilTest extends TestCase {

    /**
     * Required to test the controller
     */
    private Annonce annonce1 = new Annonce();
    private Annonce annonce2;

    /**
     * Required to test the controller
     */
    private String txtFileName = "annonce-test.txt";


    @Before
    public void setUp() {
        // Create a new Annonce and populate fields
        System.out.println("@Before - TextUtilTest - setUp");
        annonce1.setId(123L);
        annonce1.setPrice(new Double(100));
        annonce1.setViewed(new Long(100));
        annonce1.setTitle("Scooter a vendre!!");
        annonce1.setCategory(Category.Vehicle);
        annonce1.setImage("image1.jpg");
        annonce1.setDescription("Tres beau scooter, full option");
    }

    @Test
    public void testReadWriteAsTxt() throws Exception {
        System.out.println("@Test - TextUtilTest - testReadWriteAsTxt");

        // Save annonce to file
        System.out.println("@Test - TextUtilTest.saveAsTxt");
        TextUtil.saveAsTxt(annonce1, txtFileName);

        // Read annonce from file
        System.out.println("@Test - TextUtilTest.readFromTxt");
        annonce2 = TextUtil.readFromTxt(txtFileName);
        assertEquals("Saved annonce is different of the readed one", annonce1.toString(), annonce2.toString());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("@Test - TextUtilTest - tearDown");
        String txtPathFile = TextUtil.getFilePath(txtFileName);
        File file1 = new File(txtPathFile);
        file1.delete();
    }
}
