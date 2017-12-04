# java-tutorials
* Tutorials, howto and how it works 
* Des traveaux pratiques
* ACCOV, Cycle C et autre cours du [Cnam Liban](http://depinfo.isae.edu.lb)
* Publications de la [LJUG](http://ljug.cofares.net) ( [Lebanese Java Users group](http://ljug.cofares.net) )

## Lambda
* [Lambda java 8](Lambdas)

## Java Tools 

* [Maven](JavaTools)
* [Quelques trucs et astuces Maven](JavaTools/#Howto)
* [Autre](Autre)

## J2EE


* [J2EE 8](J2EE8) en complmeent de j2ee.isae.edu.lb
* [J2EE pour Cycle C Cnam Liban](J2EE-C1) en complmeent de j2ee.isae.edu.lb
* [Camp code](CampCode) Exemple générique dans plusieurs domaines (Rest, Grizzly, JPA, Securiser des service web avec reverse proxu apache2, etc...)
* [Un tomee 7.0.4 préconfiguré et complements](https://github.com/ljug/tomee704)

## [Code Camp](CampCode) 
 Exemple générique dans plusieurs domaines
  [Jersy Grizzly2](CampCode/jersey-grizzly2/) Service Jersey ambarqué (sans serveur d'application externe)
  [JPA Jersey Grizzly2](CampCode/jpa-jersey-grizzly2) Service Jersey ambarqué avec ORM JPA eclipse Link (sans serveur d'application externe)

## Liens externes et utiles

* [Portail de l'animateur](http://www.cofares.net)
* [Lebanese Java User Group](http://ljug.cofares.net)
* [Portail vers d'autres compléments](http://cours.cofares.net)

## Pour les auditeurs du Cnam Liban possédant un compte @isae.edu.lb

* [Compléments et supports](https://drive.google.com/open?id=0B2NK97qOKj2jOWwwZnRFdmc4em8)

# Le dépot maven dans le repoertoire mvn-repo

Lien vers le depot maven de ljug https://github.com/ljug/java-tutorials/tree/master/mvn-repo

Faire ceci `mvn install -DperformRelease=true -DcreateChecksum=true` puis copier cp -r .m2/repositry/<racinedevotreartifact> <artifactidpath>

# génerer les jar avec les dependances

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
