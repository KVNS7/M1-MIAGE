#include <stdio.h>
#include <pthread.h>

#define LOOPS 100000

int x = 0;

pthread_mutex_t  monMutex = PTHREAD_MUTEX_INITIALIZER;

void *incremente(){ // fonction d'incrémentation
    for (int i = 0; i < LOOPS; i++){
        pthread_mutex_lock(&monMutex);
        x++;
        pthread_mutex_unlock(&monMutex);
    }

    return NULL;
}

void *decremente(){ // fonction de décrémentation
    for (int i = 0; i < LOOPS; i++){
        pthread_mutex_lock(&monMutex);
        x--;
        pthread_mutex_unlock(&monMutex);
    }
    return NULL;
}

int main(){

    printf("\n\n Début : x = %d", x);

    pthread_t T_incr, T_decr; // déclaration des threads décrémente et incrémente

    pthread_create(&T_incr, NULL, incremente, NULL);    // lance le thread d'incrémentation
    pthread_create(&T_decr, NULL, decremente, NULL);   // mance le thread de décrémentaiton

    pthread_join(T_incr, NULL);     // attendre la fin des threads
    pthread_join(T_decr, NULL);

    printf("\n Final x = %d\n\n", x);

    return 0;
}
