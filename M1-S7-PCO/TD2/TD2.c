#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>
#include <stdlib.h>

char *PHRASE1 = "Souvent, pour s’amuser, les hommes d’équipage\n";
char *PHRASE2 = "Prennent des goélands, vastes oiseaux des mers,\n";
char *PHRASE3 = "Qui suivent, indolents compagnons de voyage,\n";
char *PHRASE4 = "Le navire glissant sur les gouffres amers.";

void *aff_T(void *mess)
{
    char *s = (char *)mess;
    for (int i = 0; i < strlen(s); i++)
    {
        putchar(s[i]);
        usleep(7000);
    }
    printf("\n");

    return 0;
}

void Question1(void)
{
    (*aff_T)(PHRASE1);
    (*aff_T)(PHRASE2);
    (*aff_T)(PHRASE3);
    (*aff_T)(PHRASE4);
}

void Question2(void)
{
    pthread_t T1, T2, T3, T4;

    pthread_create(&T1, NULL, aff_T, PHRASE1);
    pthread_create(&T2, NULL, aff_T, PHRASE2);
    pthread_create(&T3, NULL, aff_T, PHRASE3);
    pthread_create(&T4, NULL, aff_T, PHRASE4);
}

void Question3(void){
    pthread_t T1, T2, T3, T4;

    pthread_create(&T1, NULL, aff_T, PHRASE1);
    pthread_join(T1, NULL);

    pthread_create(&T2, NULL, aff_T, PHRASE2);
    pthread_join(T2, NULL);

    pthread_create(&T3, NULL, aff_T, PHRASE3);
    pthread_join(T3, NULL);

    pthread_create(&T4, NULL, aff_T, PHRASE4);
    pthread_join(T4, NULL);
}

int main(void)
{

    printf("Question1 :\n");
    Question1();
    sleep(1);

    printf("\n\nQuestion2 :\n");
    Question2();
    sleep(1);

    printf("\n\nQuestion3 :\n");
    Question3();

    return 0;
}