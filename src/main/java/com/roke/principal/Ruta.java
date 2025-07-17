/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal;

/**
 *
 * @author suemy
 */
public class Ruta {
    Refineria refineria;
    Tanque tanque;
    double costo;
    int cantidad;

    public Ruta(Refineria refineria, Tanque tanque, double costo) {
        this.refineria = refineria;
        this.tanque = tanque;
        this.costo = costo;
    }

    public double getCostoTotal() {
        return costo * cantidad;
    }
}

