package com.cubitux.view;

import com.cubitux.model.Category;
import com.cubitux.model.annonce.Annonce;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by pierre on 2016-07-15.
 */
public class AnnonceViewTest extends TestCase {

    /**
     * A simple annonce for testing purpose
     */
    private Annonce annonce;

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

        annonce = new com.cubitux.model.annonce.Annonce();
        annonce.setId(this.id);
        annonce.setTitle(this.title);
        annonce.setCategory(this.category);
        annonce.setCreated(this.now);
        annonce.setModified(this.now);
        annonce.setDescription(this.description);
        annonce.setPrice(this.price);
        annonce.setViewed(this.viewed);
        annonce.setStatus(this.status);
        annonce.setImage(image);

    }


    /**
     * This method will execute the Show() method
     */
    @Test
    public void testShow() throws Exception {
        System.out.println("@Test - testShow");
        AnnonceView.show(annonce);
    }
}
