package com.cubitux.model;

/**
 * Enumeration of the User's role
 *
 * @created by pierre on 2016-05-12.
 */
public enum Role {
    /**
     * Role by default with basic access
     */
    User,

    /**
     * Advanced role with moderation permission
     */
    Moderator,

    /**
     * Full access to the application
     */
    Administrator
}
