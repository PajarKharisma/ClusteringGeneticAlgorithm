package geneticProcess;

import geneticElement.Chromosome;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Crossover {

    private Validating validating = new Validating();

    public Crossover() {
    }

    public ArrayList<Chromosome> getBestParent(ArrayList<Chromosome> populasi){
        populasi.sort(new SortPopulasi());
        ArrayList<Chromosome> parent = new ArrayList<>();
        parent.add(populasi.get(0));
        parent.add(populasi.get(1));

        return parent;
    }

    public ArrayList<Chromosome> getRandomParent(ArrayList<Chromosome> populasi){
        Random random = new Random();
        int n = populasi.get(0).getGen().size();
        int n1 = random.nextInt(n);
        int n2 = random.nextInt(n);
        while (n1 == n2){
            n2 = random.nextInt(n);
        }

        ArrayList<Chromosome> parent = new ArrayList<>();
        parent.add(populasi.get(n1));
        parent.add(populasi.get(n2));

        return parent;
    }

    public ArrayList<Chromosome> uniform(ArrayList<Chromosome> parent, ArrayList<Point> points, int clusterAmount){
        Random random = new Random();
        ArrayList<Chromosome> offsprings = new ArrayList<>();
        FitnessValueCalculate fitnessValueCalculate = new FitnessValueCalculate();

        int threshold = random.nextInt(100);
        int[] val = new int[points.size()];
        for (int i=0; i<val.length; i++){
            val[i] = random.nextInt(100);
        }

        Chromosome offspring = new Chromosome();
        for (int i=0; i<val.length; i++){
            if(val[i] < threshold){
                offspring.addGen(parent.get(0).getGen().get(i));
            } else {
                offspring.addGen(parent.get(1).getGen().get(i));
            }
        }

        if(validating.isValid(offspring.getGen())){
            double fv = fitnessValueCalculate.getFitnessValue(points, offspring.getGen(), clusterAmount);
            offspring.setFitnessValue(fv);
            offsprings.add(offspring);
        }

        return offsprings;
    }

    public ArrayList<Chromosome> onePoint(ArrayList<Chromosome> parent, ArrayList<Point> points, int clusterAmount){
        Random random = new Random();
        FitnessValueCalculate fitnessValueCalculate = new FitnessValueCalculate();

        int first = 0;
        int last = parent.get(0).getGen().size() - 1;
        int mid = random.nextInt((last - 2) + 1) + 1;

        ArrayList<Integer> firstRange = this.getElementByRange(parent.get(0).getGen(), first, mid);
        ArrayList<Integer> lastRange = this.getElementByRange(parent.get(1).getGen(), mid+1, last);

        Chromosome[] offspring = new Chromosome[2];
        for (int i=0; i<2; i++){
            offspring[i] = new Chromosome();
            switch (i){
                case 0:
                    offspring[i].getGen().addAll(firstRange);
                    offspring[i].getGen().addAll(lastRange);
                    break;
                case 1:
                    offspring[i].getGen().addAll(lastRange);
                    offspring[i].getGen().addAll(firstRange);
                    break;
            }
        }

        ArrayList<Chromosome> offsprings = new ArrayList<>();
        for(int i=0; i<2; i++){
            if(validating.isValid(offspring[i].getGen())){
                double val = fitnessValueCalculate.getFitnessValue(points, offspring[i].getGen(), clusterAmount);
                offspring[i].setFitnessValue(val);
                offsprings.add(offspring[i]);
            }
        }

        return offsprings;
    }

    private ArrayList<Integer> getElementByRange(ArrayList<Integer> list, int first, int last){
        ArrayList<Integer> newList = new ArrayList<>();
        for (int i=first; i<=last; i++){
            newList.add(list.get(i));
        }

        return newList;
    }
}
