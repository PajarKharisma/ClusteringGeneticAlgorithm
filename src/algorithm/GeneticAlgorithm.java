package algorithm;

import geneticElement.Chromosome;
import geneticElement.ColorValues;
import geneticElement.HyperParameter;
import geneticProcess.*;
import utils.PrintPopulasi;
import utils.WriteTxt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GeneticAlgorithm {

    private int selection;
    private int crossover;
    private int mutation;
    private int representation;

    private String selectionData;
    private String crossoverData;
    private String mutationData;
    private String representationData;
    private boolean isPrint;

    public void setData(String selectionData, String crossoverData, String mutationData, String representationData, boolean isPrint){
        this.selectionData = selectionData;
        this.crossoverData = crossoverData;
        this.mutationData = mutationData;
        this.representationData = representationData;
        this.isPrint = isPrint;
    }

    public void setVariable(int selection, int crossover, int mutation, int representation){
        this.selection = selection;
        this.crossover = crossover;
        this.mutation = mutation;
        this.representation = representation;
    }

    public ArrayList<Integer> process(ArrayList<Point> points, int clusterAmount){
        WriteTxt writeTxt = new WriteTxt();
        String log = "";
        log += "Selection : " + this.selectionData + "\n";
        log += "Crossover : " + this.crossoverData + "\n";
        log += "Mutation : " + this.mutationData + "\n";
        log += "Representation : " + this.representationData + "\n";
        log += "=================================================================\n";

        GenerateChromosome generateChromosome = new GenerateChromosome();
        Crossover crossover = new Crossover();
        NaturalSelection naturalSelection = new NaturalSelection();
        PrintPopulasi printPopulasi = new PrintPopulasi();
        Validating validating = new Validating();
        Mutation mutation = new Mutation();

        generateChromosome.generate(points, clusterAmount);

        ArrayList<Chromosome> populasi = generateChromosome.getPopulasi();
        ArrayList<Integer> gen;

        int iterasi = 0;
        int convergen = 0;
        while (convergen < HyperParameter.CONVERGEN){
            for (Chromosome chromosome:populasi) {
                FitnessValueCalculate fitnessValueCalculate = new FitnessValueCalculate();
                double val = fitnessValueCalculate.getFitnessValue(points, chromosome.getGen(), clusterAmount);
                chromosome.setFitnessValue(val);
            }

            gen = populasi.get(0).getGen();

//            log += printPopulasi.listToString(populasi, "POPULASI AWAL") + "\n";
//            printPopulasi.print(populasi, "POPULASI AWAL");

            ArrayList<Chromosome> parent = new ArrayList<>();
            switch (this.selection){
                case 0 :
                    parent = crossover.getBestParent(populasi);
                    break;
                case 1:
                    parent = crossover.getRandomParent(populasi);
                    break;

            }

            if (this.isPrint){
                switch (this.representation){
                    case 0:
                        log += printPopulasi.listToString(parent, "PARENT") + "\n";
                        break;
                    case 1:
                        log += printPopulasi.listToArrayVariable(parent, "PARENT", points);

                }
            }
//            printPopulasi.print(parent, "PARENT");

            ArrayList<Chromosome> offsprings = new ArrayList<>();
            switch (this.crossover){
                case 0:
                    offsprings =crossover.onePoint(parent, points, clusterAmount);
                    break;
                case 1:
                    offsprings = crossover.uniform(parent, points, clusterAmount);
                    break;
            }

            if (this.isPrint){
                switch (this.representation){
                    case 0:
                        log += printPopulasi.listToString(offsprings, "OFFSPRINGS") + "\n";
                        break;
                    case 1:
                        log += printPopulasi.listToArrayVariable(offsprings, "OFFSPRINGS", points);
                        break;

                }
            }
//            printPopulasi.print(offsprings, "OFFSPRINGS");

            switch (this.mutation){
                case 0:
                    offsprings = mutation.replacement(offsprings, populasi, clusterAmount);
                    break;
                case 1:
                    offsprings = mutation.swap(offsprings, populasi, clusterAmount);
                    break;
                case 2:
                    offsprings = mutation.replacement(offsprings, populasi, clusterAmount);
                    offsprings = mutation.swap(offsprings, populasi, clusterAmount);
                    break;
            }

            populasi.addAll(offsprings);
            populasi.sort(new SortPopulasi());

//            log += printPopulasi.listToString(populasi, "POPULASI BARU");
//            printPopulasi.print(populasi, "POPULASI BARU");

            populasi = naturalSelection.removeChromosome(populasi);

//            printPopulasi.print(populasi, "SETELAH NATURAL SELECTION");

            if (validating.isSame(gen, populasi.get(0).getGen())){
                convergen++;
            } else {
                gen = populasi.get(0).getGen();
                convergen = 0;
            }
            iterasi++;
        }

//        printPopulasi.print(populasi, "populasi terbaik");

        writeTxt.writeFile(log, "log/", "log.txt");
        JOptionPane.showMessageDialog(null, "Proses telah selesai pada iterasi ke-" + iterasi);
        return populasi.get(0).getGen();
    }
}
