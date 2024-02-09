import heapq
import numpy as np



def get_manhattan_distance(from_state, to_state=[1, 2, 3, 4, 5, 6, 7, 0, 0]):
    """
    TODO: implement this function. This function will not be tested directly by the grader. 

    INPUT: 
        Two states (if second state is omitted then it is assumed that it is the goal state)

    RETURNS:
        A scalar that is the sum of Manhattan distances for all tiles.
    """
    distance = 0
    for i in range(len(from_state)):
        number = from_state[i]
        start_index = i
        end_index = number-1

        if start_index != end_index and number != 0:
            startX = start_index%3
            startY = start_index//3
            endX = end_index%3
            endY = end_index//3
            partial_dist = abs(startX-endX) + abs(startY-endY)
        else:
            partial_dist = 0
        distance += partial_dist

    return distance



def print_succ(state):
    """
    TODO: This is based on get_succ function below, so should implement that function.

    INPUT: 
        A state (list of length 9)

    WHAT IT DOES:
        Prints the list of all the valid successors in the puzzle. 
    """
    succ_states = get_succ(state)

    for succ_state in succ_states:
        print(succ_state, "h={}".format(get_manhattan_distance(succ_state)))


def get_succ(state):
    """
    TODO: implement this function.

    INPUT: 
        A state (list of length 9)

    RETURNS:
        A list of all the valid successors in the puzzle (don't forget to sort the result as done below). 
    """
    succ_states = []
    for i in range(len(state)):
        newState = state[:]
        if state[i] != 0:
            index_x = i%3
            index_y = i//3
            rightIndex = 10
            bottomIndex = 10
            leftIndex = -1
            topIndex = -1

            if index_x + 1 < 3:
                rightIndex = (index_y*3)+ (index_x+1)
            if index_x - 1 > -1:
                leftIndex = (index_y*3)+ (index_x-1)
            if index_y + 1 < 3:
                bottomIndex = ((index_y+1)*3)+ (index_x)
            if index_y - 1 > -1:
                topIndex = ((index_y-1)*3)+ (index_x)
            
            if rightIndex < 9 and state[i+1] == 0:
                temp = newState[i]
                newState[i] = newState[i+1]
                newState[i+1] = temp
                succ_states.append(newState)
                newState = state[:]
            if bottomIndex < 9 and state[i+3] == 0:
                temp = newState[i]
                newState[i] = newState[i+3]
                newState[i+3] = temp
                succ_states.append(newState)
                newState = state[:]
            if leftIndex > -1 and state[i-1] == 0:
                temp = newState[i]
                newState[i] = newState[i-1]
                newState[i-1] = temp
                succ_states.append(newState)
                newState = state[:]
            if topIndex > -1 and state[i-3] == 0:
                temp = newState[i]
                newState[i] = newState[i-3]
                newState[i-3] = temp
                succ_states.append(newState)
                newState = state[:]
    return sorted(succ_states)


def solve(state, goal_state=[1, 2, 3, 4, 5, 6, 7, 0, 0]):
    """
    Solves the 8-puzzle problem using the A* algorithm.

    INPUT: 
        An initial state (list of length 9)

    OUTPUT:
        Prints a path of configurations from initial state to goal state along with
        the cost of each move, the heuristic estimate of the remaining cost, and
        the maximum size of the priority queue.
    """
    # Initialize the open and closed lists
    open_list = []
    closed_list = []
    visitedStates = []

    # Format: heapq.heappush(pq ,(cost, state, (g, h, parent_index)))
    # Add the initial state to the open list
    g = 0
    h = get_manhattan_distance(state)
    f = g + h
    heapq.heappush(open_list, (f, state, (g, h, -1)))

    # Initialize the maximum size of the open list
    max_queue_size = 1

    while (len(open_list) != 0):
        min_cost_state = heapq.heappop(open_list)
        visitedStates.append(min_cost_state[1])
        closed_list.append(min_cost_state)
        # Generate the successors of the current state
        succ_states = get_succ(min_cost_state[1])


        if (min_cost_state[1] == goal_state):
            # Print the path of configurations and the cost of each move
            path = []
            curr_state = min_cost_state
            while curr_state[2][2] != -1:
                path.append(curr_state)
                curr_state = closed_list[curr_state[2][2]]
            path.append(curr_state)
            path.reverse()
            for i in range(len(path)):
                print(path[i][1], "h=" + str(path[i][2][1]), "moves: " + str(path[i][2][0]))
            print("Max queue length:", max_queue_size)
            break


        for succ_state in succ_states:

            # Compute the cost of the path to the successor
            g = min_cost_state[2][0] + 1

            # Compute the heuristic estimate of the remaining cost
            h = get_manhattan_distance(succ_state)

            # Compute the total cost
            f = g + h

            parent_index = len(closed_list) - 1

            # Check if the successor is already in the open or closed list
            if succ_state in visitedStates:
                continue
            
            else:
                toAdd = (h+g, succ_state, (g, h, closed_list.index(min_cost_state)))
                
                heapq.heappush(open_list, toAdd)

            # Update the maximum size of the open list
            max_queue_size = max(max_queue_size, len(open_list))
            


         

if __name__ == "__main__":
    """
    Feel free to write your own test code here to exaime the correctness of your functions. 
    Note that this part will not be graded.
    """
    print_succ([3, 4, 6, 0, 0, 1, 7, 2, 5])
    print()

    print(get_manhattan_distance([1, 2, 3, 4, 5, 6, 7, 0, 0], [1, 2, 3, 4, 5, 6, 7, 0, 0]))
    print()

    solve([1, 7, 0, 6, 3, 2, 0, 4, 5])
    print()
    solve([3, 4, 6, 0, 0, 1, 7, 2, 5])
    print()
