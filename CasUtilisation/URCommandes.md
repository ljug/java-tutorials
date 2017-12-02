# interpreteur de commande 'Read–Eval–Print Loop (REPL)' pour la gestion des utilisateurs / roles

## Notion de langages

## Ecrire un traducteur (tokenizer puis parser) 

## Analyseur LR
En informatique, un analyseur LR (pour Left to right, Rightmost derivation en anglais) est un analyseur pour les grammaires non contextuelles qui lit l'entrée de gauche à droite et produit une dérivation droite. On parle aussi d'analyseur LR(k) où k représente le nombre de symboles « anticipés » et non consommés qui sont utilisés pour prendre des décisions d'analyse syntaxique. D'habitude, k vaut 1 et est souvent omis. Une grammaire non contextuelle est appelée LR(k) s'il existe un analyseur syntaxique LR(k) pour elle.

On dit qu'un analyseur syntaxique LR réalise une analyse ascendante car il essaye de déduire les productions du niveau du haut de la grammaire en les construisant à partir des feuilles.

De nombreux langages de programmation sont décrits par des grammaires LR(1), ou du même genre, et, pour cette raison, les analyseurs syntaxiques LR sont souvent utilisés par les compilateurs pour faire l'analyse syntaxique de code source.

### L’analyse par descente récursive Objet (adptation de la descente recursive standard)

On traverse l’arbre de syntaxe concrète d’en haut __Racine__:

* Chaque symbole non-terminal est représenté par une méthode statique parse dans une classe du nom du non-terminal.
* Un scanneur possède 2 méthodes jeton courant et nextjeton.
* Selon le jeton courant, on décide comment développer le nœud actuel.

En même temps, la représentation désirée est construite au moyen des classe non-terminal qui constitura un arbre semantique.

## L'arbre sématique et sont interprétation (evaluation)

* Généré par les appel "parse" de la descente récursive Objet
* interprété an appliquant des actions définies dynamiquement par callback (ou listner) Les actions sont des interfaces fonctionelles. Une ation pout servir 