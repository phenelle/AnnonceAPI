package com.cubitux.model;

import java.io.Serializable;
import java.util.Date;

/**
 * A simple class that will hold user's properties
 *
 * @created by pierre on 2016-05-12.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1986464620104872158L;

    /**
     * The country
     */
    public String country;
    /**
     * The user unique identifier
     */
    private Integer id;
    /**
     * Username
     */
    private String login;
    /**
     * Password
     */
    private String password;
    /**
     * A role
     */
    private Role role;
    /**
     * Active or inactive
     */
    private Boolean status;
    /**
     * User's email
     */
    private String email;
    /**
     * Firstname
     */
    private String firstName;
    /**
     * Lastname
     */
    private String lastName;
    /**
     * The user's address
     */
    private String address;
    /**
     * The user's zipcode
     */
    private String zipCode;
    /**
     * The city
     */
    private String city;
    /**
     * The province
     */
    private String province;
    /**
     * The creation's date
     */
    private Date created;

    /**
     * Last time it was modified
     */
    private Date modified;

    /**
     * Last time user successfully logged in
     */
    private Date lastLogin;

    /**
     * Last time user access the system
     */
    private Date lastAccess;

    /**
     * Is the user logged?
     */
    private Boolean logged;

    /**
     * Session Identifier
     */
    private String session;

    /**
     * Constructor
     */
    public User() {
        this.role = Role.User;
        this.created = new Date();
        this.modified = new Date();
        this.status = false;
        this.logged = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public Boolean isLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }
}
