package Models;

public class FillPolyEdge {
    private static final double THRESHOLD = 1;
    private Vertice init;
    private Vertice end;
    private double dx;
    private double dy;
    private double x;
    private int y;
    private double dxByDy;

    public FillPolyEdge(Vertice start, Vertice end) {
        if (start.Y > end.Y) {
            initialize(end, start);
        } else {
            initialize(start, end);
        }
    }

    public boolean hit(int y) {
        return init.Y <= y && y < end.Y;
    }

    private void initialize(Vertice lower, Vertice higher) {
        init = new Vertice(lower.X, lower.Y);
        end = new Vertice(higher.X, higher.Y);
        dx = higher.X - lower.X;
        dy = higher.Y - lower.Y;
        dxByDy = dx / dy;
        y = (int) Math.ceil(init.Y);
        x = getX(y);
    }

    public static boolean isValid(Vertice start, Vertice end) {
        return Math.abs(start.Y - end.Y) > THRESHOLD;
    }

    public double x() {
        return x;
    }

    public boolean next() {
        x += dxByDy;
        y++;
        return y < end.Y;
    }

    private double getX(int y) {
        return init.X + (dx * (y - init.Y) / dy);
    }

    public Vertice p1() {
        return new Vertice(init.X, init.Y);
    }

    public Vertice p2() {
        return new Vertice(end.X, end.Y);
    }
}
