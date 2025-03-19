#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <string.h>

char *PHRASE1 = "Souvent, pour s’amuser, les hommes d’équipage";
char *PHRASE2 = "Prennent des goélands, vastes oiseaux des mers,";
char *PHRASE3 = "Qui suivent, indolents compagnons de voyage,";
char *PHRASE4 = "Le navire glissant sur les gouffres amers.";

void *aff_T(void *mess)
{
    char *s = (char *)mess;
    for (int i = 0; i < strlen(s); i++)
    {
        putchar(s[i]);
        usleep(1);
    }
    printf("\n");
    return NULL;
}

void Q1(void)
{
    (*aff_T)(PHRASE1);
    (*aff_T)(PHRASE2);
    (*aff_T)(PHRASE3);
    (*aff_T)(PHRASE4);
}
void Q2(void)
{
    pthread_t T1, T2, T3, T4;

    pthread_create(&T1, NULL, aff_T, PHRASE1);
    pthread_create(&T2, NULL, aff_T, PHRASE2);
    pthread_create(&T3, NULL, aff_T, PHRASE3);
    pthread_create(&T4, NULL, aff_T, PHRASE4);
}
void Q3(void)
{
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
    printf("Q1\n");
    Q1(); //séquentiell
    sleep(1);
    printf("Q2\n");
    Q2(); // concurrente mal faite
    sleep(1);
    printf("Q3\n");
    Q3(); // concurrente bien faite
    return 0;
}
