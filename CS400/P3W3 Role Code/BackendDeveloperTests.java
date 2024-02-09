// --== CS400 Project Three File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: Group BY, team blue

import org.junit.*;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class BackendDeveloperTests {
    /*                                                                                                         
     * JUnit test1 checks to see if the cityReader, DijkstraGraphBD, and hashtable objects being 
     * created is being appropriately stored and retrieved as defined in the 
     * RouteSeachBackend object.                                     
     */
    @Test
    public void test1(){
        CityReaderBD cityReader = new CityReaderBD();
        DijkstraGraphBD graph = new DijkstraGraphBD<>();
        Hashtable<String,CityBD> hashtable = new Hashtable<>();
        RouteSearchBackend backend = new RouteSearchBackend<>(cityReader, graph);
        assertEquals(cityReader, backend.getReader());

        assertEquals(graph, backend.getGraph());

        assertEquals(hashtable, backend.getHashtable());
    }

    /*                                                                                                         
     * JUnit test2 checks to see if the following methods: findShortestRoute, findRoutesRestricted,
     * findShortestRouteCost, findRoutesRestrictedCost, all return the correct output type and 
     * correct output based on the placeholder classes defined.                                         
     */
    @Test
    public void test2(){
        CityReaderBD cityReader = new CityReaderBD();
        DijkstraGraphBD graph = new DijkstraGraphBD<>();
        RouteSearchBackend backend = new RouteSearchBackend<>(cityReader, graph);
        List<String> listCheck = new ArrayList<>();
        List<String> restriction = new ArrayList<>();
        restriction.add("Seattle");
        assertEquals(listCheck, backend.findShortestRoute("Chicago", "Denver"));
        assertEquals(listCheck, backend.findRoutesRestricted("Chicago", "Denver", restriction));
        assertEquals(0.0, backend.findShortestRouteCost("Chicago", "Denver"), 0);
        assertEquals(0.0, backend.findRoutesRestrictedCost("Chicago", "Denver", restriction), 0);
    }

    /*                                                                                                         
     * JUnit test3 checks the getCityInfo to ensure that it accurately retrieves and displays the
     * city info based on what is specified as the argument.                                     
     */
    @Test
    public void test3(){
        CityReaderBD cityReader = new CityReaderBD();
        DijkstraGraphBD graph = new DijkstraGraphBD<>();
        RouteSearchBackend backend = new RouteSearchBackend<>(cityReader, graph);
        String check = "City Name: Chicago, State: , Population: 0.0, Timezone: America/Chicago, Latitude: 0.0, Longitude: 0.0";
        try {
            String filePath = "./testerFile.txt";
            backend.loadData(filePath);
            assertEquals(check, backend.getCityInfo("Chicago"));
        }
        catch (FileNotFoundException e) {
            assertEquals("", "File not found");
        }
    }

    /*                                                                                                         
     * Junit test4 checks if the loadData method is adding cities from a 
     * tester file (testerFile.txt), to the hashtable defined and dijkstra's graph. It also checks
     * if the overall count matches what is expected                                   
     */
    @Test
    public void test4(){
        CityReaderBD cityReader = new CityReaderBD();
        DijkstraGraphBD graph = new DijkstraGraphBD<>();
        RouteSearchBackend backend = new RouteSearchBackend<>(cityReader, graph);
        try {
            String filePath = "./testerFile.txt";
            backend.loadData(filePath);
            assertEquals(3, backend.getCount());
            assertEquals(3, backend.getHashtable().size());
            assertEquals(3, backend.getGraph().getNodeCount());
        }
        catch (FileNotFoundException e) {
            assertEquals("", "File not found");
        }
    }

    /*                                                                                                         
     * Junit test5 checks if the findCitiesByTimeZone method can accurately filter and retrieve
     * City Timezones based on the specified timezone.                                  
     */
    @Test
    public void test5(){
        CityReaderBD cityReader = new CityReaderBD();
        DijkstraGraphBD graph = new DijkstraGraphBD<>();
        RouteSearchBackend backend = new RouteSearchBackend<>(cityReader, graph);
        List<String> check = new ArrayList<>();
        check.add("Chicago");
        try {
            String filePath = "./testerFile.txt";
            backend.loadData(filePath);
            assertEquals(check, backend.findCitiesByTimeZone("America/Chicago"));
        }
        catch (FileNotFoundException e) {
            assertEquals("", "File not found");
        }
    }

    /**
     * First Integration Test. Test is not passed as List of String is expected but List of Nodes
     * are output instead. AE did not follow the proposal, return a list of Nodes instead of a list
     * of NodeTypes. This is for both findShortestRoute and findShortestRouteRestricted
     */
    @Test
    public void Integration1(){
        CityReaderBD cityReader = new CityReaderBD();
        DijkstraGraphAE graph = new DijkstraGraphAE<>();
        CityAE chicago = new CityAE(0, "Chicago", "IL", 0, 0, 0, 0);
        CityAE boston = new CityAE(0, "Boston", "MA", 0, 0, 0, 0);
        CityAE losangeles = new CityAE(0, "Los Angeles", "CA", 0, 0, 0, 0);
        CityAE sandiego = new CityAE(0, "San Diego", "CA", 0, 0, 0, 0);
        CityAE miami = new CityAE(0, "Miami", "Florida", 0, 0, 0, 0);
        CityAE madison = new CityAE(0, "Madison", "WI", 0, 0, 0, 0);
        CityAE denver = new CityAE(0, "Denver", "CO", 0, 0, 0, 0);
        graph.insertNode(chicago);
        graph.insertNode(boston);
        graph.insertNode(losangeles);
        graph.insertNode(sandiego);
        graph.insertNode(miami);
        graph.insertNode(madison);
        graph.insertNode(denver);
        graph.insertEdge(sandiego, miami, 2);
        graph.insertEdge(sandiego,losangeles, 3);
        graph.insertEdge(miami, boston, 4);
        graph.insertEdge(losangeles, boston, 2);
        graph.insertEdge(boston,chicago, 1);
        graph.insertEdge(chicago, madison, 1);
        graph.insertEdge(losangeles, denver, 1);
        graph.insertEdge(denver, madison, 1);
        graph.insertEdge(boston, denver, 10);
        RouteSearchBackendBDIntegration backend = new RouteSearchBackendBDIntegration(cityReader, graph);
        List<String> output = backend.findShortestRoute("San Diego", "Madison");
        List<String> check = new ArrayList<>();
        check.add("San Diego");
        check.add("Los Angeles");
        check.add("Denver");
        check.add("Madison");
        assertEquals(check, output);
    }

    /**
     * Second Integration Test. Methods that return doubles are working. No issues with Types.
     * This is for findShortestRouteCost and findRoutesRestrictedCost
     */
    @Test
    public void Integration2(){
        CityReaderBD cityReader = new CityReaderBD();
        DijkstraGraphAE graph = new DijkstraGraphAE<>();
        CityAE chicago = new CityAE(0, "Chicago", "IL", 0, 0, 0, 0);
        CityAE boston = new CityAE(0, "Boston", "MA", 0, 0, 0, 0);
        CityAE losangeles = new CityAE(0, "Los Angeles", "CA", 0, 0, 0, 0);
        CityAE sandiego = new CityAE(0, "San Diego", "CA", 0, 0, 0, 0);
        CityAE miami = new CityAE(0, "Miami", "Florida", 0, 0, 0, 0);
        CityAE madison = new CityAE(0, "Madison", "WI", 0, 0, 0, 0);
        CityAE denver = new CityAE(0, "Denver", "CO", 0, 0, 0, 0);
        graph.insertNode(chicago);
        graph.insertNode(boston);
        graph.insertNode(losangeles);
        graph.insertNode(sandiego);
        graph.insertNode(miami);
        graph.insertNode(madison);
        graph.insertNode(denver);
        graph.insertEdge(sandiego, miami, 2);
        graph.insertEdge(sandiego,losangeles, 3);
        graph.insertEdge(miami, boston, 4);
        graph.insertEdge(losangeles, boston, 2);
        graph.insertEdge(boston,chicago, 1);
        graph.insertEdge(chicago, madison, 1);
        graph.insertEdge(losangeles, denver, 1);
        graph.insertEdge(denver, madison, 1);
        graph.insertEdge(boston, denver, 10);
        RouteSearchBackendBDIntegration backend = new RouteSearchBackendBDIntegration(cityReader, graph);
        double output = backend.findShortestRouteCost("San Diego", "Madison");
        assertEquals(5.0, output, 0.0);
    }

    /**
     * First test for the CodeReview of the Algorithm Engineer's shortestPathDataRestricted method
     * implemented. Created a more extensive graph to test the functionalities of the code to ensure that the
     * shortestPathDataRestricted is working correctly.
     */
    @Test
    public void CodeReviewOfAlgorithmEngineer1(){
        DijkstraGraphAE graph = new DijkstraGraphAE<>();
        CityAE chicago = new CityAE(0, "Chicago", "IL", 0, 0, 0, 0);
        CityAE boston = new CityAE(0, "Boston", "MA", 0, 0, 0, 0);
        CityAE losangeles = new CityAE(0, "Los Angeles", "CA", 0, 0, 0, 0);
        CityAE sandiego = new CityAE(0, "San Diego", "CA", 0, 0, 0, 0);
        CityAE miami = new CityAE(0, "Miami", "Florida", 0, 0, 0, 0);
        CityAE madison = new CityAE(0, "Madison", "WI", 0, 0, 0, 0);
        CityAE denver = new CityAE(0, "Denver", "CO", 0, 0, 0, 0);
        graph.insertNode(chicago);
        graph.insertNode(boston);
        graph.insertNode(losangeles);
        graph.insertNode(sandiego);
        graph.insertNode(miami);
        graph.insertNode(madison);
        graph.insertNode(denver);
        graph.insertEdge(sandiego, miami, 2);
        graph.insertEdge(sandiego,losangeles, 3);
        graph.insertEdge(miami, boston, 4);
        graph.insertEdge(losangeles, boston, 2);
        graph.insertEdge(boston,chicago, 1);
        graph.insertEdge(chicago, madison, 1);
        graph.insertEdge(losangeles, denver, 1);
        graph.insertEdge(denver, madison, 1);
        graph.insertEdge(boston, denver, 1);
        List<CityAE> list = new ArrayList<CityAE>();
        list.add(boston);
        list.add(denver);
        List<CityAE> expected = new ArrayList<CityAE>();
        expected.add(sandiego);
        expected.add(losangeles);
        expected.add(boston);
        expected.add(denver);
        expected.add(madison);
        assertEquals(expected, graph.shortestPathDataRestricted(sandiego, madison, list));
    }

    /**
     * Second test for the CodeReview of the Algorithm Engineer's shortestPathCostRestricted
     * implemented. Created a more extensive graph to test the functionalities of the code to ensure that the
     * shortestPathDataRestricted is working correctly.
     */
    @Test
    public void CodeReviewOfAlgorithmEngineer2(){
        DijkstraGraphAE graph = new DijkstraGraphAE<>();
        CityAE chicago = new CityAE(0, "Chicago", "IL", 0, 0, 0, 0);
        CityAE boston = new CityAE(0, "Boston", "MA", 0, 0, 0, 0);
        CityAE losangeles = new CityAE(0, "Los Angeles", "CA", 0, 0, 0, 0);
        CityAE sandiego = new CityAE(0, "San Diego", "CA", 0, 0, 0, 0);
        CityAE miami = new CityAE(0, "Miami", "Florida", 0, 0, 0, 0);
        CityAE madison = new CityAE(0, "Madison", "WI", 0, 0, 0, 0);
        CityAE denver = new CityAE(0, "Denver", "CO", 0, 0, 0, 0);
        graph.insertNode(chicago);
        graph.insertNode(boston);
        graph.insertNode(losangeles);
        graph.insertNode(sandiego);
        graph.insertNode(miami);
        graph.insertNode(madison);
        graph.insertNode(denver);
        graph.insertEdge(sandiego, miami, 2);
        graph.insertEdge(sandiego,losangeles, 3);
        graph.insertEdge(miami, boston, 4);
        graph.insertEdge(losangeles, boston, 2);
        graph.insertEdge(boston,chicago, 1);
        graph.insertEdge(chicago, madison, 1);
        graph.insertEdge(losangeles, denver, 1);
        graph.insertEdge(denver, madison, 1);
        graph.insertEdge(boston, denver, 10);
        List<CityAE> list = new ArrayList<CityAE>();
        list.add(boston);
        list.add(denver);
        List<CityAE> expected = new ArrayList<CityAE>();
        expected.add(sandiego);
        expected.add(losangeles);
        expected.add(boston);
        expected.add(chicago);
        expected.add(madison);
        assertEquals(16.0, graph.shortestPathCostRestricted(sandiego, madison, list), 0.0);
    }

    public static void main(String[] args) {
        DijkstraGraphAE graph = new DijkstraGraphAE<>();
        CityAE chicago = new CityAE(0, "Chicago", "IL", 0, 0, 0, 0);
        CityAE boston = new CityAE(0, "Boston", "MA", 0, 0, 0, 0);
        CityAE losangeles = new CityAE(0, "Los Angeles", "CA", 0, 0, 0, 0);
        CityAE sandiego = new CityAE(0, "San Diego", "CA", 0, 0, 0, 0);
        CityAE miami = new CityAE(0, "Miami", "Florida", 0, 0, 0, 0);
        CityAE madison = new CityAE(0, "Madison", "WI", 0, 0, 0, 0);
        CityAE denver = new CityAE(0, "Denver", "CO", 0, 0, 0, 0);
        graph.insertNode(chicago);
        graph.insertNode(boston);
        graph.insertNode(losangeles);
        graph.insertNode(sandiego);
        graph.insertNode(miami);
        graph.insertNode(madison);
        graph.insertNode(denver);
        graph.insertEdge(sandiego, miami, 2);
        graph.insertEdge(sandiego,losangeles, 3);
        graph.insertEdge(miami, boston, 4);
        graph.insertEdge(losangeles, boston, 2);
        graph.insertEdge(boston,chicago, 1);
        graph.insertEdge(chicago, madison, 1);
        graph.insertEdge(losangeles, denver, 1);
        graph.insertEdge(denver, madison, 1);
        graph.insertEdge(boston, denver, 10);
        List<CityAE> list = new ArrayList<CityAE>();
        list.add(boston);
        List<CityAE> expected = new ArrayList<CityAE>();
        expected.add(sandiego);
        expected.add(losangeles);
        expected.add(boston);
        expected.add(chicago);
        expected.add(madison);
        assertEquals(expected, graph.shortestPathDataRestricted(sandiego, madison, list));
        System.out.println(expected);
        System.out.println(graph.shortestPathDataRestricted(sandiego, madison, list));
    }
}
