/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ljug.control;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ljug.entities.Users;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ljug.control.exceptions.NonexistentEntityException;
import ljug.control.exceptions.PreexistingEntityException;
import ljug.entities.Roles;

/**
 *
 * @author pfares
 */
public class RolesJpaController implements Serializable {

    public RolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Roles roles) throws PreexistingEntityException, Exception {
        if (findRoles(roles.getRoleName()) != null) return;
        if (roles.getUsersSet() == null) {
            roles.setUsersSet(new HashSet<Users>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Users> attachedUsersSet = new HashSet<Users>();
            for (Users usersSetUsersToAttach : roles.getUsersSet()) {
                usersSetUsersToAttach = em.getReference(usersSetUsersToAttach.getClass(), usersSetUsersToAttach.getUserName());
                attachedUsersSet.add(usersSetUsersToAttach);
            }
            roles.setUsersSet(attachedUsersSet);
            em.persist(roles);
            for (Users usersSetUsers : roles.getUsersSet()) {
                usersSetUsers.getRolesSet().add(roles);
                usersSetUsers = em.merge(usersSetUsers);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRoles(roles.getRoleName()) != null) {
                System.out.println("\n\nRoles " + roles + " already exists.\n\n");
                throw new PreexistingEntityException("Roles " + roles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Roles roles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles persistentRoles = em.find(Roles.class, roles.getRoleName());
            Set<Users> usersSetOld = persistentRoles.getUsersSet();
            Set<Users> usersSetNew = roles.getUsersSet();
            Set<Users> attachedUsersSetNew = new HashSet<Users>();
            for (Users usersSetNewUsersToAttach : usersSetNew) {
                usersSetNewUsersToAttach = em.getReference(usersSetNewUsersToAttach.getClass(), usersSetNewUsersToAttach.getUserName());
                attachedUsersSetNew.add(usersSetNewUsersToAttach);
            }
            usersSetNew = attachedUsersSetNew;
            roles.setUsersSet(usersSetNew);
            roles = em.merge(roles);
            for (Users usersSetOldUsers : usersSetOld) {
                if (!usersSetNew.contains(usersSetOldUsers)) {
                    usersSetOldUsers.getRolesSet().remove(roles);
                    usersSetOldUsers = em.merge(usersSetOldUsers);
                }
            }
            for (Users usersSetNewUsers : usersSetNew) {
                if (!usersSetOld.contains(usersSetNewUsers)) {
                    usersSetNewUsers.getRolesSet().add(roles);
                    usersSetNewUsers = em.merge(usersSetNewUsers);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = roles.getRoleName();
                if (findRoles(id) == null) {
                    throw new NonexistentEntityException("The roles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles roles;
            try {
                roles = em.getReference(Roles.class, id);
                roles.getRoleName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roles with id " + id + " no longer exists.", enfe);
            }
            Set<Users> usersSet = roles.getUsersSet();
            for (Users usersSetUsers : usersSet) {
                usersSetUsers.getRolesSet().remove(roles);
                usersSetUsers = em.merge(usersSetUsers);
            }
            em.remove(roles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Roles> findRolesEntities() {
        return findRolesEntities(true, -1, -1);
    }

    public List<Roles> findRolesEntities(int maxResults, int firstResult) {
        return findRolesEntities(false, maxResults, firstResult);
    }

    private List<Roles> findRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Roles.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Roles findRoles(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Roles.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Roles> rt = cq.from(Roles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
