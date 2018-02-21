package com.billennium.testing.authentication;

/**
 * Login and password for a tested app should be provided as Maven parameter.
 */
public class AuthProvider {

    public synchronized static String getLogin(){
        return System.getProperty("user");
    }

    public synchronized static String getPassword(){
        return System.getProperty("password");
    }
}
