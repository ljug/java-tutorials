/*
    Users entité JPA POJO
    Copyright (C) 2017  Pascal Fares

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

 */
package lb.edu.isae.entites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * La table utilisateur
 * @author pfares
 */
@Entity
@Table(name = "exo1_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
    , @NamedQuery(name = "Users.findByFonction", query = "SELECT u FROM Users u WHERE u.fonction = :fonction")
    , @NamedQuery(name = "Users.findByNo", query = "SELECT u FROM Users u WHERE u.foreignNo = :no")
    , @NamedQuery(name = "Users.findByFermer", query = "SELECT u FROM Users u WHERE u.fermer = :fermer")
    , @NamedQuery(name = "Users.findByGmail", query = "SELECT u FROM Users u WHERE u.gmail = :gmail")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "fonction")
    private String fonction;
    @Basic(optional = false)
    @Column(name = "foreignNo")
    private int foreignNo;
    @Basic(optional = false)
    @Column(name = "fermer")
    private boolean fermer;
    @Column(name = "gmail")
    private String gmail;

    public Users() {
    }

    public Users(String username) {
        this.username = username;
    }

    public Users(String username, String fonction, int no, boolean fermer) {
        this.username = username;
        this.fonction = fonction;
        this.foreignNo = no;
        this.fermer = fermer;
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

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public int getForeignNo() {
        return foreignNo;
    }

    public void setForeignNo(int foreignNo) {
        this.foreignNo = foreignNo;
    }

    public boolean getFermer() {
        return fermer;
    }

    public void setFermer(boolean fermer) {
        this.fermer = fermer;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username!= null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        return !((this.username == null && other.username!= null) || (this.username != null && !this.username.equals(other.username)));
    }

    @Override
    public String toString() {
        return "lb.edu.isae.entites.Users[ userId=" + username + " ]";
    }
    
}
