package com.cubitux.view;

import com.cubitux.model.annonce.Annonce;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by pierre on 2016-05-12.
 */
public class AnnonceView {

    /**
     * Print annonce to the screen
     *
     * @param annonce Annonce to be displayed
     * @throws Exception
     */
    public static void show(Annonce annonce) throws Exception {
        Field[] fields = annonce.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String getterName = "get" + StringUtils.capitalize(fieldName);

            Method method = annonce.getClass().getMethod(getterName);
            Object valueObject = method.invoke(annonce, (Object[]) null);
            String fieldValue = valueObject != null ? valueObject.toString() : null;

            System.out.println(fieldName + " = " + fieldValue);

        }
    }
}
