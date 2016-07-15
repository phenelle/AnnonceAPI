package com.cubitux.view;

import com.cubitux.model.User;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by pierre on 2016-05-19.
 */
public class UserView {

    /**
     * Print user to the screen
     *
     * @param user User to be displayed
     * @throws Exception
     */
    public static void show(User user) throws Exception {
        Field[] fields = user.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String getterName = "get" + StringUtils.capitalize(fieldName);
            try {
                Method method = user.getClass().getMethod(getterName);
                Object valueObject = method.invoke(user, (Object[]) null);
                String fieldValue = valueObject != null ? valueObject.toString() : null;
                System.out.println(fieldName + " = " + fieldValue);
            } catch (NoSuchMethodException e) {
            }
        }
    }
}
