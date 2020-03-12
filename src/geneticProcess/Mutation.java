package geneticProcess;

import geneticElement.Chromosome;

import java.util.ArrayList;
import java.util.Random;

public class Mutation {

    private Validating validating = new Validating();

    public ArrayList<Chromosome> replacement(ArrayList<Chromosome> offsprings, ArrayList<Chromosome> populasi, int clusterAmount){
        Random random = new Random();
        int chromosomeLength = populasi.get(0).getGen().size();
        for (Chromosome offspring:offsprings) {
            do{
                int index = random.nextInt(chromosomeLength);
                int value = random.nextInt(clusterAmount) + 1;
                offspring.getGen().set(index, value);
            }while (validating.isExist(offspring.getGen(), populasi));
        }
        return offsprings;
    }

    public ArrayList<Chromosome> swap(ArrayList<Chromosome> offsprings, ArrayList<Chromosome> populasi, int clusterAmount){
        Random random = new Random();
        int chromosomeLength = populasi.get(0).getGen().size();
        for (Chromosome offspring:offsprings) {
            int index1 = random.nextInt(chromosomeLength);
            int index2 = random.nextInt(chromosomeLength);
            while (index1 == index2){
                index2 = random.nextInt(chromosomeLength);
            }

            int val1 = offspring.getGen().get(index1);
            int val2 = offspring.getGen().get(index2);

            offspring.getGen().set(index1, val1);
            offspring.getGen().set(index2, val2);
            /*do{

            }while (validating.isExist(offspring.getGen(), populasi));*/
        }
        return offsprings;
    }
}
