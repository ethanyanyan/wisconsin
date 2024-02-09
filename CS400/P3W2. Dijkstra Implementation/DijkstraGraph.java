// --== CS400 File Header Information ==--
// Name: Ethan Yikai Yan
// Email: eyyan@wisc.edu
// Group and Team: BY blue
// Group TA: HAFEEZ ALI ANEES ALI
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes.  This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
    extends BaseGraph<NodeType,EdgeType>
    implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph.  The final node in this path is stored in it's node
     * field.  The total cost of this path is stored in its cost field.  And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in it's node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;
        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }
        public int compareTo(SearchNode other) {
            if( cost > other.cost ) return +1;
            if( cost < other.cost ) return -1;
            return 0;
        }
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations.  The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *         or when either start or end data do not correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        // Check if correspond to graph node
        if (!this.nodes.containsKey(start) || !this.nodes.containsKey(end)) {
            throw new NoSuchElementException("No such node");
        }
        // Initialize
        Comparator<SearchNode> costComparator = Comparator.comparingDouble(node -> node.cost);
        PriorityQueue pq = new PriorityQueue<SearchNode>(costComparator);
        Hashtable hashtable = new Hashtable<NodeType, SearchNode>();
        SearchNode startNode = new SearchNode (new Node(start), 0, null);
        pq.add(startNode);
        hashtable.put(startNode.node.data, startNode);

        while (!pq.isEmpty()) {
            
            // Look at smallest cost node
            SearchNode smallestCostNode = (SearchNode) pq.peek();
            Node currNode = smallestCostNode.node;

            // If hashtable already contains node-cost pair that is less than the smallestCodeNode
            // being evaluated, remove from PQ and move on to next smallestCostNode.
            if (hashtable.containsKey(currNode.data) && ((((SearchNode) hashtable.get(currNode.data)).cost) < smallestCostNode.cost)) {
                pq.poll();
                //System.out.println("Hashtable already has key");
                continue;
            }

            // For each neighboring node that is still unvisited, calculate the distance to that 
            // node from the starting node as the sum of the distance from the current node to the 
            // neighboring node, plus the weight of the edge connecting them.
            for (int i = 0; i < this.nodes.get(currNode.data).edgesLeaving.size(); i ++) {
                double totalCost = smallestCostNode.cost + (this.nodes.get(currNode.data).edgesLeaving.get(i).data).doubleValue();
                Node successor = this.nodes.get(currNode.data).edgesLeaving.get(i).successor;
                // Add neighbour node to hashtable if this route to neighbou node is lower than the
                // one stored in hashtable thus far
                if (hashtable.containsKey(successor.data) && ((((SearchNode) hashtable.get(successor.data)).cost) <= totalCost)) {
                    continue;
                } else {
                    SearchNode toAdd = new SearchNode(successor, totalCost, smallestCostNode);
                    pq.add(toAdd);
                    hashtable.put(successor.data, toAdd);
                }
            }

            // Remove smallestCodeNode from pq
            pq.poll();

        }

        // Check if there is a path from start to end
        if (!hashtable.containsKey(end)) {
            throw new NoSuchElementException("No path from start node to end node");
        }

        // Return a endSearchNode of type SearchNode that is linked to its predecessors to the start
        // node
        SearchNode endSearchNode = (SearchNode) hashtable.get(end);
        if (endSearchNode != null) {
            return endSearchNode;
        }
        return null;
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value.  This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path.  This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        List returnList = new LinkedList<>();
        SearchNode endSearchNode = computeShortestPath(start, end);
        // Check if there is such a path from start to end, return empty list if no path
        if (endSearchNode == null) {
            return returnList;
        }
        // Generate linked list of path if there is a path
        while (endSearchNode.node.data != start) {
            returnList.add(0, endSearchNode.node.data);
            endSearchNode = endSearchNode.predecessor;
        }
        returnList.add(0, start);

        return returnList;
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path freom the node containing the start data to the node containing the
     * end data.  This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        // endSearchNode has cost field that is the cumulative cost for the shortest path there
        SearchNode endSearchNode = computeShortestPath(start, end);
        return endSearchNode.cost;
    }

    /**
     * JUnit Test 1
     * This is a test that makes use of an example that I traced through in lecture, and will 
     * confirm that the results of my implementation match what I previously computed by hand.
     */
    @Test
    public void test1() {
        DijkstraGraph<String, Integer> graph = new DijkstraGraph<String,Integer>();
        
        // add nodes to the graph
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");
        graph.insertNode("I");
        graph.insertNode("L");
        graph.insertNode("M");
    
        // add edges to the graph
        graph.insertEdge("A", "H", 8);
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("A", "M", 5);
        graph.insertEdge("B", "M", 3);
        graph.insertEdge("D", "A", 7);
        graph.insertEdge("D", "G", 2);
        graph.insertEdge("F", "G", 9);
        graph.insertEdge("G", "L", 7);
        graph.insertEdge("H", "B", 6);
        graph.insertEdge("H", "I", 2);
        graph.insertEdge("I", "H", 2);
        graph.insertEdge("I", "D", 1);
        graph.insertEdge("I", "L", 5);
        graph.insertEdge("M", "E", 3);
        graph.insertEdge("M", "F", 4);

        // Ensure setup is correct
        assertEquals(graph.edgeCount, 15);
        assertEquals(graph.nodes.size(), 10);

        // Test from D to E, path should be D,A,B,M,E and cost should be 14
        List<String> check1 = new LinkedList();
        check1.add("D");
        check1.add("A");
        check1.add("B");
        check1.add("M");
        check1.add("E");
        assertEquals(graph.shortestPathData("D", "E"), check1);
        assertEquals(graph.shortestPathCost("D", "E"), 14, 0);
    }

    /**
     * JUnit Test 2
     * Another test using the same graph as I did for the test above, but check the 
     * cost and sequence of data along the shortest path between a different start and end node.
     */
    @Test
    public void test2() {
        DijkstraGraph<String, Integer> graph = new DijkstraGraph<String,Integer>();
        
        // add nodes to the graph
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");
        graph.insertNode("I");
        graph.insertNode("L");
        graph.insertNode("M");
    
        // add edges to the graph
        graph.insertEdge("A", "H", 8);
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("A", "M", 5);
        graph.insertEdge("B", "M", 3);
        graph.insertEdge("D", "A", 7);
        graph.insertEdge("D", "G", 2);
        graph.insertEdge("F", "G", 9);
        graph.insertEdge("G", "L", 7);
        graph.insertEdge("H", "B", 6);
        graph.insertEdge("H", "I", 2);
        graph.insertEdge("I", "H", 2);
        graph.insertEdge("I", "D", 1);
        graph.insertEdge("I", "L", 5);
        graph.insertEdge("M", "E", 3);
        graph.insertEdge("M", "F", 4);

        // Ensure setup is correct
        assertEquals(graph.edgeCount, 15);
        assertEquals(graph.nodes.size(), 10);

        // Test from I to E, path should be I,H,B,M,E and cost should be 14
        List<String> check2 = new LinkedList();
        check2.add("I");
        check2.add("H");
        check2.add("B");
        check2.add("M");
        check2.add("F");
        assertEquals(graph.shortestPathData("I", "F"), check2);
        assertEquals(graph.shortestPathCost("I", "F"), 15, 0);
    }

    /**
     * JUnit Test 3
     * A test that checks the behavior of my implementation when the node that I am
     * searching for a path between exist in the graph, but there is no sequence of directed edges 
     * that connects them from the start to the end.
     * I am using the same graph as that in lecture, removing the edge from M to E. So now there
     * are no edges going into E
     */
    @Test
    public void test3() {
        DijkstraGraph<String, Integer> graph = new DijkstraGraph<String,Integer>();
        
        // add nodes to the graph
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");
        graph.insertNode("I");
        graph.insertNode("L");
        graph.insertNode("M");
    
        // add edges to the graph
        graph.insertEdge("A", "H", 8);
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("A", "M", 5);
        graph.insertEdge("B", "M", 3);
        graph.insertEdge("D", "A", 7);
        graph.insertEdge("D", "G", 2);
        graph.insertEdge("F", "G", 9);
        graph.insertEdge("G", "L", 7);
        graph.insertEdge("H", "B", 6);
        graph.insertEdge("H", "I", 2);
        graph.insertEdge("I", "H", 2);
        graph.insertEdge("I", "D", 1);
        graph.insertEdge("I", "L", 5);
        graph.insertEdge("M", "F", 4);

        // Ensure setup is correct
        assertEquals(graph.edgeCount, 14);
        assertEquals(graph.nodes.size(), 10);

        // No path from I to E
        try {
            graph.shortestPathData("I", "E");
            assertEquals(1,2);
        }
        catch (NoSuchElementException exception) {

        }

        // No such C node
        try {
            graph.shortestPathData("I", "C");
            assertEquals(1,2);
        }
        catch (NoSuchElementException exception) {
            
        }

    }
    
}