/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkgfinal;

/**
 *
 * @author suemy
 */
public class ArbolRutas {
    class Nodo {
        Ruta ruta;
        Nodo izq, der;

        Nodo(Ruta ruta) {
            this.ruta = ruta;
        }
    }

    Nodo raiz;

    public void insertar(Ruta ruta) {
        raiz = insertarRec(raiz, ruta);
    }

    private Nodo insertarRec(Nodo nodo, Ruta ruta) {
        if (nodo == null) return new Nodo(ruta);
        if (ruta.getCostoTotal() < nodo.ruta.getCostoTotal()) nodo.izq = insertarRec(nodo.izq, ruta);
        else nodo.der = insertarRec(nodo.der, ruta);
        return nodo;
    }

    public void inOrden() {
        inOrdenRec(raiz);
    }

    private void inOrdenRec(Nodo nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izq);
            System.out.println(nodo.ruta.refineria.nombre + " -> " + nodo.ruta.tanque.ciudad + 
                " | Barriles: " + nodo.ruta.cantidad + 
                " | Costo: $" + nodo.ruta.getCostoTotal());
            inOrdenRec(nodo.der);
        }
    }
}

