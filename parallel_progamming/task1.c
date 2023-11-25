#include <stdio.h>
#include <math.h>
#include <omp.h>

int main() {
    
    double a = 10;
     
    double startInterval = 0.0;      
    double endInterval = 100.0;   

       int numSlices = 2000000000; 


    double width = (endInterval - startInterval) / numSlices;

    double totalVolume = 0.0;

    double start = omp_get_wtime();
    #pragma omp parallel for reduction (+:totalVolume)
    for (int i = 0; i < numSlices; i++) {
        //Calculate the "x" value for the current slice
        double xValue = startInterval + i * width;
        
        // Calculate the y-value (radius) of the parabola at the given x-value
        double yValue = sqrt(a * xValue * xValue);
        
        totalVolume += M_PI * yValue * yValue * width;
    }
    double end = omp_get_wtime();
    double difference = end - start; 

    printf("Volume of the paraboloid: %.2f\n", totalVolume);
    printf("Difference between end and start: %f\n", difference);

    return 0;
}



