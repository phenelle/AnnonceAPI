package com.cubitux.controller;


import com.cubitux.model.Category;
import com.cubitux.model.annonce.Annonce;
import com.cubitux.model.annonce.MovieAnnonce;
import com.cubitux.model.annonce.VehicleAnnonce;
import com.cubitux.model.exception.SystemException;
import com.cubitux.utils.DateUtil;
import com.cubitux.utils.HTTPUtil;
import com.cubitux.utils.PropertiesUtil;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The class manipulate annonce
 * <p/>
 * Created by pierre on 2016-06-17
 */
public class AnnonceCtrl {

    /**
     * Map list of annonce to JSON objects
     *
     * @param annonces
     * @return
     */
    public static JSONObject toJSON(List<Annonce> annonces) {

        // Required objects
        JSONObject jsonAnnonces = new JSONObject();
        JSONArray jsonListAnnonce = new JSONArray();

        for (Annonce annonce : annonces) {

            // Create an JSON annonce object that will hold content
            JSONObject jsonAnnonce = new JSONObject();
            jsonAnnonce.put("annonce", annonce.toJSON());

            // Add item into the list
            jsonListAnnonce.add(jsonAnnonce);
        }

        // Finally, add all items into the file structure
        jsonAnnonces.put("annonces", jsonListAnnonce);

        return jsonAnnonces;
    }

    /**
     * Convert a JSON string into an Array of annonces
     *
     * @param jsonString
     * @return
     * @throws org.json.simple.parser.ParseException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public static List<Annonce> toArray(String jsonString) throws org.json.simple.parser.ParseException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ArrayList<Annonce> annonces = new ArrayList<Annonce>();

        JSONParser parser = new JSONParser();
        JSONObject jsonObjects = (JSONObject) parser.parse(jsonString);

        JSONArray jsonAnnonces = (JSONArray) jsonObjects.get("annonces");
        Iterator annoncesIterator = jsonAnnonces.iterator();
        while (annoncesIterator.hasNext()) {

            // Create an empty annonce
            Annonce annonce = null;

            // Read a single annonce from JSON
            JSONObject jsonObject = (JSONObject) annoncesIterator.next();
            JSONObject jsonAnnonce = (JSONObject) jsonObject.get("annonce");

            String category = (String) jsonAnnonce.get("category");

            switch (Category.valueOf(category)) {
                case Vehicle:
                    String model = (String) jsonAnnonce.get("model");
                    String make = (String) jsonAnnonce.get("make");
                    Long year = (Long) jsonAnnonce.get("year");
                    VehicleAnnonce vehicleAnnonce = new VehicleAnnonce();
                    vehicleAnnonce.setModel(model);
                    vehicleAnnonce.setMake(make);
                    vehicleAnnonce.setYear(year);
                    annonce = vehicleAnnonce;
                    break;

                case Movie:
                    String movieType = (String) jsonAnnonce.get("movieType");
                    MovieAnnonce movieAnnonce = new MovieAnnonce();
                    movieAnnonce.setMovieType(movieType);
                    annonce = movieAnnonce;
                    break;

                default:
                    annonce = new Annonce();
                    break;
            }

            // Extract all properties
            Long id = (Long) jsonAnnonce.get("id");
            String title = (String) jsonAnnonce.get("title");
            String description = (String) jsonAnnonce.get("description");
            Double price = Double.valueOf(jsonAnnonce.get("price").toString());
            Long viewed = (Long) jsonAnnonce.get("viewed");
            Boolean status = (Boolean) jsonAnnonce.get("status");
            String image = (String) jsonAnnonce.get("image");
            String created = (String) jsonAnnonce.get("created");
            String modified = (String) jsonAnnonce.get("modified");

            // Feed annonce with properties
            annonce.setId(id);
            annonce.setTitle(title);
            annonce.setDescription(description);
            annonce.setPrice(price);
            annonce.setViewed(viewed);
            annonce.setCategory(Category.valueOf(category));
            annonce.setStatus(status);
            annonce.setImage(image);

            try {
                annonce.setCreated(DateUtil.stringToDate(created));
                annonce.setModified(DateUtil.stringToDate(modified));
            } catch (ParseException e) {
                // @TODO Log exception somewhere
            }

            // Add object to the list
            annonces.add(annonce);
        }
        return annonces;
    }


    /**
     * Publish an annonce on the server
     *
     * @param annonce
     */
    public static void publish(Annonce annonce) {

    }

    /**
     * Return an annonce by it's unique identifier
     *
     * @param id
     */
    public static Annonce getById(Long id) {
        Annonce annonce = new Annonce();
        return annonce;
    }


    /**
     * Will call an HTTP request to fetch the latest list of annonce
     *
     * @param category Filter annonce on a specific category only
     * @return
     * @throws SystemException
     */
    public static List<Annonce> list(Category category) throws SystemException {
        try {
            String restUrl = PropertiesUtil.getValue("restUrlListAnnonce");
            restUrl += "?category=" + category.toString();

            // Response
            String httpResponse = HTTPUtil.httpGet(restUrl);
            return AnnonceCtrl.toArray(httpResponse);
        } catch (IOException ioe) {
            throw new SystemException(ioe);
        } catch (org.json.simple.parser.ParseException pe) {
            throw new SystemException(pe);
        } catch (NoSuchMethodException nsme) {
            throw new SystemException(nsme);
        } catch (IllegalAccessException iae) {
            throw new SystemException(iae);
        } catch (InvocationTargetException ite) {
            throw new SystemException(ite);
        }
    }
}
