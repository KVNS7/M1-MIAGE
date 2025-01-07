#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <time.h>

#define N 20000

double max = 0;
int x_max = 1, y_max = 1;

double f(int x, int y)
{
    return (x * x * x) * cos((double)x) - (y * y * y) * sin((double)y) - (x * x * x) / (y * y);
}

int main(void)
{
    clock_t start = clock(); // Début de la mesure

    for (int x = 1; x <= N; x++)
    {
        for (int y = 1; y <= N; y++)
        {
            double cal = f(x, y);
            if (fabs(cal) > max)
            {
                max = cal;
                x_max = x;
                y_max = y;
            }
        }
    }

    clock_t end = clock(); // Fin de la mesure
    double time_spent = (double)(end - start) / CLOCKS_PER_SEC;

    printf("Le max est atteint en (x=%d, y=%d) et vaut %.2f\n", x_max, y_max, f(x_max, y_max));
    printf("Temps d'exécution : %.2f secondes\n", time_spent);

    return 0;
}

// TODO : à trouver : (x=17395, y=7133)  |  4 293 189 394.69
