
package com.roke.principal.ui;

import javax.swing.*;

import java.util.List;
import java.util.Map;

import com.roke.principal.examples.Rutas;
import com.roke.principal.model.City;
import com.roke.principal.repository.CityRepository;
import com.roke.principal.repository.RouteRepository;
import com.roke.principal.service.GraphService;

public class MainWindow extends javax.swing.JFrame {

    private final CityRepository cityRepo;
    private final RouteRepository routeRepo;
    private final GraphService graphService;
    private final List<City> cities;

    public MainWindow() {
        initComponents();
        //DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        // 1) Inicializamos repositorios y servicio de grafo
        cityRepo      = new CityRepository();
        routeRepo     = new RouteRepository(cityRepo);
        graphService  = new GraphService(cityRepo, routeRepo);

        // 2) Obtenemos la lista de ciudades
        cities = cityRepo.getCities();

        // 3) Creamos dos modelos idénticos para partida y destino
        DefaultComboBoxModel<City> modelPartida = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<City> modelDestino = new DefaultComboBoxModel<>();
        for (City c : cities) {
            modelPartida.addElement(c);
            modelDestino.addElement(c);
        }
        cmbPartida .setModel(modelPartida);
        cmbDestino .setModel(modelDestino);

        setTitle("Ejercicio 2 - Grupo 3 - UTP - Ingeniería de Sistemas");
    }

    private void calculateAndDisplayPath() {
        txtResultado.setText("");

        // 4) Sacamos las ciudades seleccionadas
        City startCity = (City) cmbPartida.getSelectedItem();
        City endCity   = (City) cmbDestino .getSelectedItem();

        if (startCity == null || endCity == null) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, seleccione ciudad de partida y destino.",
                    "Error de selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int startId = startCity.getId();
        int endId   = endCity  .getId();

        // 5) Calculamos distancia y ruta
        double distance = graphService.shortestDistance(startId, endId);
        if (Double.isInfinite(distance)) {
            txtResultado.append(
                    "No existe camino desde " +
                            startCity.getName() + " hasta " +
                            endCity  .getName() + ".\n"
            );
            return;
        }

        List<City> path = graphService.shortestPath(startId, endId);

        // 6) Mostramos el resultado
        txtResultado.append(
                "Ruta más corta de " +
                        startCity.getName() + " → " +
                        endCity  .getName() + ":\n"
        );
        txtResultado.append("Distancia = " + distance + " millas\n");
        txtResultado.append("Camino: ");
        for (int i = 0; i < path.size(); i++) {
            txtResultado.append(path.get(i).getName());
            if (i < path.size() - 1) txtResultado.append(" → ");
        }
        txtResultado.append("\n");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbPartida = new JComboBox<City>();
        jLabel2 = new javax.swing.JLabel();
        cmbDestino = new JComboBox<City>();
        btnCalcularRuta = new javax.swing.JButton();
        lblGrafoImagen = new javax.swing.JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        txtResultado = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ciudad de destino:");


        jLabel2.setText("Ciudad de partida:");


        btnCalcularRuta.setText("Calcular ruta");
        btnCalcularRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularRutaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCalcularRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCalcularRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        lblGrafoImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg-grafos.png"))); // NOI18N

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane1.setViewportView(txtResultado);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblGrafoImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jScrollPane1)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(lblGrafoImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalcularRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularRutaActionPerformed
        // TODO add your handling code here:
        calculateAndDisplayPath();
    }//GEN-LAST:event_btnCalcularRutaActionPerformed

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcularRuta;
    private JComboBox<City> cmbDestino;
    private JComboBox<City> cmbPartida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblGrafoImagen;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
