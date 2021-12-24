/* SumaVectoresC.c 
 Suma de dos vectores: v3 = v1 + v2
 
 Para compilar usar (-lrt: real time library, es posible que no sea necesario usar -lrt):  
	gcc  -O2  SumaVectores.c -o SumaVectores -lrt
	gcc  -O2 -S SumaVectores.c -lrt 

 Para ejecutar use: SumaVectoresC longitud
		
*/ 

#include <stdlib.h>	// biblioteca con funciones atoi(), malloc() y free()
#include <stdio.h>	// biblioteca donde se encuentra la funciÃ³n printf()
#include <time.h>	// biblioteca donde se encuentra la funciÃ³n clock_gettime() 

//SÃ³lo puede estar definida una de las tres constantes VECTOR_ (sÃ³lo uno de los ... 
//tres defines siguientes puede estar descomentado):
// #define VECTOR_LOCAL	// descomentar para que los vectores sean variables ...
			// locales (si se supera el tamaÃ±o de la pila se ...
			// generarÃ¡ el error "ViolaciÃ³n de Segmento")
#define VECTOR_GLOBAL // descomentar para que los vectores sean variables ...
			// globales (su longitud no estarÃ¡ limitada por el ...
			// tamaÃ±o de la pila del programa)
// #define VECTOR_DYNAMIC	// descomentar para que los vectores sean variables ...
			// dinÃ¡micas (memoria reutilizable durante la ejecuciÃ³n)

#ifdef VECTOR_GLOBAL
#define MAX 33554432	//=2^25
//#define MAX 4294967295  //=2^32 -1
   
double v1[MAX], v2[MAX], v3[MAX]; 
#endif
int main(int argc, char** argv){ 
  
  int i; 
  double cgt1, cgt2;

  struct timespec cgt1,cgt2; double ncgt; //para tiempo de ejecuciÃ³n

  //Leer argumento de entrada (nÂº de componentes del vector)
  if (argc<2){	
    printf("Faltan nÂº componentes del vector\n");
    exit(-1);
  }
  
  unsigned int N = atoi(argv[1]);	// MÃ¡ximo N =2^32-1=4294967295 (sizeof(unsigned int) = 4 B)
  //printf("TamaÃ±o Vectores:%u (%u B)\n",N, sizeof(unsigned int)); 
  #ifdef VECTOR_LOCAL
  double v1[N], v2[N], v3[N];   // TamaÃ±o variable local en tiempo de ejecuciÃ³n ...
				// disponible en C a partir de C99 
  #endif
  #ifdef VECTOR_GLOBAL
  if (N>MAX) N=MAX;
  #endif
  #ifdef VECTOR_DYNAMIC
  double *v1, *v2, *v3;
  v1 = (double*) malloc(N*sizeof(double));// malloc necesita el tamaÃ±o en bytes
  v2 = (double*) malloc(N*sizeof(double));
  v3 = (double*) malloc(N*sizeof(double));
  if ((v1 == NULL) || (v2 == NULL) || (v2 == NULL)) {	
    printf("No hay suficiente espacio para los vectores \n");
    exit(-2);
  }
  #endif
 
  //Inicializar vectores 
  if (N < 9)
    pragma omp parallel for for (i = 0; i < N; i++)
    {
      v1[i] = N * 0.1 + i * 0.1;
      v2[i] = N * 0.1 - i * 0.1;
    }
  else
  {
    srand(time(0));
    pragma omp parallel for for (i = 0; i < N; i++)
    {
      v1[i] = rand()/ ((double) rand()); 
      v2[i] = rand()/ ((double) rand()); //printf("%d:%f,%f/",i,v1[i],v2[i]);
    }
  }
 
  cgt1=omp_get_wtime
  //Calcular suma de vectores 
  pragma omp parallel for for(i=0; i<N; i++) 
    v3[i] = v1[i] + v2[i]; 
 
  cgt2=omp_get_wtime
  ncgt=(double) (cgt2.tv_sec-cgt1.tv_sec)+
       (double) ((cgt2.tv_nsec-cgt1.tv_nsec)/(1.e+9));

  //Imprimir resultado de la suma y el tiempo de ejecuciÃ³n
  if (N<10) {
  printf("Tiempo:%11.9f\t / TamaÃ±o Vectores:%u\n",ncgt,N); 
  pragma omp parallel for for(i=0; i<N; i++) 
    printf("/ V1[%d]+V2[%d]=V3[%d](%8.6f+%8.6f=%8.6f) /\n",
           i,i,i,v1[i],v2[i],v3[i]); 
  }
  else
    printf("Tiempo:%11.9f\t / TamaÃ±o Vectores:%u\t/ V1[0]+V2[0]=V3[0](%8.6f+%8.6f=%8.6f) / / V1[%d]+V2[%d]=V3[%d](%8.6f+%8.6f=%8.6f) /\n",
           ncgt,N,v1[0],v2[0],v3[0],N-1,N-1,N-1,v1[N-1],v2[N-1],v3[N-1]); 
 

  #ifdef VECTOR_DYNAMIC
  free(v1); // libera el espacio reservado para v1
  free(v2); // libera el espacio reservado para v2
  free(v3); // libera el espacio reservado para v3
  #endif
  return 0; 
}
