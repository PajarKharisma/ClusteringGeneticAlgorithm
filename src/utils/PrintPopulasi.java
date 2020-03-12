package utils;

import geneticElement.Chromosome;

import java.awt.*;
import java.util.ArrayList;

public class PrintPopulasi {

    public void print(ArrayList<Chromosome> populasi, String title){
        int index = 1;

        System.out.println("=====================" + title + "=====================");
        for (Chromosome chromosome:populasi) {
            System.out.println("Populasi ke-" + index);
            System.out.println(chromosome.getGen());
            System.out.println(chromosome.getFitnessValue());
            System.out.println();
            index++;
        }
    }

    public String listToString(ArrayList<Chromosome> populasi, String title){
        String output = "";
        int index = 1;

        output += "=====================" + title + "=====================\n";
        for (Chromosome chromosome:populasi) {
            output += "Populasi ke-" + index + "\n";
            output += chromosome.getGen() + "\n";
            output += chromosome.getFitnessValue() + "\n\n";
            index++;
        }

        return output;
    }

    public String listToArrayVariable(ArrayList<Chromosome> populasi, String title, ArrayList<Point> points){
        String output = "";
        int index = 1;
        output += "=====================" + title + "=====================\n";
        for (Chromosome chromosome:populasi) {
            output += "Populasi ke-" + index + "\n";
            String temp = "";
            for(int i=0; i<chromosome.getGen().size(); i++) {
                temp += "(";
                temp += chromosome.getGen().get(i) + "|";
                temp += points.get(i).getX() + ",";
                temp += points.get(i).getY() + ")";
            }
            output += temp + "\n";
            index++;

        }

        return output;
    }
}
