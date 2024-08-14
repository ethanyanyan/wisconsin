////////////////////////////////////////////////////////////////////////////////
// Main File:        my_magic_square.c
// This File:        my_magic_square.c
// Other Files:      -
// Semester:         CS 354 Lecture 001      SPRING 2024
// Grade Group:      gg 2  (See canvas.wisc.edu/groups for your gg#)
// Instructor:       deppeler
// 
// Author:           Ethan Yan
// Email:            eyyan@wisc.edu
// CS Login:         eyy
//
/////////////////////////// SEARCH LOG //////////////////////////////// 
// Online sources: do not rely web searches to solve your problems, 
// but if you do search for help on a topic, include Query and URLs here.
// IF YOU FIND CODED SOLUTIONS, IT IS ACADEMIC MISCONDUCT TO USE THEM
//                               (even for "just for reference")
// Date:   Query:                      URL:
// --------------------------------------------------------------------- 
// 
// 
// 
// 
// 
// AI chats: save a transcript.  No need to submit, just have available 
// if asked.
/////////////////////////// COMMIT LOG  ////////////////////////////// 
//  Date and label your work sessions, include date and brief description
//  Date:   Commit Message: 
//  -------------------------------------------------------------------
// Feb 11th 4:37pm - Start and complete getSize function
// Feb 11th 5:00pm - Finish part to get filename to save to 
// Feb 11th 6:09pm - Finish generateMagicSquare function 
// Feb 11th 6:52pm - Finish all functions including main 
// Feb 11th 7:13pm - Run valgrind and fix mem leaks portions
// Feb 18th 1:24pm - Handle NULL checks by doing exit(1) appropriately
// Feb 22nd 7:53am - Access argv with array arithmetic
// Feb 22nd 2:03pm - Use heap allocated array when processing input in getSize
///////////////////////// OTHER SOURCES OF HELP ////////////////////////////// 
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
// Date:   Name (email):   Helped with: (brief description)
// ---------------------------------------------------------------------------
// 
//////////////////////////// 80 columns wide ///////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
// Copyright 2020 Jim Skrentny
// Posting or sharing this file is prohibited, including any changes/additions.
// Used by permission, CS 354 Spring 2024, Deb Deppeler
////////////////////////////////////////////////////////////////////////////////

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure that represents a magic square
typedef struct {
	int size;           // dimension of the square
	int **magic_square; // pointer to heap allocated magic square
} MagicSquare;

/* 
 * Prompts the user for the magic square's size, read it,
 * check if it's an odd number >= 3 (if not, display the required
 * error message and exit)
 *
 * return the valid number
 */
int getSize() {
	char *input = malloc(10 * sizeof(char));
    if (input == NULL) {
        printf("Memory allocation for input char array failed.\n");
        exit(1);
    }

	for(int i = 0; i < 10; i++) {
        *(input + i) = 0;
    }

	int size = 0;
	printf("Enter magic square's size (odd integer >=3)\n");
	if (fgets(input, sizeof(input), stdin) == NULL) {
		printf("Error reading input.\n");
		free(input);
		exit(1);
	}

	size = atoi(input);
	if (size < 2) {
		printf("Magic square size must be >= 3.\n");
		free(input);
		exit(1);
	} else if (size % 2 == 0) {
		printf("Magic square size must be odd.\n");
		free(input);
		exit(1);
	}
	
	free(input);
	return size;   
} 

/* 
 * Makes a magic square of size n,
 * and stores it in a MagicSquare (on the heap)
 *
 * It may use the Siamese magic square algorithm 
 * or alternate from assignment 
 * or another valid algorithm that produces a magic square.
 *
 * n - the number of rows and columns
 *
 * returns a pointer to the completed MagicSquare struct.
 */
MagicSquare *generateMagicSquare(int n) {
	// Allocate memory for the 2D array
	int **array2d = malloc(sizeof(int *) * n);
	if (array2d == NULL) {
		printf("Memory allocation failed for array2d.\n");
        exit(1);
	}
	
	// Allocate each row in the 2D array
	for (int i = 0; i < n; i ++) {
		int *array1d = malloc(sizeof(int) * n);
		if (array1d == NULL) {
			for (int j = i; j >= 0; j--) {
				free(*(array2d + j));
			}
			free(array2d);
			exit(1);
		}
		
		*(array2d + i) = array1d;
	}

	// Initialize to all 0s
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			*(*(array2d+i)+j) = 0;
		}
	}

	int max_num = n * n;
	int curr_row = 0;
	int curr_col = n/2;
	for (int i = 1; i <= max_num; i++) {
		int *curr_addr = *(array2d + curr_row) + curr_col;
		*curr_addr = i;
		int next_row = curr_row - 1;
		if (next_row < 0) {
			next_row = n-1;
		}
		int next_col = curr_col + 1;
		if (next_col >= n) {
			next_col = 0;
		}

		if (*(*(array2d + next_row) + next_col) != 0) {
			curr_row++;
			if (curr_row >= n) {
				curr_row = 0;
			}
		} else {
			curr_row = next_row;
			curr_col = next_col;
		} 
	}

	MagicSquare *msquare = malloc(sizeof(MagicSquare));
    if (msquare == NULL) {
        printf("Memory allocation failed for MagicSquare struct.\n");
        for (int i = 0; i < n; i++) {
            free(*(array2d + i));
        }
        free(array2d);
        exit(1);
    }

	msquare->size = n;
    msquare->magic_square = array2d;

	return msquare;    
} 

/*   
 * Opens a new file (or overwrites the existing file)
 * and writes the magic square values to the file
 * in the specified format.
 *
 * magic_square - the magic square to write to a file
 * filename - the name of the output file
 */
void fileOutputMagicSquare(MagicSquare *magic_square, char *filename) {
	
	FILE *outputFile = fopen(filename, "w");
    if (outputFile == NULL) {
        printf("Error opening file %s for writing.\n", filename);
        return;
    }
	
	int size = magic_square->size;
	fprintf(outputFile, "%d\n", size);
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			if (j < size - 1) {
                fprintf(outputFile, "%d,", *(*(magic_square->magic_square + i) + j));
            } else {
                fprintf(outputFile, "%d", *(*(magic_square->magic_square + i) + j));	
			}
		}
		fprintf(outputFile, "\n");
	}

	fclose(outputFile);
}


/* 
 * Generates a magic square of the user specified size and
 * outputs the square to the output filename.
 * 
 * Add description of required CLAs here
 */
int main(int argc, char **argv) {
	// Check input arguments to get output filename
	if (argc != 2) {
		printf("Usage: ./my_magic_square <output_filename>\n");
		exit(1);
	}
	char *filename = *(argv+1);
	
	// Get magic square's size from user
	int size = getSize();

	// Generate the magic square by correctly interpreting 
	// the algorithm(s) in the write-up or by writing or your own.  
	// You must confirm that your program produces a 
	// Magic Sqare as described in the linked Wikipedia page.
	MagicSquare *msquare = generateMagicSquare(size);

	// Output the magic square
	fileOutputMagicSquare(msquare, filename);

	int **array2d = msquare->magic_square;
	for (int i = 0; i < size; i ++) {
		free(*(array2d + i));
	}
	free(array2d);
	free(msquare);	

	return 0;
} 

// Spring 2024


