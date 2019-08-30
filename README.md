# OC-DA JAVA-P3

## Présentation

L'application Gameplay est un jeu opposant deux adversaires et consistant à deviner une combinaison secrète choisie aléatoirement par un 
des joueurs. L'un des joueurs peut-être une intelligence artificielle (ordinateur exécutant l'application).
Le joueur qui définit la combinaison secrète est désigné défenseur, celui chargé de deviner la combinaison est l'attaquant.
Le défenseur commence par définir sa combinaison secrète. L'attaquant dispose ensuite d'un nombre limité de tentatives pour deviner la 
combinaison du défenseur.


## Configuration

L'application est configurée à partir d'une fichier config.properties. Ce fichier définit les valeurs par défaut des principaux paramètres;
à savoir: le nombre de chiffres définissant la combinaison, le nombre de tentatives et le mode de fonctionnement.

Deux mode sont possibles: 

1. le mode standard 
2. le mode développeur faisant apparaitre la combinaison secrète dès le début. Ce mode permet de vérifier si le programme analyse 
   correctement la combinaison entrée au clavier

Par défaut:<br/>
<br/>
  - le nombre de tentatives est 5<br/>
  - le nombre de chiffres de la combinaison secrète est 4<br/>
  - le mode standard est activé<br/>

## Importation et compilation sous Eclipse

Pour installer l'application sous Eclipse:<br/>
- Téléchargez l'archive du projet
- Sous Eclipse, cliquez sur Import > General > Existing Project into Workspace
- Cochez l'option Select archive file
- Séléctionnez l'archive dans le repertoire correct (bouton Browse)
- Cliquez sur Finish.

Pour compiler l'application sous Eclipse:<br/>
- Dans le menu run choisir Run configuartion
- Dans le volet de gauche, choisir Java Application > New application
- Dans le volet de droite, choisir onglet Main et choisir la classe principale Gameplay
- Cliquer sur le bouton Run
- Faites clic-droit sur le nom du projet
- Choisir Export > Java > Runnable Jar file
- Choisir la dossier de destination et nom du jar
- Valider

## Mode d'emploi

Au lancement de l'application, un menu s'affiche et vous êtes invité à choisir un mode de jeu:<br/>
- mode challenger: vous chercherez à deviner la combinaison de l'intelligence artificielle
- mode défenseur: vous définierez une combinaison secrète que l'intelligence artificielle devra deviner
- mode duel: chaque adversaire définit sa combinaison secrète et cherchent l'un après l'autre à deviner la combinaison adverse
- mode humains: même mode que précédemment mais l'intelligence artificielle est remplacée par un jouer humain

A la fin de la partie, vous êtes invité à rejouer dans le même mode, dans un autre mode ou à quitter l'application.

L'application se lance en ligne de commande par:<br/>
java -jar Gameplay.jar <br/>
<br/>
Pour activer le mode développeur, utiliser le paramètre: dev<br/>
java -jar Gameplay.jar dev


