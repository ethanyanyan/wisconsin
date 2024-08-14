////////////////////////////////////////////////////////////////////////////////
// Main File:        send_signal.c
// This File:        send_signal.c
// Other Files:      my_c_signal_handler.c, my_div0_handler.c
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
// 26 Apr 4:10pm - Start reading assignment details and requirements
// 26 Apr 4:25pm - Complete send_signal.c
//
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
#include <signal.h>
#include <string.h>
#include <stdio.h>

int main(int argc, char *argv[]) {
	// Check if 3 arguments else print usage
	if (argc != 3) {
        printf("Usage: %s <signal type> <pid>\n", argv[0]);
        return 1;
    }
   
	int pid = atoi(argv[2]);
	// Check if valid pid input
	if (pid <= 0) {
        fprintf(stderr, "Invalid PID: %s\n", argv[2]);
        return 1;
    }

	int sig;
    if (strcmp(argv[1], "-i") == 0) { // Interrupt SIG
        sig = SIGINT;
    } else if (strcmp(argv[1], "-u") == 0) { // Update count SIG
        sig = SIGUSR1;
    } else { // Default case for invalid signal type
        fprintf(stderr, "Invalid signal type: %s\n", argv[1]);
        fprintf(stderr, "Use -i for SIGINT or -u for SIGUSR1\n");
        return 1;
    }

    if (kill(pid, sig) == -1) {
        perror("Failed to send signal");
        return 1;
    }

	 return 0;
}

