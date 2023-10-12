/* https://ru.wikipedia.org/wiki/%D0%9F%D0%B0%D1%80%D0%B0%D0%B1%D0%BE%D0%BB%D0%BE%D0%B8%D0%B4*/

#include <stdio.h>
#include <math.h>

/*int main()
{
    double height, a, volume; 
    
    printf("Enter the height: ");
    scanf("%lf", &height);
    
    printf("Enter the base radios: ");
    scanf("%lf", &a);
    
    volume = (M_PI * pow(a,2) * height)/2;
    
    printf("Volume: %.2lf\n", volume);

    return 0;
}*/

double calculate_final_value(int a, int b, int c)
{
    double x1, x2, l, h, v, r, total;
    
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
    
    return v; 
}

int main()
{
    int a, b, c; 
    
    printf("a: ");
    scanf("%d", &a);
    printf("b: ");
    scanf("%d", &b);
    printf("c: ");
    scanf("%d", &c);
    
    
    double final_value = calculate_final_value(a, b, c);
    
    printf("%.2f", final_value);
    
    return 0;
    
}

