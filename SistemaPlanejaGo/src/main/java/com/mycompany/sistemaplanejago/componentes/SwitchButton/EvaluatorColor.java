package com.mycompany.sistemaplanejago.componentes.SwitchButton;

import java.awt.Color;

public class EvaluatorColor {

    public static Color evaluate(Color color1, Color color2, float fraction) {
        int r = (int) (color1.getRed() + ((color2.getRed() - color1.getRed()) * fraction));
        int g = (int) (color1.getGreen() + ((color2.getGreen() - color1.getGreen()) * fraction));
        int b = (int) (color1.getBlue() + ((color2.getBlue() - color1.getBlue()) * fraction));
        int a = (int) (color1.getAlpha() + ((color2.getAlpha() - color1.getAlpha()) * fraction));
        return new Color(r, g, b, a);
    }
}