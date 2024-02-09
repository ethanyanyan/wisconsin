// --== CS400 File Header Information ==--
// Name: Chenzhe Xu
// Email: cxu296@wisc.edu
// Group and Team: BY
// Group TA: HAFEEZ ALI ANEES ALI
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import org.junit.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;;
/*
 * This class is for AlgorithmEngineerTestb to test DijkstraGraph
 */
public class AlgorithmEngineerTest {
    /*
     * This test is to test whether the function would return the corresponding list that contains the start, end, and
     * the nodes to stop by
     */
    @Test
    public void testOne(){
        DijkstraGraphAE graph = new DijkstraGraphAE<>();
        CityAE chicago = new CityAE(0, "Chicago", "IL", 0, 0, 0, 0);
        CityAE boston = new CityAE(0, "Boston", "MA", 0, 0, 0, 0);
        CityAE losangeles = new CityAE(0, "Los Angeles", "CA", 0, 0, 0, 0);
        CityAE sandiego = new CityAE(0, "San Diego", "CA", 0, 0, 0, 0);
        CityAE miami = new CityAE(0, "Miami", "Florida", 0, 0, 0, 0);
        CityAE madison = new CityAE(0, "Madison", "WI", 0, 0, 0, 0);
        graph.insertNode(chicago);
        graph.insertNode(boston);
        graph.insertNode(losangeles);
        graph.insertNode(sandiego);
        graph.insertNode(miami);
        graph.insertNode(madison);
        graph.insertEdge(sandiego, miami, 2);
        graph.insertEdge(sandiego,losangeles, 1);
        graph.insertEdge(miami, boston, 3);
        graph.insertEdge(losangeles, chicago, 1);
        graph.insertEdge(boston, madison, 1);
        graph.insertEdge(chicago, madison, 1);
        List<CityAE> list = new ArrayList<CityAE>();
        list.add(0, boston);
        List<CityAE> expected = new ArrayList<CityAE>();
        expected.add(sandiego);
        expected.add(miami);
        expected.add(boston);
        expected.add(madison);
        assertEquals(expected, graph.shortestPathDataRestricted(sandiego, madison, list));
    }
    /*
     * This test is to test whether the function would return the corresponding list that contains the start, end, and
     * the nodes to stop by
     */
    @Test
    public void testTwo(){
        DijkstraGraphAE graph = new DijkstraGraphAE<>();
        CityAE chicago = new CityAE(0, "Chicago", "IL", 0, 0, 0, 0);
        CityAE boston = new CityAE(0, "Boston", "MA", 0, 0, 0, 0);
        CityAE losangeles = new CityAE(0, "Los Angeles", "CA", 0, 0, 0, 0);
        CityAE sandiego = new CityAE(0, "San Diego", "CA", 0, 0, 0, 0);
        CityAE miami = new CityAE(0, "Miami", "Florida", 0, 0, 0, 0);
        CityAE madison = new CityAE(0, "Madison", "WI", 0, 0, 0, 0);
        graph.insertNode(chicago);
        graph.insertNode(boston);
        graph.insertNode(losangeles);
        graph.insertNode(sandiego);
        graph.insertNode(miami);
        graph.insertNode(madison);
        graph.insertEdge(sandiego, miami, 2);
        graph.insertEdge(sandiego,losangeles, 3);
        graph.insertEdge(miami, boston, 4);
        graph.insertEdge(losangeles, boston, 2);
        graph.insertEdge(boston,chicago, 1);
        graph.insertEdge(chicago, madison, 1);
        List<CityAE> list = new ArrayList<CityAE>();
        list.add(losangeles);
        list.add(madison);
        List<CityAE> expected = new ArrayList<CityAE>();
        expected.add(sandiego);
        expected.add(losangeles);
        expected.add(boston);
        expected.add(chicago);
        expected.add(madison);
        assertEquals(expected, graph.shortestPathDataRestricted(sandiego, madison, list));
    }
    /*
     * This test is to test whether the function would return the corresponding list that contains the start, end, and
     * the nodes to stop by----the two stop by nodes are not possible to get the path
     */
    @Test
    public void testThree(){
        DijkstraGraphAE graph = new DijkstraGraphAE<>();
        CityAE chicago = new CityAE(0, "Chicago", "IL", 0, 0, 0, 0);
        CityAE boston = new CityAE(0, "Boston", "MA", 0, 0, 0, 0);
        CityAE losangeles = new CityAE(0, "Los Angeles", "CA", 0, 0, 0, 0);
        CityAE sandiego = new CityAE(0, "San Diego", "CA", 0, 0, 0, 0);
        CityAE miami = new CityAE(0, "Miami", "Florida", 0, 0, 0, 0);
        CityAE madison = new CityAE(0, "Madison", "WI", 0, 0, 0, 0);
        graph.insertNode(chicago);
        graph.insertNode(boston);
        graph.insertNode(losangeles);
        graph.insertNode(sandiego);
        graph.insertNode(miami);
        graph.insertNode(madison);
        graph.insertEdge(sandiego, miami, 2);
        graph.insertEdge(sandiego,losangeles, 3);
        graph.insertEdge(miami, boston, 4);
        graph.insertEdge(losangeles, boston, 2);
        graph.insertEdge(boston,chicago, 1);
        graph.insertEdge(chicago, madison, 1);
        List<CityAE> list = new ArrayList<CityAE>();
        list.add(miami);
        list.add(losangeles);
        assertEquals(null, graph.shortestPathDataRestricted(sandiego, madison, list));
    }
    /*
     * This is when the start and end is not possible to have any path
     */
    @Test
    public void testFour(){
        DijkstraGraphAE graph = new DijkstraGraphAE<>();
        CityAE chicago = new CityAE(0, "Chicago", "IL", 0, 0, 0, 0);
        CityAE boston = new CityAE(0, "Boston", "MA", 0, 0, 0, 0);
        CityAE losangeles = new CityAE(0, "Los Angeles", "CA", 0, 0, 0, 0);
        CityAE sandiego = new CityAE(0, "San Diego", "CA", 0, 0, 0, 0);
        CityAE miami = new CityAE(0, "Miami", "Florida", 0, 0, 0, 0);
        CityAE madison = new CityAE(0, "Madison", "WI", 0, 0, 0, 0);
        graph.insertNode(chicago);
        graph.insertNode(boston);
        graph.insertNode(losangeles);
        graph.insertNode(sandiego);
        graph.insertNode(miami);
        graph.insertNode(madison);
        graph.insertEdge(sandiego, miami, 2);
        graph.insertEdge(sandiego,losangeles, 3);
        graph.insertEdge(miami, boston, 4);
        graph.insertEdge(losangeles, boston, 2);
        graph.insertEdge(boston,chicago, 1);
        graph.insertEdge(chicago, madison, 1);
        List<CityAE> list = new ArrayList<CityAE>();
        list.add(miami);
        list.add(boston);
        assertEquals(null, graph.shortestPathDataRestricted(miami, losangeles, list));
    }
    /*
     * This is to test the correctness of the shortestpath return by the method
     */
    @Test void testFive(){
        DijkstraGraphAE graph = new DijkstraGraphAE<>();
        CityAE chicago = new CityAE(0, "Chicago", "IL", 0, 0, 0, 0);
        CityAE boston = new CityAE(0, "Boston", "MA", 0, 0, 0, 0);
        CityAE losangeles = new CityAE(0, "Los Angeles", "CA", 0, 0, 0, 0);
        CityAE sandiego = new CityAE(0, "San Diego", "CA", 0, 0, 0, 0);
        CityAE miami = new CityAE(0, "Miami", "Florida", 0, 0, 0, 0);
        CityAE madison = new CityAE(0, "Madison", "WI", 0, 0, 0, 0);
        graph.insertNode(chicago);
        graph.insertNode(boston);
        graph.insertNode(losangeles);
        graph.insertNode(sandiego);
        graph.insertNode(miami);
        graph.insertNode(madison);
        graph.insertEdge(sandiego, miami, 2);
        graph.insertEdge(sandiego,losangeles, 3);
        graph.insertEdge(miami, boston, 4);
        graph.insertEdge(losangeles, boston, 2);
        graph.insertEdge(boston,chicago, 1);
        graph.insertEdge(chicago, madison, 1);
        List<CityAE> list = new ArrayList<CityAE>();
        list.add(losangeles);
        list.add(madison);
        assertEquals(7.0, graph.shortestPathCostRestricted(sandiego, madison, list));
    }
}
