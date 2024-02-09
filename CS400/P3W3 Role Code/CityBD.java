// --== CS400 Project Three File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: Group BY, team blue

/**
 * This is a placeholder class for the backend devloper to develop and test their code.
 */
public class CityBD {
    // public CityInterface(int index, String cityName, String stateName, double latitude, double longitude, double population, int timezone);
    String name;
    String timezone;
    public CityBD (String name, String timezone) {
        this.name = name;
        this.timezone = timezone;
    }
    public int getIndex() { return 0; }
    public String getCityName() { return this.name; }
    public String getStateName() { return ""; }
    public double getLatitude() { return 0; }
    public double getLongitude() { return 0; }
    public double getPopulation() { return 0; }
    public String getTimezone() { return this.timezone; }
 }
 