/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.jozefkis.autocompletecb.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author SOFIJA
 */
public class CountryUtility
{

    private CountryUtility()
    {

    }

    public static List<Country> getCountries()
    {
        List<Country> countries = new ArrayList<>(Arrays.asList(
                new Country("Argentina", "Buenos Aires", "C1000"),
                new Country("Australia", "Canberra", "AU-2601"),
                new Country("Austria", "Vienna", "1010"),
                new Country("Bosnia and Herzegovina", "Sarajevo", "71000"),
                new Country("Brazil", "Brasília", "70000-000"),
                new Country("Canada", "Ottawa", "K1A 0A6"),
                new Country("Croatia", "Zagreb", "10000"),
                new Country("France", "Paris", "FR-75001"),
                new Country("Germany", "Berlin", "10115"),
                new Country("Greece", "Athens", "GR-10557"),
                new Country("Hungary", "Budapest", "1051"),
                new Country("Italy", "Rome", "IT-00118"),
                new Country("Japan", "Tokyo", "100-0001"),
                new Country("Mexico", "Mexico City", "06000"),
                new Country("Netherlands", "Amsterdam", "1000 AZ"),
                new Country("Norway", "Oslo", "0035"),
                new Country("Serbia", "Belgrade", "11000"),
                new Country("Spain", "Madrid", "ES-28001"),
                new Country("United Kingdom", "London", "SW1A 0AA"),
                new Country("United States", "Washington, D.C.", "US-20001")
        ));

        return countries;
    }
}
