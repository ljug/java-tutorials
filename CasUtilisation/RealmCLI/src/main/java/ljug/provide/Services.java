/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.provide;

import ljug.entities.Roles;
import ljug.entities.Users;

/**
 * Services Users Roles
 * @author Pascal Fares
 */
public interface Services {
    public void addUser(String username, String password, String email);
    public void addRole(String role);
    public void rmUser(String username);
    public void rmtRole(String role);
    public Users getUser(String username);
    public Roles getRole(String role);
    public void associate(String username, String role);
    public void dessociate(String username, String role);
}
