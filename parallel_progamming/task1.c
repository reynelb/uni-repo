#include <stdio.h>
#include <math.h>

int main()
{
    double a, b, c; 
    
    printf("a: ");
    scanf("%d", &a);
    printf("b: ");
    scanf("%d", &b);
    printf("c: ");
    scanf("%d", &c);
    
    
    double stepSize, volume, currentX, currentZ; 
    int numberOfSteps = 10; 
    stepSize = 10.0 / numberOfSteps; 
    volume = 0; 

    // Integrate using the disc method from x = 0 to x = 10
    for (int i = 0; i < numberOfSteps; i++)
    {
        // Calculate the current value of the distance from the paraboloid's central axis 
        currentX = i * stepSize; 

        // Determine the depth of the paraboloid at the current X using the ax^2 + bx + c
        currentZ = a*currentX*currentX + b*currentX + c; 

        // dV = pi * (radius^2) * thickness
        double discVolume = M_PI * pow(currentX, 2) * stepSize; 

        // Sum up all the disc volumes to approximate the total volume of paraboloid
        volume += discVolume; 
        
    }
    
    printf("%.2f", volume);
    
    return 0;
}



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