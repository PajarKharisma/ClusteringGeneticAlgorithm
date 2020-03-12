package geneticElement;

import java.awt.*;
import java.util.Random;

public class ColorValues {
    private int r;
    private int g;
    private int b;

    public ColorValues(){
        Random random = new Random();
        this.r = random.nextInt(255);
        this.g = random.nextInt(255);
        this.b = random.nextInt(255);
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
