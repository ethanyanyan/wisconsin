// --== CS400 Project Three File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: Group BY, team blue

import java.io.FileNotFoundException;
import java.util.List;

public interface RouteSearchBackendInterface {   
    // public RouteSearchBackendInterface(GraphInterface graph);
    public void loadData(String filename) throws FileNotFoundException;
    public <NodeType> List<NodeType> findRoutes(String startCity, String endCity);
    public <NodeType> List<NodeType> findRoutesRestricted(String startCity, String endCity, List<NodeType> citiesToGoThrough);
}
