#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <time.h>
#include <pthread.h>

#define N 20000

double max_pair = 0, max_impair = 0; // variables stockant les max pour les parties paire et impair
int x_max_pair = 1, y_max_pair = 1; // variables stockant les max x,y pour la partie pair
int x_max_impair = 1, y_max_impair = 1; // variables stockant les max x,y pour la partie pair

double f(int x, int y){
    return (x * x * x) * cos((double)x) - (y * y * y) * sin((double)y) - (x * x * x) / (y * y);
}

void *calcul_max_pair(void *arg){ // fonction s'occupant de X pair
    printf("Thread pour x pairs démarré.\n");
    for (int x = 2; x <= N; x += 2){
        for (int y = 1; y <= N; y++){

            double cal = f(x, y);
            
            if (fabs(cal) > max_pair){
                max_pair = cal;
                x_max_pair = x;
                y_max_pair = y;
            }
        }
    }
    printf("\nThread pour x pairs terminé.\n");
    return NULL;
}

void *calcul_max_impair(void *arg){ // fonction s'occupant de X impair

    printf("\nThread pour x impairs démarré.\n");

    for (int x = 1; x <= N; x += 2){
        for (int y = 1; y <= N; y++){

            double cal = f(x, y);

            if (fabs(cal) > max_impair)            {
                max_impair = cal;
                x_max_impair = x;
                y_max_impair = y;
            }
        }
    }
    printf("\nThread pour x impairs terminé.\n");
    return NULL;
}

int main(void){

    clock_t start = clock(); // Début de la mesure

    pthread_t T_pair, T_impair; // declaration des threads

    pthread_create(&T_pair, NULL, calcul_max_pair, NULL); // lancement des threads
    pthread_create(&T_impair, NULL, calcul_max_impair, NULL);

    pthread_join(T_pair, NULL); // attendre que les threads finissent
    pthread_join(T_impair, NULL);

    double max_final; // variables stockant les max finaux
    int x_max_final, y_max_final;

    if (fabs(max_pair) > fabs(max_impair)) // comparaison et attribution sur les variables finales
    {
        max_final = max_pair;
        x_max_final = x_max_pair;
        y_max_final = y_max_pair;
    }
    else
    {
        max_final = max_impair;
        x_max_final = x_max_impair;
        y_max_final = y_max_impair;
    }

    clock_t end = clock(); // Fin de la mesure
    double time_spent = (double)(end - start) / CLOCKS_PER_SEC;

    printf("\nLe max est atteint en (x=%d, y=%d) et vaut %.2f\n", x_max_final, y_max_final, f(x_max_final, y_max_final));

    printf("Temps d'exécution : %.2f secondes\n", time_spent);

    return 0;
}

// TODO : à trouver : (x=17395, y=7133)  |  4 293 189 394.69
