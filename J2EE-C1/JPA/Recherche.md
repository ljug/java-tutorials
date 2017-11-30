TODO : TRADUIRE 

Retrieving JPA Entity Objects
The Java Persistence API (JPA) provides various ways to retrieve objects from the database. The retrieval of objects does not require an active transaction because it does not change the content of the database.

The persistence context serves as a cache of retrieved entity objects. If a requested entity object is not found in the persistence context a new object is constructed and filled with data that is retrieved from the database (or from the L2 cache - if enabled). The new entity object is then added to the persistence context as a managed entity object and returned to the application.

Notice that construction of a new managed object during retrieval uses the no-arg constructor. Therefore, it is recommended to avoid unnecessary time consuming operations in no-arg constructors of entity classes and to keep them simple as possible.

This page covers the following topics:
Retrieval by Class and Primary Key
Retrieval by Eager Fetch
Retrieval by Navigation and Access
Retrieval by Query
Retrieval by Refresh
Cascading Refresh

# Retrieval by Class and Primary Key
Every entity object can be uniquely identified and retrieved by the combination of its class and its primary key. Given an EntityManager em, the following code fragment demonstrates retrieval of an Employee object whose primary key is 1:

  Employee employee = em.find(Employee.class, 1);
Casting of the retrieved object to Employee is not required because find is defined as returning an instance of the same class that it takes as a first argument (using generics).
An IllegalArgumentException is thrown if the specified class is not an entity class.

If the EntityManager already manages the specified entity object in its persistence context no retrieval is required and the existing managed object is returned as is. Otherwise, the object data is retrieved from the database and a new managed entity object with that retrieved data is constructed and returned. If the object is not found in the database null is returned.

A similar method, getReference, can be considered the lazy version of find:
```Java
  Employee employee = em.getReference(Employee.class, 1);
```
The getReference method works like the find method except that if the entity object is not already managed by the EntityManager a hollow object might be returned (null is never returned). A hollow object is initialized with the valid primary key but all its other persistent fields are uninitialized. The object content is retrieved from the database and the persistent fields are initialized, lazily, when the entity object is first accessed. If the requested object does not exist an EntityNotFoundException is thrown when the object is first accessed.

The getReference method is useful when a reference to an entity object is required but not its content, such as when a reference to it has to be set from another entity object.

# Retrieval by Eager Fetch
Retrieval of an entity object from the database might cause automatic retrieval of additional entity objects. By default, a retrieval operation is automatically cascaded through all the non collection and map persistent fields (i.e. through one-to-one and many-to-one relationships). Therefore, when an entity object is retrieved, all the entity objects that are reachable from it by navigation through non collection and map persistent fields are also retrieved. Theoretically, in some extreme situations this might cause the retrieval of the entire database into the memory, which is usually unacceptable.

A persistent reference field can be excluded from this automatic cascaded retrieval by using a lazy fetch type:

```JAVA
@Entity
class Employee {
   :
  @ManyToOne(fetch=FetchType.LAZY)
  private Employee manager;
   :
}
```

The default for non collection and map references is FetchType.EAGER, indicating that the retrieval operation is cascaded through the field. Explicitly specifying FetchType.LAZY in either @OneToOne or @ManyToOne annotations (currently ObjectDB does not distinguish between the two) excludes the field from participating in retrieval cascading.

When an entity object is retrieved all its persistent fields are initialized. A persistent reference field with the FetchType.LAZY fetch policy is initialized to reference a new managed hollow object (unless the referenced object is already managed by the EntityManager). In the example above, when an Employee instance is retrieved its manager field might reference a hollow Employee instance. In a hollow object the primary key is set but other persistent fields are uninitialized until the object fields are accessed.

On the other hand, the default fetch policy of persistent collection and map fields is FetchType.LAZY. Therefore, by default, when an entity object is retrieved any other entity objects that it references through its collection and map fields are not retrieved with it.

This can be changed by an explicit FetchType.EAGER setting:

```JAVA
@Entity
class Employee {
   :
  @ManyToMany(fetch=FetchType.EAGER)
  private Collection<Project> projects;
   :
}
```

Specifying FetchType.EAGER explicitly in @OneToMany or @ManyToMany annotations (currently ObjectDB does not distinguish between the two) enables cascading retrieval for the field. In the above example, when an Employee instance is retrieved all the referenced Project instances are also retrieved automatically.

# Retrieval by Navigation and Access
All the persistent fields of an entity object can be accessed freely, regardless of the current fetch policy, as long as the EntityManager is open. This also includes fields that reference entity objects that have not been loaded from the database yet and are represented by hollow objects. If the EntityManager is open when a hollow object is first accessed its content is automatically retrieved from the database and all its persistent fields are initialized.

From the point of view of the developer it looks like the entire graph of objects is present in memory. This illusion, which is based on lazy transparent activation and retrieval of objects by ObjectDB, helps hide some of the direct interaction with the database and makes database programming easier.

For example, after retrieving an Employee instance from the database the manager field may include a hollow Employee entity object:

```Java
  Employee employee = em.find(Employee.class, 1);
  Employee managed = employee.getManager(); // might be hollow
```

If manager is hollow transparent activation occurs when it is first accessed. For example:

  String managerName = manager.getName();
Accessing a persistent field in a hollow object (e.g. the name of the manager in the example above) causes the retrieval of missing content from the database and initialization of all the persistent fields.

As seen, the entire graph of objects is available for navigation, regardless of the fetch policy. The fetch policy, however, does affect performance. Eager retrieval might minimize the round trips to the database and improve performance, but unnecessary retrieval of entity objects that are not in use will decrease performance.

The fetch policy also affects objects that become detached (e.g. when the EntityManager is closed). Transparent activation is not supported for detached objects. Therefore, only content that has already been fetched from the database is available in objects that are detached.

JPA 2 introduces methods for checking if a specified entity object or a specified persistent field is loaded. For example:

  PersistenceUtil util = Persistence.getPersistenceUtil();
  boolean isObjectLoaded = util.isLoaded(employee);
  boolean isFieldLoaded = util.isLoaded(employee, "address");
As shown above, a PersistenceUtil instance is obtained from the static getPersistenceUtil method. It provides two isLoaded methods - one for checking an entity object and the other for checking a persistent field of an entity object.

# Retrieval by Query
The most flexible method for retrieving objects from the database is to use queries. The official query language of JPA is JPQL (Java Persistence Query Language). It enables retrieval of objects from the database by using simple queries as well as complex, sophisticated ones. JPA queries and JPQL are described in chapter 4.

# Retrieval by Refresh
Managed objects can be reloaded from the database by using the refresh method:

  em.refresh(employee);
The content of the managed object in memory is discarded (including changes, if any) and replaced by data that is retrieved from the database. This might be useful to ensure that the application deals with the most up to date version of an entity object, just in case it might have been changed by another EntityManager since it was retrieved.

An IllegalArgumentException is thrown by refresh if the argument is not a managed entity (including entity objects in the New, Removed or Detached states). If the object does not exist in the database anymore an EntityNotFoundException is thrown.

Cascading Refresh
Marking a reference field with CascadeType.REFRESH (or CascadeType.ALL, which includes REFRESH) indicates that refresh operations should be cascaded automatically to entity objects that are referenced by that field (multiple entity objects can be referenced by a collection field):

```Java
@Entity
class Employee {
     :
    @OneToOne(cascade=CascadeType.REFRESH)
    private Address address;
     :
}
```

In the example above, the Employee entity class contains an address field that references an instance of Address, which is another entity class. Due to the CascadeType.REFRESH setting, when an Employee instance is refreshed the operation is automatically cascaded to the referenced Address instance, which is then automatically refreshed as well. Cascading may continue recursively when applicable (e.g. to entity objects that the Address object references, if any).
