#Un test simple d'un service web restfull avec Tomcat ou TomEE

Utilisation de NETBEANS, TOMEE

### C'est quoi TOMEE

Tomee est une tomcat standard augmenter des librairies permettant d'obtenir une implémentation complète de J2EE 6 (profil web)
C'est à dire:

* CDI - Apache OpenWebBeans
* EJB - Apache OpenEJB
* JPA - Apache OpenJPA
* JSF - Apache MyFaces
* JSP - Apache Tomcat
* JSTL - Apache Tomcat
* JTA - Apache Geronimo Transaction
* Servlet - Apache Tomcat
* Javamail - Apache Geronimo JavaMail
* Bean Validation - Apache BVal

Recupérer [TOMEE](http://tomee.apache.org/apache-tomee.html)

##Qu’est-ce que REST ?


REST (Representational State Transfer) est un style d’architecture qui repose sur le protocole HTTP 
: On accède à une ressource (par son URI unique) pour procéder à diverses opérations 
(GET lecture / POST écriture / PUT modification / DELETE suppression), opérations supportées nativement par HTTP.

En quelques sorte un PUT serait utiliser pour un "update" dans une BD, et POST pour un "insert".

##Les principes

L’adresse de notre service représente le « point terminal » (endpoint) : http://localhost:8080/.
 (si notre service n’était pas uniquement un serveur REST, notre point terminal pourrait être http://localhost:8080/testRest dans l'environnment tomcat ou tomee testRest represente le contexte d'application, 
un serveur pouvant posséder plusieurs contexte d'applications.)
 
Le point terminal n’est ni plus ni moins l’adresse de notre webservice.

Notre service contient des ressources, en particulier des TestC, qui pourront être manipulés à une URI formée par convention de la sorte : http://point_terminal/nom_de_ressource/, soit dans notre exemple http://localhost:8080/testRest/wr.

##Les formats d’échange
REST n’impose ni ne revendique un format d’échange entre client et serveur.

Vous êtes libre de représenter vos données en XML, en JSON, ou tout autre format sérialisé ou dans tout autre dialecte de votre propre cru (sans oublier que le but est souvent d’exposer des services vers l’extérieur).

Il n’est pas rare que les services REST permettent au client d’indiquer le format dans lequel ils souhaitent dialoguer,
 sous la forme par exemple d’un paramètre supplémentaire dans l’URL, ou plus simplement grâce aux en tête HTTP en spécifiant le content-type.
