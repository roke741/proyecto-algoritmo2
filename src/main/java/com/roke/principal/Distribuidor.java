/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal;
import java.util.*;

/**
 *
 * @author suemy
 */
public class Distribuidor {
    List<Refineria> refinerias = new ArrayList<>();
    List<Tanque> tanques = new ArrayList<>();
    PriorityQueue<Ruta> rutas = new PriorityQueue<>(new RutaComparator());
    ArbolRutas arbol = new ArbolRutas();

    public void inicializar() {
        Refineria nuevaOrleans = new Refineria("Nueva Orleans", 300000);
        Refineria newark = new Refineria("Newark", 500000);
        refinerias.add(nuevaOrleans);
        refinerias.add(newark);

        tanques.add(new Tanque("Washington", 200000));
        tanques.add(new Tanque("Tampa", 100000));
        tanques.add(new Tanque("Atlanta", 400000));
        tanques.add(new Tanque("Cincinnati", 300000));

        double[][] costos = {
            {0.10, 0.05, 0.07, 0.09},  // Nueva Orleans
            {0.05, 0.11, 0.08, 0.07}   // Newark
        };

        for (int i = 0; i < refinerias.size(); i++) {
            for (int j = 0; j < tanques.size(); j++) {
                rutas.offer(new Ruta(refinerias.get(i), tanques.get(j), costos[i][j]));
            }
        }
    }

    public void resolver() {
        while (!rutas.isEmpty()) {
            Ruta ruta = rutas.poll();
            int disponible = ruta.refineria.capacidad;
            int requerido = ruta.tanque.demanda;

            if (disponible == 0 || requerido == 0) continue;

            int enviar = Math.min(disponible, requerido);
            ruta.cantidad = enviar;

            ruta.refineria.capacidad -= enviar;
            ruta.tanque.demanda -= enviar;

            arbol.insertar(ruta);
        }

        System.out.println("\n Plan de distribucion Optimo:");
        arbol.inOrden();
    }
}
