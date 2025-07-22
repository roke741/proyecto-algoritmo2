package com.roke.principal.ui;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;

public class CalculadoraDistanciasUI extends JFrame {

    private JComboBox<String> comboInicio;
    private JComboBox<String> comboDestino;
    private JTextArea resultadoArea;

    public CalculadoraDistanciasUI() {
        setLayout(new MigLayout("wrap 4", "[grow][grow][grow][grow]", "[][]20[grow]20[grow]"));


        // Panel del grafo con flechas dirigidas
        GrafoPanel panelGrafo = new GrafoPanel();
        add(panelGrafo, "span, grow, pushy");
    }
}