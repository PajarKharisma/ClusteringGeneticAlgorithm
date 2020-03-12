package geneticProcess;

import java.awt.*;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FitnessValueCalculate {

    private Point getCentroid(ArrayList<Point> points){
        int x = 0;
        int y = 0;
        for (Point point:points) {
            x += point.getX();
            y += point.getY();
        }

        int size = points.size() == 0 ? 1 : points.size();
        return new Point(x/size, y/size);
    }

    private double getEuclideanDistance(Point centroid, Point member){
        double result = Math.pow(centroid.getX() - member.getX(), 2) + Math.pow(centroid.getY() - member.getY(), 2);
        return Math.sqrt(result);
    }

    public double getFitnessValue(ArrayList<Point> points, ArrayList<Integer> gen, int clusterAmount){
        double result = 0;
        for(int i=1; i<=clusterAmount; i++){
            ArrayList<Point> cmember = new ArrayList<>();
            for(int j=0; j<points.size(); j++){
                if (gen.get(j) == i){
                    cmember.add(points.get(j));
                }
            }
            Point centroid = this.getCentroid(cmember);
            for (Point point:cmember) {
                result += this.getEuclideanDistance(centroid, point);
            }
        }

        return result;
    }
}
