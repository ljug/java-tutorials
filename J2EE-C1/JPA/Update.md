# Updating JPA Entity Objects
Modifying existing entity objects that are stored in the database is based on transparent persistence, which means that changes are detected and handled automatically.

This page covers the following topics:
Transparent Update
Automatic Change Tracking
UPDATE Queries

# Transparent Update
Once an entity object is retrieved from the database (no matter which way) it can simply be modified in memory from inside an active transaction:

```Java
  Employee employee = em.find(Employee.class, 1);
 
  em.getTransaction().begin();
  employee.setNickname("Joe the Plumber");
  em.getTransaction().commit();
```
The entity object is physically updated in the database when the transaction is committed. If the transaction is rolled back and not committed the update is discarded.

On commit the persist operation can be cascaded from all the entity objects that have to be stored in the database, including from all the modified entity objects. Therefore, entity objects that are referenced from modified entity objects by fields that are marked with CascadeType.PERSIST or CascadeType.ALL are also persisted. If global cascade persist is enabled all the reachable entity objects that are not managed yet are also persisted.

# Automatic Change Tracking
As shown above, an update is achieved by modifying a managed entity object from within an active transaction. No EntityManager's method is invoked to report the update. Therefore, to be able to apply database updates on commit, ObjectDB must detect changes to managed entities automatically. One way to detect changes is to keep a snapshot of every managed object when it is retrieved from the database and to compare that snapshot to the actual managed object on commit. A more efficient way to detect changes automatically is described in the Enhancer section in chapter 5.

However, detecting changes to arrays requires using snapshots even if the entity classes are enhanced. Therefore, for efficiency purposes, the default behavior of ObjectDB ignores array changes when using enhanced entity classes:

```Java
    Employee employee = em.find(Employee.class, 1);
 
    em.getTransaction().begin();
    employee.projects[0] = new Project(); // not detected automatically
    JDOHelper.makeDirty(employee, "projects"); // reported as dirty
    em.getTransaction().commit();
```
As demonstrated above, array changes are not detected automatically (by default) but it is possible to report a change explicitly by invoking the JDO's makeDirty method.

Alternatively, ObjectDB can be configured to detect array changes using snapshots as well as when enhanced entity classes are in use.

It is usually recommended to use collections rather than arrays when using JPA. Collections are more portable to ORM JPA implementations and provide better automatic change tracking support.

# UPDATE Queries
UPDATE queries provide an alternative way for updating entity objects in the database. Modifying objects using an UPDATE query may be useful especially when many entity objects have to be modified in one operation. The UPDATE Queries in JPA/JPQL in chapter 4 explains how to use JPA UPDATE queries.