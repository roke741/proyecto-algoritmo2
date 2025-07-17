/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal;

/**
 *
 * @author suemy
 */
import java.util.Comparator;

public class RutaComparator implements Comparator<Ruta> {
    public int compare(Ruta r1, Ruta r2) {
        return Double.compare(r1.costo, r2.costo);
    }
}

