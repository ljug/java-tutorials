# Cas d'utilisation sur un exemple

Exemple d'une application de réseaux social. Permettant à l'administrateur d'effectuer des traitements sur une sélection de candidats selon certains critères.

Vous trouverez aussi d'autres exemples et des TP ici

* [Un stocks et requêtes en Java8](Exemple2)
* [Camp de codes : des traveaux pratique](CampDeCode)


# L'UC : Action sur une sélection de membres

**Name :** Perform action on selected members

**Primary Actor :** Administrator

**Preconditions :** Administrator is logged in to the system.

**Postconditions :** Action is performed only on members that fit the specified criteria.

**Main Success Scenario :**
Administrator specifies criteria of members on which to perform a certain action.
Administrator specifies an action to perform on those selected members.
Administrator selects the Submit button.
The system finds all members that match the specified criteria.
The system performs the specified action on all matching members.

**Extensions :**
1a. Administrator has an option to preview those members who match the specified criteria before he or she specifies the action to be performed or before selecting the Submit button.

**Frequency of Occurrence :** Many times during the day.

# La Classe Person (Exemple)

```Java
public class Person {

    public enum Sex {
        MALE, FEMALE
    }
    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public int getAge() {
        // ...
    }
    public void printPerson() {
        // ...
    }
}
//Les personne sont disponibles dans la structure List<Person>
```

# Plusieurs aproche en java

1. Une méthode par critère de recherche
2. Méthode plus générale par critère de recherche
3. Spécifier les critères de recherche dans une classe locale
4. Spécifier les critères de recherche dans une classe Anonyme
5. Spécifier les critères de recherche avec une Lambda expression
6. Utilisez Interfaces fonctionnelles standard avec Lambda Expressions
7. Utilisez les Lambda Expressions Tout au long de l'application
8. Utilisez la Genéricité plus Intensivement
9. Utiliser des opérations globales qui acceptent les Lambda Expressions comme paramètres

