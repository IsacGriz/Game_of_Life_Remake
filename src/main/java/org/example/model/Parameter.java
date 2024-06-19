package org.example.model;

public class Parameter {

    private static int width;
    private static int height;
    private static int generations;
    private static int speed;
    private static String population;

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Parameter.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Parameter.height = height;
    }

    public static int getGenerations() {
        return generations;
    }

    public static void setGenerations(int generations) {
        Parameter.generations = generations;
    }

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Parameter.speed = speed;
    }

    public static String getPopulation() {
        return population;
    }

    public static void setPopulation(String population) {
        Parameter.population = population;
    }

    @Override
    public String toString() {
        return "Parameter [width=" + width + ", height=" + height + ", generations=" + generations
                + ", speed=" + speed + ", population=" + population + "]";
    }
}
