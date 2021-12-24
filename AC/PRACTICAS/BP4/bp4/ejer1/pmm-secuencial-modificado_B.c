#include <stdio.h>
#include <stdlib.h>
#include <time.h>


int main(int argc, char **argv)
{
    struct timespec cgt1, cgt2;
    double t; 

    if (argc < 2)
    {
        printf("[ERROR]-Debe insertar tamaño matriz\n");
        exit(-1);
    }

    unsigned int N = atoi(argv[1]);
    double **A, **B, **C;

   
    A = (double **)malloc(N * sizeof(double *));
    B = (double **)malloc(N * sizeof(double *));
    C = (double **)malloc(N * sizeof(double *));

    if ((A == NULL) || (B == NULL) || (C == NULL))
        printf("\nNo hay suficiente espacio para la matriz\n");
    
    for (int e = 0; e < N; e++){
        A[e] = (double *) malloc(N * sizeof(double));
        B[e] = (double *)malloc(N * sizeof(double));
        C[e] = (double *)malloc(N * sizeof(double));

        if ((A[e] == NULL) || (B[e] == NULL) || (C[e] == NULL))
            printf("\nNo hay suficiente espacio para la matriz en la columna\n");
    }
    int i, j, k;
    double suma;
    
    srand48(time(NULL));

    
    for (i = 0; i < N; i++)
        for (j = 0; j < N; j++)
        {
            if (N < 9){
                B[i][j] = j + 1;
                C[i][j] = j + 1;
            } else {
                B[i][j] = drand48();
                C[i][j] = drand48();
            }
        }

    
    clock_gettime(CLOCK_REALTIME, &cgt1);

   
    for (i = 0; i < N; i++)
        for (k = 0; k < N; k++)
        {
            A[i][j] = 0;
            for (j = 0; j < N; j++)
                A[i][j] = A[i][j] + B[i][k] * C[k][j];
        }


    clock_gettime(CLOCK_REALTIME, &cgt2);

    t = (double)(cgt2.tv_sec - cgt1.tv_sec) +
        (double)((cgt2.tv_nsec - cgt1.tv_nsec) / (1.e+9));

    
    printf("Tiempo (seg): %0.9f\n", t); 

    printf("\n__________\nResultados:\n");

    printf("\nMatriz B: \n");
    if (N < 9)
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
                printf("%f ", B[i][j]);
            printf("\n");
        }
    else
        printf("B[0][0]=%f B[%d][%d]=%f\n", B[0][0], N - 1, N - 1, B[N - 1][N - 1]);

    printf("\nMatriz C: \n");
    if (N < 9)
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
                printf("%f ", C[i][j]);
            printf("\n");
        }
    else
        printf("C[0][0]=%f C[%d][%d]=%f\n", C[0][0], N - 1, N - 1, C[N - 1][N - 1]);

    printf("\nMatriz A=B*C: \n");
    if (N < 9)
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
                printf("%f ", A[i][j]);
            printf("\n");
        }
    else
        printf("A[0][0]=%f A[%d][%d]=%f\n", A[0][0], N - 1, N - 1, A[N - 1][N - 1]);

    printf("\n");

    for (int e = 0; e < N; e++)
    {
       free(A[e]);
       free(B[e]);
       free(C[e]);
    }
    free(A);
    free(B);
    free(C);

    return 0;
}