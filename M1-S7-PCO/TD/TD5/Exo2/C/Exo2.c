#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

#define N 10  // Nombre de tâches

// Déclaration des sémaphores et variables
sem_t semaphores[N];
char characters[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

void* task(void* arg) {
    int id = *(int*)arg;
    
    while(1) {
        // Attente du signal du sémaphore correspondant à cette tâche
        sem_wait(&semaphores[id]);

        // Affichage du caractère associé à cette tâche
        putchar(characters[id]);
        
        // Passer le témoin à la tâche suivante (tâche suivante = id + 1)
        int next_task = (id + 1) % N;  // Circuler entre 0 et N-1
        sem_post(&semaphores[next_task]);  // Signal à la tâche suivante
    }

    return NULL;
}

int main() {
    pthread_t threads[N];
    int ids[N];

    // Initialiser les sémaphores, le premier étant à 1 pour démarrer
    for (int i = 0; i < N; i++) {
        sem_init(&semaphores[i], 0, 0);
    }
    sem_post(&semaphores[0]);  // Lancer le premier sémaphore (T1 commence)

    // Créer les threads pour chaque tâche
    for (int i = 0; i < N; i++) {
        ids[i] = i;
        pthread_create(&threads[i], NULL, task, &ids[i]);
    }

    // Attendre que tous les threads terminent (ce qui ne se produit jamais dans cet exemple)
    for (int i = 0; i < N; i++) {
        pthread_join(threads[i], NULL);
    }

    // Détruire les sémaphores à la fin
    for (int i = 0; i < N; i++) {
        sem_destroy(&semaphores[i]);
    }

    return 0;
}
