//package ProblemBasedLearning;

import java.util.ArrayList;
//covers step 1 and 2.
public class yearly_earthquakes {
    private String year;
    private ArrayList<Object> countries = new ArrayList<>();
    private ArrayList<String> cities = new ArrayList<>();
    private ArrayList<Double> magnitude = new ArrayList<>();

    public yearly_earthquakes(String year) {
        this.year = year;
    }

    public int no_of_earthquakes() {
        return magnitude.size();
    }

    public void addCountry(String country) {
        countries.add(country);
    }

    public void addCity(String city) {
        cities.add(city);
    }

    public void setMagnitude(double magnitude2) {
        this.magnitude.add(magnitude2);
    }

    public String getYear() {
        return year;
    }

    public String getCountries() {
        return countries + "";
    }

    public String getCities() {
        return cities + "";
    }

    public double getMagnitude(int index) {
        return this.magnitude.get(index);
    }

    public Object getCountry(int index) {
        return this.countries.get(index);
    }

    // used for step 2.
    public int compareMagnitude() {
        double max_magnitude = this.magnitude.get(0);
        for (int i = 1; i < this.magnitude.size(); i++) {
            if (this.magnitude.get(i) > max_magnitude) {
                max_magnitude = this.magnitude.get(i);
            }
        }
        int index = this.magnitude.indexOf(max_magnitude);
        return index;
    }
}
