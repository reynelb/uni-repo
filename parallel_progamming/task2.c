#include <stdio.h>
#include <stdlib.h>

#define MATRIX_SIZE 3

void fillMatrix(int matrix[MATRIX_SIZE][MATRIX_SIZE], int range);

void printMatrix(int matrix[MATRIX_SIZE][MATRIX_SIZE]);

int main() {
    
    int maxValue;
    printf("Enter a maximum value: ");
    scanf("%d", &maxValue);
    
    int matrixA[MATRIX_SIZE][MATRIX_SIZE];
    int matrixB[MATRIX_SIZE][MATRIX_SIZE];
    int resultMatrix[MATRIX_SIZE][MATRIX_SIZE] = {0}; 


    fillMatrix(matrixA, maxValue);
    printMatrix(matrixA);


    fillMatrix(matrixB, maxValue);
    printMatrix(matrixB);

    // Calculate the result of matrix multiplication
    for (int k = 0; k < MATRIX_SIZE; k++) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                resultMatrix[i][k] += matrixA[i][j] * matrixB[j][k];
            }
        }
    }

    printMatrix(resultMatrix);

    return 0;
}

void fillMatrix(int matrix[MATRIX_SIZE][MATRIX_SIZE], int range) {
    for (int row = 0; row < MATRIX_SIZE; row++) {
        for (int col = 0; col < MATRIX_SIZE; col++) {
            matrix[row][col] = rand() % range + 1;
        }
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
