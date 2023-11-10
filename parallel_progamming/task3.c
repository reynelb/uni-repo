#include <stdio.h>
#include <math.h>

//Hacer una comparacion con esta y la forma de standart de la libreria 

// Function to calculate factorial
//long long factorial(int n);

// Function to compute sine using Taylor series
double sineTaylor(double x, int terms);

// Function to compute cosine using Taylor series
double cosineTaylor(double x, int terms);

int main() {
    double x;
    int terms;  

    printf("Enter the value of x (in radians): ");
    scanf("%lf", &x);
    printf("Enter the number of terms for Taylor series: ");
    scanf("%d", &terms);

    double sineValue = sineTaylor(x, terms);
    double cosineValue = cosineTaylor(x, terms);

    //Sinuses comparision 
    printf("\nTaylor's sin(%lf) = %lf\n", x, sineValue);
    printf("Sdtlib's sin(%lf) = %lf\n", x, sin(x));
    printf("Difference = %e\n", sin(x) - sineValue);
    //Cosinuses comparision
    printf("\nTaylor's cos(%lf) = %lf\n", x, cosineValue);
    printf("Sdtlib's cos(%lf) = %lf\n", x, cos(x));
    printf("Difference = %e\n", cos(x) - cosineValue);

    return 0;
}

// long long factorial(int n) {
//     long long fact = 1;
//     for (int i = 1; i <= n; i++) {
//         fact *= i;
//     }
//     return fact;
// }

double sineTaylor(double x, int terms) {
    double result = 0.0;
    double term = x;
    double factorialValue = 1;

    for (int n = 1; n <= terms; n++) {
        result += term;

        // Calculate next term: term = (-term * x * x) / ((2n) * (2n + 1))
        term = (-term * x * x) / ((2 * n) * (2 * n + 1));
    }
    return result;
}

double cosineTaylor(double x, int terms) {
    double result = 1.0;
    double term = 1.0;
    double factorialValue = 1;

    for (int n = 1; n <= terms; n++) {
        // Calculate next term: term = (-term * x * x) / ((2n - 1) * 2n)
        term = (-term * x * x) / ((2 * n - 1) * (2 * n));
        
        result += term;
    }
    return result;
}