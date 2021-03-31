# GereMonBaB

##### Installation sur un serveur #####

1 - Installer AjaxSwing
  a - Téléchargement : https://creamtec.com/products/ajaxswing/install/index.html
  b - Procèdure d'installation : https://creamtec.com/products/ajaxswing/doc/setup.html
  Remarques : Utiliser openJDK 1.8 (Télécharger SEDK : https://www.oracle.com/fr/java/technologies/javase/javase-jdk8-downloads.html ou commande équivalente)
  
2 - Installer MySQL (https://dev.mysql.com/downloads/mysql/)
  Commandes pour Debian10 :
    apt install php default-mysql-client default-mysql-server php-mysql php-xml
    mysql_secure_installation (et répondre aux questions en y réfléchissant)
    mysql -u root -p pour tester
  Remarques : 
    - Nom de la base de données : GEREMONBAB
    - Login : root
    - Mot de passe : password
    
3 - Installer Drivers pour JDBC/Java - MySQL (https://dev.mysql.com/downloads/connector/j/)
  Remarques pour Debian10 :
    - Mettre/Remplacer le fichier jar (C'est à dire le driver) dans les répertoires ou se trouve les sources et le répertoire BDD puis renseigner le driver dans la variable d'environnement CLASSPATH
    - Alternative : Mettre uniquement le driver dans le dossier /lib/ext (du JRE uttilisé, c'est à dire openJDK1.8)

4 - Initialiser la base de données
  a - Récupérer l'archive git
  b - Compiler l'ensemble des fichiers avec le compilateur (openJDK 1.8) (idem pour /BDD)
  c - Dans BDD, entrer la commande : "java JDBC_connect"
  
5 - Configuration d'AjaxSwing
  Les fichiers JAVA sont compilés.
  Le répertoire de travail est le dossier où se trouve l'ensemble des sources.
  a - Créer le fichier jar (avec openJDK 1.8) : "jar cvfe GereMonBaB.jar GereMonBaB *.class"
  b - Dans le dossier AjaxSwing.../bin : Lancer le script asConsole.XX (en choisissant l'extension correspondant à votre système)
  c - Dans la fenêtre "AjaxSwing Console" cliquer sur AppWizard
  d - Renseigner le champ "Application Name" = GereMonBaB, cliquer sur Next
  e - Renseigner le champ "Main class" = GereMonBaB
  f - Ajouter le CLASSPATH en cliquant sur "ADD..." puis séléctionner le fichier jar crée à l'étape a
  g - Cliquer sur "Finish" dans la fenêtre "AjaxSwing Console"
  h - Démarrer le serveur en cliquant sur "Start Server" 
  
  
  ##### Installation sans le serveur web #####
  
  Voir les étapes 2 à 4.
  Dans ce cas, ne pas tenir compte de la version de Java !
