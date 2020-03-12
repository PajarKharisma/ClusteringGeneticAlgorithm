package gui;

import geneticElement.ColorValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private ArrayList<Point> points;
    private ControlPanel controlPanel;
    private ArrayList<Integer> clusterIndex;
    private ArrayList<ColorValues> colorValues;

    public DrawPanel(){
        setLayout(null);
        setPreferredSize(new Dimension(640,480));

        clusterIndex = new ArrayList<>();
        colorValues = new ArrayList<>();
        points = new ArrayList<Point>();
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.add(new Point(e.getX(), e.getY()));
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int index = 0;
        for (Point point : points) {
            if(colorValues.isEmpty()){
                g2.setColor(new Color(0,0,0));
            }else{
                int red = colorValues.get(clusterIndex.get(index)-1).getR();
                int green = colorValues.get(clusterIndex.get(index)-1).getG();
                int blue = colorValues.get(clusterIndex.get(index)-1).getB();
                g2.setColor(new Color(red,green,blue));
            }
            g2.fillOval(point.x - 5, point.y - 5, 20, 20);
            index++;
        }
    }

    public void setOtherPanel(ControlPanel controlPanel){
        this.controlPanel = controlPanel;
    }

    public void setPoints(ArrayList<Point> points){
        this.points = points;
    }

    public ArrayList<Point> getPoints(){
        return this.points;
    }

    public ArrayList<ColorValues> getColorValues() {
        return colorValues;
    }

    public void setColorValues(ArrayList<ColorValues> colorValues) {
        this.colorValues = colorValues;
    }

    public ArrayList<Integer> getClusterIndex() {
        return clusterIndex;
    }

    public void setClusterIndex(ArrayList<Integer> clusterIndex) {
        this.clusterIndex = clusterIndex;
    }
}
