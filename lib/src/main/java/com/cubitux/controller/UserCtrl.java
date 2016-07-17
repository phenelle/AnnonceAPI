package com.cubitux.controller;

import com.cubitux.model.Role;
import com.cubitux.model.User;
import com.cubitux.model.exception.AccountNotActivatedException;
import com.cubitux.model.exception.LoginOrPasswordException;
import com.cubitux.model.exception.SystemException;
import com.cubitux.utils.DateUtil;
import com.cubitux.utils.HTTPUtil;
import com.cubitux.utils.PropertiesUtil;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

/**
 * Created by pierre on 2016-05-12.
 */
public class UserCtrl {

    private static void jsonToUser(JSONObject jsonObject, User user) throws ParseException {
        user.setSession((String) jsonObject.get("session"));
        user.setRole(Role.valueOf((String) jsonObject.get("role")));
        user.setEmail((String) jsonObject.get("email"));
        user.setFirstName((String) jsonObject.get("firstname"));
        user.setLastName((String) jsonObject.get("lastname"));
        user.setAddress((String) jsonObject.get("address"));
        user.setCountry((String) jsonObject.get("country"));
        user.setCity((String) jsonObject.get("city"));
        user.setProvince((String) jsonObject.get("province"));
        user.setCreated(DateUtil.stringToDate((String) jsonObject.get("created")));
        user.setModified(DateUtil.stringToDate((String) jsonObject.get("modified")));
        user.setLastLogin(DateUtil.stringToDate((String) jsonObject.get("lastlogin")));
        user.setLastAccess(DateUtil.stringToDate((String) jsonObject.get("lastaccess")));
    }

    /**
     * Verify an user is already authenticated based on his session string
     *
     * @param user
     * @return True if session was founded, False otherwise
     * @throws SystemException
     */
    public static Boolean isAuthenticate(User user) throws SystemException {
        if (user.getSession() != null) {
            user.setLogged(false);
            try {
                String restUrl = PropertiesUtil.getValue("restUrlIsAuthenticate");

                // Perform HTTP call
                HashMap<String, String> arguments = new HashMap<String, String>();
                arguments.put("session", user.getSession());

                // Response
                String httpResponse = HTTPUtil.httpPost(restUrl, arguments);

                // Parse JSON response
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(httpResponse);
                Long error = (Long) jsonObject.get("error");
                if (error.intValue() == 0) {
                    user.setLogged(true);
                    jsonToUser(jsonObject, user);
                    return true;
                }

            } catch (IOException ioe) {
                throw new SystemException(ioe);
            } catch (org.json.simple.parser.ParseException pe1) {
                throw new SystemException(pe1);
            } catch (java.text.ParseException pe2) {
                throw new SystemException(pe2);
            }
        }

        return false;
    }


    /**
     * Authenticate an user. If user gets authenticate, then it will have session
     *
     * @param user
     * @throws LoginOrPasswordException
     * @throws AccountNotActivatedException
     * @throws SystemException,             Any other exception that is trapped (IO, Parse, etc...)
     */
    public static void authenticate(User user) throws LoginOrPasswordException, AccountNotActivatedException, SystemException {
        try {
            // Reset any existing session
            user.setLogged(false);
            user.setSession(null);

            // Perform HTTP call
            HashMap<String, String> arguments = new HashMap<String, String>();
            arguments.put("login", user.getLogin());
            arguments.put("password", user.getPassword());

            // Response
            String restUrl = PropertiesUtil.getValue("restUrlAuthenticate");
            String httpResponse = HTTPUtil.httpPost(restUrl, arguments);

            // Parse JSON response
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(httpResponse);

            Long error = (Long) jsonObject.get("error");
            switch (error.intValue()) {
                case 0:
                    user.setLogged(true);
                    jsonToUser(jsonObject, user);
                    break;
                case 100:
                case 102:
                    throw new LoginOrPasswordException((String) jsonObject.get("message"));
                case 101:
                    throw new AccountNotActivatedException((String) jsonObject.get("message"));
            }
        } catch (IOException ioe) {
            throw new SystemException(ioe);
        } catch (org.json.simple.parser.ParseException pe1) {
            throw new SystemException(pe1);
        } catch (java.text.ParseException pe2) {
            throw new SystemException(pe2);
        }
    }

    public static Boolean logout(User user) throws SystemException {
        if (user.getSession() != null) {
            try {
                String restUrl = PropertiesUtil.getValue("restUrlLogout");

                // Perform HTTP call
                HashMap<String, String> arguments = new HashMap<String, String>();
                arguments.put("session", user.getSession());

                // Response
                String httpResponse = HTTPUtil.httpPost(restUrl, arguments);

                // Parse JSON response
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(httpResponse);
                Long error = (Long) jsonObject.get("error");
                if (error.intValue() == 0) {
                    user.setSession(null);
                    user.setLogged(false);
                    return true;
                }

            } catch (IOException ioe) {
                throw new SystemException(ioe);
            } catch (org.json.simple.parser.ParseException pe1) {
                throw new SystemException(pe1);
            }
        }

        return false;
    }

    public static void register(User user) throws Exception {

    }

    public static void edit(User user) throws Exception {

    }

}
