package com.mycompany.sistemaplanejago.componentes.graficoLinhaHome;

import java.math.BigDecimal;

public class LineData {
   private String label; 
    private BigDecimal value; 

    public LineData(String label, BigDecimal value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    } 
}
