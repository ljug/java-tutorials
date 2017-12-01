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
import ljug.entities.Roles;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import ljug.control.exceptions.NonexistentEntityException;
import ljug.control.exceptions.PreexistingEntityException;
import ljug.entities.Users;

/**
 *
 * @author pfares
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) throws PreexistingEntityException, Exception {
        if (findUsers(users.getUserName()) != null) return;
        if (users.getRolesSet() == null) {
            users.setRolesSet(new HashSet<Roles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Roles> attachedRolesSet = new HashSet<Roles>();
            for (Roles rolesSetRolesToAttach : users.getRolesSet()) {
                rolesSetRolesToAttach = em.getReference(rolesSetRolesToAttach.getClass(), rolesSetRolesToAttach.getRoleName());
                attachedRolesSet.add(rolesSetRolesToAttach);
            }
            users.setRolesSet(attachedRolesSet);
            em.persist(users);
            for (Roles rolesSetRoles : users.getRolesSet()) {
                rolesSetRoles.getUsersSet().add(users);
                rolesSetRoles = em.merge(rolesSetRoles);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsers(users.getUserName()) != null) {
                System.out.println("Users " + users + " already exists.");
                throw new PreexistingEntityException("Users " + users + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getUserName());
            Set<Roles> rolesSetOld = persistentUsers.getRolesSet();
            Set<Roles> rolesSetNew = users.getRolesSet();
            Set<Roles> attachedRolesSetNew = new HashSet<Roles>();
            for (Roles rolesSetNewRolesToAttach : rolesSetNew) {
                rolesSetNewRolesToAttach = em.getReference(rolesSetNewRolesToAttach.getClass(), rolesSetNewRolesToAttach.getRoleName());
                attachedRolesSetNew.add(rolesSetNewRolesToAttach);
            }
            rolesSetNew = attachedRolesSetNew;
            users.setRolesSet(rolesSetNew);
            users = em.merge(users);
            for (Roles rolesSetOldRoles : rolesSetOld) {
                if (!rolesSetNew.contains(rolesSetOldRoles)) {
                    rolesSetOldRoles.getUsersSet().remove(users);
                    rolesSetOldRoles = em.merge(rolesSetOldRoles);
                }
            }
            for (Roles rolesSetNewRoles : rolesSetNew) {
                if (!rolesSetOld.contains(rolesSetNewRoles)) {
                    rolesSetNewRoles.getUsersSet().add(users);
                    rolesSetNewRoles = em.merge(rolesSetNewRoles);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = users.getUserName();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getUserName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            Set<Roles> rolesSet = users.getRolesSet();
            for (Roles rolesSetRoles : rolesSet) {
                rolesSetRoles.getUsersSet().remove(users);
                rolesSetRoles = em.merge(rolesSetRoles);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
