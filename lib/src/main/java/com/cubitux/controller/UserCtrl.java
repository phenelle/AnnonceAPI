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
import java.util.HashMap;

/**
 * Created by pierre on 2016-05-12.
 */
public class UserCtrl {

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
            String restUrl = PropertiesUtil.getValue("restUrlAuthenticate");

            // Reset any existing session
            user.setLogged(false);
            user.setSession(null);

            // Perform HTTP call
            HashMap<String, String> arguments = new HashMap<String, String>();
            arguments.put("login", user.getLogin());
            arguments.put("password", user.getPassword());

            // Response
            String httpResponse = HTTPUtil.httpPost(restUrl, arguments);

            // Parse JSON response
            JSONParser parser = new JSONParser();
            JSONObject jsonObjects = (JSONObject) parser.parse(httpResponse);

            Long error = (Long) jsonObjects.get("error");
            switch (error.intValue()) {
                case 0:
                    user.setLogged(true);
                    user.setSession((String) jsonObjects.get("session"));
                    user.setRole(Role.valueOf((String) jsonObjects.get("role")));
                    user.setEmail((String) jsonObjects.get("email"));
                    user.setFirstName((String) jsonObjects.get("firstname"));
                    user.setLastName((String) jsonObjects.get("lastname"));
                    user.setAddress((String) jsonObjects.get("address"));
                    user.setCountry((String) jsonObjects.get("country"));
                    user.setCity((String) jsonObjects.get("city"));
                    user.setProvince((String) jsonObjects.get("province"));
                    user.setCreated(DateUtil.stringToDate((String) jsonObjects.get("created")));
                    user.setModified(DateUtil.stringToDate((String) jsonObjects.get("modified")));
                    user.setLastLogin(DateUtil.stringToDate((String) jsonObjects.get("lastlogin")));
                    user.setLastAccess(DateUtil.stringToDate((String) jsonObjects.get("lastaccess")));
                    break;
                case 100:
                case 102:
                    throw new LoginOrPasswordException((String) jsonObjects.get("message"));
                case 101:
                    throw new AccountNotActivatedException((String) jsonObjects.get("message"));
            }
        } catch (IOException ioe) {
            throw new SystemException(ioe);
        } catch (org.json.simple.parser.ParseException pe1) {
            throw new SystemException(pe1);
        } catch (java.text.ParseException pe2) {
            throw new SystemException(pe2);
        }
    }

    public static void register(User user) throws Exception {

    }

    public static void edit(User user) throws Exception {

    }

}
