/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.roke.principal.model;

/**
 *
 * @author Jhordie
 */
public class Route {
    private City origin;
    private City destination;
    private double distance;

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
