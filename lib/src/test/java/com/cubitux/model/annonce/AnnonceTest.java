package com.cubitux.model.annonce;

import com.cubitux.model.Category;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * This class will tests all Annonce Properties
 *
 * @created by pierre on 2016-05-13.
 */
public class AnnonceTest extends TestCase {

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
     * This method will test the Constructor's default value
     *
     * @throws Exception
     */
    @Test
    public void testConstructor() throws Exception {
        System.out.println("@Test - testConstructor");
        com.cubitux.model.annonce.Annonce a = new com.cubitux.model.annonce.Annonce();
        assertEquals("The default status is not 'unpublished' ", a.getStatus(), Boolean.valueOf(false));
        assertEquals("The default value for viewed is not 0 ", a.getViewed(), Long.valueOf(0));
        assertNotNull("The default created date is empty", a.getCreated());
        assertNotNull("The default modified date is empty", a.getModified());
    }

    /**
     * This method will test all properties and function of the Annonce class
     *
     * @throws Exception
     */
    @Test
    public void testFields() throws Exception {
        System.out.println("@Test - testFields");
        assertSame("The title is incorrect", this.title, annonce.getTitle());
        assertSame("The category is incorect", this.category, annonce.getCategory());
        assertEquals("The ID is incorrect", this.id, annonce.getId());
        assertSame("The status is incorrect", this.status, annonce.getStatus());
        assertSame("The created date is incorrect", this.now, annonce.getCreated());
        assertSame("The modified date is incorrect", this.now, annonce.getModified());
        assertEquals("The price is incorrect", this.price, annonce.getPrice());
        assertEquals("The viewed is incorrect", this.viewed, annonce.getViewed());
        assertEquals("The image path is incorrect", this.image, annonce.getImage());
        assertEquals("The description is incorrect", this.description, annonce.getDescription());
    }

}

