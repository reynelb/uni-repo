#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>

#define MATRIX_SIZE 3

void fillMatrix(int matrix[MATRIX_SIZE][MATRIX_SIZE], int range);
void printMatrix(int matrix[MATRIX_SIZE][MATRIX_SIZE]);

int main(int argc, char* argv[]) {
    int rank, size;
    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    int maxValue = 10;
    int matrixA[MATRIX_SIZE][MATRIX_SIZE];
    int matrixB[MATRIX_SIZE][MATRIX_SIZE];
    int resultMatrix[MATRIX_SIZE][MATRIX_SIZE] = {0};

    if (rank == 0) {

        fillMatrix(matrixA, maxValue);
        printMatrix(matrixA);

        fillMatrix(matrixB, maxValue);
        printMatrix(matrixB);
    }

    // Broadcast matrices A and B to all processes
    MPI_Bcast(matrixA, MATRIX_SIZE * MATRIX_SIZE, MPI_INT, 0, MPI_COMM_WORLD);
    MPI_Bcast(matrixB, MATRIX_SIZE * MATRIX_SIZE, MPI_INT, 0, MPI_COMM_WORLD);

    // Each process computes a part of the result matrix
    for (int i = rank; i < MATRIX_SIZE; i += size) {
        for (int j = 0; j < MATRIX_SIZE; j++) {
            for (int k = 0; k < MATRIX_SIZE; k++) {
                resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
            }
        }
    }

    // Gather the partial results into the root process
    MPI_Gather(resultMatrix, MATRIX_SIZE * MATRIX_SIZE / size, MPI_INT, 
               resultMatrix, MATRIX_SIZE * MATRIX_SIZE / size, MPI_INT, 
               0, MPI_COMM_WORLD);

    // Print the result in the root process
    if (rank == 0) {
        printMatrix(resultMatrix);
    }

    MPI_Finalize();
    return 0;
}

void fillMatrix(int matrix[MATRIX_SIZE][MATRIX_SIZE], int range) {
    for (int row = 0; row < MATRIX_SIZE; row++) {
        for (int col = 0; col < MATRIX_SIZE; col++) {
            matrix[row][col] = rand() % range + 1;
        }
    }

void printMatrix(int matrix[MATRIX_SIZE][MATRIX_SIZE]) {
    for (int row = 0; row < MATRIX_SIZE; row++) {
        for (int col = 0; col < MATRIX_SIZE; col++) {
            printf("%d ", matrix[row][col]);
        }
        printf("\n");
    }
    printf("\n");
}
