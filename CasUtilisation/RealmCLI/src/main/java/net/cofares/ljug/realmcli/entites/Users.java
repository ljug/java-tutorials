/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ljug.realmcli.entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pfares
 */
@Entity
@Table(name = "users", catalog = "Realm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName")
    , @NamedQuery(name = "Users.findByUserGsuite", query = "SELECT u FROM Users u WHERE u.userGsuite = :userGsuite")
    , @NamedQuery(name = "Users.findByUserPass", query = "SELECT u FROM Users u WHERE u.userPass = :userPass")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_name", nullable = false, length = 15)
    private String userName;
    @Column(name = "user_gsuite", length = 256)
    private String userGsuite;
    @Basic(optional = false)
    @Column(name = "user_pass", nullable = false, length = 64)
    private String userPass;
    @Lob
    @Column(name = "description", length = 65535)
    private String description;
    @ManyToMany(mappedBy = "usersList")
    private List<Roles> rolesList;

    public Users() {
    }

    public Users(String userName) {
        this.userName = userName;
    }

    public Users(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGsuite() {
        return userGsuite;
    }

    public void setUserGsuite(String userGsuite) {
        this.userGsuite = userGsuite;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        return !((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName)));
    }

    @Override
    public String toString() {
        return "net.cofares.ljug.realmcli.entites.Users[ userName=" + userName + " ]";
    }
    
}
