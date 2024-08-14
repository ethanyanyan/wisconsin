////////////////////////////////////////////////////////////////////////////////
// Main File:        my_div0_handler.c
// This File:        my_div0_handler.c
// Other Files:      my_c_signal_handler.c, send_signal.c
//					 
// Semester:         CS 354 Lecture 001      SPRING 2024
// Grade Group:      gg 02  (See canvas.wisc.edu/groups for your gg#)
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
// 
// AI chats: save a transcript.  No need to submit, just have available 
// if asked.
/////////////////////////// COMMIT LOG  ////////////////////////////// 
//  Date and label your work sessions, include date and brief description
//  Date:   Commit Message: 
//  -------------------------------------------------------------------
// 26 Apr 4:25pm - Start reading assignment details and requirements
// 26 Apr 4:47pm - Complete program requirements
// 3 May 4:01pm - Add new line at start of print when SIGINT
//
// 
// 
///////////////////////// OTHER SOURCES OF HELP ////////////////////////////// 
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
// Date:   Name (email):   Helped with: (brief description)
// ---------------------------------------------------------------------------
// 
//////////////////////////// 80 columns wide ///////////////////////////////////
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <stdio.h>

int success_operations = 0;

void handle_signals(int signal) {
	if (signal == SIGFPE) { // Catch when divide by zero
		printf("Error: a division by 0 operation was attempted.\n");
		printf("Total number of operations completed successfully: %d\n", 
				success_operations);
		printf("The program will be terminated.\n");
		exit(0);
	} else if (signal == SIGINT) { // If user does Ctrl+C
		printf("\nTotal number of operations completed successfully: %d\n", 
				success_operations);
		printf("The program will be terminated.\n");
		exit(0);
	}
}


int main(int argc, char *argv[]) {
	char input[100]; // Initialize with mandatory 100 byte buffer
    int int1, int2;
	struct sigaction act;
    memset(&act, 0, sizeof(act));

	// Register signal handler
	act.sa_handler = handle_signals;

	// Handle SIGINT
    if (sigaction(SIGINT, &act, NULL) == -1) {
        perror("sigaction - SIGINT");
        return 1;
    }
    // Handle SIGFPE
    if (sigaction(SIGFPE, &act, NULL) == -1) {
        perror("sigaction - SIGFPE");
        return 1;
    }

	while(1) {
		// Get user's input for the first integer
        printf("Enter first integer: ");
        if (fgets(input, sizeof(input), stdin) == NULL) {
            perror("Error reading input");
            break;
        }
        int1 = atoi(input); // Convert string to integer

        // Get user's input for the second integer
        printf("Enter second integer: ");
        if (fgets(input, sizeof(input), stdin) == NULL) {
            perror("Error reading input");
            break;
        }
        int2 = atoi(input); // Convert string to integer

        if (int2 == 0) {
            raise(SIGFPE);  // Manually raise SIGFPE if int2 is zero
        } else {
            printf("%d / %d is %d with a remainder of %d\n", 
					int1, int2, int1 / int2, int1 % int2);
            success_operations++; // Increment success_operation count
        }
    }   

	return 0;
}

