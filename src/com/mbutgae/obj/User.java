/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbutgae.obj;

/**
 *
 * @author ALPABETAPINTAR
 */
public class User {

    private String username, password, rights;
    
    public User() {
 
    }
    
    public User(String user, String pass, String rights) {
        this.username = user;
        this.password = pass;
        this.rights = rights;
    }
        
    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    

   
    public void showStatus() {
        //return super.toString(); //To change body of generated methods, choose Tools | Templates.
        
        System.out.println("User : "+this.username);
        System.out.println("Rights : "+this.rights);
    }

    
}
