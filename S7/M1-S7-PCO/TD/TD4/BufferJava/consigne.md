définir une classe buffer qui contient des objets (on peut remplacer les objets par des char pour simplifier)

Put depose un objet dans le buffer
get qui retient un objet

si j'ai déposer a b c d je dois lire a b c d
buffer à une taille n (taille max)

pour travailler en mode circulaire je prend l'indice lire et écrire a 0 et a chaque fois que j'avance j'augmente l'indice modulo n

une thread producteur, une thread consommateur, ralentir l'un devrait ralentir l'autre etc


je maintient un entier avec le nombre d'elements non lus

quand get bloqué tant que y'a pas un objet, put bloqué tant que y'a pas de place