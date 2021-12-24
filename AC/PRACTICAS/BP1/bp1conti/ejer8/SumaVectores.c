#pragma omp parallel
{
    #pragma omp sections
    {
        #pragma omp section
        {
            for(i=0; i<N/4;i++){
                v1[i]= N*0.1+i+0.1;v2[i] = N*0.1-i*0.1
            }
        }
        #pragma omp section
        {
            for(i=0; i<N/2;i++){
                v1[i]= N*0.1+i+0.1;v2[i] = N*0.1-i*0.1
            }
        }
        #pragma omp section
        {
            for(i=N/2; i<(N*3/4);i++){
                v1[i]= N*0.1+i+0.1;v2[i] = N*0.1-i*0.1
            }
        }
         #pragma omp section
        {
            for(i=(N*3/4); i<N ;i++){
                v1[i]= N*0.1+i+0.1;v2[i] = N*0.1-i*0.1
            }
        }
    
    
    }
    #pragma omp single
    {
        start = omp_get_wtime();
    }
     #pragma omp sections
    {
        #pragma omp section
        {
            for(i=0; i<N/4;i++){
                v1[i]= N*0.1+i+0.1;v2[i] = N*0.1-i*0.1
            }
        }
        #pragma omp section
        {
            for(i=0; i<N/2;i++){
                v1[i]= N*0.1+i+0.1;v2[i] = N*0.1-i*0.1
            }
        }
        #pragma omp section
        {
            for(i=N/2; i<(N*3/4);i++){
                v1[i]= N*0.1+i+0.1;v2[i] = N*0.1-i*0.1
            }
        }
         #pragma omp section
        {
            for(i=(N*3/4); i<N ;i++){
                v1[i]= N*0.1+i+0.1;v2[i] = N*0.1-i*0.1
            }
        }
    
    
    }
    #pragma omp single
    {
        end = omp_get_wtime();
    }






}