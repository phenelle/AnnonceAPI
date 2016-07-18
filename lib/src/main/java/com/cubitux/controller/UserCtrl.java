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

    private static User jsonToUser(JSONObject jsonObject) throws ParseException {
        User user = new User();
        user.setSession((String) jsonObject.get("session"));
        user.setRole(Role.valueOf((String) jsonObject.get("role")));
        user.setEmail((String) jsonObject.get("email"));
        user.setLogin((String) jsonObject.get("login"));
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
        return user;
    }

    /**
     * Verify an user is already authenticated based on his session string
     *
     * @return True if session was founded, False otherwise
     * @throws SystemException
     */
    public static User isAuthenticate(String session) throws SystemException {
        // Create new user
        User user = new User();
        user.setLogged(false);
        user.setSession(session);

        try {
            String restUrl = PropertiesUtil.getValue("restUrlIsAuthenticate");

            // Perform HTTP call
            HashMap<String, String> arguments = new HashMap<String, String>();
            arguments.put("session", session);

            // Response
            String httpResponse = HTTPUtil.httpPost(restUrl, arguments);

            // Parse JSON response
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(httpResponse);
            Long error = (Long) jsonObject.get("error");
            if (error.intValue() == 0) {
                user = jsonToUser(jsonObject);
                user.setLogged(true);
            }

        } catch (IOException ioe) {
            throw new SystemException(ioe);
        } catch (org.json.simple.parser.ParseException pe1) {
            throw new SystemException(pe1);
        } catch (java.text.ParseException pe2) {
            throw new SystemException(pe2);
        }

        return user;
    }


    /**
     * Authenticate an user. If user gets authenticate, then it will have session
     *
     * @param login
     * @param password
     * @throws LoginOrPasswordException
     * @throws AccountNotActivatedException
     * @throws SystemException,             Any other exception that is trapped (IO, Parse, etc...)
     */
    public static User authenticate(String login, String password) throws LoginOrPasswordException, AccountNotActivatedException, SystemException {
        // Create new user
        User user = new User();
        user.setLogged(false);
        user.setSession(null);
        user.setLogin(login);
        user.setPassword(password);

        try {
            // Perform HTTP call
            HashMap<String, String> arguments = new HashMap<String, String>();
            arguments.put("login", login);
            arguments.put("password", password);

            // Response
            String restUrl = PropertiesUtil.getValue("restUrlAuthenticate");
            String httpResponse = HTTPUtil.httpPost(restUrl, arguments);

            // Parse JSON response
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(httpResponse);

            Long error = (Long) jsonObject.get("error");
            switch (error.intValue()) {
                case 0:
                    user = jsonToUser(jsonObject);
                    user.setLogged(true);
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
        return user;
    }

    /**
     * Logout any existing session
     *
     * @param session
     * @return
     * @throws SystemException
     */
    public static User logout(String session) throws SystemException {
        User user = new User();
        user.setLogged(true);
        user.setSession(session);

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
            }

        } catch (IOException ioe) {
            throw new SystemException(ioe);
        } catch (org.json.simple.parser.ParseException pe1) {
            throw new SystemException(pe1);
        }

        return user;
   }

    public static void register(User user) throws Exception {

    }

    public static void edit(User user) throws Exception {

    }

}
