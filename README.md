#AGB

Ceci est la documentation de AGB (Application de Gestion de Bibliothèque) développé par Sébastien Dutertre et Maxime Chevignard. Afin de se connecter sur ce premier projet sans base de données, il suffit d'entrer les couples suivants : seb/pwdseb (compte Bibliothécaire) ou max/pwdmax (compte adhérent). Il existe une autre manière de se connecter, en cliquant sur "Accéder à la base de données" sans avoir rentré de couple, et alors la session sera Temporaire avec l'utilisateur Anonyme.

En tant qu'utilisateur Anonyme, vous pourrez faire une recherche de livre avec soit le titre du livre soit son auteur. Voici la liste des livres disponibles pour le moment dans la maquette :

"Pierre Boule","La Planete des Singes"
"Sylvain Tesson","Dans les forêts de Siberie"
"Albert Camus","La Peste"
"Haruki Murakami","1Q84 Trilogie"
"Herge","Tintin au Tibet"
"Herge","Objectif Lune"
"Karl Marx","Das Kapital"
"Timothy Lachin","Into the Cronosphere"

Les fonctions implémentées :
Adhérent :
Réserver un livre : cette fonction ajoute un emprunt dans la liste sans diminuer la liste de livres disponibles.
Annuler une réservation : retire la réservation pour le livre indiqué de l'adhérent connecté (s'il elle existe).

Bibliothécaire :
Ajouter ou supprimer des exemplaires : s'il s'agit d'un nouveau livre, celui-ci est automatiquement considéré comme tel et ajouté à la bibliothèque. Un nombre négatif permet de supprimer des exemplaires (mais on ne vas pas dans le négatif). 
Supprimer un livre : retire un livre de la bibliothèque.
Emprunt ou Restitution : enregistre un emprunt ou une restitution pour un utilisateur. Lors d'un emprunt, si l'exemplaire visé faisait l'objet d'une réservation alors cette réservation devient un emprunt (marqué grâce à un booleen) et l'exemplaire est marqué comme emprunté. En cas de restitution : l'emprunt est supprimé de la liste des emprunts.

La deuxième partie du TP a donc été d'implémenter un EJB (Entreprise Java Bean) dans notre architecture. Cet EJB est un EJB Singleton qui permet donc à de multiples utilisateurs de se connecter et d'accéder aux livres de la liste, d'ajouter de nouveaux livres, de les supprimer, de les réserver, etc... 