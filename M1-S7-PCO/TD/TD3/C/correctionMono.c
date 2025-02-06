
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <math.h>

#define N 20000

double max = 0;
int x_max=1, y_max=1;

double f(int x, int y)
{
    return (x * x * x) * cos((double)x) - (y * y * y) * sin((double)y) - (x*x*x)/(y*y);
}

int main(void)
{
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
    printf("le max est atteint en (x=%d, y=%d) et vaut %.2f\n", x_max, y_max, f(x_max, y_max));
}