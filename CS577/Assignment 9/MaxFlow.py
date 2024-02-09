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
        n, m = map(int, input().strip().split())
        graph = [[0] * n for _ in range(n)]

        for _ in range(m):
            source, dest, capacity = map(int, input().strip().split())
            if graph[source - 1][dest - 1] == 0:
                graph[source - 1][dest - 1] = capacity
            else:
                graph[source - 1][dest - 1] = graph[source - 1][dest - 1] + capacity

        g = Graph(graph)
        source = 0  # Assuming source is vertex number 1
        sink = n - 1  # Assuming sink is the last vertex number n

        print(g.ford_fulkerson(source, sink))

if __name__ == "__main__":
    main()
