package org.example;

public class Vector3 {
    private double x;
    private double y;
    private double z;

    Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 add(Vector3 vector) {
        return new Vector3(x + vector.x, y + vector.y, z + vector.z);
    }

    public Vector3 subtract(Vector3 vector) {
        return new Vector3(x - vector.x, y - vector.y, z - vector.z);
    }

    public double dot(Vector3 vector) {
        return x * vector.x + y * vector.y + z * vector.z;
    }

    public Vector3 cross(Vector3 vector) {
        return new Vector3(y * vector.z - z * vector.y,
                           z * vector.x - x * vector.z,
                           x * vector.y - y * vector.x);
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 normalize() {
        double len = length();
        if (len == 0) return new Vector3(0, 0, 0);
        double scale = 1 / len;
        return new Vector3(x * scale,  y * scale,  z * scale);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
