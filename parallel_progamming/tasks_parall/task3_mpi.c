#include <stdio.h>
#include <math.h>
#include <mpi.h>

double sineTaylor(double x, int terms);
double cosineTaylor(double x, int terms);

int main(int argc, char* argv[]) {
    MPI_Init(&argc, &argv);

    int world_size, process_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &process_rank);
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);

   
    double x_start = 0.0; 
    double x_end = 10.0;  
    int terms = 100;      
    int num_points = 100; 

    double x_step = (x_end - x_start) / num_points;

    for (int i = 0; i < num_points; i++) {
        if (i % world_size == process_rank) {
            double x = x_start + i * x_step;
            double sineValue = sineTaylor(x, terms);
            double cosineValue = cosineTaylor(x, terms);

            MPI_Send(&x, 1, MPI_DOUBLE, 0, 0, MPI_COMM_WORLD);
            MPI_Send(&sineValue, 1, MPI_DOUBLE, 0, 1, MPI_COMM_WORLD);
            MPI_Send(&cosineValue, 1, MPI_DOUBLE, 0, 2, MPI_COMM_WORLD);
        }
    }

    if (process_rank == 0) {
        for (int i = 0; i < num_points; i++) {
            double x, sineValue, cosineValue;
            MPI_Recv(&x, 1, MPI_DOUBLE, MPI_ANY_SOURCE, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            MPI_Recv(&sineValue, 1, MPI_DOUBLE, MPI_ANY_SOURCE, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            MPI_Recv(&cosineValue, 1, MPI_DOUBLE, MPI_ANY_SOURCE, 2, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

            printf("x = %f, Taylor's sin(x) = %f, Taylor's cos(x) = %f\n", x, sineValue, cosineValue);
        }
    }

    MPI_Finalize();
    return 0;
}

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
