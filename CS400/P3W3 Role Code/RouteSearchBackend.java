// --== CS400 Project Three File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: Group BY, team blue

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * This is the RouteSearchBackend class that makes use of a CityInterface object implemented by 
 * the DW and a Graph object developed by the AE to efficiently store and retrieve information 
 * about paths from one city to another.
 */
public class RouteSearchBackend<NodeType> {   
    CityReaderBD cityReader;
    DijkstraGraphBD graph;
    Hashtable<String,CityBD> hashtable; // hashtable to store city names and respective timezones
    int count;

    /**
     * This is the constructor for the RouteSearch Backend class, initializing the DijkstraGraph,
     * Hashtable and cityReader objects.
     */
    public RouteSearchBackend(CityReaderBD cityReader, DijkstraGraphBD graph) {
        this.cityReader = cityReader;
        this.graph = graph;
        this.hashtable = new Hashtable<>();
        this.count = 0;
    }

    /**
     * This loadData method loads a specified filename and utilizes the readCitiesFromFile method
     * developed by the DW and then adds the City objects to the DijkstraGraph and Hashtable.
     * The count for how many city objects was parsed is updated accordingly as well.
     * @param filename file to be read ad parsed
     * @throws FileNotFoundException exception is thrown when file to be read does not exist in the
     *                               working directory
     */
    public void loadData(String filename) throws FileNotFoundException {
        // readCitiesFromFile method may throw a FileNotFoundException
        List<CityBD> cities = cityReader.readCitiesFromFile(filename);
        for (CityBD city : cities){
            graph.insertNode(city);
            hashtable.put(city.getCityName(), city);
            count++;
        }
    }

    /**
     * Filters through the cities and returns a list of cities that matches the timezone defined 
     * as a argument.
     * @param timezone to look for cities in this specified timezone
     * @return a list of Strings with the city names that matches the timezone specified
     */
    public List<String> findCitiesByTimeZone(String timezone) {
        List<String> matchingCities = new ArrayList<>();
        
        for (Map.Entry<String, CityBD> entry : hashtable.entrySet()) {
            String city = entry.getKey();
            String cityTimezone = entry.getValue().getTimezone();
            
            if (cityTimezone.equals(timezone)) {
                matchingCities.add(city);
            }
        }
        
        return matchingCities;
    }

    /**
     * Returns information about a city in a String format.
     * @param cityName city that we are searching information about
     * @return a string detailing all the information about a city
     */
    public String getCityInfo(String cityName) {
        CityBD city = hashtable.get(cityName);

        return "City Name: " + city.getCityName() + ", State: " + city.getStateName() 
                + ", Population: " + city.getPopulation() + ", Timezone: " + city.getTimezone() 
                + ", Latitude: " + city.getLatitude() + ", Longitude: " + city.getLongitude();
    }

    /**
     * Uses the shortestPathData method by the AE to get the shortest path between 2 cities then
     * returning the path suggested in a List of strings.
     * @param startCity City to start path from
     * @param endCity City to end path at
     * @return a list of strings that detail the path of the shortest cost
     */
    public List<String> findShortestRoute(String startCity, String endCity) {
        List<NodeType> shortestRoute = graph.shortestPathData(startCity, endCity);
        List<String> returnList = new ArrayList<>();
        for (NodeType node : shortestRoute) {
            returnList.add((String) node);
        }
        return returnList;
    }

    /**
     * Uses the shortestPathCost method by the AE to get the shortest path between 2 cities then
     * returning the path suggested in a List of strings.
     * @param startCity City to start path from
     * @param endCity City to end path at
     * @return the cost of the shortest path
     */
    public double findShortestRouteCost(String startCity, String endCity) {
        return graph.shortestPathCost(startCity, endCity);
    }

    /**
     * Uses the shortestPathDataRestricted method by the AE to get the shortest path between 
     * 2 cities, with a restriction specifying certain cities that must be gone through, then 
     * returning the path suggested in a List of strings.
     * @param startCity City to start path from
     * @param endCity City to end path at
     * @param citiesToGoThrough restriction specifying certain cities that must be gone through 
     * @return a list of strings that detail the path of the shortest cost
     */
    public List<String> findRoutesRestricted(String startCity, String endCity, List<NodeType> citiesToGoThrough) {
        List<NodeType> shortestRoute = graph.shortestPathDataRestricted(startCity, endCity, citiesToGoThrough);
        List<String> returnList = new ArrayList<>();
        for (NodeType node : shortestRoute) {
            returnList.add((String) node);
        }
        return returnList;
    }

    /**
     * Uses the shortestPathDataCost method by the AE to get the shortest path between 
     * 2 cities, with a restriction specifying certain cities that must be gone through, then 
     * returning the path suggested in a List of strings.
     * @param startCity City to start path from
     * @param endCity City to end path at
     * @param citiesToGoThrough restriction specifying certain cities that must be gone through 
     * @return the cost of the shortest path
     */
    public double findRoutesRestrictedCost(String startCity, String endCity, List<NodeType> citiesToGoThrough) {
        return graph.shortestPathCostRestricted(startCity, endCity, citiesToGoThrough);
    }

    /**
     * Basic getter method to return the count of cities parsed in loadData method
     * @return count of cities parsed
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Basic getter method to retrieve DijkstraGraph used
     * @return DijkstraGraph object in the RouteSearchBackend object
     */
    public DijkstraGraphBD getGraph() {
        return this.graph;
    }
    
    /**
     * Basic getter method to retrieve Hashtable used
     * @return Hashtable object in the RouteSearchBackend object
     */
    public Hashtable getHashtable() {
        return this.hashtable;
    }

    /**
     * Basic getter method to retrieve cityReader used
     * @return cityReader object in the RouteSearchBackend object
     */
    public CityReaderBD getReader() {
        return this.cityReader;
    }
}
