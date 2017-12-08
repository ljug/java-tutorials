# Quelques exemple plus consistants

## gestion de profils et de Realm (utilisateur et roles)

### Les sous-module

* [Interpreteur de commade REPL](URCommandes.md)
* [Gestion réalm version JPA directe](RealmCLI.md)
* [Gestion réalm version JPA Restfull sans Serveur d'application](RealmAuto.md)

L'objectif est de préparer un depots pour la securisation des applications J2EE grace à un domaine de sécurité "Realm" , Utilsateurs et Roles 

Un "Realm" = royaume d’autentification = lieu de stockage des infos logi/pwd/role

* Nous crérons 2 applications en pure Java sans conteneurs d'application web
  1. En ligne de commande : JPA, Mysql et POJO JAVA
  2. En integrant un serveur ambarqueé grizlly et l'utilisation de service Restfull jersey (une version augmenté de 1)

## _La vue_ base de donnée Mysql et le catalogue Realm et les trois table users, roles, users_roles : _pour demarrer_

```SQL
CREATE DATABASE  IF NOT EXISTS `Realm` 
USE `Realm`;

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `role_name` varchar(15) NOT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_name` varchar(15) NOT NULL,
  `role_name` varchar(15) NOT NULL,
  PRIMARY KEY (`user_name`,`role_name`),
  KEY `fk_user_roles_2_idx` (`role_name`),
  CONSTRAINT `fk_role_name` FOREIGN KEY (`role_name`) REFERENCES `roles` (`role_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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


## _La vue_ Entité 

## Liens 
MysqlDB.scripts
README.md
RealmCLI
UtilitairesLJUG