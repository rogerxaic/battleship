# Battleship

Projet JAVA de l'INSA de Lyon. Création du jeu "bataille navale".

##Build & execute instructions
```bash
javac -d build -s src/ src/battleship/*.java
java -cp build/ battleship.Battleship
```
Il faut que le dossier "build" existe pour que les commandes fonctionnent. Pour faire cela il suffit d'executer :
```bash
mkdir build
```
Autrement, on pourrait créer un dossier avec un nom différent et changer chaque "build" qui apparait dans les commandes précedentes pour le nom du dossier qu'on vient de créer.
