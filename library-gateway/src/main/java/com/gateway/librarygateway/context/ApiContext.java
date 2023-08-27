package com.gateway.librarygateway.context;

public class ApiContext {
    
    static private String authURL = "http://localhost:8080/auth/user";
    /**
     * @return String return the authURL
     */
    static public String getAuthURL() {
        return authURL;
    }
}
