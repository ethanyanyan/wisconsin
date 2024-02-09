public class CityAE  implements CityInterfaceAE{
    private int index;
    private String cityName;
    private String stateName;
    private double latitude;
    private double longitude;
    private double population;
    private int timezone;
    public CityAE(int index, String cityName, String stateName, double latitude, double longitude, double population, int timezone){
        this.index = index;
        this.cityName = cityName;
        this.stateName = stateName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
        this.timezone = timezone;
    }
    @Override
    public int compareTo(CityInterfaceAE o) {
        if(cityName.compareTo(o.getCityName()) > 0){
            return 1;
        }
        if(cityName.compareTo(o.getCityName()) < 0){
            return -1;
        }
        return 0;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public String getStateName() {
        return stateName;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public double getPopulation() {
        return population;
    }

    @Override
    public int getTimezone() {
        return timezone;
    }
    
}
