#include <stdio.h>
#include <math.h>

int main()
{
    double height, area, volume; 
    
    printf("Enter the height: ");
    scanf("%lf", &height);
    
    printf("Enter the base radios: ");
    scanf("%lf", &area);
    
    volume = (M_PI * pow(area,2) * height)/2;
    
    printf("Volume: %.2lf\n", volume);

    return 0;
}
