#include <mpi.h>
#include <stdio.h>
#include <math.h>

int main(int argc, char** argv) {
    int rank, size;
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    double a = 10;
    double startInterval = 0.0;
    double endInterval = 100.0;
    int numSlices = 2000000000;
    double width = (endInterval - startInterval) / numSlices;
    double totalVolume = 0.0;
    double localVolume = 0.0;

    double start = MPI_Wtime();

    // Divide work among MPI processes
    int local_n = numSlices / size;
    double local_start = startInterval + rank * local_n * width;
    double local_end = local_start + local_n * width;

    for (int i = 0; i < local_n; i++) {
        double xValue = local_start + i * width;
        double yValue = sqrt(a * xValue * xValue);
        localVolume += M_PI * yValue * yValue * width;
    }

    // Collect results from all processes
    MPI_Reduce(&localVolume, &totalVolume, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

    double end = MPI_Wtime();

    if(rank == 0) {
        printf("Volume of the paraboloid: %.2f\n", totalVolume);
        printf("Time taken: %f seconds\n", end - start);
    }

    MPI_Finalize();
    return 0;
}
