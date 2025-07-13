package com.mycompany.sistemaplanejago.componentes.graficoBarraHome;

import java.awt.Color;

public class Bar {
    private double value;
    private Color color;
    private String label; 

    public Bar(double value, Color color, String label) {
        this.value = value;
        this.color = color;
        this.label = label;
    }

    public double getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public String getLabel() {
        return label;
    }
}
