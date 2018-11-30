# Etapes projet Maven Simple

La [LJUG](http://ljug.cofares.net) a préparé un archetype pour vos squeltte de projet dans le cadre de votre formation au [Cnam Liban](http://www.cnam-liban.fr) ou utilisateur de ce projet git dans github 
## but archetype:generate pour créer le projet

### utiliser larchetype net.cofares.ljug:cnamliban-pojo-archetype:1.3 en mode interactif ) 


```Bash
mvn archetype:generate                               \
  -DarchetypeGroupId=net.cofares.ljug                \
  -DarchetypeArtifactId=cnamliban-pojo-archetype     \
  -DarchetypeVersion=1.3
```
suivre les instructions

```Bash
pfares@cnamBackup:~$ mvn archetype:generate                               \
>   -DarchetypeGroupId=net.cofares.ljug                \
>   -DarchetypeArtifactId=cnamliban-pojo-archetype     \
>   -DarchetypeVersion=1.3
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] >>> maven-archetype-plugin:3.0.1:generate (default-cli) > generate-sources @ standalone-pom >>>
[INFO] 
[INFO] <<< maven-archetype-plugin:3.0.1:generate (default-cli) < generate-sources @ standalone-pom <<<
[INFO] 
[INFO] 
[INFO] --- maven-archetype-plugin:3.0.1:generate (default-cli) @ standalone-pom ---
[INFO] Generating project in Interactive mode
[INFO] Archetype repository not defined. Using the one from [net.cofares.ljug:cnamliban-pojo-archetype:1.3] found in catalog local
```
#### Vous pourrez avoir parfois ce message

```Bash
[INFO] Generating project in Interactive mode
[WARNING] Archetype not found in any catalog. Falling back to central repository.
[WARNING] Add a repsoitory with id 'archetype' in your settings.xml if archetype's repository is elsewhere.
Define value for property 'groupId':
```

#### Saisir votre groupId exemple lb.edu.isae

puis les autres informations demandées

```Bash
Define value for property 'groupId': lb.edu.isae
Define value for property 'artifactId': MonPremierProjetMaven
Define value for property 'version' 1.0-SNAPSHOT: : 
Define value for property 'package' lb.edu.isae: : 
Confirm properties configuration:
groupId: lb.edu.isae
artifactId: MonPremierProjetMaven
version: 1.0-SNAPSHOT
package: lb.edu.isae
 Y: : 
```
#### Après plusieurs download probable vous aurez cette conclusion
```Bash
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Archetype: cnamliban-pojo-archetype:1.3
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: lb.edu.isae
[INFO] Parameter: artifactId, Value: MonPremierProjetMaven
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Parameter: package, Value: lb.edu.isae
[INFO] Parameter: packageInPathFormat, Value: lb/edu/isae
[INFO] Parameter: package, Value: lb.edu.isae
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Parameter: groupId, Value: lb.edu.isae
[INFO] Parameter: artifactId, Value: MonPremierProjetMaven
[INFO] Project created from Archetype in dir: /home/cnamliban/MonPremierProjetMaven
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 04:46 min
[INFO] Finished at: 2018-11-30T08:02:14+02:00
[INFO] Final Memory: 17M/224M
[INFO] ------------------------------------------------------------------------
```

### Un autre exemple

```Bash
mvn archetype:generate

[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] >>> maven-archetype-plugin:3.0.1:generate (default-cli) > generate-sources @ standalone-pom >>>
[INFO] 
[INFO] <<< maven-archetype-plugin:3.0.1:generate (default-cli) < generate-sources @ standalone-pom <<<
[INFO] 
[INFO] 
[INFO] --- maven-archetype-plugin:3.0.1:generate (default-cli) @ standalone-pom ---
[INFO] Generating project in Interactive mode
[INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
Choose archetype:
1: remote -> am.ik.archetype:maven-reactjs-blank-archetype (Blank Project for React.js)
2: remote -> am.ik.archetype:msgpack-rpc-jersey-blank-archetype (Blank Project for Spring Boot + Jersey)
3: remote -> am.ik.archetype:mvc-1.0-blank-archetype (MVC 1.0 Blank Project)
4: remote -> am.ik.archetype:spring-boot-blank-archetype (Blank Project for Spring Boot)
5: remote -> am.ik.archetype:spring-boot-docker-blank-archetype (Docker Blank Project for Spring Boot)
...... ETC .....
1982: remote -> us.fatehi:schemacrawler-archetype-maven-project (-)
1983: remote -> us.fatehi:schemacrawler-archetype-plugin-command (-)
1984: remote -> us.fatehi:schemacrawler-archetype-plugin-dbconnector (-)
1985: remote -> us.fatehi:schemacrawler-archetype-plugin-lint (-)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 1073: maven-archetype-quickstart
Choose archetype:
1: remote -> org.apache.maven.archetypes:maven-archetype-quickstart (An archetype which contains a sample Maven project.)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 1
Choose org.apache.maven.archetypes:maven-archetype-quickstart version: 
1: 1.0-alpha-1
2: 1.0-alpha-2
3: 1.0-alpha-3
4: 1.0-alpha-4
5: 1.0
6: 1.1
Choose a number: 6:5
Define value for property 'groupId': lb.edu.isae
Define value for property 'artifactId': app1 
Define value for property 'version' 1.0-SNAPSHOT: : 
Define value for property 'package' lb.edu.isae: : isae
Confirm properties configuration:
groupId: lb.edu.isae
artifactId: app1
version: 1.0-SNAPSHOT
package: isae
 Y: : 
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Old (1.x) Archetype: maven-archetype-quickstart:1.0
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: basedir, Value: /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple
[INFO] Parameter: package, Value: isae
[INFO] Parameter: groupId, Value: lb.edu.isae
[INFO] Parameter: artifactId, Value: app1
[INFO] Parameter: packageName, Value: isae
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] project created from Old (1.x) Archetype in dir: /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 04:04 min
[INFO] Finished at: 2017-11-08T11:44:11+02:00
[INFO] Final Memory: 19M/315M
[INFO] ------------------------------------------------------------------------

```

## Analyser ce qui a été créé par maven

```bash
pfares@cnamBackup:~/mesgit/java-tutorials/JavaTools/MavenSimple$ ls -R
.:
app1  README.md
./app1:
pom.xml  src
./app1/src:
main  test
./app1/src/main:
java
./app1/src/main/java:
isae
./app1/src/main/java/isae:
App.java
./app1/src/test:
java
./app1/src/test/java:
isae
./app1/src/test/java/isae:
AppTest.java
```

### En particulier le pom.xml et la structure des répertoires

## Utiliser les phase du cycle de vie : mvn compile

```bash
pfares@cnamBackup:~/mesgit/java-tutorials/JavaTools/MavenSimple/app1$ mvn compile
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building app1 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ app1 ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1/src/main/resources
[INFO]

    [INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ app1 ---

[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] Compiling 1 source file to /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1/target/classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.650 s
[INFO] Finished at: 2017-11-08T11:53:55+02:00
[INFO] Final Memory: 13M/205M
[INFO] ------------------------------------------------------------------------
```
Remarquer les plugins qui ont été activé

## mvn package (pour crée le package jar et le test)

```
mvn package
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building app1 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ app1 ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ app1 ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ app1 ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ app1 ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] Compiling 1 source file to /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ app1 ---
[INFO] Surefire report directory: /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running isae.AppTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.006 sec

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ app1 ---
[INFO] Building jar: /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1/target/app1-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.095 s
[INFO] Finished at: 2017-11-08T12:07:02+02:00
[INFO] Final Memory: 16M/209M
[INFO] ------------------------------------------------------------------------
```
### verifier ce qui a éé créé

```bash
pfares@cnamBackup:~/mesgit/java-tutorials/JavaTools/MavenSimple/app1$ ls target/
app1-1.0-SNAPSHOT.jar  classes  maven-archiver  maven-status  surefire-reports  test-classes
```

## finalement mvn install (mise a jour du depot local)

```
pfares@cnamBackup:~/mesgit/java-tutorials/JavaTools/MavenSimple/app1$ mvn install
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] Building app1 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ app1 ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ app1 ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ app1 ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ app1 ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ app1 ---
[INFO] Surefire report directory: /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running isae.AppTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.003 sec

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ app1 ---
[INFO] 
[INFO] --- maven-install-plugin:2.4:install (default-install) @ app1 ---
Downloading from central: 
# Remarque parfouis quelques download surtout les premières fois
[INFO] Installing /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1/target/app1-1.0-SNAPSHOT.jar to /home/pfares/.m2/repository/lb/edu/isae/app1/1.0-SNAPSHOT/app1-1.0-SNAPSHOT.jar
[INFO] Installing /home/pascalfares/mesgit/java-tutorials/JavaTools/MavenSimple/app1/pom.xml to /home/pfares/.m2/repository/lb/edu/isae/app1/1.0-SNAPSHOT/app1-1.0-SNAPSHOT.pom
```

### vérifier ~/.m2/repository/lb/edu/isae/app1




