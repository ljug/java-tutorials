# Optional

Selon la documentation Oracle, un Optional est un objet conteneur qui peut contenir ou non une valeur non nulle. Il a été introduit dans Java 8 pour remédier à la malédiction des NullPointerExceptions. Essentiellement, Optional est une classe wrapper qui contient une référence à un autre objet (tout simplement).

Comme toute autre fonctionnalité d'un langage de programmation, il peut être utilisé correctement ou abusé. Afin de connaître la meilleure façon d'utiliser la classe Optional (ie facultative), il faut comprendre ce qui suit:

## Ce qu'il essaie de résoudre
Optional est une tentative de réduire le nombre d'exceptions de pointeur nul (java.lang.NullPointerException) dans les systèmes Java, en ajoutant la possibilité de créer des API plus expressives qui tiennent compte de la possibilité que des valeurs de retour manquent parfois.

## Ce qu'il n'essaie pas de résoudre
Optional n'est pas censé être un mécanisme pour éviter tous les types de pointeurs nuls. Les paramètres d'entrée obligatoires des méthodes et des constructeurs doivent encore être testés par exemple.

Comme lors de l'utilisation de null, Optional n'aide pas à transmettre la signification d'une valeur absente. De la même manière, null peut signifier beaucoup de choses différentes (valeur non trouvée, etc.), tout comme une valeur facultative absente.

L'appelant de la méthode devra toujours vérifier la documentation de la méthode pour comprendre la signification de l'option facultative, afin de la traiter correctement.

## Un exemple

`User getUser(...)` 

Ceci rendrait par exemple un utilisateur recherché en BD. Mais il est possible qu'en fonction des critères on ne trouve pas, donc avant Optional on rendait null ou on levait une exception... 

Un développeur pouvait utiliser cette fonction ainsi: `getUser(...).getEmail()` par exemple. Et dans le cas ou getUser renvoyait null, il y avait une erreur java.lang.NullPointerException à l'execution (et pas à la compilation!)

Une méthode `Optional<User> getUser(...)` est syntaxiquement et sémantiquement plus expressive et Optional possède une API permettant de faire l'introspection nécessaire

## Alors Optional c'est quoi finalement?
Tout simplement un conteneur de référence d'un d'objet pouvant être null ou pas. Avec une api de requête concernant la référence 

## L'utilisation de Optional dans un cadre de programmation fonctionnel est encore plus évident

Dans la séquence : flux données en entrée ->  filter -> map -> reduce 

[ ] TODO Référencer paradigmes fonctionnel en JAVA 

## Une illustration

```java
public class OptionalExemple {
    public static int tailleDe(String str) {
        return str.length();
    }

    public static void main(String... args) {
        String str=null;
        //Traitement qui devrait donner une valeur à str.....
        System.out.println(tailleDe(str));
    }
}
```

Compilation ok... si le Traitement ne donne pas de valeur a str à l'execution on a ceci:

```bash
Exception in thread "main" java.lang.NullPointerException
        at OptionalExemple.tailleDe(OptionalExemple.java:3)
        at OptionalExemple.main(OptionalExemple.java:9)
```

### solution 1

```java
public class OptionalExempleS1 {
    public static int tailleDe(String str) {
        if (str !=null) {
            return str.length();
        } else {
            //Quoi mettre ici? par exemple
            return 0;
        }
    }

    public static void main(String... args) {
        String str=null;
        //Traitement qui devrait donner une valeur à str.....
        System.out.println(tailleDe(str));
    }
}
```

### solution 2 (avec Optional et paradigme fonctionnel)

```java
import java.util.Optional;

public class OptionalExempleS2 {
    public static int tailleDe(Optional<String> str) {
        return str.map(String::length).orElse(0);
    }

    public static void main(String... args) {
        String str=null;
        //Traitement qui devrait donner une valeur à str.....
        System.out.println(tailleDe(Optional.ofNullable(str)));
    }
}
```