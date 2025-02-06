#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

#define N 10

pthread_mutex_t Tmutex[N];
pthread_t Tthread[N];
pthread_mutex_t signal_fin;

char Val;
void *f(void *x)
{
    int id;
    id = *(int *)x;
    printf("la tache %d demarre \n", id);
    while (1)
    {
        pthread_mutex_lock(&(Tmutex[id])); // la tâche attend le signal
        putchar(Val);
        pthread_mutex_unlock(&signal_fin);

        if (Val == '0')
            break;
    }
    return NULL;
}

int main(void)
{
    for (int i = 0; i < N; i++)
    {
        pthread_mutex_init(&(Tmutex[i]), NULL);
        pthread_mutex_lock(&(Tmutex[i]));
        int *id = (int *)malloc(sizeof(int));
        pthread_mutex_init(&signal_fin, NULL);
        pthread_mutex_lock(&signal_fin);

        *id = i;
        pthread_create(&(Tthread[i]), NULL, f, (void *)id);
    }
    printf("les tâches sont démarrées et en attente\n");
    usleep(100);
    while (1)
    {
        printf("Entrez un caractère :");
        scanf("%c", &Val);
        fflush(stdin);

        for (int i = 0; i < N; i++)
        {
            pthread_mutex_unlock(&(Tmutex[i])); // envoi du signal à la tâche i
        }
        for (int i = 0; i < N; i++){
            pthread_mutex_lock(&signal_fin);
        }

            if (Val == '0')
                break;
        usleep(1000);
        printf("\n");
    }
    for (int i = 0; i < N; i++)
    {
        pthread_join(Tthread[i], NULL);
    }
    printf("\n");
    return 0;
}