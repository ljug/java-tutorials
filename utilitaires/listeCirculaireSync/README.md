# do this to deploy to maven central
mvn clean javadoc:jar source:jar-no-fork verify gpg:sign install deploy
