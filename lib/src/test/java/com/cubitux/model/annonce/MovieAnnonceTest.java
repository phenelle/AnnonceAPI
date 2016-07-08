package com.cubitux.model.annonce;

import com.cubitux.model.Category;

import junit.framework.TestCase;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by pierre on 2016-07-07.
 */
public class MovieAnnonceTest extends TestCase {


    /**
     * A simple annonce for testing purpose
     */
    private MovieAnnonce movieAnnonce;

    /**
     * Annonce unique Identifier for testing purpose
     */
    private Long id = 12345L;

    /**
     * Annonce status for testing purpose
     */
    private Boolean status = true;

    /**
     * Annonce date for testing purpose
     */
    private Date now = new Date();


    /**
     * Annonce title for testing purpose
     */
    private String title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

    /**
     * Annonce category for testing purpose
     */
    private Category category = Category.Vehicle;

    /**
     * Annonce description for testing purpose
     */
    private String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras tristique, odio at commodo malesuada, tortor mi pharetra ante, " +
            "et tempor turpis massa sed tortor. \nAliquam erat volutpat. Donec vitae tempus nulla, eget pharetra purus. Aenean pharetra molestie mi, vitae volutpat " +
            "elit placerat vel. \nFusce bibendum tortor vel nibh euismod viverra. Phasellus a dictum ex, non feugiat libero. Suspendisse rutrum odio a mauris maximus, \n" +
            "non tincidunt ipsum ullamcorper. Nam risus nisl, bibendum quis diam et, commodo maximus purus. Aenean faucibus eu nisl non consequat. Nunc eget elit ac quam " +
            "elementum vestibulum eu in orci. Integer hendrerit non urna ullamcorper facilisis. Suspendisse consectetur eros id fermentum pretium. In vehicula nunc sed " +
            "magna tempus lobortis. Nunc in lacus vel lorem sollicitudin sagittis.\n";

    /**
     * Annonce price for testing purpose
     */
    private Double price = 1000.0;

    /**
     * Annonce viewed for testing purpose
     */
    private Long viewed = 500L;

    /**
     * Annonce path to the image
     */
    private String image = "/var/www/html/img/test.png";


    /**
     * This function will be executed before the test begin
     * The idea here is to prepare all data before testing them
     */
    @Before
    public void setUp() {
        System.out.println("@Before - setUp");

        movieAnnonce = new MovieAnnonce();

        movieAnnonce.setId(this.id);
        movieAnnonce.setTitle(this.title);
        movieAnnonce.setCategory(this.category);
        movieAnnonce.setCreated(this.now);
        movieAnnonce.setModified(this.now);
        movieAnnonce.setDescription(this.description);
        movieAnnonce.setPrice(this.price);
        movieAnnonce.setViewed(this.viewed);
        movieAnnonce.setStatus(this.status);
        movieAnnonce.setImage(image);
        movieAnnonce.setMovieType("Horror");

    }

    @Test
    public void testToJSON() {
        Annonce annonce = movieAnnonce;
        JSONObject jsonObject1 = movieAnnonce.toJSON();
        JSONObject jsonObject2 = annonce.toJSON();

    }
}
