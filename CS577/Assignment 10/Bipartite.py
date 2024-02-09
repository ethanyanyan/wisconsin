from collections import defaultdict, deque

class Graph:
    def __init__(self, graph):
        self.graph = graph
        self.ROW = len(graph)

    # Using BFS as a searching algorithm
    def bfs(self, s, t, parent):
        visited = [False] * (self.ROW)
        queue = deque()

        queue.append(s)
        visited[s] = True

        while queue:
            u = queue.popleft()

            for ind, val in enumerate(self.graph[u]):
                if visited[ind] == False and val > 0:
                    queue.append(ind)
                    visited[ind] = True
                    parent[ind] = u

        return True if visited[t] else False

    # Applying fordfulkerson algorithm
    def ford_fulkerson(self, source, sink):
        parent = [-1] * (self.ROW)
        max_flow = 0

        while self.bfs(source, sink, parent):
            path_flow = float("Inf")
            s = sink

            while(s != source):
                path_flow = min(path_flow, self.graph[parent[s]][s])
                s = parent[s]

            max_flow += path_flow

            v = sink
            while(v != source):
                u = parent[v]
                self.graph[u][v] -= path_flow
                self.graph[v][u] += path_flow
                v = parent[v]

        return max_flow


def main():
    number_of_instances = int(input().strip())
    for _ in range(number_of_instances):
        setA, setB, numEdges = map(int, input().strip().split())
        totalNumNodes = setA + setB + 2
        graph = [[0] * (totalNumNodes) for _ in range(totalNumNodes)]
        for i in range(setA):
            graph[0][i+1] = 1

        for i in range(setB):
            graph[i+setA+1][totalNumNodes-1] = 1

        for i in range(numEdges):
            nodeA, nodeB = map(int, input().strip().split())
            graph[nodeA][setA+nodeB] += 1
        
        g = Graph(graph)
        source = 0  # Assuming source is vertex number 1
        sink = totalNumNodes - 1  # Assuming sink is the last vertex number totalNumNodes

        max_flow = g.ford_fulkerson(source, sink)
        print(f"{max_flow} {'Y' if max_flow == max(setA,setB) else 'N'}")

if __name__ == "__main__":
    main()
