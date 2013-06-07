/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.knights.games.bubblepopper.graphics;

import javafx.scene.Node;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

/**
 *
 * @author hope
 */
public class VisualEffects {

    private static double azimuth = 125;
    private static double elevation = 65;
    private static Color lightColor = Color.WHITE;
    private static Light light;
    private static Lighting lightSystem;

    public static void setLighting(Node n) {
        light = new Light.Distant(azimuth, elevation, lightColor);
        lightSystem = new Lighting(light);
        n.setEffect(lightSystem);
    }

    public static void setAzimuth(double azimuth) {
        VisualEffects.azimuth = azimuth;
    }

    public static void setElevation(double elevation) {
        VisualEffects.elevation = elevation;
    }

    public static void setLightColor(Color lightColor) {
        VisualEffects.lightColor = lightColor;
    }

    public static void setLight(Light light) {
        VisualEffects.light = light;
    }

    public static void setLightSystem(Lighting lightSystem) {
        VisualEffects.lightSystem = lightSystem;
    }
}
