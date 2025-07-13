package com.mycompany.sistemaplanejago.componentes.graficoLinhaHome;

import java.awt.Color;   
import java.util.List;

public class Line {
    private String name; 
    private Color color; 
    private List<LineData> dataPoints; 

    public Line(String name, Color color, List<LineData> dataPoints) {
        this.name = name;
        this.color = color;
        this.dataPoints = dataPoints;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public List<LineData> getDataPoints() {
        return dataPoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setDataPoints(List<LineData> dataPoints) {
        this.dataPoints = dataPoints;
    }
}
