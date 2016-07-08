package com.cubitux.utils;

import com.cubitux.model.Category;
import com.cubitux.model.annonce.Annonce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * Created by pierre on 2016-06-17.
 */
public class TextUtil {

    /**
     * Return path to fileName
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String getFilePath(String fileName) throws Exception {
        String annoncePath = PropertiesUtil.getValue("annoncePath");
        String annoncePathFile = annoncePath + File.separator + fileName;
        return annoncePathFile;
    }

    /**
     * This method will read an annonce from a local file
     *
     * @return The annonce that was readed
     * @throws Exception
     */
    public static Annonce readFromTxt(String txtFileName) throws Exception {

        // Get filepath where the annonce will be stored
        String annoncePathFile = TextUtil.getFilePath(txtFileName);

        // Create an empty annonce
        Annonce annonce = new Annonce();

        // Open the file
        FileInputStream fstream = new FileInputStream(annoncePathFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        //Read File Line By Line
        Integer i = 1;
        while ((strLine = br.readLine()) != null) {
            switch (i) {
                case 1:
                    annonce.setId(Long.valueOf(strLine));
                    break;
                case 2:
                    annonce.setTitle(strLine);
                    break;
                case 3:
                    annonce.setCategory(Category.valueOf(strLine));
                    break;
                case 4:
                    annonce.setViewed(Long.valueOf(strLine));
                    break;
                case 5:
                    annonce.setStatus(Boolean.valueOf(strLine));
                    break;
                case 6:
                    annonce.setPrice(Double.valueOf(strLine));
                    break;
                case 7:
                    annonce.setCreated(DateUtil.stringToDate(strLine));
                    break;
                case 8:
                    annonce.setModified(DateUtil.stringToDate(strLine));
                    break;
                case 9:
                    annonce.setImage(strLine);
                    break;
                default:
                    annonce.setDescription(annonce.getDescription() + strLine);
                    break;
            }

            i = i + 1;
        }

        //Close the input stream
        br.close();

        return annonce;
    }

    /**
     * This method will save an annonce into a local file
     *
     * @param annonce The annonce to save
     * @throws Exception
     */
    public static void saveAsTxt(Annonce annonce, String txtFileName) throws Exception {

        // Get filepath where the annonce will be stored
        String annoncePathFile = TextUtil.getFilePath(txtFileName);

        // Store the annonce into a file
        FileWriter fileWriter = new FileWriter(annoncePathFile);
        fileWriter.write(annonce.getId() + "\n");
        fileWriter.write(annonce.getTitle() + "\n");
        fileWriter.write(annonce.getCategory() + "\n");
        fileWriter.write(annonce.getViewed() + "\n");
        fileWriter.write(annonce.getStatus() + "\n");
        fileWriter.write(annonce.getPrice() + "\n");
        fileWriter.write(DateUtil.dateToString(annonce.getCreated()) + "\n");
        fileWriter.write(DateUtil.dateToString(annonce.getModified()) + "\n");
        fileWriter.write(annonce.getImage() + "\n");
        fileWriter.write(annonce.getDescription() + "\n");


        // Always close your fileWriter (otherwise, the file is locked...)
        fileWriter.close();
    }
}
