package geneticProcess;

import geneticElement.Chromosome;
import geneticElement.HyperParameter;

import java.util.ArrayList;

public class NaturalSelection {

    public ArrayList<Chromosome> removeChromosome(ArrayList<Chromosome> population){
        while(population.size() > HyperParameter.POPULATION_SIZE){
            population.remove(population.size()-1);
        }

        return population;
    }
}
