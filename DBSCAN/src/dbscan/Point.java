package dbscan;

public class Point {

    private double x;

    private double y;

    public Point(double a, double b) {
        x = a;
        y = b;
    }

    public double getX() {return x;}

    public double getY() {return y;}
    
    @Override
    public boolean equals(Object  o) {
        Point p=(Point) o;
        return x==p.getX() && y==p.getY();
}

} 