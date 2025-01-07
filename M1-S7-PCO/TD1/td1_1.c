#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <math.h>
#include <time.h>

#define N 50000

float max = 0;
int x_max, y_max;

float f(int x, int y){
    return x * x * x * sin((double)x) - y * y * y * cos((double)y) + x * y * y;
}

int main(void){

    clock_t debut, fin;
    double temps;
    
    debut = clock();

    for (int x = 0; x <= N; x++){
        for (int y = 0; y <= N; y++){
            float cal = f(x, y);
            if (fabs(cal) > max){
                max = cal;
                x_max = x;
                y_max = y;
            }
        }
    }

    printf("le max est atteint en (x=%d, y=%d) et vaut %f\n", x_max, y_max, max);

    fin = clock();

    temps = ((double)(fin - debut)) / CLOCKS_PER_SEC;

    printf("\nTemps d'exécution : %f secondes \n\n", temps);
}

// Thanks to Hiba for the help