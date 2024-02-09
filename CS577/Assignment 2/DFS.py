def recursive_dfs(dict, visited, currNode):
    if currNode in visited:
        return
    if currNode not in visited:
        visited.append(currNode)

    for neighbor in dict[currNode]:
        if neighbor not in visited:
            recursive_dfs(dict, visited, neighbor)
        else:
            continue

def iterative_dfs(dict, visited, currNode):
    if currNode in visited:
        return
    stack = [currNode]

    while stack:
        node = stack.pop()
        if node not in visited:
            visited.append(node)

        # Push unvisited neighbors onto the stack
        for neighbor in reversed(dict[node]):
            if neighbor not in visited:
                stack.append(neighbor)

def print_instance():
    num_nodes = int(input().strip())

    # Create adjacency list graph
    graph = {}
    first_node = None

    for i in range(num_nodes):
        line = input().strip().split()
        key = line[0]
        values = line[1:]

        if not first_node:
            first_node = key

        if key not in graph:
            graph[key] = []

        graph[key].extend(values)

    # Perform DFS
    visited = []
    for node in graph:
        recursive_dfs(graph, visited, node)

    # Print DFS result string
    print_string = " ".join(visited)
    print(print_string)

num_instances = int(input().strip())

count_instances = 0
while count_instances < num_instances:
    print_instance()
    count_instances += 1