package geneticProcess;

import geneticElement.Chromosome;

import java.util.Comparator;
import java.lang.*;

public class SortPopulasi implements Comparator<Chromosome> {

    @Override
    public int compare(Chromosome o1, Chromosome o2) {
        return o1.getFitnessValue().compareTo(o2.getFitnessValue());
    }
}
