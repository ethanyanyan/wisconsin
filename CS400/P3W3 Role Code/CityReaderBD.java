// --== CS400 Project Three File Header ==--
// Name: Ethan Yikai Yan
// CSL Username: eyy
// Email: eyyan@wisc.edu
// Lecture #: LEC 002 TR 13:00 - 14:15
// Notes to Grader: Group BY, team blue

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is a placeholder class for the backend developer to develop and test their code.
 */
public class CityReaderBD {
    // public CityReaderInterface();
    /**
     * 
     * @param filename file to read from
     * @return List of City objects
     * @throws FileNotFoundException when file does not exist
     */
    public List<CityBD> readCitiesFromFile(String filename) throws FileNotFoundException {
        List<CityBD> cities = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            String[] cityLine = scanner.nextLine().split(",");
            String cityName = cityLine[0].trim();
            String timeZone = cityLine[1].trim();
            cities.add(new CityBD(cityName, timeZone));
        }
        scanner.close();
        return cities;
    }
 }
 