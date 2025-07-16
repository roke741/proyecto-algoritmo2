package com.roke.principal.repository;

import com.roke.principal.model.City;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// Repositorio que gestiona una lista de ciudades
public class CityRepository {

    // Lista que almacena las ciudades
    private final List<City> cities;

    // Constructor que inicializa la lista con 8 ciudades predeterminadas
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

    // Devuelve la lista completa de ciudades
    public List<City> getCities() {
        return cities;
    }


    // Devuelve una ciudad según su ID
    public City getById(int id) {
        // Verifica que el ID esté dentro del rango válido
        if (id < 1 || id > cities.size()) {
            // Muestra mensaje por consola y lanza una excepción si el ID no es válido
            System.out.println("No existe ciudad con id=" + id);
            throw new NoSuchElementException("No existe ciudad con id=" + id);
        }
        
        // Retorna la ciudad correspondiente (índice = id - 1 porque la lista es 0-based)
        return cities.get(id - 1);
    }
}


