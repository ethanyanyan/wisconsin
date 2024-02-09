// --== CS400 Project Three File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: Group BY, team blue

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes.  This class makes use of
 * Dijkstra's shortest path algorithm.
 * This is a placeholder class for the backend developer to develop and test their code.
 */
public class DijkstraGraphBD<NodeType, EdgeType extends Number>
    extends BaseGraphBD<NodeType,EdgeType>
    implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a CityNode
     * contains data about one specific path between the start node and another
     * node in the graph.  The final node in this path is stored in it's node
     * field.  The total cost of this path is stored in its cost field.  And the
     * predecessor CityNode within this path is referened by the predecessor
     * field (this field is null within the CityNode containing the starting
     * node in it's node field).
     *
     * CityNodes are Comparable and are sorted by cost so that the lowest cost
     * CityNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class CityNode implements Comparable<CityNode> {
        public Node node;
        public double cost;
        public CityNode predecessor;
        public CityNode(Node node, double cost, CityNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }
        public int compareTo(CityNode other) {
            if( cost > other.cost ) return +1;
            if( cost < other.cost ) return -1;
            return 0;
        }
    }
    /**
     * This helper method creates a network of CityNodes while computing the
     * shortest path between the provided start and end locations.  The
     * CityNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return CityNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *         or when either start or end data do not correspond to a graph node
     */
    protected CityNode computeShortestPath(NodeType start, NodeType end) {
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
        return 0;
    }

    public void removeAll() {

    }
    public List<NodeType> shortestPathDataRestricted(NodeType start, NodeType end, List<NodeType> nodesToGoThrough) {
        List returnList = new LinkedList<>();

        return returnList;
    }

    public double shortestPathCostRestricted(NodeType start, NodeType end, List<NodeType> nodesToGoThrough) {
        return 0;
    }

}