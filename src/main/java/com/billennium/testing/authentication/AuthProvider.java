package com.billennium.testing.authentication;

public class AuthProvider {

    public synchronized static String getLogin(){

        return System.getProperty("user");
    }

    public synchronized static String getPassword(){

        return System.getProperty("password");
    }
}
