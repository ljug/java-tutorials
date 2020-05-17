# java-tutorials

Vous trouverez ici:
* Tutorials, howto and how it works 
* Des travaux pratiques
* Des [FAQ](FAQ/) : Questions fréquentes issues de mes auditeurs
* Le dépots des exercices ou traveaux d'auditeurs dans le cadre des UEs : ACCOV, Cycle C informatique et autre cours du [Cnam Liban](http://depinfo.isae.edu.lb)
* Les publications de la [LJUG](http://ljug.cofares.net) ( [Lebanese Java Users group](http://ljug.cofares.net) )

# Activités du moment

- [x] Application complètes a la mode "microservice" (afin de donner une idée des concepts derrière ce pattern d'implémentation), utilisation JPA, RESTFull, Serveur ambarqué Grizzly, Lambda Java8, pattern conception: Fabrique, Callback, Interpreteur, Observer.
- [x] Documentation du cours cycle C
- [x] FAQ
- [x] Les nouveautés Java à partir de la version 9, Visiter [Nouveautés Java >9](NouveautesJDK9+/)

# Thèmes

* [Code Camp](#code-camp)
* [Lambda Java8](#lambda)
* [J2EE](#j2ee)
* [Concurence et Synchronisation (ext)](http://concurrence.cofares.net/)
* [Design patterns en Java](http://design-patterns.cofares.net/)
* [Transverse, Non classifié](Autre)

## [Code Camp](CampCode) 

* Exemple générique dans plusieurs domaines
  * [Jersy Grizzly2](CampCode/jersey-grizzly2/) Service Jersey ambarqué (sans serveur d'application externe)
  * [JPA Jersey Grizzly2](CampCode/jpa-jersey-grizzly2) Service Jersey ambarqué avec ORM JPA eclipse Link (sans serveur d'application externe)
* [Cas d'utilisation](CasUtilisation) exemples couvrant l'utilisation et la création de de librairies (design patern fabrique), l'exposition de services répartis par restful, utilisation de JPA (ORM EclispeLink), Maven, Grizlly2, Un interpreteur [REPL](https://en.wikipedia.org/wiki/Read%E2%80%93eval%E2%80%93print_loop). Plus généralement une application construite a base de "Composant" un composant fournit (réalise) des services et consomme (utilise) d'autres services.

## Lambda
* [Lambda java 8](Lambdas)

## Java Tools 

* [Maven](JavaTools)
* [Quelques trucs et astuces Maven](JavaTools/#Howto)
* [Autre](Autre)

## J2EE

* [J2EE 8](J2EE8/j2ee.asciidoc) en complmeent de j2ee.isae.edu.lb
* [J2EE pour Cycle C Cnam Liban](J2EE-C1) en complmeent de j2ee.isae.edu.lb
* [Camp code](CampCode) Exemple générique dans plusieurs domaines (Rest, Grizzly, JPA, Securiser des service web avec reverse proxu apache2, etc...)
* [Un tomee 7.0.4 préconfiguré et complements](https://github.com/ljug/tomee704)

## [Transverse, Non classifié](Autre)

* [Utilitaires et structures de données](/utilitaires/)

## ACCOV

# Liens externes et utiles

* [Portail de l'animateur](http://www.cofares.net)
* [Lebanese Java User Group](http://ljug.cofares.net)
* [Portail vers d'autres compléments](http://cours.cofares.net)

# Pour les auditeurs du Cnam Liban possédant un compte @isae.edu.lb

* [Compléments et supports](https://drive.google.com/open?id=0B2NK97qOKj2jOWwwZnRFdmc4em8)

# Le dépot maven dans le repoertoire mvn-repo

Lien vers le depot maven de ljug https://github.com/ljug/java-tutorials/tree/master/mvn-repo

Faire ceci `mvn install -DperformRelease=true -DcreateChecksum=true` puis copier cp -r .m2/repositry/<racinedevotreartifact> <artifactidpath>


---
* [ ] TODO A CLASSER ce qui suit ailleur!


# générer les jar avec les dépendances

Inserrer ce code | Puis
-----------------|-----
```XML
<build> 
  <plugins>
    <plugin> 
      <artifactId>maven-assembly-plugin</artifactId> 
      <configuration> 
        <archive>
          <manifest>
            <mainClass>fully.qualified.MainClass</mainClass>
          </manifest>
        </archive>
        <descriptorRefs>
          <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
      </configuration>
    </plugin>
  </plugins>
</build> 
```
| mvn clean compile assembly:single

# Actions

* [ ] Préparer les présentations
* [ ] Finaliser Rest Jersey Grizzly JPA
* [ ] Securiser Griszly
* [ ] Utiliser Asciidoctor http://asciidoctor.org/docs/asciidoc-syntax-quick-reference/

# Le depot maven central de ljug (Lebanese Java Users Group) groupId=net.cofares.ljug

# La LJUG : [Lebanese Java Users Group](http://ljug.cofares.net)

* Un blog http://ljug.cofares.net
* et ce site http://java.cofares.net

