////////////////////////////////////////////////////////////////////////////////
// Main File:        cache2Dclash.c
// This File:        cache2Dclash.c
// Other Files:      cache1D.c, cache2Dcols.c, cache2Drows.c
//					 (each with its own main functions for p4A)
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
// AI chats: save a transcript.  No need to submit, just have available 
// if asked.
/////////////////////////// COMMIT LOG  ////////////////////////////// 
//  Date and label your work sessions, include date and brief description
//  Date:   Commit Message: 
//  -------------------------------------------------------------------
// 11 Mar 2:21pm - Create this file and complete file requirements
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

#include <stdio.h>

#define ROWS 128
#define COLS 8

int arr2D[ROWS][COLS];

int main() {
    for (int iteration = 0; iteration < 100; iteration++) {
        for (int row = 0; row < ROWS; row += 64) { 
            for (int col = 0; col < COLS; col++) {
                arr2D[row][col] = iteration + row + col; 
            }
        }
    }

    return 0;
}

