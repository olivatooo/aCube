package acube.acube.com;

import android.content.res.Resources;

/**
 * Created by bruce on 14-11-6.
 */
public class Utils {
    public static String temp;
    public static String lux;
    public static String humi;
    public static String som;
    public static int idade;
    public static int lux_ideal;
    public static int db_ideal;
    public static int ambiente;
    public static boolean help;

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return  dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp){
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}
