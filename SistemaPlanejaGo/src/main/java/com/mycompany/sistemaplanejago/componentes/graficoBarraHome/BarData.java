package com.mycompany.sistemaplanejago.componentes.graficoBarraHome;

import java.util.ArrayList;
import java.util.List;

public class BarData {
    private List<Bar> bars;

    public BarData() {
        this.bars = new ArrayList<>();
    }

    public void addBar(Bar bar) {
        this.bars.add(bar);
    }

    public List<Bar> getBars() {
        return bars;
    }
}
