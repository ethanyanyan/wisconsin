////////////////////////////////////////////////////////////////////////////////
// Main File:        tests folder (test101.c, test102.c, test103.c, test104.c,
//                                 test105.5, test110.c, test121.c, test122.c,
//                                 test123.c, test201.c, test202.c, test211.c,
//                                 test212_wo_coal.c, test213.c,
//                                 test214_wo_coal.c, test301.c, test302.c,
//                                 test303.c, test401.c, test402.c)
// This File:        p3Heap.c
// Other Files:      p3Heap.h, p3Heap.o, libheap.so, Makefile, tests/Makefile
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
// Feb 26th 9:43am - Started work on p3a, with rough initial draft for balloc
// Feb 26th 9:22pm - Ran into seg fault, then stuck in inifinite loop
// Feb 27th 2:02pm - Debug infinite loop and rework balloc function
//   This included using gdb to debug and single out errors, error found
//   was adding sizeof(blockHeader) to block_size_needed after calculating
//   padding instead of before resulting code not moving to next blockHeader
// Feb 27th 8:42pm - Complete balloc function
// Mar 3rd 1:39pm - Started work on p3b, starting on bfree() 
// Mar 3rd 6:08pm - Completed bfree() passing test201 but failing test202
// Mar 3rd 6:50pm - Debugged using GDB and found error in balloc() fixed code
//	 in balloc and resubmit for p3a and continue working on p3b
// Mar 3rd 6:58pm - Seems like code is now passing all test! Will have to 
//	 implement more tests and checks to check code
// Mar 5th 10:07am - Implement 8 more custom tests, run make memcheck, to
//	 ensure code for p3Heap.c is working as expected.
///////////////////////// OTHER SOURCES OF HELP ////////////////////////////// 
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
// Date:   Name (email):   Helped with: (brief description)
// ---------------------------------------------------------------------------
// 
//////////////////////////// 80 columns wide ///////////////////////////////////

/////////////////////////////////////////////////////////////////////////////
//
// Copyright 2020-2024 Deb Deppeler based on work by Jim Skrentny
// Posting or sharing this file is prohibited, including any changes.
// Used by permission SPRING 2024, CS354-deppeler
//
/////////////////////////////////////////////////////////////////////////////

#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <string.h>
#include "p3Heap.h"
#include <stdbool.h>

/*
 * This structure serves as the header for each allocated and free block.
 * It also serves as the footer for each free block.
 */
typedef struct blockHeader {           

    /*
     * 1) The size of each heap block must be a multiple of 8
     * 2) heap blocks have blockHeaders that contain size and status bits
     * 3) free heap block contain a footer, but we can use the blockHeader 
     *.
     * All heap blocks have a blockHeader with size and status
     * Free heap blocks have a blockHeader as its footer with size only
     *
     * Status is stored using the two least significant bits.
     *   Bit0 => least significant bit, last bit
     *   Bit0 == 0 => free block
     *   Bit0 == 1 => allocated block
     *
     *   Bit1 => second last bit 
     *   Bit1 == 0 => previous block is free
     *   Bit1 == 1 => previous block is allocated
     * 
     * Start Heap: 
     *  The blockHeader for the first block of the heap is after skip 4 bytes.
     *  This ensures alignment requirements can be met.
     * 
     * End Mark: 
     *  The end of the available memory is indicated using a size_status of 1.
     * 
     * Examples:
     * 
     * 1. Allocated block of size 24 bytes:
     *    Allocated Block Header:
     *      If the previous block is free      p-bit=0 size_status would be 25
     *      If the previous block is allocated p-bit=1 size_status would be 27
     * 
     * 2. Free block of size 24 bytes:
     *    Free Block Header:
     *      If the previous block is free      p-bit=0 size_status would be 24
     *      If the previous block is allocated p-bit=1 size_status would be 26
     *    Free Block Footer:
     *      size_status should be 24
     */
    int size_status;

} blockHeader;         

/* Global variable - DO NOT CHANGE NAME or TYPE. 
 * It must point to the first block in the heap and is set by init_heap()
 * i.e., the block at the lowest address.
 */
blockHeader *heap_start = NULL;     

/* Size of heap allocation padded to round to nearest page size.
 */
int alloc_size;

/*
 * Additional global variables may be added as needed below
 * TODO: add global variables needed by your function
 */



/* 
 * Function for allocating 'size' bytes of heap memory.
 * Argument size: requested size for the payload
 * Returns address of allocated block (payload) on success.
 * Returns NULL on failure.
 *
 * This function must:
 * - Check size - Return NULL if size < 1 
 * - Determine block size rounding up to a multiple of 8 
 *   and possibly adding padding as a result.
 *
 * - Use BEST-FIT PLACEMENT POLICY to chose a free block
 *
 * - If the BEST-FIT block that is found is exact size match
 *   - 1. Update all heap blocks as needed for any affected blocks
 *   - 2. Return the address of the allocated block payload
 *
 * - If the BEST-FIT block that is found is large enough to split 
 *   - 1. SPLIT the free block into two valid heap blocks:
 *         1. an allocated block
 *         2. a free block
 *         NOTE: both blocks must meet heap block requirements 
 *       - Update all heap block header(s) and footer(s) 
 *              as needed for any affected blocks.
 *   - 2. Return the address of the allocated block payload
 *
 *   Return if NULL unable to find and allocate block for required size
 *
 * Note: payload address that is returned is NOT the address of the
 *       block header.  It is the address of the start of the 
 *       available memory for the requesterr.
 *
 * Tips: Be careful with pointer arithmetic and scale factors.
 */
void* balloc(int size) {     
    //TODO: Your code goes in here.
	if (size < 1) {
		return NULL;
	}

	int initial_size = size + sizeof(blockHeader);
	int overflow = initial_size % 8;
	int padding = (8 - overflow) % 8;
	int block_size_needed = initial_size + padding;

	// loop until end mark
	blockHeader *curr_block = heap_start;
	blockHeader* best_fit_block = NULL;
	while (curr_block->size_status != 1) {
		int curr_block_size = curr_block->size_status & ~0x07; // Clear the lower 3 bits
		bool a_bit = curr_block->size_status & 0x01;
		
		if (!a_bit) { // Current block is free
			if (curr_block_size == block_size_needed) { // Found exact fit
				best_fit_block = curr_block;
				break;
			} else if (curr_block_size > block_size_needed) {
				if (best_fit_block == NULL || best_fit_block->size_status > curr_block_size) {
					best_fit_block = curr_block;
				}
			}
		}

		// Proceed to next block
		curr_block = (blockHeader *)((char *)curr_block + curr_block_size);
	}
	
	if (best_fit_block == NULL) {
        return NULL; // No suitable block found
    }
	
	int remainder_size = (best_fit_block->size_status & ~0x07) - block_size_needed;
	if (remainder_size >= (int)(sizeof(blockHeader) + 4)) { // Enough space for splitting
		blockHeader* next_block = (blockHeader*)((char*)best_fit_block + block_size_needed);
		if (next_block->size_status != 1) { // Check if end marker
			// Set next block size status and p-bit to 1
			next_block->size_status = remainder_size | 0x02; // Free but prev block allocated
		}
		// Set footer for next_block
		int next_block_size = next_block->size_status & ~0x07;
		blockHeader* next_block_footer = (blockHeader*)((char*)next_block + next_block_size - sizeof(blockHeader));
		next_block_footer->size_status = next_block_size;
		// Set current best fit block size status and a-bit to 1
		if ((best_fit_block->size_status & 0x02) == 0x02) { // check if p-bit was 2 originally
			best_fit_block->size_status = block_size_needed | 0x03;
		} else {
			best_fit_block->size_status = block_size_needed | 0x01;
		}
		// Return address of payload
		return (void*)((char*)best_fit_block + sizeof(blockHeader));
	} else { // Not enough space to split
		// Update next block p-bit to 1
		blockHeader* next_block = (blockHeader*)((char*)best_fit_block + (best_fit_block->size_status & ~0x07));
        if (next_block->size_status != 1) { // Check if end marker
            // Set next block size status and p-bit to 1
			next_block->size_status = next_block->size_status | 0x02; // Prev block allocated
        }
		// Set current best fit block a-bit to 1
		best_fit_block->size_status = best_fit_block->size_status | 0x01;
        // Return address of payload
		return (void*)((char*)best_fit_block + sizeof(blockHeader));
	}		 
	
    return NULL;
} 

/* 
 * Function for freeing up a previously allocated block.
 * Argument ptr: address of the block to be freed up.
 * Returns 0 on success.
 * Returns -1 on failure.
 * This function should:
 * - Return -1 if ptr is NULL.
 * - Return -1 if ptr is not a multiple of 8.
 * - Return -1 if ptr is outside of the heap space.
 * - Return -1 if ptr block is already freed.
 * - Update header(s) and footer as needed.
 *
 * If free results in two or more adjacent free blocks,
 * they will be immediately coalesced into one larger free block.
 * so free blocks require a footer (blockHeader works) to store the size
 *
 * TIP: work on getting immediate coalescing to work after your code 
 *      can pass the tests in partA and partB of tests/ directory.
 *      Submit code that passes partA and partB to Canvas before continuing.
 */                    
int bfree(void *ptr) {    
    //TODO: Your code goes in here.
	if (ptr == NULL) {
		return -1;
	}
	
	if (((int)ptr % 8) != 0) {
		return -1;
	}

	blockHeader* header = (blockHeader*)((char*)ptr - sizeof(blockHeader));
	if ((void*)header < (void*)heap_start || (void*)header >= ((void*)heap_start + alloc_size)) {
		return -1;
	}
	
	if ((header->size_status & 0x01) == 0) { 
		return -1;
	}

	// Mark the curr block as free
	header->size_status &= ~0x01;

	// check if need to coalesce with next block
	int current_block_size = header->size_status & ~0x07;
	blockHeader* next_block_header = (blockHeader*)((char*)header + current_block_size);
	
	// check next_block is not end mark and if next_block is not allocated where a-bit is 0, coalesce
	if (next_block_header->size_status != 1 && (next_block_header->size_status & 0x01) == 0) {
		current_block_size += (next_block_header->size_status & ~0x07);
		header->size_status = current_block_size | (header->size_status & 0x02); // preserve p-bit
	}	
	// Set footer of new coalesced block
	blockHeader* new_footer = (blockHeader*)((char*)header + current_block_size - sizeof(blockHeader));	
	new_footer->size_status = current_block_size;

	// Check if prev_block is free or allocated looking at p-bit
	if ((header->size_status & 0x02) == 0) { // prev_block is allocated
		// get footer
		blockHeader* prev_block_footer = (blockHeader*)((char*)header - sizeof(blockHeader));
		int prev_block_size = prev_block_footer->size_status & ~0x07; // get size without a/p bits
		// Move to check if prev_block is allocated or free
		blockHeader* prev_block_header = (blockHeader*)((char*)header - prev_block_size);	
		if ((prev_block_header->size_status & 0x01) == 0) { // prev_block is free
			// Add curr_block size since prev block is free
			int new_size = current_block_size + (prev_block_header->size_status & ~0x07);
			current_block_size += (prev_block_header->size_status & ~0x07);
			prev_block_header->size_status = new_size | (prev_block_header->size_status & 0x02);
			// Set footer of new coalesced block
			blockHeader* prev_block_new_footer = (blockHeader*)((char*)prev_block_header + new_size - sizeof(blockHeader));
			prev_block_new_footer->size_status = new_size;
		} // else don't do anything since prev_block is allocated
		header = prev_block_header; // Adjust the header pointer to point to the start of the coalesced block
	} // if p-bit is 1, means prev_block allocated, no need check
	
	// Set the next block p-bit to 0 
    blockHeader* new_next_block_header = (blockHeader*)((char*)header + current_block_size);
	new_next_block_header->size_status = new_next_block_header->size_status & ~0x02;
	return 0;
} 


/* 
 * Initializes the memory allocator.
 * Called ONLY once by a program.
 * Argument sizeOfRegion: the size of the heap space to be allocated.
 * Returns 0 on success.
 * Returns -1 on failure.
 */                    
int init_heap(int sizeOfRegion) {    

    static int allocated_once = 0; //prevent multiple myInit calls

    int   pagesize; // page size
    int   padsize;  // size of padding when heap size not a multiple of page size
    void* mmap_ptr; // pointer to memory mapped area
    int   fd;

    blockHeader* end_mark;

    if (0 != allocated_once) {
        fprintf(stderr, 
                "Error:mem.c: InitHeap has allocated space during a previous call\n");
        return -1;
    }

    if (sizeOfRegion <= 0) {
        fprintf(stderr, "Error:mem.c: Requested block size is not positive\n");
        return -1;
    }

    // Get the pagesize from O.S. 
    pagesize = getpagesize();

    // Calculate padsize as the padding required to round up sizeOfRegion 
    // to a multiple of pagesize
    padsize = sizeOfRegion % pagesize;
    padsize = (pagesize - padsize) % pagesize;

    alloc_size = sizeOfRegion + padsize;

    // Using mmap to allocate memory
    fd = open("/dev/zero", O_RDWR);
    if (-1 == fd) {
        fprintf(stderr, "Error:mem.c: Cannot open /dev/zero\n");
        return -1;
    }
    mmap_ptr = mmap(NULL, alloc_size, PROT_READ | PROT_WRITE, MAP_PRIVATE, fd, 0);
    if (MAP_FAILED == mmap_ptr) {
        fprintf(stderr, "Error:mem.c: mmap cannot allocate space\n");
        allocated_once = 0;
        return -1;
    }

    allocated_once = 1;

    // for double word alignment and end mark
    alloc_size -= 8;

    // Initially there is only one big free block in the heap.
    // Skip first 4 bytes for double word alignment requirement.
    heap_start = (blockHeader*) mmap_ptr + 1;

    // Set the end mark
    end_mark = (blockHeader*)((void*)heap_start + alloc_size);
    end_mark->size_status = 1;

    // Set size in header
    heap_start->size_status = alloc_size;

    // Set p-bit as allocated in header
    // note a-bit left at 0 for free
    heap_start->size_status += 2;

    // Set the footer
    blockHeader *footer = (blockHeader*) ((void*)heap_start + alloc_size - 4);
    footer->size_status = alloc_size;

    return 0;
} 

/* STUDENTS MAY EDIT THIS FUNCTION, but do not change function header.
 * TIP: review this implementation to see one way to traverse through
 *      the blocks in the heap.
 *
 * Can be used for DEBUGGING to help you visualize your heap structure.
 * It traverses heap blocks and prints info about each block found.
 * 
 * Prints out a list of all the blocks including this information:
 * No.      : serial number of the block 
 * Status   : free/used (allocated)
 * Prev     : status of previous block free/used (allocated)
 * t_Begin  : address of the first byte in the block (where the header starts) 
 * t_End    : address of the last byte in the block 
 * t_Size   : size of the block as stored in the block header
 */                     
void disp_heap() {     

    int    counter;
    char   status[6];
    char   p_status[6];
    char * t_begin = NULL;
    char * t_end   = NULL;
    int    t_size;

    blockHeader *current = heap_start;
    counter = 1;

    int used_size =  0;
    int free_size =  0;
    int is_used   = -1;

    fprintf(stdout, 
            "********************************** HEAP: Block List ****************************\n");
    fprintf(stdout, "No.\tStatus\tPrev\tt_Begin\t\tt_End\t\tt_Size\n");
    fprintf(stdout, 
            "--------------------------------------------------------------------------------\n");

    while (current->size_status != 1) {
        t_begin = (char*)current;
        t_size = current->size_status;

        if (t_size & 1) {
            // LSB = 1 => used block
            strcpy(status, "alloc");
            is_used = 1;
            t_size = t_size - 1;
        } else {
            strcpy(status, "FREE ");
            is_used = 0;
        }

        if (t_size & 2) {
            strcpy(p_status, "alloc");
            t_size = t_size - 2;
        } else {
            strcpy(p_status, "FREE ");
        }

        if (is_used) 
            used_size += t_size;
        else 
            free_size += t_size;

        t_end = t_begin + t_size - 1;

        fprintf(stdout, "%d\t%s\t%s\t0x%08lx\t0x%08lx\t%4i\n", counter, status, 
                p_status, (unsigned long int)t_begin, (unsigned long int)t_end, t_size);

        current = (blockHeader*)((char*)current + t_size);
        counter = counter + 1;
    }

    fprintf(stdout, 
            "--------------------------------------------------------------------------------\n");
    fprintf(stdout, 
            "********************************************************************************\n");
    fprintf(stdout, "Total used size = %4d\n", used_size);
    fprintf(stdout, "Total free size = %4d\n", free_size);
    fprintf(stdout, "Total size      = %4d\n", used_size + free_size);
    fprintf(stdout, 
            "********************************************************************************\n");
    fflush(stdout);

    return;  
} 


//		p3Heap.c (SP24)                     
                                       
