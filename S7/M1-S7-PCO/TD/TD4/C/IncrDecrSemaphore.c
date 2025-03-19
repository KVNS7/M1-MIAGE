#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>

int val = 0;
sem_t *monSemaphore;

void incremente(){
    val++;
}

void decremente(){
    val--;
}

void *repeat(void *arg) {
    void (*function)() = (void (*)())arg;
    for (int i = 0; i < 100000; i++) {
        sem_wait(monSemaphore);
        function();
        sem_post(monSemaphore);
    }
    return NULL;
}

int main(){

pthread_t inc, dec;

monSemaphore = sem_open("/monSem", O_CREAT, 0644, 1);

printf("\nVal1 = %d\n", val);

pthread_create(&inc, NULL, repeat, (void *)incremente);
pthread_create(&dec, NULL, repeat, (void *)decremente);


pthread_join(inc, NULL);
pthread_join(dec, NULL);

sem_close(monSemaphore);

printf("\nVal2 = %d\n", val);

    return 0;
}