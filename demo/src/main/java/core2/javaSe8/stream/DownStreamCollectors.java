package core2.javaSe8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/7/8 5:36 PM
 * @version 1.0
 */
public class DownStreamCollectors {
    public static class City{
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public String getState() {
            return state;
        }

        public int getPopulation() {
            return population;
        }
    }

    public static Stream<City> readCities(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName)).map(l -> l.split(",")).map(a->new City(a[0],a[1],Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales.collect(Collectors.groupingBy(Locale::getCountry,Collectors.toSet()));
        System.out.println("countryToLocaleSet: "+ countryToLocaleSet);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String,Long> countryToLocalCounts = locales.collect(Collectors.groupingBy(Locale::getCountry,Collectors.counting()));
        System.out.println("countryToLocalCounts: " + countryToLocalCounts);

        Stream<City> cities = readCities("cities.txt");
        Map<String,Integer> stateToCityPopulation = cities.collect(Collectors.groupingBy(City::getState,Collectors.summingInt(City::getPopulation)));
        System.out.println("stateToCityPopulation: " + stateToCityPopulation);
        cities = readCities("cities.txt");

        Map<String, Optional<String>> stateToLongestCityName = cities.collect(
                Collectors.groupingBy(
                        City::getState,Collectors.mapping(City::getName,Collectors.maxBy(Comparator.comparing(String::length))))
        );
        System.out.println("stateToLongestCityName:" + stateToLongestCityName);


        locales = Stream.of(Locale.getAvailableLocales());
        Map<String,Set<String>> countryToLanguages = locales.collect(
                Collectors.groupingBy(Locale::getCountry,Collectors.mapping(Locale::getLanguage,Collectors.toSet()))
        );
        System.out.println("countryToLanguages: " + countryToLanguages);

        cities = readCities("cities.txt");
        Map<String,IntSummaryStatistics> stateToCityPopulationSummary = cities.collect(Collectors.groupingBy(
           City::getState,Collectors.summarizingInt(City::getPopulation)
        ));
        System.out.println(stateToCityPopulationSummary.get("NY"));

        cities = readCities("cities.txt");
        Map<String,String> stateToCityNames = cities.collect(Collectors.groupingBy(
                City::getState,Collectors.reducing("",City::getName,(s,t) -> s.length() == 0 ? t : s + ", " +t)
        ));
        System.out.println("stateToCityNames: " + stateToCityNames);

        cities = readCities("cities.txt");
        stateToCityNames = cities.collect(Collectors.groupingBy(
                City::getState,
                Collectors.mapping(City::getName, Collectors.joining(", "))
        ));
        System.out.println("stateToCityNames: " + stateToCityNames);




    }
}
