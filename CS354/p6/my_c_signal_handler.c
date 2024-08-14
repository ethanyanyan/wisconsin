////////////////////////////////////////////////////////////////////////////////
// Main File:        my_c_signal_handler.c
// This File:        my_c_signal_handler.c
// Other Files:      send_signal.c, my_div0_handler.c
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
// 26 Apr 3:10pm - visit man pages to read on time(), ctime(), 
//                 ,localtime(), sigaction(), and alarm()
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
// 26 Apr 2:58pm - Start reading assignment details and requirements
// 26 Apr 3:10pm - Read manpages necessary
// 26 Apr 4:05pm - Complete this program requirements
// 26 Apr 6:45pm - Include checks for time and ctime return values 
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
#include <unistd.h>
#include <string.h>
#include <time.h>
#include <stdio.h>


#define ALARM_INTERVAL 5
int sigusr1_count = 0;

void handle_signals(int signal) {
	time_t now;
	if (time(&now) == ((time_t)-1)) {
        fprintf(stderr, "Failed to get the current time.\n");
        exit(1); // Exit if time could not be retrieved
    }

	char *current_time = ctime(&now);
    if (current_time == NULL) {
        fprintf(stderr, "Failed to convert time to string.\n");
        exit(1); // Exit if time string could not be generated
    }
	
	if (signal == SIGALRM) { // Handle ALRM SIG, Print every 5 seconds
		printf("PID: %d CURRENT TIME: %s", getpid(), current_time);
        alarm(ALARM_INTERVAL);
	} else if (signal == SIGINT) { // Handle Interrupt SIG
        printf("\nSIGINT handled.\n");
		printf("SIGUSR1 was handled %d times. Exiting now.\n", sigusr1_count);
		exit(0);
    } else if (signal == SIGUSR1) { // Handle USR1 SIG, increment count
		sigusr1_count++;
		printf("Received SIGUSR1. Count: %d, PID: %d, \nCURRENT TIME: %s", 
			sigusr1_count, getpid(), ctime(&now));
	}
}

int main() {
	struct sigaction act;
    memset(&act, 0, sizeof(act));
	
	// Register signal handler
	act.sa_handler = handle_signals;

	// Handle SIGALRM
    if (sigaction(SIGALRM, &act, NULL) < 0) {
        perror("sigaction - SIGALRM");
        return 1;
    }
    // Handle SIGINT
    if (sigaction(SIGINT, &act, NULL) < 0) {
        perror("sigaction - SIGINT");
        return 1;
    }
    // Handle SIGUSR1
    if (sigaction(SIGUSR1, &act, NULL) < 0) {
        perror("sigaction - SIGUSR1");
        return 1;
    }

	printf("PID and time print every %d seconds.\n", ALARM_INTERVAL);
    printf("Type Ctrl-C to end the program.\n");	

	// Set the initial alarm
    alarm(ALARM_INTERVAL);

    while(1) {
        // Infinite loop doing nothing
    }
    return 0;
}

