public interface CityInterfaceAE extends Comparable<CityInterfaceAE>{
    // public CityInterface(int index, String cityName, String stateName, double latitude, double longitude, double population, int timezone);
    public int getIndex(); 
    public String getCityName();
    public String getStateName();
    public double getLatitude();
    public double getLongitude();
    public double getPopulation();
    public int getTimezone();
 }
 