# Quelques exemple plus consistants

## gestion de profils et de Realm (utilisateur et rôles)

L'objectif de l'application est de préparer un dépôts pour la sécurisation des applications J2EE grace à un domaine de sécurité "Realm" , Utilisateurs et Rôles 

* **Un realm**: (Classification des utilisateurs par rôles) : Un utilisateurs possède plusieurs rôles, chaque rôles permet un certain nombre d'actions. Un rôle regroupe plusieurs utilisateurs.

### Les Opérations prévues

* ajouter/supprimer utiliseur
* ajouter/supprimer rôle
* associer/dissosier utilisateur rôle
* Lister rôles pour un utilisateur OU tous
* Lister utilisateur pour un rôle OU tous

### Une décomposition statique: Les sous-modules (comme microservice)

* [Interpreteur de commade REPL](URCommandes.md)
* [Gestion réalm version JPA directe](RealmCLI/)
* [Gestion réalm version JPA Restfull sans Serveur d'application](RealmAuto.md)

pour en savoir plus concernant les [microservice](/MicroService)? et pourquoi un  [microservice](/MicroService) visiter cette présentation simple [ici](/MicroService)

L'application que ous allons créer sera basé sur la composition de micrioservices, nous produiront notre application en 

1. Découpant notre appli en microservices
2. Developperons chaque microservice avec les design pattern qui vont bien et les technologies adaptés pour chacun d'eux
3. Nou recomposeront les microservices pour obtenir notre application finale 


Un "Realm" = royaume d’autentification = lieu de stockage des infos login/pwd/rôle

* Nous créerons 2 applications en pure Java sans conteneurs d'application web
  1. En ligne de commande : JPA, Mysql et POJO JAVA
  2. En intégrant un serveur embarqué grizlly et l'utilisation de service RESTfull jersey (une version augmenté de 1)

## _La vue_ Entité
## _La vue_ base de donnée Mysql et le catalogue Realm et les trois table users, rôles, users_rôles : _pour démarrer_

```SQL
CREATE DATABASE  IF NOT EXISTS `InitRealm` 
USE `Realm`;

DROP TABLE IF EXISTS `rôles`;

CREATE TABLE `rôles` (
  `rôle_name` varchar(15) NOT NULL,
  PRIMARY KEY (`rôle_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `users_rôles`;
CREATE TABLE `user_rôles` (
  `user_name` varchar(15) NOT NULL,
  `rôle_name` varchar(15) NOT NULL,
  PRIMARY KEY (`user_name`,`rôle_name`),
  KEY `fk_user_rôles_2_idx` (`rôle_name`),
  CONSTRAINT `fk_rôle_name` FOREIGN KEY (`rôle_name`) REFERENCES `rôles` (`rôle_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_name` FOREIGN KEY (`user_name`) REFERENCES `users` (`user_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_name` varchar(15) NOT NULL,
  `user_gsuite` varchar(256) DEFAULT NULL,
  `user_pass` varchar(64) NOT NULL,
  `description` text,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```



## Liens 
MysqlDB.scripts
README.md
RealmCLI
UtilitairesLJUG