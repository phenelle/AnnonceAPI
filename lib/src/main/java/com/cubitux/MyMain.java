package com.cubitux;

import com.cubitux.model.Category;
import com.cubitux.model.annonce.Annonce;
import com.cubitux.view.AnnonceView;

public class MyMain {

    public static void main(String[] args) throws Exception {


        String txtFileName = "annonce.txt";

        // Create new controller / view
        AnnonceView view = new AnnonceView();

        // Create a new Annonce and populate fields
        System.out.println("Creating Annonce");
        Annonce annonce1 = new Annonce();
        annonce1.setId(1L);
        annonce1.setPrice(new Double(100));
        annonce1.setTitle("Scooter a vendre!!");
        annonce1.setCategory(Category.Vehicle);
        annonce1.setDescription("Tres beau scooter \nFull option");

        System.out.println("Now showing nnonce");
        AnnonceView.show(annonce1);


    }
}
