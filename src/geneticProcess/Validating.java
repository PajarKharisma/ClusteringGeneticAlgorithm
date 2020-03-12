package geneticProcess;

import geneticElement.Chromosome;

import java.util.ArrayList;

public class Validating {

    public boolean isValid(ArrayList<Integer> chromose){
        int val = chromose.get(0);
        for (Integer i:chromose) {
            if(i != val){
                return true;
            }
        }
        return false;
    }

    public boolean isExist(ArrayList<Integer> chromosome, ArrayList<Chromosome> populasi){
        for (Chromosome c:populasi) {
            if (chromosome.equals(c.getGen())){
                return true;
            }
        }
        return false;
    }

    public boolean isSame(ArrayList<Integer> list1, ArrayList<Integer> list2){
        return list1.equals(list2);
    }
}
