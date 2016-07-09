package com.cubitux.utils;

import com.cubitux.controller.AnnonceCtrl;
import com.cubitux.model.annonce.Annonce;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;


/**
 * Created by pierre on 2016-06-17.
 */
public class JsonFileUtil {

    /**
     * This method will save a list of annonce into a local json file
     *
     * @param annonces     A list of annonce to save
     * @param jsonFileName Filename were the annonce will be saved
     * @throws Exception
     */
    public static void saveAsJSON(List<Annonce> annonces, String jsonFileName) throws Exception {

        // Get filepath where the annonce will be stored
        String annoncePathFile = PropertiesUtil.getValue("annoncePath") + File.separator + jsonFileName;

        JSONObject jsonAnnonces = AnnonceCtrl.toJSON(annonces);

        // Write JSON into the file
        FileWriter file = new FileWriter(annoncePathFile);
        file.write(jsonAnnonces.toJSONString());
        file.flush();
        file.close();

    }

    /**
     * Read a list of annonces from a file
     *
     * @param jsonFileName JSON file where the annonce are stored
     * @return A List of Annonce
     * @throws Exception
     */
    public static List<Annonce> readFromJSON(String jsonFileName) throws Exception {
        String annoncePathFile = PropertiesUtil.getValue("annoncePath") + File.separator + jsonFileName;

        FileReader fileReader = new FileReader(annoncePathFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuffer stringBuffer = new StringBuffer();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
            stringBuffer.append("\n");
        }
        fileReader.close();

        return AnnonceCtrl.toArray(stringBuffer.toString());
    }
}
