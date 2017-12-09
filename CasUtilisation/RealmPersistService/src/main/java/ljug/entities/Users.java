package ljug.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName")
    , @NamedQuery(name = "Users.findByUserGsuite", query = "SELECT u FROM Users u WHERE u.userGsuite = :userGsuite")
    , @NamedQuery(name = "Users.findByUserPass", query = "SELECT u FROM Users u WHERE u.userPass = :userPass")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String userName;
    private String description;
    private String userGsuite;
    @Basic(optional = false)
    private String userPass;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE}, mappedBy = "usersSet", fetch = FetchType.EAGER)
    private Set<Roles> rolesSet;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @XmlTransient
    public Set<Roles> getRolesSet() {
        return rolesSet;
    }

    public void setRolesSet(Set<Roles> rolesSet) {
        this.rolesSet = rolesSet;
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
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Users{ userName:" + userName + " }";
    }
    
}
