

---
# Outils pour projets Java

 [Vers la présentation du cours](https://docs.google.com/presentation/d/11EZh-Xo4qAX_JgPmYslyLWuTeY1pt2y9yma9AraYDIM/edit?usp=sharing)

## maven

* [Projet Maven simple](MavenSimple)
* [Modifier le pom](pluginsConfig)

# Lien externe pour répondre a ces questions https://java.developpez.com/faq/maven/?page=Terminologie-et-documentation

* Qu'est-ce que Maven ?
* Qu'est-ce que le POM ?
* Qu'est-ce qu'un archetype ?
* Qu'est-ce qu'une dépendance ?
* Qu'est-ce qu'un artefact ?
* Qu'est-ce que le groupId/artifactId ?
* Qu'est-ce qu'un SNAPSHOT ?
* Qu'est-ce que le repository local, distant ?


# Howto

## Le dépot maven dans le repoertoire mvn-repo

Lien vers le depot maven de ljug https://github.com/ljug/java-tutorials/tree/master/mvn-repo

Faire ceci `mvn install -DperformRelease=true -DcreateChecksum=true` puis copier cp -r .m2/repositry/<racinedevotreartifact> <artifactidpath>

## génerer les jar avec les dependances

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

TODO

- [] A compléter
