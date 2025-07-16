package com.roke.principal.model;

import java.util.Objects;


public class City {
    // Atributo final: identificador único de la ciudad. No se puede modificar después de asignarse
    private final int id;
    // Nombre de la ciudad (sí puede modificarse después)
    private String name;

    
    // Constructor que inicializa la ciudad con un id y un nombre.    
    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    // Método equals: se considera que dos ciudades son iguales si tienen el mismo nombre.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City other = (City) o;
        return Objects.equals(name, other.name);
    }

    // Método toString: cuando se imprime un objeto City, se mostrará solo su nombre.
    @Override
    public String toString() {
        return name;
    }
}
