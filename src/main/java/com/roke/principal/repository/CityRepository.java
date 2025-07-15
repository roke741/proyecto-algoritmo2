/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.roke.principal.repository;

import com.roke.principal.model.City;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Jhordie
 */
public class CityRepository {

    private final List<City> cities;


    public CityRepository() {
        cities = new ArrayList<>();
        cities.add(new City(1, "Ciudad 1"));
        cities.add(new City(2, "Ciudad 2"));
        cities.add(new City(3, "Ciudad 3"));
        cities.add(new City(4, "Ciudad 4"));
        cities.add(new City(5, "Ciudad 5"));
        cities.add(new City(6, "Ciudad 6"));
        cities.add(new City(7, "Ciudad 7"));
        cities.add(new City(8, "Ciudad 8"));
    }

    public List<City> getCities() {
        return cities;
    }

    public City getById(int id) {
        if (id < 1 || id > cities.size()) {
            System.out.println("No existe ciudad con id=" + id);
            throw new NoSuchElementException("No existe ciudad con id=" + id);
        }
        return cities.get(id - 1);
    }
}


