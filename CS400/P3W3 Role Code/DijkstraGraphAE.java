// --== CS400 File Header Information ==--
// Name: Chenzhe Xu
// Email: cxu296@wisc.edu
// Group and Team: BY
// Group TA: HAFEEZ ALI ANEES ALI
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes.  This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraphAE<NodeType, EdgeType extends Number>
    extends BaseGraphAE<NodeType,EdgeType>
    implements GraphADTAE<NodeType, EdgeType> {
    private double shortest;
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
    public DijkstraGraphAE(){
        shortest = 0;
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
    protected CityNode computeShortestPath(CityAE start, CityAE end) {

        //explore shorter path possibilities before longer ones within your computeShortestPath definition.
        PriorityQueue<CityNode> queue = new PriorityQueue<>();
        //make use of a java.util.Hashtable to keep track of the nodes that you have already 
        //visited (and found the shortest path reaching them from the start node).
        Hashtable<Node, Double> visited = new Hashtable<>();
        //Get the starting node from hashtable
        Node node = nodes.get(start);
        //Check whether the start node and end node exist
        if(containsNode(start) == false || containsNode(end) == false){
            throw new NoSuchElementException("Error: computeShortestPath() start or end is not existing in the graph");
        }
        //Create the startNode
        CityNode startNode = new CityNode(node, 0, null);
        //Insert to Priority Queue and Hashtable
        visited.put(node, 0.0);
        queue.add(startNode);
        //Condition to check whether the queue is empty
        while (!queue.isEmpty()){
            CityNode current = queue.poll(); 
            //Once the path founded, return
            if (current.node.city.getCityName().equals(end.getCityName())) {
                return current;
            }
            //Check each edge between two given node
            for(Edge edge: current.node.edgesLeaving){
                double costs = current.cost + edge.data;
                //Condition to check
                if((!visited.containsKey(edge.successor) || costs < visited.get(edge.successor))){
                    visited.put(edge.successor, costs);
                    CityNode neighborNode = new CityNode(edge.successor, costs, current);
                    queue.add(neighborNode);
                }
            }
  
        }
        throw new NoSuchElementException("Error: computeShortestPath() No path from start to end is found");
    }
    /*
     * This method is to find all paths from the start node and end node
     */
    protected List<List<Node>> findAllPaths(Node start, Node end) {
        List<List<Node>> paths = new LinkedList<>();
        Node startNode = start;
        Node endNode = end;
        if (startNode == null || endNode == null) {
            return paths; // return empty list
        }
        List<Node> path = new LinkedList<>();
        path.add(start);
        dfs(paths, path, startNode, endNode);
        return paths;
    }
    /*
     * This is the help method to help findAllPaths to find all path from start to end
     */
    private void dfs(List<List<Node>> paths, List<Node> path, Node start, Node end) {
        if (start == end) {
            paths.add(new LinkedList<>(path)); // add new path to the list
        } else {
            for (Edge edge : start.edgesLeaving) {
                Node nextNode = edge.successor;
                if (!path.contains(nextNode)) { // avoid cycles
                    path.add(nextNode);
                    dfs(paths, path, nextNode, end);
                    path.remove(path.size() - 1); // backtrack
                }
            }
        }
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
    public List<Node> shortestPathData(CityAE start, CityAE end){
                //Get the end of the shortest path
                CityNode node = computeShortestPath(start, end);
                List<Node> shortestPathData = new ArrayList<>();
                //Loop rhtough and insert to the list
                while(node != null){
                    shortestPathData.add(0, node.node);
                    node = node.predecessor;
                }
                return shortestPathData;
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
    public double shortestPathCost(CityAE start, CityAE end) {
        //Get from the shortest path
        CityNode node = computeShortestPath(start, end);
        return node.cost;
    }
    /*
     * This function is to get the shortest Path with the requirement of nodes to goe through and it will
     * return null if there is no path exists and the path reference if it exists
     */
    public List<CityAE>shortestPathDataRestricted(CityAE start, CityAE end, List<CityAE> nodesToGoThrough) {
        Node node_one = nodes.get(start);
        Node node_two = nodes.get(end);
        //Get the end of the shortest path
        List<List<Node>> node = findAllPaths(node_one, node_two);
        double[] arr = getShortest(node, nodesToGoThrough);
        shortest = arr[0];
        int index = (int)arr[1];
        if(arr[1] == -1){
            return null;
        }else{
            List<CityAE> cityReturn = new ArrayList<>();
            for(int i = 0; i < node.get(index).size(); i++){
                cityReturn.add(node.get(index).get(i).city);
            }
            return cityReturn;
        }
 
    }
    /*
     * This is the helper method for shortestPathDataRestricted to get the shortest path
     */
    private double[] getShortest(List<List<Node>> node, List<CityAE> nodesToGoThrough){
        int index = -1;
        List<List<Node>> processed = new ArrayList<>();
        //The following is to processs the node list in order to get the paths that contain all nodesToGoThrough
        for(int i=0; i < node.size(); i++){
            boolean[] myBooleanArray = new boolean[nodesToGoThrough.size()];
            for(int k = 0; k < myBooleanArray.length; k++){
                myBooleanArray[k] = false;
            }
            for(int j = 0; j < nodesToGoThrough.size(); j++){
                for(int a = 0; a < node.get(i).size(); a++){
                    if(node.get(i).get(a).city.getCityName().equals(nodesToGoThrough.get(j).getCityName())){
                        myBooleanArray[j] = true;
                        break;
                    }
                }
            }
            int trueCount = 0;
            for(int a = 0; a < myBooleanArray.length; a++){
                if(myBooleanArray[a] == true){
                    trueCount++;
                }
            }
            if(trueCount == myBooleanArray.length){
                processed.add(node.get(i));
            }
        }
        //node = processed;
        double[] check = new double[processed.size()];
        for(int i = 0; i < check.length; i++){
            check[i] = 0;
        }
        for(int i = 0; i <processed.size(); i++){
            for(int j = 0; j < processed.get(i).size()-1; j++){
                List<Edge> edgesLeaving = processed.get(i).get(j).edgesLeaving;
                for(int k =0; k<edgesLeaving.size(); k++){
                    if(edgesLeaving.get(k).successor.equals(processed.get(i).get(j+1))){
                        //System.out.println(edgesLeaving.get(k).data);
                        check[i] += edgesLeaving.get(k).data;
                        break;
                    }
                }
            }
        }
        //The following is to get the shortest path from the list of list of node
        double min = Double.MAX_VALUE;
        for (int i = 0; i <check.length; i++) {
            if (check[i] < min) {
                index = i;
                min = check[i];
            }
        }
        double[] arr = new double[2];
        arr[0] = min;
        if(index != -1){
            List<Node> list_check = processed.get(index);
            for(int i = 0; i < node.size(); i++){
                if(node.get(i).equals(list_check)){
                    index = i;
                    break;
                }
            }
        }
        arr[1] = index;
        return arr;
    }

    /*
     * This method is to get the cost of the shortest path with the nodesToGoThrough requirement
     */
    public double shortestPathCostRestricted(CityAE start, CityAE end, List<CityAE> nodesToGoThrough) {
        shortestPathDataRestricted( start,  end, nodesToGoThrough);
        return shortest;
    }
}