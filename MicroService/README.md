# Microservice
En informatique, les microservices sont un style d'architecture logicielle à partir duquel un ensemble complexe d'applications est décomposé en plusieurs processus indépendants et faiblement couplés, souvent spécialisés dans une seule tâche. Les processus indépendants communiquent les uns avec les autres en utilisant des API.

Les API peuvant êtres un librairies expostant une interface public, un EJB, u des services web tel que RESTfull.

Un microservice ou micro composant, fournit (expose) des services et en consome (utilisae) d'autres

Un avantage est que lors d'un besoin critique en une ressource, seul le microservice lié sera augmenté, contrairement à la totalité de l'application dans une architecture classique. L'autre avantage est une meilleur isolation lors des développements étant donnée le faible couplage, donc moins de coordination et test unitaire plus simple (meilleur encapsulation).

L'application finale sera la composition des microservices.

* Les services individuels sont simples à remplacer
* Les services sont conçus pour leur utilité spécifique (par exemple la facturation, la chaîne logistique, l'interface...)
* L'architecture est plus symétrique que hiérarchique (passage d'une architecture client-serveur à une architecture de plusieurs entités communicantes)
* L'architecture facilite le déploiement continu du code

La philosophie de l'architecture microservices s'inspire en grande partie de la philosophie UNIX, qui prône « ne faire qu'une seule chose, et la faire bien ». Elle est décrite comme suit1,2,3 :

* Les services sont petits, et conçus pour remplir une seule fonction.
* L'organisation du projet doit prendre en compte l'automatisation, le déploiement et les tests.
* Chaque service est élastique, résilient, composable, minimal et complet3.

# Plus d'effort pour definir les interfaces publique à long termes