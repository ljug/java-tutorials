Deleting JPA Entity Objects
Existing entity objects can be deleted from the database either explicitly by invoking the remove method or implicitly as a result of a cascade operation.

This page covers the following topics:
Explicit Remove
Cascading Remove
Orphan Removal
DELETE Queries
Explicit Remove
In order to delete an object from the database it has to first be retrieved (no matter which way) and then in an active transaction, it can be deleted using the remove method:

  Employee employee = em.find(Employee.class, 1);
 
  em.getTransaction().begin();
  em.remove(employee);
  em.getTransaction().commit();
The entity object is physically deleted from the database when the transaction is committed. Embedded objects that are contained in the entity object are also deleted. If the transaction is rolled back and not committed the object is not deleted.

An IllegalArgumentException is thrown by remove if the argument is not a an instance of an entity class or if it is a detached entity. A TransactionRequiredException is thrown if there is no active transaction when remove is called because operations that modify the database require an active transaction.

Cascading Remove
Marking a reference field with CascadeType.REMOVE (or CascadeType.ALL, which includes REMOVE) indicates that remove operations should be cascaded automatically to entity objects that are referenced by that field (multiple entity objects can be referenced by a collection field):

@Entity
class Employee {
     :
    @OneToOne(cascade=CascadeType.REMOVE)
    private Address address;
     :
}
In the example above, the Employee entity class contains an address field that references an instance of Address, which is another entity class. Due to the CascadeType.REMOVE setting, when an Employee instance is removed the operation is automatically cascaded to the referenced Address instance, which is then automatically removed as well. Cascading may continue recursively when applicable (e.g. to entity objects that the Address object references, if any).

Orphan Removal
JPA 2 supports an additional and more aggressive remove cascading mode which can be specified using the orphanRemoval element of the @OneToOne and @OneToMany annotations:

@Entity
class Employee {
     :
    @OneToOne(orphanRemoval=true)
    private Address address;
     :
}
When an Employee entity object is removed the remove operation is cascaded to the referenced Address entity object. In this regard, orphanRemoval=true and cascade=CascadeType.REMOVE are identical, and if orphanRemoval=true is specified, CascadeType.REMOVE is redundant.

The difference between the two settings is in the response to disconnecting a relationship. For example, such as when setting the address field to null or to another Address object.

If orphanRemoval=true is specified the disconnected Address instance is automatically removed. This is useful for cleaning up dependent objects (e.g. Address) that should not exist without a reference from an owner object (e.g. Employee).
If only cascade=CascadeType.REMOVE is specified no automatic action is taken since disconnecting a relationship is not a remove operation.
To avoid dangling references as a result of orphan removal this feature should only be enabled for fields that hold private non shared dependent objects.

Orphan removal can also be set for collection and map fields. For example:

@Entity
class Employee {
     :
    @OneToMany(orphanRemoval=true)
    private List<Address> addresses;
     :
}
In this case, removal of an Address object from the collection leads to automatic removal of that object from the database.

DELETE Queries
DELETE queries provide an alternative way for removing entity objects from the database. Deleting objects using a DELETE query may be useful especially when many entity objects have to be deleted in one operation. The DELETE Queries in JPA/JPQL in chapter 4 explains how to use JPA DELETE queries.