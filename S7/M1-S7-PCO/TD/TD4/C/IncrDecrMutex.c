#include <stdio.h>
#include <pthread.h>

int val = 0;
pthread_mutex_t monMutex = PTHREAD_MUTEX_INITIALIZER;

void incremente(){
    val++;
}

void decremente(){
    val--;
}

void *repeat(void *arg) {
    void (*function)() = (void (*)())arg;
    for (int i = 0; i < 10000; i++) {
        pthread_mutex_lock(&monMutex);
        function();
        pthread_mutex_unlock(&monMutex);
    }
    return NULL;
}

int main(){

pthread_t inc, dec;

printf("\nVal1 = %d\n", val);

pthread_create(&inc, NULL, repeat, (void *)incremente);
pthread_create(&dec, NULL, repeat, (void *)decremente);


pthread_join(inc, NULL);
pthread_join(dec, NULL);


printf("\nVal2 = %d\n", val);

    return 0;
}