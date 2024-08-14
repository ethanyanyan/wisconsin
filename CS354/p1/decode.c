////////////////////////////////////////////////////////////////////////////////
// Main File:        decode.c
// This File:        decode.c
// Other Files:      cipher.txt
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
// Jan 29th 7am - Started reading through project details and code provided 
// Jan 29th 8:33pm - Submitted first submission for p1
// Feb 5th 10:45am - Updated Commit Log and submitted second submission for p1
// 
// 
///////////////////////// OTHER SOURCES OF HELP ////////////////////////////// 
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
// Date:   Name (email):   Helped with: (brief description)
// ---------------------------------------------------------------------------
// 
//////////////////////////// 80 columns wide ///////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
// Copyright 2019 Jim Skrentny
// Posting or sharing this file is prohibited, including any changes/additions.
// Used by permission, Deb Deppeler, SPRING 2024
////////////////////////////////////////////////////////////////////////////////

/*
 * This program decodes cipher texts using the Caesar cipher technique. 
 * Left shifts are used to encode the message and 
 * right shifts are used to decode the ciphertext.   
 *
 * The ciphertext is read from a file named "cipher.txt" 
 * that must be present in the current working directory.    
 * 
 * The number of right shifts required to decode the message is calculated
 * using a (not so) secret key, the CS login of the intended recipient.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char * read_cipher_file(); 
char * get_login_key();  
char * decode(char *cipher, char *key); 
int    calculate_shifts(char *key);  

int main(int argc, char *argv[]) {      

    // Read cipher-text from cipher file
    char *cipher = read_cipher_file();
    printf("Your cipher text:\n%s\n", cipher);

    // Prompt user for CS login
    // This is used to determine the shifts needed for decoding
    char * key = get_login_key();

    // Decode the ciphertext using the Caesar cipher technique
    char * plaintext = decode(cipher, key);

    // Print out the plain-text
    printf("Plaintext:\n%s\n", plaintext);

    return 0;
}     

/*
 * Reads the first line of the file named "ciphertext.txt" 
 * Returns the first line as a string.
 */
char * read_cipher_file() {     

    // Open the ciphertext file for reading
    FILE *fp;
    fp = fopen("cipher.txt", "r");
    if (fp == NULL) {
        fprintf(stderr, "Cannot open file for reading.\n");
        exit(1);
    }

    char *str = malloc(1000);
    // Read the first and only line in the file as ciphertext
    if (fgets(str, 1000, fp) == NULL) {
        fprintf(stderr, "Error reading cipher text file.\n");
        exit(1);
    }

    int len = strlen(str);
    // Replace the trailing newline with a null character
    if (str[len - 1] == '\n') str[len - 1] = '\0';

    return str;
}        

/*
 * Prompt the user for their CS login and return it as a string
 */
char * get_login_key() {    

    // Prompt the user for their CS login
    char *str = malloc(50);
    printf("Your CS login: ");
    if (fgets (str, 50, stdin) == NULL) {
        fprintf(stderr, "Error reading user input.\n");
        exit(1);
    }

    // Replace the trailing newline with a null character
    int len = strlen(str);
    if (str[len - 1] == '\n') str[len - 1] = '\0';

    return str;
}            

/*
 * Decodes the cipher to plaintext.
 */
char * decode(char *cipher, char *key) {        

    // Calculate the number of shifts needed to decode the message from the key
    int shifts = calculate_shifts(key);

    // Decode by right shifting every lowercase alphabet letter in the ciphertext
    char *p = cipher;
    for ( ; *p != '\0'; ++p) {
        // Skip decoding if not a lowercase letter (a-z)
        if (*p < 'a' || *p > 'z') continue;

        // Offset from 'a' is the index of letter at current index
        // ASCII number for 'a' is 97
        int offset_from_a = *p - 'a';

        // The lower case alphabet(a-z) is indexed from 0-25
        // We do a circular right shift by the required number of shifts
        int new_offset_from_a = (offset_from_a + shifts) % 26;

        // Convert the character at current "new" index for current character
        // back to the ASCII code for the decoded letter
        *p = new_offset_from_a + 'a';
    }

    return cipher; // which is now decoded into plaintext
}        

/*
 * Calculate and return the number of shifts (1-25) needed in the Caesar cipher
 * by taking the bit-wise XOR of every character in the CS login key string
 */
int calculate_shifts(char * key) {  

    char *p = key;
    int shifts = 0;

    for ( ; *p != '\0'; ++p) shifts = shifts ^ *p; 
    shifts = abs(shifts % 26);
    if (shifts == 0) shifts = 1;
    return shifts;
}    

                       

