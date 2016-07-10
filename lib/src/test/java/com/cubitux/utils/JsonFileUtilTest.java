package com.cubitux.utils;

import com.cubitux.model.Category;
import com.cubitux.model.annonce.Annonce;
import com.cubitux.model.annonce.MovieAnnonce;
import com.cubitux.model.annonce.VehicleAnnonce;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierre on 2016-06-17.
 */
public class JsonFileUtilTest extends TestCase {

    /**
     * Required to test the controller
     */
    private Annonce annonce = new Annonce();
    private MovieAnnonce movieAnnonce = new MovieAnnonce();
    private VehicleAnnonce vehicleAnnonce = new VehicleAnnonce();

    /**
     * Required to test the controller
     */
    private String jsonFileName = "annonces.json";

    @Before
    public void setUp() {
        // Create a new Annonce and populate fields
        System.out.println("@Before - JsonFileUtilTest - setUp");
        annonce.setId(123L);
        annonce.setPrice(Double.valueOf(100));
        annonce.setViewed(100L);
        annonce.setTitle("Annonce Generique");
        annonce.setCategory(Category.ALL);
        annonce.setImage("image1.jpg");
        annonce.setDescription("Lorem ipsum sit dolor amet elit");

        movieAnnonce.setId(567L);
        movieAnnonce.setPrice(Double.valueOf(10));
        movieAnnonce.setViewed(5L);
        movieAnnonce.setTitle("Hunger Games");
        movieAnnonce.setCategory(Category.Movie);
        movieAnnonce.setImage("image1.jpg");
        movieAnnonce.setDescription("Lorem ipsum");

        vehicleAnnonce.setId(234L);
        vehicleAnnonce.setPrice(Double.valueOf(500));
        vehicleAnnonce.setViewed(1L);
        vehicleAnnonce.setTitle("Scooter a vendre!!");
        vehicleAnnonce.setCategory(Category.Vehicle);
        vehicleAnnonce.setImage("image1.jpg");
        vehicleAnnonce.setDescription("Tres beau scooter, full option");
        vehicleAnnonce.setMake("PGO");
        vehicleAnnonce.setModel("Bigmax");
        vehicleAnnonce.setYear(2008L);
    }

    @Test
    public void testReadWriteAsJSON() throws Exception {
        System.out.println("@Test - JsonFileUtilTest - testReadWriteAsJSON");

        List<Annonce> listAnnonce1 = new ArrayList<Annonce>();
        listAnnonce1.add(annonce);
        listAnnonce1.add(vehicleAnnonce);
        listAnnonce1.add(movieAnnonce);

        JsonFileUtil.saveAsJSON(listAnnonce1, jsonFileName);

        List<Annonce> listAnnonce2 = JsonFileUtil.readFromJSON(jsonFileName);
        assertTrue(EqualsBuilder.reflectionEquals(listAnnonce1, listAnnonce2));
    }


    @After
    public void tearDown() throws Exception {
        System.out.println("@Test - JsonFileUtilTest - tearDown");

        String jsonPathFile = TextUtil.getFilePath(jsonFileName);
        File file2 = new File(jsonPathFile);
        file2.delete();
    }
}
