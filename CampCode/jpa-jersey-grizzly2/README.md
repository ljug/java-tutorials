# Exemple Jarser grizzly 2 issue de [Grizzly-Jersey-Intro](http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/griz_jersey_intro/Grizzly-Jersey-Intro.html)

## Un exemple ne necessiatant que Java SE (pas de serveur d'application)

### Grizzly Web Server
Project Grizzly est un serveur Web *en pure Java* construit à l'aide de l'[API NIO](https://www.jmdoudoux.fr/java/dej/chap-nio2.htm). Grizzly est le composant serveur Web pour le serveur d'applications open source [GlassFish](https://javaee.github.io/glassfish/). Cependant, le but de Grizzly est d'aider les développeurs à faire beaucoup plus. Avec Grizzly, vous pouvez créer des serveurs évolutifs et robustes en utilisant NIO, tout en offrant des composants de framework étendus, notamment Web Framework (HTTP / S), WebSocket, Comet, et bien plus!

### Jersey, REST et JAX-RS
Jersey RESTful Web Services framework est un framework open source de qualité de production pour le développement de Services Web RESTfull en Java. Jersey prend en charge les API JAX-RS et sert d'implémentation de référence à JAX-RS. Jersey aide à exposer vos données dans une variété de types de médias de représentation et encapsule les détails de bas niveau de la communication client-serveur. Jersey simplifie le développement des services Web RESTful et de leurs clients en Java de manière standard et portable.

### Scénario
Le service Web RESTful créé dans ce didacticiel est le début d'une application REST pour la gestion des données client. Pour cet exemple, les données client sont stockées dans une liste. Vous allez créer une méthode Web pour renvoyer tous les clients stockés dans la liste et une méthode pour rechercher un client par ID. Pour simplifier les choses, toutes les données sont renvoyées dans un format de texte brut.

   Remaques une autres version de ce didacticiel incopere la persistance avec JPA et les represenation JSON et XML  [JPA Jersey Grizzly](..)

### Vous aurez besoin d'avoir installé

* maven >= 3
* java jdk >= 8
* netbeans >=8.2

## Les étapes

### Créer le projet 

vous pouvez le voir [ici](https://github.com/ljug/java-tutorials/tree/master/CampCode)

```BASH
mvn archetype:generate -DarchetypeArtifactId=jersey-quickstart-grizzly2 -DarchetypeGroupId=org.glassfish.jersey.archetypes -DinteractiveMode=false -DgroupId=net.cofares.ljug -DartifactId=jersey-service -Dpackage=net.cofares.ljug.rest -DarchetypeVersion=2.17
```

### Géneérer et lancer le service

```Bash
cd <le repertoire du projet>
mvn clean compile
mvn exec:java
```
...
Ceci vous donnera en principe ce résultat:
```Bash
déc. 03, 2017 4:50:22 PM org.glassfish.grizzly.http.server.NetworkListener start
INFOS: Started listener bound to [localhost:8080]
déc. 03, 2017 4:50:22 PM org.glassfish.grizzly.http.server.HttpServer start
INFOS: [HttpServer] Started.
Jersey app started with WADL available at http://localhost:8080/myapp/application.wadl
Hit enter to stop it...
```
Aleer dans votre "browser" favory et essayer http://localhost:8080/myapp/application.wadl et http://localhost:8080/myapp/myresource

## Maintenant Enrichissons le service

Ajouter ces 3 fichiers à votre projet (is definissent le serviceWeb l'entité et le dépot d'entité (ici c'est une liste)

[CustomerService.java](CustomerService.java.md), [Customer.java](Customer.java.md) et
[CustomerList.java](CustomerList.java.md)


