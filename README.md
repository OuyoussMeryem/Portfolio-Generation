Les vidéos de démonstration
**1-Création d'un utilisateur via Keycloak avec des rôles d'administrateur et d'utilisateur**
Dans cette démo, je montre comment créer un utilisateur directement depuis l'interface de Keycloak, en lui attribuant les rôles d'administrateur (ADMIN) et d'utilisateur (USER).


https://github.com/user-attachments/assets/1f310383-2dc2-4cbd-85bd-fe85ceaff1ae



**2-Création d'un utilisateur via l'application Angular avec le rôle utilisateur par défaut**
Cette démo illustre la création d'un utilisateur via mon application Angular. L'utilisateur obtient automatiquement le rôle d'utilisateur (USER) par défaut. Je montre également les contraintes à respecter lors de l'inscription.


https://github.com/user-attachments/assets/20bcd1b7-351e-4a9e-91d0-6a1f0240d8d9



**3-Utilisation de Docker Compose avec PostgreSQL pour Keycloak et MySQL pour les données de l'application**
Dans cette démo, je montre comment les données de Keycloak sont stockées dans une base de données PostgreSQL, tandis que les autres entités JPA de l'application utilisent une base de données MySQL, le tout orchestré via Docker Compose.


https://github.com/user-attachments/assets/44c3bf99-51a6-4642-9c92-4fefa79fc67d



**4-Connexion en tant qu'utilisateur, génération de portfolio et consultation des portfolios générés**
Ici, je montre comment un utilisateur se connecte avec le rôle USER, génère un portfolio, puis consulte la liste des portfolios qu'il a créés, incluant les données d'inscription récupérées depuis Keycloak.


**5-Authentification en tant qu'administrateur et visualisation des portfolios générés par les utilisateurs**
Dans cette démo, un administrateur (ADMIN) se connecte pour accéder à une fonctionnalité supplémentaire : la visualisation du nombre total de portfolios générés dans l'application, ainsi que le nombre de portfolios générés par chaque utilisateur.

