/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.knights.games.bubblepopper;

import java.util.Random;

/**
 *
 * @author hope
 */
public class Utils {

    public static int MENU = 0;
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;
    public static final int LEVEL_4 = 4;
    public static final int LEVEL_5 = 5;
    public static final int LEVEL_6 = 6;
    public static final int LEVEL_7 = 7;
    public static final int LEVEL_8 = 8;
    public static String TITLE = "Bubble Popper";
    private static Random r = new Random();
    public static double WIDTH;
    public static double HEIGHT;

    public static double getRandomDouble() {
        return r.nextDouble();
    }

    public static int getRandomInt(int max) {
        return r.nextInt(max);
    }
    
    public static int getRandomInt() {
        return r.nextInt();
    }
    
    public static boolean getRandomBoolean() {
        return r.nextBoolean();
    }
}
