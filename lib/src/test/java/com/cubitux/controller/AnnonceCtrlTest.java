package com.cubitux.controller;

import com.cubitux.model.Category;
import com.cubitux.model.annonce.Annonce;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.List;

/**
 * Created by pierre on 2016-06-25.
 */
public class AnnonceCtrlTest extends TestCase {
    /**
     * Object that is tested
     */
    private AnnonceCtrl controller = new AnnonceCtrl();

    @Test
    public void testList() throws Exception {
        System.out.println("@Test - testList");
        Category category = Category.ALL;
        List<Annonce> annonces = AnnonceCtrl.list(category);
        assertFalse("List is empty", annonces.isEmpty());
        for (int i = 0; i < annonces.size(); i++) {
            Annonce annonce = annonces.get(i);
            assertTrue("Category is not vehicle ", "Vehicle".equals(annonce.getCategory().toString()));
        }
    }
}
