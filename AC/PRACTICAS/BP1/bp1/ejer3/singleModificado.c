#include <stdio.h>
#include <omp.h>
main() {
    int n = 9, i, a, b[n];
    for (i=0; i<n; i++) b[i] = -1;
    #pragma omp parallel
    {
        #pragma omp master
        { 
            printf("Introduce valor de inicialización a: \n");
            scanf("%d", &a );
            printf("Single ejecutada por el thread %d\n",
            omp_get_thread_num());
        }
        #pragma omp barrier 

        #pragma omp for
            for (i=0; i<n; i++)
            b[i] = a;

        #pragma omp master
        {
            printf("Dentro de la región parallel:\n");
            for (i=0; i<n; i++) printf("b[%d] = %d\t",i,b[i]);
            printf("\n");
            printf("Single ejecutada por el thread %d\n",
            omp_get_thread_num());
        }
        #pragma omp barrier
           
        }
        printf("Depués de la región parallel:\n");
        for (i=0; i<n; i++) printf("b[%d] = %d\t",i,b[i]);
        printf("\n");
    
}