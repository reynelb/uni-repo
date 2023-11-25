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






// {
//     double a, b, c; 
    
//     printf("a: ");
//     scanf("%lf", &a);
//     printf("b: ");
//     scanf("%lf", &b);
//     printf("c: ");
//     scanf("%lf", &c);
    
    
//     double stepSize, volume, radius, y_value; 
//     int numberOfSteps = 1000; 
//     stepSize = 10.0 / numberOfSteps; // This defines the thickness of the disk 
//     volume = 0; 

//     for (int i = 0; i < numberOfSteps; i++)
//     {
    
//         y_value = i * stepSize;
//         radius = sqrt(a*y_value*y_value + b*y_value + c);
        
//         double cylinderVolume = M_PI * pow(radius, 2) * y_value;

//         volume += cylinderVolume;
        
//     }
    
//     printf("%.2f", volume);
    
//     return 0;
// }



/*
int main()
{
    int a, b, c, total; 
    
    printf("a: ");
    scanf("%d", &a);
    printf("b: ");
    scanf("%d", &b);
    printf("c: ");
    scanf("%d", &c);
    
    
    double x1, x2, l, h, v, r; 
    h = 0.1;
    v = 0; 
    
    for (int i = 0; i < 100; i++)
    {
        l = i*h; 
        total = b*b - 4*a*(c-1);
        x1 = (-b - sqrt(total)/2*a);
        x2 = (-b + sqrt(total)/2*a);
        r = fabs(x2-x1)/2;
        v = v + M_PI * pow(r,2)*h;
        
    }
    
    printf("%.2f", v);
    
    return 0;
    
    
}*/