TODO Traduire

# Storing JPA Entity Objects
New entity objects can be stored in the database either explicitly by invoking the persist method or implicitly as a result of a cascade operation.

This page covers the following topics:
Explicit Persist
Referenced Embedded Objects
Referenced Entity Objects
Cascading Persist
Global Cascading Persist
Batch Store

# Explicit Persist
The following code stores an instance of the Employee entity class in the database:
```Java
  Employee employee = new Employee("Samuel", "Joseph", "Wurzelbacher");
  em.getTransaction().begin();
  em.persist(employee);
  em.getTransaction().commit();
```

The Employee instance is constructed as an ordinary Java object and its initial state is New. An explicit call to persist associates the object with an owner EntityManager em and changes its state to Managed. The new entity object is stored in the database when the transaction is committed.

An IllegalArgumentException is thrown by persist if the argument is not an instance of an entity class. Only instances of entity classes can be stored in the database independently. Objects of other persistable types can only be stored in the database embedded in containing entities (as field values).

A TransactionRequiredException is thrown if there is no active transaction when persist is called because operations that modify the database require an active transaction.

If the database already contains another entity of the same type with the same primary key, an EntityExistsException is thrown. The exception is thrown either by persist (if that existing entity object is currently managed by the EntityManager) or by commit.

# Referenced Embedded Objects
The following code stores an Employee instance with a reference to an Address instance:
```Java
  Employee employee = new Employee("Samuel", "Joseph", "Wurzelbacher");
  Address address = new Address("Holland", "Ohio");
  employee.setAddress(address);
 
  em.getTransaction().begin();
  em.persist(employee);
  em.getTransaction().commit();
```
Instances of persistable types other than entity classes are automatically stored embedded in containing entity objects. Therefore, if Address is defined as an embeddable class the Employee entity object is automatically stored in the database with its Address instance as an embedded object.

Notice that embedded objects cannot be shared by multiple entity objects. Each containing entity object should have its own embedded objects.

# Referenced Entity Objects
On the other hand, suppose that the Address class in the code above is defined as an entity class. In this case, the referenced Address instance is not stored in the database automatically with the referencing Employee instance.

To avoid a dangling reference in the database, an IllegalStateException is thrown on commit if a persisted entity object has to be stored in the database in a transaction and it references another entity object that is not expected to be stored in the database at the end of that transaction.

It is the application's responsibility to verify that when an object is stored in the database, the entire closure of entity objects that are reachable from that object by navigation through persistent reference fields is also stored in the database. This can be done either by explicit persist of every reachable object or alternatively by setting automatic cascading persist.

# Cascading Persist
Marking a reference field with CascadeType.PERSIST (or CascadeType.ALL that also covers PERSIST) indicates that persist operations should be cascaded automatically to entity objects that are referenced by that field (multiple entity objects can be referenced by a collection field):
```Java
@Entity
class Employee {
     :
    @OneToOne(cascade=CascadeType.PERSIST)
    private Address address;
     :
}
```
In the example above, the Employee entity class contains an address field that references an instance of Address, which is another entity class. Due to the CascadeType.PERSIST setting, when an Employee instance is persisted the operation is automatically cascaded to the referenced Address instance which is then automatically persisted without the need for a separate persist call for Address. Cascading may continue recursively when applicable (e.g. to entity objects that the Address object references, etc.).

# Global Cascading Persist
Instead of specifying CascadeType.PERSIST individually for every relevant reference field, it can be specified globally for any persistent reference, either by setting the ObjectDB configuration or in a JPA portable way, by specifying the cascade-persist XML element in the XML mapping file:

```XML
<entity-mappings xmlns="http://java.sun.com/xml/ns/persistence/orm"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://java.sun.com/xml/ns/persistence/orm
 http://java.sun.com/xml/ns/persistence/orm_1_0.xsd" version="1.0">
   <persistence-unit-metadata>
     <persistence-unit-defaults>
       <cascade-persist/>
     </persistence-unit-defaults>
   </persistence-unit-metadata>
</entity-mappings>
```
The mapping file has to be located either in the default location, META-INF/orm.xml, or in another location that is specified explicitly in the persistence unit definition (in persistence.xml).

# Batch Store
Storing a large number of entity objects requires special consideration. The combination of the clear and flush methods can be used to save memory in large transactions:

```Java
  em.getTransaction().begin();
  for (int i = 1; i <= 1000000; i++) {
      Point point = new Point(i, i);
      em.persist(point);
      if ((i % 10000) == 0) {
          em.flush();
          em.clear();
      }
  }
  em.getTransaction().commit();
```
Managed entity objects consume more memory than ordinary non managed Java objects. Therefore, holding 1,000,000 managed Point instances in the persistence context might consume too much memory. The sample code above clears the persistence context after every 10,000 persists. Updates are flushed to the database before clearing, otherwise they would be lost.

Updates that are sent to the database using flush are considered temporary and are only visible to the owner EntityManager until a commit. With no explicit commit, these updates are later discarded. The combination of clear and flush enables moving the temporary updates from memory to the database.

Note: Flushing updates to the database is sometimes also useful before executing queries in order to get up to date results.

Storing large amount of entity objects can also be performed by multiple transactions:

```Java
  em.getTransaction().begin();
  for (int i = 1; i <= 1000000; i++) {
      Point point = new Point(i, i);
      em.persist(point);
      if ((i % 10000) == 0) {
          em.getTransaction().commit();
          em.clear();          
          em.getTransaction().begin();
      }
  }
  em.getTransaction().commit();
```

Splitting a batch store into multiple transactions is more efficient than using one transaction with multiple invocations of the flush and clear methods. So using multiple transactions is preferred when applicable.