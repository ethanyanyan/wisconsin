////////////////////////////////////////////////////////////////////////////////
// Main File:        check_board.c
// This File:        check_board.c
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
// Feb 5th 8:33am - "reset array to 0 in C" 
//   - "https://www.geeksforgeeks.org/how-to-empty-a-char-array-in-c/" 
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
// Feb 5th 7:26am - Started Reading through project details and provided 
//   code, completed checking CLA
// Feb 5th 7:52am - Completed call to get_board_size and dynamically 
//   allocate memory to 1d/2d arrays 
// Feb 5th 8:32am - Completed valid_board function
// Feb 5th 8:38am - Changed for loop to memset in valid_board function 
//   for neater, more optimized code
// Feb 5th 8:59am - Finished up rest of TODOs in the main function
// Feb 5th 9:13am - Fix to ensure to free up memory in main function after 
//   reading first line (insert free(line);)
// Feb 5th 2:30pm - Fix to ensure check value in 2d array is between 1 to size
// Feb 5th 8:47pm - Fix to check tokens are integers and not other chars
// Feb 16th 11:31am - Insert fclose(fp) before each exit in main
///////////////////////// OTHER SOURCES OF HELP ////////////////////////////// 
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
// Date:   Name (email):   Helped with: (brief description)
// ---------------------------------------------------------------------------
// 
//////////////////////////// 80 columns wide ///////////////////////////////////
// Fix compiler error
// Implement remaining functionality


///////////////////////////////////////////////////////////////////////////////
// Copyright 2021-24 Deb Deppeler
// Posting or sharing this file is prohibited, including any changes/additions.
//
// We have provided comments and structure for this program to help you get 
// started.  Later programs will not provide the same level of commenting,
// rather you will be expected to add same level of comments to your work.
// 09/20/2021 Revised to free memory allocated in get_board_size function.
// 01/24/2022 Revised to use pointers for CLAs
////////////////////////////////////////////////////////////////////////////////

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *DELIM = ",";  // commas ',' are a common delimiter character for data strings

/* TODO: implement this function
 * Returns 1 if and only if the board is in a valid Sudoku board state.
 * Otherwise returns 0.
 * 
 * A valid row or column contains only blanks or the digits 1-size, 
 * with no duplicate digits, where size is the value 1 to 9.
 * 
 * Note: p2A requires only that each row and each column are valid.
 * 
 * board: heap allocated 2D array of integers 
 * size:  number of rows and columns in the board
 */
int valid_board(int **board, int size) {
	// Set counter to track each number
	int *counter = malloc(sizeof(int) * size);
	if (counter == NULL) {
        printf("Memory allocation failed for counter.\n");
        exit(1);
    }

	// Check rows
	for (int i = 0; i < size; i ++) {
		memset(counter, 0, sizeof(int) * size); // Reset counter to all zeros before checking 1 to 9
		for (int j = 0; j < size; j++) {
			int num = *(*(board + i) + j);
			if (num == 0) {
				continue;
			} else if (num > 0 && num <= size && *(counter + num - 1) == 0) {
				(*(counter + num - 1))++;
			} else {
				free(counter);
				return 0;
			}
		}
	}

	// Check cols
	for (int i = 0; i < size; i ++) {
		memset(counter, 0, sizeof(int) * size); // Reset counter to all zeros before checking 1 to 9
		for (int j = 0; j < size; j++) {
			int num = *(*(board + j) + i);
			if (num == 0) {
                continue;
            } else if (num > 0 && num <= size && *(counter + num - 1) == 0) {
                (*(counter + num - 1))++; 
            } else {
				free(counter);
                return 0;
            } 
		}	
	}
	free(counter);
	return 1;
}    

/* COMPLETED (DO NOT EDIT):       
 * Read the first line of file to get the size of that board.
 * 
 * PRE-CONDITION #1: file exists
 * PRE-CONDITION #2: first line of file contains valid non-zero integer value
 *
 * fptr: file pointer for the board's input file
 * size: a pointer to an int to store the size
 *
 * POST-CONDITION: the integer whos address is passed in as size (int *) 
 * will now have the size (number of rows and cols) of the board being checked.
 */
void get_board_size(FILE *fptr, int *size) {      
	char *line = NULL;
	size_t len = 0;

	// 'man getline' to learn about <stdio.h> getline
	if ( getline(&line, &len, fptr) == -1 ) {
		printf("Error reading the input file.\n");
		free(line);
		exit(1);
	}

	char *size_chars = NULL;
	size_chars = strtok(line, DELIM);
	*size = atoi(size_chars);

	// free memory allocated for line 
	free(line);
	line = NULL;
}


/* TODO: COMPLETE THE MAIN FUNCTION
 * This program prints "valid" (without quotes) if the input file contains
 * a valid state of a Sudoku puzzle board wrt to rows and columns only.
 * It prints "invalid" (without quotes) if the input file is not valid.
 *
 * Usage: A single CLA that is the name of a file that contains board data.
 *
 * argc: the number of command line args (CLAs)
 * argv: the CLA strings, includes the program name
 *
 * Returns 0 if able to correctly output valid or invalid.
 * Exit with a non-zero result if unable to open and read the file given.
 */
int main( int argc, char **argv ) {              

	// TODO: Check if number of command-line arguments is correct.
  	if (argc != 2) {
		printf("Usage: ./check_board <input_filename>\n");
		exit(1);
	}
	// Open the file 
	FILE *fp = fopen(*(argv + 1), "r");
	if (fp == NULL) {
		printf("Can't open file for reading.\n");
		exit(1);
	}

	// will store the board's size, number of rows and columns
	int size;

	// TODO: Call get_board_size to read first line of file as the board size.
	get_board_size(fp, &size);	
	// TODO: Dynamically allocate a 2D array for given board size.
	// You must dyamically create a 1D array of pointers to other 1D arrays of ints
	int **array2d = malloc(sizeof(int *) * size);
	if (array2d == NULL) {
		printf("Memory allocation failed for array2d.\n");
		fclose(fp);
		exit(1);
	}
	for (int i = 0; i < size; i++) {
		int *array1d = malloc(sizeof(int) * size);
		if (array1d == NULL) {
			printf("Memory allocation failed for row %d.\n", i);
			while (--i >= 0) {
				free(*(array2d + i)); // Free each row allocated so far
			}
			free(array2d); // Free 2d array space
			fclose(fp);
			exit(1);
		}
		*(array2d+i) = array1d;
	}
	// Read the remaining lines.
	// Tokenize each line and store the values in your 2D array.
	char *line = NULL;
	size_t len = 0;
	char *token = NULL;
	for (int i = 0; i < size; i++) {

		// read the line
		if (getline(&line, &len, fp) == -1) {
			printf("Error while reading line %i of the file.\n", i+2);
			// Free all memory used earlier
			for (int k = 0; k < size; k++) {
				free(*(array2d + k)); // Free each 1d row
			}
			free(array2d);
			free(line); 
			fclose(fp);
			exit(1);
		}

		token = strtok(line, DELIM);
		for (int j = 0; j < size; j++) {
			// TODO: Complete the line of code below
			// to initialize elements of your 2D array.
			long num = 0;
			char *endptr = NULL;
			if (token == NULL) {
				printf("invalid\n");
				for (int k = 0; k < size; k++) {
					free(*(array2d + k)); // Free each 1d row
				}
	            free(array2d);
				free(line);
				fclose(fp);
				exit(1);
			}

			num = strtol(token, &endptr, 10);
			if (*endptr != '\0' && *endptr != '\n') {
                printf("invalid\n");
                for (int k = 0; k < size; k++) {
                    free(*(array2d + k));
                }
                free(array2d);
                free(line);
                fclose(fp);
                exit(1);
            }
			*(*(array2d + i) + j) = (int)num; 
			token = strtok(NULL, DELIM);
		}
	}

	free(line);

	// TODO: Call valid_board and print the appropriate
	//       output depending on the function's return value.
	int valid = valid_board(array2d, size);
	if (valid == 1) {
		printf("valid\n");
	} else {
		printf("invalid\n");
	}
	// TODO: Free dynamically allocated memory.
	for (int i = 0; i < size; i++) {
		free(*(array2d + i)); // Free each 1d row
    }
	free(array2d);
	//Close the file.
	if (fclose(fp) != 0) {
		printf("Error while closing the file.\n");
		exit(1);
	}
} 
