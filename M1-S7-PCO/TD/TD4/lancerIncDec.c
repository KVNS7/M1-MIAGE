#include <stdio.h>
#include <stdlib.h>

int main() {
    int i;

    // Définir le nombre d'itérations
    int iterations = 10;

    int result = system("gcc -o incr-decrX ./incr-decrX.c");
    // Boucle for pour appeler le programme incr-decrX.c en boucle
    for (i = 0; i < iterations; i++) {        
        // Utilisation de system() pour lancer le programme incr-decrX.c
        result = system("./incr-decrX");

        // Vérification du résultat du lancement du programme
        if (result == -1) {
            perror("Erreur lors de l'exécution de incr-decrX");
            return 1;
        }
    }

    return 0;
}
