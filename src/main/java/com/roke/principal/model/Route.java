package com.roke.principal.model;

// Clase que representa una ruta entre dos ciudades
public class Route {
    private City origin;
    private City destination;
    private double distance;

    // Constructor que inicializa la ruta con su origen, destino y distancia
    public Route(City origin, City destination, double distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    } 
}
