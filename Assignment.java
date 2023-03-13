//package ProblemBasedLearning;

import java.sql.Statement;
import java.util.Queue;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import eu.bitm.NominatimReverseGeocoding.Address;
import eu.bitm.NominatimReverseGeocoding.NominatimReverseGeocodingJAPI;

//covers Step 1 and 2.
public class Assignment {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        // Step1: Use
        // https://www.daniel-braun.com/technik/reverse-geocoding-library-for-java/
        // library to find
        // out the city and country from the given coordinates and store them in yearly
        // earthquake collection along
        // with magnitude. (Collection of each year means 52 collections)

        NominatimReverseGeocodingJAPI nominatim1 = new NominatimReverseGeocodingJAPI();
        Address address;
        String url = "jdbc:mysql://localhost:3306/pbl";
        String uname = "root";
        String pass = "";
        String query = "Select substr(Date, -4 , 4) as 'Date' from earthquake;"; // getting the year from database.
        Connection com = DriverManager.getConnection(url, uname, pass);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Statement st = com.createStatement();
        ResultSet rs = st.executeQuery(query);

        yearly_earthquakes[] arr = new yearly_earthquakes[52]; // 52 collections
        String year = null, temp = null, city = null, country = null;
        for (int i = 0; i < arr.length; i++) {
            if (rs.next())
                year = rs.getString(1);
            if (year.equals(temp)) {
                i--;
                continue;
            } else {
                arr[i] = new yearly_earthquakes(year);// passing year to each object of yearly_earthquakes starting from
                                                      // 1965.
                temp = year;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            query = "Select Latitude, Longitude , Magnitude from earthquake where Date like '%" + arr[i].getYear()
                    + "';"; // getting number of rows of coordinates and magnitude for specific year.
            rs = st.executeQuery(query);
            while (rs.next()) {
                double latitude = rs.getDouble("Latitude");
                double longitude = rs.getDouble("Longitude");
                address = nominatim1.getAdress(latitude, longitude);
                country = address.getCountry();
                arr[i].addCountry(country);// add country,city and magnitude of (i)year i.e, 1965,1966.. to
                                           // yearly_earthquakes.
                city = address.getCity();
                arr[i].addCity(city);
                arr[i].setMagnitude(rs.getDouble("Magnitude"));
            }
            System.out.println(arr[i].getYear());
        } // end of for loop.
        st.close();
        com.close();

        // Step 2 : Make a queue storing biggest (with highest magnitude) quake of each
        // year with magnitude and
        // country, starting from 1965 to 2016. (52 elements in the queue approx.)

        Queue<Object> queue_obj = new java.util.LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            // this compareMagnitude() method compares magnitude of country of each year
            // and returns the index of that country.
            int index = arr[i].compareMagnitude();
            // that country is added to the queue along with magnitude through the returned
            // index.
            queue_obj.add(arr[i].getCountry(index) + " " + arr[i].getMagnitude(index));
            // Here queue is converted to array, because we will need to access elements of
            // 2005 to 2015(For traversal)
        }
        stackAndLinkedList obj = new stackAndLinkedList();
        /*
         * Problem 1
         * Find the average number of earthquakes per year for each country and which
         * country is
         * most vulnerable to earthquakes (which country has the most number of earth
         * quakes)?
         */
        System.out.println("Problem 1-");
        obj.averageNumberOfEarthquakes();
        obj.mostVulnerable();
        /*
         * Problem 2
         * Which are the biggest earthquakes from 2005 to 2015 and occurred and in which
         * country (use step 2)?
         */
        System.out.println("Problem 2-");
        // abs() returns positive value, if we want element of year 2005, 2005-1965 =
        // 40. Start the loop from 40
        // where biggest earthquake of 2005 is stored.
        Object[] highest_magnitudes = new Object[52];
        highest_magnitudes = queue_obj.toArray();
        for (int i = Math.abs(1965 - 2005); i <= Math.abs(1965 - 2015); i++)
            System.out.println(arr[i].getYear() + " " + highest_magnitudes[i]);
        /*
         * Problem 3
         * How to determine the recent 5 earthquakes from each country?
         */
        System.out.println("Problem 3-");
        obj.recentEarthquakes();
        /*
         * Problem 4
         * How to find the most recent above 6 magnitude earthquakes (use step 4).
         */
        System.out.println("Problem 4-\nRecent above 6 magnitude earthquakes:");
        obj.earthquake_above_6();
    } // end of main()
} // end of program
