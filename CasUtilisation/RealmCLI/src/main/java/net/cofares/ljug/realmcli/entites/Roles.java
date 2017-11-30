package net.cofares.ljug.realmcli.entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "roles", catalog = "Realm", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
    , @NamedQuery(name = "Roles.findByRoleName", query = "SELECT r FROM Roles r WHERE r.roleName = :roleName")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "role_name", nullable = false, length = 15)
    private String roleName;
    @JoinTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "role_name", referencedColumnName = "role_name", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "user_name", referencedColumnName = "user_name", nullable = false)})
    @ManyToMany
    private List<Users> usersList;

    public Roles() {
    }

    public Roles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleName != null ? roleName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        return !((this.roleName == null && other.roleName != null) || (this.roleName != null && !this.roleName.equals(other.roleName)));
    }

    @Override
    public String toString() {
        return "net.cofares.ljug.realmcli.entites.Roles[ roleName=" + roleName + " ]";
    }
    
}
