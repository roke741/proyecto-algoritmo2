
package com.roke.principal.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.roke.principal.examples.Rutas;

public class MainWindow extends javax.swing.JFrame {

    private JComboBox<Integer> endNodeComboBox;
    private JTextArea outputArea;
    private JButton calculateButton;

    private int numVertices = 8; // Nodos del 1 al 8
    private int startNode = 1; // Nodo de inicio fijo
    private List<Rutas.Ruta>[] grafo; // Grafo

    public MainWindow() {
        setTitle("Rutas Más Cortas desde el Nodo 1");
        setSize(700, 550); // Aumentar un poco el tamaño
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeGraph();
        initComponentss();
        initComponents();
    }

    private void initializeGraph() {
        // Inicializar la lista de adyacencia (índice 0 no usado, nodos 1 a numVertices)
        grafo = new ArrayList[numVertices + 1];
        for (int i = 0; i <= numVertices; i++) {
            grafo[i] = new ArrayList<>();
        }

        // Agregamos las aristas con sus pesos (tu grafo del código original)
        grafo[1].add(new Rutas.Ruta(2, 1));
        grafo[1].add(new Rutas.Ruta(3, 2));
        grafo[2].add(new Rutas.Ruta(3, 1));
        grafo[2].add(new Rutas.Ruta(4, 5));
        grafo[2].add(new Rutas.Ruta(5, 2));
        grafo[3].add(new Rutas.Ruta(4, 2));
        grafo[3].add(new Rutas.Ruta(5, 1));
        grafo[3].add(new Rutas.Ruta(6, 4));
        grafo[4].add(new Rutas.Ruta(5, 3));
        grafo[4].add(new Rutas.Ruta(7, 8));
        grafo[5].add(new Rutas.Ruta(6, 3));
        grafo[5].add(new Rutas.Ruta(7, 7));
        grafo[6].add(new Rutas.Ruta(7, 5));
        grafo[6].add(new Rutas.Ruta(8, 2));
        grafo[7].add(new Rutas.Ruta(8, 6));

        // Asegurarse de que el grafo sea no dirigido si es el caso
        // Si una arista A->B existe, también B->A con el mismo peso
        // Tu grafo original no lo especifica, pero para rutas más cortas suele ser bidireccional
        // Si es dirigido, elimina estas líneas
        addBidirectionalEdge(2, 1, 1);
        addBidirectionalEdge(3, 1, 2);
        addBidirectionalEdge(3, 2, 1);
        addBidirectionalEdge(4, 2, 5);
        addBidirectionalEdge(5, 2, 2);
        addBidirectionalEdge(4, 3, 2);
        addBidirectionalEdge(5, 3, 1);
        addBidirectionalEdge(6, 3, 4);
        addBidirectionalEdge(5, 4, 3);
        addBidirectionalEdge(7, 4, 8);
        addBidirectionalEdge(6, 5, 3);
        addBidirectionalEdge(7, 5, 7);
        addBidirectionalEdge(7, 6, 5);
        addBidirectionalEdge(8, 6, 2);
        addBidirectionalEdge(8, 7, 6);
    }

    // Método auxiliar para añadir aristas bidireccionales
    private void addBidirectionalEdge(int u, int v, int weight) {
        // Solo añadir si no existe ya para evitar duplicados si el grafo ya es bidireccional
        // Esto es una simplificación, una verificación más robusta podría ser necesaria
        boolean exists = false;
        for(Rutas.Ruta r : grafo[u]) {
            if (r.destino == v) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            grafo[u].add(new Rutas.Ruta(v, weight));
        }

        exists = false;
        for(Rutas.Ruta r : grafo[v]) {
            if (r.destino == u) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            grafo[v].add(new Rutas.Ruta(u, weight));
        }
    }


    private void initComponentss() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Panel de control para selección de nodos
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        mainPanel.add(controlPanel, BorderLayout.NORTH);

        controlPanel.add(new JLabel("Nodo de Inicio: " + startNode)); // Mostrar nodo de inicio fijo

        controlPanel.add(new JLabel("Seleccionar Nodo Destino:"));
        Integer[] nodes = new Integer[numVertices - 1]; // Nodos del 2 al 8
        for (int i = 0; i < numVertices - 1; i++) {
            nodes[i] = i + 2; // Empezar desde el nodo 2
        }
        endNodeComboBox = new JComboBox<>(nodes);
        endNodeComboBox.setSelectedItem(2); // Por defecto, nodo 2
        controlPanel.add(endNodeComboBox);

        calculateButton = new JButton("Calcular Ruta");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayPath();
            }
        });
        controlPanel.add(calculateButton);

        // Área de texto para mostrar resultados
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void calculateAndDisplayPath() {
        outputArea.setText(""); // Limpiar área de texto

        Integer endNode = (Integer) endNodeComboBox.getSelectedItem();

        if (endNode == null) {
            outputArea.setText("Por favor, seleccione un nodo destino.");
            return;
        }

        // Ejecutar tu algoritmo de Dijkstra
        Map<Integer, Rutas.PathResult> results =
                Rutas.findShortestPathsAndAllMinPaths(numVertices, grafo, startNode);

        Rutas.PathResult pathResult = results.get(endNode);

        outputArea.append("Rutas más cortas desde el Nodo " + startNode + " al Nodo " + endNode + ":\n\n");

        if (pathResult.distance == Integer.MAX_VALUE) {
            outputArea.append("  -> Nodo " + endNode + ": Inalcanzable\n");
        } else {
            outputArea.append("  -> Nodo " + endNode + ": Distancia = " + pathResult.distance + "\n");
            outputArea.append("     Caminos mínimos posibles:\n");
            for (List<Integer> camino : pathResult.paths) {
                for (int j = 0; j < camino.size(); j++) {
                    outputArea.append(String.valueOf(camino.get(j)));
                    if (j < camino.size() - 1) outputArea.append(" -> ");
                }
                outputArea.append("\n");
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 886, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
