/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.provide;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import ljug.entities.Roles;
import ljug.entities.Users;

/**
 *
 * @author pfares
 */
class ServiceControle implements Services {

    public ServiceControle(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void addUser(String username, String password, String email) {
        Users u = new Users(username, username);
        if (email != null) {
            u.setUserGsuite(email);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void addRole(String role) {
        Roles r = new Roles(role);
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(r);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Users getUser(String username) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, username);
        } finally {
            em.close();
        }
    }

    @Override
    public Roles getRole(String role) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Roles.class, role);
        } finally {
            em.close();
        }
    }

    @Override
    public void associate(String username, String role) {
        Users u = getUser(username);
        Roles r = getRole(role);
        u.getRolesSet().add(r);
        r.getUsersSet().add(u);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            u = em.merge(u);
            //r = em.merge(r);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void dessociate(String username, String role) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users;
            Roles roles;
            users = em.find(Users.class, username);
            roles = em.find(Roles.class, role);
            users.getRolesSet().remove(roles);
            roles.getUsersSet().remove(users);
            em.merge(roles);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void rmUser(String username) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users;
            users = em.find(Users.class, username);
            users.setRolesSet(new HashSet<>());
            em.merge(users);
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void rmtRole(String role) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles roles;
            roles = em.find(Roles.class, role);
            roles.setUsersSet(new HashSet<>());
            em.merge(roles);
            em.remove(roles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Users> getUsers() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Roles> getRoles() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Roles.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Set<Users> getUsers(String role) {
        Roles r = getRole(role);
        return r.getUsersSet();
    }

    @Override
    public Set<Roles> getRoles(String username) {
        Users u = getUser(username);
        return u.getRolesSet();
    }
}
