package geneticElement;

import java.awt.*;
import java.util.ArrayList;

public class Chromosome {

    private Double fitnessValue;
    private ArrayList<Integer> gen;

    public Chromosome(){
        gen = new ArrayList<>();
    }

    public Chromosome(ArrayList<Integer> gen){
        this.gen = gen;
    }

    public Double getFitnessValue() {
        return this.fitnessValue;
    }

    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public void addGen(int cluster){
        this.gen.add(cluster);
    }

    public ArrayList<Integer> getGen() {
        return gen;
    }

    public void setGen(ArrayList<Integer> gen) {
        this.gen = gen;
    }
}
