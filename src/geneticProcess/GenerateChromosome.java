package geneticProcess;

import geneticElement.HyperParameter;
import geneticProcess.Validating;
import geneticElement.Chromosome;
import org.omg.CORBA.INTERNAL;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GenerateChromosome {

    private ArrayList<Chromosome> populasi;
    private Validating validating = new Validating();
    ExecutorService exec;

    public GenerateChromosome() {
        populasi = new ArrayList<>();
    }

    public void generate(ArrayList<Point> coordinate, int clusterAmount) {
        Random random = new Random();
        exec = Executors.newFixedThreadPool(4);

        while (populasi.size() < HyperParameter.POPULATION_SIZE) {
            ArrayList<Integer> chromose = new ArrayList<>();
            for (int j = 0; j < coordinate.size(); j++) {
                int cluster = random.nextInt((clusterAmount - 1) + 1) + 1;
                chromose.add(cluster);
            }
            if (validating.isValid(chromose) && !validating.isExist(chromose, this.populasi)) {
                populasi.add(new Chromosome(chromose));
            }
        }
    }

    public ArrayList<Chromosome> generateParalel(ArrayList<Point> coordinateInput, int clusterAmountInput){
        Random random = new Random();
        exec = Executors.newFixedThreadPool(4);

        final ArrayList<Point>[] coordinate = new ArrayList[1];
        coordinate[0] = coordinateInput;

        final int[] clusterAmount = {clusterAmountInput};

        while (populasi.size() < HyperParameter.POPULATION_SIZE) {
            ArrayList<Integer> chromose = new ArrayList<>();
            for (int j = 0; j < coordinate[0].size(); j++) {
                int cluster = random.nextInt((clusterAmount[0] - 1) + 1) + 1;
                chromose.add(cluster);
            }
            if (validating.isValid(chromose) && !validating.isExist(chromose, this.populasi)) {
                populasi.add(new Chromosome(chromose));
            }
        }
        return null;
    }

    public ArrayList<Chromosome> getPopulasi() {
        return this.populasi;
    }
}

class Generate implements Runnable{

    @Override
    public void run() {

    }
}
