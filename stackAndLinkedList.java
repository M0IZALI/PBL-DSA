//package ProblemBasedLearning;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Stack;
import java.util.LinkedList;

//Covers Step 3 and 4..
public class stackAndLinkedList {
    String[] countries = { "United States", "China", "Indonesia", "Iran", "Philippines", "Chile", "Vanuatu",
            "Taiwan", "Papua Niugini", "Hellas", "Argentina", "Nepal", "Japan", "México", "República Dominicana",
            "Solomon Islands", "South Georgia and the South Sandwich Islands", "Colombia", "Perú",
            "Antigua and Barbuda", "Zambia", "Guatemala", "Myanmar", "République démocratique du Congo",
            "New Zealand/Aotearoa", "Venezuela", "Mongolia ᠮᠤᠩᠭᠤᠯ ᠤᠯᠤᠰ", "Bolivia", "Russia",
            "Turkey", "Tajikistan", "France", "Brasil", "India", "Ecuador", "Tonga", "Viti", "Australia",
            "Panamá", "Belau", "Canada", "Ísland", "Afghanistan", "Pakistan", "འབྲུགཡུལ་", "Costa Rica",
            "Nicaragua", "British Indian Ocean Territory", "Albania", "Ethiopia", "Egypt", "Italia",
            "South Africa", "El Salvador", "Bosna i Hercegovina / Босна и Херцеговина", "Kyrgyzstan",
            "Oʻzbekiston", "Djibouti", "Kazakhstan", "România", "Thailand", "Madagascar",
            "Gabon", "Norge", "Cuba", "España", "Tanzania", "België / Belgique / Belgien", "Malaysia", "Serbia",
            "Montenegro", "ኤርትራ Eritrea إرتريا", "Algérie / ⵍⵣⵣⴰⵢⴻⵔ / الجزائر", "Việt Nam",
            "Laos", "Polska", "Honduras", "Iraq", "Türkmenistan",
            "Saint Kitts and Nevis", "Guinée", "Yemen", "Georgia", "Bangladesh",
            "Kalaallit Nunaat", "Malawi", "Portugal", "Kenya", "South Sudan", "Micronesia",
            "Bulgaria", "Uganda", "Croatia", "Maroc / ⵍⵎⵖⵔⵉⴱ / المغرب", "Jamaica", "السودان",
            "Trinidad and Tobago", "Հայաստան", "Северна Македонија", "Paraguay / Paraguái", "Κύπρος - Kıbrıs",
            "السعودية", "Dominica", "Azərbaycan", "سوريا", "Soomaaliya الصومال", "Slovenija", "Kosova / Kosovo",
            "Moçambique", "Ayiti" };

    // step 3: Make a stack from the collections, one for each country which stores
    // earthquake and its magnitude
    // in the order of the event (the most recent event on top).
    public Stack<Object>[] getCollectionStack() throws SQLException, ClassNotFoundException, IOException {
        Stack<Object>[] stack = new Stack[111];
        for (int i = 0; i < stack.length; i++)
            stack[i] = new Stack<>();
        int level = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    "collections.txt")));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i++) {
                    stack[level].push(values[i]);
                }
                level++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stack;
    }

    public int getCountryByIndex(String country) {
        for (int i = 0; i < countries.length; i++) {
            if (countries[i].equals(country)) {
                return i;
            }
        }
        return -1;
    }

    // used for problem 1.
    public void averageNumberOfEarthquakes() throws ClassNotFoundException, SQLException, IOException {
        Stack<Object>[] stack = getCollectionStack();
        for (int i = 0; i < countries.length; i++) {
            String country = countries[i];
            int level = getCountryByIndex(country);
            double size = stack[level].size();
            // total number of earthquakes of a country(size) divided by 52,
            // because there are total 52 years and we want earthquakes per year.
            System.out.println(country + " : " + size / 52 + " earthquakes per year.");
        }
    }

    // used for problem 1.
    public void mostVulnerable() throws SQLException, IOException, ClassNotFoundException {
        Stack<Object>[] stack = getCollectionStack();
        int max_size = stack[0].size();
        String str = "";
        for (int i = 1; i < stack.length; i++) {
            // comparing size of stacks of each country, then it will return the stack with
            // maximum size, vulnerable country.
            if (stack[i].size() > max_size) {
                max_size = stack[i].size();
                str = (String) stack[i].peek();
                str = str.substring(0, str.indexOf(":"));
            }
        }
        System.out.println(str + " is most vulnerable to earthquakes.");
    }

    // used for problem 3.
    public void recentEarthquakes() throws SQLException, IOException, ClassNotFoundException {
        Stack<Object>[] stack = getCollectionStack();
        for (int i = 0; i < countries.length; i++) {
            String country = countries[i]; // getting earthquakes of each country.
            int index = getCountryByIndex(country);
            Object[] array = stack[index].toArray();
            int num = array.length - 5; // to get the recent 5 earthquakes;
            if (num < 5) // if a country has earthquakes below 5, dont print that country.
                continue;
            System.out.println("Recent 5 earthquakes of " + countries[index] + " are: ");
            System.out.print("[");
            for (int j = array.length - 1; j >= num; j--)
                System.out.println(array[j]);
        }
        System.out.println("]");
    }

    // Step 4:Make a linked list which saves the one most recent earthquake with
    // magnitude and country name
    // from each country (use the stack from step 3).

    // used for problem 4.
    public void earthquake_above_6() throws SQLException, IOException, ClassNotFoundException {
        Stack<Object>[] stack = getCollectionStack();
        LinkedList<Object> List = new LinkedList<>();
        // storing the on most recent earthquake in a linkedlist of each country using
        // stack's peek() method.
        for (int i = 0; i < stack.length - 1; i++)
            List.add(stack[i].peek());
        for (int i = 0; i < List.size(); i++) {
            String str = List.get(i).toString();
            double magnitude = Double.parseDouble(str.substring(str.length() - 3));
            if (magnitude > 6.0d) { // because we want above 6 magnitude earthquakes
                System.out.println(List.get(i));
            }
        }
    }
}
