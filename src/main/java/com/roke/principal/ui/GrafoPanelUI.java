package com.roke.principal.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class GrafoPanelUI extends javax.swing.JPanel {

    // Coordenadas fijas para los nodos
    private final int[][] posiciones = {
        {100, 180}, // Ciudad 1
        {160, 280}, // Ciudad 2
        {150, 100}, // Ciudad 3
        {290, 280}, // Ciudad 4
        {290, 180}, // Ciudad 5
        {420, 100}, // Ciudad 6
        {400, 280}, // Ciudad 7
        {500, 180} // Ciudad 8
    };

    // Aristas: origen, destino, peso
    private final int[][] aristas = {
        {0, 1, 1}, {0, 2, 2},
        {1, 3, 5}, {1, 4, 2}, {1, 2, 1},
        {2, 3, 2}, {2, 4, 1}, {2, 5, 4},
        {3, 4, 3}, {3, 6, 8}, {3, 5, 6},
        {4, 5, 3}, {4, 6, 7},
        {5, 6, 5}, {5, 7, 2},
        {6, 7, 6},};

    public GrafoPanelUI() {
        initComponents();
        setPreferredSize(new Dimension(1000,400));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Aristas con flechas y pesos
        for (int[] a : aristas) {
            int x1 = posiciones[a[0]][0];
            int y1 = posiciones[a[0]][1];
            int x2 = posiciones[a[1]][0];
            int y2 = posiciones[a[1]][1];
            dibujarFlecha(g2, x1, y1, x2, y2);

            // Mostrar el peso en el medio
            int midX = (x1 + x2) / 2;
            int midY = (y1 + y2) / 2;
            g2.setColor(Color.BLUE);
            g2.drawString(String.valueOf(a[2]), midX + 5, midY - 5);
            g2.setColor(Color.BLACK);
        }

        // Nodos
        for (int i = 0; i < posiciones.length; i++) {
            int x = posiciones[i][0];
            int y = posiciones[i][1];

            g2.setColor(Color.LIGHT_GRAY);
            g2.fillOval(x - 25, y - 25, 30, 30);

            g2.setColor(Color.BLACK);
            g2.drawOval(x - 25, y - 25, 30, 30);
            
            g2.setFont(new Font("Arial", Font.BOLD, 14));
            g2.drawString("Ciudad " + (i + 1), x - 25, y - 20);
        }
    }

    private void dibujarFlecha(Graphics2D g2, int x1, int y1, int x2, int y2) {
        // Calcular dirección
        double dx = x2 - x1;
        double dy = y2 - y1;
        double angle = Math.atan2(dy, dx);

        // Restar radio del nodo (15px) para que no se meta dentro del círculo
        int offset = 25;
        int nx1 = (int) (x1 + offset * Math.cos(angle));
        int ny1 = (int) (y1 + offset * Math.sin(angle));
        int nx2 = (int) (x2 - offset * Math.cos(angle));
        int ny2 = (int) (y2 - offset * Math.sin(angle));

        // Línea de la arista
        g2.drawLine(nx1, ny1, nx2, ny2);

        // Flechita
        int arrowSize = 10;
        int xArrow1 = (int) (nx2 - arrowSize * Math.cos(angle - Math.PI / 6));
        int yArrow1 = (int) (ny2 - arrowSize * Math.sin(angle - Math.PI / 6));
        int xArrow2 = (int) (nx2 - arrowSize * Math.cos(angle + Math.PI / 6));
        int yArrow2 = (int) (ny2 - arrowSize * Math.sin(angle + Math.PI / 6));

        int[] xPoints = {nx2, xArrow1, xArrow2};
        int[] yPoints = {ny2, yArrow1, yArrow2};
        g2.fillPolygon(xPoints, yPoints, 3);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
