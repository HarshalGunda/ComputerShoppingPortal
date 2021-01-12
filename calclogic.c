#include <stdio.h>

extern int countTotal(int [], int);

int countTotal(int arr[], int length)
{
    int i, sum = 0;

    for(i=0; i<length; i++)
    {
        sum+=arr[i];
    }

    return sum;
}