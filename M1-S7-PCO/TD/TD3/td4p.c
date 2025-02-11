#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <pthread.h>
#include <time.h>

#define N 60000

double max = 0;
int x_max = 1, y_max = 1;
pthread_mutex_t monMutex = PTHREAD_MUTEX_INITIALIZER;

double f(int x, int y) {
    return (x * x * x) * cos((double)x) - (y * y * y) * sin((double)y) - (x * x * x) / (y * y);
}

void *calcule(void *arg) {
    int start = *(int *)arg; // Récupération du point de départ (0 pour pairs, 1 pour impairs)
    double max_loc = 0;
    int x_max_loc = 1, y_max_loc = 1;

    // Calcul pour les valeurs `x` correspondant à pairs ou impairs
    for (int x = start + 1; x <= N; x += 2) {
        for (int y = 1; y <= N; y++) {
            double result = f(x, y);
            if (fabs(result) > max_loc) {
                max_loc = fabs(result);
                x_max_loc = x;
                y_max_loc = y;
            }
        }
    }

    // Mise à jour des variables globales avec verrouillage
    pthread_mutex_lock(&monMutex);
    if (max_loc > max) {
        max = max_loc;
        x_max = x_max_loc;
        y_max = y_max_loc;
    }
    pthread_mutex_unlock(&monMutex);

    return NULL;
}

int main(void) {
    int debut[2] = {0, 1}; // 0 pour les pairs, 1 pour les impairs
    pthread_t mes_thread[2];

    // Chronométrage : début
    clock_t start = clock();

    // Création des threads pour les pairs et les impairs
    pthread_create(&mes_thread[0], NULL, calcule, (void *)&debut[0]);
    pthread_create(&mes_thread[1], NULL, calcule, (void *)&debut[1]);

    // Attente de la fin des threads
    pthread_join(mes_thread[0], NULL);
    pthread_join(mes_thread[1], NULL);

    // Chronométrage : fin
    clock_t end = clock();
    double time_spent = (double)(end - start) / CLOCKS_PER_SEC;

    // Affichage des résultats
    printf("Le max est atteint en (x=%d, y=%d) et vaut %.2f\n", x_max, y_max, max);
    printf("Temps écoulé : %.2f secondes\n", time_spent);

    return 0;
}
